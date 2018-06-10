// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.BuildCompat;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi24, MediaBrowserCompatApi21

public static abstract class mToken
{
    private class StubApi21
        implements MediaBrowserCompatApi21.SubscriptionCallback
    {

        final MediaBrowserCompat.SubscriptionCallback this$0;

        List applyOptions(List list, Bundle bundle)
        {
            if (list != null) goto _L2; else goto _L1
_L1:
            bundle = null;
_L4:
            return bundle;
_L2:
            int i;
            int l;
            i = bundle.getInt("android.media.browse.extra.PAGE", -1);
            l = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
            if (i != -1)
            {
                break; /* Loop/switch isn't completed */
            }
            bundle = list;
            if (l == -1) goto _L4; else goto _L3
_L3:
            int k = l * i;
            int j = k + l;
            if (i < 0 || l < 1 || k >= list.size())
            {
                return Collections.EMPTY_LIST;
            }
            i = j;
            if (j > list.size())
            {
                i = list.size();
            }
            return list.subList(k, i);
        }

        public void onChildrenLoaded(String s, List list)
        {
            Object obj;
            if (mSubscriptionRef == null)
            {
                obj = null;
            } else
            {
                obj = (MediaBrowserCompat.Subscription)mSubscriptionRef.get();
            }
            if (obj == null)
            {
                MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, MediaBrowserCompat.MediaItem.fromMediaItemList(list));
            } else
            {
                list = MediaBrowserCompat.MediaItem.fromMediaItemList(list);
                List list1 = ((MediaBrowserCompat.Subscription) (obj)).getCallbacks();
                obj = ((MediaBrowserCompat.Subscription) (obj)).getOptionsList();
                int i = 0;
                while (i < list1.size()) 
                {
                    Bundle bundle = (Bundle)((List) (obj)).get(i);
                    if (bundle == null)
                    {
                        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, list);
                    } else
                    {
                        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, applyOptions(list, bundle), bundle);
                    }
                    i++;
                }
            }
        }

        public void onError(String s)
        {
            MediaBrowserCompat.SubscriptionCallback.this.onError(s);
        }

        StubApi21()
        {
            this$0 = MediaBrowserCompat.SubscriptionCallback.this;
            super();
        }
    }

    private class StubApi24 extends StubApi21
        implements MediaBrowserCompatApi24.SubscriptionCallback
    {

        final MediaBrowserCompat.SubscriptionCallback this$0;

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
            MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, MediaBrowserCompat.MediaItem.fromMediaItemList(list), bundle);
        }

        public void onError(String s, Bundle bundle)
        {
            MediaBrowserCompat.SubscriptionCallback.this.onError(s, bundle);
        }

        StubApi24()
        {
            this$0 = MediaBrowserCompat.SubscriptionCallback.this;
            super();
        }
    }


    private final Object mSubscriptionCallbackObj;
    WeakReference mSubscriptionRef;
    private final IBinder mToken;

    private void setSubscription(onError onerror)
    {
        mSubscriptionRef = new WeakReference(onerror);
    }

    public void onChildrenLoaded(String s, List list)
    {
    }

    public void onChildrenLoaded(String s, List list, Bundle bundle)
    {
    }

    public void onError(String s)
    {
    }

    public void onError(String s, Bundle bundle)
    {
    }




    public back()
    {
        if (android.os.ptionCallback >= 26 || BuildCompat.isAtLeastO())
        {
            mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24());
            mToken = null;
            return;
        }
        if (android.os.ptionCallback.mToken >= 21)
        {
            mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
            mToken = new Binder();
            return;
        } else
        {
            mSubscriptionCallbackObj = null;
            mToken = new Binder();
            return;
        }
    }
}
