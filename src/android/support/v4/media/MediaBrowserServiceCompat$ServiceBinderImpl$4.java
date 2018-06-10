// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.token
    implements Runnable
{

    final val.id this$1;
    final val.id val$callbacks;
    final String val$id;
    final IBinder val$token;

    public void run()
    {
        Object obj = val$callbacks.inder();
        obj = (inder)mConnections.get(obj);
        if (obj == null)
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription for callback that isn't registered id=").append(val$id).toString());
        } else
        if (!removeSubscription(val$id, ((val.id) (obj)), val$token))
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription called for ").append(val$id).append(" which is not subscribed").toString());
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$id = s;
        val$token = IBinder.this;
        super();
    }
}
