// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.uid
    implements Runnable
{

    final val.pkg this$1;
    final val.pkg val$callbacks;
    final String val$pkg;
    final Bundle val$rootHints;
    final int val$uid;

    public void run()
    {
        Object obj;
        val.uid uid1;
        obj = val$callbacks.inder();
        mConnections.remove(obj);
        uid1 = new it>(_fld0);
        uid1. = val$pkg;
        uid1.tHints = val$rootHints;
        uid1.lbacks = val$callbacks;
        uid1.t = onGetRoot(val$pkg, val$uid, val$rootHints);
        if (uid1.t != null)
        {
            break MISSING_BLOCK_LABEL_180;
        }
        Log.i("MBServiceCompat", (new StringBuilder()).append("No root for client ").append(val$pkg).append(" from service ").append(getClass().getName()).toString());
        val$callbacks.onnectFailed();
_L1:
        return;
        obj;
        Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnectFailed() failed. Ignoring. pkg=").append(val$pkg).toString());
        return;
        try
        {
            mConnections.put(obj, uid1);
            if (mSession != null)
            {
                val$callbacks.onnect(uid1.t.d(), mSession, uid1.t.s());
                return;
            }
        }
        catch (RemoteException remoteexception)
        {
            Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnect() failed. Dropping client. pkg=").append(val$pkg).toString());
            mConnections.remove(obj);
            return;
        }
          goto _L1
    }

    ()
    {
        this$1 = final_;
        val$callbacks = 1;
        val$pkg = s;
        val$rootHints = bundle;
        val$uid = I.this;
        super();
    }
}
