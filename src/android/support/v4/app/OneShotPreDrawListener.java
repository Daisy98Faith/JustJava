// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import android.view.ViewTreeObserver;

class OneShotPreDrawListener
    implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
{

    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    private OneShotPreDrawListener(View view, Runnable runnable)
    {
        mView = view;
        mViewTreeObserver = view.getViewTreeObserver();
        mRunnable = runnable;
    }

    public static OneShotPreDrawListener add(View view, Runnable runnable)
    {
        runnable = new OneShotPreDrawListener(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(runnable);
        view.addOnAttachStateChangeListener(runnable);
        return runnable;
    }

    public boolean onPreDraw()
    {
        removeListener();
        mRunnable.run();
        return true;
    }

    public void onViewAttachedToWindow(View view)
    {
        mViewTreeObserver = view.getViewTreeObserver();
    }

    public void onViewDetachedFromWindow(View view)
    {
        removeListener();
    }

    public void removeListener()
    {
        if (mViewTreeObserver.isAlive())
        {
            mViewTreeObserver.removeOnPreDrawListener(this);
        } else
        {
            mView.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        mView.removeOnAttachStateChangeListener(this);
    }
}
