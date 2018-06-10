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
    final CharSequence val$error;

    public void onAnimationEnd(Animator animator)
    {
        mErrorView.setText(val$error);
        mErrorView.setVisibility(4);
    }

    ()
    {
        this$0 = final_textinputlayout;
        val$error = CharSequence.this;
        super();
    }
}
