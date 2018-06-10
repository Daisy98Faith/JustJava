// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

public class TextScale extends Transition
{

    private static final String PROPNAME_SCALE = "android:textscale:scale";

    public TextScale()
    {
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        if (transitionvalues.view instanceof TextView)
        {
            TextView textview = (TextView)transitionvalues.view;
            transitionvalues.values.put("android:textscale:scale", Float.valueOf(textview.getScaleX()));
        }
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(final ViewGroup view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if (transitionvalues != null && transitionvalues1 != null && (transitionvalues.view instanceof TextView) && (transitionvalues1.view instanceof TextView))
        {
            view = (TextView)transitionvalues1.view;
            transitionvalues = transitionvalues.values;
            transitionvalues1 = transitionvalues1.values;
            float f;
            float f1;
            if (transitionvalues.get("android:textscale:scale") != null)
            {
                f = ((Float)transitionvalues.get("android:textscale:scale")).floatValue();
            } else
            {
                f = 1.0F;
            }
            if (transitionvalues1.get("android:textscale:scale") != null)
            {
                f1 = ((Float)transitionvalues1.get("android:textscale:scale")).floatValue();
            } else
            {
                f1 = 1.0F;
            }
            if (f != f1)
            {
                transitionvalues = ValueAnimator.ofFloat(new float[] {
                    f, f1
                });
                transitionvalues.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

                    final TextScale this$0;
                    final TextView val$view;

                    public void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        float f2 = ((Float)valueanimator.getAnimatedValue()).floatValue();
                        view.setScaleX(f2);
                        view.setScaleY(f2);
                    }

            
            {
                this$0 = TextScale.this;
                view = textview;
                super();
            }
                });
                return transitionvalues;
            }
        }
        return null;
    }
}
