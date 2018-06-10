// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, PlaybackStateCompat, ParcelableVolumeInfo, IMediaControllerCallback

class this._cls0 extends this._cls0
{

    final this._cls0 this$0;

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
        return this._mth0(this._cls0.this);
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
        return _fld0;
    }

    public int getRepeatMode()
    {
        return _fld0;
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
        return nabled;
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
        if (!nabled(this._cls0.this))
        {
            this._mth0(this._cls0.this).register(imediacontrollercallback);
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

    public void sendCommand(String s, Bundle bundle, this._cls0 _pcls0)
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
        this._mth0(this._cls0.this).unregister(imediacontrollercallback);
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
