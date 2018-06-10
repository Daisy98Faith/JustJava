// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Visibility, TransitionValues, ViewGroupUtils, ViewGroupOverlayImpl, 
//            Transition

public class Fade extends Visibility
{

    public static final int IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    private static final String PROPNAME_SCREEN_X = "android:fade:screenX";
    private static final String PROPNAME_SCREEN_Y = "android:fade:screenY";
    private int mFadingMode;

    public Fade()
    {
        this(3);
    }

    public Fade(int i)
    {
        mFadingMode = i;
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        int ai[] = new int[2];
        transitionvalues.view.getLocationOnScreen(ai);
        transitionvalues.values.put("android:fade:screenX", Integer.valueOf(ai[0]));
        transitionvalues.values.put("android:fade:screenY", Integer.valueOf(ai[1]));
    }

    private Animator createAnimation(View view, float f, float f1, AnimatorListenerAdapter animatorlisteneradapter)
    {
        Object obj = null;
        if (f == f1)
        {
            view = obj;
            if (animatorlisteneradapter != null)
            {
                animatorlisteneradapter.onAnimationEnd(null);
                view = obj;
            }
        } else
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "alpha", new float[] {
                f, f1
            });
            view = objectanimator;
            if (animatorlisteneradapter != null)
            {
                objectanimator.addListener(animatorlisteneradapter);
                return objectanimator;
            }
        }
        return view;
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        super.captureStartValues(transitionvalues);
        captureValues(transitionvalues);
    }

    public Animator onAppear(final ViewGroup endView, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        if ((mFadingMode & 1) != 1 || transitionvalues1 == null)
        {
            return null;
        } else
        {
            endView = transitionvalues1.view;
            endView.setAlpha(0.0F);
            addListener(new Transition.TransitionListenerAdapter() {

                boolean mCanceled;
                float mPausedAlpha;
                final Fade this$0;
                final View val$endView;

                public void onTransitionCancel(Transition transition)
                {
                    endView.setAlpha(1.0F);
                    mCanceled = true;
                }

                public void onTransitionEnd(Transition transition)
                {
                    if (!mCanceled)
                    {
                        endView.setAlpha(1.0F);
                    }
                }

                public void onTransitionPause(Transition transition)
                {
                    mPausedAlpha = endView.getAlpha();
                    endView.setAlpha(1.0F);
                }

                public void onTransitionResume(Transition transition)
                {
                    endView.setAlpha(mPausedAlpha);
                }

            
            {
                this$0 = Fade.this;
                endView = view;
                super();
                mCanceled = false;
            }
            });
            return createAnimation(endView, 0.0F, 1.0F, null);
        }
    }

    public Animator onDisappear(final ViewGroup finalSceneRoot, TransitionValues transitionvalues, int i, final TransitionValues finalView, final int finalVisibility)
    {
        View view;
        final Object finalOverlayView;
        final Object finalViewToKeep;
        Object obj;
        Object obj1;
        Object obj2;
        if ((mFadingMode & 2) != 2)
        {
            return null;
        }
        obj1 = null;
        int j;
        if (transitionvalues != null)
        {
            view = transitionvalues.view;
        } else
        {
            view = null;
        }
        if (finalView != null)
        {
            finalView = finalView.view;
        } else
        {
            finalView = null;
        }
        obj2 = null;
        obj = null;
        if (finalView != null && finalView.getParent() != null) goto _L2; else goto _L1
_L1:
        if (finalView == null) goto _L4; else goto _L3
_L3:
        finalOverlayView = finalView;
        finalViewToKeep = obj;
_L6:
        if (finalOverlayView != null)
        {
            i = ((Integer)transitionvalues.values.get("android:fade:screenX")).intValue();
            j = ((Integer)transitionvalues.values.get("android:fade:screenY")).intValue();
            transitionvalues = new int[2];
            finalSceneRoot.getLocationOnScreen(transitionvalues);
            ViewCompat.offsetLeftAndRight(((View) (finalOverlayView)), i - transitionvalues[0] - ((View) (finalOverlayView)).getLeft());
            ViewCompat.offsetTopAndBottom(((View) (finalOverlayView)), j - transitionvalues[1] - ((View) (finalOverlayView)).getTop());
            ViewGroupUtils.getOverlay(finalSceneRoot).add(((View) (finalOverlayView)));
            return createAnimation(finalView, 1.0F, 0.0F, new AnimatorListenerAdapter() {

                final Fade this$0;
                final View val$finalOverlayView;
                final ViewGroup val$finalSceneRoot;
                final View val$finalView;
                final View val$finalViewToKeep;
                final int val$finalVisibility;

                public void onAnimationEnd(Animator animator)
                {
                    finalView.setAlpha(1.0F);
                    if (finalViewToKeep != null)
                    {
                        finalViewToKeep.setVisibility(finalVisibility);
                    }
                    if (finalOverlayView != null)
                    {
                        ViewGroupUtils.getOverlay(finalSceneRoot).remove(finalOverlayView);
                    }
                }

            
            {
                this$0 = Fade.this;
                finalView = view;
                finalViewToKeep = view1;
                finalVisibility = i;
                finalOverlayView = view2;
                finalSceneRoot = viewgroup;
                super();
            }
            });
        }
        break; /* Loop/switch isn't completed */
_L4:
        finalOverlayView = obj2;
        finalView = obj1;
        finalViewToKeep = obj;
        if (view != null)
        {
            if (view.getParent() == null)
            {
                finalOverlayView = view;
                finalView = view;
                finalViewToKeep = obj;
            } else
            {
                finalOverlayView = obj2;
                finalView = obj1;
                finalViewToKeep = obj;
                if (view.getParent() instanceof View)
                {
                    finalOverlayView = obj2;
                    finalView = obj1;
                    finalViewToKeep = obj;
                    if (view.getParent().getParent() == null)
                    {
                        i = ((View)view.getParent()).getId();
                        finalOverlayView = obj2;
                        finalView = obj1;
                        finalViewToKeep = obj;
                        if (i != -1)
                        {
                            finalOverlayView = obj2;
                            finalView = obj1;
                            finalViewToKeep = obj;
                            if (finalSceneRoot.findViewById(i) != null)
                            {
                                finalOverlayView = obj2;
                                finalView = obj1;
                                finalViewToKeep = obj;
                                if (mCanRemoveViews)
                                {
                                    finalOverlayView = view;
                                    finalView = view;
                                    finalViewToKeep = obj;
                                }
                            }
                        }
                    }
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (finalVisibility == 4)
        {
            finalViewToKeep = finalView;
            finalOverlayView = obj2;
        } else
        if (view == finalView)
        {
            finalViewToKeep = finalView;
            finalOverlayView = obj2;
        } else
        {
            finalView = view;
            finalOverlayView = finalView;
            finalViewToKeep = obj;
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (finalViewToKeep != null)
        {
            ((View) (finalViewToKeep)).setVisibility(0);
            return createAnimation(finalView, 1.0F, 0.0F, new AnimatorListenerAdapter() {

                boolean mCanceled;
                float mPausedAlpha;
                final Fade this$0;
                final View val$finalOverlayView;
                final ViewGroup val$finalSceneRoot;
                final View val$finalView;
                final View val$finalViewToKeep;
                final int val$finalVisibility;

                public void onAnimationCancel(Animator animator)
                {
                    mCanceled = true;
                    if (mPausedAlpha >= 0.0F)
                    {
                        finalView.setAlpha(mPausedAlpha);
                    }
                }

                public void onAnimationEnd(Animator animator)
                {
                    if (!mCanceled)
                    {
                        finalView.setAlpha(1.0F);
                    }
                    if (finalViewToKeep != null && !mCanceled)
                    {
                        finalViewToKeep.setVisibility(finalVisibility);
                    }
                    if (finalOverlayView != null)
                    {
                        ViewGroupUtils.getOverlay(finalSceneRoot).remove(finalOverlayView);
                    }
                }

            
            {
                this$0 = Fade.this;
                finalView = view;
                finalViewToKeep = view1;
                finalVisibility = i;
                finalOverlayView = view2;
                finalSceneRoot = viewgroup;
                super();
                mCanceled = false;
                mPausedAlpha = -1F;
            }
            });
        } else
        {
            return null;
        }
    }
}
