// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Transition, TransitionValues

public abstract class Visibility extends Transition
{
    private static class VisibilityInfo
    {

        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        private VisibilityInfo()
        {
        }

    }


    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String sTransitionProperties[] = {
        "android:visibility:visibility", "android:visibility:parent"
    };

    public Visibility()
    {
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getVisibility();
        transitionvalues.values.put("android:visibility:visibility", Integer.valueOf(i));
        transitionvalues.values.put("android:visibility:parent", transitionvalues.view.getParent());
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
        visibilityinfo = new VisibilityInfo();
        visibilityinfo.mVisibilityChange = false;
        visibilityinfo.mFadeIn = false;
        if (transitionvalues != null)
        {
            visibilityinfo.mStartVisibility = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.mStartParent = (ViewGroup)transitionvalues.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.mStartVisibility = -1;
            visibilityinfo.mStartParent = null;
        }
        if (transitionvalues1 != null)
        {
            visibilityinfo.mEndVisibility = ((Integer)transitionvalues1.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.mEndParent = (ViewGroup)transitionvalues1.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.mEndVisibility = -1;
            visibilityinfo.mEndParent = null;
        }
        if (transitionvalues == null || transitionvalues1 == null) goto _L2; else goto _L1
_L1:
        if (visibilityinfo.mStartVisibility != visibilityinfo.mEndVisibility || visibilityinfo.mStartParent != visibilityinfo.mEndParent) goto _L4; else goto _L3
_L3:
        return visibilityinfo;
_L4:
        if (visibilityinfo.mStartVisibility == visibilityinfo.mEndVisibility) goto _L6; else goto _L5
_L5:
        if (visibilityinfo.mStartVisibility != 0) goto _L8; else goto _L7
_L7:
        visibilityinfo.mFadeIn = false;
        visibilityinfo.mVisibilityChange = true;
_L2:
        if (transitionvalues == null)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
            return visibilityinfo;
        }
        break; /* Loop/switch isn't completed */
_L8:
        if (visibilityinfo.mEndVisibility == 0)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (visibilityinfo.mEndParent == null)
        {
            visibilityinfo.mFadeIn = false;
            visibilityinfo.mVisibilityChange = true;
        } else
        if (visibilityinfo.mStartParent == null)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
        }
        if (true) goto _L2; else goto _L9
_L9:
        if (transitionvalues1 != null) goto _L3; else goto _L10
_L10:
        visibilityinfo.mFadeIn = false;
        visibilityinfo.mVisibilityChange = true;
        return visibilityinfo;
    }

    boolean areValuesChanged(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if (transitionvalues != null || transitionvalues1 != null)
        {
            if (((VisibilityInfo) (transitionvalues = getVisibilityChangeInfo(transitionvalues, transitionvalues1))).mVisibilityChange && (((VisibilityInfo) (transitionvalues)).mStartVisibility == 0 || ((VisibilityInfo) (transitionvalues)).mEndVisibility == 0))
            {
                return true;
            }
        }
        return false;
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
label0:
        {
label1:
            {
                Object obj1 = null;
                visibilityinfo = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
                Object obj = obj1;
                if (!visibilityinfo.mVisibilityChange)
                {
                    break label1;
                }
                boolean flag = false;
                if (mTargets.size() > 0 || mTargetIds.size() > 0)
                {
                    View view;
                    if (transitionvalues != null)
                    {
                        obj = transitionvalues.view;
                    } else
                    {
                        obj = null;
                    }
                    if (transitionvalues1 != null)
                    {
                        view = transitionvalues1.view;
                    } else
                    {
                        view = null;
                    }
                    if (isValidTarget(((View) (obj))) || isValidTarget(view))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                }
                if (!flag && visibilityinfo.mStartParent == null)
                {
                    obj = obj1;
                    if (visibilityinfo.mEndParent == null)
                    {
                        break label1;
                    }
                }
                if (!visibilityinfo.mFadeIn)
                {
                    break label0;
                }
                obj = onAppear(viewgroup, transitionvalues, visibilityinfo.mStartVisibility, transitionvalues1, visibilityinfo.mEndVisibility);
            }
            return ((Animator) (obj));
        }
        return onDisappear(viewgroup, transitionvalues, visibilityinfo.mStartVisibility, transitionvalues1, visibilityinfo.mEndVisibility);
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        if (transitionvalues == null)
        {
            return false;
        }
        int i = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
        transitionvalues = (View)transitionvalues.values.get("android:visibility:parent");
        boolean flag;
        if (i == 0 && transitionvalues != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return null;
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return null;
    }

}
