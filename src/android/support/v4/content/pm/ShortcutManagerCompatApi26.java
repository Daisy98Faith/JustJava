// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.pm;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;

// Referenced classes of package android.support.v4.content.pm:
//            ShortcutInfoCompat

class ShortcutManagerCompatApi26
{

    ShortcutManagerCompatApi26()
    {
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutinfocompat)
    {
        return ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).createShortcutResultIntent(shortcutinfocompat.toShortcutInfo());
    }

    public static boolean isRequestPinShortcutSupported(Context context)
    {
        return ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).isRequestPinShortcutSupported();
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutinfocompat, IntentSender intentsender)
    {
        return ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).requestPinShortcut(shortcutinfocompat.toShortcutInfo(), intentsender);
    }
}
