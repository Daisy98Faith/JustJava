// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class this._cls0
    implements ServiceConnection
{

    final isCurrent this$0;

    private void postOrRun(Runnable runnable)
    {
        if (Thread.currentThread() == _fld0._mth0().getThread())
        {
            runnable.run();
            return;
        } else
        {
            _fld0._mth0(runnable);
            return;
        }
    }

    boolean isCurrent(String s)
    {
        if (_fld0 != this)
        {
            if (_fld0 != 0)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(_fld0).append(" with mServiceConnection=").append(_fld0).append(" this=").append(this).toString());
            }
            return false;
        } else
        {
            return true;
        }
    }

    public void onServiceConnected(final ComponentName name, final IBinder binder)
    {
        postOrRun(new Runnable() {

            final MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection this$1;
            final IBinder val$binder;
            final ComponentName val$name;

            public void run()
            {
                if (MediaBrowserCompat.DEBUG)
                {
                    Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceConnected name=").append(name).append(" binder=").append(binder).toString());
                    dump();
                }
                if (isCurrent("onServiceConnected"))
                {
                    mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, mRootHints);
                    mCallbacksMessenger = new Messenger(mHandler);
                    mHandler.setCallbacksMessenger(mCallbacksMessenger);
                    mState = 1;
                    try
                    {
                        if (MediaBrowserCompat.DEBUG)
                        {
                            Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                            dump();
                        }
                        mServiceBinderWrapper.connect(mContext, mCallbacksMessenger);
                        return;
                    }
                    catch (RemoteException remoteexception)
                    {
                        Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(mServiceComponent).toString());
                    }
                    if (MediaBrowserCompat.DEBUG)
                    {
                        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                        dump();
                        return;
                    }
                }
            }

            
            {
                this$1 = MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                name = componentname;
                binder = ibinder;
                super();
            }
        });
    }

    public void onServiceDisconnected(final ComponentName name)
    {
        postOrRun(new Runnable() {

            final MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection this$1;
            final ComponentName val$name;

            public void run()
            {
                if (MediaBrowserCompat.DEBUG)
                {
                    Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceDisconnected name=").append(name).append(" this=").append(this).append(" mServiceConnection=").append(mServiceConnection).toString());
                    dump();
                }
                if (!isCurrent("onServiceDisconnected"))
                {
                    return;
                } else
                {
                    mServiceBinderWrapper = null;
                    mCallbacksMessenger = null;
                    mHandler.setCallbacksMessenger(null);
                    mState = 3;
                    mCallback.onConnectionSuspended();
                    return;
                }
            }

            
            {
                this$1 = MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                name = componentname;
                super();
            }
        });
    }

    _cls2.val.name()
    {
        this$0 = this._cls0.this;
        super();
    }
}
