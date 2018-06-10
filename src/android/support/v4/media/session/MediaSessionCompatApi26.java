// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.media.session.MediaSession;

class MediaSessionCompatApi26
{
    public static interface Callback
        extends MediaSessionCompatApi24.Callback
    {

        public abstract void onSetRepeatMode(int i);

        public abstract void onSetShuffleModeEnabled(boolean flag);
    }

    static class CallbackProxy extends MediaSessionCompatApi24.CallbackProxy
    {

        public void onSetRepeatMode(int i)
        {
            ((Callback)mCallback).onSetRepeatMode(i);
        }

        public void onSetShuffleModeEnabled(boolean flag)
        {
            ((Callback)mCallback).onSetShuffleModeEnabled(flag);
        }

        CallbackProxy(Callback callback)
        {
            super(callback);
        }
    }


    MediaSessionCompatApi26()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static void setRepeatMode(Object obj, int i)
    {
        ((MediaSession)obj).setRepeatMode(i);
    }

    public static void setShuffleModeEnabled(Object obj, boolean flag)
    {
        ((MediaSession)obj).setShuffleModeEnabled(flag);
    }
}
