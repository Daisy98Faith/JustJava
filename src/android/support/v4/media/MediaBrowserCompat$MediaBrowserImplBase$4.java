// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

class val.extras
    implements Runnable
{

    final val.extras this$0;
    final val.extras val$callback;
    final Bundle val$extras;
    final String val$query;

    public void run()
    {
        val$callback.callback(val$query, val$extras);
    }

    ()
    {
        this$0 = final_;
        val$callback = 1;
        val$query = s;
        val$extras = Bundle.this;
        super();
    }
}
