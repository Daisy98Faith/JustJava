// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class this._cls0
{

    final MediaBrowserServiceCompat this$0;

    public void addSubscription(final String id, final IBinder token, final Bundle options, final this._cls0 callbacks)
    {
        mHandler.tOrRun(new Runnable() {

            final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
            final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
            final String val$id;
            final Bundle val$options;
            final IBinder val$token;

            public void run()
            {
                Object obj = callbacks.asBinder();
                obj = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(obj);
                if (obj == null)
                {
                    Log.w("MBServiceCompat", (new StringBuilder()).append("addSubscription for callback that isn't registered id=").append(id).toString());
                    return;
                } else
                {
                    addSubscription(id, ((MediaBrowserServiceCompat.ConnectionRecord) (obj)), token, options);
                    return;
                }
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                id = s;
                token = ibinder;
                options = bundle;
                super();
            }
        });
    }

    public void connect(final String pkg, final int uid, final Bundle rootHints, final _cls3.val.options callbacks)
    {
        if (!isValidPackage(pkg, uid))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Package/uid mismatch: uid=").append(uid).append(" package=").append(pkg).toString());
        } else
        {
            mHandler.tOrRun(new Runnable() {

                final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
                final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
                final String val$pkg;
                final Bundle val$rootHints;
                final int val$uid;

                public void run()
                {
                    Object obj;
                    MediaBrowserServiceCompat.ConnectionRecord connectionrecord;
                    obj = callbacks.asBinder();
                    mConnections.remove(obj);
                    connectionrecord = new MediaBrowserServiceCompat.ConnectionRecord(this$0);
                    connectionrecord.pkg = pkg;
                    connectionrecord.rootHints = rootHints;
                    connectionrecord.callbacks = callbacks;
                    connectionrecord.root = onGetRoot(pkg, uid, rootHints);
                    if (connectionrecord.root != null)
                    {
                        break MISSING_BLOCK_LABEL_180;
                    }
                    Log.i("MBServiceCompat", (new StringBuilder()).append("No root for client ").append(pkg).append(" from service ").append(getClass().getName()).toString());
                    callbacks.onConnectFailed();
_L1:
                    return;
                    obj;
                    Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnectFailed() failed. Ignoring. pkg=").append(pkg).toString());
                    return;
                    try
                    {
                        mConnections.put(obj, connectionrecord);
                        if (mSession != null)
                        {
                            callbacks.onConnect(connectionrecord.root.getRootId(), mSession, connectionrecord.root.getExtras());
                            return;
                        }
                    }
                    catch (RemoteException remoteexception)
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnect() failed. Dropping client. pkg=").append(pkg).toString());
                        mConnections.remove(obj);
                        return;
                    }
                      goto _L1
                }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                pkg = s;
                rootHints = bundle;
                uid = i;
                super();
            }
            });
            return;
        }
    }

    public void disconnect(final _cls1.val.pkg callbacks)
    {
        mHandler.tOrRun(new Runnable() {

            final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
            final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;

            public void run()
            {
                IBinder ibinder = callbacks.asBinder();
                if ((MediaBrowserServiceCompat.ConnectionRecord)mConnections.remove(ibinder) == null);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                super();
            }
        });
    }

    public void getMediaItem(final String mediaId, final ResultReceiver receiver, final sBinder callbacks)
    {
        if (TextUtils.isEmpty(mediaId) || receiver == null)
        {
            return;
        } else
        {
            mHandler.tOrRun(new Runnable() {

                final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
                final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
                final String val$mediaId;
                final ResultReceiver val$receiver;

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(obj);
                    if (obj == null)
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("getMediaItem for callback that isn't registered id=").append(mediaId).toString());
                        return;
                    } else
                    {
                        performLoadItem(mediaId, ((MediaBrowserServiceCompat.ConnectionRecord) (obj)), receiver);
                        return;
                    }
                }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                mediaId = s;
                receiver = resultreceiver;
                super();
            }
            });
            return;
        }
    }

    public void registerCallbacks(final _cls5.val.receiver callbacks, final Bundle rootHints)
    {
        mHandler.tOrRun(new Runnable() {

            final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
            final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
            final Bundle val$rootHints;

            public void run()
            {
                IBinder ibinder = callbacks.asBinder();
                mConnections.remove(ibinder);
                MediaBrowserServiceCompat.ConnectionRecord connectionrecord = new MediaBrowserServiceCompat.ConnectionRecord(this$0);
                connectionrecord.callbacks = callbacks;
                connectionrecord.rootHints = rootHints;
                mConnections.put(ibinder, connectionrecord);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                rootHints = bundle;
                super();
            }
        });
    }

    public void removeSubscription(final String id, final IBinder token, final _cls6.val.rootHints callbacks)
    {
        mHandler.tOrRun(new Runnable() {

            final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
            final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
            final String val$id;
            final IBinder val$token;

            public void run()
            {
                Object obj = callbacks.asBinder();
                obj = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(obj);
                if (obj == null)
                {
                    Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription for callback that isn't registered id=").append(id).toString());
                } else
                if (!removeSubscription(id, ((MediaBrowserServiceCompat.ConnectionRecord) (obj)), token))
                {
                    Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription called for ").append(id).append(" which is not subscribed").toString());
                    return;
                }
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                id = s;
                token = ibinder;
                super();
            }
        });
    }

    public void search(final String query, final Bundle extras, final ResultReceiver receiver, final _cls4.val.id callbacks)
    {
        if (TextUtils.isEmpty(query) || receiver == null)
        {
            return;
        } else
        {
            mHandler.tOrRun(new Runnable() {

                final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
                final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;
                final Bundle val$extras;
                final String val$query;
                final ResultReceiver val$receiver;

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(obj);
                    if (obj == null)
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("search for callback that isn't registered query=").append(query).toString());
                        return;
                    } else
                    {
                        performSearch(query, extras, ((MediaBrowserServiceCompat.ConnectionRecord) (obj)), receiver);
                        return;
                    }
                }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                query = s;
                extras = bundle;
                receiver = resultreceiver;
                super();
            }
            });
            return;
        }
    }

    public void unregisterCallbacks(final _cls8.val.receiver callbacks)
    {
        mHandler.tOrRun(new Runnable() {

            final MediaBrowserServiceCompat.ServiceBinderImpl this$1;
            final MediaBrowserServiceCompat.ServiceCallbacks val$callbacks;

            public void run()
            {
                IBinder ibinder = callbacks.asBinder();
                mConnections.remove(ibinder);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.ServiceBinderImpl.this;
                callbacks = servicecallbacks;
                super();
            }
        });
    }

    _cls8.val.receiver()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
    }
}
