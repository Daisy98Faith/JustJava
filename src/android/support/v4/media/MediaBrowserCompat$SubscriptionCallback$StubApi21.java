// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class this._cls0
    implements this._cls0
{

    final applyOptions this$0;

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
        if (ionRef == null)
        {
            obj = null;
        } else
        {
            obj = (ionRef)ionRef.get();
        }
        if (obj == null)
        {
            Loaded(s, Loaded(list));
        } else
        {
            list = Loaded(list);
            List list1 = ((Loaded) (obj)).Loaded();
            obj = ((Loaded) (obj)).Loaded();
            int i = 0;
            while (i < list1.size()) 
            {
                Bundle bundle = (Bundle)((List) (obj)).get(i);
                if (bundle == null)
                {
                    Loaded(s, list);
                } else
                {
                    Loaded(s, applyOptions(list, bundle), bundle);
                }
                i++;
            }
        }
    }

    public void onError(String s)
    {
        applyOptions.this.applyOptions(s);
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
