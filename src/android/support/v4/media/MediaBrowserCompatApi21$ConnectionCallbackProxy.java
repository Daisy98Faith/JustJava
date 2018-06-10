// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;


// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatApi21

static class mConnectionCallback extends android.media.browse.oxy
{

    protected final nectionSuspended mConnectionCallback;

    public void onConnected()
    {
        mConnectionCallback.nected();
    }

    public void onConnectionFailed()
    {
        mConnectionCallback.nectionFailed();
    }

    public void onConnectionSuspended()
    {
        mConnectionCallback.nectionSuspended();
    }

    public ( )
    {
        mConnectionCallback = ;
    }
}
