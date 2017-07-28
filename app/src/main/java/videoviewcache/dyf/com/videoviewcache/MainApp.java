package videoviewcache.dyf.com.videoviewcache;

import android.app.Application;
import android.content.Context;

import videoviewcache.dyf.com.videoviewcache.contacts.Constants;
import videoviewcache.dyf.com.videoviewcache.videocache.HttpProxyCacheServer;

/**
 * Created by dyf on 2017/7/28.
 */

public class MainApp extends Application {



    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        MainApp app = (MainApp) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(Constants.getVideoCacheDir(this))
                .build();
    }


}
