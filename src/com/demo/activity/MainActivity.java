package com.demo.activity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.bitmaptest.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int collection = 1000000;
	List<byte[]> data = new ArrayList<byte[]>(collection);

	private Button mBtnGotoAdatper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBtnGotoAdatper = (Button)findViewById(R.id.btn_adapter);
		mBtnGotoAdatper.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,AdapterActivity.class);
				startActivity(intent);
			}
		});
		
		/*
		 * int pic = R.drawable.pic2;
		 * 
		 * Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
		 * pic); Log.d("test", "bitmap by resource width:" + bitmap.getWidth());
		 * Log.d("test", "bitmap by resource height:" + bitmap.getHeight());
		 * Log.d("test", "bitmap by resource bytesCount:" +
		 * bitmap.getByteCount());
		 * 
		 * InputStream stream = this.getResources().openRawResource(pic); Bitmap
		 * bitmapByStream = BitmapFactory.decodeStream(stream); Log.d("test",
		 * "bitmap by stream width:" + bitmapByStream.getWidth()); Log.d("test",
		 * "bitmap by stream height:" + bitmapByStream.getHeight());
		 * Log.d("test", "bitmap by stream bytesCount:" +
		 * bitmapByStream.getByteCount());
		 * 
		 * BitmapDrawable btimapDrawble = (BitmapDrawable)
		 * this.getResources().getDrawable(pic); Log.d("test",
		 * "btimapDrawble width:" + btimapDrawble.getBitmap().getWidth());
		 * Log.d("test", "btimapDrawble height:" +
		 * btimapDrawble.getBitmap().getHeight()); Log.d("test",
		 * "btimapDrawble bytesCount:" +
		 * btimapDrawble.getBitmap().getByteCount());
		 * 
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * bitmapByStream.compress(CompressFormat.JPEG, 5, baos);
		 * ByteArrayInputStream isBm = new
		 * ByteArrayInputStream(baos.toByteArray()); Bitmap bitmapCompress =
		 * BitmapFactory.decodeStream(isBm); Log.d("test",
		 * "bitmapCompress width:" + bitmapCompress.getWidth()); Log.d("test",
		 * "bitmapCompress height:" + bitmapCompress.getHeight()); Log.d("test",
		 * "bitmapCompress bytesCount:" + bitmapCompress.getByteCount());
		 * 
		 * byte[] oriBitmapSize = Bitmap2Bytes(bitmapByStream); byte[]
		 * comBitmapSize = Bitmap2Bytes(bitmapCompress); Log.d("test",
		 * "oriBitmapSize:" + oriBitmapSize.length); Log.d("test",
		 * "comBitmapSize:" + comBitmapSize.length);
		 * 
		 * ImageView imageView = (ImageView) findViewById(R.id.imageView);
		 * imageView.setImageBitmap(bitmap);
		 */
	}

	public byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	// private byte[] InputStreamToByte(InputStream is) throws IOException {
	// ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
	// int ch;
	// while ((ch = is.read()) != -1) {
	// bytestream.write(ch);
	// }
	// byte imgdata[] = bytestream.toByteArray();
	// bytestream.close();
	// return imgdata;
	// }

}
