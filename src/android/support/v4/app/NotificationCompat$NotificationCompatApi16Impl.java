// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, RemoteInput, NotificationCompatJellybean

static class  extends 
{

    public Notification build( ,  1)
    {
         2 = new (., ., .(), .(), ., ., ., ., ., ., ., ., .te, .te, .te, .te, .te, .te, .te, .te, .te, .te, .te);
        NotificationCompat.addActionsToBuilder(2, .te);
        NotificationCompat.addStyleToBuilderJellybean(2, .te);
        1 = 1.te(, 2);
        if (.te != null)
        {
            Bundle bundle = getExtras(1);
            if (bundle != null)
            {
                .getExtras.getExtras(bundle);
            }
        }
        return 1;
    }

    public getExtras getAction(Notification notification, int i)
    {
        return (getExtras)NotificationCompatJellybean.getAction(notification, i, getExtras, RemoteInput.FACTORY);
    }

    public int getActionCount(Notification notification)
    {
        return NotificationCompatJellybean.getActionCount(notification);
    }

    public getExtras[] getActionsFromParcelableArrayList(ArrayList arraylist)
    {
        return (getExtras[])(getExtras[])NotificationCompatJellybean.getActionsFromParcelableArrayList(arraylist, lableArrayList, RemoteInput.FACTORY);
    }

    public Bundle getExtras(Notification notification)
    {
        return NotificationCompatJellybean.getExtras(notification);
    }

    public String getGroup(Notification notification)
    {
        return NotificationCompatJellybean.getGroup(notification);
    }

    public boolean getLocalOnly(Notification notification)
    {
        return NotificationCompatJellybean.getLocalOnly(notification);
    }

    public ArrayList getParcelableArrayListForActions(lableArrayList alablearraylist[])
    {
        return NotificationCompatJellybean.getParcelableArrayListForActions(alablearraylist);
    }

    public String getSortKey(Notification notification)
    {
        return NotificationCompatJellybean.getSortKey(notification);
    }

    public boolean isGroupSummary(Notification notification)
    {
        return NotificationCompatJellybean.isGroupSummary(notification);
    }

    ()
    {
    }
}
