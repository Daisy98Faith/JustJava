// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

class val.binder
    implements Runnable
{

    final is._cls0 this$1;
    final IBinder val$binder;
    final ComponentName val$name;

    public void run()
    {
        if (MediaBrowserCompat.DEBUG)
        {
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceConnected name=").append(val$name).append(" binder=").append(val$binder).toString());
            val.binder.this.binder();
        }
        if (Current("onServiceConnected"))
        {
            Current = new Current(val$binder, _fld0.binder);
            _fld0 = new Messenger(_fld0);
            _fld0._mth0(_fld0);
            _fld0 = 1;
            try
            {
                if (MediaBrowserCompat.DEBUG)
                {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    _mth0();
                }
                _fld0._mth0(_fld0, _fld0);
                return;
            }
            catch (RemoteException remoteexception)
            {
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(_fld0).toString());
            }
            if (MediaBrowserCompat.DEBUG)
            {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                _mth0();
                return;
            }
        }
    }

    ()
    {
        this$1 = final_;
        val$name = componentname;
        val$binder = IBinder.this;
        super();
    }
}
