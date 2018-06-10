// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class sult extends sult
{

    final MediaBrowserServiceCompat this$0;
    final ResultReceiver val$receiver;

    volatile void onResultSent(Object obj, int i)
    {
        onResultSent((List)obj, i);
    }

    void onResultSent(List list, int i)
    {
        if ((i & 4) != 0 || list == null)
        {
            val$receiver.send(-1, null);
            return;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (android.os.Parcelable[])list.toArray(new [0]));
            val$receiver.send(0, bundle);
            return;
        }
    }

    (ResultReceiver resultreceiver)
    {
        this$0 = final_mediabrowserservicecompat;
        val$receiver = resultreceiver;
        super(Object.this);
    }
}
