// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapper, TintAwareDrawable, DrawableWrapperApi14, DrawableWrapperApi19, 
//            DrawableWrapperApi21

public final class DrawableCompat
{
    static class DrawableCompatApi17Impl extends DrawableCompatBaseImpl
    {

        private static final String TAG = "DrawableCompatApi17";
        private static Method sGetLayoutDirectionMethod;
        private static boolean sGetLayoutDirectionMethodFetched;
        private static Method sSetLayoutDirectionMethod;
        private static boolean sSetLayoutDirectionMethodFetched;

        public int getLayoutDirection(Drawable drawable)
        {
            if (!sGetLayoutDirectionMethodFetched)
            {
                int i;
                try
                {
                    sGetLayoutDirectionMethod = android/graphics/drawable/Drawable.getDeclaredMethod("getLayoutDirection", new Class[0]);
                    sGetLayoutDirectionMethod.setAccessible(true);
                }
                catch (NoSuchMethodException nosuchmethodexception)
                {
                    Log.i("DrawableCompatApi17", "Failed to retrieve getLayoutDirection() method", nosuchmethodexception);
                }
                sGetLayoutDirectionMethodFetched = true;
            }
            if (sGetLayoutDirectionMethod == null)
            {
                break MISSING_BLOCK_LABEL_84;
            }
            i = ((Integer)sGetLayoutDirectionMethod.invoke(drawable, new Object[0])).intValue();
            return i;
            drawable;
            Log.i("DrawableCompatApi17", "Failed to invoke getLayoutDirection() via reflection", drawable);
            sGetLayoutDirectionMethod = null;
            return 0;
        }

        public boolean setLayoutDirection(Drawable drawable, int i)
        {
            if (!sSetLayoutDirectionMethodFetched)
            {
                try
                {
                    sSetLayoutDirectionMethod = android/graphics/drawable/Drawable.getDeclaredMethod("setLayoutDirection", new Class[] {
                        Integer.TYPE
                    });
                    sSetLayoutDirectionMethod.setAccessible(true);
                }
                catch (NoSuchMethodException nosuchmethodexception)
                {
                    Log.i("DrawableCompatApi17", "Failed to retrieve setLayoutDirection(int) method", nosuchmethodexception);
                }
                sSetLayoutDirectionMethodFetched = true;
            }
            if (sSetLayoutDirectionMethod == null)
            {
                break MISSING_BLOCK_LABEL_91;
            }
            sSetLayoutDirectionMethod.invoke(drawable, new Object[] {
                Integer.valueOf(i)
            });
            return true;
            drawable;
            Log.i("DrawableCompatApi17", "Failed to invoke setLayoutDirection(int) via reflection", drawable);
            sSetLayoutDirectionMethod = null;
            return false;
        }

        DrawableCompatApi17Impl()
        {
        }
    }

    static class DrawableCompatApi19Impl extends DrawableCompatApi17Impl
    {

        public int getAlpha(Drawable drawable)
        {
            return drawable.getAlpha();
        }

        public boolean isAutoMirrored(Drawable drawable)
        {
            return drawable.isAutoMirrored();
        }

        public void setAutoMirrored(Drawable drawable, boolean flag)
        {
            drawable.setAutoMirrored(flag);
        }

        public Drawable wrap(Drawable drawable)
        {
            Object obj = drawable;
            if (!(drawable instanceof TintAwareDrawable))
            {
                obj = new DrawableWrapperApi19(drawable);
            }
            return ((Drawable) (obj));
        }

        DrawableCompatApi19Impl()
        {
        }
    }

    static class DrawableCompatApi21Impl extends DrawableCompatApi19Impl
    {

        public void applyTheme(Drawable drawable, android.content.res.Resources.Theme theme)
        {
            drawable.applyTheme(theme);
        }

        public boolean canApplyTheme(Drawable drawable)
        {
            return drawable.canApplyTheme();
        }

