// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSeekBarHelper

public class AppCompatSeekBar extends SeekBar
{

    private AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context context)
    {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this);
        mAppCompatSeekBarHelper.loadFromAttributes(attributeset, i);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        mAppCompatSeekBarHelper.drawableStateChanged();
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        mAppCompatSeekBarHelper.jumpDrawablesToCurrentState();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mAppCompatSeekBarHelper.drawTickMarks(canvas);
    }
}
