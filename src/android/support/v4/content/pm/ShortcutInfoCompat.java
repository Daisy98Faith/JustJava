// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.text.TextUtils;

public class ShortcutInfoCompat
{
    public static class Builder
    {

        private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();

        public ShortcutInfoCompat build()
        {
            if (TextUtils.isEmpty(mInfo.mLabel))
            {
                throw new IllegalArgumentException("Shortcut much have a non-empty label");
            }
            if (mInfo.mIntents == null || mInfo.mIntents.length == 0)
            {
                throw new IllegalArgumentException("Shortcut much have an intent");
            } else
            {
                return mInfo;
            }
        }

        public Builder setActivity(ComponentName componentname)
        {
            mInfo.mActivity = componentname;
            return this;
        }

        public Builder setDisabledMessage(CharSequence charsequence)
        {
            mInfo.mDisabledMessage = charsequence;
            return this;
        }

        public Builder setIcon(int i)
        {
            mInfo.mIconId = i;
            return this;
        }

        public Builder setIcon(Bitmap bitmap)
        {
            mInfo.mIconBitmap = bitmap;
            return this;
        }

        public Builder setIntent(Intent intent)
        {
            return setIntents(new Intent[] {
                intent
            });
        }

        public Builder setIntents(Intent aintent[])
        {
            mInfo.mIntents = aintent;
            return this;
        }

        public Builder setLongLabel(CharSequence charsequence)
        {
            mInfo.mLongLabel = charsequence;
            return this;
        }

        public Builder setShortLabel(CharSequence charsequence)
        {
            mInfo.mLabel = charsequence;
            return this;
        }

        public Builder(Context context, String s)
        {
            mInfo.mContext = context;
            mInfo.mId = s;
        }
    }


    private ComponentName mActivity;
    private Context mContext;
    private CharSequence mDisabledMessage;
    private Bitmap mIconBitmap;
    private int mIconId;
    private String mId;
    private Intent mIntents[];
    private CharSequence mLabel;
    private CharSequence mLongLabel;

    private ShortcutInfoCompat()
    {
    }


    Intent addToIntent(Intent intent)
    {
        intent.putExtra("android.intent.extra.shortcut.INTENT", mIntents[mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", mLabel.toString());
        if (mIconId != 0)
        {
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", android.content.Intent.ShortcutIconResource.fromContext(mContext, mIconId));
        }
        if (mIconBitmap != null)
        {
            intent.putExtra("android.intent.extra.shortcut.ICON", mIconBitmap);
        }
        return intent;
    }

    ShortcutInfo toShortcutInfo()
    {
        android.content.pm.ShortcutInfo.Builder builder = (new android.content.pm.ShortcutInfo.Builder(mContext, mId)).setShortLabel(mLabel).setIntents(mIntents);
        if (mIconId == 0) goto _L2; else goto _L1
_L1:
        builder.setIcon(Icon.createWithResource(mContext, mIconId));
_L4:
        if (!TextUtils.isEmpty(mLongLabel))
        {
            builder.setLongLabel(mLongLabel);
        }
        if (!TextUtils.isEmpty(mDisabledMessage))
        {
            builder.setDisabledMessage(mDisabledMessage);
        }
        if (mActivity != null)
        {
            builder.setActivity(mActivity);
        }
        return builder.build();
_L2:
        if (mIconBitmap != null)
        {
            builder.setIcon(Icon.createWithBitmap(mIconBitmap));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }


/*
    static Context access$102(ShortcutInfoCompat shortcutinfocompat, Context context)
    {
        shortcutinfocompat.mContext = context;
        return context;
    }

*/


/*
    static String access$202(ShortcutInfoCompat shortcutinfocompat, String s)
    {
        shortcutinfocompat.mId = s;
        return s;
    }

*/



/*
    static CharSequence access$302(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mLabel = charsequence;
        return charsequence;
    }

*/


/*
    static CharSequence access$402(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mLongLabel = charsequence;
        return charsequence;
    }

*/


/*
    static CharSequence access$502(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mDisabledMessage = charsequence;
        return charsequence;
    }

*/



/*
    static Intent[] access$602(ShortcutInfoCompat shortcutinfocompat, Intent aintent[])
    {
        shortcutinfocompat.mIntents = aintent;
        return aintent;
    }

*/


/*
    static Bitmap access$702(ShortcutInfoCompat shortcutinfocompat, Bitmap bitmap)
    {
        shortcutinfocompat.mIconBitmap = bitmap;
        return bitmap;
    }

*/


/*
    static int access$802(ShortcutInfoCompat shortcutinfocompat, int i)
    {
        shortcutinfocompat.mIconId = i;
        return i;
    }

*/


/*
    static ComponentName access$902(ShortcutInfoCompat shortcutinfocompat, ComponentName componentname)
    {
        shortcutinfocompat.mActivity = componentname;
        return componentname;
    }

*/
}
