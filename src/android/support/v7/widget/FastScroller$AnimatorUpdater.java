// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

// Referenced classes of package android.support.v7.widget:
//            FastScroller

private class <init>
    implements android.animation.istener
{

    final FastScroller this$0;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = (int)(((Float)valueanimator.getAnimatedValue()).floatValue() * 255F);
        FastScroller.access$600(FastScroller.this).setAlpha(i);
        FastScroller.access$700(FastScroller.this).setAlpha(i);
        FastScroller.access$500(FastScroller.this);
    }

    private ()
    {
        this$0 = FastScroller.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
