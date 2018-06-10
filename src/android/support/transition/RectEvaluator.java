// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

class RectEvaluator
    implements TypeEvaluator
{

    private Rect mRect;

    RectEvaluator()
    {
    }

    RectEvaluator(Rect rect)
    {
        mRect = rect;
    }

    public Rect evaluate(float f, Rect rect, Rect rect1)
    {
        int i = rect.left + (int)((float)(rect1.left - rect.left) * f);
        int j = rect.top + (int)((float)(rect1.top - rect.top) * f);
        int k = rect.right + (int)((float)(rect1.right - rect.right) * f);
        int l = rect.bottom + (int)((float)(rect1.bottom - rect.bottom) * f);
        if (mRect == null)
        {
            return new Rect(i, j, k, l);
        } else
        {
            mRect.set(i, j, k, l);
            return mRect;
        }
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Rect)obj, (Rect)obj1);
    }
}
