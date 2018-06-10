// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextHelperV17, AppCompatDrawableManager, TintInfo, TintTypedArray

class AppCompatTextHelper
{

    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    final TextView mView;

    AppCompatTextHelper(TextView textview)
    {
        mView = textview;
    }

    static AppCompatTextHelper create(TextView textview)
    {
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            return new AppCompatTextHelperV17(textview);
        } else
        {
            return new AppCompatTextHelper(textview);
        }
    }

    protected static TintInfo createTintInfo(Context context, AppCompatDrawableManager appcompatdrawablemanager, int i)
    {
        context = appcompatdrawablemanager.getTintList(context, i);
        if (context != null)
        {
            appcompatdrawablemanager = new TintInfo();
            appcompatdrawablemanager.mHasTintList = true;
            appcompatdrawablemanager.mTintList = context;
            return appcompatdrawablemanager;
        } else
        {
            return null;
        }
    }

    final void applyCompoundDrawableTint(Drawable drawable, TintInfo tintinfo)
    {
        if (drawable != null && tintinfo != null)
        {
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
        }
    }

    void applyCompoundDrawablesTints()
    {
        if (mDrawableLeftTint != null || mDrawableTopTint != null || mDrawableRightTint != null || mDrawableBottomTint != null)
        {
            Drawable adrawable[] = mView.getCompoundDrawables();
            applyCompoundDrawableTint(adrawable[0], mDrawableLeftTint);
            applyCompoundDrawableTint(adrawable[1], mDrawableTopTint);
            applyCompoundDrawableTint(adrawable[2], mDrawableRightTint);
            applyCompoundDrawableTint(adrawable[3], mDrawableBottomTint);
        }
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        Context context = mView.getContext();
        Object obj = AppCompatDrawableManager.get();
        Object obj1 = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextHelper, i, 0);
        int j = ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft))
        {
            mDrawableLeftTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop))
        {
            mDrawableTopTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight))
        {
            mDrawableRightTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom))
        {
            mDrawableBottomTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        ((TintTypedArray) (obj1)).recycle();
        boolean flag4 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag = false;
        boolean flag1 = false;
        android.content.res.ColorStateList colorstatelist = null;
        obj = null;
        Object obj2 = null;
        obj1 = null;
        TintTypedArray tinttypedarray = null;
        if (j != -1)
        {
            TintTypedArray tinttypedarray1 = TintTypedArray.obtainStyledAttributes(context, j, android.support.v7.appcompat.R.styleable.TextAppearance);
            flag2 = flag3;
            flag = flag1;
            if (!flag4)
            {
                flag2 = flag3;
                flag = flag1;
                if (tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
                {
                    flag = true;
                    flag2 = tinttypedarray1.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false);
                }
            }
            obj = colorstatelist;
            colorstatelist = tinttypedarray;
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                obj1 = obj2;
                if (tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
                {
                    obj1 = tinttypedarray1.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
                }
                obj = obj1;
                colorstatelist = tinttypedarray;
                if (tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint))
                {
                    colorstatelist = tinttypedarray1.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
                    obj = obj1;
                }
            }
            tinttypedarray1.recycle();
            obj1 = colorstatelist;
        }
        tinttypedarray = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.TextAppearance, i, 0);
        flag3 = flag2;
        i = ((flag) ? 1 : 0);
        if (!flag4)
        {
            flag3 = flag2;
            i = ((flag) ? 1 : 0);
            if (tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
            {
                i = 1;
                flag3 = tinttypedarray.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false);
            }
        }
        attributeset = ((AttributeSet) (obj));
        colorstatelist = ((android.content.res.ColorStateList) (obj1));
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            if (tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
            {
                obj = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            }
            attributeset = ((AttributeSet) (obj));
            colorstatelist = ((android.content.res.ColorStateList) (obj1));
            if (tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint))
            {
                colorstatelist = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
                attributeset = ((AttributeSet) (obj));
            }
        }
        tinttypedarray.recycle();
        if (attributeset != null)
        {
            mView.setTextColor(attributeset);
        }
        if (colorstatelist != null)
        {
            mView.setHintTextColor(colorstatelist);
        }
        if (!flag4 && i != 0)
        {
            setAllCaps(flag3);
        }
    }

    void onSetTextAppearance(Context context, int i)
    {
        context = TintTypedArray.obtainStyledAttributes(context, i, android.support.v7.appcompat.R.styleable.TextAppearance);
        if (context.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
        {
            setAllCaps(context.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false));
        }
        if (android.os.Build.VERSION.SDK_INT < 23 && context.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
        {
            android.content.res.ColorStateList colorstatelist = context.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            if (colorstatelist != null)
            {
                mView.setTextColor(colorstatelist);
            }
        }
        context.recycle();
    }

    void setAllCaps(boolean flag)
    {
        TextView textview = mView;
        AllCapsTransformationMethod allcapstransformationmethod;
        if (flag)
        {
            allcapstransformationmethod = new AllCapsTransformationMethod(mView.getContext());
        } else
        {
            allcapstransformationmethod = null;
        }
        textview.setTransformationMethod(allcapstransformationmethod);
    }
}
