// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

class this._cls0
    implements this._cls0
{

    final tPaddingTop this$0;

    public View getChildAt(int i)
    {
        return tChildAt(i);
    }

    public int getChildCount()
    {
        return tChildCount();
    }

    public int getChildEnd(View view)
    {
        tChildCount tchildcount = (tChildCount)view.getLayoutParams();
        return tDecoratedBottom(view) + tchildcount.tomMargin;
    }

    public int getChildStart(View view)
    {
        tomMargin tommargin = (tomMargin)view.getLayoutParams();
        return tDecoratedTop(view) - tommargin.Margin;
    }

    public View getParent()
    {
        return ecyclerView;
    }

    public int getParentEnd()
    {
        return tHeight() - tPaddingBottom();
    }

    public int getParentStart()
    {
        return tPaddingTop();
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
