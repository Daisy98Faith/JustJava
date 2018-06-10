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
//            MediaSessionCompat, MediaSessionCompatApi26, MediaSessionCompatApi24, MediaSessionCompatApi23, 
//            MediaSessionCompatApi21

public static abstract class mCallbackObj
{
    private class StubApi21
        implements MediaSessionCompatApi21.Callback
    {

        final MediaSessionCompat.Callback this$0;

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            if (s.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER"))
            {
                s = (MediaSessionCompat.MediaSessionImplApi21)mSessionImpl.get();
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
                onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                return;
            }
            if (s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT"))
            {
                bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
                onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                return;
            }
            if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM"))
            {
                bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
                onRemoveQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                return;
            }
            if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT"))
            {
                onRemoveQueueItemAt(bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                return;
            } else
            {
                MediaSessionCompat.Callback.this.onCommand(s, bundle, resultreceiver);
                return;
            }
        }

        public void onCustomAction(String s, Bundle bundle)
        {
            if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPlayFromUri(s, bundle);
                return;
            }
            if (s.equals("android.support.v4.media.session.action.PREPARE"))
            {
                onPrepare();
                return;
            }
            if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromMediaId(s, bundle);
                return;
            }
            if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromSearch(s, bundle);
                return;
            }
            if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromUri(s, bundle);
                return;
            }
            if (s.equals("android.support.v4.media.session.action.SET_REPEAT_MODE"))
            {
                int i = bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                onSetRepeatMode(i);
                return;
            }
            if (s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED"))
            {
                boolean flag = bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
                onSetShuffleModeEnabled(flag);
                return;
            } else
            {
                MediaSessionCompat.Callback.this.onCustomAction(s, bundle);
                return;
            }
        }

        public void onFastForward()
        {
            MediaSessionCompat.Callback.this.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            return MediaSessionCompat.Callback.this.onMediaButtonEvent(intent);
        }

        public void onPause()
        {
            MediaSessionCompat.Callback.this.onPause();
        }

        public void onPlay()
        {
            MediaSessionCompat.Callback.this.onPlay();
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPlayFromMediaId(s, bundle);
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPlayFromSearch(s, bundle);
        }

        public void onRewind()
        {
            MediaSessionCompat.Callback.this.onRewind();
        }

        public void onSeekTo(long l)
        {
            MediaSessionCompat.Callback.this.onSeekTo(l);
        }

        public void onSetRating(Object obj)
        {
            MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(obj));
        }

        public void onSkipToNext()
        {
            MediaSessionCompat.Callback.this.onSkipToNext();
        }

        public void onSkipToPrevious()
        {
            MediaSessionCompat.Callback.this.onSkipToPrevious();
        }

        public void onSkipToQueueItem(long l)
        {
            MediaSessionCompat.Callback.this.onSkipToQueueItem(l);
        }

        public void onStop()
        {
            MediaSessionCompat.Callback.this.onStop();
        }

        StubApi21()
        {
            this$0 = MediaSessionCompat.Callback.this;
            super();
        }
    }

    private class StubApi23 extends StubApi21
        implements MediaSessionCompatApi23.Callback
    {

        final MediaSessionCompat.Callback this$0;

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPlayFromUri(uri, bundle);
        }

        StubApi23()
        {
            this$0 = MediaSessionCompat.Callback.this;
            super();
        }
    }

    private class StubApi24 extends StubApi23
        implements MediaSessionCompatApi24.Callback
    {

        final MediaSessionCompat.Callback this$0;

        public void onPrepare()
        {
            MediaSessionCompat.Callback.this.onPrepare();
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPrepareFromMediaId(s, bundle);
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPrepareFromSearch(s, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
            MediaSessionCompat.Callback.this.onPrepareFromUri(uri, bundle);
        }

        StubApi24()
        {
            this$0 = MediaSessionCompat.Callback.this;
            super();
        }
    }

    private class StubApi26 extends StubApi24
        implements MediaSessionCompatApi26.Callback
    {

        final MediaSessionCompat.Callback this$0;

        public void onSetRepeatMode(int i)
        {
            MediaSessionCompat.Callback.this.onSetRepeatMode(i);
        }

        public void onSetShuffleModeEnabled(boolean flag)
        {
            MediaSessionCompat.Callback.this.onSetShuffleModeEnabled(flag);
        }

        private StubApi26()
        {
            this$0 = MediaSessionCompat.Callback.this;
            super();
        }

        StubApi26(MediaSessionCompat._cls1 _pcls1)
        {
            this();
        }
    }


    final Object mCallbackObj;
    WeakReference mSessionImpl;

    public void onAddQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
    }

    public void onAddQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i)
    {
    }

    public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
    }

    public void onCustomAction(String s, Bundle bundle)
    {
    }

    public void onFastForward()
    {
    }

    public boolean onMediaButtonEvent(Intent intent)
    {
        return false;
    }

    public void onPause()
    {
    }

    public void onPlay()
    {
    }

    public void onPlayFromMediaId(String s, Bundle bundle)
    {
    }

    public void onPlayFromSearch(String s, Bundle bundle)
    {
    }

    public void onPlayFromUri(Uri uri, Bundle bundle)
    {
    }

    public void onPrepare()
    {
    }

    public void onPrepareFromMediaId(String s, Bundle bundle)
    {
    }

    public void onPrepareFromSearch(String s, Bundle bundle)
    {
    }

    public void onPrepareFromUri(Uri uri, Bundle bundle)
    {
    }

    public void onRemoveQueueItem(MediaDescriptionCompat mediadescriptioncompat)
    {
    }

    public void onRemoveQueueItemAt(int i)
    {
    }

    public void onRewind()
    {
    }

    public void onSeekTo(long l)
    {
    }

    public void onSetRating(RatingCompat ratingcompat)
    {
    }

    public void onSetRepeatMode(int i)
    {
    }

    public void onSetShuffleModeEnabled(boolean flag)
    {
    }

    public void onSkipToNext()
    {
    }

    public void onSkipToPrevious()
    {
    }

    public void onSkipToQueueItem(long l)
    {
    }

    public void onStop()
    {
    }

    public back()
    {
        if (android.os.essionCompat.Callback >= 26)
        {
            mCallbackObj = MediaSessionCompatApi26.createCallback(new StubApi26(null));
            return;
        }
        if (android.os.essionCompat._fld1 >= 24)
        {
            mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
            return;
        }
        if (android.os.essionCompat.Callback >= 23)
        {
            mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
            return;
        }
        if (android.os.essionCompat.Callback >= 21)
        {
            mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
            return;
        } else
        {
            mCallbackObj = null;
            return;
        }
    }
}
