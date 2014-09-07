package com.StephenLee.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @descrption 图片服辅助类. 主要负责图片创建,图片缩放，图片压缩,图片裁剪，获取图片信息,类型转换等操作
 * 
 * @author Stephen
 */
public class BitmapUtils {

	/**
	 * @descrption 通过InputStream创建Bitmap.
	 *             使用BitmapFactory.decodeStream方法创建Bitmap，没有option参数，所以创建的Bitmap
	 *             的width和height为原图的尺寸
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
	 * @descrption Bitmap转bytes
	 * 
	 * @note 可以使用此方法把Bitmap转成bytes，bytes.length则为处理后的Bitmap所占内存大小
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
	 * @descrption 压缩图片
	 * 
	 * @note 压缩图片可以减少图片所占内存，但是压缩图片是一个耗时操作，如果图片像素点太多，可能会有很明显的延迟
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
