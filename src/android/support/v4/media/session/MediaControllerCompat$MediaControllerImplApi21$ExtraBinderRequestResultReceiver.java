// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat

private static class mMediaControllerImpl extends ResultReceiver
{

    private WeakReference mMediaControllerImpl;

    protected void onReceiveResult(int i, Bundle bundle)
    {
        mMediaControllerImpl mmediacontrollerimpl = (mMediaControllerImpl)mMediaControllerImpl.get();
        if (mmediacontrollerimpl == null || bundle == null)
        {
            return;
        } else
        {
            mMediaControllerImpl(mmediacontrollerimpl, mMediaControllerImpl(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
            mMediaControllerImpl(mmediacontrollerimpl);
            return;
        }
    }

    public ( , Handler handler)
    {
        super(handler);
        mMediaControllerImpl = new WeakReference();
    }
}
