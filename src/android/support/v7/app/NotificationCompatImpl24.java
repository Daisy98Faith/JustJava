// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

class NotificationCompatImpl24
{

    NotificationCompatImpl24()
    {
    }

    public static void addDecoratedCustomViewStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
    {
        notificationbuilderwithbuilderaccessor.getBuilder().setStyle(new android.app.Notification.DecoratedCustomViewStyle());
    }

    public static void addDecoratedMediaCustomViewStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
    {
        notificationbuilderwithbuilderaccessor.getBuilder().setStyle(new android.app.Notification.DecoratedMediaCustomViewStyle());
    }
}
