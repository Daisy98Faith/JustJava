// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatApi23

static class mItemCallback extends android.media.browse.oxy
{

    protected final mLoaded mItemCallback;

    public void onError(String s)
    {
        mItemCallback.or(s);
    }

    public void onItemLoaded(android.media.browse.oxy oxy)
    {
        if (oxy == null)
        {
            mItemCallback.mLoaded(null);
            return;
        } else
        {
            Parcel parcel = Parcel.obtain();
            oxy.nItemLoaded(parcel, 0);
            mItemCallback.mLoaded(parcel);
            return;
        }
    }

    public ( )
    {
        mItemCallback = ;
    }
}
