// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi23

public static abstract class mItemCallbackObj
{
    private class StubApi23
        implements MediaBrowserCompatApi23.ItemCallback
    {

        final MediaBrowserCompat.ItemCallback this$0;

        public void onError(String s)
        {
            MediaBrowserCompat.ItemCallback.this.onError(s);
        }

        public void onItemLoaded(Parcel parcel)
        {
            if (parcel == null)
            {
                MediaBrowserCompat.ItemCallback.this.onItemLoaded(null);
                return;
            } else
            {
                parcel.setDataPosition(0);
                MediaBrowserCompat.MediaItem mediaitem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                MediaBrowserCompat.ItemCallback.this.onItemLoaded(mediaitem);
                return;
            }
        }

        StubApi23()
        {
            this$0 = MediaBrowserCompat.ItemCallback.this;
            super();
        }
    }


    final Object mItemCallbackObj;

    public void onError(String s)
    {
    }

    public void onItemLoaded(onItemLoaded onitemloaded)
    {
    }

    public back()
    {
        if (android.os..ItemCallback >= 23)
        {
            mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
            return;
        } else
        {
            mItemCallbackObj = null;
            return;
        }
    }
}
