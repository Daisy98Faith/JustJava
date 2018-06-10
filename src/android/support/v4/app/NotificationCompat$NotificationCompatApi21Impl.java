// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, NotificationCompatApi21

static class ry extends ry
{

    public Notification build(ry ry, ry ry1)
    {
        ry ry2 = new ry(ry.ry, ry.ry, ry.ry(), ry.ry(), ry.ry, ry.ry, ry.ry, ry.ry, ry.ry, ry.ry, ry.ry, ry.ry, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te, ry.te);
        NotificationCompat.addActionsToBuilder(ry2, ry.te);
        NotificationCompat.addStyleToBuilderJellybean(ry2, ry.te);
        ry1 = ry1.te(ry, ry2);
        if (ry.te != null)
        {
            ry.te.te(getExtras(ry1));
        }
        return ry1;
    }

    public Bundle getBundleForUnreadConversation(getExtras getextras)
    {
        return NotificationCompatApi21.getBundleForUnreadConversation(getextras);
    }

    public String getCategory(Notification notification)
    {
        return NotificationCompatApi21.getCategory(notification);
    }

    public ry getUnreadConversationFromBundle(Bundle bundle, ry ry, ry ry1)
    {
        return NotificationCompatApi21.getUnreadConversationFromBundle(bundle, ry, ry1);
    }

    ry()
    {
    }
}
