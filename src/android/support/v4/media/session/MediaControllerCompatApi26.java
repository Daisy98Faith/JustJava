// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.media.session.MediaController;

class MediaControllerCompatApi26
{
    public static interface Callback
        extends MediaControllerCompatApi21.Callback
    {

        public abstract void onRepeatModeChanged(int i);

        public abstract void onShuffleModeChanged(boolean flag);
    }

    static class CallbackProxy extends MediaControllerCompatApi21.CallbackProxy
    {

        public void onRepeatModeChanged(int i)
        {
            ((Callback)mCallback).onRepeatModeChanged(i);
        }

        public void onShuffleModeChanged(boolean flag)
        {
            ((Callback)mCallback).onShuffleModeChanged(flag);
        }

        CallbackProxy(Callback callback)
        {
            super(callback);
        }
    }

    public static class TransportControls extends MediaControllerCompatApi23.TransportControls
    {

        public static void setRepeatMode(Object obj, int i)
        {
            ((android.media.session.MediaController.TransportControls)obj).setRepeatMode(i);
        }

        public static void setShuffleModeEnabled(Object obj, boolean flag)
        {
            ((android.media.session.MediaController.TransportControls)obj).setShuffleModeEnabled(flag);
        }

        public TransportControls()
        {
        }
    }


    MediaControllerCompatApi26()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static int getRepeatMode(Object obj)
    {
        return ((MediaController)obj).getRepeatMode();
    }

    public static boolean isShuffleModeEnabled(Object obj)
    {
        return ((MediaController)obj).isShuffleModeEnabled();
    }
}
