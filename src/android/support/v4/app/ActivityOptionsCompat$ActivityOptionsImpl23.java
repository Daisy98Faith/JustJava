// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            ActivityOptionsCompat, ActivityOptionsCompat23

private static class mImpl extends ActivityOptionsCompat
{

    private final ActivityOptionsCompat23 mImpl;

    public void requestUsageTimeReport(PendingIntent pendingintent)
    {
        mImpl.requestUsageTimeReport(pendingintent);
    }

    public Bundle toBundle()
    {
        return mImpl.toBundle();
    }

    public void update(ActivityOptionsCompat activityoptionscompat)
    {
        if (activityoptionscompat instanceof mImpl)
        {
            activityoptionscompat = (mImpl)activityoptionscompat;
            mImpl.update(((mImpl) (activityoptionscompat)).mImpl);
        }
    }

    (ActivityOptionsCompat23 activityoptionscompat23)
    {
        mImpl = activityoptionscompat23;
    }
}