        public void clearColorFilter(Drawable drawable)
        {
            drawable.clearColorFilter();
            if (!(drawable instanceof InsetDrawable)) goto _L2; else goto _L1
_L1:
            clearColorFilter(((InsetDrawable)drawable).getDrawable());
_L4:
            return;
_L2:
            if (drawable instanceof DrawableWrapper)
            {
                clearColorFilter(((DrawableWrapper)drawable).getWrappedDrawable());
                return;
            }
            if (drawable instanceof DrawableContainer)
            {
                drawable = (android.graphics.drawable.DrawableContainer.DrawableContainerState)((DrawableContainer)drawable).getConstantState();
                if (drawable != null)
                {
                    int i = 0;
                    int j = drawable.getChildCount();
                    while (i < j) 
                    {
                        Drawable drawable1 = drawable.getChild(i);
                        if (drawable1 != null)
                        {
                            clearColorFilter(drawable1);
                        }
                        i++;
                    }
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public ColorFilter getColorFilter(Drawable drawable)
        {
            return drawable.getColorFilter();
        }

        public void inflate(Drawable drawable, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
            throws IOException, XmlPullParserException
        {
            drawable.inflate(resources, xmlpullparser, attributeset, theme);
        }

        public void setHotspot(Drawable drawable, float f, float f1)
        {
            drawable.setHotspot(f, f1);
        }

        public void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
        {
            drawable.setHotspotBounds(i, j, k, l);
        }

        public void setTint(Drawable drawable, int i)
        {
            drawable.setTint(i);
        }

        public void setTintList(Drawable drawable, ColorStateList colorstatelist)
        {
            drawable.setTintList(colorstatelist);
        }

        public void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
        {
            drawable.setTintMode(mode);
        }

        public Drawable wrap(Drawable drawable)
        {
            Object obj = drawable;
            if (!(drawable instanceof TintAwareDrawable))
            {
                obj = new DrawableWrapperApi21(drawable);
            }
            return ((Drawable) (obj));
        }

        DrawableCompatApi21Impl()
        {
        }
    }

    static class DrawableCompatApi23Impl extends DrawableCompatApi21Impl
    {

        public void clearColorFilter(Drawable drawable)
        {
            drawable.clearColorFilter();
        }

        public int getLayoutDirection(Drawable drawable)
        {
            return drawable.getLayoutDirection();
        }

        public boolean setLayoutDirection(Drawable drawable, int i)
        {
            return drawable.setLayoutDirection(i);
        }

        public Drawable wrap(Drawable drawable)
        {
            return drawable;
        }

        DrawableCompatApi23Impl()
        {
        }
    }

    static class DrawableCompatBaseImpl
    {

        public void applyTheme(Drawable drawable, android.content.res.Resources.Theme theme)
        {
        }

        public boolean canApplyTheme(Drawable drawable)
        {
            return false;
        }

        public void clearColorFilter(Drawable drawable)
        {
            drawable.clearColorFilter();
        }

        public int getAlpha(Drawable drawable)
        {
            return 0;
        }

        public ColorFilter getColorFilter(Drawable drawable)
        {
            return null;
        }

        public int getLayoutDirection(Drawable drawable)
        {
            return 0;
        }

        public void inflate(Drawable drawable, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
            throws IOException, XmlPullParserException
        {
            drawable.inflate(resources, xmlpullparser, attributeset);
        }

        public boolean isAutoMirrored(Drawable drawable)
        {
            return false;
        }

        public void jumpToCurrentState(Drawable drawable)
        {
            drawable.jumpToCurrentState();
        }

        public void setAutoMirrored(Drawable drawable, boolean flag)
        {
        }

        public void setHotspot(Drawable drawable, float f, float f1)
        {
        }

        public void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
        {
        }

        public boolean setLayoutDirection(Drawable drawable, int i)
        {
            return false;
        }

        public void setTint(Drawable drawable, int i)
        {
            if (drawable instanceof TintAwareDrawable)
            {
                ((TintAwareDrawable)drawable).setTint(i);
            }
        }

        public void setTintList(Drawable drawable, ColorStateList colorstatelist)
        {
            if (drawable instanceof TintAwareDrawable)
            {
                ((TintAwareDrawable)drawable).setTintList(colorstatelist);
            }
        }

        public void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
        {
            if (drawable instanceof TintAwareDrawable)
            {
                ((TintAwareDrawable)drawable).setTintMode(mode);
            }
        }

        public Drawable wrap(Drawable drawable)
        {
            Object obj = drawable;
            if (!(drawable instanceof TintAwareDrawable))
            {
                obj = new DrawableWrapperApi14(drawable);
            }
            return ((Drawable) (obj));
        }

        DrawableCompatBaseImpl()
        {
        }
    }


    static final DrawableCompatBaseImpl IMPL;

    private DrawableCompat()
    {
    }

    public static void applyTheme(Drawable drawable, android.content.res.Resources.Theme theme)
    {
        IMPL.applyTheme(drawable, theme);
    }

    public static boolean canApplyTheme(Drawable drawable)
    {
        return IMPL.canApplyTheme(drawable);
    }

    public static void clearColorFilter(Drawable drawable)
    {
        IMPL.clearColorFilter(drawable);
    }

    public static int getAlpha(Drawable drawable)
    {
        return IMPL.getAlpha(drawable);
    }

    public static ColorFilter getColorFilter(Drawable drawable)
    {
        return IMPL.getColorFilter(drawable);
    }

    public static int getLayoutDirection(Drawable drawable)
    {
        return IMPL.getLayoutDirection(drawable);
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        IMPL.inflate(drawable, resources, xmlpullparser, attributeset, theme);
    }

    public static boolean isAutoMirrored(Drawable drawable)
    {
        return IMPL.isAutoMirrored(drawable);
    }

    public static void jumpToCurrentState(Drawable drawable)
    {
        IMPL.jumpToCurrentState(drawable);
    }

    public static void setAutoMirrored(Drawable drawable, boolean flag)
    {
        IMPL.setAutoMirrored(drawable, flag);
    }

    public static void setHotspot(Drawable drawable, float f, float f1)
    {
        IMPL.setHotspot(drawable, f, f1);
    }

    public static void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
    {
        IMPL.setHotspotBounds(drawable, i, j, k, l);
    }

    public static boolean setLayoutDirection(Drawable drawable, int i)
    {
        return IMPL.setLayoutDirection(drawable, i);
    }

    public static void setTint(Drawable drawable, int i)
    {
        IMPL.setTint(drawable, i);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorstatelist)
    {
        IMPL.setTintList(drawable, colorstatelist);
    }

    public static void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setTintMode(drawable, mode);
    }

    public static Drawable unwrap(Drawable drawable)
    {
        Drawable drawable1 = drawable;
        if (drawable instanceof DrawableWrapper)
        {
            drawable1 = ((DrawableWrapper)drawable).getWrappedDrawable();
        }
        return drawable1;
    }

    public static Drawable wrap(Drawable drawable)
    {
        return IMPL.wrap(drawable);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 23)
        {
            IMPL = new DrawableCompatApi23Impl();
        } else
        if (i >= 21)
        {
            IMPL = new DrawableCompatApi21Impl();
        } else
        if (i >= 19)
        {
            IMPL = new DrawableCompatApi19Impl();
        } else
        if (i >= 17)
        {
            IMPL = new DrawableCompatApi17Impl();
        } else
        {
            IMPL = new DrawableCompatBaseImpl();
        }
    }
}
