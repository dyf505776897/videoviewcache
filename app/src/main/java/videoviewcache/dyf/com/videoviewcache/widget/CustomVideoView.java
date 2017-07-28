package videoviewcache.dyf.com.videoviewcache.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 可以播放视频的View
 */

public class CustomVideoView extends VideoView {

    private Context mContext;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * 播放视频
     *
     * @param uri 播放地址
     */
    public void playVideo(Uri uri) {
        playVideo(uri, 0);
    }

    /**
     * 播放视频
     *
     * @param uri 播放地址
     */
    public void playVideo(Uri uri, int position) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        /**设置播放路径**/
        setVideoURI(uri);
        seekTo(position);
        /**开始播放**/
        start();
        setOnPreparedListener(mp -> {
            /**设置循环播放**/
            mp.setLooping(true);
        });
        setOnErrorListener((mp, what, extra) -> true);
    }


    public void playVideo(String url) {
        playVideo(url, 0);
    }

    /**
     * 播放视频
     *
     * @param url 播放地址
     */
    public void playVideo(String url, int position) {
        if (url == null) {
            throw new IllegalArgumentException("url can not be null");
        }

        /**设置播放路径**/
        setVideoPath(url);
        seekTo(position);
        /**开始播放**/
        start();
        setOnPreparedListener(mp -> {
            /**设置循环播放**/
            mp.setLooping(true);
        });
        setOnErrorListener((mp, what, extra) -> true);
    }
}
