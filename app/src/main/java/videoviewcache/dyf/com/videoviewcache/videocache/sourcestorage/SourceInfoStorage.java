package videoviewcache.dyf.com.videoviewcache.videocache.sourcestorage;

import videoviewcache.dyf.com.videoviewcache.videocache.SourceInfo;

/**
 * Storage for {@link SourceInfo}.
 *
 * @author Alexey Danilov (danikula@gmail.com).
 */
public interface SourceInfoStorage {

    SourceInfo get(String url);

    void put(String url, SourceInfo sourceInfo);

    void release();
}
