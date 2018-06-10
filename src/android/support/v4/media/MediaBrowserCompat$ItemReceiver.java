// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private static class mCallback extends ResultReceiver
{

    private final mMediaId mCallback;
    private final String mMediaId;

    protected void onReceiveResult(int i, Bundle bundle)
    {
        if (bundle != null)
        {
            bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
        }
        if (i != 0 || bundle == null || !bundle.containsKey("media_item"))
        {
            mCallback.onError(mMediaId);
            return;
        }
        bundle = bundle.getParcelable("media_item");
        if (bundle == null || (bundle instanceof mMediaId))
        {
            mCallback.onItemLoaded((onItemLoaded)bundle);
            return;
        } else
        {
            mCallback.onError(mMediaId);
            return;
        }
    }

    (String s,  , Handler handler)
    {
        super(handler);
        mMediaId = s;
        mCallback = ;
    }
}
