// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorCompat, ViewPropertyAnimatorCompatICS, ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener

static class MyVpaListener.mVpa
    implements MyVpaListener.mVpa
{
    static class MyVpaListener
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
            if (android.os.Build.VERSION.SDK_INT >= 16 || !mAnimEndCalled)
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

        MyVpaListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
        {
            mVpa = viewpropertyanimatorcompat;
        }
    }


    public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
    {
        return null;
    }

    public void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        view.setTag(0x7e000000, viewpropertyanimatorlistener);
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
    }

    public void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
    {
    }

    public void translationZ(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
    {
    }

    public void translationZBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
    {
    }

    public void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
    {
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
        viewpropertyanimatorcompat.mEndAction = runnable;
    }

    public void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
    {
        viewpropertyanimatorcompat.mOldLayerType = view.getLayerType();
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
    }

    public void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
    {
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
        viewpropertyanimatorcompat.mStartAction = runnable;
    }

    public void z(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
    {
    }

    public void zBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
    {
    }

    MyVpaListener.mVpa()
    {
    }
}
