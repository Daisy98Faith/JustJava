// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat, MediaControllerCompatApi21, MediaSessionCompat, IMediaSession, 
//            PlaybackStateCompat, ParcelableVolumeInfo

static class requestExtraBinder
    implements requestExtraBinder
{
    private static class ExtraBinderRequestResultReceiver extends ResultReceiver
    {

        private WeakReference mMediaControllerImpl;

        protected void onReceiveResult(int i, Bundle bundle)
        {
            MediaControllerCompat.MediaControllerImplApi21 mediacontrollerimplapi21 = (MediaControllerCompat.MediaControllerImplApi21)mMediaControllerImpl.get();
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

        public ExtraBinderRequestResultReceiver(MediaControllerCompat.MediaControllerImplApi21 mediacontrollerimplapi21, Handler handler)
        {
            super(handler);
            mMediaControllerImpl = new WeakReference(mediacontrollerimplapi21);
        }
    }

    private class ExtraCallback extends IMediaControllerCallback.Stub
    {

        private MediaControllerCompat.Callback mCallback;
        final MediaControllerCompat.MediaControllerImplApi21 this$0;

        public void onEvent(final String event, Bundle bundle)
            throws RemoteException
        {
            mCallback.mHandler.post(bundle. new Runnable() {

                final ExtraCallback this$1;
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

                final ExtraCallback this$1;
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

                final ExtraCallback this$1;
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

                final ExtraCallback this$1;
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


        ExtraCallback(MediaControllerCompat.Callback callback)
        {
            this$0 = MediaControllerCompat.MediaControllerImplApi21.this;
            super();
            mCallback = callback;
        }
    }


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
        ExtraCallback extracallback1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_111;
        }
        ExtraCallback extracallback = (mPendingCallbacks)iterator.next();
        extracallback1 = new ExtraCallback(extracallback);
        mCallbackMap.put(extracallback, extracallback1);
        extracallback.k = true;
        mExtraBinder.registerCallbackListener(extracallback1);
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

    public mControllerObj getPlaybackInfo()
    {
        Object obj = MediaControllerCompatApi21.getPlaybackInfo(mControllerObj);
        if (obj != null)
        {
            return new mControllerObj(backType(obj), cyAudioStream(obj), meControl(obj), olume(obj), entVolume(obj));
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
            return mControllerObj(list);
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
        if (android.os.iaControllerImplApi21 >= 22 || mExtraBinder == null)
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

    public mControllerObj getTransportControls()
    {
        Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
        if (obj != null)
        {
            return new nit>(obj);
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

    public final void registerCallback(mExtraBinder mextrabinder, Handler handler)
    {
        MediaControllerCompatApi21.registerCallback(mControllerObj, mControllerObj(mextrabinder), handler);
        if (mExtraBinder != null)
        {
            mExtraBinder(mextrabinder, handler);
            handler = new ExtraCallback(mextrabinder);
            mCallbackMap.put(mextrabinder, handler);
            mextrabinder.k = true;
            try
            {
                mExtraBinder.registerCallbackListener(handler);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (mExtraBinder mextrabinder)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in registerCallback. ").append(mextrabinder).toString());
            }
            return;
        }
        mExtraBinder(mextrabinder, handler);
        synchronized (mPendingCallbacks)
        {
            mPendingCallbacks.add(mextrabinder);
        }
        return;
        mextrabinder;
        handler;
        JVM INSTR monitorexit ;
        throw mextrabinder;
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

    public final void unregisterCallback(mControllerObj mcontrollerobj)
    {
        MediaControllerCompatApi21.unregisterCallback(mControllerObj, mControllerObj(mcontrollerobj));
        if (mExtraBinder == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        try
        {
            mcontrollerobj = (ExtraCallback)mCallbackMap.remove(mcontrollerobj);
        }
        // Misplaced declaration of an exception variable
        catch (mControllerObj mcontrollerobj)
        {
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in unregisterCallback. ").append(mcontrollerobj).toString());
            return;
        }
        if (mcontrollerobj == null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        mExtraBinder.unregisterCallbackListener(mcontrollerobj);
        return;
        synchronized (mPendingCallbacks)
        {
            mPendingCallbacks.remove(mcontrollerobj);
        }
        return;
        mcontrollerobj;
        list;
        JVM INSTR monitorexit ;
        throw mcontrollerobj;
    }



/*
    static IMediaSession access$002(ExtraCallback._cls4 _pcls4, IMediaSession imediasession)
    {
        _pcls4.mExtraBinder = imediasession;
        return imediasession;
    }

*/


    public ExtraCallback.mCallback(Context context, ExtraCallback.mCallback mcallback)
        throws RemoteException
    {
        mCallbackMap = new HashMap();
        mPendingCallbacks = new ArrayList();
        mControllerObj = MediaControllerCompatApi21.fromToken(context, mcallback.mControllerObj());
        if (mControllerObj == null)
        {
            throw new RemoteException();
        } else
        {
            requestExtraBinder();
            return;
        }
    }

    public requestExtraBinder(Context context, MediaSessionCompat mediasessioncompat)
    {
        mCallbackMap = new HashMap();
        mPendingCallbacks = new ArrayList();
        mControllerObj = MediaControllerCompatApi21.fromToken(context, mediasessioncompat.getSessionToken().mControllerObj());
        requestExtraBinder();
    }
}
