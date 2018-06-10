// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

class val.resultWrapper extends val.resultWrapper
{

    final onResultSent this$1;
    final onResultSent val$resultWrapper;

    public void detach()
    {
        val$resultWrapper.resultWrapper();
    }

    void onResultSent(val.resultWrapper resultwrapper, int i)
    {
        if (resultwrapper == null)
        {
            val$resultWrapper.resultWrapper(null);
            return;
        } else
        {
            Parcel parcel = Parcel.obtain();
            resultwrapper.resultWrapper(parcel, 0);
            val$resultWrapper.resultWrapper(parcel);
            return;
        }
    }

    volatile void onResultSent(Object obj, int i)
    {
        onResultSent((onResultSent)obj, i);
    }

    ( 1)
    {
        this$1 = final_;
        val$resultWrapper = 1;
        super(Object.this);
    }
}
