// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.transition:
//            AutoTransition, Transition, Scene

public class TransitionManager
{
    private static class MultiListener
        implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
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
            ArrayMap arraymap;
            removeListeners();
            TransitionManager.sPendingTransitions.remove(mSceneRoot);
            arraymap = TransitionManager.getRunningTransitions();
            arraylist1 = (ArrayList)arraymap.get(mSceneRoot);
            arraylist = null;
            if (arraylist1 != null) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = new ArrayList();
            arraymap.put(mSceneRoot, obj);
_L4:
            ((ArrayList) (obj)).add(mTransition);
            mTransition.addListener(arraymap. new Transition.TransitionListenerAdapter() {

                final MultiListener this$0;
                final ArrayMap val$runningTransitions;

                public void onTransitionEnd(Transition transition)
                {
                    ((ArrayList)runningTransitions.get(mSceneRoot)).remove(transition);
                }

            
            {
                this$0 = final_multilistener;
                runningTransitions = ArrayMap.this;
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
            TransitionManager.sPendingTransitions.remove(mSceneRoot);
            view = (ArrayList)TransitionManager.getRunningTransitions().get(mSceneRoot);
            if (view != null && view.size() > 0)
            {
                for (view = view.iterator(); view.hasNext(); ((Transition)view.next()).resume(mSceneRoot)) { }
            }
            mTransition.clearValues(true);
        }

        MultiListener(Transition transition, ViewGroup viewgroup)
        {
            mTransition = transition;
            mSceneRoot = viewgroup;
        }
    }


    private static final String LOG_TAG = "TransitionManager";
    private static Transition sDefaultTransition = new AutoTransition();
    private static ArrayList sPendingTransitions = new ArrayList();
    private static ThreadLocal sRunningTransitions = new ThreadLocal();
    private ArrayMap mScenePairTransitions;
    private ArrayMap mSceneTransitions;

    public TransitionManager()
    {
        mSceneTransitions = new ArrayMap();
        mScenePairTransitions = new ArrayMap();
    }

    public static void beginDelayedTransition(ViewGroup viewgroup)
    {
        beginDelayedTransition(viewgroup, null);
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, Transition transition)
    {
        if (!sPendingTransitions.contains(viewgroup) && ViewCompat.isLaidOut(viewgroup))
        {
            sPendingTransitions.add(viewgroup);
            Transition transition1 = transition;
            if (transition == null)
            {
                transition1 = sDefaultTransition;
            }
            transition = transition1.clone();
            sceneChangeSetup(viewgroup, transition);
            Scene.setCurrentScene(viewgroup, null);
            sceneChangeRunTransition(viewgroup, transition);
        }
    }

    private static void changeScene(Scene scene, Transition transition)
    {
        ViewGroup viewgroup = scene.getSceneRoot();
        Transition transition1 = null;
        if (transition != null)
        {
            transition1 = transition.clone();
            transition1.setSceneRoot(viewgroup);
        }
        transition = Scene.getCurrentScene(viewgroup);
        if (transition != null && transition.isCreatedFromLayoutResource())
        {
            transition1.setCanRemoveViews(true);
        }
        sceneChangeSetup(viewgroup, transition1);
        scene.enter();
        sceneChangeRunTransition(viewgroup, transition1);
    }

    static ArrayMap getRunningTransitions()
    {
        WeakReference weakreference;
label0:
        {
            WeakReference weakreference1 = (WeakReference)sRunningTransitions.get();
            if (weakreference1 != null)
            {
                weakreference = weakreference1;
                if (weakreference1.get() != null)
                {
                    break label0;
                }
            }
            weakreference = new WeakReference(new ArrayMap());
            sRunningTransitions.set(weakreference);
        }
        return (ArrayMap)weakreference.get();
    }

    private Transition getTransition(Scene scene)
    {
        Object obj = scene.getSceneRoot();
        if (obj != null)
        {
            obj = Scene.getCurrentScene(((View) (obj)));
            if (obj != null)
            {
                ArrayMap arraymap = (ArrayMap)mScenePairTransitions.get(scene);
                if (arraymap != null)
                {
                    obj = (Transition)arraymap.get(obj);
                    if (obj != null)
                    {
                        return ((Transition) (obj));
                    }
                }
            }
        }
        scene = (Transition)mSceneTransitions.get(scene);
        if (scene != null)
        {
            return scene;
        } else
        {
            return sDefaultTransition;
        }
    }

    public static void go(Scene scene)
    {
        changeScene(scene, sDefaultTransition);
    }

    public static void go(Scene scene, Transition transition)
    {
        changeScene(scene, transition);
    }

    private static void sceneChangeRunTransition(ViewGroup viewgroup, Transition transition)
    {
        if (transition != null && viewgroup != null)
        {
            transition = new MultiListener(transition, viewgroup);
            viewgroup.addOnAttachStateChangeListener(transition);
            viewgroup.getViewTreeObserver().addOnPreDrawListener(transition);
        }
    }

    private static void sceneChangeSetup(ViewGroup viewgroup, Transition transition)
    {
        Object obj = (ArrayList)getRunningTransitions().get(viewgroup);
        if (obj != null && ((ArrayList) (obj)).size() > 0)
        {
            for (obj = ((ArrayList) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((Transition)((Iterator) (obj)).next()).pause(viewgroup)) { }
        }
        if (transition != null)
        {
            transition.captureValues(viewgroup, true);
        }
        viewgroup = Scene.getCurrentScene(viewgroup);
        if (viewgroup != null)
        {
            viewgroup.exit();
        }
    }

    public void setTransition(Scene scene, Scene scene1, Transition transition)
    {
        ArrayMap arraymap1 = (ArrayMap)mScenePairTransitions.get(scene1);
        ArrayMap arraymap = arraymap1;
        if (arraymap1 == null)
        {
            arraymap = new ArrayMap();
            mScenePairTransitions.put(scene1, arraymap);
        }
        arraymap.put(scene, transition);
    }

    public void setTransition(Scene scene, Transition transition)
    {
        mSceneTransitions.put(scene, transition);
    }

    public void transitionTo(Scene scene)
    {
        changeScene(scene, getTransition(scene));
    }


}
