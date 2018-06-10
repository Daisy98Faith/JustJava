// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.options
    implements Runnable
{

    final val.options this$1;
    final val.options val$callbacks;
    final String val$id;
    final Bundle val$options;
    final IBinder val$token;

    public void run()
    {
        Object obj = val$callbacks.inder();
        obj = (inder)mConnections.get(obj);
        if (obj == null)
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("addSubscription for callback that isn't registered id=").append(val$id).toString());
            return;
        } else
        {
            addSubscription(val$id, ((val.id) (obj)), val$token, val$options);
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$id = s;
        val$token = ibinder;
        val$options = Bundle.this;
        super();
    }
}
