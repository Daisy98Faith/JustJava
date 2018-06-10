// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserServiceCompatApi24, MediaBrowserServiceCompatApi21

class <init> extends <init>
    implements <init>
{

    final MediaBrowserServiceCompat this$0;

    public Bundle getBrowserRootHints()
    {
        if (mCurConnection != null)
        {
            if (mCurConnection.<init> == null)
            {
                return null;
            } else
            {
                return new Bundle(mCurConnection.<init>);
            }
        } else
        {
            return MediaBrowserServiceCompatApi24.getBrowserRootHints(mServiceObj);
        }
    }

    public void notifyChildrenChanged(String s, Bundle bundle)
    {
        if (bundle == null)
        {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, s);
            return;
        } else
        {
            MediaBrowserServiceCompatApi24.notifyChildrenChanged(mServiceObj, s, bundle);
            return;
        }
    }

    public void onCreate()
    {
        mServiceObj = MediaBrowserServiceCompatApi24.createService(MediaBrowserServiceCompat.this, this);
        MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }

    public void onLoadChildren(final String final_obj, mServiceObj mserviceobj, Bundle bundle)
    {
        mserviceobj = new MediaBrowserServiceCompat.Result(mserviceobj) {

            final MediaBrowserServiceCompat.MediaBrowserServiceImplApi24 this$1;
            final MediaBrowserServiceCompatApi24.ResultWrapper val$resultWrapper;

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
                resultWrapper.sendResult(((List) (obj)), i);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplApi24.this;
                resultWrapper = resultwrapper;
                super(final_obj);
            }
        };
        MediaBrowserServiceCompat.this.onLoadChildren(final_obj, mserviceobj, bundle);
    }

    _cls1.val.resultWrapper()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super(MediaBrowserServiceCompat.this);
    }
}
