// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;


// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class this._cls0
    implements this._cls0
{

    final onSuspended this$0;

    public void onConnected()
    {
        if (nCallbackInternal != null)
        {
            nCallbackInternal.onConnected();
        }
        d();
    }

    public void onConnectionFailed()
    {
        if (nCallbackInternal != null)
        {
            nCallbackInternal.onConnectionFailed();
        }
        onFailed();
    }

    public void onConnectionSuspended()
    {
        if (nCallbackInternal != null)
        {
            nCallbackInternal.onConnectionSuspended();
        }
        onSuspended();
    }

    CallbackInternal()
    {
        this$0 = this._cls0.this;
        super();
    }
}
