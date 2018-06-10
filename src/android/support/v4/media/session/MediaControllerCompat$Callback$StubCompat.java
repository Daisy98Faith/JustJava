// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat, ParcelableVolumeInfo, PlaybackStateCompat

private class this._cls0 extends this._cls0
{

    final ler.post this$0;

    public void onEvent(String s, Bundle bundle)
        throws RemoteException
    {
        _fld0.post(1, s, bundle);
    }

    public void onExtrasChanged(Bundle bundle)
        throws RemoteException
    {
        post.post(7, bundle, null);
    }

    public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
        throws RemoteException
    {
        post.post(3, mediametadatacompat, null);
    }

    public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
        throws RemoteException
    {
        post.post(2, playbackstatecompat, null);
    }

    public void onQueueChanged(List list)
        throws RemoteException
    {
        post.post(5, list, null);
    }

    public void onQueueTitleChanged(CharSequence charsequence)
        throws RemoteException
    {
        post.post(6, charsequence, null);
    }

    public void onRepeatModeChanged(int i)
        throws RemoteException
    {
        post.post(9, Integer.valueOf(i), null);
    }

    public void onSessionDestroyed()
        throws RemoteException
    {
        post.post(8, null, null);
    }

    public void onShuffleModeChanged(boolean flag)
        throws RemoteException
    {
        post.post(10, Boolean.valueOf(flag), null);
    }

    public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        throws RemoteException
    {
        ler.post post = null;
        if (parcelablevolumeinfo != null)
        {
            post = new ler.post(parcelablevolumeinfo.volumeType, parcelablevolumeinfo.audioStream, parcelablevolumeinfo.controlType, parcelablevolumeinfo.maxVolume, parcelablevolumeinfo.currentVolume);
        }
        this._cls0.this.post.post(4, post, null);
    }

    ler()
    {
        this$0 = this._cls0.this;
        super();
    }
}
