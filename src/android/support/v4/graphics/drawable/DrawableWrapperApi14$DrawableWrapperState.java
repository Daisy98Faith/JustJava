// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperApi14

protected static abstract class mTintMode extends android.graphics.drawable.apperState
{

    int mChangingConfigurations;
    android.graphics.drawable.apperState mDrawableState;
    ColorStateList mTint;
    android.graphics.rawableWrapperState mTintMode;

    boolean canConstantState()
    {
        return mDrawableState != null;
    }

    public int getChangingConfigurations()
    {
        int j = mChangingConfigurations;
        int i;
        if (mDrawableState != null)
        {
            i = mDrawableState.ations();
        } else
        {
            i = 0;
        }
        return i | j;
    }

    public Drawable newDrawable()
    {
        return newDrawable(null);
    }

    public abstract Drawable newDrawable(Resources resources);

    ( , Resources resources)
    {
        mTint = null;
        mTintMode = DrawableWrapperApi14.DEFAULT_TINT_MODE;
        if ( != null)
        {
            mChangingConfigurations = .mChangingConfigurations;
            mDrawableState = .mDrawableState;
            mTint = .mTint;
            mTintMode = .mTintMode;
        }
    }
}
