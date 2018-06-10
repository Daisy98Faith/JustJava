// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Transition, RectEvaluator, TransitionValues, ViewGroupUtils, 
//            ViewUtils, ViewOverlayImpl

public class ChangeBounds extends Transition
{

    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String sTransitionProperties[] = {
        "android:changeBounds:bounds", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"
    };
    boolean mReparent;
    boolean mResizeClip;
    int mTempLocation[];

    public ChangeBounds()
    {
        mTempLocation = new int[2];
        mResizeClip = false;
        mReparent = false;
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        transitionvalues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionvalues.values.put("android:changeBounds:parent", transitionvalues.view.getParent());
        transitionvalues.view.getLocationInWindow(mTempLocation);
        transitionvalues.values.put("android:changeBounds:windowX", Integer.valueOf(mTempLocation[0]));
        transitionvalues.values.put("android:changeBounds:windowY", Integer.valueOf(mTempLocation[1]));
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(final ViewGroup parent, final TransitionValues parent, TransitionValues transitionvalues)
    {
        if (parent != null && transitionvalues != null) goto _L2; else goto _L1
_L1:
        parent = null;
_L5:
        return parent;
_L2:
        final View view;
        int j1;
        int k1;
        int l1;
        int i2;
        int k3;
        int l3;
        int i4;
        int j4;
        Object obj = parent.values;
        view = transitionvalues.values;
        obj = (ViewGroup)((Map) (obj)).get("android:changeBounds:parent");
        ViewGroup viewgroup = (ViewGroup)view.get("android:changeBounds:parent");
        if (obj == null || viewgroup == null)
        {
            return null;
        }
        view = transitionvalues.view;
        int i;
        int k;
        boolean flag;
        int j2;
        int l2;
        int i3;
        int j3;
        if (obj == viewgroup || ((ViewGroup) (obj)).getId() == viewgroup.getId())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (mReparent && i == 0)
        {
            break MISSING_BLOCK_LABEL_918;
        }
        parent = (Rect)parent.values.get("android:changeBounds:bounds");
        parent = (Rect)transitionvalues.values.get("android:changeBounds:bounds");
        i4 = ((Rect) (parent)).left;
        j4 = ((Rect) (parent)).left;
        k3 = ((Rect) (parent)).top;
        l3 = ((Rect) (parent)).top;
        i3 = ((Rect) (parent)).right;
        j3 = ((Rect) (parent)).right;
        j2 = ((Rect) (parent)).bottom;
        l2 = ((Rect) (parent)).bottom;
        j1 = i3 - i4;
        k1 = j2 - k3;
        l1 = j3 - j4;
        i2 = l2 - l3;
        flag = false;
        k = 0;
        i = ((flag) ? 1 : 0);
        if (j1 != 0)
        {
            i = ((flag) ? 1 : 0);
            if (k1 != 0)
            {
                i = ((flag) ? 1 : 0);
                if (l1 != 0)
                {
                    i = ((flag) ? 1 : 0);
                    if (i2 != 0)
                    {
                        if (i4 != j4)
                        {
                            k = 0 + 1;
                        }
                        i = k;
                        if (k3 != l3)
                        {
                            i = k + 1;
                        }
                        k = i;
                        if (i3 != j3)
                        {
                            k = i + 1;
                        }
                        i = k;
                        if (j2 != l2)
                        {
                            i = k + 1;
                        }
                    }
                }
            }
        }
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_1226;
        }
        if (mResizeClip) goto _L4; else goto _L3
_L3:
        parent = new PropertyValuesHolder[i];
        if (i4 != j4)
        {
            view.setLeft(i4);
        }
        if (k3 != l3)
        {
            view.setTop(k3);
        }
        if (i3 != j3)
        {
            view.setRight(i3);
        }
        if (j2 != l2)
        {
            view.setBottom(j2);
        }
        float f;
        float f1;
        Rect rect;
        int j;
        int l;
        int i1;
        int k2;
        if (i4 != j4)
        {
            l = 0 + 1;
            parent[0] = PropertyValuesHolder.ofInt("left", new int[] {
                i4, j4
            });
        } else
        {
            l = 0;
        }
        i = l;
        if (k3 != l3)
        {
            parent[l] = PropertyValuesHolder.ofInt("top", new int[] {
                k3, l3
            });
            i = l + 1;
        }
        l = i;
        if (i3 != j3)
        {
            parent[i] = PropertyValuesHolder.ofInt("right", new int[] {
                i3, j3
            });
            l = i + 1;
        }
        if (j2 != l2)
        {
            parent[l] = PropertyValuesHolder.ofInt("bottom", new int[] {
                j2, l2
            });
        }
        parent = ObjectAnimator.ofPropertyValuesHolder(view, parent);
        parent = parent;
        if (view.getParent() instanceof ViewGroup)
        {
            parent = (ViewGroup)view.getParent();
            ViewGroupUtils.suppressLayout(parent, true);
            addListener(new Transition.TransitionListenerAdapter() {

                boolean mCanceled;
                final ChangeBounds this$0;
                final ViewGroup val$parent;

                public void onTransitionCancel(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, false);
                    mCanceled = true;
                }

                public void onTransitionEnd(Transition transition)
                {
                    if (!mCanceled)
                    {
                        ViewGroupUtils.suppressLayout(parent, false);
                    }
                }

                public void onTransitionPause(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, false);
                }

                public void onTransitionResume(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, true);
                }

            
            {
                this$0 = ChangeBounds.this;
                parent = viewgroup;
                super();
                mCanceled = false;
            }
            });
            return parent;
        }
        if (true) goto _L5; else goto _L4
