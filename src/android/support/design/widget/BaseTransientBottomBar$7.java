// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls0 extends AnimatorListenerAdapter
{

    final BaseTransientBottomBar this$0;

    public void onAnimationEnd(Animator animator)
    {
        onViewShown();
    }

    public void onAnimationStart(Animator animator)
    {
        BaseTransientBottomBar.access$000(BaseTransientBottomBar.this).animateContentIn(70, 180);
    }

    ntentViewCallback()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
