// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ChangeBounds, ViewUtils, ViewOverlayImpl

class <init> extends AnimatorListenerAdapter
{

    final ChangeBounds this$0;
    final BitmapDrawable val$drawable;
    final ViewGroup val$sceneRoot;
    final View val$view;

    public void onAnimationEnd(Animator animator)
    {
        ViewUtils.getOverlay(val$sceneRoot).remove(val$drawable);
        val$view.setVisibility(0);
    }

    ()
    {
        this$0 = final_changebounds;
        val$sceneRoot = viewgroup;
        val$drawable = bitmapdrawable;
        val$view = View.this;
        super();
    }
}
