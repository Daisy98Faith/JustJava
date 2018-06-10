// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager, TintInfo, TintTypedArray, DrawableUtils

class AppCompatBackgroundHelper
{

    private int mBackgroundResId;
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager = AppCompatDrawableManager.get();
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view)
    {
        mBackgroundResId = -1;
        mView = view;
    }

    private boolean applyFrameworkTintUsingColorFilter(Drawable drawable)
    {
        if (mTmpInfo == null)
        {
            mTmpInfo = new TintInfo();
        }
        TintInfo tintinfo = mTmpInfo;
        tintinfo.clear();
        Object obj = ViewCompat.getBackgroundTintList(mView);
        if (obj != null)
        {
            tintinfo.mHasTintList = true;
            tintinfo.mTintList = ((ColorStateList) (obj));
        }
        obj = ViewCompat.getBackgroundTintMode(mView);
        if (obj != null)
        {
            tintinfo.mHasTintMode = true;
            tintinfo.mTintMode = ((android.graphics.PorterDuff.Mode) (obj));
        }
        if (tintinfo.mHasTintList || tintinfo.mHasTintMode)
        {
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
            return true;
        } else
        {
            return false;
        }
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter()
    {
        boolean flag1 = true;
        int i = android.os.Build.VERSION.SDK_INT;
        boolean flag;
        if (i < 21)
        {
            flag = false;
        } else
        {
            flag = flag1;
            if (i != 21)
            {
                flag = flag1;
                if (mInternalBackgroundTint == null)
                {
                    return false;
                }
            }
        }
        return flag;
    }

    void applySupportBackgroundTint()
    {
        Drawable drawable = mView.getBackground();
        if (drawable != null && (!shouldApplyFrameworkTintUsingColorFilter() || !applyFrameworkTintUsingColorFilter(drawable)))
        {
            if (mBackgroundTint != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, mBackgroundTint, mView.getDrawableState());
                return;
            }
            if (mInternalBackgroundTint != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, mInternalBackgroundTint, mView.getDrawableState());
                return;
            }
        }
    }

    ColorStateList getSupportBackgroundTintList()
    {
        if (mBackgroundTint != null)
        {
            return mBackgroundTint.mTintList;
        } else
        {
            return null;
        }
    }

    android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        if (mBackgroundTint != null)
        {
            return mBackgroundTint.mTintMode;
        } else
        {
            return null;
        }
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        attributeset = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, android.support.v7.appcompat.R.styleable.ViewBackgroundHelper, i, 0);
        ColorStateList colorstatelist;
        if (!attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background))
        {
            break MISSING_BLOCK_LABEL_67;
        }
        mBackgroundResId = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background, -1);
        colorstatelist = mDrawableManager.getTintList(mView.getContext(), mBackgroundResId);
        if (colorstatelist == null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        setInternalBackgroundTint(colorstatelist);
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint))
        {
            ViewCompat.setBackgroundTintList(mView, attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint));
        }
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode))
        {
            ViewCompat.setBackgroundTintMode(mView, DrawableUtils.parseTintMode(attributeset.getInt(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
        }
        attributeset.recycle();
        return;
        Exception exception;
        exception;
        attributeset.recycle();
        throw exception;
    }

    void onSetBackgroundDrawable(Drawable drawable)
    {
        mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        applySupportBackgroundTint();
    }

    void onSetBackgroundResource(int i)
    {
        mBackgroundResId = i;
        ColorStateList colorstatelist;
        if (mDrawableManager != null)
        {
            colorstatelist = mDrawableManager.getTintList(mView.getContext(), i);
        } else
        {
            colorstatelist = null;
        }
        setInternalBackgroundTint(colorstatelist);
        applySupportBackgroundTint();
    }

    void setInternalBackgroundTint(ColorStateList colorstatelist)
    {
        if (colorstatelist != null)
        {
            if (mInternalBackgroundTint == null)
            {
                mInternalBackgroundTint = new TintInfo();
            }
            mInternalBackgroundTint.mTintList = colorstatelist;
            mInternalBackgroundTint.mHasTintList = true;
        } else
        {
            mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintList = colorstatelist;
        mBackgroundTint.mHasTintList = true;
        applySupportBackgroundTint();
    }

    void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintMode = mode;
        mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }
}
