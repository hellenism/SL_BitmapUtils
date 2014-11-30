package com.demo.activity;

import com.example.bitmaptest.R;
import com.sl.BitmapUtils.BitmapUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.ImageView;

public class AdapterActivity extends Activity {

	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter);
		iv = (ImageView) findViewById(R.id.iv);

		Point targetSize = BitmapUtils.getBitmapWidthHeightByReferentSzie(
				getResources(), R.drawable.pic2, 720, 1280);
		Bitmap bitmap = BitmapUtils.createBitmapByCertainSpec(getResources(),
				R.drawable.pic2, targetSize.x, targetSize.y);
		iv.setImageBitmap(bitmap);
	}
}
