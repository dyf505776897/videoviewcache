package videoviewcache.dyf.com.videoviewcache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

import videoviewcache.dyf.com.videoviewcache.util.LogUtil;
import videoviewcache.dyf.com.videoviewcache.videocache.CacheListener;
import videoviewcache.dyf.com.videoviewcache.videocache.HttpProxyCacheServer;
import videoviewcache.dyf.com.videoviewcache.widget.CustomVideoView;

public class MainActivity extends AppCompatActivity implements CacheListener {
    public static final String TAG = "MainActivity";

    CustomVideoView customVideoView;
    String videoUrl = "http://112.5.254.248/hc.yinyuetai.com/uploads/videos/common/B2B6013BA4B457FA84D8FE5272EEA86D.flv";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        play();
    }

    private void initView(){
        customVideoView = (CustomVideoView) findViewById(R.id.cv);
    }



    private void play(){
        /**播放本地视频**/
        //		Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.lbc);
        //		customVideoView.playVideo(uri, currentPostion);
        /**获取参数，根据不同的参数播放不同的视频**/
        LogUtil.d("SpashFragment","开始播放视频=lbc");
        /**播放视频 添加了缓存**/
        HttpProxyCacheServer proxy = MainApp.getProxy(MainActivity.this);
        proxy.registerCacheListener(this, videoUrl);
        String proxyUrl = proxy.getProxyUrl(videoUrl);
        Log.d(TAG, "Use proxy url " + proxyUrl + " instead of original url " + videoUrl);
        customVideoView.setVisibility(View.VISIBLE);
        customVideoView.playVideo(proxyUrl);
    }


    @Override
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        Log.d(TAG, "Use proxy url " + cacheFile == null ? "null ext " : cacheFile.getPath() + " instead of original url " + url);
    }
}
