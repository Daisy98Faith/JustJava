// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.app:
//            ActionBarDrawerToggle, ActionBarDrawerToggleIcs

private static class 
    implements 
{

    public Drawable getThemeUpIndicator(Activity activity)
    {
        return ActionBarDrawerToggleIcs.getThemeUpIndicator(activity);
    }

    public Object setActionBarDescription(Object obj, Activity activity, int i)
    {
        return ActionBarDrawerToggleIcs.setActionBarDescription(obj, activity, i);
    }

    public Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i)
    {
        return ActionBarDrawerToggleIcs.setActionBarUpIndicator(obj, activity, drawable, i);
    }

    ()
    {
    }
}
