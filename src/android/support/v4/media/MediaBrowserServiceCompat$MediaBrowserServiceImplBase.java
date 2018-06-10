// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserCompatUtils

class this._cls0
    implements this._cls0
{

    private Messenger mMessenger;
    final MediaBrowserServiceCompat this$0;

    public Bundle getBrowserRootHints()
    {
        if (mCurConnection == null)
        {
            throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
        }
        if (mCurConnection._fld0 == null)
        {
            return null;
        } else
        {
            return new Bundle(mCurConnection._fld0);
        }
    }

    public void notifyChildrenChanged(final String parentId, final Bundle options)
    {
        mHandler._mth0(new Runnable() {

            final MediaBrowserServiceCompat.MediaBrowserServiceImplBase this$1;
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
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplBase.this;
                parentId = s;
                options = bundle;
                super();
            }
        });
    }

    public IBinder onBind(Intent intent)
    {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction()))
        {
            return mMessenger.getBinder();
        } else
        {
            return null;
        }
    }

    public void onCreate()
    {
        mMessenger = new Messenger(mHandler);
    }

    public void setSessionToken(final android.support.v4.media.session.er token)
    {
        mHandler.mMessenger(new Runnable() {

            final MediaBrowserServiceCompat.MediaBrowserServiceImplBase this$1;
            final android.support.v4.media.session.MediaSessionCompat.Token val$token;

            public void run()
            {
                for (Iterator iterator = mConnections.values().iterator(); iterator.hasNext();)
                {
                    MediaBrowserServiceCompat.ConnectionRecord connectionrecord = (MediaBrowserServiceCompat.ConnectionRecord)iterator.next();
                    try
                    {
                        connectionrecord.callbacks.onConnect(connectionrecord.root.getRootId(), token, connectionrecord.root.getExtras());
                    }
                    catch (RemoteException remoteexception)
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("Connection for ").append(connectionrecord.pkg).append(" is no longer valid.").toString());
                        iterator.remove();
                    }
                }

            }

            
            {
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplBase.this;
                token = token1;
                super();
            }
        });
    }

    _cls2.val.options()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
    }
}
