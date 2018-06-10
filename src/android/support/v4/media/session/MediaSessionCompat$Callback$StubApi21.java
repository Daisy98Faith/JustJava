// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat

private class this._cls0
    implements this._cls0
{

    final eueItem this$0;

    public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        if (s.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER"))
        {
            s = (_cls21)pl.get();
            if (s != null)
            {
                bundle = new Bundle();
                BundleCompat.putBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER", s.getExtraSessionBinder());
                resultreceiver.send(0, bundle);
            }
            return;
        }
        if (s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM"))
        {
            bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
            Item((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
            return;
        }
        if (s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT"))
        {
            bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
            Item((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
            return;
        }
        if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM"))
        {
            bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
            eueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
            return;
        }
        if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT"))
        {
            eueItemAt(bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
            return;
        } else
        {
            eueItemAt(s, bundle, resultreceiver);
            return;
        }
    }

    public void onCustomAction(String s, Bundle bundle)
    {
        if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
        {
            s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
            bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            Uri.this.Uri(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE"))
        {
            Uri.this.Uri();
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
        {
            s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            romMediaId(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
        {
            s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            romSearch(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
        {
            s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            romUri(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.SET_REPEAT_MODE"))
        {
            int i = bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
            tMode(i);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED"))
        {
            boolean flag = bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
            leModeEnabled(flag);
            return;
        } else
        {
            tion(s, bundle);
            return;
        }
    }

    public void onFastForward()
    {
        ard();
    }

    public boolean onMediaButtonEvent(Intent intent)
    {
        return tonEvent(intent);
    }

    public void onPause()
    {
        tonEvent();
    }

    public void onPlay()
    {
        tonEvent();
    }

    public void onPlayFromMediaId(String s, Bundle bundle)
    {
        MediaId(s, bundle);
    }

    public void onPlayFromSearch(String s, Bundle bundle)
    {
        Search(s, bundle);
    }

    public void onRewind()
    {
        Search();
    }

    public void onSeekTo(long l)
    {
        Search(l);
    }

    public void onSetRating(Object obj)
    {
        g(RatingCompat.fromRating(obj));
    }

    public void onSkipToNext()
    {
        xt();
    }

    public void onSkipToPrevious()
    {
        evious();
    }

    public void onSkipToQueueItem(long l)
    {
        eueItem(l);
    }

    public void onStop()
    {
        eueItem();
    }

    _cls21()
    {
        this$0 = this._cls0.this;
        super();
    }
}
