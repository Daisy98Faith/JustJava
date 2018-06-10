// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, MediaControllerCompatApi21, PlaybackStateCompat, MediaControllerCompatApi26, 
//            ParcelableVolumeInfo, IMediaSession, IMediaControllerCallback

public final class MediaControllerCompat
{
    public static abstract class Callback
        implements android.os.IBinder.DeathRecipient
    {

        private final Object mCallbackObj;
        MessageHandler mHandler;
        boolean mHasExtraCallback;
        boolean mRegistered;

        private void setHandler(Handler handler)
        {
            mHandler = new MessageHandler(handler.getLooper());
        }

        public void binderDied()
        {
            onSessionDestroyed();
        }

        public void onAudioInfoChanged(PlaybackInfo playbackinfo)
        {
        }

        public void onExtrasChanged(Bundle bundle)
        {
        }

        public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
        {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
        {
        }

        public void onQueueChanged(List list)
        {
        }

        public void onQueueTitleChanged(CharSequence charsequence)
        {
        }

        public void onRepeatModeChanged(int i)
        {
        }

        public void onSessionDestroyed()
        {
        }

        public void onSessionEvent(String s, Bundle bundle)
        {
        }

        public void onShuffleModeChanged(boolean flag)
        {
        }



        public Callback()
        {
            mRegistered = false;
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                mCallbackObj = MediaControllerCompatApi26.createCallback(new StubApi26());
                return;
            }
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21());
                return;
            } else
            {
                mCallbackObj = new StubCompat();
                return;
            }
        }
    }

    private class Callback.MessageHandler extends Handler
    {

        private static final int MSG_DESTROYED = 8;
        private static final int MSG_EVENT = 1;
        private static final int MSG_UPDATE_EXTRAS = 7;
        private static final int MSG_UPDATE_METADATA = 3;
        private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
        private static final int MSG_UPDATE_QUEUE = 5;
        private static final int MSG_UPDATE_QUEUE_TITLE = 6;
        private static final int MSG_UPDATE_REPEAT_MODE = 9;
        private static final int MSG_UPDATE_SHUFFLE_MODE = 10;
        private static final int MSG_UPDATE_VOLUME = 4;
        final Callback this$0;

        public void handleMessage(Message message)
        {
            if (!mRegistered)
            {
                return;
            }
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                onSessionEvent((String)message.obj, message.getData());
                return;

            case 2: // '\002'
                onPlaybackStateChanged((PlaybackStateCompat)message.obj);
                return;

            case 3: // '\003'
                onMetadataChanged((MediaMetadataCompat)message.obj);
                return;

            case 5: // '\005'
                onQueueChanged((List)message.obj);
                return;

            case 6: // '\006'
                onQueueTitleChanged((CharSequence)message.obj);
                return;

            case 9: // '\t'
                onRepeatModeChanged(((Integer)message.obj).intValue());
                return;

            case 10: // '\n'
                onShuffleModeChanged(((Boolean)message.obj).booleanValue());
                return;

            case 7: // '\007'
                onExtrasChanged((Bundle)message.obj);
                return;

            case 4: // '\004'
                onAudioInfoChanged((PlaybackInfo)message.obj);
                return;

            case 8: // '\b'
                onSessionDestroyed();
                return;
            }
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        public Callback.MessageHandler(Looper looper)
        {
            this$0 = Callback.this;
            super(looper);
        }
    }

    private class Callback.StubApi21
        implements MediaControllerCompatApi21.Callback
    {

        final Callback this$0;

        public void onAudioInfoChanged(int i, int j, int k, int l, int i1)
        {
            Callback.this.onAudioInfoChanged(new PlaybackInfo(i, j, k, l, i1));
        }

        public void onExtrasChanged(Bundle bundle)
        {
            Callback.this.onExtrasChanged(bundle);
        }

        public void onMetadataChanged(Object obj)
        {
            Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(obj));
        }

        public void onPlaybackStateChanged(Object obj)
        {
            if (mHasExtraCallback)
            {
                return;
            } else
            {
                Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(obj));
                return;
            }
        }

        public void onQueueChanged(List list)
        {
            Callback.this.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
        }

        public void onQueueTitleChanged(CharSequence charsequence)
        {
            Callback.this.onQueueTitleChanged(charsequence);
        }

        public void onSessionDestroyed()
        {
            Callback.this.onSessionDestroyed();
        }

        public void onSessionEvent(String s, Bundle bundle)
        {
            if (mHasExtraCallback && android.os.Build.VERSION.SDK_INT < 23)
            {
                return;
            } else
            {
                Callback.this.onSessionEvent(s, bundle);
                return;
            }
        }

        Callback.StubApi21()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private class Callback.StubApi26 extends Callback.StubApi21
        implements MediaControllerCompatApi26.Callback
    {

        final Callback this$0;

        public void onRepeatModeChanged(int i)
        {
            Callback.this.onRepeatModeChanged(i);
        }

        public void onShuffleModeChanged(boolean flag)
        {
            Callback.this.onShuffleModeChanged(flag);
        }

        private Callback.StubApi26()
        {
            this$0 = Callback.this;
            super();
        }

    }

    private class Callback.StubCompat extends IMediaControllerCallback.Stub
    {

        final Callback this$0;

        public void onEvent(String s, Bundle bundle)
            throws RemoteException
        {
            mHandler.post(1, s, bundle);
        }

        public void onExtrasChanged(Bundle bundle)
            throws RemoteException
        {
            mHandler.post(7, bundle, null);
        }

        public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
            throws RemoteException
        {
            mHandler.post(3, mediametadatacompat, null);
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
            throws RemoteException
        {
            mHandler.post(2, playbackstatecompat, null);
        }

        public void onQueueChanged(List list)
            throws RemoteException
        {
            mHandler.post(5, list, null);
        }

        public void onQueueTitleChanged(CharSequence charsequence)
            throws RemoteException
        {
            mHandler.post(6, charsequence, null);
        }

        public void onRepeatModeChanged(int i)
            throws RemoteException
        {
            mHandler.post(9, Integer.valueOf(i), null);
        }

        public void onSessionDestroyed()
            throws RemoteException
        {
            mHandler.post(8, null, null);
        }

        public void onShuffleModeChanged(boolean flag)
            throws RemoteException
        {
            mHandler.post(10, Boolean.valueOf(flag), null);
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
            throws RemoteException
        {
            PlaybackInfo playbackinfo = null;
            if (parcelablevolumeinfo != null)
            {
                playbackinfo = new PlaybackInfo(parcelablevolumeinfo.volumeType, parcelablevolumeinfo.audioStream, parcelablevolumeinfo.controlType, parcelablevolumeinfo.maxVolume, parcelablevolumeinfo.currentVolume);
            }
            mHandler.post(4, playbackinfo, null);
        }

        Callback.StubCompat()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private static class MediaControllerExtraData extends android.support.v4.app.SupportActivity.ExtraData
    {

        private final MediaControllerCompat mMediaController;

        MediaControllerCompat getMediaController()
        {
            return mMediaController;
        }

        MediaControllerExtraData(MediaControllerCompat mediacontrollercompat)
        {
            mMediaController = mediacontrollercompat;
        }
    }

    static interface MediaControllerImpl
    {

        public abstract void addQueueItem(MediaDescriptionCompat mediadescriptioncompat);

        public abstract void addQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i);

        public abstract void adjustVolume(int i, int j);

        public abstract boolean dispatchMediaButtonEvent(KeyEvent keyevent);

        public abstract Bundle getExtras();

        public abstract long getFlags();

        public abstract Object getMediaController();

        public abstract MediaMetadataCompat getMetadata();

        public abstract String getPackageName();

        public abstract PlaybackInfo getPlaybackInfo();

        public abstract PlaybackStateCompat getPlaybackState();

        public abstract List getQueue();

        public abstract CharSequence getQueueTitle();

        public abstract int getRatingType();

        public abstract int getRepeatMode();

        public abstract PendingIntent getSessionActivity();

        public abstract TransportControls getTransportControls();

        public abstract boolean isShuffleModeEnabled();

        public abstract void registerCallback(Callback callback, Handler handler);

        public abstract void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat);

        public abstract void removeQueueItemAt(int i);

        public abstract void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver);

        public abstract void setVolumeTo(int i, int j);

        public abstract void unregisterCallback(Callback callback);
    }

    static class MediaControllerImplApi21
        implements MediaControllerImpl
    {

        private HashMap mCallbackMap;
        protected final Object mControllerObj;
        private IMediaSession mExtraBinder;
        private List mPendingCallbacks;

        private void processPendingCallbacks()
        {
            if (mExtraBinder == null)
            {
                return;
            }
            List list = mPendingCallbacks;
            list;
            JVM INSTR monitorenter ;
            Iterator iterator = mPendingCallbacks.iterator();
_L1:
            ExtraCallback extracallback;
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_111;
            }
            Callback callback = (Callback)iterator.next();
            extracallback = new ExtraCallback(callback);
            mCallbackMap.put(callback, extracallback);
            callback.mHasExtraCallback = true;
            mExtraBinder.registerCallbackListener(extracallback);
              goto _L1
            Object obj;
            obj;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in registerCallback. ").append(obj).toString());
            mPendingCallbacks.clear();
            list;
            JVM INSTR monitorexit ;
            return;
            obj;
            list;
            JVM INSTR monitorexit ;
            throw obj;
        }

        private void requestExtraBinder()
        {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
        }

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediadescriptioncompat);
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", bundle, null);
        }

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediadescriptioncompat);
            bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i);
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", bundle, null);
        }

        public void adjustVolume(int i, int j)
        {
            MediaControllerCompatApi21.adjustVolume(mControllerObj, i, j);
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
        {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(mControllerObj, keyevent);
        }

        public Bundle getExtras()
        {
            return MediaControllerCompatApi21.getExtras(mControllerObj);
        }

        public long getFlags()
        {
            return MediaControllerCompatApi21.getFlags(mControllerObj);
        }

        public Object getMediaController()
        {
            return mControllerObj;
        }

        public MediaMetadataCompat getMetadata()
        {
            Object obj = MediaControllerCompatApi21.getMetadata(mControllerObj);
            if (obj != null)
            {
                return MediaMetadataCompat.fromMediaMetadata(obj);
            } else
            {
                return null;
            }
        }

        public String getPackageName()
        {
            return MediaControllerCompatApi21.getPackageName(mControllerObj);
        }

        public PlaybackInfo getPlaybackInfo()
        {
            Object obj = MediaControllerCompatApi21.getPlaybackInfo(mControllerObj);
            if (obj != null)
            {
                return new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(obj), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(obj), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(obj), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(obj), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(obj));
            } else
            {
                return null;
            }
        }

        public PlaybackStateCompat getPlaybackState()
        {
            if (mExtraBinder == null)
            {
                break MISSING_BLOCK_LABEL_45;
            }
            PlaybackStateCompat playbackstatecompat = mExtraBinder.getPlaybackState();
            return playbackstatecompat;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPlaybackState. ").append(remoteexception).toString());
            Object obj = MediaControllerCompatApi21.getPlaybackState(mControllerObj);
            if (obj != null)
            {
                return PlaybackStateCompat.fromPlaybackState(obj);
            } else
            {
                return null;
            }
        }

        public List getQueue()
        {
            List list = MediaControllerCompatApi21.getQueue(mControllerObj);
            if (list != null)
            {
                return MediaSessionCompat.QueueItem.fromQueueItemList(list);
            } else
            {
                return null;
            }
        }

        public CharSequence getQueueTitle()
        {
            return MediaControllerCompatApi21.getQueueTitle(mControllerObj);
        }

        public int getRatingType()
        {
            if (android.os.Build.VERSION.SDK_INT >= 22 || mExtraBinder == null)
            {
                break MISSING_BLOCK_LABEL_54;
            }
            int i = mExtraBinder.getRatingType();
            return i;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getRatingType. ").append(remoteexception).toString());
            return MediaControllerCompatApi21.getRatingType(mControllerObj);
        }

        public int getRepeatMode()
        {
            if (mExtraBinder == null)
            {
                break MISSING_BLOCK_LABEL_46;
            }
            int i = mExtraBinder.getRepeatMode();
            return i;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getRepeatMode. ").append(remoteexception).toString());
            return 0;
        }

        public PendingIntent getSessionActivity()
        {
            return MediaControllerCompatApi21.getSessionActivity(mControllerObj);
        }

        public TransportControls getTransportControls()
        {
            Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
            if (obj != null)
            {
                return new TransportControlsApi21(obj);
            } else
            {
                return null;
            }
        }

        public boolean isShuffleModeEnabled()
        {
            if (mExtraBinder == null)
            {
                break MISSING_BLOCK_LABEL_46;
            }
            boolean flag = mExtraBinder.isShuffleModeEnabled();
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in isShuffleModeEnabled. ").append(remoteexception).toString());
            return false;
        }

        public final void registerCallback(Callback callback, Handler handler)
        {
            MediaControllerCompatApi21.registerCallback(mControllerObj, callback.mCallbackObj, handler);
            if (mExtraBinder != null)
            {
                callback.setHandler(handler);
                handler = new ExtraCallback(callback);
                mCallbackMap.put(callback, handler);
                callback.mHasExtraCallback = true;
                try
                {
                    mExtraBinder.registerCallbackListener(handler);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (Callback callback)
                {
                    Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in registerCallback. ").append(callback).toString());
                }
                return;
            }
            callback.setHandler(handler);
            synchronized (mPendingCallbacks)
            {
                mPendingCallbacks.add(callback);
            }
            return;
            callback;
            handler;
            JVM INSTR monitorexit ;
            throw callback;
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediadescriptioncompat);
            sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, null);
        }

        public void removeQueueItemAt(int i)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i);
            sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT", bundle, null);
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            MediaControllerCompatApi21.sendCommand(mControllerObj, s, bundle, resultreceiver);
        }

        public void setVolumeTo(int i, int j)
        {
            MediaControllerCompatApi21.setVolumeTo(mControllerObj, i, j);
        }

        public final void unregisterCallback(Callback callback)
        {
            MediaControllerCompatApi21.unregisterCallback(mControllerObj, callback.mCallbackObj);
            if (mExtraBinder == null)
            {
                break MISSING_BLOCK_LABEL_73;
            }
            try
            {
                callback = (ExtraCallback)mCallbackMap.remove(callback);
            }
            // Misplaced declaration of an exception variable
            catch (Callback callback)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in unregisterCallback. ").append(callback).toString());
                return;
            }
            if (callback == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            mExtraBinder.unregisterCallbackListener(callback);
            return;
            synchronized (mPendingCallbacks)
            {
                mPendingCallbacks.remove(callback);
            }
            return;
            callback;
            list;
            JVM INSTR monitorexit ;
            throw callback;
        }



