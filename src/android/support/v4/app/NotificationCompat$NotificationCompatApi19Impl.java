// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, RemoteInput, NotificationCompatKitKat

static class  extends 
{

    public Notification build( ,  1)
    {
         2 = new (., ., .(), .(), ., ., ., ., ., ., ., ., .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te);
        NotificationCompat.addActionsToBuilder(2, .te);
        NotificationCompat.addStyleToBuilderJellybean(2, .te);
        return 1.te(, 2);
    }

    public te getAction(Notification notification, int i)
    {
        return (te)NotificationCompatKitKat.getAction(notification, i, te, RemoteInput.FACTORY);
    }

    public int getActionCount(Notification notification)
    {
        return NotificationCompatKitKat.getActionCount(notification);
    }

    public Bundle getExtras(Notification notification)
    {
        return NotificationCompatKitKat.getExtras(notification);
    }

    public String getGroup(Notification notification)
    {
        return NotificationCompatKitKat.getGroup(notification);
    }

    public boolean getLocalOnly(Notification notification)
    {
        return NotificationCompatKitKat.getLocalOnly(notification);
    }

    public String getSortKey(Notification notification)
    {
        return NotificationCompatKitKat.getSortKey(notification);
    }

    public boolean isGroupSummary(Notification notification)
    {
        return NotificationCompatKitKat.isGroupSummary(notification);
    }

    ()
    {
    }
}
