// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.transition:
//            Transition, TransitionValues, TransitionValuesMaps

public class TransitionSet extends Transition
{
    static class TransitionSetListener extends Transition.TransitionListenerAdapter
    {

        TransitionSet mTransitionSet;

        public void onTransitionEnd(Transition transition)
        {
            int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        public void onTransitionStart(Transition transition)
        {
            if (!mTransitionSet.mStarted)
            {
                mTransitionSet.start();
                mTransitionSet.mStarted = true;
            }
        }

        TransitionSetListener(TransitionSet transitionset)
        {
            mTransitionSet = transitionset;
        }
    }


    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private int mCurrentListeners;
    private boolean mPlayTogether;
    private boolean mStarted;
    private ArrayList mTransitions;

    public TransitionSet()
    {
        mTransitions = new ArrayList();
        mPlayTogether = true;
        mStarted = false;
    }

    private void setupStartEndListeners()
    {
        TransitionSetListener transitionsetlistener = new TransitionSetListener(this);
        for (Iterator iterator = mTransitions.iterator(); iterator.hasNext(); ((Transition)iterator.next()).addListener(transitionsetlistener)) { }
        mCurrentListeners = mTransitions.size();
    }

    public volatile Transition addListener(Transition.TransitionListener transitionlistener)
    {
        return addListener(transitionlistener);
    }

