// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperApi19

class DrawableWrapperApi21 extends DrawableWrapperApi19
{
    private static class DrawableWrapperStateLollipop extends DrawableWrapperApi14.DrawableWrapperState
    {

        public Drawable newDrawable(Resources resources)
        {
            return new DrawableWrapperApi21(this, resources);
        }

        DrawableWrapperStateLollipop(DrawableWrapperApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate, resources);
        }
    }


    DrawableWrapperApi21(Drawable drawable)
    {
        super(drawable);
    }

    DrawableWrapperApi21(DrawableWrapperApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        super(drawablewrapperstate, resources);
    }

    public Rect getDirtyBounds()
    {
        return mDrawable.getDirtyBounds();
    }

    public void getOutline(Outline outline)
    {
        mDrawable.getOutline(outline);
    }

    protected boolean isCompatTintEnabled()
    {
        boolean flag;
label0:
        {
            boolean flag1 = false;
            flag = flag1;
            if (android.os.Build.VERSION.SDK_INT != 21)
            {
                break label0;
            }
            Drawable drawable = mDrawable;
            if (!(drawable instanceof GradientDrawable) && !(drawable instanceof DrawableContainer))
            {
                flag = flag1;
                if (!(drawable instanceof InsetDrawable))
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    DrawableWrapperApi14.DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateLollipop(mState, null);
    }

    public void setHotspot(float f, float f1)
    {
        mDrawable.setHotspot(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        mDrawable.setHotspotBounds(i, j, k, l);
    }

    public boolean setState(int ai[])
    {
        if (super.setState(ai))
        {
            invalidateSelf();
            return true;
        } else
        {
            return false;
        }
    }

    public void setTint(int i)
    {
        if (isCompatTintEnabled())
        {
            super.setTint(i);
            return;
        } else
        {
            mDrawable.setTint(i);
            return;
        }
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        if (isCompatTintEnabled())
        {
            super.setTintList(colorstatelist);
            return;
        } else
        {
            mDrawable.setTintList(colorstatelist);
            return;
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (isCompatTintEnabled())
        {
            super.setTintMode(mode);
            return;
        } else
        {
            mDrawable.setTintMode(mode);
            return;
        }
    }
}
