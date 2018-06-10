// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.rootHints
    implements Runnable
{

    final val.rootHints this$1;
    final val.rootHints val$callbacks;
    final Bundle val$rootHints;

    public void run()
    {
        android.os.IBinder ibinder = val$callbacks.inder();
        mConnections.remove(ibinder);
        val.rootHints roothints = new it>(_fld0);
        roothints.lbacks = val$callbacks;
        roothints.tHints = val$rootHints;
        mConnections.put(ibinder, roothints);
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$rootHints = Bundle.this;
        super();
    }
}
