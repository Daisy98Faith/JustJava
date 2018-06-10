// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            VectorEnabledTintResources, TintResources

public class TintContextWrapper extends ContextWrapper
{

    private static final Object CACHE_LOCK = new Object();
    private static ArrayList sCache;
    private final Resources mResources;
    private final android.content.res.Resources.Theme mTheme;

    private TintContextWrapper(Context context)
    {
        super(context);
        if (VectorEnabledTintResources.shouldBeUsed())
        {
            mResources = new VectorEnabledTintResources(this, context.getResources());
            mTheme = mResources.newTheme();
            mTheme.setTo(context.getTheme());
            return;
        } else
        {
            mResources = new TintResources(this, context.getResources());
            mTheme = null;
            return;
        }
    }

    private static boolean shouldWrap(Context context)
    {
        while ((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources) || (context.getResources() instanceof VectorEnabledTintResources) || android.os.Build.VERSION.SDK_INT >= 21 && !VectorEnabledTintResources.shouldBeUsed()) 
        {
            return false;
        }
        return true;
    }

    public static Context wrap(Context context)
    {
        if (!shouldWrap(context)) goto _L2; else goto _L1
_L1:
        Object obj1 = CACHE_LOCK;
        obj1;
        JVM INSTR monitorenter ;
        if (sCache != null) goto _L4; else goto _L3
_L3:
        sCache = new ArrayList();
_L6:
        context = new TintContextWrapper(context);
        sCache.add(new WeakReference(context));
        return context;
_L4:
        int i = sCache.size() - 1;
_L13:
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        Object obj = (WeakReference)sCache.get(i);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        if (((WeakReference) (obj)).get() != null)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        sCache.remove(i);
        break MISSING_BLOCK_LABEL_174;
        i = sCache.size() - 1;
_L12:
        if (i < 0) goto _L6; else goto _L5
_L5:
        obj = (WeakReference)sCache.get(i);
        if (obj == null) goto _L8; else goto _L7
_L7:
        obj = (TintContextWrapper)((WeakReference) (obj)).get();
_L10:
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((TintContextWrapper) (obj)).getBaseContext() != context)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1;
        JVM INSTR monitorexit ;
        return ((Context) (obj));
        context;
        obj1;
        JVM INSTR monitorexit ;
        throw context;
_L8:
        obj = null;
        if (true) goto _L10; else goto _L9
_L9:
        i--;
        if (true) goto _L12; else goto _L11
_L11:
          goto _L6
_L2:
        return context;
        i--;
          goto _L13
    }

    public AssetManager getAssets()
    {
        return mResources.getAssets();
    }

    public Resources getResources()
    {
        return mResources;
    }

    public android.content.res.Resources.Theme getTheme()
    {
        if (mTheme == null)
        {
            return super.getTheme();
        } else
        {
            return mTheme;
        }
    }

    public void setTheme(int i)
    {
        if (mTheme == null)
        {
            super.setTheme(i);
            return;
        } else
        {
            mTheme.applyStyle(i, true);
            return;
        }
    }

}
