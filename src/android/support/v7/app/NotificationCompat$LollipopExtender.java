// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Notification;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

// Referenced classes of package android.support.v7.app:
//            NotificationCompat

private static class or extends android.support.v4.app.or
{

    public Notification build(android.support.v4.app.or or, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
    {
        android.widget.RemoteViews remoteviews = NotificationCompat.access$600(notificationbuilderwithbuilderaccessor, or);
        notificationbuilderwithbuilderaccessor = notificationbuilderwithbuilderaccessor.build();
        if (remoteviews != null)
        {
            notificationbuilderwithbuilderaccessor.contentView = remoteviews;
        }
        NotificationCompat.access$700(notificationbuilderwithbuilderaccessor, or);
        NotificationCompat.access$800(notificationbuilderwithbuilderaccessor, or);
        return notificationbuilderwithbuilderaccessor;
    }

    or()
    {
    }
}
