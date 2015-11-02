package com.android.traffic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.traffic.view.ArcMenu;
import com.android.traffic.view.ArcMenu.OnMenuItemClickListener;
import com.android.traffic.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private Context context;
	private ArcMenu mArcMenu;
	private GridView mGridView;
	private SimpleAdapter mSimpleAdapter;
	private List<Map<String, Object>> dataList;

	private static String welcome = "";
	private TextView welcomeTextView;

	private GestureOverlayView gestureOverlayView;

	private int[] image = { R.drawable.address_book, R.drawable.calendar,
			R.drawable.camera, R.drawable.clock, R.drawable.games_control,
			R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
			R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
			R.drawable.youtube };

	private String[] text = { "地图显示", "公交查询", "离线地图", "导航", "登录", "注册", "智能助手",
			"个人中心", "娱乐", "浏览器", "紧急拨号", "关于" };

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Intent intent_to_map = new Intent(context, MapActivity.class);
				startActivity(intent_to_map);

				break;

			case 2:
				Intent intent_to_bus = new Intent(context, BusActivity.class);
				startActivity(intent_to_bus);

				break;

			case 3:
				Intent intent_to_download = new Intent(context,
						DownloadActivity.class);
				startActivity(intent_to_download);

				break;

			case 4:
				Intent intent_to_navigation = new Intent(context,
						NavigationActivity.class);
				startActivity(intent_to_navigation);

				break;

			case 5:
	
				break;

			case 6:

				break;

			}

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		this.context = this;

		initView();
		initEvent();
		initGridView();
		initWelcome();

		// 在这里读取当前是否联网，若没有联网，则以对话框的形式通知用户；
		isNetworkConnecting();

		// 进行手势识别
		initGesture();

	}

	private void initGesture() {
		final GestureLibrary gestureLibrary = GestureLibraries.fromRawResource(
				context, R.raw.gestures);
		gestureLibrary.load();

		gestureOverlayView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						ArrayList<Prediction> arrayList = gestureLibrary
								.recognize(gesture);

						Prediction prediction = arrayList.get(0);
						if (prediction.score >= 3.0) {
							if (prediction.name.equals("back")) {
								Toast.makeText(context, "退出",
										Toast.LENGTH_SHORT).show();
								finish();
							}

						} else {
							Toast.makeText(context, "手势不存在", Toast.LENGTH_SHORT)
									.show();
						}

					}
				});

	}// initGesture();

	private void isNetworkConnecting() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		// 联网
		if (networkInfo != null) {
			// 不做任何操作；

		}
		// 未联网，弹出对话框进行提醒；
		// 并在对话框中的按钮中进行WiFi设置，可以打开WiFi；
		else {
			Builder builder = new Builder(context);
			builder.setTitle("网络状态");
			builder.setMessage("您当前未联网，请连接网络！");

			builder.setPositiveButton("打开WiFi", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					WifiManager wifiManager = (WifiManager) context
							.getSystemService(WIFI_SERVICE);
					wifiManager.setWifiEnabled(true);
					Toast.makeText(context, "已经为您打开WiFi", Toast.LENGTH_SHORT)
							.show();

				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();

		}

	}// isNetworkConnecting();

	private void initWelcome() {
		welcomeTextView = (TextView) findViewById(R.id.id_welcome);
		Intent intent_login = getIntent();
		welcome = intent_login.getStringExtra("welcome");

		if (welcome == null) {
			welcomeTextView.setText("欢迎您使用交通助手");
		} else {
			welcomeTextView.setText(welcome + "  欢迎您使用交通助手");
		}

	}

	private void initGridView() {
		mGridView = (GridView) findViewById(R.id.id_gridview);
		mGridView.setOnItemClickListener(this);
		dataList = new ArrayList<Map<String, Object>>();
		mSimpleAdapter = new SimpleAdapter(context, getData(),
				R.layout.gridview_item, new String[] { "image", "text" },
				new int[] { R.id.id_pic, R.id.id_text });
		mGridView.setAdapter(mSimpleAdapter);

	}

	private List<Map<String, Object>> getData() {
		for (int i = 0; i < image.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", image[i]);
			map.put("text", text[i]);
			dataList.add(map);

		}

		return dataList;
	}

	/**
	 * 
	 * 需要在菜单的点击事件中，进行Activity的延迟跳转。 使菜单消失的动画能全部显示完成后再进行跳转
	 * 
	 */
	private void initEvent() {
		mArcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				Toast.makeText(MainActivity.this, pos + ":" + view.getTag(),
						Toast.LENGTH_SHORT).show();

				final Message message = new Message();

				switch (pos) {
				case 1:
					// Intent intent_to_map = new Intent(context,
					// MapActivity.class);
					// startActivity(intent_to_map);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 1;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				case 2:
					// Intent intent_to_bus = new Intent(context,
					// BusActivity.class);
					// startActivity(intent_to_bus);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 2;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				case 3:
					// Intent intent_to_download = new Intent(context,
					// DownloadActivity.class);
					// startActivity(intent_to_download);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 3;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				case 4:
					// Intent intent_to_navigation = new Intent(context,
					// NavigationActivity.class);
					// startActivity(intent_to_navigation);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 4;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				case 5:
					// Intent intent_to_login = new Intent(context,
					// LoginActivity.class);
					// startActivity(intent_to_login);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 5;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				case 6:
					// Intent intent_to_chat = new Intent(context,
					// ChatActivity.class);
					// startActivity(intent_to_chat);

					new Thread() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
								message.what = 6;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

					break;

				}

			}
		});

	}// initEvent();

	private void initView() {
		mArcMenu = (ArcMenu) findViewById(R.id.id_right_bottom);
		gestureOverlayView = (GestureOverlayView) findViewById(R.id.id_gestureOverlayView);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			if (mArcMenu.isOpen()) {
				mArcMenu.toggleMenu(600);
			}

		}

		return super.onTouchEvent(event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			Intent intent_map = new Intent(context, MapActivity.class);
			startActivity(intent_map);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;
		case 1:
			Intent intent_bus = new Intent(context, BusActivity.class);
			startActivity(intent_bus);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;
		case 2:
			Intent intent_download = new Intent(context, DownloadActivity.class);
			startActivity(intent_download);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;
		case 3:
			Intent intent_navigate = new Intent(context,
					NavigationActivity.class);
			startActivity(intent_navigate);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;
		case 4:

			break;

		case 5:

			break;
		case 6:
//			Intent intent_chat = new Intent(context, ChatActivity.class);
//			startActivity(intent_chat);
//			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;

		case 7:

			break;

		case 8:
//			Intent intent_face = new Intent(context, FaceActivity.class);
//			startActivity(intent_face);
//			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;

		case 9:
			Intent intent_browser = new Intent(context, BrowserActivity.class);
			startActivity(intent_browser);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;

		case 10:
			Intent intent_dial = new Intent(context, DialActivity.class);
			startActivity(intent_dial);
			overridePendingTransition(R.anim.come_in, R.anim.come_out);

			break;

		case 11:

			Builder builder = new Builder(context);
			builder.setTitle("关于");
			builder.setMessage("城市交通智能助手" + "\n" + "版本：1.0" + "\n" + "开发者：陈宇峰");
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();

			break;

		}

	}// onItemClick()

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}

		return true;
	}

}
