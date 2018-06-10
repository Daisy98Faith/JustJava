// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

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
    final String val$mediaId;
    final ResultReceiver val$receiver;

    public void run()
    {
        Object obj = val$callbacks.inder();
        obj = (inder)mConnections.get(obj);
        if (obj == null)
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("getMediaItem for callback that isn't registered id=").append(val$mediaId).toString());
            return;
        } else
        {
            performLoadItem(val$mediaId, ((val.mediaId) (obj)), val$receiver);
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$mediaId = s;
        val$receiver = ResultReceiver.this;
        super();
    }
}
