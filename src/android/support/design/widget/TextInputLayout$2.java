// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

// Referenced classes of package android.support.design.widget:
//            TextInputLayout

class  extends AnimatorListenerAdapter
{

    final TextInputLayout this$0;

    public void onAnimationStart(Animator animator)
    {
        mErrorView.setVisibility(0);
    }

    ()
    {
        this$0 = TextInputLayout.this;
        super();
    }
}
