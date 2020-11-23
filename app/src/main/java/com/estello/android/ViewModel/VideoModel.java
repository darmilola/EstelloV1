package com.estello.android.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoModel implements Parcelable {

    private String videoId;
    private String videoUrl;
    private String fileSize;
    private String videoThumbnailUrl;
    private String playbackCacheID;
    private long playbackPosition;

    public VideoModel() {
    }

    protected VideoModel(Parcel in) {
        videoId = in.readString();
        videoUrl = in.readString();
        fileSize = in.readString();
        videoThumbnailUrl = in.readString();
        playbackCacheID = in.readString();
        playbackPosition = in.readLong();
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel in) {
            return new VideoModel(in);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

    public void setPlaybackCacheID(String playbackCacheID) {
        this.playbackCacheID = playbackCacheID;
    }

    public void setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    public long getPlaybackPosition() {
        return playbackPosition;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setVideoThumbnailUrl(String videoThumbnailUrl) {
        this.videoThumbnailUrl = videoThumbnailUrl;
    }

    public String getPlaybackCacheID() {
        return playbackCacheID;
    }

    public String getVideoThumbnailUrl() {
        return videoThumbnailUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoId);
        dest.writeString(videoUrl);
        dest.writeString(fileSize);
        dest.writeString(videoThumbnailUrl);
        dest.writeString(playbackCacheID);
        dest.writeLong(playbackPosition);
    }
}
