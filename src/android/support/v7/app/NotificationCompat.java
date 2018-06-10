// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.util.List;

// Referenced classes of package android.support.v7.app:
//            NotificationCompatImplBase, NotificationCompatImplJellybean, NotificationCompatImpl21, NotificationCompatImpl24

public class NotificationCompat extends android.support.v4.app.NotificationCompat
{
    private static class Api24Extender extends android.support.v4.app.BuilderExtender
    {

        public Notification build(android.support.v4.app.Builder builder, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            NotificationCompat.addStyleToBuilderApi24(notificationbuilderwithbuilderaccessor, builder);
            return notificationbuilderwithbuilderaccessor.build();
        }

        private Api24Extender()
        {
        }

    }

    public static class Builder extends android.support.v4.app.Builder
    {

        protected android.support.v4.app.BuilderExtender getExtender()
        {
            if (android.os.Build.VERSION.SDK_INT >= 24)
            {
                return new Api24Extender();
            }
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                return new LollipopExtender();
            }
            if (android.os.Build.VERSION.SDK_INT >= 16)
            {
                return new JellybeanExtender();
            }
            if (android.os.Build.VERSION.SDK_INT >= 14)
            {
                return new IceCreamSandwichExtender();
            } else
            {
                return super.getExtender();
            }
        }

        protected CharSequence resolveText()
        {
            if (mStyle instanceof android.support.v4.app.MessagingStyle)
            {
                android.support.v4.app.MessagingStyle messagingstyle = (android.support.v4.app.MessagingStyle)mStyle;
                android.support.v4.app.MessagingStyle.Message message = NotificationCompat.findLatestIncomingMessage(messagingstyle);
                CharSequence charsequence = messagingstyle.getConversationTitle();
                if (message != null)
                {
                    if (charsequence != null)
                    {
                        return NotificationCompat.makeMessageLine(this, messagingstyle, message);
                    } else
                    {
                        return message.getText();
                    }
                }
            }
            return super.resolveText();
        }

        protected CharSequence resolveTitle()
        {
            if (mStyle instanceof android.support.v4.app.MessagingStyle)
            {
                Object obj = (android.support.v4.app.MessagingStyle)mStyle;
                android.support.v4.app.MessagingStyle.Message message = NotificationCompat.findLatestIncomingMessage(((android.support.v4.app.MessagingStyle) (obj)));
                obj = ((android.support.v4.app.MessagingStyle) (obj)).getConversationTitle();
                if (obj != null || message != null)
                {
                    if (obj != null)
                    {
                        return ((CharSequence) (obj));
                    } else
                    {
                        return message.getSender();
                    }
                }
            }
            return super.resolveTitle();
        }

