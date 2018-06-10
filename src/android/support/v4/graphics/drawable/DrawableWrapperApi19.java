// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperApi14

class DrawableWrapperApi19 extends DrawableWrapperApi14
{
    private static class DrawableWrapperStateKitKat extends DrawableWrapperApi14.DrawableWrapperState
    {

        public Drawable newDrawable(Resources resources)
        {
            return new DrawableWrapperApi19(this, resources);
        }

        DrawableWrapperStateKitKat(DrawableWrapperApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate, resources);
        }
    }


    DrawableWrapperApi19(Drawable drawable)
    {
        super(drawable);
    }

    DrawableWrapperApi19(DrawableWrapperApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        super(drawablewrapperstate, resources);
    }

    public boolean isAutoMirrored()
    {
        return mDrawable.isAutoMirrored();
    }

    DrawableWrapperApi14.DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateKitKat(mState, null);
    }

    public void setAutoMirrored(boolean flag)
    {
        mDrawable.setAutoMirrored(flag);
    }
}
