// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener, ViewPropertyAnimatorCompatJB, ViewPropertyAnimatorCompatICS, 
//            ViewPropertyAnimatorCompatKK

public final class ViewPropertyAnimatorCompat
{
    static class ViewPropertyAnimatorCompatApi16Impl extends ViewPropertyAnimatorCompatBaseImpl
    {

        public void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
        {
            ViewPropertyAnimatorCompatJB.setListener(view, viewpropertyanimatorlistener);
        }

        public void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            view.animate().withEndAction(runnable);
        }

        public void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            view.animate().withLayer();
        }

        public void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            view.animate().withStartAction(runnable);
        }

        ViewPropertyAnimatorCompatApi16Impl()
        {
        }
    }

    static class ViewPropertyAnimatorCompatApi18Impl extends ViewPropertyAnimatorCompatApi16Impl
    {

        public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return (Interpolator)view.animate().getInterpolator();
        }

        ViewPropertyAnimatorCompatApi18Impl()
        {
        }
    }

    static class ViewPropertyAnimatorCompatApi19Impl extends ViewPropertyAnimatorCompatApi18Impl
    {

        public void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
        {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, viewpropertyanimatorupdatelistener);
        }

        ViewPropertyAnimatorCompatApi19Impl()
        {
        }
    }

    static class ViewPropertyAnimatorCompatApi21Impl extends ViewPropertyAnimatorCompatApi19Impl
    {

        public void translationZ(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            view.animate().translationZ(f);
        }

        public void translationZBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            view.animate().translationZBy(f);
        }

        public void z(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            view.animate().z(f);
        }

        public void zBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            view.animate().zBy(f);
        }

        ViewPropertyAnimatorCompatApi21Impl()
        {
        }
    }

    static class ViewPropertyAnimatorCompatBaseImpl
        implements ViewPropertyAnimatorCompatImpl
    {

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

        ViewPropertyAnimatorCompatBaseImpl()
        {
        }
    }

    static class ViewPropertyAnimatorCompatBaseImpl.MyVpaListener
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

        ViewPropertyAnimatorCompatBaseImpl.MyVpaListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
        {
            mVpa = viewpropertyanimatorcompat;
        }
    }

    static interface ViewPropertyAnimatorCompatImpl
    {

        public abstract Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener);

        public abstract void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener);

        public abstract void translationZ(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void translationZBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable);

        public abstract void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable);

        public abstract void z(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void zBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);
    }


    static final ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 0x7e000000;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
    private WeakReference mView;

    ViewPropertyAnimatorCompat(View view)
    {
        mStartAction = null;
        mEndAction = null;
        mOldLayerType = -1;
        mView = new WeakReference(view);
    }

    public ViewPropertyAnimatorCompat alpha(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().alpha(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().alphaBy(f);
        }
        return this;
    }

    public void cancel()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().cancel();
        }
    }

    public long getDuration()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            return view.animate().getDuration();
        } else
        {
            return 0L;
        }
    }

    public Interpolator getInterpolator()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            return IMPL.getInterpolator(this, view);
        } else
        {
            return null;
        }
    }

    public long getStartDelay()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            return view.animate().getStartDelay();
        } else
        {
            return 0L;
        }
    }

    public ViewPropertyAnimatorCompat rotation(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotation(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotationBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().rotationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().scaleX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().scaleXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().scaleY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().scaleYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long l)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().setDuration(l);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.setListener(this, view, viewpropertyanimatorlistener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long l)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().setStartDelay(l);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.setUpdateListener(this, view, viewpropertyanimatorupdatelistener);
        }
        return this;
    }

    public void start()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationX(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().translationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().translationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().translationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().translationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.translationZ(this, view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.translationZBy(this, view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.withEndAction(this, view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer()
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.withLayer(this, view);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.withStartAction(this, view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat x(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().x(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().xBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().y(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            view.animate().yBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat z(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.z(this, view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f)
    {
        View view = (View)mView.get();
        if (view != null)
        {
            IMPL.zBy(this, view, f);
        }
        return this;
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            IMPL = new ViewPropertyAnimatorCompatApi21Impl();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            IMPL = new ViewPropertyAnimatorCompatApi19Impl();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 18)
        {
            IMPL = new ViewPropertyAnimatorCompatApi18Impl();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            IMPL = new ViewPropertyAnimatorCompatApi16Impl();
        } else
        {
            IMPL = new ViewPropertyAnimatorCompatBaseImpl();
        }
    }
}
