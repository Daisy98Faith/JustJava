// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v4.widget:
//            TintableCompoundButton

public final class CompoundButtonCompat
{
    static class CompoundButtonCompatApi21Impl extends CompoundButtonCompatBaseImpl
    {

        public ColorStateList getButtonTintList(CompoundButton compoundbutton)
        {
            return compoundbutton.getButtonTintList();
        }

        public android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
        {
            return compoundbutton.getButtonTintMode();
        }

        public void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
        {
            compoundbutton.setButtonTintList(colorstatelist);
        }

        public void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
        {
            compoundbutton.setButtonTintMode(mode);
        }

        CompoundButtonCompatApi21Impl()
        {
        }
    }

    static class CompoundButtonCompatApi23Impl extends CompoundButtonCompatApi21Impl
    {

        public Drawable getButtonDrawable(CompoundButton compoundbutton)
        {
            return compoundbutton.getButtonDrawable();
        }

        CompoundButtonCompatApi23Impl()
        {
        }
    }

    static class CompoundButtonCompatBaseImpl
    {

        private static final String TAG = "CompoundButtonCompat";
        private static Field sButtonDrawableField;
        private static boolean sButtonDrawableFieldFetched;

        public Drawable getButtonDrawable(CompoundButton compoundbutton)
        {
            if (!sButtonDrawableFieldFetched)
            {
                try
                {
                    sButtonDrawableField = android/widget/CompoundButton.getDeclaredField("mButtonDrawable");
                    sButtonDrawableField.setAccessible(true);
                }
                catch (NoSuchFieldException nosuchfieldexception)
                {
                    Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", nosuchfieldexception);
                }
                sButtonDrawableFieldFetched = true;
            }
            if (sButtonDrawableField == null)
            {
                break MISSING_BLOCK_LABEL_73;
            }
            compoundbutton = (Drawable)sButtonDrawableField.get(compoundbutton);
            return compoundbutton;
            compoundbutton;
            Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", compoundbutton);
            sButtonDrawableField = null;
            return null;
        }

        public ColorStateList getButtonTintList(CompoundButton compoundbutton)
        {
            if (compoundbutton instanceof TintableCompoundButton)
            {
                return ((TintableCompoundButton)compoundbutton).getSupportButtonTintList();
            } else
            {
                return null;
            }
        }

        public android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
        {
            if (compoundbutton instanceof TintableCompoundButton)
            {
                return ((TintableCompoundButton)compoundbutton).getSupportButtonTintMode();
            } else
            {
                return null;
            }
        }

        public void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
        {
            if (compoundbutton instanceof TintableCompoundButton)
            {
                ((TintableCompoundButton)compoundbutton).setSupportButtonTintList(colorstatelist);
            }
        }

        public void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
        {
            if (compoundbutton instanceof TintableCompoundButton)
            {
                ((TintableCompoundButton)compoundbutton).setSupportButtonTintMode(mode);
            }
        }

        CompoundButtonCompatBaseImpl()
        {
        }
    }


    private static final CompoundButtonCompatBaseImpl IMPL;

    private CompoundButtonCompat()
    {
    }

    public static Drawable getButtonDrawable(CompoundButton compoundbutton)
    {
        return IMPL.getButtonDrawable(compoundbutton);
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundbutton)
    {
        return IMPL.getButtonTintList(compoundbutton);
    }

    public static android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
    {
        return IMPL.getButtonTintMode(compoundbutton);
    }

    public static void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
    {
        IMPL.setButtonTintList(compoundbutton, colorstatelist);
    }

    public static void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setButtonTintMode(compoundbutton, mode);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 23)
        {
            IMPL = new CompoundButtonCompatApi23Impl();
        } else
        if (i >= 21)
        {
            IMPL = new CompoundButtonCompatApi21Impl();
        } else
        {
            IMPL = new CompoundButtonCompatBaseImpl();
        }
    }
}