        public Builder(Context context)
        {
            super(context);
        }
    }

    public static class DecoratedCustomViewStyle extends android.support.v4.app.Style
    {

        public DecoratedCustomViewStyle()
        {
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle
    {

        public DecoratedMediaCustomViewStyle()
        {
        }
    }

    private static class IceCreamSandwichExtender extends android.support.v4.app.BuilderExtender
    {

        public Notification build(android.support.v4.app.Builder builder, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            RemoteViews remoteviews = NotificationCompat.addStyleGetContentViewIcs(notificationbuilderwithbuilderaccessor, builder);
            notificationbuilderwithbuilderaccessor = notificationbuilderwithbuilderaccessor.build();
            if (remoteviews != null)
            {
                notificationbuilderwithbuilderaccessor.contentView = remoteviews;
            } else
            if (builder.getContentView() != null)
            {
                notificationbuilderwithbuilderaccessor.contentView = builder.getContentView();
                return notificationbuilderwithbuilderaccessor;
            }
            return notificationbuilderwithbuilderaccessor;
        }

        IceCreamSandwichExtender()
        {
        }
    }

    private static class JellybeanExtender extends android.support.v4.app.BuilderExtender
    {

        public Notification build(android.support.v4.app.Builder builder, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            RemoteViews remoteviews = NotificationCompat.addStyleGetContentViewJellybean(notificationbuilderwithbuilderaccessor, builder);
            notificationbuilderwithbuilderaccessor = notificationbuilderwithbuilderaccessor.build();
            if (remoteviews != null)
            {
                notificationbuilderwithbuilderaccessor.contentView = remoteviews;
            }
            NotificationCompat.addBigStyleToBuilderJellybean(notificationbuilderwithbuilderaccessor, builder);
            return notificationbuilderwithbuilderaccessor;
        }

        JellybeanExtender()
        {
        }
    }

    private static class LollipopExtender extends android.support.v4.app.BuilderExtender
    {

        public Notification build(android.support.v4.app.Builder builder, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            RemoteViews remoteviews = NotificationCompat.addStyleGetContentViewLollipop(notificationbuilderwithbuilderaccessor, builder);
            notificationbuilderwithbuilderaccessor = notificationbuilderwithbuilderaccessor.build();
            if (remoteviews != null)
            {
                notificationbuilderwithbuilderaccessor.contentView = remoteviews;
            }
            NotificationCompat.addBigStyleToBuilderLollipop(notificationbuilderwithbuilderaccessor, builder);
            NotificationCompat.addHeadsUpToBuilderLollipop(notificationbuilderwithbuilderaccessor, builder);
            return notificationbuilderwithbuilderaccessor;
        }

        LollipopExtender()
        {
        }
    }

    public static class MediaStyle extends android.support.v4.app.Style
    {

        int mActionsToShowInCompact[];
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        android.support.v4.media.session.MediaSessionCompat.Token mToken;

        public MediaStyle setCancelButtonIntent(PendingIntent pendingintent)
        {
            mCancelButtonIntent = pendingintent;
            return this;
        }

        public MediaStyle setMediaSession(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            mToken = token;
            return this;
        }

        public transient MediaStyle setShowActionsInCompactView(int ai[])
        {
            mActionsToShowInCompact = ai;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean flag)
        {
            mShowCancelButton = flag;
            return this;
        }

        public MediaStyle()
        {
            mActionsToShowInCompact = null;
        }

        public MediaStyle(android.support.v4.app.Builder builder)
        {
            mActionsToShowInCompact = null;
            setBuilder(builder);
        }
    }


    public NotificationCompat()
    {
    }

    private static void addBigStyleToBuilderJellybean(Notification notification, android.support.v4.app.Builder builder)
    {
        if (builder.mStyle instanceof MediaStyle)
        {
            MediaStyle mediastyle = (MediaStyle)builder.mStyle;
            RemoteViews remoteviews;
            boolean flag;
            if (builder.getBigContentView() != null)
            {
                remoteviews = builder.getBigContentView();
            } else
            {
                remoteviews = builder.getContentView();
            }
            if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && remoteviews != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            NotificationCompatImplBase.overrideMediaBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, mediastyle.mShowCancelButton, mediastyle.mCancelButtonIntent, flag);
            if (flag)
            {
                NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.bigContentView, remoteviews);
            }
        } else
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            addDecoratedBigStyleToBuilderJellybean(notification, builder);
            return;
        }
    }

    private static void addBigStyleToBuilderLollipop(Notification notification, android.support.v4.app.Builder builder)
    {
        RemoteViews remoteviews;
        if (builder.getBigContentView() != null)
        {
            remoteviews = builder.getBigContentView();
        } else
        {
            remoteviews = builder.getContentView();
        }
        if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && remoteviews != null)
        {
            NotificationCompatImplBase.overrideMediaBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, false, null, true);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.bigContentView, remoteviews);
            setBackgroundColor(builder.mContext, notification.bigContentView, builder.getColor());
        } else
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            addDecoratedBigStyleToBuilderJellybean(notification, builder);
            return;
        }
    }

    private static void addDecoratedBigStyleToBuilderJellybean(Notification notification, android.support.v4.app.Builder builder)
    {
        RemoteViews remoteviews = builder.getBigContentView();
        if (remoteviews == null)
        {
            remoteviews = builder.getContentView();
        }
        if (remoteviews == null)
        {
            return;
        } else
        {
            RemoteViews remoteviews1 = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, notification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), android.support.v7.appcompat.R.layout.notification_template_custom_big, false, builder.mActions);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteviews1, remoteviews);
            notification.bigContentView = remoteviews1;
            return;
        }
    }

    private static void addDecoratedHeadsUpToBuilderLollipop(Notification notification, android.support.v4.app.Builder builder)
    {
        RemoteViews remoteviews1 = builder.getHeadsUpContentView();
        RemoteViews remoteviews;
        if (remoteviews1 != null)
        {
            remoteviews = remoteviews1;
        } else
        {
            remoteviews = builder.getContentView();
        }
        if (remoteviews1 == null)
        {
            return;
        } else
        {
            RemoteViews remoteviews2 = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, notification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), android.support.v7.appcompat.R.layout.notification_template_custom_big, false, builder.mActions);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteviews2, remoteviews);
            notification.headsUpContentView = remoteviews2;
            return;
        }
    }

    private static void addHeadsUpToBuilderLollipop(Notification notification, android.support.v4.app.Builder builder)
    {
        RemoteViews remoteviews;
        if (builder.getHeadsUpContentView() != null)
        {
            remoteviews = builder.getHeadsUpContentView();
        } else
        {
            remoteviews = builder.getContentView();
        }
        if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && remoteviews != null)
        {
            notification.headsUpContentView = NotificationCompatImplBase.generateMediaBigView(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, false, null, true);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.headsUpContentView, remoteviews);
            setBackgroundColor(builder.mContext, notification.headsUpContentView, builder.getColor());
        } else
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            addDecoratedHeadsUpToBuilderLollipop(notification, builder);
            return;
        }
    }

    private static void addMessagingFallBackStyle(android.support.v4.app.MessagingStyle messagingstyle, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, android.support.v4.app.Builder builder)
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        List list = messagingstyle.getMessages();
        boolean flag;
        int i;
        if (messagingstyle.getConversationTitle() != null || hasMessagesWithoutSender(messagingstyle.getMessages()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = list.size() - 1;
        while (i >= 0) 
        {
            Object obj = (android.support.v4.app.MessagingStyle.Message)list.get(i);
            if (flag)
            {
                obj = makeMessageLine(builder, messagingstyle, ((android.support.v4.app.MessagingStyle.Message) (obj)));
            } else
            {
                obj = ((android.support.v4.app.MessagingStyle.Message) (obj)).getText();
            }
            if (i != list.size() - 1)
            {
                spannablestringbuilder.insert(0, "\n");
            }
            spannablestringbuilder.insert(0, ((CharSequence) (obj)));
            i--;
        }
        NotificationCompatImplJellybean.addBigTextStyle(notificationbuilderwithbuilderaccessor, spannablestringbuilder);
    }

    private static RemoteViews addStyleGetContentViewIcs(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, android.support.v4.app.Builder builder)
    {
        if (builder.mStyle instanceof MediaStyle)
        {
            MediaStyle mediastyle = (MediaStyle)builder.mStyle;
            boolean flag;
            if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && builder.getContentView() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            notificationbuilderwithbuilderaccessor = NotificationCompatImplBase.overrideContentViewMedia(notificationbuilderwithbuilderaccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.mActions, mediastyle.mActionsToShowInCompact, mediastyle.mShowCancelButton, mediastyle.mCancelButtonIntent, flag);
            if (flag)
            {
                NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notificationbuilderwithbuilderaccessor, builder.getContentView());
                return notificationbuilderwithbuilderaccessor;
            }
        } else
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            return getDecoratedContentView(builder);
        }
        return null;
    }

    private static RemoteViews addStyleGetContentViewJellybean(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, android.support.v4.app.Builder builder)
    {
        if (builder.mStyle instanceof android.support.v4.app.MessagingStyle)
        {
            addMessagingFallBackStyle((android.support.v4.app.MessagingStyle)builder.mStyle, notificationbuilderwithbuilderaccessor, builder);
        }
        return addStyleGetContentViewIcs(notificationbuilderwithbuilderaccessor, builder);
    }

    private static RemoteViews addStyleGetContentViewLollipop(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, android.support.v4.app.Builder builder)
    {
        if (builder.mStyle instanceof MediaStyle)
        {
            MediaStyle mediastyle = (MediaStyle)builder.mStyle;
            int ai[] = mediastyle.mActionsToShowInCompact;
            Object obj;
            boolean flag;
            boolean flag1;
            if (mediastyle.mToken != null)
            {
                obj = mediastyle.mToken.getToken();
            } else
            {
                obj = null;
            }
            NotificationCompatImpl21.addMediaStyle(notificationbuilderwithbuilderaccessor, ai, obj);
            if (builder.getContentView() != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (android.os.Build.VERSION.SDK_INT >= 21 && android.os.Build.VERSION.SDK_INT <= 23)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag1 || flag && builder.getBigContentView() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && flag)
            {
                notificationbuilderwithbuilderaccessor = NotificationCompatImplBase.overrideContentViewMedia(notificationbuilderwithbuilderaccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.mActions, mediastyle.mActionsToShowInCompact, false, null, flag1);
                if (flag1)
                {
                    NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notificationbuilderwithbuilderaccessor, builder.getContentView());
                }
                setBackgroundColor(builder.mContext, notificationbuilderwithbuilderaccessor, builder.getColor());
                return notificationbuilderwithbuilderaccessor;
            } else
            {
                return null;
            }
        }
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            return getDecoratedContentView(builder);
        } else
        {
            return addStyleGetContentViewJellybean(notificationbuilderwithbuilderaccessor, builder);
        }
    }

    private static void addStyleToBuilderApi24(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, android.support.v4.app.Builder builder)
    {
        if (builder.mStyle instanceof DecoratedCustomViewStyle)
        {
            NotificationCompatImpl24.addDecoratedCustomViewStyle(notificationbuilderwithbuilderaccessor);
        } else
        {
            if (builder.mStyle instanceof DecoratedMediaCustomViewStyle)
            {
                NotificationCompatImpl24.addDecoratedMediaCustomViewStyle(notificationbuilderwithbuilderaccessor);
                return;
            }
            if (!(builder.mStyle instanceof android.support.v4.app.MessagingStyle))
            {
                addStyleGetContentViewLollipop(notificationbuilderwithbuilderaccessor, builder);
                return;
            }
        }
    }

    private static android.support.v4.app.MessagingStyle.Message findLatestIncomingMessage(android.support.v4.app.MessagingStyle messagingstyle)
    {
        messagingstyle = messagingstyle.getMessages();
        for (int i = messagingstyle.size() - 1; i >= 0; i--)
        {
            android.support.v4.app.MessagingStyle.Message message = (android.support.v4.app.MessagingStyle.Message)messagingstyle.get(i);
            if (!TextUtils.isEmpty(message.getSender()))
            {
                return message;
            }
        }

        if (!messagingstyle.isEmpty())
        {
            return (android.support.v4.app.MessagingStyle.Message)messagingstyle.get(messagingstyle.size() - 1);
        } else
        {
            return null;
        }
    }

    private static RemoteViews getDecoratedContentView(android.support.v4.app.Builder builder)
    {
        if (builder.getContentView() == null)
        {
            return null;
        } else
        {
            RemoteViews remoteviews = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mNotification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), android.support.v7.appcompat.R.layout.notification_template_custom_big, false, null);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteviews, builder.getContentView());
            return remoteviews;
        }
    }

    public static android.support.v4.media.session.MediaSessionCompat.Token getMediaSession(Notification notification)
    {
        notification = getExtras(notification);
        if (notification != null)
        {
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                notification = notification.getParcelable("android.mediaSession");
                if (notification != null)
                {
                    return android.support.v4.media.session.MediaSessionCompat.Token.fromToken(notification);
                }
            } else
            {
                Object obj = BundleCompat.getBinder(notification, "android.mediaSession");
                if (obj != null)
                {
                    notification = Parcel.obtain();
                    notification.writeStrongBinder(((android.os.IBinder) (obj)));
                    notification.setDataPosition(0);
                    obj = (android.support.v4.media.session.MediaSessionCompat.Token)android.support.v4.media.session.MediaSessionCompat.Token.CREATOR.createFromParcel(notification);
                    notification.recycle();
                    return ((android.support.v4.media.session.MediaSessionCompat.Token) (obj));
                }
            }
        }
        return null;
    }

    private static boolean hasMessagesWithoutSender(List list)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            if (((android.support.v4.app.MessagingStyle.Message)list.get(i)).getSender() == null)
            {
                return true;
            }
        }

        return false;
    }

    private static TextAppearanceSpan makeFontColorSpan(int i)
    {
        return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
    }

    private static CharSequence makeMessageLine(android.support.v4.app.Builder builder, android.support.v4.app.MessagingStyle messagingstyle, android.support.v4.app.MessagingStyle.Message message)
    {
        BidiFormatter bidiformatter = BidiFormatter.getInstance();
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        Object obj;
        int i;
        boolean flag;
        int j;
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || android.os.Build.VERSION.SDK_INT <= 10)
        {
            i = 0xff000000;
        } else
        {
            i = -1;
        }
        obj = message.getSender();
        j = i;
        if (TextUtils.isEmpty(message.getSender()))
        {
            if (messagingstyle.getUserDisplayName() == null)
            {
                messagingstyle = "";
            } else
            {
                messagingstyle = messagingstyle.getUserDisplayName();
            }
            j = i;
            obj = messagingstyle;
            if (flag)
            {
                j = i;
                obj = messagingstyle;
                if (builder.getColor() != 0)
                {
                    j = builder.getColor();
                    obj = messagingstyle;
                }
            }
        }
        builder = bidiformatter.unicodeWrap(((CharSequence) (obj)));
        spannablestringbuilder.append(builder);
        spannablestringbuilder.setSpan(makeFontColorSpan(j), spannablestringbuilder.length() - builder.length(), spannablestringbuilder.length(), 33);
        if (message.getText() == null)
        {
            builder = "";
        } else
        {
            builder = message.getText();
        }
        spannablestringbuilder.append("  ").append(bidiformatter.unicodeWrap(builder));
        return spannablestringbuilder;
    }

    private static void setBackgroundColor(Context context, RemoteViews remoteviews, int i)
    {
        int j = i;
        if (i == 0)
        {
            j = context.getResources().getColor(android.support.v7.appcompat.R.color.notification_material_background_media_default_color);
        }
        remoteviews.setInt(android.support.v7.appcompat.R.id.status_bar_latest_event_content, "setBackgroundColor", j);
    }









}
