// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            CollapsingToolbarLayout, MathUtils, ViewOffsetHelper, CollapsingTextHelper, 
//            AppBarLayout

private class this._cls0
    implements this._cls0
{

    final CollapsingToolbarLayout this$0;

    public void onOffsetChanged(AppBarLayout appbarlayout, int i)
    {
        this._cls0 _lcls0;
        ViewOffsetHelper viewoffsethelper;
        int j;
        mCurrentOffset = i;
        int k;
        int i1;
        if (mLastInsets != null)
        {
            j = mLastInsets.getSystemWindowInsetTop();
        } else
        {
            j = 0;
        }
        k = 0;
        i1 = getChildCount();
        if (k >= i1)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        appbarlayout = getChildAt(k);
        _lcls0 = (this._cls0)appbarlayout.getLayoutParams();
        viewoffsethelper = CollapsingToolbarLayout.getViewOffsetHelper(appbarlayout);
        switch (_lcls0.eMode)
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_138;
_L4:
        k++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_42;
_L1:
        viewoffsethelper.setTopAndBottomOffset(MathUtils.constrain(-i, 0, getMaxOffsetForPinChild(appbarlayout)));
        continue; /* Loop/switch isn't completed */
        viewoffsethelper.setTopAndBottomOffset(Math.round((float)(-i) * _lcls0.xMult));
        if (true) goto _L4; else goto _L3
_L3:
        updateScrimVisibility();
        if (mStatusBarScrim != null && j > 0)
        {
            ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
        }
        int l = getHeight();
        int j1 = ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this);
        mCollapsingTextHelper.setExpansionFraction((float)Math.abs(i) / (float)(l - j1 - j));
        return;
    }

    ()
    {
        this$0 = CollapsingToolbarLayout.this;
        super();
    }
}
