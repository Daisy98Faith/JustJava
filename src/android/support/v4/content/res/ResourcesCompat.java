// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public final class ResourcesCompat
{

    private ResourcesCompat()
    {
    }

    public static int getColor(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return resources.getColor(i, theme);
        } else
        {
            return resources.getColor(i);
        }
    }

    public static ColorStateList getColorStateList(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return resources.getColorStateList(i, theme);
        } else
        {
            return resources.getColorStateList(i);
        }
    }

    public static Drawable getDrawable(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return resources.getDrawable(i, theme);
        } else
        {
            return resources.getDrawable(i);
        }
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int j, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return resources.getDrawableForDensity(i, j, theme);
        }
        if (android.os.Build.VERSION.SDK_INT >= 15)
        {
            return resources.getDrawableForDensity(i, j);
        } else
        {
            return resources.getDrawable(i);
        }
    }
}
