// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorCompat, ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener

static interface 
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
