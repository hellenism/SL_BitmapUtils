package com.sl.BitmapUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * @descrption
 * 
 * @author Stephen
 */
public class BitmapUtils {
	/**
	 * 使用指定的宽高创建Bitmap
	 * 
	 * 对此图片进行适当的缩放处理
	 * 
	 * @param res
	 * @param resId
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap createBitmapByCertainSpec(Resources res, int resId,
			int w, int h) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		// 先解码图片的边框，而非整张图片，从而可以得到图片的宽高
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		options.inJustDecodeBounds = false;
		options.inTempStorage = new byte[1024];

		// 此时options已经得到了resId对于图片的尺寸
		// 计算isSampleSize
		options.inSampleSize = computeSampleSize(options, -1, w * h);
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inDither = false;
		options.inPurgeable = true;

		Bitmap sourceBitmap = null;
		try {
			sourceBitmap = BitmapFactory.decodeResource(res, resId, options);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		return sourceBitmap;
	}

	/**
	 * @descrption 创建原始尺寸图
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
	 * 获取Bitmap所占内存大小，单位为byte
	 * 
	 * @param bitmap
	 * @return
	 */
	public static int getBitmapSizeInMemery(Bitmap bitmap) {
		int result = 0;
		if (null == bitmap)
			return result;
		return bitmap.getByteCount();
	}

	/**
	 * 获取图片的实际宽高
	 * 
	 * @param res
	 * @param resId
	 * @return Point对象，x表示宽，y表示高
	 */
	public static Point getBitmapWidthAndHeight(Resources res, int resId) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 先解码图片的边框，而非整张图片，从而可以得到图片的宽高
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		return new Point(options.outWidth, options.outHeight);
	}

	/**
	 * 根据设计基准尺寸，获取当前设备下，目标图片的正确缩放尺寸
	 * 
	 * @param res
	 *            Resource , 用于获取当前设备的屏幕尺寸
	 * @param resId
	 *            图片资源id
	 * @param compareWith
	 *            设计图基准宽,通常是720
	 * @param compareHeight
	 *            设计图基准高,通常是1280
	 * @return 目标图片在当前设备下的正确缩放尺寸
	 */
	public static Point getBitmapWidthHeightByReferentSzie(Resources res,
			int resId, int compareWith, int compareHeight) {
		Point resultSize = new Point();
		Point targetImageSize = getBitmapWidthAndHeight(res, resId);
		int screenWidth = res.getDisplayMetrics().widthPixels;
		int screenHeight = res.getDisplayMetrics().heightPixels;
		float scaleRateOfWidth = screenWidth / compareWith;
		float scaleRateOfHeight = screenHeight / compareHeight;
		int scaledImgWidth = (int) (targetImageSize.x * scaleRateOfWidth);
		int scaledImgHeight = (int) (targetImageSize.y * scaleRateOfHeight);
		resultSize.x = scaledImgWidth;
		resultSize.y = scaledImgHeight;
		return resultSize;
	}

	/**
	 * Bitmap转Bytes
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
	 * 压缩图片质量
	 * 
	 * @param bitmap
	 * @param quality
	 * @return
	 */
	public static Bitmap compressBitmap(Bitmap bitmap, int quality) {
		Bitmap resultBitmap = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outStream);
		ByteArrayInputStream isBm = new ByteArrayInputStream(
				outStream.toByteArray());
		resultBitmap = BitmapFactory.decodeStream(isBm, null, null);
		return resultBitmap;
	}

	// ------ private ------
	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}
}
