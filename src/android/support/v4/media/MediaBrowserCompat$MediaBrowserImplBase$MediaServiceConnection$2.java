// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

class val.name
    implements Runnable
{

    final Current this$1;
    final ComponentName val$name;

    public void run()
    {
        if (MediaBrowserCompat.DEBUG)
        {
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceDisconnected name=").append(val$name).append(" this=").append(this).append(" mServiceConnection=").append(_fld0.name).toString());
            _mth0();
        }
        if (!Current("onServiceDisconnected"))
        {
            return;
        } else
        {
            Current = null;
            Current = null;
            Current.Current(null);
            Current = 3;
            Current.Current();
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$name = ComponentName.this;
        super();
    }
}
