// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.receiver
    implements Runnable
{

    final val.receiver this$1;
    final val.receiver val$callbacks;
    final Bundle val$extras;
    final String val$query;
    final ResultReceiver val$receiver;

    public void run()
    {
        Object obj = val$callbacks.inder();
        obj = (inder)mConnections.get(obj);
        if (obj == null)
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("search for callback that isn't registered query=").append(val$query).toString());
            return;
        } else
        {
            performSearch(val$query, val$extras, ((val.extras) (obj)), val$receiver);
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$query = s;
        val$extras = bundle;
        val$receiver = ResultReceiver.this;
        super();
    }
}
