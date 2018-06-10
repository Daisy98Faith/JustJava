// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AppLaunchChecker
{

    private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
    private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";

    public AppLaunchChecker()
    {
    }

    public static boolean hasStartedFromLauncher(Context context)
    {
        return context.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
    }

    public static void onActivityCreate(Activity activity)
    {
        SharedPreferences sharedpreferences = activity.getSharedPreferences("android.support.AppLaunchChecker", 0);
        if (!sharedpreferences.getBoolean("startedFromLauncher", false))
        {
            if ((activity = activity.getIntent()) != null && "android.intent.action.MAIN".equals(activity.getAction()) && (activity.hasCategory("android.intent.category.LAUNCHER") || activity.hasCategory("android.intent.category.LEANBACK_LAUNCHER")))
            {
                android.support.v4.content.SharedPreferencesCompat.EditorCompat.getInstance().apply(sharedpreferences.edit().putBoolean("startedFromLauncher", true));
                return;
            }
        }
    }
}
