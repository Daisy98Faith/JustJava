// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi21

static class mRootHints
    implements mRootHints, lbackImpl, nectionCallbackInternal
{

    protected final Object mBrowserObj;
    protected Messenger mCallbacksMessenger;
    protected final mCallbacksMessenger mHandler = new (this);
    protected final Bundle mRootHints;
    protected lbackImpl mServiceBinderWrapper;
    private final ArrayMap mSubscriptions = new ArrayMap();

    public void connect()
    {
        MediaBrowserCompatApi21.connect(mBrowserObj);
    }

    public void disconnect()
    {
        if (mServiceBinderWrapper != null && mCallbacksMessenger != null)
        {
            try
            {
                mServiceBinderWrapper.nregisterCallbackMessenger(mCallbacksMessenger);
            }
            catch (RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
            }
        }
        MediaBrowserCompatApi21.disconnect(mBrowserObj);
    }

    public Bundle getExtras()
    {
        return MediaBrowserCompatApi21.getExtras(mBrowserObj);
    }

    public void getItem(final String mediaId, final mBrowserObj cb)
    {
        if (TextUtils.isEmpty(mediaId))
        {
            throw new IllegalArgumentException("mediaId is empty");
        }
        if (cb == null)
        {
            throw new IllegalArgumentException("cb is null");
        }
        if (!MediaBrowserCompatApi21.isConnected(mBrowserObj))
        {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            mHandler.mHandler(new Runnable() {

                final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
                final MediaBrowserCompat.ItemCallback val$cb;
                final String val$mediaId;

                public void run()
                {
                    cb.onError(mediaId);
                }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                cb = itemcallback;
                mediaId = s;
                super();
            }
            });
            return;
        }
        if (mServiceBinderWrapper == null)
        {
            mHandler.mHandler(new Runnable() {

                final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
                final MediaBrowserCompat.ItemCallback val$cb;
                final String val$mediaId;

                public void run()
                {
                    cb.onError(mediaId);
                }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                cb = itemcallback;
                mediaId = s;
                super();
            }
            });
            return;
        }
        mBrowserObj mbrowserobj = new _cls2.val.mediaId(mediaId, cb, mHandler);
        try
        {
            mServiceBinderWrapper.etMediaItem(mediaId, mbrowserobj, mCallbacksMessenger);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error getting media item: ").append(mediaId).toString());
        }
        mHandler.mHandler(new Runnable() {

            final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
            final MediaBrowserCompat.ItemCallback val$cb;
            final String val$mediaId;

            public void run()
            {
                cb.onError(mediaId);
            }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                cb = itemcallback;
                mediaId = s;
                super();
            }
        });
    }

    public String getRoot()
    {
        return MediaBrowserCompatApi21.getRoot(mBrowserObj);
    }

    public ComponentName getServiceComponent()
    {
        return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
    }

    public android.support.v4.media.session.Obj getSessionToken()
    {
        return android.support.v4.media.session.Obj(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
    }

    public boolean isConnected()
    {
        return MediaBrowserCompatApi21.isConnected(mBrowserObj);
    }

    public void onConnected()
    {
        Object obj = MediaBrowserCompatApi21.getExtras(mBrowserObj);
        if (obj != null)
        {
            if ((obj = BundleCompat.getBinder(((Bundle) (obj)), "extra_messenger")) != null)
            {
                mServiceBinderWrapper = new init>(((android.os.IBinder) (obj)), mRootHints);
                mCallbacksMessenger = new Messenger(mHandler);
                mHandler.lbacksMessenger(mCallbacksMessenger);
                try
                {
                    mServiceBinderWrapper.egisterCallbackMessenger(mCallbacksMessenger);
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
                return;
            }
        }
    }

    public void onConnectionFailed()
    {
    }

    public void onConnectionFailed(Messenger messenger)
    {
    }

    public void onConnectionSuspended()
    {
        mServiceBinderWrapper = null;
        mCallbacksMessenger = null;
        mHandler.lbacksMessenger(null);
    }

    public void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle)
    {
        if (mCallbacksMessenger == messenger) goto _L2; else goto _L1
_L1:
        return;
_L2:
        messenger = (mCallbacksMessenger)mSubscriptions.get(s);
        if (messenger != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (MediaBrowserCompat.DEBUG)
        {
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("onLoadChildren for id that isn't subscribed id=").append(s).toString());
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        messenger = messenger.ck(bundle);
        if (messenger != null)
        {
            if (bundle == null)
            {
                if (list == null)
                {
                    messenger.nError(s);
                    return;
                } else
                {
                    messenger.nChildrenLoaded(s, list);
                    return;
                }
            }
            if (list == null)
            {
                messenger.nError(s, bundle);
                return;
            } else
            {
                messenger.nChildrenLoaded(s, list, bundle);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.nLoaded nloaded, Bundle bundle)
    {
    }

    public void search(final String query, final Bundle extras, final nChildrenLoaded callback)
    {
        if (!isConnected())
        {
            Log.i("MediaBrowserCompat", "Not connected, unable to search.");
            mHandler.mHandler(new Runnable() {

                final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
                final MediaBrowserCompat.SearchCallback val$callback;
                final Bundle val$extras;
                final String val$query;

                public void run()
                {
                    callback.onError(query, extras);
                }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                callback = searchcallback;
                query = s;
                extras = bundle;
                super();
            }
            });
            return;
        }
        if (mServiceBinderWrapper == null)
        {
            Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
            mHandler.mHandler(new Runnable() {

                final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
                final MediaBrowserCompat.SearchCallback val$callback;
                final Bundle val$extras;
                final String val$query;

                public void run()
                {
                    callback.onError(query, extras);
                }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                callback = searchcallback;
                query = s;
                extras = bundle;
                super();
            }
            });
            return;
        }
        nChildrenLoaded nchildrenloaded = new init>(query, extras, callback, mHandler);
        try
        {
            mServiceBinderWrapper.earch(query, extras, nchildrenloaded, mCallbacksMessenger);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error searching items with query: ").append(query).toString(), remoteexception);
        }
        mHandler.mHandler(new Runnable() {

            final MediaBrowserCompat.MediaBrowserImplApi21 this$0;
            final MediaBrowserCompat.SearchCallback val$callback;
            final Bundle val$extras;
            final String val$query;

            public void run()
            {
                callback.onError(query, extras);
            }

            
            {
                this$0 = MediaBrowserCompat.MediaBrowserImplApi21.this;
                callback = searchcallback;
                query = s;
                extras = bundle;
                super();
            }
        });
    }

    public void subscribe(String s, Bundle bundle, _cls6.val.extras extras)
    {
        _cls6.val.extras extras2 = (_cls6.val.extras)mSubscriptions.get(s);
        _cls6.val.extras extras1 = extras2;
        if (extras2 == null)
        {
            extras1 = new mSubscriptions();
            mSubscriptions.put(s, extras1);
        }
        ccess._mth100(extras, extras1);
        if (bundle == null)
        {
            bundle = null;
        } else
        {
            bundle = new Bundle(bundle);
        }
        extras1.ck(bundle, extras);
        if (mServiceBinderWrapper == null)
        {
            MediaBrowserCompatApi21.subscribe(mBrowserObj, s, ccess._mth200(extras));
            return;
        }
        try
        {
            mServiceBinderWrapper.ddSubscription(s, ccess._mth000(extras), bundle, mCallbacksMessenger);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Bundle bundle)
        {
            Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error subscribing media item: ").append(s).toString());
        }
    }

    public void unsubscribe(String s, mCallbacksMessenger mcallbacksmessenger)
    {
        mCallbacksMessenger mcallbacksmessenger1 = (mCallbacksMessenger)mSubscriptions.get(s);
        if (mcallbacksmessenger1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mServiceBinderWrapper != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mcallbacksmessenger == null)
        {
            MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
        } else
        {
            List list = mcallbacksmessenger1.cks();
            List list2 = mcallbacksmessenger1.sList();
            for (int i = list.size() - 1; i >= 0; i--)
            {
                if (list.get(i) == mcallbacksmessenger)
                {
                    list.remove(i);
                    list2.remove(i);
                }
            }

            if (list.size() == 0)
            {
                MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
            }
        }
_L4:
        if (mcallbacksmessenger1.mBrowserObj() || mcallbacksmessenger == null)
        {
            mSubscriptions.remove(s);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
label0:
        {
            if (mcallbacksmessenger != null)
            {
                break label0;
            }
            try
            {
                mServiceBinderWrapper.emoveSubscription(s, null, mCallbacksMessenger);
            }
            catch (RemoteException remoteexception)
            {
                Log.d("MediaBrowserCompat", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
            }
        }
          goto _L4
        List list1;
        List list3;
        int j;
        list1 = mcallbacksmessenger1.cks();
        list3 = mcallbacksmessenger1.sList();
        j = list1.size() - 1;
_L6:
        if (j < 0) goto _L4; else goto _L5
_L5:
        if (list1.get(j) == mcallbacksmessenger)
        {
            mServiceBinderWrapper.emoveSubscription(s, ccess._mth000(mcallbacksmessenger), mCallbacksMessenger);
            list1.remove(j);
            list3.remove(j);
        }
        j--;
          goto _L6
    }

    public nectionCallbackInternal(Context context, ComponentName componentname, _cls6.val.extras extras, Bundle bundle)
    {
        if (android.os.wserImplApi21 <= 25)
        {
            Bundle bundle1 = bundle;
            if (bundle == null)
            {
                bundle1 = new Bundle();
            }
            bundle1.putInt("extra_client_version", 1);
            mRootHints = new Bundle(bundle1);
        } else
        {
            if (bundle == null)
            {
                bundle = null;
            } else
            {
                bundle = new Bundle(bundle);
            }
            mRootHints = bundle;
        }
        extras.InternalConnectionCallback(this);
        mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentname, extras.nnectionCallbackObj, mRootHints);
    }
}