/*
        static IMediaSession access$002(MediaControllerImplApi21 mediacontrollerimplapi21, IMediaSession imediasession)
        {
            mediacontrollerimplapi21.mExtraBinder = imediasession;
            return imediasession;
        }

*/


        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token)
            throws RemoteException
        {
            mCallbackMap = new HashMap();
            mPendingCallbacks = new ArrayList();
            mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if (mControllerObj == null)
            {
                throw new RemoteException();
            } else
            {
                requestExtraBinder();
                return;
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat mediasessioncompat)
        {
            mCallbackMap = new HashMap();
            mPendingCallbacks = new ArrayList();
            mControllerObj = MediaControllerCompatApi21.fromToken(context, mediasessioncompat.getSessionToken().getToken());
            requestExtraBinder();
        }
    }

    private static class MediaControllerImplApi21.ExtraBinderRequestResultReceiver extends ResultReceiver
    {

        private WeakReference mMediaControllerImpl;

        protected void onReceiveResult(int i, Bundle bundle)
        {
            MediaControllerImplApi21 mediacontrollerimplapi21 = (MediaControllerImplApi21)mMediaControllerImpl.get();
            if (mediacontrollerimplapi21 == null || bundle == null)
            {
                return;
            } else
            {
                mediacontrollerimplapi21.mExtraBinder = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
                mediacontrollerimplapi21.processPendingCallbacks();
                return;
            }
        }

        public MediaControllerImplApi21.ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediacontrollerimplapi21, Handler handler)
        {
            super(handler);
            mMediaControllerImpl = new WeakReference(mediacontrollerimplapi21);
        }
    }

    private class MediaControllerImplApi21.ExtraCallback extends IMediaControllerCallback.Stub
    {

        private Callback mCallback;
        final MediaControllerImplApi21 this$0;

        public void onEvent(final String event, Bundle bundle)
            throws RemoteException
        {
            mCallback.mHandler.post(bundle. new Runnable() {

                final MediaControllerImplApi21.ExtraCallback this$1;
                final String val$event;
                final Bundle val$extras;

                public void run()
                {
                    mCallback.onSessionEvent(event, extras);
                }

            
            {
                this$1 = final_extracallback;
                event = s;
                extras = Bundle.this;
                super();
            }
            });
        }

        public void onExtrasChanged(Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
            throws RemoteException
        {
            mCallback.mHandler.post(playbackstatecompat. new Runnable() {

                final MediaControllerImplApi21.ExtraCallback this$1;
                final PlaybackStateCompat val$state;

                public void run()
                {
                    mCallback.onPlaybackStateChanged(state);
                }

            
            {
                this$1 = final_extracallback;
                state = PlaybackStateCompat.this;
                super();
            }
            });
        }

        public void onQueueChanged(List list)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void onQueueTitleChanged(CharSequence charsequence)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void onRepeatModeChanged(int i)
            throws RemoteException
        {
            mCallback.mHandler.post(i. new Runnable() {

                final MediaControllerImplApi21.ExtraCallback this$1;
                final int val$repeatMode;

                public void run()
                {
                    mCallback.onRepeatModeChanged(repeatMode);
                }

            
            {
                this$1 = final_extracallback;
                repeatMode = I.this;
                super();
            }
            });
        }

        public void onSessionDestroyed()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void onShuffleModeChanged(boolean flag)
            throws RemoteException
        {
            mCallback.mHandler.post(flag. new Runnable() {

                final MediaControllerImplApi21.ExtraCallback this$1;
                final boolean val$enabled;

                public void run()
                {
                    mCallback.onShuffleModeChanged(enabled);
                }

            
            {
                this$1 = final_extracallback;
                enabled = Z.this;
                super();
            }
            });
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
            throws RemoteException
        {
            throw new AssertionError();
        }


        MediaControllerImplApi21.ExtraCallback(Callback callback)
        {
            this$0 = MediaControllerImplApi21.this;
            super();
            mCallback = callback;
        }
    }

    static class MediaControllerImplApi23 extends MediaControllerImplApi21
    {

        public TransportControls getTransportControls()
        {
            Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
            if (obj != null)
            {
                return new TransportControlsApi23(obj);
            } else
            {
                return null;
            }
        }

        public MediaControllerImplApi23(Context context, MediaSessionCompat.Token token)
            throws RemoteException
        {
            super(context, token);
        }

        public MediaControllerImplApi23(Context context, MediaSessionCompat mediasessioncompat)
        {
            super(context, mediasessioncompat);
        }
    }

    static class MediaControllerImplApi24 extends MediaControllerImplApi23
    {

        public TransportControls getTransportControls()
        {
            Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
            if (obj != null)
            {
                return new TransportControlsApi24(obj);
            } else
            {
                return null;
            }
        }

        public MediaControllerImplApi24(Context context, MediaSessionCompat.Token token)
            throws RemoteException
        {
            super(context, token);
        }

        public MediaControllerImplApi24(Context context, MediaSessionCompat mediasessioncompat)
        {
            super(context, mediasessioncompat);
        }
    }

    static class MediaControllerImplApi26 extends MediaControllerImplApi24
    {

        public int getRepeatMode()
        {
            return MediaControllerCompatApi26.getRepeatMode(mControllerObj);
        }

        public TransportControls getTransportControls()
        {
            Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
            if (obj != null)
            {
                return new TransportControlsApi26(obj);
            } else
            {
                return null;
            }
        }

        public boolean isShuffleModeEnabled()
        {
            return MediaControllerCompatApi26.isShuffleModeEnabled(mControllerObj);
        }

        MediaControllerImplApi26(Context context, MediaSessionCompat.Token token)
            throws RemoteException
        {
            super(context, token);
        }

        MediaControllerImplApi26(Context context, MediaSessionCompat mediasessioncompat)
        {
            super(context, mediasessioncompat);
        }
    }

    static class MediaControllerImplBase
        implements MediaControllerImpl
    {

        private IMediaSession mBinder;
        private MediaSessionCompat.Token mToken;
        private TransportControls mTransportControls;

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            try
            {
                if ((4L & mBinder.getFlags()) == 0L)
                {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
            }
            // Misplaced declaration of an exception variable
            catch (MediaDescriptionCompat mediadescriptioncompat)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in addQueueItem. ").append(mediadescriptioncompat).toString());
                return;
            }
            mBinder.addQueueItem(mediadescriptioncompat);
            return;
        }

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            try
            {
                if ((4L & mBinder.getFlags()) == 0L)
                {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
            }
            // Misplaced declaration of an exception variable
            catch (MediaDescriptionCompat mediadescriptioncompat)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in addQueueItemAt. ").append(mediadescriptioncompat).toString());
                return;
            }
            mBinder.addQueueItemAt(mediadescriptioncompat, i);
            return;
        }

        public void adjustVolume(int i, int j)
        {
            try
            {
                mBinder.adjustVolume(i, j, null);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in adjustVolume. ").append(remoteexception).toString());
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
        {
            if (keyevent == null)
            {
                throw new IllegalArgumentException("event may not be null.");
            }
            try
            {
                mBinder.sendMediaButton(keyevent);
            }
            // Misplaced declaration of an exception variable
            catch (KeyEvent keyevent)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in dispatchMediaButtonEvent. ").append(keyevent).toString());
            }
            return false;
        }

        public Bundle getExtras()
        {
            Bundle bundle;
            try
            {
                bundle = mBinder.getExtras();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getExtras. ").append(remoteexception).toString());
                return null;
            }
            return bundle;
        }

        public long getFlags()
        {
            long l;
            try
            {
                l = mBinder.getFlags();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getFlags. ").append(remoteexception).toString());
                return 0L;
            }
            return l;
        }

        public Object getMediaController()
        {
            return null;
        }

        public MediaMetadataCompat getMetadata()
        {
            MediaMetadataCompat mediametadatacompat;
            try
            {
                mediametadatacompat = mBinder.getMetadata();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getMetadata. ").append(remoteexception).toString());
                return null;
            }
            return mediametadatacompat;
        }

        public String getPackageName()
        {
            String s;
            try
            {
                s = mBinder.getPackageName();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPackageName. ").append(remoteexception).toString());
                return null;
            }
            return s;
        }

        public PlaybackInfo getPlaybackInfo()
        {
            Object obj;
            try
            {
                obj = mBinder.getVolumeAttributes();
                obj = new PlaybackInfo(((ParcelableVolumeInfo) (obj)).volumeType, ((ParcelableVolumeInfo) (obj)).audioStream, ((ParcelableVolumeInfo) (obj)).controlType, ((ParcelableVolumeInfo) (obj)).maxVolume, ((ParcelableVolumeInfo) (obj)).currentVolume);
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPlaybackInfo. ").append(remoteexception).toString());
                return null;
            }
            return ((PlaybackInfo) (obj));
        }

        public PlaybackStateCompat getPlaybackState()
        {
            PlaybackStateCompat playbackstatecompat;
            try
            {
                playbackstatecompat = mBinder.getPlaybackState();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPlaybackState. ").append(remoteexception).toString());
                return null;
            }
            return playbackstatecompat;
        }

        public List getQueue()
        {
            List list;
            try
            {
                list = mBinder.getQueue();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getQueue. ").append(remoteexception).toString());
                return null;
            }
            return list;
        }

        public CharSequence getQueueTitle()
        {
            CharSequence charsequence;
            try
            {
                charsequence = mBinder.getQueueTitle();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getQueueTitle. ").append(remoteexception).toString());
                return null;
            }
            return charsequence;
        }

        public int getRatingType()
        {
            int i;
            try
            {
                i = mBinder.getRatingType();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getRatingType. ").append(remoteexception).toString());
                return 0;
            }
            return i;
        }

        public int getRepeatMode()
        {
            int i;
            try
            {
                i = mBinder.getRepeatMode();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getRepeatMode. ").append(remoteexception).toString());
                return 0;
            }
            return i;
        }

        public PendingIntent getSessionActivity()
        {
            PendingIntent pendingintent;
            try
            {
                pendingintent = mBinder.getLaunchPendingIntent();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getSessionActivity. ").append(remoteexception).toString());
                return null;
            }
            return pendingintent;
        }

        public TransportControls getTransportControls()
        {
            if (mTransportControls == null)
            {
                mTransportControls = new TransportControlsBase(mBinder);
            }
            return mTransportControls;
        }

        public boolean isShuffleModeEnabled()
        {
            boolean flag;
            try
            {
                flag = mBinder.isShuffleModeEnabled();
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in isShuffleModeEnabled. ").append(remoteexception).toString());
                return false;
            }
            return flag;
        }

        public void registerCallback(Callback callback, Handler handler)
        {
            if (callback == null)
            {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try
            {
                mBinder.asBinder().linkToDeath(callback, 0);
                mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                callback.setHandler(handler);
                callback.mRegistered = true;
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Handler handler)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in registerCallback. ").append(handler).toString());
            }
            callback.onSessionDestroyed();
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            try
            {
                if ((4L & mBinder.getFlags()) == 0L)
                {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
            }
            // Misplaced declaration of an exception variable
            catch (MediaDescriptionCompat mediadescriptioncompat)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in removeQueueItem. ").append(mediadescriptioncompat).toString());
                return;
            }
            mBinder.removeQueueItem(mediadescriptioncompat);
            return;
        }

        public void removeQueueItemAt(int i)
        {
            try
            {
                if ((4L & mBinder.getFlags()) == 0L)
                {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in removeQueueItemAt. ").append(remoteexception).toString());
                return;
            }
            mBinder.removeQueueItemAt(i);
            return;
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            try
            {
                mBinder.sendCommand(s, bundle, new MediaSessionCompat.ResultReceiverWrapper(resultreceiver));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in sendCommand. ").append(s).toString());
            }
        }

        public void setVolumeTo(int i, int j)
        {
            try
            {
                mBinder.setVolumeTo(i, j, null);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setVolumeTo. ").append(remoteexception).toString());
            }
        }

        public void unregisterCallback(Callback callback)
        {
            if (callback == null)
            {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try
            {
                mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                mBinder.asBinder().unlinkToDeath(callback, 0);
                callback.mRegistered = false;
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Callback callback)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in unregisterCallback. ").append(callback).toString());
            }
        }

        public MediaControllerImplBase(MediaSessionCompat.Token token)
        {
            mToken = token;
            mBinder = IMediaSession.Stub.asInterface((IBinder)token.getToken());
        }
    }

    public static final class PlaybackInfo
    {

        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        public int getAudioStream()
        {
            return mAudioStream;
        }

        public int getCurrentVolume()
        {
            return mCurrentVolume;
        }

        public int getMaxVolume()
        {
            return mMaxVolume;
        }

        public int getPlaybackType()
        {
            return mPlaybackType;
        }

        public int getVolumeControl()
        {
            return mVolumeControl;
        }

        PlaybackInfo(int i, int j, int k, int l, int i1)
        {
            mPlaybackType = i;
            mAudioStream = j;
            mVolumeControl = k;
            mMaxVolume = l;
            mCurrentVolume = i1;
        }
    }

    public static abstract class TransportControls
    {

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String s, Bundle bundle);

        public abstract void playFromSearch(String s, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String s, Bundle bundle);

        public abstract void prepareFromSearch(String s, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long l);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle);

        public abstract void sendCustomAction(String s, Bundle bundle);

        public abstract void setRating(RatingCompat ratingcompat);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleModeEnabled(boolean flag);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long l);

        public abstract void stop();

        TransportControls()
        {
        }
    }

    static class TransportControlsApi21 extends TransportControls
    {

        protected final Object mControlsObj;

        public void fastForward()
        {
            MediaControllerCompatApi21.TransportControls.fastForward(mControlsObj);
        }

        public void pause()
        {
            MediaControllerCompatApi21.TransportControls.pause(mControlsObj);
        }

        public void play()
        {
            MediaControllerCompatApi21.TransportControls.play(mControlsObj);
        }

        public void playFromMediaId(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(mControlsObj, s, bundle);
        }

        public void playFromSearch(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.playFromSearch(mControlsObj, s, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle)
        {
            if (uri == null || Uri.EMPTY.equals(uri))
            {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            } else
            {
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
                bundle1.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
                sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle1);
                return;
            }
        }

        public void prepare()
        {
            sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
        }

        public void prepareFromMediaId(String s, Bundle bundle)
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", s);
            bundle1.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle1);
        }

        public void prepareFromSearch(String s, Bundle bundle)
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", s);
            bundle1.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle1);
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
        {
            Bundle bundle1 = new Bundle();
            bundle1.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle1.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle1);
        }

        public void rewind()
        {
            MediaControllerCompatApi21.TransportControls.rewind(mControlsObj);
        }

        public void seekTo(long l)
        {
            MediaControllerCompatApi21.TransportControls.seekTo(mControlsObj, l);
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, customaction.getAction(), bundle);
        }

        public void sendCustomAction(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, s, bundle);
        }

        public void setRating(RatingCompat ratingcompat)
        {
            Object obj = mControlsObj;
            if (ratingcompat != null)
            {
                ratingcompat = ((RatingCompat) (ratingcompat.getRating()));
            } else
            {
                ratingcompat = null;
            }
            MediaControllerCompatApi21.TransportControls.setRating(obj, ratingcompat);
        }

        public void setRepeatMode(int i)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        public void setShuffleModeEnabled(boolean flag)
        {
            Bundle bundle = new Bundle();
            bundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED", flag);
            sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED", bundle);
        }

        public void skipToNext()
        {
            MediaControllerCompatApi21.TransportControls.skipToNext(mControlsObj);
        }

        public void skipToPrevious()
        {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(mControlsObj);
        }

        public void skipToQueueItem(long l)
        {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(mControlsObj, l);
        }

        public void stop()
        {
            MediaControllerCompatApi21.TransportControls.stop(mControlsObj);
        }

        public TransportControlsApi21(Object obj)
        {
            mControlsObj = obj;
        }
    }

    static class TransportControlsApi23 extends TransportControlsApi21
    {

        public void playFromUri(Uri uri, Bundle bundle)
        {
            MediaControllerCompatApi23.TransportControls.playFromUri(mControlsObj, uri, bundle);
        }

        public TransportControlsApi23(Object obj)
        {
            super(obj);
        }
    }

    static class TransportControlsApi24 extends TransportControlsApi23
    {

        public void prepare()
        {
            MediaControllerCompatApi24.TransportControls.prepare(mControlsObj);
        }

        public void prepareFromMediaId(String s, Bundle bundle)
        {
            MediaControllerCompatApi24.TransportControls.prepareFromMediaId(mControlsObj, s, bundle);
        }

        public void prepareFromSearch(String s, Bundle bundle)
        {
            MediaControllerCompatApi24.TransportControls.prepareFromSearch(mControlsObj, s, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
        {
            MediaControllerCompatApi24.TransportControls.prepareFromUri(mControlsObj, uri, bundle);
        }

        public TransportControlsApi24(Object obj)
        {
            super(obj);
        }
    }

    static class TransportControlsApi26 extends TransportControlsApi24
    {

        public void setRepeatMode(int i)
        {
            MediaControllerCompatApi26.TransportControls.setRepeatMode(mControlsObj, i);
        }

        public void setShuffleModeEnabled(boolean flag)
        {
            MediaControllerCompatApi26.TransportControls.setShuffleModeEnabled(mControlsObj, flag);
        }

        TransportControlsApi26(Object obj)
        {
            super(obj);
        }
    }

    static class TransportControlsBase extends TransportControls
    {

        private IMediaSession mBinder;

        public void fastForward()
        {
            try
            {
                mBinder.fastForward();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in fastForward. ").append(remoteexception).toString());
            }
        }

        public void pause()
        {
            try
            {
                mBinder.pause();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in pause. ").append(remoteexception).toString());
            }
        }

        public void play()
        {
            try
            {
                mBinder.play();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in play. ").append(remoteexception).toString());
            }
        }

        public void playFromMediaId(String s, Bundle bundle)
        {
            try
            {
                mBinder.playFromMediaId(s, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in playFromMediaId. ").append(s).toString());
            }
        }

        public void playFromSearch(String s, Bundle bundle)
        {
            try
            {
                mBinder.playFromSearch(s, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in playFromSearch. ").append(s).toString());
            }
        }

        public void playFromUri(Uri uri, Bundle bundle)
        {
            try
            {
                mBinder.playFromUri(uri, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Uri uri)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in playFromUri. ").append(uri).toString());
            }
        }

        public void prepare()
        {
            try
            {
                mBinder.prepare();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in prepare. ").append(remoteexception).toString());
            }
        }

        public void prepareFromMediaId(String s, Bundle bundle)
        {
            try
            {
                mBinder.prepareFromMediaId(s, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in prepareFromMediaId. ").append(s).toString());
            }
        }

        public void prepareFromSearch(String s, Bundle bundle)
        {
            try
            {
                mBinder.prepareFromSearch(s, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in prepareFromSearch. ").append(s).toString());
            }
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
        {
            try
            {
                mBinder.prepareFromUri(uri, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Uri uri)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in prepareFromUri. ").append(uri).toString());
            }
        }

        public void rewind()
        {
            try
            {
                mBinder.rewind();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in rewind. ").append(remoteexception).toString());
            }
        }

        public void seekTo(long l)
        {
            try
            {
                mBinder.seekTo(l);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in seekTo. ").append(remoteexception).toString());
            }
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle)
        {
            sendCustomAction(customaction.getAction(), bundle);
        }

        public void sendCustomAction(String s, Bundle bundle)
        {
            try
            {
                mBinder.sendCustomAction(s, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in sendCustomAction. ").append(s).toString());
            }
        }

        public void setRating(RatingCompat ratingcompat)
        {
            try
            {
                mBinder.rate(ratingcompat);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (RatingCompat ratingcompat)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setRating. ").append(ratingcompat).toString());
            }
        }

        public void setRepeatMode(int i)
        {
            try
            {
                mBinder.setRepeatMode(i);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setRepeatMode. ").append(remoteexception).toString());
            }
        }

        public void setShuffleModeEnabled(boolean flag)
        {
            try
            {
                mBinder.setShuffleModeEnabled(flag);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setShuffleModeEnabled. ").append(remoteexception).toString());
            }
        }

        public void skipToNext()
        {
            try
            {
                mBinder.next();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToNext. ").append(remoteexception).toString());
            }
        }

        public void skipToPrevious()
        {
            try
            {
                mBinder.previous();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToPrevious. ").append(remoteexception).toString());
            }
        }

        public void skipToQueueItem(long l)
        {
            try
            {
                mBinder.skipToQueueItem(l);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToQueueItem. ").append(remoteexception).toString());
            }
        }

        public void stop()
        {
            try
            {
                mBinder.stop();
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in stop. ").append(remoteexception).toString());
            }
        }

        public TransportControlsBase(IMediaSession imediasession)
        {
            mBinder = imediasession;
        }
    }


    static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final MediaSessionCompat.Token mToken;

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token)
        throws RemoteException
    {
        if (token == null)
        {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        mToken = token;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            mImpl = new MediaControllerImplApi26(context, token);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            mImpl = new MediaControllerImplApi24(context, token);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            mImpl = new MediaControllerImplApi23(context, token);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            mImpl = new MediaControllerImplApi21(context, token);
            return;
        } else
        {
            mImpl = new MediaControllerImplBase(mToken);
            return;
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediasessioncompat)
    {
        if (mediasessioncompat == null)
        {
            throw new IllegalArgumentException("session must not be null");
        }
        mToken = mediasessioncompat.getSessionToken();
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            mImpl = new MediaControllerImplApi26(context, mediasessioncompat);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            mImpl = new MediaControllerImplApi24(context, mediasessioncompat);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            mImpl = new MediaControllerImplApi23(context, mediasessioncompat);
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            mImpl = new MediaControllerImplApi21(context, mediasessioncompat);
            return;
        } else
        {
            mImpl = new MediaControllerImplBase(mToken);
            return;
        }
    }

    public static MediaControllerCompat getMediaController(Activity activity)
    {
        Object obj1 = null;
        MediaControllerCompat mediacontrollercompat;
        if (activity instanceof SupportActivity)
        {
            activity = (MediaControllerExtraData)((SupportActivity)activity).getExtraData(android/support/v4/media/session/MediaControllerCompat$MediaControllerExtraData);
            mediacontrollercompat = obj1;
            if (activity != null)
            {
                mediacontrollercompat = activity.getMediaController();
            }
        } else
        {
            mediacontrollercompat = obj1;
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                Object obj2 = MediaControllerCompatApi21.getMediaController(activity);
                mediacontrollercompat = obj1;
                if (obj2 != null)
                {
                    Object obj = MediaControllerCompatApi21.getSessionToken(obj2);
                    try
                    {
                        activity = new MediaControllerCompat(activity, MediaSessionCompat.Token.fromToken(obj));
                    }
                    // Misplaced declaration of an exception variable
                    catch (Activity activity)
                    {
                        Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getMediaController. ").append(activity).toString());
                        return null;
                    }
                    return activity;
                }
            }
        }
        return mediacontrollercompat;
    }

    public static void setMediaController(Activity activity, MediaControllerCompat mediacontrollercompat)
    {
        if (activity instanceof SupportActivity)
        {
            ((SupportActivity)activity).putExtraData(new MediaControllerExtraData(mediacontrollercompat));
        }
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            Object obj = null;
            if (mediacontrollercompat != null)
            {
                obj = MediaControllerCompatApi21.fromToken(activity, mediacontrollercompat.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity, obj);
        }
    }

    public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
        mImpl.addQueueItem(mediadescriptioncompat);
    }

    public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i)
    {
        mImpl.addQueueItem(mediadescriptioncompat, i);
    }

    public void adjustVolume(int i, int j)
    {
        mImpl.adjustVolume(i, j);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
    {
        if (keyevent == null)
        {
            throw new IllegalArgumentException("KeyEvent may not be null");
        } else
        {
            return mImpl.dispatchMediaButtonEvent(keyevent);
        }
    }

    public Bundle getExtras()
    {
        return mImpl.getExtras();
    }

    public long getFlags()
    {
        return mImpl.getFlags();
    }

    public Object getMediaController()
    {
        return mImpl.getMediaController();
    }

    public MediaMetadataCompat getMetadata()
    {
        return mImpl.getMetadata();
    }

    public String getPackageName()
    {
        return mImpl.getPackageName();
    }

    public PlaybackInfo getPlaybackInfo()
    {
        return mImpl.getPlaybackInfo();
    }

    public PlaybackStateCompat getPlaybackState()
    {
        return mImpl.getPlaybackState();
    }

    public List getQueue()
    {
        return mImpl.getQueue();
    }

    public CharSequence getQueueTitle()
    {
        return mImpl.getQueueTitle();
    }

    public int getRatingType()
    {
        return mImpl.getRatingType();
    }

    public int getRepeatMode()
    {
        return mImpl.getRepeatMode();
    }

    public PendingIntent getSessionActivity()
    {
        return mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken()
    {
        return mToken;
    }

    public TransportControls getTransportControls()
    {
        return mImpl.getTransportControls();
    }

    boolean isExtraBinderReady()
    {
        if (mImpl instanceof MediaControllerImplApi21)
        {
            return ((MediaControllerImplApi21)mImpl).mExtraBinder != null;
        } else
        {
            return false;
        }
    }

    public boolean isShuffleModeEnabled()
    {
        return mImpl.isShuffleModeEnabled();
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        if (callback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        }
        Handler handler1 = handler;
        if (handler == null)
        {
            handler1 = new Handler();
        }
        mImpl.registerCallback(callback, handler1);
    }

    public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
        mImpl.removeQueueItem(mediadescriptioncompat);
    }

    public void removeQueueItemAt(int i)
    {
        mImpl.removeQueueItemAt(i);
    }

    public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("command cannot be null or empty");
        } else
        {
            mImpl.sendCommand(s, bundle, resultreceiver);
            return;
        }
    }

    public void setVolumeTo(int i, int j)
    {
        mImpl.setVolumeTo(i, j);
    }

    public void unregisterCallback(Callback callback)
    {
        if (callback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        } else
        {
            mImpl.unregisterCallback(callback);
            return;
        }
    }
}
