// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

class ViewUtilsLollipop
{

    private static final int STATE_LIST_ANIM_ATTRS[] = {
        0x1010448
    };

    ViewUtilsLollipop()
    {
    }

    static void setBoundsViewOutlineProvider(View view)
    {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void setDefaultAppBarLayoutStateListAnimator(View view, float f)
    {
        int i = view.getResources().getInteger(android.support.design.R.integer.app_bar_elevation_anim_duration);
        StateListAnimator statelistanimator = new StateListAnimator();
        int j = android.support.design.R.attr.state_collapsible;
        int k = -android.support.design.R.attr.state_collapsed;
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            0.0F
        }).setDuration(i);
        statelistanimator.addState(new int[] {
            0x101000e, j, k
        }, objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            f
        }).setDuration(i);
        statelistanimator.addState(new int[] {
            0x101000e
        }, objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            0.0F
        }).setDuration(0L);
        statelistanimator.addState(new int[0], objectanimator);
        view.setStateListAnimator(statelistanimator);
    }

    static void setStateListAnimatorFromAttrs(View view, AttributeSet attributeset, int i, int j)
    {
        Context context;
        context = view.getContext();
        attributeset = context.obtainStyledAttributes(attributeset, STATE_LIST_ANIM_ATTRS, i, j);
        if (attributeset.hasValue(0))
        {
            view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, attributeset.getResourceId(0, 0)));
        }
        attributeset.recycle();
        return;
        view;
        attributeset.recycle();
        throw view;
    }

}
