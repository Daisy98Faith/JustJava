// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, MediaSessionCompatApi21, MediaSessionCompatApi24, IMediaControllerCallback, 
//            PlaybackStateCompat, MediaSessionCompatApi22, MediaSessionCompatApi26, ParcelableVolumeInfo

static class mSessionObj
    implements mSessionObj
{
    class ExtraSession extends IMediaSession.Stub
    {

        final MediaSessionCompat.MediaSessionImplApi21 this$0;

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            throw new AssertionError();
        }

        public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            throw new AssertionError();
        }

        public void adjustVolume(int i, int j, String s)
        {
            throw new AssertionError();
        }

        public void fastForward()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public Bundle getExtras()
        {
            throw new AssertionError();
        }

        public long getFlags()
        {
            throw new AssertionError();
        }

        public PendingIntent getLaunchPendingIntent()
        {
            throw new AssertionError();
        }

        public MediaMetadataCompat getMetadata()
        {
            throw new AssertionError();
        }

        public String getPackageName()
        {
            throw new AssertionError();
        }

        public PlaybackStateCompat getPlaybackState()
        {
            return mPlaybackState;
        }

        public List getQueue()
        {
            return null;
        }

        public CharSequence getQueueTitle()
        {
            throw new AssertionError();
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
            throw new AssertionError();
        }

        public ParcelableVolumeInfo getVolumeAttributes()
        {
            throw new AssertionError();
        }

        public boolean isShuffleModeEnabled()
        {
            return mShuffleModeEnabled;
        }

        public boolean isTransportControlEnabled()
        {
            throw new AssertionError();
        }

        public void next()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void pause()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void play()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepare()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void previous()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            if (!mDestroyed)
            {
                mExtraControllerCallbacks.register(imediacontrollercallback);
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            throw new AssertionError();
        }

        public void removeQueueItemAt(int i)
        {
            throw new AssertionError();
        }

        public void rewind()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void seekTo(long l)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void sendCommand(String s, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultreceiverwrapper)
        {
            throw new AssertionError();
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public boolean sendMediaButton(KeyEvent keyevent)
        {
            throw new AssertionError();
        }

        public void setRepeatMode(int i)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setShuffleModeEnabled(boolean flag)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setVolumeTo(int i, int j, String s)
        {
            throw new AssertionError();
        }

        public void skipToQueueItem(long l)
        {
            throw new AssertionError();
        }

        public void stop()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            mExtraControllerCallbacks.unregister(imediacontrollercallback);
        }

        ExtraSession()
        {
            this$0 = MediaSessionCompat.MediaSessionImplApi21.this;
            super();
        }
    }


    private boolean mDestroyed;
    private final RemoteCallbackList mExtraControllerCallbacks;
    private ExtraSession mExtraSessionBinder;
    private PlaybackStateCompat mPlaybackState;
    int mRatingType;
    int mRepeatMode;
    private final Object mSessionObj;
    boolean mShuffleModeEnabled;
    private final ExtraSession mToken;

    public String getCallingPackage()
    {
        if (android.os.MediaSessionImplApi21 < 24)
        {
            return null;
        } else
        {
            return MediaSessionCompatApi24.getCallingPackage(mSessionObj);
        }
    }

    ExtraSession getExtraSessionBinder()
    {
        if (mExtraSessionBinder == null)
        {
            mExtraSessionBinder = new ExtraSession();
        }
        return mExtraSessionBinder;
    }

    public Object getMediaSession()
    {
        return mSessionObj;
    }

    public Object getRemoteControlClient()
    {
        return null;
    }

    public mSessionObj getSessionToken()
    {
        return mToken;
    }

    public boolean isActive()
    {
        return MediaSessionCompatApi21.isActive(mSessionObj);
    }

    public void release()
    {
        mDestroyed = true;
        MediaSessionCompatApi21.release(mSessionObj);
    }

    public void sendSessionEvent(String s, Bundle bundle)
    {
        if (android.os.lback < 23)
        {
            int i = mExtraControllerCallbacks.beginBroadcast() - 1;
            while (i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onEvent(s, bundle);
                }
                catch (RemoteException remoteexception) { }
                i--;
            }
            mExtraControllerCallbacks.finishBroadcast();
        }
        MediaSessionCompatApi21.sendSessionEvent(mSessionObj, s, bundle);
    }

    public void setActive(boolean flag)
    {
        MediaSessionCompatApi21.setActive(mSessionObj, flag);
    }

    public void setCallback(mSessionObj msessionobj, Handler handler)
    {
        Object obj1 = mSessionObj;
        Object obj;
        if (msessionobj == null)
        {
            obj = null;
        } else
        {
            obj = msessionobj.mSessionObj;
        }
        MediaSessionCompatApi21.setCallback(obj1, obj, handler);
        if (android.os.pi21.setCallback < 26 && msessionobj != null)
        {
            msessionobj.mSessionObj = new WeakReference(this);
        }
    }

    public void setExtras(Bundle bundle)
    {
        MediaSessionCompatApi21.setExtras(mSessionObj, bundle);
    }

    public void setFlags(int i)
    {
        MediaSessionCompatApi21.setFlags(mSessionObj, i);
    }

    public void setMediaButtonReceiver(PendingIntent pendingintent)
    {
        MediaSessionCompatApi21.setMediaButtonReceiver(mSessionObj, pendingintent);
    }

    public void setMetadata(MediaMetadataCompat mediametadatacompat)
    {
        Object obj = mSessionObj;
        if (mediametadatacompat == null)
        {
            mediametadatacompat = null;
        } else
        {
            mediametadatacompat = ((MediaMetadataCompat) (mediametadatacompat.getMediaMetadata()));
        }
        MediaSessionCompatApi21.setMetadata(obj, mediametadatacompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
    {
        mPlaybackState = playbackstatecompat;
        int i = mExtraControllerCallbacks.beginBroadcast() - 1;
        while (i >= 0) 
        {
            Object obj = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
            try
            {
                ((IMediaControllerCallback) (obj)).onPlaybackStateChanged(playbackstatecompat);
            }
            catch (RemoteException remoteexception) { }
            i--;
        }
        mExtraControllerCallbacks.finishBroadcast();
        obj = mSessionObj;
        if (playbackstatecompat == null)
        {
            playbackstatecompat = null;
        } else
        {
            playbackstatecompat = ((PlaybackStateCompat) (playbackstatecompat.getPlaybackState()));
        }
        MediaSessionCompatApi21.setPlaybackState(obj, playbackstatecompat);
    }

    public void setPlaybackToLocal(int i)
    {
        MediaSessionCompatApi21.setPlaybackToLocal(mSessionObj, i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
    {
        MediaSessionCompatApi21.setPlaybackToRemote(mSessionObj, volumeprovidercompat.getVolumeProvider());
    }

    public void setQueue(List list)
    {
        ArrayList arraylist = null;
        if (list != null)
        {
            ArrayList arraylist1 = new ArrayList();
            list = list.iterator();
            do
            {
                arraylist = arraylist1;
                if (!list.hasNext())
                {
                    break;
                }
                arraylist1.add(((mSessionObj)list.next()).());
            } while (true);
        }
        MediaSessionCompatApi21.setQueue(mSessionObj, arraylist);
    }

    public void setQueueTitle(CharSequence charsequence)
    {
        MediaSessionCompatApi21.setQueueTitle(mSessionObj, charsequence);
    }

    public void setRatingType(int i)
    {
        if (android.os.MediaSessionImplApi21 < 22)
        {
            mRatingType = i;
            return;
        } else
        {
            MediaSessionCompatApi22.setRatingType(mSessionObj, i);
            return;
        }
    }

    public void setRepeatMode(int i)
    {
        if (android.os.lback < 26)
        {
            if (mRepeatMode != i)
            {
                mRepeatMode = i;
                int j = mExtraControllerCallbacks.beginBroadcast() - 1;
                while (j >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(j);
                    try
                    {
                        imediacontrollercallback.onRepeatModeChanged(i);
                    }
                    catch (RemoteException remoteexception) { }
                    j--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
            return;
        } else
        {
            MediaSessionCompatApi26.setRepeatMode(mSessionObj, i);
            return;
        }
    }

    public void setSessionActivity(PendingIntent pendingintent)
    {
        MediaSessionCompatApi21.setSessionActivity(mSessionObj, pendingintent);
    }

    public void setShuffleModeEnabled(boolean flag)
    {
        if (android.os.lback < 26)
        {
            if (mShuffleModeEnabled != flag)
            {
                mShuffleModeEnabled = flag;
                int i = mExtraControllerCallbacks.beginBroadcast() - 1;
                while (i >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                    try
                    {
                        imediacontrollercallback.onShuffleModeChanged(flag);
                    }
                    catch (RemoteException remoteexception) { }
                    i--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
            return;
        } else
        {
            MediaSessionCompatApi26.setShuffleModeEnabled(mSessionObj, flag);
            return;
        }
    }




    public ExtraSession.this._cls0(Context context, String s)
    {
        mDestroyed = false;
        mExtraControllerCallbacks = new RemoteCallbackList();
        mSessionObj = MediaSessionCompatApi21.createSession(context, s);
        mToken = new mToken(MediaSessionCompatApi21.getSessionToken(mSessionObj));
    }

    public mSessionObj(Object obj)
    {
        mDestroyed = false;
        mExtraControllerCallbacks = new RemoteCallbackList();
        mSessionObj = MediaSessionCompatApi21.verifySession(obj);
        mToken = new mToken(MediaSessionCompatApi21.getSessionToken(mSessionObj));
    }
}
