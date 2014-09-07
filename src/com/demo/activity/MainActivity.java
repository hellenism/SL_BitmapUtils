package com.demo.activity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.bitmaptest.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int collection = 1000000;
	List<byte[]> data = new ArrayList<byte[]>(collection);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int pic = R.drawable.pic2;

		// 与dpi有关
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), pic);
		Log.d("test", "bitmap by resource width:" + bitmap.getWidth());
		Log.d("test", "bitmap by resource height:" + bitmap.getHeight());
		Log.d("test", "bitmap by resource bytesCount:" + bitmap.getByteCount());

		// 与dpi无关
		InputStream stream = this.getResources().openRawResource(pic);
		Bitmap bitmapByStream = BitmapFactory.decodeStream(stream);
		Log.d("test", "bitmap by stream width:" + bitmapByStream.getWidth());
		Log.d("test", "bitmap by stream height:" + bitmapByStream.getHeight());
		Log.d("test", "bitmap by stream bytesCount:" + bitmapByStream.getByteCount());

		// 与dpi有关
		BitmapDrawable btimapDrawble = (BitmapDrawable) this.getResources().getDrawable(pic);
		Log.d("test", "btimapDrawble width:" + btimapDrawble.getBitmap().getWidth());
		Log.d("test", "btimapDrawble height:" + btimapDrawble.getBitmap().getHeight());
		Log.d("test", "btimapDrawble bytesCount:" + btimapDrawble.getBitmap().getByteCount());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmapByStream.compress(CompressFormat.JPEG, 5, baos);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmapCompress = BitmapFactory.decodeStream(isBm);
		Log.d("test", "bitmapCompress width:" + bitmapCompress.getWidth());
		Log.d("test", "bitmapCompress height:" + bitmapCompress.getHeight());
		Log.d("test", "bitmapCompress bytesCount:" + bitmapCompress.getByteCount());
		
		byte[] oriBitmapSize = Bitmap2Bytes(bitmapByStream);
		byte[] comBitmapSize = Bitmap2Bytes(bitmapCompress);
		Log.d("test", "oriBitmapSize:" + oriBitmapSize.length);
		Log.d("test", "comBitmapSize:" + comBitmapSize.length);

		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageBitmap(bitmap);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
