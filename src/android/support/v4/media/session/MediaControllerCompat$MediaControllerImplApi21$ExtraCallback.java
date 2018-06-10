// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat, PlaybackStateCompat, ParcelableVolumeInfo

private class mCallback extends mCallback
{

    private _cls4.val.enabled mCallback;
    final _cls4.val.enabled this$0;

    public void onEvent(final String event, final Bundle extras)
        throws RemoteException
    {
        mCallback.mCallback.mCallback(new Runnable() {

            final MediaControllerCompat.MediaControllerImplApi21.ExtraCallback this$1;
            final String val$event;
            final Bundle val$extras;

            public void run()
            {
                mCallback.onSessionEvent(event, extras);
            }

            
            {
                this$1 = MediaControllerCompat.MediaControllerImplApi21.ExtraCallback.this;
                event = s;
                extras = bundle;
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

    public void onPlaybackStateChanged(final PlaybackStateCompat state)
        throws RemoteException
    {
        mCallback.mCallback.mCallback(new Runnable() {

            final MediaControllerCompat.MediaControllerImplApi21.ExtraCallback this$1;
            final PlaybackStateCompat val$state;

            public void run()
            {
                mCallback.onPlaybackStateChanged(state);
            }

            
            {
                this$1 = MediaControllerCompat.MediaControllerImplApi21.ExtraCallback.this;
                state = playbackstatecompat;
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

    public void onRepeatModeChanged(final int repeatMode)
        throws RemoteException
    {
        mCallback.mCallback.mCallback(new Runnable() {

            final MediaControllerCompat.MediaControllerImplApi21.ExtraCallback this$1;
            final int val$repeatMode;

            public void run()
            {
                mCallback.onRepeatModeChanged(repeatMode);
            }

            
            {
                this$1 = MediaControllerCompat.MediaControllerImplApi21.ExtraCallback.this;
                repeatMode = i;
                super();
            }
        });
    }

    public void onSessionDestroyed()
        throws RemoteException
    {
        throw new AssertionError();
    }

    public void onShuffleModeChanged(final boolean enabled)
        throws RemoteException
    {
        mCallback.mCallback.mCallback(new Runnable() {

            final MediaControllerCompat.MediaControllerImplApi21.ExtraCallback this$1;
            final boolean val$enabled;

            public void run()
            {
                mCallback.onShuffleModeChanged(enabled);
            }

            
            {
                this$1 = MediaControllerCompat.MediaControllerImplApi21.ExtraCallback.this;
                enabled = flag;
                super();
            }
        });
    }

    public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        throws RemoteException
    {
        throw new AssertionError();
    }


    _cls4.val.enabled(_cls4.val.enabled enabled1)
    {
        this$0 = this._cls0.this;
        super();
        mCallback = enabled1;
    }
}
