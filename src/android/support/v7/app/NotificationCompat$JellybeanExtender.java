// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Notification;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

// Referenced classes of package android.support.v7.app:
//            NotificationCompat

private static class r extends android.support.v4.app.r
{

    public Notification build(android.support.v4.app.r r, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
    {
        android.widget.RemoteViews remoteviews = NotificationCompat.access$400(notificationbuilderwithbuilderaccessor, r);
        notificationbuilderwithbuilderaccessor = notificationbuilderwithbuilderaccessor.build();
        if (remoteviews != null)
        {
            notificationbuilderwithbuilderaccessor.contentView = remoteviews;
        }
        NotificationCompat.access$500(notificationbuilderwithbuilderaccessor, r);
        return notificationbuilderwithbuilderaccessor;
    }

    r()
    {
    }
}
