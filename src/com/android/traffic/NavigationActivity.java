package com.android.traffic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.android.traffic.R;

public class NavigationActivity extends Activity implements
		OnGetGeoCoderResultListener {

	private Context context;
	private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

	// 天安门坐标
	// double mLat1 = 39.915291;
	// double mLon1 = 116.403857;
	// 百度大厦坐标
	// double mLat2 = 40.056858;
	// double mLon2 = 116.308194;

	private EditText startAddress;
	private EditText endAddress;

	private String start_address;
	private String end_address;

	private double start_latitude;
	private double start_longitude;
	private double end_latitude;
	private double end_longitude;

	private static boolean isFirst = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		this.context = this;
		setContentView(R.layout.activity_navigation);
		initView();

		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
	}

	private void initView() {
		startAddress = (EditText) findViewById(R.id.id_startAddress);
		endAddress = (EditText) findViewById(R.id.id_endAddress);

	}

	/**
	 * 开始导航,点击导航按钮
	 * 
	 * @param view
	 */
	public void startNavi(View view) {
		// 获取编辑框中的起始城市和地址；
		start_address = startAddress.getText().toString().trim();
		end_address = endAddress.getText().toString().trim();

		// Geo搜索
		mSearch.geocode(new GeoCodeOption().city("").address(start_address));

	}// startNavi();

	// 返回的地理位置结果应该有两个，即返回2次。
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(context, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
			return;
		}

		if (isFirst == true) {
			start_latitude = result.getLocation().latitude;
			start_longitude = result.getLocation().longitude;

			Log.i("Navigation", "开始纬度：" + start_latitude + "开始经度："
					+ start_longitude);
			isFirst = false;
			mSearch.geocode(new GeoCodeOption().city("").address(end_address));

		} else if (isFirst == false) {
			end_latitude = result.getLocation().latitude;
			end_longitude = result.getLocation().longitude;
			Log.i("Navigation", "结束纬度：" + end_latitude + "结束经度："
					+ end_longitude);
			isFirst = true;

			Log.i("Navigation", "开始纬度：" + start_latitude + ";开始经度："
					+ start_longitude + "-----结束纬度：" + end_latitude + ";结束经度："
					+ end_longitude);

			LatLng pt1 = new LatLng(start_latitude, start_longitude);
			LatLng pt2 = new LatLng(end_latitude, end_longitude);
			// 构建 导航参数
			NaviPara para = new NaviPara();
			para.startPoint = pt1;
			para.startName = "从这里开始";
			para.endPoint = pt2;
			para.endName = "到这里结束";

			try {

				BaiduMapNavigation.openBaiduMapNavi(para, this);

			} catch (BaiduMapAppNotSupportNaviException e) {
				e.printStackTrace();
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						BaiduMapNavigation.getLatestBaiduMapApp(context);
					}
				});

				builder.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				builder.create().show();
			}

		}

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

}
