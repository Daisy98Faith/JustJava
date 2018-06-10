// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi24

static class mResultObj
{

    android.service.media.er.parcelListToItemList mResultObj;

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

    public void sendResult(List list, int i)
    {
        try
        {
            MediaBrowserServiceCompatApi24.access$000().setInt(mResultObj, i);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            Log.w("MBSCompatApi24", illegalaccessexception);
        }
        mResultObj.mResultObj(parcelListToItemList(list));
    }

    (android.service.media.er er)
    {
        mResultObj = er;
    }
}
