package com.android.traffic.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class UserService {
	private DatabaseHelper dbHelper;
	private boolean isExit_username = false;
	private boolean isExit_password = false;

	private String mode = "";

	public UserService(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	// 登陆用
	/*
	 * 在登录的时候进行判断 (1)在输入用户名后，若不存在该用户，显示“不存在该用户” (2)若该用户存在，但密码输入错误，显示“密码错误”
	 * (3)用户名密码正确，登录成功
	 */
	public String login(String username, String password) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql_username = "select * from user where 用户名=?";
		String sql_password = "select * from user where 密码=?";
		String sql_username_password = "select * from user where 用户名=? and 密码=?";

		/**
		 * 让用户使用手机号、邮箱都可以进行登录；
		 * 
		 * 
		 */
		String sql_phone = "select * from user where 手机=?";
		String sql_mail = "select * from user where 邮箱=?";
		String sql_phone_password = "select * from user where 手机=? and 密码=?";
		String sql_mail_password = "select * from user where 邮箱=? and 密码=?";

		Cursor cursor_username = sdb.rawQuery(sql_username,
				new String[] { username });
		Cursor cursor_password = sdb.rawQuery(sql_password,
				new String[] { password });
		Cursor cursor_username_password = sdb.rawQuery(sql_username_password,
				new String[] { username, password });

		Cursor cursor_phone = sdb
				.rawQuery(sql_phone, new String[] { username });
		Cursor cursor_mail = sdb.rawQuery(sql_mail, new String[] { username });
		Cursor cursor_phone_password = sdb.rawQuery(sql_phone_password,
				new String[] { username, password });
		Cursor cursor_mail_password = sdb.rawQuery(sql_mail_password,
				new String[] { username, password });

		if (cursor_username.moveToFirst() == true
				|| cursor_phone.moveToFirst() == true
				|| cursor_mail.moveToFirst() == true) {
			isExit_username = true;// 表示该用户存在
		}

		// 在该用户存在的情况下；
		if (isExit_username == true) {
			if (cursor_username_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "用户名";
			} else if (cursor_phone_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "手机";
			} else if (cursor_mail_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "邮箱";
			}
		}

		if (isExit_username && isExit_password) {
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();

			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();

			return "登录成功";
		} else if (isExit_username && !isExit_password) {
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();
			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();
			return "密码错误";
		} else if (!isExit_username && !isExit_password) {
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();
			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();
			return "该用户不存在";
		}

		return "登录失败";
	}

	// 注册用
	public String register(String username, String password, String birthday,
			String sex, String registerDate, String phone, String mail) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into user(用户名,密码,生日,性别,注册日期,手机,邮箱) values(?,?,?,?,?,?,?)";
		Object obj[] = { username, password, birthday, sex, registerDate,
				phone, mail };

		// 在这里进行判断用户名是否重复
		String sql_username = "select * from user where 用户名=?";
		Cursor cursor = sdb.rawQuery(sql_username, new String[] { username });
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return "用户名已存在";
		}

		// 在这里进行判断密码是否大于等于6位
		String judge_password = password;
		int password_number = judge_password.length();// 密码位数
		if (password_number < 6) {
			return "密码小于六位";
		}

		// 要求密码为
		/*
		 * (1)数字 (2)大写字母 (3)小写字母 (4)标点符号
		 * 
		 * 至少为其中的两种
		 */

		// 从反面考虑，即排除全是数字、全是大写字母、全是小写字母、全是标点符号
		String regex_lower = "[\\p{Lower}]+";// 正则表达式 密码：小写字母
		String regex_upper = "[\\p{Upper}]+";// 大写字母
		String regex_number = "[\\p{Digit}]+";// 数字
		String regex_char = "[\\p{Punct}]+";// 标点符号

		if (password.matches(regex_number) || password.matches(regex_upper)
				|| password.matches(regex_lower)
				|| password.matches(regex_char)) {

			return "大写+小写+数字+字符（至少包含2种）";

		}

		sdb.execSQL(sql, obj);// 完成注册
		return "注册成功";
	}

	public String select_username(String username) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql_username_username = "select * from user where 用户名=?";
		Cursor cursor_username_username = sdb.rawQuery(sql_username_username,
				new String[] { username });

		String sql_phone_username = "select * from user where 手机=?";
		Cursor cursor_phone_username = sdb.rawQuery(sql_phone_username,
				new String[] { username });

		String sql_mail_username = "select * from user where 邮箱=?";
		Cursor cursor_mail_username = sdb.rawQuery(sql_mail_username,
				new String[] { username });

		String username_show = "";

		if (mode.equals("用户名")) {
			while (cursor_username_username.moveToNext()) {
				username_show = cursor_username_username
						.getString(cursor_username_username
								.getColumnIndex("用户名"));
			}
			cursor_username_username.close();
		} else if (mode.equals("手机")) {
			while (cursor_phone_username.moveToNext()) {
				username_show = cursor_phone_username
						.getString(cursor_phone_username.getColumnIndex("用户名"));
			}
			cursor_phone_username.close();
		} else if (mode.equals("邮箱")) {
			while (cursor_mail_username.moveToNext()) {
				username_show = cursor_mail_username
						.getString(cursor_mail_username.getColumnIndex("用户名"));
			}
			cursor_mail_username.close();
		}
		return username_show;
	}

	public String delete_account(String username, String password) {

		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql_username = "select * from user where 用户名=?";
		String sql_password = "select * from user where 密码=?";
		String sql_username_password = "select * from user where 用户名=? and 密码=?";

		String sql_phone = "select * from user where 手机=?";
		String sql_mail = "select * from user where 邮箱=?";
		String sql_phone_password = "select * from user where 手机=? and 密码=?";
		String sql_mail_password = "select * from user where 邮箱=? and 密码=?";

		Cursor cursor_username = sdb.rawQuery(sql_username,
				new String[] { username });
		Cursor cursor_password = sdb.rawQuery(sql_password,
				new String[] { password });
		Cursor cursor_username_password = sdb.rawQuery(sql_username_password,
				new String[] { username, password });

		Cursor cursor_phone = sdb
				.rawQuery(sql_phone, new String[] { username });
		Cursor cursor_mail = sdb.rawQuery(sql_mail, new String[] { username });
		Cursor cursor_phone_password = sdb.rawQuery(sql_phone_password,
				new String[] { username, password });
		Cursor cursor_mail_password = sdb.rawQuery(sql_mail_password,
				new String[] { username, password });

		if (cursor_username.moveToFirst() || cursor_phone.moveToFirst()
				|| cursor_mail.moveToFirst()) {
			isExit_username = true;// 表示该用户存在
		}

		// 在该用户存在的情况下；
		if (isExit_username) {
			if (cursor_username_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "用户名";
			} else if (cursor_phone_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "手机";
			} else if (cursor_mail_password.moveToFirst()) {
				isExit_password = true;// 在该用户存在的情况下，密码输入正确
				mode = "邮箱";
			}
		}

		if (isExit_username && isExit_password) {

			if (mode.equals("用户名")) {
				sdb.delete("user", "用户名 like ?", new String[] { username });
			} else if (mode.equals("手机")) {
				sdb.delete("user", "手机 like ?", new String[] { username });
			} else if (mode.equals("邮箱")) {
				sdb.delete("user", "邮箱 like ?", new String[] { username });
			}
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();
			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();
			return "执行注销";
		} else if (isExit_username && !isExit_password) {
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();
			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();
			return "密码错误";
		} else if (!isExit_username && !isExit_password) {
			cursor_username.close();
			cursor_password.close();
			cursor_username_password.close();
			cursor_phone.close();
			cursor_mail.close();
			cursor_phone_password.close();
			cursor_mail_password.close();
			return "该用户不存在";
		}
		return null;
	}

	public String modify(String username, String password, String birthday,
			String sex, String phone, String mail) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();

		// 在这里进行判断密码是否大于等于6位
		String judge_password = password;
		int password_number = judge_password.length();// 密码位数
		if (password_number < 6) {
			return "密码小于六位";
		}

		// 要求密码为
		/*
		 * (1)数字 (2)大写字母 (3)小写字母 (4)标点符号
		 * 
		 * 至少为其中的两种
		 */

		// 从反面考虑，即排除全是数字、全是大写字母、全是小写字母、全是标点符号
		String regex_lower = "[\\p{Lower}]+";// 正则表达式 密码：小写字母
		String regex_upper = "[\\p{Upper}]+";// 大写字母
		String regex_number = "[\\p{Digit}]+";// 数字
		String regex_char = "[\\p{Punct}]+";// 标点符号

		if (password.matches(regex_number) || password.matches(regex_upper)
				|| password.matches(regex_lower)
				|| password.matches(regex_char)) {

			return "大写+小写+数字+字符（至少包含2种）";

		}

		ContentValues values = new ContentValues();
		values.put("密码", password);
		values.put("生日", birthday);
		values.put("性别", sex);
		values.put("手机", phone);
		values.put("邮箱", mail);
		sdb.update("user", values, "用户名=?", new String[] { username });
		sdb.close();

		return "修改成功";
	}
}
