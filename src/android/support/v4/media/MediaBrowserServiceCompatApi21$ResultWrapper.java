// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi21

static class mResultObj
{

    android.service.media.er.mResultObj mResultObj;

    public void detach()
    {
        mResultObj.mResultObj();
    }

    List parcelListToItemList(List list)
    {
        if (list != null) goto _L2; else goto _L1
_L1:
        list = null;
_L4:
        return list;
_L2:
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            list = arraylist;
            if (!iterator.hasNext())
            {
                continue;
            }
            list = (Parcel)iterator.next();
            list.setDataPosition(0);
            arraylist.add(android.media.browse.per.ResultWrapper(list));
            list.recycle();
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void sendResult(Object obj)
    {
        if (obj instanceof List)
        {
            mResultObj.mResultObj(parcelListToItemList((List)obj));
            return;
        }
        if (obj instanceof Parcel)
        {
            obj = (Parcel)obj;
            ((Parcel) (obj)).setDataPosition(0);
            mResultObj.mResultObj(android.media.browse.per.mResultObj.ult(((Parcel) (obj))));
            ((Parcel) (obj)).recycle();
            return;
        } else
        {
            mResultObj.mResultObj(null);
            return;
        }
    }

    (android.service.media.er er)
    {
        mResultObj = er;
    }
}
