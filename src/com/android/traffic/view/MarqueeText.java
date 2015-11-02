package com.android.traffic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeText extends TextView {

	public MarqueeText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	// 返回始终为true,使该控件获得焦点；
	@Override
	public boolean isFocused() {
		return true;
	}

}
