// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV9

class this._cls0
    implements Runnable
{

    final AppCompatDelegateImplV9 this$0;

    public void run()
    {
        mActionModePopup.showAtLocation(mActionModeView, 55, 0, 0);
        endOnGoingFadeAnimation();
        if (shouldAnimateActionModeView())
        {
            mActionModeView.setAlpha(0.0F);
            mFadeAnim = ViewCompat.animate(mActionModeView).alpha(1.0F);
            mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {

                final AppCompatDelegateImplV9._cls5 this$1;

                public void onAnimationEnd(View view)
                {
                    mActionModeView.setAlpha(1.0F);
                    mFadeAnim.setListener(null);
                    mFadeAnim = null;
                }

                public void onAnimationStart(View view)
                {
                    mActionModeView.setVisibility(0);
                }

            
            {
                this$1 = AppCompatDelegateImplV9._cls5.this;
                super();
            }
            });
            return;
        } else
        {
            mActionModeView.setAlpha(1.0F);
            mActionModeView.setVisibility(0);
            return;
        }
    }

    nerAdapter()
    {
        this$0 = AppCompatDelegateImplV9.this;
        super();
    }
}
