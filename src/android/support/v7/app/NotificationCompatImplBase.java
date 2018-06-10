// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.widget.RemoteViews;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

class NotificationCompatImplBase
{

    private static final int MAX_ACTION_BUTTONS = 3;
    static final int MAX_MEDIA_BUTTONS = 5;
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;

    NotificationCompatImplBase()
    {
    }

    public static RemoteViews applyStandardTemplate(Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, int j, Bitmap bitmap, CharSequence charsequence3, 
            boolean flag, long l, int k, int i1, int j1, boolean flag1)
    {
        Resources resources = context.getResources();
        RemoteViews remoteviews = new RemoteViews(context.getPackageName(), j1);
        boolean flag2 = false;
        j1 = 0;
        if (k < -1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (android.os.Build.VERSION.SDK_INT >= 16 && android.os.Build.VERSION.SDK_INT < 21)
        {
            if (k != 0)
            {
                remoteviews.setInt(android.support.v7.appcompat.R.id.notification_background, "setBackgroundResource", android.support.v7.appcompat.R.drawable.notification_bg_low);
                remoteviews.setInt(android.support.v7.appcompat.R.id.icon, "setBackgroundResource", android.support.v7.appcompat.R.drawable.notification_template_icon_low_bg);
            } else
            {
                remoteviews.setInt(android.support.v7.appcompat.R.id.notification_background, "setBackgroundResource", android.support.v7.appcompat.R.drawable.notification_bg);
                remoteviews.setInt(android.support.v7.appcompat.R.id.icon, "setBackgroundResource", android.support.v7.appcompat.R.drawable.notification_template_icon_bg);
            }
        }
        if (bitmap != null)
        {
            if (android.os.Build.VERSION.SDK_INT >= 16)
            {
                remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.icon, 0);
                remoteviews.setImageViewBitmap(android.support.v7.appcompat.R.id.icon, bitmap);
            } else
            {
                remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.icon, 8);
            }
            if (j != 0)
            {
                k = resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_right_icon_size);
                int k1 = resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_small_icon_background_padding);
                float f;
                if (android.os.Build.VERSION.SDK_INT >= 21)
                {
                    context = createIconWithBackground(context, j, k, k - k1 * 2, i1);
                    remoteviews.setImageViewBitmap(android.support.v7.appcompat.R.id.right_icon, context);
                } else
                {
                    remoteviews.setImageViewBitmap(android.support.v7.appcompat.R.id.right_icon, createColoredBitmap(context, j, -1));
                }
                remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.right_icon, 0);
            }
        } else
        if (j != 0)
        {
            remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.icon, 0);
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                context = createIconWithBackground(context, j, resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_big_circle_margin), resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_small_icon_size_as_large), i1);
                remoteviews.setImageViewBitmap(android.support.v7.appcompat.R.id.icon, context);
            } else
            {
                remoteviews.setImageViewBitmap(android.support.v7.appcompat.R.id.icon, createColoredBitmap(context, j, -1));
            }
        }
        if (charsequence != null)
        {
            remoteviews.setTextViewText(android.support.v7.appcompat.R.id.title, charsequence);
        }
        j = ((flag2) ? 1 : 0);
        if (charsequence1 != null)
        {
            remoteviews.setTextViewText(android.support.v7.appcompat.R.id.text, charsequence1);
            j = 1;
        }
        if (android.os.Build.VERSION.SDK_INT < 21 && bitmap != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (charsequence2 != null)
        {
            remoteviews.setTextViewText(android.support.v7.appcompat.R.id.info, charsequence2);
            remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.info, 0);
            j = 1;
            i = 1;
        } else
        if (i > 0)
        {
            if (i > resources.getInteger(android.support.v7.appcompat.R.integer.status_bar_notification_info_maxnum))
            {
                remoteviews.setTextViewText(android.support.v7.appcompat.R.id.info, resources.getString(android.support.v7.appcompat.R.string.status_bar_notification_info_overflow));
            } else
            {
                context = NumberFormat.getIntegerInstance();
                remoteviews.setTextViewText(android.support.v7.appcompat.R.id.info, context.format(i));
            }
            remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.info, 0);
            j = 1;
            i = 1;
        } else
        {
            remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.info, 8);
            i = k;
        }
        k = j1;
        if (charsequence3 != null)
        {
            k = j1;
            if (android.os.Build.VERSION.SDK_INT >= 16)
            {
                remoteviews.setTextViewText(android.support.v7.appcompat.R.id.text, charsequence3);
                if (charsequence1 != null)
                {
                    remoteviews.setTextViewText(android.support.v7.appcompat.R.id.text2, charsequence1);
                    remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.text2, 0);
                    k = 1;
                } else
                {
                    remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.text2, 8);
                    k = j1;
                }
            }
        }
        if (k != 0 && android.os.Build.VERSION.SDK_INT >= 16)
        {
            if (flag1)
            {
                f = resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_subtext_size);
                remoteviews.setTextViewTextSize(android.support.v7.appcompat.R.id.text, 0, f);
            }
            remoteviews.setViewPadding(android.support.v7.appcompat.R.id.line1, 0, 0, 0, 0);
        }
        if (l != 0L)
        {
            if (flag && android.os.Build.VERSION.SDK_INT >= 16)
            {
                remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.chronometer, 0);
                remoteviews.setLong(android.support.v7.appcompat.R.id.chronometer, "setBase", (SystemClock.elapsedRealtime() - System.currentTimeMillis()) + l);
                remoteviews.setBoolean(android.support.v7.appcompat.R.id.chronometer, "setStarted", true);
            } else
            {
                remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.time, 0);
                remoteviews.setLong(android.support.v7.appcompat.R.id.time, "setTime", l);
            }
            i = 1;
        }
        k = android.support.v7.appcompat.R.id.right_side;
        if (i != 0)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        remoteviews.setViewVisibility(k, i);
        k = android.support.v7.appcompat.R.id.line3;
        if (j != 0)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        remoteviews.setViewVisibility(k, i);
        return remoteviews;
    }

    public static RemoteViews applyStandardTemplateWithActions(Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, int j, Bitmap bitmap, CharSequence charsequence3, 
            boolean flag, long l, int k, int i1, int j1, boolean flag1, 
            ArrayList arraylist)
    {
        charsequence = applyStandardTemplate(context, charsequence, charsequence1, charsequence2, i, j, bitmap, charsequence3, flag, l, k, i1, j1, flag1);
        charsequence.removeAllViews(android.support.v7.appcompat.R.id.actions);
        i = 0;
        j = i;
        if (arraylist != null)
        {
            k = arraylist.size();
            j = i;
            if (k > 0)
            {
                i1 = 1;
                i = k;
                if (k > 3)
                {
                    i = 3;
                }
                k = 0;
                do
                {
                    j = i1;
                    if (k >= i)
                    {
                        break;
                    }
                    charsequence1 = generateActionButton(context, (android.support.v4.app.NotificationCompat.Action)arraylist.get(k));
                    charsequence.addView(android.support.v7.appcompat.R.id.actions, charsequence1);
                    k++;
                } while (true);
            }
        }
        if (j != 0)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        charsequence.setViewVisibility(android.support.v7.appcompat.R.id.actions, i);
        charsequence.setViewVisibility(android.support.v7.appcompat.R.id.action_divider, i);
        return charsequence;
    }

    public static void buildIntoRemoteViews(Context context, RemoteViews remoteviews, RemoteViews remoteviews1)
    {
        hideNormalContent(remoteviews);
        remoteviews.removeAllViews(android.support.v7.appcompat.R.id.notification_main_column);
        remoteviews.addView(android.support.v7.appcompat.R.id.notification_main_column, remoteviews1.clone());
        remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.notification_main_column, 0);
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            remoteviews.setViewPadding(android.support.v7.appcompat.R.id.notification_main_column_container, 0, calculateTopPadding(context), 0, 0);
        }
    }

    public static int calculateTopPadding(Context context)
    {
        int i = context.getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_top_pad);
        int j = context.getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.notification_top_pad_large_text);
        float f = (constrain(context.getResources().getConfiguration().fontScale, 1.0F, 1.3F) - 1.0F) / 0.3F;
        return Math.round((1.0F - f) * (float)i + (float)j * f);
    }

    public static float constrain(float f, float f1, float f2)
    {
        if (f < f1)
        {
            return f1;
        }
        if (f > f2)
        {
            return f2;
        } else
        {
            return f;
        }
    }

    private static Bitmap createColoredBitmap(Context context, int i, int j)
    {
        return createColoredBitmap(context, i, j, 0);
    }

    private static Bitmap createColoredBitmap(Context context, int i, int j, int k)
    {
        context = context.getResources().getDrawable(i);
        Bitmap bitmap;
        if (k == 0)
        {
            i = context.getIntrinsicWidth();
        } else
        {
            i = k;
        }
        if (k == 0)
        {
            k = context.getIntrinsicHeight();
        }
        bitmap = Bitmap.createBitmap(i, k, android.graphics.Bitmap.Config.ARGB_8888);
        context.setBounds(0, 0, i, k);
        if (j != 0)
        {
            context.mutate().setColorFilter(new PorterDuffColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN));
        }
        context.draw(new Canvas(bitmap));
        return bitmap;
    }

    public static Bitmap createIconWithBackground(Context context, int i, int j, int k, int l)
    {
        int j1 = android.support.v7.appcompat.R.drawable.notification_icon_background;
        int i1 = l;
        if (l == 0)
        {
            i1 = 0;
        }
        Bitmap bitmap = createColoredBitmap(context, j1, i1, j);
        Canvas canvas = new Canvas(bitmap);
        context = context.getResources().getDrawable(i).mutate();
        context.setFilterBitmap(true);
        i = (j - k) / 2;
        context.setBounds(i, i, k + i, k + i);
        context.setColorFilter(new PorterDuffColorFilter(-1, android.graphics.PorterDuff.Mode.SRC_ATOP));
        context.draw(canvas);
        return bitmap;
    }

    private static RemoteViews generateActionButton(Context context, android.support.v4.app.NotificationCompat.Action action)
    {
        Object obj;
        boolean flag;
        int i;
        if (action.actionIntent == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = context.getPackageName();
        if (flag)
        {
            i = getActionTombstoneLayoutResource();
        } else
        {
            i = getActionLayoutResource();
        }
        obj = new RemoteViews(((String) (obj)), i);
        ((RemoteViews) (obj)).setImageViewBitmap(android.support.v7.appcompat.R.id.action_image, createColoredBitmap(context, action.getIcon(), context.getResources().getColor(android.support.v7.appcompat.R.color.notification_action_color_filter)));
        ((RemoteViews) (obj)).setTextViewText(android.support.v7.appcompat.R.id.action_text, action.title);
        if (!flag)
        {
            ((RemoteViews) (obj)).setOnClickPendingIntent(android.support.v7.appcompat.R.id.action_container, action.actionIntent);
        }
        if (android.os.Build.VERSION.SDK_INT >= 15)
        {
            ((RemoteViews) (obj)).setContentDescription(android.support.v7.appcompat.R.id.action_container, action.title);
        }
        return ((RemoteViews) (obj));
    }

    private static RemoteViews generateContentViewMedia(Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, Bitmap bitmap, CharSequence charsequence3, boolean flag, 
            long l, int j, List list, int ai[], boolean flag1, PendingIntent pendingintent, 
            boolean flag2)
    {
        int k;
        if (flag2)
        {
            k = android.support.v7.appcompat.R.layout.notification_template_media_custom;
        } else
        {
            k = android.support.v7.appcompat.R.layout.notification_template_media;
        }
        charsequence = applyStandardTemplate(context, charsequence, charsequence1, charsequence2, i, 0, bitmap, charsequence3, flag, l, j, 0, k, true);
        k = list.size();
        if (ai == null)
        {
            i = 0;
        } else
        {
            i = Math.min(ai.length, 3);
        }
        charsequence.removeAllViews(android.support.v7.appcompat.R.id.media_actions);
        if (i > 0)
        {
            for (j = 0; j < i; j++)
            {
                if (j >= k)
                {
                    throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] {
                        Integer.valueOf(j), Integer.valueOf(k - 1)
                    }));
                }
                charsequence1 = generateMediaActionButton(context, (android.support.v4.app.NotificationCompatBase.Action)list.get(ai[j]));
                charsequence.addView(android.support.v7.appcompat.R.id.media_actions, charsequence1);
            }

        }
        if (flag1)
        {
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.end_padder, 8);
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.cancel_action, 0);
            charsequence.setOnClickPendingIntent(android.support.v7.appcompat.R.id.cancel_action, pendingintent);
            charsequence.setInt(android.support.v7.appcompat.R.id.cancel_action, "setAlpha", context.getResources().getInteger(android.support.v7.appcompat.R.integer.cancel_button_image_alpha));
            return charsequence;
        } else
        {
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.end_padder, 0);
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.cancel_action, 8);
            return charsequence;
        }
    }

    private static RemoteViews generateMediaActionButton(Context context, android.support.v4.app.NotificationCompatBase.Action action)
    {
        boolean flag;
        if (action.getActionIntent() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        context = new RemoteViews(context.getPackageName(), android.support.v7.appcompat.R.layout.notification_media_action);
        context.setImageViewResource(android.support.v7.appcompat.R.id.action0, action.getIcon());
        if (!flag)
        {
            context.setOnClickPendingIntent(android.support.v7.appcompat.R.id.action0, action.getActionIntent());
        }
        if (android.os.Build.VERSION.SDK_INT >= 15)
        {
            context.setContentDescription(android.support.v7.appcompat.R.id.action0, action.getTitle());
        }
        return context;
    }

    public static RemoteViews generateMediaBigView(Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, Bitmap bitmap, CharSequence charsequence3, boolean flag, 
            long l, int j, int k, List list, boolean flag1, PendingIntent pendingintent, 
            boolean flag2)
    {
        int i1 = Math.min(list.size(), 5);
        charsequence = applyStandardTemplate(context, charsequence, charsequence1, charsequence2, i, 0, bitmap, charsequence3, flag, l, j, k, getBigMediaLayoutResource(flag2, i1), false);
        charsequence.removeAllViews(android.support.v7.appcompat.R.id.media_actions);
        if (i1 > 0)
        {
            for (i = 0; i < i1; i++)
            {
                charsequence1 = generateMediaActionButton(context, (android.support.v4.app.NotificationCompatBase.Action)list.get(i));
                charsequence.addView(android.support.v7.appcompat.R.id.media_actions, charsequence1);
            }

        }
        if (flag1)
        {
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.cancel_action, 0);
            charsequence.setInt(android.support.v7.appcompat.R.id.cancel_action, "setAlpha", context.getResources().getInteger(android.support.v7.appcompat.R.integer.cancel_button_image_alpha));
            charsequence.setOnClickPendingIntent(android.support.v7.appcompat.R.id.cancel_action, pendingintent);
            return charsequence;
        } else
        {
            charsequence.setViewVisibility(android.support.v7.appcompat.R.id.cancel_action, 8);
            return charsequence;
        }
    }

    private static int getActionLayoutResource()
    {
        return android.support.v7.appcompat.R.layout.notification_action;
    }

    private static int getActionTombstoneLayoutResource()
    {
        return android.support.v7.appcompat.R.layout.notification_action_tombstone;
    }

    private static int getBigMediaLayoutResource(boolean flag, int i)
    {
        if (i <= 3)
        {
            if (flag)
            {
                return android.support.v7.appcompat.R.layout.notification_template_big_media_narrow_custom;
            } else
            {
                return android.support.v7.appcompat.R.layout.notification_template_big_media_narrow;
            }
        }
        if (flag)
        {
            return android.support.v7.appcompat.R.layout.notification_template_big_media_custom;
        } else
        {
            return android.support.v7.appcompat.R.layout.notification_template_big_media;
        }
    }

    private static void hideNormalContent(RemoteViews remoteviews)
    {
        remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.title, 8);
        remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.text2, 8);
        remoteviews.setViewVisibility(android.support.v7.appcompat.R.id.text, 8);
    }

    public static RemoteViews overrideContentViewMedia(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, Bitmap bitmap, CharSequence charsequence3, 
            boolean flag, long l, int j, List list, int ai[], boolean flag1, 
            PendingIntent pendingintent, boolean flag2)
    {
        context = generateContentViewMedia(context, charsequence, charsequence1, charsequence2, i, bitmap, charsequence3, flag, l, j, list, ai, flag1, pendingintent, flag2);
        notificationbuilderwithbuilderaccessor.getBuilder().setContent(context);
        if (flag1)
        {
            notificationbuilderwithbuilderaccessor.getBuilder().setOngoing(true);
        }
        return context;
    }

    public static void overrideMediaBigContentView(Notification notification, Context context, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, int i, Bitmap bitmap, CharSequence charsequence3, 
            boolean flag, long l, int j, int k, List list, boolean flag1, 
            PendingIntent pendingintent, boolean flag2)
    {
        notification.bigContentView = generateMediaBigView(context, charsequence, charsequence1, charsequence2, i, bitmap, charsequence3, flag, l, j, k, list, flag1, pendingintent, flag2);
        if (flag1)
        {
            notification.flags = notification.flags | 2;
        }
    }
}
