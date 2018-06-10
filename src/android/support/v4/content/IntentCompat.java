// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

public final class IntentCompat
{
    static class IntentCompatApi15Impl extends IntentCompatBaseImpl
    {

        public Intent makeMainSelectorActivity(String s, String s1)
        {
            return Intent.makeMainSelectorActivity(s, s1);
        }

        IntentCompatApi15Impl()
        {
        }
    }

    static class IntentCompatBaseImpl
    {

        public Intent makeMainSelectorActivity(String s, String s1)
        {
            s = new Intent(s);
            s.addCategory(s1);
            return s;
        }

        IntentCompatBaseImpl()
        {
        }
    }


    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final IntentCompatBaseImpl IMPL;

    private IntentCompat()
    {
    }

    public static Intent makeMainActivity(ComponentName componentname)
    {
        return Intent.makeMainActivity(componentname);
    }

    public static Intent makeMainSelectorActivity(String s, String s1)
    {
        return IMPL.makeMainSelectorActivity(s, s1);
    }

    public static Intent makeRestartActivityTask(ComponentName componentname)
    {
        return Intent.makeRestartActivityTask(componentname);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 15)
        {
            IMPL = new IntentCompatApi15Impl();
        } else
        {
            IMPL = new IntentCompatBaseImpl();
        }
    }
}
