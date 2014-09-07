package com.StephenLee.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @descrption ͼƬ��������. ��Ҫ����ͼƬ����,ͼƬ���ţ�ͼƬѹ��,ͼƬ�ü�����ȡͼƬ��Ϣ,����ת���Ȳ���
 * 
 * @author Stephen
 */
public class BitmapUtils {

	/**
	 * @descrption ͨ��InputStream����Bitmap.
	 *             ʹ��BitmapFactory.decodeStream��������Bitmap��û��option���������Դ�����Bitmap
	 *             ��width��heightΪԭͼ�ĳߴ�
	 * 
	 * @note
	 * 
	 * @param res
	 * @param resId
	 * @return
	 */
	public static Bitmap createOriBitmapByStream(Resources res, int resId) {
		Bitmap resultBitmap = null;
		InputStream is = null;
		try {
			is = res.openRawResource(resId);
			resultBitmap = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			// Ignore
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
		return resultBitmap;
	}

	/**
	 * @descrption Bitmapתbytes
	 * 
	 * @note ����ʹ�ô˷�����Bitmapת��bytes��bytes.length��Ϊ������Bitmap��ռ�ڴ��С
	 * 
	 * @param btimap
	 * @return
	 */
	public static byte[] Bitmap2Bytes(Bitmap btimap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		btimap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * @descrption ѹ��ͼƬ
	 * 
	 * @note ѹ��ͼƬ���Լ���ͼƬ��ռ�ڴ棬����ѹ��ͼƬ��һ����ʱ���������ͼƬ���ص�̫�࣬���ܻ��к����Ե��ӳ�
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compressBitmap(Bitmap bitmap, int quality) {
		Bitmap resultBitmap = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outStream);
		ByteArrayInputStream isBm = new ByteArrayInputStream(outStream.toByteArray());
		resultBitmap = BitmapFactory.decodeStream(isBm, null, null);
		return resultBitmap;
	}
}
