// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, NotificationCompat

public static class mBuilder
    implements NotificationBuilderWithBuilderAccessor
{

    private android.app.uilderBase.mBuilder mBuilder;

    public Notification build()
    {
        return mBuilder.mBuilder();
    }

    public android.app.uilderBase getBuilder()
    {
        return mBuilder;
    }

    (Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
            PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag)
    {
        context = (new android.app.uilderBase(context)).uilderBase(notification.when).uilderBase(notification.icon, notification.iconLevel).uilderBase(notification.contentView).uilderBase(notification.tickerText, remoteviews).uilderBase(notification.sound, notification.audioStreamType).uilderBase(notification.vibrate).uilderBase(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        boolean flag1;
        if ((notification.flags & 2) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        context = context.uilderBase(flag1);
        if ((notification.flags & 8) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        context = context.uilderBase(flag1);
        if ((notification.flags & 0x10) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        context = context.uilderBase(flag1).uilderBase(notification.defaults).uilderBase(charsequence).uilderBase(charsequence1).uilderBase(charsequence2).uilderBase(pendingintent).uilderBase(notification.deleteIntent);
        if ((notification.flags & 0x80) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mBuilder = context.mBuilder(pendingintent1, flag1).mBuilder(bitmap).mBuilder(i).mBuilder(j, k, flag);
    }
}
