// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, IMediaControllerCallback, PlaybackStateCompat, ParcelableVolumeInfo

static class mRcc
    implements mRcc
{
    private static final class Command
    {

        public final String command;
        public final Bundle extras;
        public final ResultReceiver stub;

        public Command(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            command = s;
            extras = bundle;
            stub = resultreceiver;
        }
    }

    class MediaSessionStub extends IMediaSession.Stub
    {

        final MediaSessionCompat.MediaSessionImplBase this$0;

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            postToHandler(25, mediadescriptioncompat);
        }

        public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            postToHandler(26, mediadescriptioncompat, i);
        }

        public void adjustVolume(int i, int j, String s)
        {
            MediaSessionCompat.MediaSessionImplBase.this.adjustVolume(i, j);
        }

        public void fastForward()
            throws RemoteException
        {
            postToHandler(16);
        }

        public Bundle getExtras()
        {
            Bundle bundle;
            synchronized (mLock)
            {
                bundle = mExtras;
            }
            return bundle;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public long getFlags()
        {
            long l;
            synchronized (mLock)
            {
                l = mFlags;
            }
            return l;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public PendingIntent getLaunchPendingIntent()
        {
            PendingIntent pendingintent;
            synchronized (mLock)
            {
                pendingintent = mSessionActivity;
            }
            return pendingintent;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public MediaMetadataCompat getMetadata()
        {
            return mMetadata;
        }

        public String getPackageName()
        {
            return mPackageName;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            return getStateWithUpdatedPosition();
        }

        public List getQueue()
        {
            List list;
            synchronized (mLock)
            {
                list = mQueue;
            }
            return list;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public CharSequence getQueueTitle()
        {
            return mQueueTitle;
        }

        public int getRatingType()
        {
            return mRatingType;
        }

        public int getRepeatMode()
        {
            return mRepeatMode;
        }

        public String getTag()
        {
            return mTag;
        }

        public ParcelableVolumeInfo getVolumeAttributes()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            VolumeProviderCompat volumeprovidercompat;
            int l;
            int i1;
            l = mVolumeType;
            i1 = mLocalStream;
            volumeprovidercompat = mVolumeProvider;
            if (l != 2) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            int k;
            i = volumeprovidercompat.getVolumeControl();
            j = volumeprovidercompat.getMaxVolume();
            k = volumeprovidercompat.getCurrentVolume();
_L3:
            obj;
            JVM INSTR monitorexit ;
            return new ParcelableVolumeInfo(l, i1, i, j, k);
_L2:
            i = 2;
            j = mAudioManager.getStreamMaxVolume(i1);
            k = mAudioManager.getStreamVolume(i1);
              goto _L3
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean isShuffleModeEnabled()
        {
            return mShuffleModeEnabled;
        }

        public boolean isTransportControlEnabled()
        {
            return (mFlags & 2) != 0;
        }

        public void next()
            throws RemoteException
        {
            postToHandler(14);
        }

        public void pause()
            throws RemoteException
        {
            postToHandler(12);
        }

        public void play()
            throws RemoteException
        {
            postToHandler(7);
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(8, s, bundle);
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(9, s, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(10, uri, bundle);
        }

        public void prepare()
            throws RemoteException
        {
            postToHandler(3);
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(4, s, bundle);
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(5, s, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(6, uri, bundle);
        }

        public void previous()
            throws RemoteException
        {
            postToHandler(15);
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            postToHandler(19, ratingcompat);
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            if (mDestroyed)
            {
                try
                {
                    imediacontrollercallback.onSessionDestroyed();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (IMediaControllerCallback imediacontrollercallback)
                {
                    return;
                }
            } else
            {
                mControllerCallbacks.register(imediacontrollercallback);
                return;
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            postToHandler(27, mediadescriptioncompat);
        }

        public void removeQueueItemAt(int i)
        {
            postToHandler(28, i);
        }

        public void rewind()
            throws RemoteException
        {
            postToHandler(17);
        }

        public void seekTo(long l)
            throws RemoteException
        {
            postToHandler(18, Long.valueOf(l));
        }

        public void sendCommand(String s, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultreceiverwrapper)
        {
            postToHandler(1, new Command(s, bundle, MediaSessionCompat.ResultReceiverWrapper.access$100(resultreceiverwrapper)));
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(20, s, bundle);
        }

        public boolean sendMediaButton(KeyEvent keyevent)
        {
            boolean flag;
            if ((mFlags & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                postToHandler(21, keyevent);
            }
            return flag;
        }

        public void setRepeatMode(int i)
            throws RemoteException
        {
            postToHandler(23, i);
        }

        public void setShuffleModeEnabled(boolean flag)
            throws RemoteException
        {
            postToHandler(24, Boolean.valueOf(flag));
        }

        public void setVolumeTo(int i, int j, String s)
        {
            MediaSessionCompat.MediaSessionImplBase.this.setVolumeTo(i, j);
        }

        public void skipToQueueItem(long l)
        {
            postToHandler(11, Long.valueOf(l));
        }

        public void stop()
            throws RemoteException
        {
            postToHandler(13);
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            mControllerCallbacks.unregister(imediacontrollercallback);
        }

        MediaSessionStub()
        {
            this$0 = MediaSessionCompat.MediaSessionImplBase.this;
            super();
        }
    }

    class MessageHandler extends Handler
    {

        private static final int KEYCODE_MEDIA_PAUSE = 127;
        private static final int KEYCODE_MEDIA_PLAY = 126;
        private static final int MSG_ADD_QUEUE_ITEM = 25;
        private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
        private static final int MSG_ADJUST_VOLUME = 2;
        private static final int MSG_COMMAND = 1;
        private static final int MSG_CUSTOM_ACTION = 20;
        private static final int MSG_FAST_FORWARD = 16;
        private static final int MSG_MEDIA_BUTTON = 21;
        private static final int MSG_NEXT = 14;
        private static final int MSG_PAUSE = 12;
        private static final int MSG_PLAY = 7;
        private static final int MSG_PLAY_MEDIA_ID = 8;
        private static final int MSG_PLAY_SEARCH = 9;
        private static final int MSG_PLAY_URI = 10;
        private static final int MSG_PREPARE = 3;
        private static final int MSG_PREPARE_MEDIA_ID = 4;
        private static final int MSG_PREPARE_SEARCH = 5;
        private static final int MSG_PREPARE_URI = 6;
        private static final int MSG_PREVIOUS = 15;
        private static final int MSG_RATE = 19;
        private static final int MSG_REMOVE_QUEUE_ITEM = 27;
        private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
        private static final int MSG_REWIND = 17;
        private static final int MSG_SEEK_TO = 18;
        private static final int MSG_SET_REPEAT_MODE = 23;
        private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
        private static final int MSG_SET_VOLUME = 22;
        private static final int MSG_SKIP_TO_ITEM = 11;
        private static final int MSG_STOP = 13;
        final MediaSessionCompat.MediaSessionImplBase this$0;

        private void onMediaButtonEvent(KeyEvent keyevent, MediaSessionCompat.Callback callback)
        {
            boolean flag2 = true;
            if (keyevent != null && keyevent.getAction() == 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            boolean flag;
            boolean flag1;
            long l;
            if (mState == null)
            {
                l = 0L;
            } else
            {
                l = mState.getActions();
            }
            keyevent.getKeyCode();
            JVM INSTR lookupswitch 9: default 116
        //                       79: 117
        //                       85: 117
        //                       86: 261
        //                       87: 229
        //                       88: 245
        //                       89: 291
        //                       90: 275
        //                       126: 197
        //                       127: 213;
               goto _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L8:
            continue; /* Loop/switch isn't completed */
_L3:
            return;
_L4:
            if (mState != null && mState.getState() == 3)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if ((516L & l) != 0L)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if ((514L & l) == 0L)
            {
                flag2 = false;
            }
            if (flag && flag2)
            {
                callback.onPause();
                return;
            }
            continue; /* Loop/switch isn't completed */
_L10:
            if ((4L & l) == 0L) goto _L1; else goto _L12
_L12:
            callback.onPlay();
            return;
_L11:
            if ((2L & l) == 0L) goto _L1; else goto _L13
_L13:
            callback.onPause();
            return;
_L6:
            if ((32L & l) == 0L) goto _L1; else goto _L14
_L14:
            callback.onSkipToNext();
            return;
_L7:
            if ((16L & l) == 0L) goto _L1; else goto _L15
_L15:
            callback.onSkipToPrevious();
            return;
_L5:
            if ((1L & l) == 0L) goto _L1; else goto _L16
_L16:
            callback.onStop();
            return;
_L9:
            if ((64L & l) == 0L) goto _L1; else goto _L17
_L17:
            callback.onFastForward();
            return;
            if ((8L & l) == 0L) goto _L1; else goto _L18
_L18:
            callback.onRewind();
            return;
            if (flag || !flag1) goto _L1; else goto _L19
_L19:
            callback.onPlay();
            return;
        }

        public void handleMessage(Message message)
        {
            MediaSessionCompat.Callback callback = mCallback;
            if (callback != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                message = (Command)message.obj;
                callback.onCommand(((Command) (message)).command, ((Command) (message)).extras, ((Command) (message)).stub);
                return;

            case 21: // '\025'
                message = (KeyEvent)message.obj;
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.putExtra("android.intent.extra.KEY_EVENT", message);
                if (!callback.onMediaButtonEvent(intent))
                {
                    onMediaButtonEvent(message, callback);
                    return;
                }
                break;

            case 3: // '\003'
                callback.onPrepare();
                return;

            case 4: // '\004'
                callback.onPrepareFromMediaId((String)message.obj, message.getData());
                return;

            case 5: // '\005'
                callback.onPrepareFromSearch((String)message.obj, message.getData());
                return;

            case 6: // '\006'
                callback.onPrepareFromUri((Uri)message.obj, message.getData());
                return;

            case 7: // '\007'
                callback.onPlay();
                return;

            case 8: // '\b'
                callback.onPlayFromMediaId((String)message.obj, message.getData());
                return;

            case 9: // '\t'
                callback.onPlayFromSearch((String)message.obj, message.getData());
                return;

            case 10: // '\n'
                callback.onPlayFromUri((Uri)message.obj, message.getData());
                return;

            case 11: // '\013'
                callback.onSkipToQueueItem(((Long)message.obj).longValue());
                return;

            case 12: // '\f'
                callback.onPause();
                return;

            case 13: // '\r'
                callback.onStop();
                return;

            case 14: // '\016'
                callback.onSkipToNext();
                return;

            case 15: // '\017'
                callback.onSkipToPrevious();
                return;

            case 16: // '\020'
                callback.onFastForward();
                return;

            case 17: // '\021'
                callback.onRewind();
                return;

            case 18: // '\022'
                callback.onSeekTo(((Long)message.obj).longValue());
                return;

            case 19: // '\023'
                callback.onSetRating((RatingCompat)message.obj);
                return;

            case 20: // '\024'
                callback.onCustomAction((String)message.obj, message.getData());
                return;

            case 25: // '\031'
                callback.onAddQueueItem((MediaDescriptionCompat)message.obj);
                return;

            case 26: // '\032'
                callback.onAddQueueItem((MediaDescriptionCompat)message.obj, message.arg1);
                return;

            case 27: // '\033'
                callback.onRemoveQueueItem((MediaDescriptionCompat)message.obj);
                return;

            case 28: // '\034'
                callback.onRemoveQueueItemAt(message.arg1);
                return;

            case 2: // '\002'
                adjustVolume(message.arg1, 0);
                return;

            case 22: // '\026'
                setVolumeTo(message.arg1, 0);
                return;

            case 23: // '\027'
                callback.onSetRepeatMode(message.arg1);
                return;

            case 24: // '\030'
                callback.onSetShuffleModeEnabled(((Boolean)message.obj).booleanValue());
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public void post(int i)
        {
            post(i, null);
        }

        public void post(int i, Object obj)
        {
            obtainMessage(i, obj).sendToTarget();
        }

        public void post(int i, Object obj, int j)
        {
            obtainMessage(i, j, 0, obj).sendToTarget();
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        public MessageHandler(Looper looper)
        {
            this$0 = MediaSessionCompat.MediaSessionImplBase.this;
            super(looper);
        }
    }


    static final int RCC_PLAYSTATE_NONE = 0;
    final AudioManager mAudioManager;
    volatile mIsRccRegistered mCallback;
    private final Context mContext;
    final RemoteCallbackList mControllerCallbacks = new RemoteCallbackList();
    boolean mDestroyed;
    Bundle mExtras;
    int mFlags;
    private MessageHandler mHandler;
    boolean mIsActive;
    private boolean mIsMbrRegistered;
    private boolean mIsRccRegistered;
    int mLocalStream;
    final Object mLock = new Object();
    private final ComponentName mMediaButtonReceiverComponentName;
    private final PendingIntent mMediaButtonReceiverIntent;
    MediaMetadataCompat mMetadata;
    final String mPackageName;
    List mQueue;
    CharSequence mQueueTitle;
    int mRatingType;
    final RemoteControlClient mRcc;
    int mRepeatMode;
    PendingIntent mSessionActivity;
    boolean mShuffleModeEnabled;
    PlaybackStateCompat mState;
    private final MediaSessionStub mStub;
    final String mTag;
    private final MediaSessionStub mToken;
    private android.support.v4.media.mplBase.MediaSessionStub mVolumeCallback;
    VolumeProviderCompat mVolumeProvider;
    int mVolumeType;

    private void sendEvent(String s, Bundle bundle)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onEvent(s, bundle);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendExtras(Bundle bundle)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onExtrasChanged(bundle);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendMetadata(MediaMetadataCompat mediametadatacompat)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onMetadataChanged(mediametadatacompat);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendQueue(List list)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onQueueChanged(list);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendQueueTitle(CharSequence charsequence)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onQueueTitleChanged(charsequence);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendRepeatMode(int i)
    {
        int j = mControllerCallbacks.beginBroadcast() - 1;
        while (j >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(j);
            try
            {
                imediacontrollercallback.onRepeatModeChanged(i);
            }
            catch (RemoteException remoteexception) { }
            j--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendSessionDestroyed()
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onSessionDestroyed();
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
        mControllerCallbacks.kill();
    }

    private void sendShuffleModeEnabled(boolean flag)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onShuffleModeChanged(flag);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    private void sendState(PlaybackStateCompat playbackstatecompat)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onPlaybackStateChanged(playbackstatecompat);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    void adjustVolume(int i, int j)
    {
        if (mVolumeType == 2)
        {
            if (mVolumeProvider != null)
            {
                mVolumeProvider.onAdjustVolume(i);
            }
            return;
        } else
        {
            mAudioManager.adjustStreamVolume(mLocalStream, i, j);
            return;
        }
    }

    android.media.diaSessionImplBase buildRccMetadata(Bundle bundle)
    {
        android.media.diaSessionImplBase diasessionimplbase = mRcc.editMetadata(true);
        if (bundle != null)
        {
            if (bundle.containsKey("android.media.metadata.ART"))
            {
                diasessionimplbase.tmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ART"));
            } else
            if (bundle.containsKey("android.media.metadata.ALBUM_ART"))
            {
                diasessionimplbase.tmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ALBUM_ART"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM"))
            {
                diasessionimplbase.ring(1, bundle.getString("android.media.metadata.ALBUM"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST"))
            {
                diasessionimplbase.ring(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.ARTIST"))
            {
                diasessionimplbase.ring(2, bundle.getString("android.media.metadata.ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.AUTHOR"))
            {
                diasessionimplbase.ring(3, bundle.getString("android.media.metadata.AUTHOR"));
            }
            if (bundle.containsKey("android.media.metadata.COMPILATION"))
            {
                diasessionimplbase.ring(15, bundle.getString("android.media.metadata.COMPILATION"));
            }
            if (bundle.containsKey("android.media.metadata.COMPOSER"))
            {
                diasessionimplbase.ring(4, bundle.getString("android.media.metadata.COMPOSER"));
            }
            if (bundle.containsKey("android.media.metadata.DATE"))
            {
                diasessionimplbase.ring(5, bundle.getString("android.media.metadata.DATE"));
            }
            if (bundle.containsKey("android.media.metadata.DISC_NUMBER"))
            {
                diasessionimplbase.ng(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.DURATION"))
            {
                diasessionimplbase.ng(9, bundle.getLong("android.media.metadata.DURATION"));
            }
            if (bundle.containsKey("android.media.metadata.GENRE"))
            {
                diasessionimplbase.ring(6, bundle.getString("android.media.metadata.GENRE"));
            }
            if (bundle.containsKey("android.media.metadata.TITLE"))
            {
                diasessionimplbase.ring(7, bundle.getString("android.media.metadata.TITLE"));
            }
            if (bundle.containsKey("android.media.metadata.TRACK_NUMBER"))
            {
                diasessionimplbase.ng(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.WRITER"))
            {
                diasessionimplbase.ring(11, bundle.getString("android.media.metadata.WRITER"));
                return diasessionimplbase;
            }
        }
        return diasessionimplbase;
    }

    public String getCallingPackage()
    {
        return null;
    }

    public Object getMediaSession()
    {
        return null;
    }

    int getRccStateFromState(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 6: // '\006'
        case 8: // '\b'
            return 8;

        case 7: // '\007'
            return 9;

        case 4: // '\004'
            return 4;

        case 0: // '\0'
            return 0;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 5: // '\005'
            return 5;

        case 9: // '\t'
            return 7;

        case 10: // '\n'
        case 11: // '\013'
            return 6;

        case 1: // '\001'
            return 1;
        }
    }

    int getRccTransportControlFlagsFromActions(long l)
    {
        int j = 0;
        if ((1L & l) != 0L)
        {
            j = 0 | 0x20;
        }
        int i = j;
        if ((2L & l) != 0L)
        {
            i = j | 0x10;
        }
        j = i;
        if ((4L & l) != 0L)
        {
            j = i | 4;
        }
        i = j;
        if ((8L & l) != 0L)
        {
            i = j | 2;
        }
        j = i;
        if ((16L & l) != 0L)
        {
            j = i | 1;
        }
        i = j;
        if ((32L & l) != 0L)
        {
            i = j | 0x80;
        }
        j = i;
        if ((64L & l) != 0L)
        {
            j = i | 0x40;
        }
        i = j;
        if ((512L & l) != 0L)
        {
            i = j | 8;
        }
        return i;
    }

    public Object getRemoteControlClient()
    {
        return null;
    }

    public mRcc getSessionToken()
    {
        return mToken;
    }

    PlaybackStateCompat getStateWithUpdatedPosition()
    {
        long l1 = -1L;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        PlaybackStateCompat playbackstatecompat = mState;
        long l = l1;
        if (mMetadata == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        l = l1;
        if (mMetadata.containsKey("android.media.metadata.DURATION"))
        {
            l = mMetadata.getLong("android.media.metadata.DURATION");
        }
        obj;
        JVM INSTR monitorexit ;
        Object obj1;
        obj1 = null;
        obj = obj1;
        if (playbackstatecompat == null) goto _L2; else goto _L1
_L1:
        if (playbackstatecompat.getState() == 3 || playbackstatecompat.getState() == 4) goto _L4; else goto _L3
_L3:
        obj = obj1;
        if (playbackstatecompat.getState() != 5) goto _L2; else goto _L4
_L4:
        long l2;
        l1 = playbackstatecompat.getLastPositionUpdateTime();
        l2 = SystemClock.elapsedRealtime();
        obj = obj1;
        if (l1 <= 0L) goto _L2; else goto _L5
_L5:
        l1 = (long)(playbackstatecompat.getPlaybackSpeed() * (float)(l2 - l1)) + playbackstatecompat.getPosition();
        if (l < 0L || l1 <= l) goto _L7; else goto _L6
_L6:
        obj = new eTime(playbackstatecompat);
        ((eTime) (obj)).eTime(playbackstatecompat.getState(), l, playbackstatecompat.getPlaybackSpeed(), l2);
        obj = ((eTime) (obj)).eTime();
_L2:
        Exception exception;
        if (obj == null)
        {
            return playbackstatecompat;
        } else
        {
            return ((PlaybackStateCompat) (obj));
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        l = l1;
        if (l1 < 0L)
        {
            l = 0L;
        }
        if (true) goto _L6; else goto _L8
_L8:
    }

    public boolean isActive()
    {
        return mIsActive;
    }

    void postToHandler(int i)
    {
        postToHandler(i, null);
    }

    void postToHandler(int i, int j)
    {
        postToHandler(i, null, j);
    }

    void postToHandler(int i, Object obj)
    {
        postToHandler(i, obj, ((Bundle) (null)));
    }

    void postToHandler(int i, Object obj, int j)
    {
        synchronized (mLock)
        {
            if (mHandler != null)
            {
                mHandler.post(i, obj, j);
            }
        }
        return;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
    }

    void postToHandler(int i, Object obj, Bundle bundle)
    {
        synchronized (mLock)
        {
            if (mHandler != null)
            {
                mHandler.post(i, obj, bundle);
            }
        }
        return;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
    }

    void registerMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
    {
        mAudioManager.registerMediaButtonEventReceiver(componentname);
    }

    public void release()
    {
        mIsActive = false;
        mDestroyed = true;
        update();
        sendSessionDestroyed();
    }

    public void sendSessionEvent(String s, Bundle bundle)
    {
        sendEvent(s, bundle);
    }

    void sendVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
    {
        int i = mControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
            try
            {
                imediacontrollercallback.onVolumeInfoChanged(parcelablevolumeinfo);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mControllerCallbacks.finishBroadcast();
    }

    public void setActive(boolean flag)
    {
        if (flag != mIsActive)
        {
            mIsActive = flag;
            if (update())
            {
                setMetadata(mMetadata);
                setPlaybackState(mState);
                return;
            }
        }
    }

    public void setCallback(mState mstate, Handler handler)
    {
        mCallback = mstate;
        if (mstate != null)
        {
            mstate = handler;
            if (handler == null)
            {
                mstate = new Handler();
            }
            synchronized (mLock)
            {
                mHandler = new MessageHandler(mstate.getLooper());
            }
            return;
        } else
        {
            return;
        }
        mstate;
        handler;
        JVM INSTR monitorexit ;
        throw mstate;
    }

    public void setExtras(Bundle bundle)
    {
        mExtras = bundle;
        sendExtras(bundle);
    }

    public void setFlags(int i)
    {
        synchronized (mLock)
        {
            mFlags = i;
        }
        update();
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setMediaButtonReceiver(PendingIntent pendingintent)
    {
    }

    public void setMetadata(MediaMetadataCompat mediametadatacompat)
    {
        obj = mediametadatacompat;
        if (mediametadatacompat != null)
        {
            obj = (new android.support.v4.media.mplBase(mediametadatacompat, MediaSessionCompat.sMaxBitmapSize)).e();
        }
        synchronized (mLock)
        {
            mMetadata = ((MediaMetadataCompat) (obj));
        }
        sendMetadata(((MediaMetadataCompat) (obj)));
        if (!mIsActive)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_53;
        obj;
        mediametadatacompat;
        JVM INSTR monitorexit ;
        throw obj;
        if (obj == null)
        {
            mediametadatacompat = null;
        } else
        {
            mediametadatacompat = ((MediaMetadataCompat) (obj)).getBundle();
        }
        buildRccMetadata(mediametadatacompat).();
        return;
    }

    public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
    {
        synchronized (mLock)
        {
            mState = playbackstatecompat;
        }
        sendState(playbackstatecompat);
        if (!mIsActive)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_32;
        playbackstatecompat;
        obj;
        JVM INSTR monitorexit ;
        throw playbackstatecompat;
        if (playbackstatecompat == null)
        {
            mRcc.setPlaybackState(0);
            mRcc.setTransportControlFlags(0);
            return;
        } else
        {
            setRccState(playbackstatecompat);
            mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(playbackstatecompat.getActions()));
            return;
        }
    }

    public void setPlaybackToLocal(int i)
    {
        if (mVolumeProvider != null)
        {
            mVolumeProvider.setCallback(null);
        }
        mVolumeType = 1;
        sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, 2, mAudioManager.getStreamMaxVolume(mLocalStream), mAudioManager.getStreamVolume(mLocalStream)));
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
    {
        if (volumeprovidercompat == null)
        {
            throw new IllegalArgumentException("volumeProvider may not be null");
        }
        if (mVolumeProvider != null)
        {
            mVolumeProvider.setCallback(null);
        }
        mVolumeType = 2;
        mVolumeProvider = volumeprovidercompat;
        sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, mVolumeProvider.getVolumeControl(), mVolumeProvider.getMaxVolume(), mVolumeProvider.getCurrentVolume()));
        volumeprovidercompat.setCallback(mVolumeCallback);
    }

    public void setQueue(List list)
    {
        mQueue = list;
        sendQueue(list);
    }

    public void setQueueTitle(CharSequence charsequence)
    {
        mQueueTitle = charsequence;
        sendQueueTitle(charsequence);
    }

    public void setRatingType(int i)
    {
        mRatingType = i;
    }

    void setRccState(PlaybackStateCompat playbackstatecompat)
    {
        mRcc.setPlaybackState(getRccStateFromState(playbackstatecompat.getState()));
    }

    public void setRepeatMode(int i)
    {
        if (mRepeatMode != i)
        {
            mRepeatMode = i;
            sendRepeatMode(i);
        }
    }

    public void setSessionActivity(PendingIntent pendingintent)
    {
        synchronized (mLock)
        {
            mSessionActivity = pendingintent;
        }
        return;
        pendingintent;
        obj;
        JVM INSTR monitorexit ;
        throw pendingintent;
    }

    public void setShuffleModeEnabled(boolean flag)
    {
        if (mShuffleModeEnabled != flag)
        {
            mShuffleModeEnabled = flag;
            sendShuffleModeEnabled(flag);
        }
    }

    void setVolumeTo(int i, int j)
    {
        if (mVolumeType == 2)
        {
            if (mVolumeProvider != null)
            {
                mVolumeProvider.onSetVolumeTo(i);
            }
            return;
        } else
        {
            mAudioManager.setStreamVolume(mLocalStream, i, j);
            return;
        }
    }

    void unregisterMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
    {
        mAudioManager.unregisterMediaButtonEventReceiver(componentname);
    }

    boolean update()
    {
        boolean flag1 = false;
        if (!mIsActive) goto _L2; else goto _L1
_L1:
        if (mIsMbrRegistered || (mFlags & 1) == 0) goto _L4; else goto _L3
_L3:
        registerMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
        mIsMbrRegistered = true;
_L12:
        if (mIsRccRegistered || (mFlags & 2) == 0) goto _L6; else goto _L5
_L5:
        boolean flag;
        mAudioManager.registerRemoteControlClient(mRcc);
        mIsRccRegistered = true;
        flag = true;
_L8:
        return flag;
_L4:
        if (mIsMbrRegistered && (mFlags & 1) == 0)
        {
            unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
            mIsMbrRegistered = false;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        flag = flag1;
        if (!mIsRccRegistered) goto _L8; else goto _L7
_L7:
        flag = flag1;
        if ((mFlags & 2) != 0) goto _L8; else goto _L9
_L9:
        mRcc.setPlaybackState(0);
        mAudioManager.unregisterRemoteControlClient(mRcc);
        mIsRccRegistered = false;
        return false;
_L2:
        if (mIsMbrRegistered)
        {
            unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
            mIsMbrRegistered = false;
        }
        flag = flag1;
        if (!mIsRccRegistered) goto _L8; else goto _L10
_L10:
        mRcc.setPlaybackState(0);
        mAudioManager.unregisterRemoteControlClient(mRcc);
        mIsRccRegistered = false;
        return false;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public _cls1.this._cls0(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
    {
        mDestroyed = false;
        mIsActive = false;
        mIsMbrRegistered = false;
        mIsRccRegistered = false;
        mVolumeCallback = new android.support.v4.media.VolumeProviderCompat.Callback() {

            final MediaSessionCompat.MediaSessionImplBase this$0;

            public void onVolumeChanged(VolumeProviderCompat volumeprovidercompat)
            {
                if (mVolumeProvider != volumeprovidercompat)
                {
                    return;
                } else
                {
                    volumeprovidercompat = new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeprovidercompat.getVolumeControl(), volumeprovidercompat.getMaxVolume(), volumeprovidercompat.getCurrentVolume());
                    sendVolumeInfoChanged(volumeprovidercompat);
                    return;
                }
            }

            
            {
                this$0 = MediaSessionCompat.MediaSessionImplBase.this;
                super();
            }
        };
        if (componentname == null)
        {
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
        } else
        {
            mContext = context;
            mPackageName = context.getPackageName();
            mAudioManager = (AudioManager)context.getSystemService("audio");
            mTag = s;
            mMediaButtonReceiverComponentName = componentname;
            mMediaButtonReceiverIntent = pendingintent;
            mStub = new MediaSessionStub();
            mToken = new mToken(mStub);
            mRatingType = 0;
            mVolumeType = 1;
            mLocalStream = 3;
            mRcc = new RemoteControlClient(pendingintent);
            return;
        }
    }
}
