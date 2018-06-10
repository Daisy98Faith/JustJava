// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.transition:
//            TransitionManager, Transition

private static class mSceneRoot
    implements android.view.er, android.view.
{

    ViewGroup mSceneRoot;
    Transition mTransition;

    private void removeListeners()
    {
        mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
        mSceneRoot.removeOnAttachStateChangeListener(this);
    }

    public boolean onPreDraw()
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        final ArrayMap runningTransitions;
        removeListeners();
        TransitionManager.access$000().remove(mSceneRoot);
        runningTransitions = TransitionManager.getRunningTransitions();
        arraylist1 = (ArrayList)runningTransitions.get(mSceneRoot);
        arraylist = null;
        if (arraylist1 != null) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = new ArrayList();
        runningTransitions.put(mSceneRoot, obj);
_L4:
        ((ArrayList) (obj)).add(mTransition);
        mTransition.addListener(new Transition.TransitionListenerAdapter() {

            final TransitionManager.MultiListener this$0;
            final ArrayMap val$runningTransitions;

            public void onTransitionEnd(Transition transition)
            {
                ((ArrayList)runningTransitions.get(mSceneRoot)).remove(transition);
            }

            
            {
                this$0 = TransitionManager.MultiListener.this;
                runningTransitions = arraymap;
                super();
            }
        });
        mTransition.captureValues(mSceneRoot, false);
        if (arraylist != null)
        {
            for (obj = arraylist.iterator(); ((Iterator) (obj)).hasNext(); ((Transition)((Iterator) (obj)).next()).resume(mSceneRoot)) { }
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj = arraylist1;
        if (arraylist1.size() > 0)
        {
            arraylist = new ArrayList(arraylist1);
            obj = arraylist1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        mTransition.playTransition(mSceneRoot);
        return true;
    }

    public void onViewAttachedToWindow(View view)
    {
    }

    public void onViewDetachedFromWindow(View view)
    {
        removeListeners();
        TransitionManager.access$000().remove(mSceneRoot);
        view = (ArrayList)TransitionManager.getRunningTransitions().get(mSceneRoot);
        if (view != null && view.size() > 0)
        {
            for (view = view.iterator(); view.hasNext(); ((Transition)view.next()).resume(mSceneRoot)) { }
        }
        mTransition.clearValues(true);
    }

    pter(Transition transition, ViewGroup viewgroup)
    {
        mTransition = transition;
        mSceneRoot = viewgroup;
    }
}
