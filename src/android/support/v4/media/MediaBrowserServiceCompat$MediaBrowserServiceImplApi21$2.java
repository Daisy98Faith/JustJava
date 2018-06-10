// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.resultWrapper extends val.resultWrapper
{

    final val.resultWrapper this$1;
    final val.resultWrapper val$resultWrapper;

    public void detach()
    {
        val$resultWrapper.resultWrapper();
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
                obj = (onResultSent)list.next();
                Parcel parcel = Parcel.obtain();
                ((onResultSent) (obj)).onResultSent(parcel, 0);
                arraylist.add(parcel);
            } while (true);
        }
        val$resultWrapper.resultWrapper(obj);
    }

    ( 1)
    {
        this$1 = final_;
        val$resultWrapper = 1;
        super(Object.this);
    }
}
