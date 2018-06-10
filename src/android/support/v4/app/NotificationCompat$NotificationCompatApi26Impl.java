// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, NotificationCompatApi26

static class  extends 
{

    public Notification build( ,  1)
    {
         2 = new (., ., ., ., ., ., ., ., ., ., ., ., .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te);
        NotificationCompat.addActionsToBuilder(2, .te);
        NotificationCompat.addStyleToBuilderApi24(2, .te);
        1 = 1.te(, 2);
        if (.te != null)
        {
            .te.te(getExtras(1));
        }
        return 1;
    }

    public String getChannel(Notification notification)
    {
        return NotificationCompatApi26.getChannel(notification);
    }

    ()
    {
    }
}