_L4:
label0:
        {
            if (j1 != l1)
            {
                view.setRight(Math.max(j1, l1) + j4);
            }
            if (k1 != i2)
            {
                view.setBottom(Math.max(k1, i2) + l3);
            }
            if (i4 != j4)
            {
                view.setTranslationX(i4 - j4);
            }
            if (k3 != l3)
            {
                view.setTranslationY(k3 - l3);
            }
            f = j4 - i4;
            f1 = l3 - k3;
            i1 = l1 - j1;
            k2 = i2 - k1;
            l = 0;
            if (f != 0.0F)
            {
                l = 0 + 1;
            }
            j = l;
            if (f1 != 0.0F)
            {
                j = l + 1;
            }
            if (i1 == 0)
            {
                l = j;
                if (k2 == 0)
                {
                    break label0;
                }
            }
            l = j + 1;
        }
        parent = new PropertyValuesHolder[l];
        if (f != 0.0F)
        {
            j = 0 + 1;
            parent[0] = PropertyValuesHolder.ofFloat("translationX", new float[] {
                view.getTranslationX(), 0.0F
            });
        } else
        {
            j = 0;
        }
        if (f1 != 0.0F)
        {
            parent[j] = PropertyValuesHolder.ofFloat("translationY", new float[] {
                view.getTranslationY(), 0.0F
            });
        }
        if (i1 != 0 || k2 != 0)
        {
            new Rect(0, 0, j1, k1);
            new Rect(0, 0, l1, i2);
        }
        parent = ObjectAnimator.ofPropertyValuesHolder(view, parent);
        if (view.getParent() instanceof ViewGroup)
        {
            parent = (ViewGroup)view.getParent();
            ViewGroupUtils.suppressLayout(parent, true);
            addListener(new Transition.TransitionListenerAdapter() {

                boolean mCanceled;
                final ChangeBounds this$0;
                final ViewGroup val$parent;

                public void onTransitionCancel(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, false);
                    mCanceled = true;
                }

                public void onTransitionEnd(Transition transition)
                {
                    if (!mCanceled)
                    {
                        ViewGroupUtils.suppressLayout(parent, false);
                    }
                }

                public void onTransitionPause(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, false);
                }

                public void onTransitionResume(Transition transition)
                {
                    ViewGroupUtils.suppressLayout(parent, true);
                }

            
            {
                this$0 = ChangeBounds.this;
                parent = viewgroup;
                super();
                mCanceled = false;
            }
            });
        }
        parent.addListener(new AnimatorListenerAdapter() {

            final ChangeBounds this$0;

            public void onAnimationEnd(Animator animator)
            {
            }

            
            {
                this$0 = ChangeBounds.this;
                super();
            }
        });
        return parent;
        j = ((Integer)parent.values.get("android:changeBounds:windowX")).intValue();
        l = ((Integer)parent.values.get("android:changeBounds:windowY")).intValue();
        i1 = ((Integer)transitionvalues.values.get("android:changeBounds:windowX")).intValue();
        j1 = ((Integer)transitionvalues.values.get("android:changeBounds:windowY")).intValue();
        if (j != i1 || l != j1)
        {
            parent.getLocationInWindow(mTempLocation);
            parent = Bitmap.createBitmap(view.getWidth(), view.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(parent));
            parent = new BitmapDrawable(parent);
            view.setVisibility(4);
            ViewUtils.getOverlay(parent).add(parent);
            transitionvalues = new Rect(j - mTempLocation[0], l - mTempLocation[1], (j - mTempLocation[0]) + view.getWidth(), (l - mTempLocation[1]) + view.getHeight());
            rect = new Rect(i1 - mTempLocation[0], j1 - mTempLocation[1], (i1 - mTempLocation[0]) + view.getWidth(), (j1 - mTempLocation[1]) + view.getHeight());
            transitionvalues = ObjectAnimator.ofObject(parent, "bounds", sRectEvaluator, new Object[] {
                transitionvalues, rect
            });
            transitionvalues.addListener(new AnimatorListenerAdapter() {

                final ChangeBounds this$0;
                final BitmapDrawable val$drawable;
                final ViewGroup val$sceneRoot;
                final View val$view;

                public void onAnimationEnd(Animator animator)
                {
                    ViewUtils.getOverlay(sceneRoot).remove(drawable);
                    view.setVisibility(0);
                }

            
            {
                this$0 = ChangeBounds.this;
                sceneRoot = viewgroup;
                drawable = bitmapdrawable;
                view = view1;
                super();
            }
            });
            return transitionvalues;
        }
        return null;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public void setResizeClip(boolean flag)
    {
        mResizeClip = flag;
    }

}
