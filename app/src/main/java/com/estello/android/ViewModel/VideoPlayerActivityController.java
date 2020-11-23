/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.estello.android.ViewModel;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;




import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.PlayerProviderImpl;
import com.estello.android.Arvi.model.PlaybackInfo;
import com.estello.android.Arvi.model.VolumeInfo;
import com.estello.android.Arvi.player.Player;
import com.estello.android.Arvi.util.cache.PlaybackInfoCache;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.Playable;
import com.estello.android.Arvi.widget.PlaybackState;
import com.estello.android.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;


import java.util.Random;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public abstract class VideoPlayerActivityController implements Playable,
        Player.AttachmentStateDelegate, Player.EventListener {


    public static final String TAG = "PlayableItemViewHolder";
    private static final float DEFAULT_TRIGGER_OFFSET = 0.5f;
    public  PlayerView mPlayerView;
    public boolean isNewPlayer = false;
    public String playBackCacheID = "";
    private Player player;
    private Player mPlayer = null;
    private boolean isInReadyState = false;
    private int positionInAdapter;
    private boolean isPausedByUser = false;
 /*   private PlayBackStarted playBackStarted;
    private OnReadyState onReadyState;
    private onPlayBackPause onPlayBackPause;
    private onPlayBackStopped onPlayBackStopped;*/
    Context context;
    private PlayBackStateListener playBackStateListener;
    private Config config;




    public interface  PlayBackStateListener{
        public void OnStart();
        public void onBuffering();
        public void onReady();
        public void onPause();
        public void onStop();
        public void onError();
    }

    public VideoPlayerActivityController(Context context,PlayerView playerView,Config config) {
         this.context = context;
         mPlayerView = playerView;
         isNewPlayer = true;
         this.config = config;
    }


    public void setPlayBackStateListener(PlayBackStateListener playBackStateListener) {
        this.playBackStateListener = playBackStateListener;
    }

    @Override
    public final void start() {
        if(!isTrulyPlayable()) {
            return;
        }
        if(startPlayer()) {
            onStateChanged((getPlaybackState() == Player.PlaybackState.READY) ? PlaybackState.READY : PlaybackState.STARTED);
        }
    }


    public void setPausedByUser(boolean pausedByUser) {
        isPausedByUser = pausedByUser;
    }

    public boolean isPausedByUser() {
        return isPausedByUser;
    }

    @Override
    public final void restart() {
        if(!isTrulyPlayable()) {
            return;
        }
        restartPlayer();
        onStateChanged(PlaybackState.RESTARTED);
    }

    @Override
    public final void pause() {
        if (!isTrulyPlayable()) {
            return;
        }
        pausePlayer();
        if (isPausedByUser()) {
            onStateChanged(PlaybackState.PAUSED);
        }
    }
    @Override
    public final void stop() {
        if(!isTrulyPlayable()) {
            return;
        }
        stopPlayer();
        onStateChanged(PlaybackState.STOPPED);
    }
    @Override
    public final void release() {
        if(!isTrulyPlayable()) {
            return;
        }
        releasePlayer();
        onStateChanged(PlaybackState.STOPPED);
    }
    private boolean startPlayer() {

        // creating/updating the PlaybackInfo for this particular Playable
        final Player player = getOrInitPlayer();
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final VolumeInfo volumeInfo = playbackInfo.getVolumeInfo();
        setPlaybackInfo(playbackInfo);
        // determining whether the current Playable should play this time
        final boolean shouldPlay = (isLooping() || !playbackInfo.isEnded());

        //preparing the Player
        player.init();
        player.attach(mPlayerView);
        player.getVolumeController().setVolume(volumeInfo.getVolume());
        player.getVolumeController().setMuted(volumeInfo.isMuted());
        player.setMediaSource(createMediaSource());
        player.setAttachmentStateDelegate(this);
        player.addEventListener(this);

        // performing the playing related operations (if necessary)
        if(shouldPlay) {
            player.seek(playbackInfo.getPlaybackPosition());
            player.prepare(false);
            player.play();
        }
        isNewPlayer = false;
        return shouldPlay;
    }




    private void restartPlayer() {
        // updating the PlaybackInfo
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.setPlaybackPosition(0);

        final VolumeInfo volumeInfo = playbackInfo.getVolumeInfo();

        setPlaybackInfo(playbackInfo);

        // preparing the Player
        final Player player = getOrInitPlayer();
        player.init();
        player.attach(mPlayerView);
        player.getVolumeController().setVolume(volumeInfo.getVolume());
        player.getVolumeController().setMuted(volumeInfo.isMuted());
        player.setMediaSource(createMediaSource());
        player.setAttachmentStateDelegate(this);
        player.removeEventListener(this);
        player.addEventListener(this);
        player.seek(playbackInfo.getPlaybackPosition());
        player.prepare(false);
        player.play();
    }




    private void pausePlayer() {
        final Player player = getPlayer();
        final PlaybackInfo playbackInfo = getPlaybackInfo();

        if (player != null) {
            player.pause();
            player.removeEventListener(this);
            playbackInfo.setPlaybackPosition(player.getPlaybackPosition());
            setPlaybackInfo(playbackInfo);
        }
    }





    private void stopPlayer() {

        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        if(player != null) {
            player.pause();
            player.detach(mPlayerView);
            player.stop(true);
            player.setAttachmentStateDelegate(null);
            player.removeEventListener(this);
            playbackInfo.setPlaybackPosition(0L);
            setPlaybackInfo(playbackInfo);
        }
    }




    private void releasePlayer() {
        final Player player = getPlayer();
        unregisterPlayer();
        removePlaybackInfo();

        if(player != null) {
            player.pause();
            player.stop(true);
            player.detach(mPlayerView);
            player.setAttachmentStateDelegate(null);
            player.removeEventListener(this);
        }
    }




    @Override
    public final void seekTo(long positionInMillis) {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        if(player != null) {
            player.seek(positionInMillis);
            playbackInfo.setPlaybackPosition(positionInMillis);
            setPlaybackInfo(playbackInfo);
        }
    }




    @Override
    public final long getPlaybackPosition() {
        //final Player player = getPlayer();
        return ((player != null) ? player.getPlaybackPosition() : 0);
    }




    @Override
    public long getDuration() {
        if(isNewPlayer){

            isNewPlayer = false;

        }
        player = getPlayer();
        return ((player != null) ? player.getDuration() : 0);
    }

    @Override
    public final View getPlayerView() {
        return mPlayerView;
    }


    private Player getPlayer() {
        return PlayerProviderImpl.getInstance(context).getPlayer(getConfig(), getPlayBackCacheID());
    }

    private Player getOrInitPlayer() {
        return PlayerProviderImpl.getInstance(context).getOrInitPlayer(getConfig(), getPlayBackCacheID());
    }

    private void unregisterPlayer() {
        PlayerProviderImpl.getInstance(context).unregister(getConfig(), getPlayBackCacheID());
    }

    private MediaSource createMediaSource() {
        return PlayerProviderImpl.getInstance(context).createMediaSource(
                getConfig(),
                Uri.parse(getUrl()),
                isLooping()
        );
    }


    public void setInReadyState(boolean inReadyState) {
        isInReadyState = inReadyState;
    }
    public boolean getInReadyState(){
        return isInReadyState;
    }
    private void setPlaybackInfo(PlaybackInfo playbackInfo) {
        PlaybackInfoCache.getInstance(isNewPlayer,playBackCacheID).put(getPlayBackCacheID(), playbackInfo);
    }

    @Override
    public final PlaybackInfo getPlaybackInfo() {
        return PlaybackInfoCache.getInstance(isNewPlayer,getPlayBackCacheID()).get(getPlayBackCacheID(), new PlaybackInfo());
    }

    private void removePlaybackInfo() {
        PlaybackInfoCache.getInstance(isNewPlayer,getPlayBackCacheID()).remove(getPlayBackCacheID());
    }




    private int getPlaybackState() {
        final Player player = getPlayer();
        return ((player != null) ? player.getPlaybackState() : Player.PlaybackState.IDLE);
    }




    @NonNull
    @Override
    public Config getConfig() {
        return this.config;
    }




    @NonNull
    @Override
    public String getTag() {

        return  "";
    }




    @NonNull
    @Override
    public final String getKey() {

        return  getPlayBackCacheID();
    }





    @FloatRange(from = 0.0, to = 1.0)
    protected float getTriggerOffset() {
        return DEFAULT_TRIGGER_OFFSET;
    }




    protected final void setVolume(@FloatRange(from = 0.0, to = 1.0) float audioVolume) {
        // creating/updating the corresponding Playback Info
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.getVolumeInfo().setVolume(audioVolume);

        setPlaybackInfo(playbackInfo);

        // updating the Player-related state (if necessary)
        final Player player = getPlayer();

        if(player != null) {
            player.getVolumeController().setVolume(audioVolume);
        }
    }





    @FloatRange(from = 0.0, to = 1.0)
    protected final float getVolume() {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        return ((player != null) ? player.getVolumeController().getVolume() : playbackInfo.getVolumeInfo().getVolume());
    }





    public final void setMuted(boolean isMuted) {
        // creating/updating the corresponding Playback Info
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.getVolumeInfo().setMuted(isMuted);
        setPlaybackInfo(playbackInfo);
        // updating the Player-related state (if necessary)
        final Player player = getPlayer();
        if(player != null) {
            player.getVolumeController().setMuted(isMuted);
        }
    }

    int getPositionInAdapter() {
        return positionInAdapter;
    }

    public void setPositionInAdapter(int positionInAdapter) {
        this.positionInAdapter = positionInAdapter;
    }


    protected final boolean isMuted() {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        return ((player != null) ? player.getVolumeController().isMuted() : playbackInfo.getVolumeInfo().isMuted());
    }


    String generatePlaybackCacheID(){
        String SALTCHARS = "ABCDEFGHIJLMNOPQRSTUVWXYZ123456890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 18){
            int index = (int)(random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltr = salt.toString();
        return  saltr;
    }



    @Override
    public final boolean isPlaying() {
        player = getPlayer();
        if(player == null);
        if(player != null){
            mPlayer = player;
        }
        return ((mPlayer != null) && mPlayer.isPlaying());
    }




    @Override
    public final boolean isTrulyPlayable() {
        return (mPlayerView != null);
    }




    @Override
    public boolean isLooping() {
        return true;
    }




    private boolean isEnded() {
        final Player player = getPlayer();
        return ((player != null) && (player.getPlaybackState() == Player.PlaybackState.ENDED));
    }




    @Override
    public final boolean isAttached(@NonNull Player player) {
        return (isTrulyPlayable() && player.isAttached(mPlayerView));
    }




    @Override
    public final boolean wantsToPlay() {
        return (ExoPlayerUtils.getVisibleAreaOffset(this) >= getTriggerOffset());
    }




    /**
     * <br>
     *      Used to determine whether the playback of a non-looping video can be started.
     *      (Used as a last means of confirmation of the initiation of the playback)
     * <br>
     *      It is to be overridden and used only in cases when you need a specific
     *      control over when the video playback starts (or can be started).
     * <br>
     *      By default, it's always <strong>true</strong>.
     *
     * @return <strong>true</strong> to allow the initiation of the video playback, <strong>false</strong> otherwise.
     */
    protected boolean canStartPlaying() {
        return true;
    }





    protected void onStateChanged(@NonNull PlaybackState playbackState) {
        // to be overridden.
        if(playBackStateListener != null){
            if(playbackState == PlaybackState.BUFFERING)playBackStateListener.onBuffering();
            if(playbackState == PlaybackState.STARTED)playBackStateListener.OnStart();
            if(playbackState == PlaybackState.PAUSED)playBackStateListener.onPause();
            if(playbackState == PlaybackState.READY)playBackStateListener.onReady();
            if(playbackState == PlaybackState.STOPPED)playBackStateListener.onStop();
            if(playbackState == PlaybackState.ERROR)playBackStateListener.onError();
        }
    }




    @Override
    public final void onAttach(@NonNull Player player) {
        if(mPlayerView != null) {
            player.attach(mPlayerView);
        }

    }




    @Override
    public final void onDetach(@NonNull Player player) {
        if(mPlayerView != null) {
            player.detach(mPlayerView);
        }
    }




    @Override
    public void onPlayabilityStateChanged(boolean isPlayable) {
        // to be overridden if necessary.
    }




    @Override
    public final void onPlayerStateChanged(int playbackState) {
        switch(playbackState) {

            case Player.PlaybackState.IDLE:
                onPlaybackIdle();
                break;

            case Player.PlaybackState.BUFFERING:
                onPlaybackBuffering();
                break;

            case Player.PlaybackState.READY:
                onPlaybackReady();
                break;

            case Player.PlaybackState.ENDED:
                onPlaybackEnded();
                break;

        }
    }

    private void onPlaybackIdle() {
        getPlaybackInfo().setEnded(isEnded());
        onStateChanged(PlaybackState.STOPPED);
    }

    private void onPlaybackBuffering() {
        getPlaybackInfo().setEnded(isEnded());
        onStateChanged(PlaybackState.BUFFERING);
    }

    private void onPlaybackReady() {
        getPlaybackInfo().setEnded(isEnded());
        onStateChanged(PlaybackState.READY);
    }




    private void onPlaybackEnded() {
        onStateChanged(PlaybackState.STOPPED);
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.setPlaybackPosition(0);
        playbackInfo.setEnded(isEnded());
        setPlaybackInfo(playbackInfo);
    }




    @Override
    public final void onLoadingChanged(boolean isLoading) {
        // do nothing.
    }




    @Override
    public final void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        // do nothing.
    }




    @Override
    public final void onPlayerError(ExoPlaybackException error) {
        Log.e(TAG, ("onPlayerError: " + error.getLocalizedMessage()));
        onStateChanged(PlaybackState.ERROR);
    }

    public String getPlayBackCacheID() {
        return playBackCacheID;
    }

    public void setPlayBackCacheID(String playBackCacheID) {
        this.playBackCacheID = playBackCacheID;
    }
}
