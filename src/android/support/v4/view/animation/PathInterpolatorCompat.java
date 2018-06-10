// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

// Referenced classes of package android.support.v4.view.animation:
//            PathInterpolatorApi14

public final class PathInterpolatorCompat
{

    private PathInterpolatorCompat()
    {
    }

    public static Interpolator create(float f, float f1)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return new PathInterpolator(f, f1);
        } else
        {
            return new PathInterpolatorApi14(f, f1);
        }
    }

    public static Interpolator create(float f, float f1, float f2, float f3)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return new PathInterpolator(f, f1, f2, f3);
        } else
        {
            return new PathInterpolatorApi14(f, f1, f2, f3);
        }
    }

    public static Interpolator create(Path path)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return new PathInterpolator(path);
        } else
        {
            return new PathInterpolatorApi14(path);
        }
    }
}
