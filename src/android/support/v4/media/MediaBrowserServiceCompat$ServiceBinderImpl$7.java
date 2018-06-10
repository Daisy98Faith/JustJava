// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.support.v4.util.ArrayMap;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.callbacks
    implements Runnable
{

    final inder this$1;
    final inder val$callbacks;

    public void run()
    {
        android.os.IBinder ibinder = val$callbacks.inder();
        mConnections.remove(ibinder);
    }

    ()
    {
        this$1 = final_;
        val$callbacks = val.callbacks.this;
        super();
    }
}
