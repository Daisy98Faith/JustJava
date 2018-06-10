// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class sult extends sult
{

    final MediaBrowserServiceCompat this$0;
    final ResultReceiver val$receiver;

    void onResultSent( , int i)
    {
        if ((i & 2) != 0)
        {
            val$receiver.send(-1, null);
            return;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", );
            val$receiver.send(0, bundle);
            return;
        }
    }

    volatile void onResultSent(Object obj, int i)
    {
        onResultSent(()obj, i);
    }

    (ResultReceiver resultreceiver)
    {
        this$0 = final_mediabrowserservicecompat;
        val$receiver = resultreceiver;
        super(Object.this);
    }
}