    public TransitionSet addListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)addListener(transitionlistener);
    }

    public volatile Transition addTarget(int i)
    {
        return addTarget(i);
    }

    public volatile Transition addTarget(View view)
    {
        return addTarget(view);
    }

    public volatile Transition addTarget(Class class1)
    {
        return addTarget(class1);
    }

    public volatile Transition addTarget(String s)
    {
        return addTarget(s);
    }

    public TransitionSet addTarget(int i)
    {
        for (int j = 0; j < mTransitions.size(); j++)
        {
            ((Transition)mTransitions.get(j)).addTarget(i);
        }

        return (TransitionSet)addTarget(i);
    }

    public TransitionSet addTarget(View view)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).addTarget(view);
        }

        return (TransitionSet)addTarget(view);
    }

    public TransitionSet addTarget(Class class1)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).addTarget(class1);
        }

        return (TransitionSet)addTarget(class1);
    }

    public TransitionSet addTarget(String s)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).addTarget(s);
        }

        return (TransitionSet)addTarget(s);
    }

    public TransitionSet addTransition(Transition transition)
    {
        mTransitions.add(transition);
        transition.mParent = this;
        if (mDuration >= 0L)
        {
            transition.setDuration(mDuration);
        }
        return this;
    }

    protected void cancel()
    {
        cancel();
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).cancel();
        }

    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        if (isValidTarget(transitionvalues.view))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Transition transition = (Transition)iterator.next();
                if (transition.isValidTarget(transitionvalues.view))
                {
                    transition.captureEndValues(transitionvalues);
                    transitionvalues.mTargetedTransitions.add(transition);
                }
            } while (true);
        }
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        if (isValidTarget(transitionvalues.view))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Transition transition = (Transition)iterator.next();
                if (transition.isValidTarget(transitionvalues.view))
                {
                    transition.captureStartValues(transitionvalues);
                    transitionvalues.mTargetedTransitions.add(transition);
                }
            } while (true);
        }
    }

    public Transition clone()
    {
        TransitionSet transitionset = (TransitionSet)clone();
        transitionset.mTransitions = new ArrayList();
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            transitionset.addTransition(((Transition)mTransitions.get(i)).clone());
        }

        return transitionset;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        long l = getStartDelay();
        int j = mTransitions.size();
        int i = 0;
        while (i < j) 
        {
            Transition transition = (Transition)mTransitions.get(i);
            if (l > 0L && (mPlayTogether || i == 0))
            {
                long l1 = transition.getStartDelay();
                if (l1 > 0L)
                {
                    transition.setStartDelay(l + l1);
                } else
                {
                    transition.setStartDelay(l);
                }
            }
            transition.createAnimators(viewgroup, transitionvaluesmaps, transitionvaluesmaps1, arraylist, arraylist1);
            i++;
        }
    }

    public Transition excludeTarget(int i, boolean flag)
    {
        for (int j = 0; j < mTransitions.size(); j++)
        {
            ((Transition)mTransitions.get(j)).excludeTarget(i, flag);
        }

        return excludeTarget(i, flag);
    }

    public Transition excludeTarget(View view, boolean flag)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).excludeTarget(view, flag);
        }

        return excludeTarget(view, flag);
    }

    public Transition excludeTarget(Class class1, boolean flag)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).excludeTarget(class1, flag);
        }

        return excludeTarget(class1, flag);
    }

    public Transition excludeTarget(String s, boolean flag)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).excludeTarget(s, flag);
        }

        return excludeTarget(s, flag);
    }

    public int getOrdering()
    {
        return !mPlayTogether ? 1 : 0;
    }

    public Transition getTransitionAt(int i)
    {
        if (i < 0 || i >= mTransitions.size())
        {
            return null;
        } else
        {
            return (Transition)mTransitions.get(i);
        }
    }

    public int getTransitionCount()
    {
        return mTransitions.size();
    }

    public void pause(View view)
    {
        pause(view);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).pause(view);
        }

    }

    public volatile Transition removeListener(Transition.TransitionListener transitionlistener)
    {
        return removeListener(transitionlistener);
    }

    public TransitionSet removeListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)removeListener(transitionlistener);
    }

    public volatile Transition removeTarget(int i)
    {
        return removeTarget(i);
    }

    public volatile Transition removeTarget(View view)
    {
        return removeTarget(view);
    }

    public volatile Transition removeTarget(Class class1)
    {
        return removeTarget(class1);
    }

    public volatile Transition removeTarget(String s)
    {
        return removeTarget(s);
    }

    public TransitionSet removeTarget(int i)
    {
        for (int j = 0; j < mTransitions.size(); j++)
        {
            ((Transition)mTransitions.get(j)).removeTarget(i);
        }

        return (TransitionSet)removeTarget(i);
    }

    public TransitionSet removeTarget(View view)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).removeTarget(view);
        }

        return (TransitionSet)removeTarget(view);
    }

    public TransitionSet removeTarget(Class class1)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).removeTarget(class1);
        }

        return (TransitionSet)removeTarget(class1);
    }

    public TransitionSet removeTarget(String s)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).removeTarget(s);
        }

        return (TransitionSet)removeTarget(s);
    }

    public TransitionSet removeTransition(Transition transition)
    {
        mTransitions.remove(transition);
        transition.mParent = null;
        return this;
    }

    public void resume(View view)
    {
        resume(view);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).resume(view);
        }

    }

    protected void runAnimators()
    {
        if (!mTransitions.isEmpty()) goto _L2; else goto _L1
_L1:
        start();
        end();
_L4:
        return;
_L2:
        setupStartEndListeners();
        if (!mPlayTogether)
        {
            for (int i = 1; i < mTransitions.size(); i++)
            {
                ((Transition)mTransitions.get(i - 1)).addListener(new Transition.TransitionListenerAdapter() {

                    final TransitionSet this$0;
                    final Transition val$nextTransition;

                    public void onTransitionEnd(Transition transition1)
                    {
                        nextTransition.runAnimators();
                        transition1.removeListener(this);
                    }

            
            {
                this$0 = TransitionSet.this;
                nextTransition = transition;
                super();
            }
                });
            }

            Transition transition = (Transition)mTransitions.get(0);
            if (transition != null)
            {
                transition.runAnimators();
                return;
            }
        } else
        {
            Iterator iterator = mTransitions.iterator();
            while (iterator.hasNext()) 
            {
                ((Transition)iterator.next()).runAnimators();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void setCanRemoveViews(boolean flag)
    {
        setCanRemoveViews(flag);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).setCanRemoveViews(flag);
        }

    }

    public volatile Transition setDuration(long l)
    {
        return setDuration(l);
    }

    public TransitionSet setDuration(long l)
    {
        setDuration(l);
        if (mDuration >= 0L)
        {
            int j = mTransitions.size();
            for (int i = 0; i < j; i++)
            {
                ((Transition)mTransitions.get(i)).setDuration(l);
            }

        }
        return this;
    }

    public volatile Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        return setInterpolator(timeinterpolator);
    }

    public TransitionSet setInterpolator(TimeInterpolator timeinterpolator)
    {
        return (TransitionSet)setInterpolator(timeinterpolator);
    }

    public TransitionSet setOrdering(int i)
    {
        switch (i)
        {
        default:
            throw new AndroidRuntimeException((new StringBuilder()).append("Invalid parameter for TransitionSet ordering: ").append(i).toString());

        case 1: // '\001'
            mPlayTogether = false;
            return this;

        case 0: // '\0'
            mPlayTogether = true;
            return this;
        }
    }

    volatile Transition setSceneRoot(ViewGroup viewgroup)
    {
        return setSceneRoot(viewgroup);
    }

    TransitionSet setSceneRoot(ViewGroup viewgroup)
    {
        setSceneRoot(viewgroup);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).setSceneRoot(viewgroup);
        }

        return this;
    }

    public volatile Transition setStartDelay(long l)
    {
        return setStartDelay(l);
    }

    public TransitionSet setStartDelay(long l)
    {
        return (TransitionSet)setStartDelay(l);
    }

    String toString(String s)
    {
        String s1 = toString(s);
        for (int i = 0; i < mTransitions.size(); i++)
        {
            s1 = (new StringBuilder()).append(s1).append("\n").append(((Transition)mTransitions.get(i)).toString((new StringBuilder()).append(s).append("  ").toString())).toString();
        }

        return s1;
    }



/*
    static boolean access$002(TransitionSet transitionset, boolean flag)
    {
        transitionset.mStarted = flag;
        return flag;
    }

*/



/*
    static int access$106(TransitionSet transitionset)
    {
        int i = transitionset.mCurrentListeners - 1;
        transitionset.mCurrentListeners = i;
        return i;
    }

*/
}
