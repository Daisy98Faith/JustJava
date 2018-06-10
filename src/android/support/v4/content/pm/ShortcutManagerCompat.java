// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.BuildCompat;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.content.pm:
//            ShortcutManagerCompatApi26, ShortcutInfoCompat

public class ShortcutManagerCompat
{

    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    public ShortcutManagerCompat()
    {
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutinfocompat)
    {
        Intent intent = null;
        if (BuildCompat.isAtLeastO())
        {
            intent = ShortcutManagerCompatApi26.createShortcutResultIntent(context, shortcutinfocompat);
        }
        context = intent;
        if (intent == null)
        {
            context = new Intent();
        }
        return shortcutinfocompat.addToIntent(context);
    }

    public static boolean isRequestPinShortcutSupported(Context context)
    {
        boolean flag1 = false;
        if (!BuildCompat.isAtLeastO()) goto _L2; else goto _L1
_L1:
        boolean flag = ShortcutManagerCompatApi26.isRequestPinShortcutSupported(context);
_L4:
        return flag;
_L2:
        flag = flag1;
        if (ContextCompat.checkSelfPermission(context, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        context = context.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
        do
        {
            flag = flag1;
            if (!context.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            String s = ((ResolveInfo)context.next()).activityInfo.permission;
            if (TextUtils.isEmpty(s) || "com.android.launcher.permission.INSTALL_SHORTCUT".equals(s))
            {
                return true;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutinfocompat, IntentSender intentsender)
    {
        if (BuildCompat.isAtLeastO())
        {
            return ShortcutManagerCompatApi26.requestPinShortcut(context, shortcutinfocompat, intentsender);
        }
        if (!isRequestPinShortcutSupported(context))
        {
            return false;
        }
        shortcutinfocompat = shortcutinfocompat.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if (intentsender == null)
        {
            context.sendBroadcast(shortcutinfocompat);
            return true;
        } else
        {
            context.sendOrderedBroadcast(shortcutinfocompat, null, new BroadcastReceiver(intentsender) {

                final IntentSender val$callback;

                public void onReceive(Context context1, Intent intent)
                {
                    try
                    {
                        callback.sendIntent(context1, 0, null, null, null);
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch (Context context1)
                    {
                        return;
                    }
                }

            
            {
                callback = intentsender;
                super();
            }
            }, null, -1, null, null);
            return true;
        }
    }
}
