// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private static class mCallback extends ResultReceiver
{

    private final mExtras mCallback;
    private final Bundle mExtras;
    private final String mQuery;

    protected void onReceiveResult(int i, Bundle bundle)
    {
        if (i != 0 || bundle == null || !bundle.containsKey("search_results"))
        {
            mCallback.r(mQuery, mExtras);
            return;
        }
        android.os.Parcelable aparcelable[] = bundle.getParcelableArray("search_results");
        bundle = null;
        if (aparcelable != null)
        {
            ArrayList arraylist = new ArrayList();
            int j = aparcelable.length;
            i = 0;
            do
            {
                bundle = arraylist;
                if (i >= j)
                {
                    break;
                }
                arraylist.add((mExtras)aparcelable[i]);
                i++;
            } while (true);
        }
        mCallback.chResult(mQuery, mExtras, bundle);
    }

    (String s, Bundle bundle,  , Handler handler)
    {
        super(handler);
        mQuery = s;
        mExtras = bundle;
        mCallback = ;
    }
}
