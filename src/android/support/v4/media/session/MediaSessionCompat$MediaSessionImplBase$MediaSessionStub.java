// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, ParcelableVolumeInfo, IMediaControllerCallback, PlaybackStateCompat

class this._cls0 extends this._cls0
{

    final cks this$0;

    public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
        _mth0(25, mediadescriptioncompat);
    }

    public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
    {
        _mth0(26, mediadescriptioncompat, i);
    }

    public void adjustVolume(int i, int j, String s)
    {
        _mth0(i, j);
    }

    public void fastForward()
        throws RemoteException
    {
        _mth0(16);
    }

    public Bundle getExtras()
    {
        Bundle bundle;
        synchronized (_fld0)
        {
            bundle = _fld0;
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
        synchronized (_fld0)
        {
            l = _fld0;
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
        synchronized (_fld0)
        {
            pendingintent = _fld0;
        }
        return pendingintent;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public MediaMetadataCompat getMetadata()
    {
        return _fld0;
    }

    public String getPackageName()
    {
        return _fld0;
    }

    public PlaybackStateCompat getPlaybackState()
    {
        return edPosition();
    }

    public List getQueue()
    {
        List list;
        synchronized (edPosition)
        {
            list = edPosition;
        }
        return list;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public CharSequence getQueueTitle()
    {
        return edPosition;
    }

    public int getRatingType()
    {
        return edPosition;
    }

    public int getRepeatMode()
    {
        return edPosition;
    }

    public String getTag()
    {
        return edPosition;
    }

    public ParcelableVolumeInfo getVolumeAttributes()
    {
        Object obj = edPosition;
        obj;
        JVM INSTR monitorenter ;
        VolumeProviderCompat volumeprovidercompat;
        int l;
        int i1;
        l = edPosition;
        i1 = edPosition;
        volumeprovidercompat = edPosition;
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
        j = edPosition.getStreamMaxVolume(i1);
        k = edPosition.getStreamVolume(i1);
          goto _L3
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isShuffleModeEnabled()
    {
        return ed;
    }

    public boolean isTransportControlEnabled()
    {
        return (ed & 2) != 0;
    }

    public void next()
        throws RemoteException
    {
        ed(14);
    }

    public void pause()
        throws RemoteException
    {
        ed(12);
    }

    public void play()
        throws RemoteException
    {
        ed(7);
    }

    public void playFromMediaId(String s, Bundle bundle)
        throws RemoteException
    {
        ed(8, s, bundle);
    }

    public void playFromSearch(String s, Bundle bundle)
        throws RemoteException
    {
        ed(9, s, bundle);
    }

    public void playFromUri(Uri uri, Bundle bundle)
        throws RemoteException
    {
        ed(10, uri, bundle);
    }

    public void prepare()
        throws RemoteException
    {
        ed(3);
    }

    public void prepareFromMediaId(String s, Bundle bundle)
        throws RemoteException
    {
        ed(4, s, bundle);
    }

    public void prepareFromSearch(String s, Bundle bundle)
        throws RemoteException
    {
        ed(5, s, bundle);
    }

    public void prepareFromUri(Uri uri, Bundle bundle)
        throws RemoteException
    {
        ed(6, uri, bundle);
    }

    public void previous()
        throws RemoteException
    {
        ed(15);
    }

    public void rate(RatingCompat ratingcompat)
        throws RemoteException
    {
        ed(19, ratingcompat);
    }

    public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
    {
        if (ed)
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
            cks.register(imediacontrollercallback);
            return;
        }
    }

    public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
        cks(27, mediadescriptioncompat);
    }

    public void removeQueueItemAt(int i)
    {
        cks(28, i);
    }

    public void rewind()
        throws RemoteException
    {
        cks(17);
    }

    public void seekTo(long l)
        throws RemoteException
    {
        cks(18, Long.valueOf(l));
    }

    public void sendCommand(String s, Bundle bundle, cks cks)
    {
        cks.this.cks(1, new cks(s, bundle, cks(cks)));
    }

    public void sendCustomAction(String s, Bundle bundle)
        throws RemoteException
    {
        cks(20, s, bundle);
    }

    public boolean sendMediaButton(KeyEvent keyevent)
    {
        boolean flag;
        if ((cks & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            cks(21, keyevent);
        }
        return flag;
    }

    public void setRepeatMode(int i)
        throws RemoteException
    {
        cks(23, i);
    }

    public void setShuffleModeEnabled(boolean flag)
        throws RemoteException
    {
        cks(24, Boolean.valueOf(flag));
    }

    public void setVolumeTo(int i, int j, String s)
    {
        cks(i, j);
    }

    public void skipToQueueItem(long l)
    {
        cks(11, Long.valueOf(l));
    }

    public void stop()
        throws RemoteException
    {
        cks(13);
    }

    public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
    {
        cks.unregister(imediacontrollercallback);
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
