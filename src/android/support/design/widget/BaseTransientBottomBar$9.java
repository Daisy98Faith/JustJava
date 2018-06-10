// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.animation.Animation;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls0
    implements android.view.animation.er
{

    final BaseTransientBottomBar this$0;

    public void onAnimationEnd(Animation animation)
    {
        onViewShown();
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
    }

    ()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
