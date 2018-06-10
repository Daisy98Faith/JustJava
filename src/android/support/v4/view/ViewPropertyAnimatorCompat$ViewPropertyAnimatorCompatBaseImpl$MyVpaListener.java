// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorCompat

static class mVpa
    implements ViewPropertyAnimatorListener
{

    boolean mAnimEndCalled;
    ViewPropertyAnimatorCompat mVpa;

    public void onAnimationCancel(View view)
    {
        Object obj = view.getTag(0x7e000000);
        ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
        if (obj instanceof ViewPropertyAnimatorListener)
        {
            viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
        }
        if (viewpropertyanimatorlistener != null)
        {
            viewpropertyanimatorlistener.onAnimationCancel(view);
        }
    }

    public void onAnimationEnd(View view)
    {
        if (mVpa.mOldLayerType >= 0)
        {
            view.setLayerType(mVpa.mOldLayerType, null);
            mVpa.mOldLayerType = -1;
        }
        if (android.os.yVpaListener.mVpa >= 16 || !mAnimEndCalled)
        {
            if (mVpa.mEndAction != null)
            {
                Runnable runnable = mVpa.mEndAction;
                mVpa.mEndAction = null;
                runnable.run();
            }
            Object obj = view.getTag(0x7e000000);
            ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
            if (obj instanceof ViewPropertyAnimatorListener)
            {
                viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
            }
            if (viewpropertyanimatorlistener != null)
            {
                viewpropertyanimatorlistener.onAnimationEnd(view);
            }
            mAnimEndCalled = true;
        }
    }

    public void onAnimationStart(View view)
    {
        mAnimEndCalled = false;
        if (mVpa.mOldLayerType >= 0)
        {
            view.setLayerType(2, null);
        }
        if (mVpa.mStartAction != null)
        {
            Runnable runnable = mVpa.mStartAction;
            mVpa.mStartAction = null;
            runnable.run();
        }
        Object obj = view.getTag(0x7e000000);
        ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
        if (obj instanceof ViewPropertyAnimatorListener)
        {
            viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
        }
        if (viewpropertyanimatorlistener != null)
        {
            viewpropertyanimatorlistener.onAnimationStart(view);
        }
    }

    i(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
    {
        mVpa = viewpropertyanimatorcompat;
    }
}
