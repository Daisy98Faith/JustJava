// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

class NotificationCompatImplJellybean
{

    NotificationCompatImplJellybean()
    {
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence)
    {
        (new android.app.Notification.BigTextStyle(notificationbuilderwithbuilderaccessor.getBuilder())).bigText(charsequence);
    }
}
