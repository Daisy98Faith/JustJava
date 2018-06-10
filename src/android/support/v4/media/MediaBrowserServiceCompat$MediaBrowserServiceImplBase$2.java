// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserCompatUtils

class val.options
    implements Runnable
{

    final val.parentId this$1;
    final Bundle val$options;
    final String val$parentId;

    public void run()
    {
        for (Iterator iterator = mConnections.keySet().iterator(); iterator.hasNext();)
        {
            Object obj = (IBinder)iterator.next();
            obj = (val.options)mConnections.get(obj);
            Object obj1 = (List)((val.options) (obj))..get(val$parentId);
            if (obj1 != null)
            {
                obj1 = ((List) (obj1)).iterator();
                while (((Iterator) (obj1)).hasNext()) 
                {
                    Pair pair = (Pair)((Iterator) (obj1)).next();
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(val$options, (Bundle)pair.second))
                    {
                        performLoadChildren(val$parentId, ((val.parentId) (obj)), (Bundle)pair.second);
                    }
                }
            }
        }

    }

    ()
    {
        this$1 = final_;
        val$parentId = s;
        val$options = Bundle.this;
        super();
    }
}
