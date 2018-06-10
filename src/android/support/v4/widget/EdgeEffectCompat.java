// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat
{
    static class EdgeEffectApi21Impl extends EdgeEffectBaseImpl
    {

        public void onPull(EdgeEffect edgeeffect, float f, float f1)
        {
            edgeeffect.onPull(f, f1);
        }

        EdgeEffectApi21Impl()
        {
        }
    }

    static class EdgeEffectBaseImpl
    {

        public void onPull(EdgeEffect edgeeffect, float f, float f1)
        {
            edgeeffect.onPull(f);
        }

        EdgeEffectBaseImpl()
        {
        }
    }


    private static final EdgeEffectBaseImpl IMPL;
    private EdgeEffect mEdgeEffect;

    public EdgeEffectCompat(Context context)
    {
        mEdgeEffect = new EdgeEffect(context);
    }

    public static void onPull(EdgeEffect edgeeffect, float f, float f1)
    {
        IMPL.onPull(edgeeffect, f, f1);
    }

    public boolean draw(Canvas canvas)
    {
        return mEdgeEffect.draw(canvas);
    }

    public void finish()
    {
        mEdgeEffect.finish();
    }

    public boolean isFinished()
    {
        return mEdgeEffect.isFinished();
    }

    public boolean onAbsorb(int i)
    {
        mEdgeEffect.onAbsorb(i);
        return true;
    }

    public boolean onPull(float f)
    {
        mEdgeEffect.onPull(f);
        return true;
    }

    public boolean onPull(float f, float f1)
    {
        IMPL.onPull(mEdgeEffect, f, f1);
        return true;
    }

    public boolean onRelease()
    {
        mEdgeEffect.onRelease();
        return mEdgeEffect.isFinished();
    }

    public void setSize(int i, int j)
    {
        mEdgeEffect.setSize(i, j);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            IMPL = new EdgeEffectApi21Impl();
        } else
        {
            IMPL = new EdgeEffectBaseImpl();
        }
    }
}
