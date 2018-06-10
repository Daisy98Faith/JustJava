// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

public class VectorEnabledTintResources extends Resources
{

    public static final int MAX_SDK_WHERE_REQUIRED = 20;
    private final WeakReference mContextRef;

    public VectorEnabledTintResources(Context context, Resources resources)
    {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        mContextRef = new WeakReference(context);
    }

    public static boolean shouldBeUsed()
    {
        return AppCompatDelegate.isCompatVectorFromResourcesEnabled() && android.os.Build.VERSION.SDK_INT <= 20;
    }

    public Drawable getDrawable(int i)
        throws android.content.res.Resources.NotFoundException
    {
        Context context = (Context)mContextRef.get();
        if (context != null)
        {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(context, this, i);
        } else
        {
            return super.getDrawable(i);
        }
    }

    final Drawable superGetDrawable(int i)
    {
        return super.getDrawable(i);
    }
}
