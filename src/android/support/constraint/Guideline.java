// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Guideline extends View
{

    public Guideline(Context context)
    {
        super(context);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i);
        super.setVisibility(8);
    }

    public void draw(Canvas canvas)
    {
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(0, 0);
    }

    public void setVisibility(int i)
    {
    }
}
