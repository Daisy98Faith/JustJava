// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.transition:
//            TransitionValuesMaps, TransitionValues, ViewUtils, TransitionSet, 
//            WindowIdImpl

public abstract class Transition
    implements Cloneable
{
    private static class AnimationInfo
    {

        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowIdImpl mWindowId;

        AnimationInfo(View view, String s, Transition transition, WindowIdImpl windowidimpl, TransitionValues transitionvalues)
        {
            mView = view;
            mName = s;
            mValues = transitionvalues;
            mWindowId = windowidimpl;
            mTransition = transition;
        }
    }

    private static class ArrayListManager
    {

        static ArrayList add(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if (arraylist == null)
            {
                arraylist1 = new ArrayList();
            }
            if (!arraylist1.contains(obj))
            {
                arraylist1.add(obj);
            }
            return arraylist1;
        }

        static ArrayList remove(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if (arraylist != null)
            {
                arraylist.remove(obj);
                arraylist1 = arraylist;
                if (arraylist.isEmpty())
                {
                    arraylist1 = null;
                }
            }
            return arraylist1;
        }

        private ArrayListManager()
        {
        }
    }

    public static interface MatchOrder
        extends Annotation
    {
    }

    public static interface TransitionListener
    {

        public abstract void onTransitionCancel(Transition transition);

        public abstract void onTransitionEnd(Transition transition);

        public abstract void onTransitionPause(Transition transition);

        public abstract void onTransitionResume(Transition transition);

        public abstract void onTransitionStart(Transition transition);
    }

    public static class TransitionListenerAdapter
        implements TransitionListener
    {

        public void onTransitionCancel(Transition transition)
        {
        }

        public void onTransitionEnd(Transition transition)
        {
        }

        public void onTransitionPause(Transition transition)
        {
        }

        public void onTransitionResume(Transition transition)
        {
        }

        public void onTransitionStart(Transition transition)
        {
        }

        public TransitionListenerAdapter()
        {
        }
    }


    static final boolean DBG = false;
    private static final int DEFAULT_MATCH_ORDER[] = {
        2, 1, 3, 4
    };
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static ThreadLocal sRunningAnimators = new ThreadLocal();
    private ArrayList mAnimators;
    boolean mCanRemoveViews;
    private ArrayList mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    private ArrayList mEndValuesList;
    private boolean mEnded;
    private TimeInterpolator mInterpolator;
    private ArrayList mListeners;
    private int mMatchOrder[];
    private String mName;
    private ArrayMap mNameOverrides;
    private int mNumInstances;
    TransitionSet mParent;
    private boolean mPaused;
    private ViewGroup mSceneRoot;
    private long mStartDelay;
    private TransitionValuesMaps mStartValues;
    private ArrayList mStartValuesList;
    private ArrayList mTargetChildExcludes;
    private ArrayList mTargetExcludes;
    private ArrayList mTargetIdChildExcludes;
    private ArrayList mTargetIdExcludes;
    ArrayList mTargetIds;
    private ArrayList mTargetNameExcludes;
    private ArrayList mTargetNames;
    private ArrayList mTargetTypeChildExcludes;
    private ArrayList mTargetTypeExcludes;
    private ArrayList mTargetTypes;
    ArrayList mTargets;

    public Transition()
    {
        mName = getClass().getName();
        mStartDelay = -1L;
        mDuration = -1L;
        mInterpolator = null;
        mTargetIds = new ArrayList();
        mTargets = new ArrayList();
        mTargetNames = null;
        mTargetTypes = null;
        mTargetIdExcludes = null;
        mTargetExcludes = null;
        mTargetTypeExcludes = null;
        mTargetNameExcludes = null;
        mTargetIdChildExcludes = null;
        mTargetChildExcludes = null;
        mTargetTypeChildExcludes = null;
        mStartValues = new TransitionValuesMaps();
        mEndValues = new TransitionValuesMaps();
        mParent = null;
        mMatchOrder = DEFAULT_MATCH_ORDER;
        mSceneRoot = null;
        mCanRemoveViews = false;
        mCurrentAnimators = new ArrayList();
        mNumInstances = 0;
        mPaused = false;
        mEnded = false;
        mListeners = null;
        mAnimators = new ArrayList();
    }

    private void addUnmatched(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for (int i = 0; i < arraymap.size(); i++)
        {
            TransitionValues transitionvalues = (TransitionValues)arraymap.valueAt(i);
            if (isValidTarget(transitionvalues.view))
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(null);
            }
        }

        for (int j = 0; j < arraymap1.size(); j++)
        {
            arraymap = (TransitionValues)arraymap1.valueAt(j);
            if (isValidTarget(((TransitionValues) (arraymap)).view))
            {
                mEndValuesList.add(arraymap);
                mStartValuesList.add(null);
            }
        }

    }

    private static void addViewValues(TransitionValuesMaps transitionvaluesmaps, View view, TransitionValues transitionvalues)
    {
        long l;
label0:
        {
            transitionvaluesmaps.mViewValues.put(view, transitionvalues);
            int i = view.getId();
            if (i >= 0)
            {
                if (transitionvaluesmaps.mIdValues.indexOfKey(i) >= 0)
                {
                    transitionvaluesmaps.mIdValues.put(i, null);
                } else
                {
                    transitionvaluesmaps.mIdValues.put(i, view);
                }
            }
            transitionvalues = ViewCompat.getTransitionName(view);
            if (transitionvalues != null)
            {
                if (transitionvaluesmaps.mNameValues.containsKey(transitionvalues))
                {
                    transitionvaluesmaps.mNameValues.put(transitionvalues, null);
                } else
                {
                    transitionvaluesmaps.mNameValues.put(transitionvalues, view);
                }
            }
            if (view.getParent() instanceof ListView)
            {
                transitionvalues = (ListView)view.getParent();
                if (transitionvalues.getAdapter().hasStableIds())
                {
                    l = transitionvalues.getItemIdAtPosition(transitionvalues.getPositionForView(view));
                    if (transitionvaluesmaps.mItemIdValues.indexOfKey(l) < 0)
                    {
                        break label0;
                    }
                    view = (View)transitionvaluesmaps.mItemIdValues.get(l);
                    if (view != null)
                    {
                        ViewCompat.setHasTransientState(view, false);
                        transitionvaluesmaps.mItemIdValues.put(l, null);
                    }
                }
            }
            return;
        }
        ViewCompat.setHasTransientState(view, true);
        transitionvaluesmaps.mItemIdValues.put(l, view);
    }

    private static boolean alreadyContains(int ai[], int i)
    {
        int k = ai[i];
        for (int j = 0; j < i; j++)
        {
            if (ai[j] == k)
            {
                return true;
            }
        }

        return false;
    }

    private void captureHierarchy(View view, boolean flag)
    {
        if (view != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int l = view.getId();
        if (mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf(l)) || mTargetExcludes != null && mTargetExcludes.contains(view))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mTargetTypeExcludes != null)
        {
            int i1 = mTargetTypeExcludes.size();
            for (int i = 0; i < i1; i++)
            {
                if (((Class)mTargetTypeExcludes.get(i)).isInstance(view))
                {
                    continue; /* Loop/switch isn't completed */
                }
            }

        }
        if (view.getParent() instanceof ViewGroup)
        {
            TransitionValues transitionvalues = new TransitionValues();
            transitionvalues.view = view;
            int j;
            if (flag)
            {
                captureStartValues(transitionvalues);
            } else
            {
                captureEndValues(transitionvalues);
            }
            transitionvalues.mTargetedTransitions.add(this);
            if (flag)
            {
                addViewValues(mStartValues, view, transitionvalues);
            } else
            {
                addViewValues(mEndValues, view, transitionvalues);
            }
        }
        if (!(view instanceof ViewGroup) || mTargetIdChildExcludes != null && mTargetIdChildExcludes.contains(Integer.valueOf(l)) || mTargetChildExcludes != null && mTargetChildExcludes.contains(view))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mTargetTypeChildExcludes != null)
        {
            l = mTargetTypeChildExcludes.size();
            for (j = 0; j < l; j++)
            {
                if (((Class)mTargetTypeChildExcludes.get(j)).isInstance(view))
                {
                    continue; /* Loop/switch isn't completed */
                }
            }

        }
        view = (ViewGroup)view;
        int k = 0;
        while (k < view.getChildCount()) 
        {
            captureHierarchy(view.getChildAt(k), flag);
            k++;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private ArrayList excludeId(ArrayList arraylist, int i, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if (i > 0)
            {
                if (!flag)
                {
                    break label0;
                }
                arraylist1 = ArrayListManager.add(arraylist, Integer.valueOf(i));
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, Integer.valueOf(i));
    }

    private static ArrayList excludeObject(ArrayList arraylist, Object obj, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if (obj != null)
            {
                if (!flag)
                {
                    break label0;
                }
                arraylist1 = ArrayListManager.add(arraylist, obj);
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, obj);
    }

    private ArrayList excludeType(ArrayList arraylist, Class class1, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if (class1 != null)
            {
                if (!flag)
                {
                    break label0;
                }
                arraylist1 = ArrayListManager.add(arraylist, class1);
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, class1);
    }

    private ArrayList excludeView(ArrayList arraylist, View view, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if (view != null)
            {
                if (!flag)
                {
                    break label0;
                }
                arraylist1 = ArrayListManager.add(arraylist, view);
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, view);
    }

    private static ArrayMap getRunningAnimators()
    {
        ArrayMap arraymap1 = (ArrayMap)sRunningAnimators.get();
        ArrayMap arraymap = arraymap1;
        if (arraymap1 == null)
        {
            arraymap = new ArrayMap();
            sRunningAnimators.set(arraymap);
        }
        return arraymap;
    }

    private static boolean isValidMatch(int i)
    {
        return i >= 1 && i <= 4;
    }

    private static boolean isValueChanged(TransitionValues transitionvalues, TransitionValues transitionvalues1, String s)
    {
        transitionvalues = ((TransitionValues) (transitionvalues.values.get(s)));
        transitionvalues1 = ((TransitionValues) (transitionvalues1.values.get(s)));
        if (transitionvalues == null && transitionvalues1 == null)
        {
            return false;
        }
        if (transitionvalues == null || transitionvalues1 == null)
        {
            return true;
        }
        return !transitionvalues.equals(transitionvalues1);
    }

    private void matchIds(ArrayMap arraymap, ArrayMap arraymap1, SparseArray sparsearray, SparseArray sparsearray1)
    {
        int j = sparsearray.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)sparsearray.valueAt(i);
            if (view == null || !isValidTarget(view))
            {
                continue;
            }
            View view1 = (View)sparsearray1.get(sparsearray.keyAt(i));
            if (view1 == null || !isValidTarget(view1))
            {
                continue;
            }
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if (transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchInstances(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for (int i = arraymap.size() - 1; i >= 0; i--)
        {
            Object obj = (View)arraymap.keyAt(i);
            if (obj == null || !isValidTarget(((View) (obj))))
            {
                continue;
            }
            obj = (TransitionValues)arraymap1.remove(obj);
            if (obj != null && ((TransitionValues) (obj)).view != null && isValidTarget(((TransitionValues) (obj)).view))
            {
                TransitionValues transitionvalues = (TransitionValues)arraymap.removeAt(i);
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(obj);
            }
        }

    }

    private void matchItemIds(ArrayMap arraymap, ArrayMap arraymap1, LongSparseArray longsparsearray, LongSparseArray longsparsearray1)
    {
        int j = longsparsearray.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)longsparsearray.valueAt(i);
            if (view == null || !isValidTarget(view))
            {
                continue;
            }
            View view1 = (View)longsparsearray1.get(longsparsearray.keyAt(i));
            if (view1 == null || !isValidTarget(view1))
            {
                continue;
            }
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if (transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchNames(ArrayMap arraymap, ArrayMap arraymap1, ArrayMap arraymap2, ArrayMap arraymap3)
    {
        int j = arraymap2.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)arraymap2.valueAt(i);
            if (view == null || !isValidTarget(view))
            {
                continue;
            }
            View view1 = (View)arraymap3.get(arraymap2.keyAt(i));
            if (view1 == null || !isValidTarget(view1))
            {
                continue;
            }
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if (transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchStartAndEnd(TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1)
    {
        ArrayMap arraymap;
        ArrayMap arraymap1;
        int i;
        arraymap = new ArrayMap(transitionvaluesmaps.mViewValues);
        arraymap1 = new ArrayMap(transitionvaluesmaps1.mViewValues);
        i = 0;
_L7:
        if (i >= mMatchOrder.length)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        mMatchOrder[i];
        JVM INSTR tableswitch 1 4: default 76
    //                   1 85
    //                   2 95
    //                   3 113
    //                   4 131;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_131;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        matchInstances(arraymap, arraymap1);
          goto _L8
_L3:
        matchNames(arraymap, arraymap1, transitionvaluesmaps.mNameValues, transitionvaluesmaps1.mNameValues);
          goto _L8
_L4:
        matchIds(arraymap, arraymap1, transitionvaluesmaps.mIdValues, transitionvaluesmaps1.mIdValues);
          goto _L8
        matchItemIds(arraymap, arraymap1, transitionvaluesmaps.mItemIdValues, transitionvaluesmaps1.mItemIdValues);
          goto _L8
        addUnmatched(arraymap, arraymap1);
        return;
    }

    private void runAnimator(Animator animator, final ArrayMap runningAnimators)
    {
        if (animator != null)
        {
            animator.addListener(new AnimatorListenerAdapter() {

                final Transition this$0;
                final ArrayMap val$runningAnimators;

                public void onAnimationEnd(Animator animator1)
                {
                    runningAnimators.remove(animator1);
                    mCurrentAnimators.remove(animator1);
                }

                public void onAnimationStart(Animator animator1)
                {
                    mCurrentAnimators.add(animator1);
                }

            
            {
                this$0 = Transition.this;
                runningAnimators = arraymap;
                super();
            }
            });
            animate(animator);
        }
    }

    public Transition addListener(TransitionListener transitionlistener)
    {
        if (mListeners == null)
        {
            mListeners = new ArrayList();
        }
        mListeners.add(transitionlistener);
        return this;
    }

    public Transition addTarget(int i)
    {
        if (i > 0)
        {
            mTargetIds.add(Integer.valueOf(i));
        }
        return this;
    }

    public Transition addTarget(View view)
    {
        mTargets.add(view);
        return this;
    }

    public Transition addTarget(Class class1)
    {
        if (mTargetTypes == null)
        {
            mTargetTypes = new ArrayList();
        }
        mTargetTypes.add(class1);
        return this;
    }

    public Transition addTarget(String s)
    {
        if (mTargetNames == null)
        {
            mTargetNames = new ArrayList();
        }
        mTargetNames.add(s);
        return this;
    }

    protected void animate(Animator animator)
    {
        if (animator == null)
        {
            end();
            return;
        }
        if (getDuration() >= 0L)
        {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0L)
        {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null)
        {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter() {

            final Transition this$0;

            public void onAnimationEnd(Animator animator1)
            {
                end();
                animator1.removeListener(this);
            }

            
            {
                this$0 = Transition.this;
                super();
            }
        });
        animator.start();
    }

    boolean areValuesChanged(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        flag = flag1;
        if (transitionvalues == null) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (transitionvalues1 == null) goto _L2; else goto _L3
_L3:
        String as[] = getTransitionProperties();
        if (as == null) goto _L5; else goto _L4
_L4:
        int i;
        int j;
        j = as.length;
        i = 0;
_L13:
        flag = flag1;
        if (i >= j) goto _L2; else goto _L6
_L6:
        if (!isValueChanged(transitionvalues, transitionvalues1, as[i])) goto _L8; else goto _L7
_L7:
        flag = true;
_L2:
        return flag;
_L8:
        i++;
        continue; /* Loop/switch isn't completed */
_L5:
        Iterator iterator = transitionvalues.values.keySet().iterator();
_L11:
        flag = flag1;
        if (!iterator.hasNext()) goto _L2; else goto _L9
_L9:
        if (!isValueChanged(transitionvalues, transitionvalues1, (String)iterator.next())) goto _L11; else goto _L10
_L10:
        return true;
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected void cancel()
    {
        for (int i = mCurrentAnimators.size() - 1; i >= 0; i--)
        {
            ((Animator)mCurrentAnimators.get(i)).cancel();
        }

        if (mListeners != null && mListeners.size() > 0)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int k = arraylist.size();
            for (int j = 0; j < k; j++)
            {
                ((TransitionListener)arraylist.get(j)).onTransitionCancel(this);
            }

        }
    }

    public abstract void captureEndValues(TransitionValues transitionvalues);

    public abstract void captureStartValues(TransitionValues transitionvalues);

    void captureValues(ViewGroup viewgroup, boolean flag)
    {
        clearValues(flag);
        if ((mTargetIds.size() > 0 || mTargets.size() > 0) && (mTargetNames == null || mTargetNames.isEmpty()) && (mTargetTypes == null || mTargetTypes.isEmpty()))
        {
            int i = 0;
            while (i < mTargetIds.size()) 
            {
                View view = viewgroup.findViewById(((Integer)mTargetIds.get(i)).intValue());
                if (view != null)
                {
                    TransitionValues transitionvalues1 = new TransitionValues();
                    transitionvalues1.view = view;
                    if (flag)
                    {
                        captureStartValues(transitionvalues1);
                    } else
                    {
                        captureEndValues(transitionvalues1);
                    }
                    transitionvalues1.mTargetedTransitions.add(this);
                    if (flag)
                    {
                        addViewValues(mStartValues, view, transitionvalues1);
                    } else
                    {
                        addViewValues(mEndValues, view, transitionvalues1);
                    }
                }
                i++;
            }
            i = 0;
            while (i < mTargets.size()) 
            {
                viewgroup = (View)mTargets.get(i);
                TransitionValues transitionvalues = new TransitionValues();
                transitionvalues.view = viewgroup;
                if (flag)
                {
                    captureStartValues(transitionvalues);
                } else
                {
                    captureEndValues(transitionvalues);
                }
                transitionvalues.mTargetedTransitions.add(this);
                if (flag)
                {
                    addViewValues(mStartValues, viewgroup, transitionvalues);
                } else
                {
                    addViewValues(mEndValues, viewgroup, transitionvalues);
                }
                i++;
            }
        } else
        {
            captureHierarchy(viewgroup, flag);
        }
        if (!flag && mNameOverrides != null)
        {
            int l = mNameOverrides.size();
            viewgroup = new ArrayList(l);
            for (int j = 0; j < l; j++)
            {
                String s = (String)mNameOverrides.keyAt(j);
                viewgroup.add(mStartValues.mNameValues.remove(s));
            }

            for (int k = 0; k < l; k++)
            {
                View view1 = (View)viewgroup.get(k);
                if (view1 != null)
                {
                    String s1 = (String)mNameOverrides.valueAt(k);
                    mStartValues.mNameValues.put(s1, view1);
                }
            }

        }
    }

    void clearValues(boolean flag)
    {
        if (flag)
        {
            mStartValues.mViewValues.clear();
            mStartValues.mIdValues.clear();
            mStartValues.mItemIdValues.clear();
            return;
        } else
        {
            mEndValues.mViewValues.clear();
            mEndValues.mIdValues.clear();
            mEndValues.mItemIdValues.clear();
            return;
        }
    }

    public Transition clone()
    {
        Transition transition;
        try
        {
            transition = (Transition)super.clone();
            transition.mAnimators = new ArrayList();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        return transition;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        ArrayMap arraymap;
        int i;
        int k;
        arraymap = getRunningAnimators();
        k = arraylist.size();
        i = 0;
_L2:
        TransitionValues transitionvalues;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_487;
        }
        transitionvalues = (TransitionValues)arraylist.get(i);
        TransitionValues transitionvalues1 = (TransitionValues)arraylist1.get(i);
        transitionvaluesmaps = transitionvalues;
        if (transitionvalues != null)
        {
            transitionvaluesmaps = transitionvalues;
            if (!transitionvalues.mTargetedTransitions.contains(this))
            {
                transitionvaluesmaps = null;
            }
        }
        transitionvalues = transitionvalues1;
        if (transitionvalues1 != null)
        {
            transitionvalues = transitionvalues1;
            if (!transitionvalues1.mTargetedTransitions.contains(this))
            {
                transitionvalues = null;
            }
        }
        if (transitionvaluesmaps != null || transitionvalues != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Animator animator;
        Animator animator1;
        View view;
        TransitionValues transitionvalues2;
        String as[];
        int j;
        if (transitionvaluesmaps == null || transitionvalues == null || areValuesChanged(transitionvaluesmaps, transitionvalues))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L4; else goto _L3
_L3:
        animator1 = createAnimator(viewgroup, transitionvaluesmaps, transitionvalues);
        if (animator1 == null) goto _L4; else goto _L5
_L5:
        transitionvalues2 = null;
        if (transitionvalues == null)
        {
            break MISSING_BLOCK_LABEL_471;
        }
        view = transitionvalues.view;
        as = getTransitionProperties();
        transitionvaluesmaps = view;
        transitionvalues = transitionvalues2;
        animator = animator1;
        if (view == null) goto _L7; else goto _L6
_L6:
        transitionvaluesmaps = view;
        transitionvalues = transitionvalues2;
        animator = animator1;
        if (as == null) goto _L7; else goto _L8
_L8:
        transitionvaluesmaps = view;
        transitionvalues = transitionvalues2;
        animator = animator1;
        if (as.length <= 0) goto _L7; else goto _L9
_L9:
        int l;
        transitionvalues2 = new TransitionValues();
        transitionvalues2.view = view;
        transitionvaluesmaps = (TransitionValues)transitionvaluesmaps1.mViewValues.get(view);
        if (transitionvaluesmaps != null)
        {
            for (j = 0; j < as.length; j++)
            {
                transitionvalues2.values.put(as[j], ((TransitionValues) (transitionvaluesmaps)).values.get(as[j]));
            }

        }
        l = arraymap.size();
        j = 0;
_L13:
        transitionvaluesmaps = view;
        transitionvalues = transitionvalues2;
        animator = animator1;
        if (j >= l) goto _L7; else goto _L10
_L10:
        transitionvaluesmaps = (AnimationInfo)arraymap.get((Animator)arraymap.keyAt(j));
        if (((AnimationInfo) (transitionvaluesmaps)).mValues == null || ((AnimationInfo) (transitionvaluesmaps)).mView != view || !((AnimationInfo) (transitionvaluesmaps)).mName.equals(getName()) || !((AnimationInfo) (transitionvaluesmaps)).mValues.equals(transitionvalues2)) goto _L12; else goto _L11
_L11:
        animator = null;
        transitionvalues = transitionvalues2;
        transitionvaluesmaps = view;
_L7:
        if (animator != null)
        {
            arraymap.put(animator, new AnimationInfo(transitionvaluesmaps, getName(), this, ViewUtils.getWindowId(viewgroup), transitionvalues));
            mAnimators.add(animator);
        }
          goto _L4
_L12:
        j++;
          goto _L13
        transitionvaluesmaps = ((TransitionValues) (transitionvaluesmaps)).view;
        transitionvalues = transitionvalues2;
        animator = animator1;
          goto _L7
          goto _L4
    }

    protected void end()
    {
        mNumInstances = mNumInstances - 1;
        if (mNumInstances == 0)
        {
            if (mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int l = arraylist.size();
                for (int i = 0; i < l; i++)
                {
                    ((TransitionListener)arraylist.get(i)).onTransitionEnd(this);
                }

            }
            for (int j = 0; j < mStartValues.mItemIdValues.size(); j++)
            {
                View view = (View)mStartValues.mItemIdValues.valueAt(j);
                if (view != null)
                {
                    ViewCompat.setHasTransientState(view, false);
                }
            }

            for (int k = 0; k < mEndValues.mItemIdValues.size(); k++)
            {
                View view1 = (View)mEndValues.mItemIdValues.valueAt(k);
                if (view1 != null)
                {
                    ViewCompat.setHasTransientState(view1, false);
                }
            }

            mEnded = true;
        }
    }

    public Transition excludeChildren(int i, boolean flag)
    {
        mTargetIdChildExcludes = excludeId(mTargetIdChildExcludes, i, flag);
        return this;
    }

    public Transition excludeChildren(View view, boolean flag)
    {
        mTargetChildExcludes = excludeView(mTargetChildExcludes, view, flag);
        return this;
    }

    public Transition excludeChildren(Class class1, boolean flag)
    {
        mTargetTypeChildExcludes = excludeType(mTargetTypeChildExcludes, class1, flag);
        return this;
    }

    public Transition excludeTarget(int i, boolean flag)
    {
        mTargetIdExcludes = excludeId(mTargetIdExcludes, i, flag);
        return this;
    }

    public Transition excludeTarget(View view, boolean flag)
    {
        mTargetExcludes = excludeView(mTargetExcludes, view, flag);
        return this;
    }

    public Transition excludeTarget(Class class1, boolean flag)
    {
        mTargetTypeExcludes = excludeType(mTargetTypeExcludes, class1, flag);
        return this;
    }

    public Transition excludeTarget(String s, boolean flag)
    {
        mTargetNameExcludes = excludeObject(mTargetNameExcludes, s, flag);
        return this;
    }

    public long getDuration()
    {
        return mDuration;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    TransitionValues getMatchedTransitionValues(View view, boolean flag)
    {
        if (mParent == null) goto _L2; else goto _L1
_L1:
        view = mParent.getMatchedTransitionValues(view, flag);
_L4:
        return view;
_L2:
        ArrayList arraylist;
        int i;
        byte byte0;
        int k;
        if (flag)
        {
            arraylist = mStartValuesList;
        } else
        {
            arraylist = mEndValuesList;
        }
        if (arraylist == null)
        {
            return null;
        }
        k = arraylist.size();
        byte0 = -1;
        i = 0;
_L5:
label0:
        {
            int j = byte0;
            if (i < k)
            {
                TransitionValues transitionvalues = (TransitionValues)arraylist.get(i);
                if (transitionvalues == null)
                {
                    return null;
                }
                if (transitionvalues.view != view)
                {
                    break label0;
                }
                j = i;
            }
            view = null;
            if (j >= 0)
            {
                if (flag)
                {
                    view = mEndValuesList;
                } else
                {
                    view = mStartValuesList;
                }
                return (TransitionValues)view.get(j);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
    }

    public String getName()
    {
        return mName;
    }

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public List getTargetIds()
    {
        return mTargetIds;
    }

    public List getTargetNames()
    {
        return mTargetNames;
    }

    public List getTargetTypes()
    {
        return mTargetTypes;
    }

    public List getTargets()
    {
        return mTargets;
    }

    public String[] getTransitionProperties()
    {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean flag)
    {
        if (mParent != null)
        {
            return mParent.getTransitionValues(view, flag);
        }
        TransitionValuesMaps transitionvaluesmaps;
        if (flag)
        {
            transitionvaluesmaps = mStartValues;
        } else
        {
            transitionvaluesmaps = mEndValues;
        }
        return (TransitionValues)transitionvaluesmaps.mViewValues.get(view);
    }

    boolean isValidTarget(View view)
    {
        int k = view.getId();
        if (mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf(k)))
        {
            return false;
        }
        if (mTargetExcludes != null && mTargetExcludes.contains(view))
        {
            return false;
        }
        if (mTargetTypeExcludes != null)
        {
            int l = mTargetTypeExcludes.size();
            for (int i = 0; i < l; i++)
            {
                if (((Class)mTargetTypeExcludes.get(i)).isInstance(view))
                {
                    return false;
                }
            }

        }
        if (mTargetNameExcludes != null && ViewCompat.getTransitionName(view) != null && mTargetNameExcludes.contains(ViewCompat.getTransitionName(view)))
        {
            return false;
        }
        if (mTargetIds.size() == 0 && mTargets.size() == 0 && (mTargetTypes == null || mTargetTypes.isEmpty()) && (mTargetNames == null || mTargetNames.isEmpty()))
        {
            return true;
        }
        if (mTargetIds.contains(Integer.valueOf(k)) || mTargets.contains(view))
        {
            return true;
        }
        if (mTargetNames != null && mTargetNames.contains(ViewCompat.getTransitionName(view)))
        {
            return true;
        }
        if (mTargetTypes != null)
        {
            for (int j = 0; j < mTargetTypes.size(); j++)
            {
                if (((Class)mTargetTypes.get(j)).isInstance(view))
                {
                    return true;
                }
            }

        }
        return false;
    }

    public void pause(View view)
    {
        if (!mEnded)
        {
            ArrayMap arraymap = getRunningAnimators();
            int i = arraymap.size();
            view = ViewUtils.getWindowId(view);
            for (i--; i >= 0; i--)
            {
                AnimationInfo animationinfo = (AnimationInfo)arraymap.valueAt(i);
                if (animationinfo.mView != null && view.equals(animationinfo.mWindowId))
                {
                    ((Animator)arraymap.keyAt(i)).cancel();
                }
            }

            if (mListeners != null && mListeners.size() > 0)
            {
                view = (ArrayList)mListeners.clone();
                int k = view.size();
                for (int j = 0; j < k; j++)
                {
                    ((TransitionListener)view.get(j)).onTransitionPause(this);
                }

            }
            mPaused = true;
        }
    }

    void playTransition(ViewGroup viewgroup)
    {
        mStartValuesList = new ArrayList();
        mEndValuesList = new ArrayList();
        matchStartAndEnd(mStartValues, mEndValues);
        ArrayMap arraymap = getRunningAnimators();
        int i = arraymap.size();
        WindowIdImpl windowidimpl = ViewUtils.getWindowId(viewgroup);
        i--;
        while (i >= 0) 
        {
            Animator animator = (Animator)arraymap.keyAt(i);
            if (animator == null)
            {
                continue;
            }
            AnimationInfo animationinfo = (AnimationInfo)arraymap.get(animator);
            if (animationinfo == null || animationinfo.mView == null || animationinfo.mWindowId != windowidimpl)
            {
                continue;
            }
            TransitionValues transitionvalues = animationinfo.mValues;
            Object obj = animationinfo.mView;
            TransitionValues transitionvalues1 = getTransitionValues(((View) (obj)), true);
            obj = getMatchedTransitionValues(((View) (obj)), true);
            boolean flag;
            if ((transitionvalues1 != null || obj != null) && animationinfo.mTransition.areValuesChanged(transitionvalues, ((TransitionValues) (obj))))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (animator.isRunning() || animator.isStarted())
                {
                    animator.cancel();
                } else
                {
                    arraymap.remove(animator);
                }
            }
            i--;
        }
        createAnimators(viewgroup, mStartValues, mEndValues, mStartValuesList, mEndValuesList);
        runAnimators();
    }

    public Transition removeListener(TransitionListener transitionlistener)
    {
        if (mListeners != null)
        {
            mListeners.remove(transitionlistener);
            if (mListeners.size() == 0)
            {
                mListeners = null;
                return this;
            }
        }
        return this;
    }

    public Transition removeTarget(int i)
    {
        if (i > 0)
        {
            mTargetIds.remove(Integer.valueOf(i));
        }
        return this;
    }

    public Transition removeTarget(View view)
    {
        mTargets.remove(view);
        return this;
    }

    public Transition removeTarget(Class class1)
    {
        if (mTargetTypes != null)
        {
            mTargetTypes.remove(class1);
        }
        return this;
    }

    public Transition removeTarget(String s)
    {
        if (mTargetNames != null)
        {
            mTargetNames.remove(s);
        }
        return this;
    }

    public void resume(View view)
    {
        if (mPaused)
        {
            if (!mEnded)
            {
                ArrayMap arraymap = getRunningAnimators();
                int i = arraymap.size();
                view = ViewUtils.getWindowId(view);
                for (i--; i >= 0; i--)
                {
                    AnimationInfo animationinfo = (AnimationInfo)arraymap.valueAt(i);
                    if (animationinfo.mView != null && view.equals(animationinfo.mWindowId))
                    {
                        ((Animator)arraymap.keyAt(i)).end();
                    }
                }

                if (mListeners != null && mListeners.size() > 0)
                {
                    view = (ArrayList)mListeners.clone();
                    int k = view.size();
                    for (int j = 0; j < k; j++)
                    {
                        ((TransitionListener)view.get(j)).onTransitionResume(this);
                    }

                }
            }
            mPaused = false;
        }
    }

    protected void runAnimators()
    {
        start();
        ArrayMap arraymap = getRunningAnimators();
        Iterator iterator = mAnimators.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Animator animator = (Animator)iterator.next();
            if (arraymap.containsKey(animator))
            {
                start();
                runAnimator(animator, arraymap);
            }
        } while (true);
        mAnimators.clear();
        end();
    }

    void setCanRemoveViews(boolean flag)
    {
        mCanRemoveViews = flag;
    }

    public Transition setDuration(long l)
    {
        mDuration = l;
        return this;
    }

    public Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolator = timeinterpolator;
        return this;
    }

    public transient void setMatchOrder(int ai[])
    {
        if (ai == null || ai.length == 0)
        {
            mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        for (int i = 0; i < ai.length; i++)
        {
            if (!isValidMatch(ai[i]))
            {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (alreadyContains(ai, i))
            {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }

        mMatchOrder = (int[])ai.clone();
    }

    Transition setSceneRoot(ViewGroup viewgroup)
    {
        mSceneRoot = viewgroup;
        return this;
    }

    public Transition setStartDelay(long l)
    {
        mStartDelay = l;
        return this;
    }

    protected void start()
    {
        if (mNumInstances == 0)
        {
            if (mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int j = arraylist.size();
                for (int i = 0; i < j; i++)
                {
                    ((TransitionListener)arraylist.get(i)).onTransitionStart(this);
                }

            }
            mEnded = false;
        }
        mNumInstances = mNumInstances + 1;
    }

    public String toString()
    {
        return toString("");
    }

    String toString(String s)
    {
        String s1;
label0:
        {
            s1 = (new StringBuilder()).append(s).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(": ").toString();
            s = s1;
            if (mDuration != -1L)
            {
                s = (new StringBuilder()).append(s1).append("dur(").append(mDuration).append(") ").toString();
            }
            s1 = s;
            if (mStartDelay != -1L)
            {
                s1 = (new StringBuilder()).append(s).append("dly(").append(mStartDelay).append(") ").toString();
            }
            s = s1;
            if (mInterpolator != null)
            {
                s = (new StringBuilder()).append(s1).append("interp(").append(mInterpolator).append(") ").toString();
            }
            if (mTargetIds.size() <= 0)
            {
                s1 = s;
                if (mTargets.size() <= 0)
                {
                    break label0;
                }
            }
            s1 = (new StringBuilder()).append(s).append("tgts(").toString();
            s = s1;
            if (mTargetIds.size() > 0)
            {
                int i = 0;
                do
                {
                    s = s1;
                    if (i >= mTargetIds.size())
                    {
                        break;
                    }
                    s = s1;
                    if (i > 0)
                    {
                        s = (new StringBuilder()).append(s1).append(", ").toString();
                    }
                    s1 = (new StringBuilder()).append(s).append(mTargetIds.get(i)).toString();
                    i++;
                } while (true);
            }
            s1 = s;
            if (mTargets.size() > 0)
            {
                int j = 0;
                do
                {
                    s1 = s;
                    if (j >= mTargets.size())
                    {
                        break;
                    }
                    s1 = s;
                    if (j > 0)
                    {
                        s1 = (new StringBuilder()).append(s).append(", ").toString();
                    }
                    s = (new StringBuilder()).append(s1).append(mTargets.get(j)).toString();
                    j++;
                } while (true);
            }
            s1 = (new StringBuilder()).append(s1).append(")").toString();
        }
        return s1;
    }


}
