// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, NotificationBuilderWithBuilderAccessor

static class BuilderBase.mBuilder
    implements BuilderBase.mBuilder
{
    public static class BuilderBase
        implements NotificationBuilderWithBuilderAccessor
    {

        private android.app.Notification.Builder mBuilder;

        public Notification build()
        {
            return mBuilder.getNotification();
        }

        public android.app.Notification.Builder getBuilder()
        {
            return mBuilder;
        }

        BuilderBase(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag)
        {
            context = (new android.app.Notification.Builder(context)).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean flag1;
            if ((notification.flags & 2) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            context = context.setOngoing(flag1);
            if ((notification.flags & 8) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            context = context.setOnlyAlertOnce(flag1);
            if ((notification.flags & 0x10) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            context = context.setAutoCancel(flag1).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 0x80) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            mBuilder = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setProgress(j, k, flag);
        }
    }


    public Notification build(BuilderBase.mBuilder mbuilder, BuilderBase.mBuilder mbuilder1)
    {
        return mbuilder1.mBuilder(mbuilder, new BuilderBase(mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase(), mbuilder.BuilderBase(), mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.BuilderBase, mbuilder.ate));
    }

    public ate getAction(Notification notification, int i)
    {
        return null;
    }

    public int getActionCount(Notification notification)
    {
        return 0;
    }

    public ate[] getActionsFromParcelableArrayList(ArrayList arraylist)
    {
        return null;
    }

    public Bundle getBundleForUnreadConversation(ate ate)
    {
        return null;
    }

    public String getCategory(Notification notification)
    {
        return null;
    }

    public String getChannel(Notification notification)
    {
        return null;
    }

    public Bundle getExtras(Notification notification)
    {
        return null;
    }

    public String getGroup(Notification notification)
    {
        return null;
    }

    public boolean getLocalOnly(Notification notification)
    {
        return false;
    }

    public ArrayList getParcelableArrayListForActions(ate aate[])
    {
        return null;
    }

    public String getSortKey(Notification notification)
    {
        return null;
    }

    public ory getUnreadConversationFromBundle(Bundle bundle, ory ory, ory ory1)
    {
        return null;
    }

    public boolean isGroupSummary(Notification notification)
    {
        return false;
    }

    BuilderBase.mBuilder()
    {
    }
}
