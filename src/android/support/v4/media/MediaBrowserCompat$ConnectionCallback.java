// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;


// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi21

public static class mConnectionCallbackObj
{
    static interface ConnectionCallbackInternal
    {

        public abstract void onConnected();

        public abstract void onConnectionFailed();

        public abstract void onConnectionSuspended();
    }

    private class StubApi21
        implements MediaBrowserCompatApi21.ConnectionCallback
    {

        final MediaBrowserCompat.ConnectionCallback this$0;

        public void onConnected()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnected();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnected();
        }

        public void onConnectionFailed()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnectionFailed();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
        }

        public void onConnectionSuspended()
        {
            if (mConnectionCallbackInternal != null)
            {
                mConnectionCallbackInternal.onConnectionSuspended();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
        }

        StubApi21()
        {
            this$0 = MediaBrowserCompat.ConnectionCallback.this;
            super();
        }
    }


    ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;

    public void onConnected()
    {
    }

    public void onConnectionFailed()
    {
    }

    public void onConnectionSuspended()
    {
    }

    void setInternalConnectionCallback(ConnectionCallbackInternal connectioncallbackinternal)
    {
        mConnectionCallbackInternal = connectioncallbackinternal;
    }

    public back()
    {
        if (android.os.ctionCallback >= 21)
        {
            mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
            return;
        } else
        {
            mConnectionCallbackObj = null;
            return;
        }
    }
}
