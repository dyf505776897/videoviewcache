package videoviewcache.dyf.com.videoviewcache.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 ******************************************
 * @文件名称	:  FileUtils.java
 * @创建时间	: 2015/11/19
 * @文件描述	: 文件工具类
 ******************************************
 */
public class FileUtils {
	private final static String TAG = "FileUtils";
	
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/formats/";
	
	
	public static void saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG"); 
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	
	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	
	
	
	/**
	 * 读取表情配置文件
	 * 
	 * @param context
	 * @return
	 */
	public static List<String> getEmojiFile(Context context) {
		try {
			List<String> list = new ArrayList<String>();
			InputStream in = context.getResources().getAssets().open("emoji"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				list.add(str);
			}

			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	/**
//	 * 读取礼物配置文件
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static List<ChatGift> getGiftFile(Context context) {
//		 
//			String resture = Util.getFromAssets(context,"gift.json");
//			Gson gson = new Gson();
//		    ChatGiftInfo mPersonInfo = gson.fromJson(resture, ChatGiftInfo.class);
//			return mPersonInfo.getList();
//		 
//	}
	
	/**
	 * 检查SD卡是否存在
	 * 
	 * @return
	 */
	public static boolean checkSDCard() {
		final String status = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(status)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getExternalDir(){
		if (checkSDCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();//获取跟目录 
		}
		return "";
	}

	
	/**
	 * 获取可以使用的缓存目录(默认目录名/yyApp/)
	 * @param context
	 * @return
	 */
	public static File getDiskCacheDir(Context context) {
		final String cachePath = checkSDCard() ? getExternalCacheDir(context).getPath() : getAppCacheDir(context);

        File cacheDirFile = new File(cachePath);
        if (!cacheDirFile.exists()) {
            cacheDirFile.mkdirs();
        }

        return cacheDirFile;
	}
	 
	
    /**
     * 获取程序外部的缓存目录
     * @param context
     * @return
     */
     public static File getExternalCacheDir(Context context) {
     	// 这个sd卡中文件路径下的内容会随着，程序卸载或者设置中清除缓存后一起清空
         final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
         return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
     }
     
 	/**
 	 * 获取安装在用户手机上的com.youyuan.yyapp下的cache目录
 	 * 
 	 * @return cache path
 	 */
 	public static String getAppCacheDir(Context context) {
 		return context.getCacheDir().getPath();
 	}
 	
    /**
     * 获取文件名
     * 
     * @param path
     *            全路径
     * @return
     */
    public static String getFileName(String path) {
    	if (!TextUtils.isEmpty(path)) {
    		return path.substring(path.lastIndexOf(File.separator) + 1);
    	}
    	return "";
    }
    
    /**
     * 获取文件路径空间大小
     * @param path
     * @return
     */
    public static long getUsableSpace(File path) {
    	try{
    		 final StatFs stats = new StatFs(path.getPath());
    	     return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    	}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
       
    }

    /**
	 * 空间大小单位格式化
	 * @param size
	 * @return
	 */
	public static String formatSize(long size) {
        String suffix = null;
        float fSize=0;

        if (size >= 1024) {
            suffix = "KB";
            fSize=size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }
        
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
 	
 	/**
     * 获取文件所在的文件路径
     * 
     * @param path
     * @return
     */
    public static String getFilePath(String path) {
        return path.substring(0, path.lastIndexOf(File.separator) + 1);
    }
    
    /**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
	    if (LogUtil.DEBUBABLE) {
	        LogUtil.e(TAG,"deleteFile path " + filePath);
	    }
		
		if (!TextUtils.isEmpty(filePath)) {
			final File file = new File(filePath);
			if (file.exists()) {
				return file.delete();
			}
		}
		return false;
	}
	
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
     * 保存文件
     * 
     * @param content
     * @param fileName
     * @param isAppend
     * @return
     */
    public static boolean writeStringToFile(String content, String fileName, boolean isAppend) {
        return writeStringToFile(content, "", fileName, isAppend);
    }

    
    public static boolean writeStringToFile(String content,
            String directoryPath, String fileName, boolean isAppend) {
        if (!TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(directoryPath)) {// 是否需要创建新的目录
                final File threadListFile = new File(directoryPath);
                if (!threadListFile.exists()) {
                    threadListFile.mkdirs();
                }
            }
            boolean bFlag = false;
            final int iLen = content.length();
            final File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                final FileOutputStream fos = new FileOutputStream(file,
                        isAppend);
                byte[] buffer = new byte[iLen];
                try {
                    buffer = content.getBytes();
                    fos.write(buffer);
                    if (isAppend) {
                        fos.write("||".getBytes());
                    }
                    fos.flush();
                    bFlag = true;
                } catch (IOException ioex) {
                    if (LogUtil.DEBUBABLE) {
                        ioex.printStackTrace();
                    }
                } finally {
                    fos.close();
                    buffer = null;
                }
            } catch (Exception ex) {
                if (LogUtil.DEBUBABLE) {
                    ex.printStackTrace();
                }
            } catch (OutOfMemoryError o) {
                if (LogUtil.DEBUBABLE) {
                    o.printStackTrace();
                }
            }
            return bFlag;
        }
        return false;
    }
}
