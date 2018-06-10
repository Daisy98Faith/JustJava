// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.ViewPropertyAnimator;

// Referenced classes of package android.support.v7.widget:
//            ActionBarOverlayLayout, ActionBarContainer

class this._cls0
    implements Runnable
{

    final ActionBarOverlayLayout this$0;

    public void run()
    {
        haltActionBarHideOffsetAnimations();
        mCurrentActionBarTopAnimator = mActionBarTop.animate().translationY(0.0F).setListener(mTopAnimatorListener);
    }

    ()
    {
        this$0 = ActionBarOverlayLayout.this;
        super();
    }
}
