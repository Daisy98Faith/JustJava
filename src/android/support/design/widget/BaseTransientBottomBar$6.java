// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls0
    implements LayoutChangeListener
{

    final BaseTransientBottomBar this$0;

    public void onLayoutChange(View view, int i, int j, int k, int l)
    {
        mView.setOnLayoutChangeListener(null);
        if (shouldAnimate())
        {
            animateViewIn();
            return;
        } else
        {
            onViewShown();
            return;
        }
    }

    ackbarBaseLayout()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
