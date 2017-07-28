package videoviewcache.dyf.com.videoviewcache.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 调试用
 * 
 * @author huanglb
 * 
 */
public class LogUtil {
    // 关闭debug
    public static final boolean DEBUBABLE = true;

    private static final String TAG = "birdslove";

    /**
     * 保存日志到文件中
     */
    private final boolean isSaveToFile = false;
    
    /**
     * 日志保存路径，sdcard/(UMS_APP_NAME设置的名称)/Log.txt
     */
    private static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.hztuen.dogfood/";
    private static final String LOG_PATH = LOG_DIR + "Log.txt";
    
    private static LogUtil instance = new LogUtil();

    public static LogUtil getLogger() {
        return instance;
    }

    /**
     * log.i
     */
    public void info(String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.i(TAG, message);
        }
    }

    public static void i(String msg) {
        instance.info(msg);
    }
    /**
     * @author HUANGLB
     * @param msg
     */
    public static void i(String tag,String msg) {
        instance.info(tag,msg);
    }

    public static void i(String tag, Object msgObj) {
        if (msgObj != null) {
            try {
                final Gson gson = new Gson();   
                final String objLog = gson.toJson(msgObj, new TypeToken<Object>() {}.getType());
                instance.info(tag, objLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void info(String tag,String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.i(tag, message);
        }
    }
    
    public static void i(Exception e) {
        instance.info(e != null ? e.toString() : "null");
    }

    /**
     * log.v
     */
    public void verbose(String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.v(TAG, message);
        }
    }
    
    public void verbose(String tag, String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.v(tag, message);
        }
    }

    public static void v(String msg) {
        instance.verbose(msg);
    }
    
    public static void v(String tag, String msg) {
        instance.verbose(tag, msg);
    }
    
    public static void v(String tag, Object msgObj) {
        if (msgObj != null) {
            try {
                final Gson gson = new Gson();   
                final String objLog = gson.toJson(msgObj, new TypeToken<Object>() {}.getType());
                instance.verbose(tag, objLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void v(Exception e) {
        instance.verbose(e != null ? e.toString() : "null");
    }

    /**
     * log.d
     */
    public void debug(String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.d(TAG, message);
        }
    }
    
    public void debug(String tag, String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.d(tag, message);
        }
    }

    public static void d(String msg) {
        instance.debug(msg);
    }
    
    public static void d(String tag, String msg) {
        instance.debug(tag, msg);
    }
    
    public static void d(String tag, Object msgObj) {
        if (msgObj != null) {
            try {
                final Gson gson = new Gson();   
                final String objLog = gson.toJson(msgObj, new TypeToken<Object>() {}.getType());
                instance.debug(tag, objLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(Exception e) {
        instance.debug(e != null ? e.toString() : "null");
    }

    /**
     * log.warn
     * 
     * @param msg
     */
    public void warn(String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.w(TAG, message);
        }
    }

    public static void w(String msg) {
        instance.warn(msg);
    }

    public static void w(Exception e) {
        instance.warn(e != null ? e.toString() : "null");
    }
    
    /**
     * log.e
     * 
     * @param msg
     */
    public void error(String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.e(TAG, message);
        }
    }
    
    public void error(String tag, String msg) {
        if (DEBUBABLE) {
            final String message = createMessage(msg);
            Log.e(tag, message);
        }
    }
    
    public static void e(String msg) {
        instance.error(msg);
    }
    
    public static void e(String tag, String msg) {
        instance.error(tag, msg);
    }
    
    public static void e(String tag, Object msgObj) {
        if (msgObj != null) {
            try {
                final Gson gson = new Gson();   
                final String objLog = gson.toJson(msgObj, new TypeToken<Object>() {}.getType());
                instance.error(tag, objLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void e(Exception e) {
        instance.error(e);
    }

    /**
     * log.error
     * 
     * @param e
     */
    public void error(Exception e) {
        if (DEBUBABLE) {
            final StringBuffer sb = new StringBuffer();
            final String name = getFunctionName();
            final StackTraceElement[] sts = e.getStackTrace();

            if (name != null) {
                sb.append(name + " - " + e + "\r\n");
            } else {
                sb.append(e + "\r\n");
            }
            if (sts != null && sts.length > 0) {
                for (final StackTraceElement st : sts) {
                    if (st != null) {
                        sb.append("[ " + st.getFileName() + ":" + st.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(TAG, sb.toString());
        }
    }
    
    /**
     * 获取函数名称
     */
    private String getFunctionName() {
        final StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }

        for (final StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return "[" + Thread.currentThread().getName() + "("
                    + Thread.currentThread().getId() + "): " + st.getFileName()
                    + ":" + st.getLineNumber() + "]";
        }

        return null;
    }

    private String createMessage(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return null;
        }
        
        final String functionName = getFunctionName();
        final String message = (functionName == null ? msg : (functionName + " - " + msg));
        
        if (isSaveToFile) {
            FileUtils.writeStringToFile(msg + "\r\n", LOG_DIR, LOG_PATH, true);
        }
        
        return message;
    }
    
    /**
     * 保存信息到文件
     * 
     * @param msg
     */
    public static void saveMessage(String msg) {
        if (DEBUBABLE) {
            FileUtils.writeStringToFile(msg + "\r\n", LOG_PATH, true);
        }
    }

}
