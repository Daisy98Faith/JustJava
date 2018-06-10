// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.os.BuildCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation;

// Referenced classes of package android.support.v4.app:
//            FragmentManagerImpl

static class mShouldRunOnHWLayer
    implements android.view.animation.mShouldRunOnHWLayer
{

    private android.view.animation.mOriginalListener mOriginalListener;
    private boolean mShouldRunOnHWLayer;
    View mView;

    public void onAnimationEnd(Animation animation)
    {
        if (mView != null && mShouldRunOnHWLayer)
        {
            if (ViewCompat.isAttachedToWindow(mView) || BuildCompat.isAtLeastN())
            {
                mView.post(new Runnable() {

                    final FragmentManagerImpl.AnimateOnHWLayerIfNeededListener this$0;

                    public void run()
                    {
                        mView.setLayerType(0, null);
                    }

            
            {
                this$0 = FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this;
                super();
            }
                });
            } else
            {
                mView.setLayerType(0, null);
            }
        }
        if (mOriginalListener != null)
        {
            mOriginalListener.mOriginalListener(animation);
        }
    }

    public void onAnimationRepeat(Animation animation)
    {
        if (mOriginalListener != null)
        {
            mOriginalListener.mOriginalListener(animation);
        }
    }

    public void onAnimationStart(Animation animation)
    {
        if (mOriginalListener != null)
        {
            mOriginalListener.mOriginalListener(animation);
        }
    }

    public _cls1.this._cls0(View view, Animation animation)
    {
        if (view == null || animation == null)
        {
            return;
        } else
        {
            mView = view;
            return;
        }
    }

    public mView(View view, Animation animation, android.view.animation.mView mview)
    {
        if (view == null || animation == null)
        {
            return;
        } else
        {
            mOriginalListener = mview;
            mView = view;
            mShouldRunOnHWLayer = true;
            return;
        }
    }
}
