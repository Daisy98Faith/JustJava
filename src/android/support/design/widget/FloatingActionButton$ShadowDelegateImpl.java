// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.design.widget:
//            ShadowViewDelegate, FloatingActionButton

private class this._cls0
    implements ShadowViewDelegate
{

    final FloatingActionButton this$0;

    public float getRadius()
    {
        return (float)getSizeDimension() / 2.0F;
    }

    public boolean isCompatPaddingEnabled()
    {
        return mCompatPadding;
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        FloatingActionButton.access$001(FloatingActionButton.this, drawable);
    }

    public void setShadowPadding(int i, int j, int k, int l)
    {
        mShadowPadding.set(i, j, k, l);
        setPadding(mImagePadding + i, mImagePadding + j, mImagePadding + k, mImagePadding + l);
    }

    ()
    {
        this$0 = FloatingActionButton.this;
        super();
    }
}
