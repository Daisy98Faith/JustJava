// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.view.ViewCompat;

// Referenced classes of package android.support.v4.widget:
//            AutoScrollHelper

private class this._cls0
    implements Runnable
{

    final AutoScrollHelper this$0;

    public void run()
    {
        if (!mAnimating)
        {
            return;
        }
        if (mNeedsReset)
        {
            mNeedsReset = false;
            mScroller._mth0();
        }
        this._cls0 _lcls0 = mScroller;
        if (_lcls0.ed() || !shouldAnimate())
        {
            mAnimating = false;
            return;
        }
        if (mNeedsCancel)
        {
            mNeedsCancel = false;
            cancelTargetTouch();
        }
        _lcls0.crollDelta();
        int i = _lcls0.X();
        int j = _lcls0.Y();
        scrollTargetBy(i, j);
        ViewCompat.postOnAnimation(mTarget, this);
    }

    ()
    {
        this$0 = AutoScrollHelper.this;
        super();
    }
}
