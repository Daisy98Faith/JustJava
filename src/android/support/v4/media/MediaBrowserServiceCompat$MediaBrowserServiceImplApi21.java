// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserServiceCompatApi21, MediaBrowserCompatUtils

class this._cls0
    implements this._cls0, this._cls0
{

    Messenger mMessenger;
    Object mServiceObj;
    final MediaBrowserServiceCompat this$0;

    public Bundle getBrowserRootHints()
    {
        if (mMessenger != null)
        {
            if (mCurConnection == null)
            {
                throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
            }
            if (mCurConnection.mMessenger != null)
            {
                return new Bundle(mCurConnection.mMessenger);
            }
        }
        return null;
    }

    public void notifyChildrenChanged(final String parentId, final Bundle options)
    {
        if (mMessenger == null)
        {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, parentId);
            return;
        } else
        {
            mHandler.mServiceObj(new Runnable() {

                final MediaBrowserServiceCompat.MediaBrowserServiceImplApi21 this$1;
                final Bundle val$options;
                final String val$parentId;

                public void run()
                {
                    for (Iterator iterator = mConnections.keySet().iterator(); iterator.hasNext();)
                    {
                        Object obj = (IBinder)iterator.next();
                        obj = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(obj);
                        Object obj1 = (List)((MediaBrowserServiceCompat.ConnectionRecord) (obj)).subscriptions.get(parentId);
                        if (obj1 != null)
                        {
                            obj1 = ((List) (obj1)).iterator();
                            while (((Iterator) (obj1)).hasNext()) 
                            {
                                Pair pair = (Pair)((Iterator) (obj1)).next();
                                if (MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle)pair.second))
                                {
                                    performLoadChildren(parentId, ((MediaBrowserServiceCompat.ConnectionRecord) (obj)), (Bundle)pair.second);
                                }
                            }
                        }
                    }

                }

            
            {
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this;
                parentId = s;
                options = bundle;
                super();
            }
            });
            return;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return MediaBrowserServiceCompatApi21.onBind(mServiceObj, intent);
    }

    public void onCreate()
    {
        mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
        MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }

    public mServiceObj onGetRoot(String s, int i, Bundle bundle)
    {
        Bundle bundle1;
        Object obj = null;
        bundle1 = obj;
        if (bundle != null)
        {
            bundle1 = obj;
            if (bundle.getInt("extra_client_version", 0) != 0)
            {
                bundle.remove("extra_client_version");
                mMessenger = new Messenger(mHandler);
                bundle1 = new Bundle();
                bundle1.putInt("extra_service_version", 1);
                BundleCompat.putBinder(bundle1, "extra_messenger", mMessenger.getBinder());
            }
        }
        bundle = MediaBrowserServiceCompat.this.onGetRoot(s, i, bundle);
        if (bundle == null)
        {
            return null;
        }
        if (bundle1 != null) goto _L2; else goto _L1
_L1:
        s = bundle.mMessenger();
_L4:
        return new mMessenger(bundle.mMessenger(), s);
_L2:
        s = bundle1;
        if (bundle.mMessenger() != null)
        {
            bundle1.putAll(bundle.mMessenger());
            s = bundle1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onLoadChildren(final String final_obj, mMessenger mmessenger)
    {
        mmessenger = new MediaBrowserServiceCompat.Result(mmessenger) {

            final MediaBrowserServiceCompat.MediaBrowserServiceImplApi21 this$1;
            final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            public void detach()
            {
                resultWrapper.detach();
            }

            volatile void onResultSent(Object obj, int i)
            {
                onResultSent((List)obj, i);
            }

            void onResultSent(List list, int i)
            {
                Object obj = null;
                if (list != null)
                {
                    ArrayList arraylist = new ArrayList();
                    list = list.iterator();
                    do
                    {
                        obj = arraylist;
                        if (!list.hasNext())
                        {
                            break;
                        }
                        obj = (MediaBrowserCompat.MediaItem)list.next();
                        Parcel parcel = Parcel.obtain();
                        ((MediaBrowserCompat.MediaItem) (obj)).writeToParcel(parcel, 0);
                        arraylist.add(parcel);
                    } while (true);
                }
                resultWrapper.sendResult(obj);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this;
                resultWrapper = resultwrapper;
                super(final_obj);
            }
        };
        MediaBrowserServiceCompat.this.onLoadChildren(final_obj, mmessenger);
    }

    public void setSessionToken(android.support.v4.media.session.sultWrapper sultwrapper)
    {
        MediaBrowserServiceCompatApi21.setSessionToken(mServiceObj, sultwrapper.Obj());
    }

    _cls2.val.resultWrapper()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
    }
}
