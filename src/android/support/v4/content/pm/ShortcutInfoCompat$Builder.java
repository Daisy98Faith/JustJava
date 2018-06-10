// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;

// Referenced classes of package android.support.v4.content.pm:
//            ShortcutInfoCompat

public static class mInfo
{

    private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat(null);

    public ShortcutInfoCompat build()
    {
        if (TextUtils.isEmpty(ShortcutInfoCompat.access$300(mInfo)))
        {
            throw new IllegalArgumentException("Shortcut much have a non-empty label");
        }
        if (ShortcutInfoCompat.access$600(mInfo) == null || ShortcutInfoCompat.access$600(mInfo).length == 0)
        {
            throw new IllegalArgumentException("Shortcut much have an intent");
        } else
        {
            return mInfo;
        }
    }

    public mInfo setActivity(ComponentName componentname)
    {
        ShortcutInfoCompat.access$902(mInfo, componentname);
        return this;
    }

    public mInfo setDisabledMessage(CharSequence charsequence)
    {
        ShortcutInfoCompat.access$502(mInfo, charsequence);
        return this;
    }

    public mInfo setIcon(int i)
    {
        ShortcutInfoCompat.access$802(mInfo, i);
        return this;
    }

    public mInfo setIcon(Bitmap bitmap)
    {
        ShortcutInfoCompat.access$702(mInfo, bitmap);
        return this;
    }

    public mInfo setIntent(Intent intent)
    {
        return setIntents(new Intent[] {
            intent
        });
    }

    public setIntents setIntents(Intent aintent[])
    {
        ShortcutInfoCompat.access$602(mInfo, aintent);
        return this;
    }

    public mInfo setLongLabel(CharSequence charsequence)
    {
        ShortcutInfoCompat.access$402(mInfo, charsequence);
        return this;
    }

    public mInfo setShortLabel(CharSequence charsequence)
    {
        ShortcutInfoCompat.access$302(mInfo, charsequence);
        return this;
    }

    public (Context context, String s)
    {
        ShortcutInfoCompat.access$102(mInfo, context);
        ShortcutInfoCompat.access$202(mInfo, s);
    }
}
