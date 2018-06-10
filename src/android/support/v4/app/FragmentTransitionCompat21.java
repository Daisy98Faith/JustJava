// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.app:
//            OneShotPreDrawListener

class FragmentTransitionCompat21
{

    FragmentTransitionCompat21()
    {
    }

    public static void addTarget(Object obj, View view)
    {
        if (obj != null)
        {
            ((Transition)obj).addTarget(view);
        }
    }

    public static void addTargets(Object obj, ArrayList arraylist)
    {
        obj = (Transition)obj;
        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!(obj instanceof TransitionSet))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (TransitionSet)obj;
        int k = ((TransitionSet) (obj)).getTransitionCount();
        int i = 0;
        while (i < k) 
        {
            addTargets(((TransitionSet) (obj)).getTransitionAt(i), arraylist);
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!hasSimpleTarget(((Transition) (obj))) && isNullOrEmpty(((Transition) (obj)).getTargets()))
        {
            int l = arraylist.size();
            int j = 0;
            while (j < l) 
            {
                ((Transition) (obj)).addTarget((View)arraylist.get(j));
                j++;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, Object obj)
    {
        TransitionManager.beginDelayedTransition(viewgroup, (Transition)obj);
    }

    private static void bfsAddViewChildren(List list, View view)
    {
        int k = list.size();
        if (!containedBeforeIndex(list, view, k))
        {
            list.add(view);
            int i = k;
            while (i < list.size()) 
            {
                view = (View)list.get(i);
                if (view instanceof ViewGroup)
                {
                    view = (ViewGroup)view;
                    int l = view.getChildCount();
                    for (int j = 0; j < l; j++)
                    {
                        View view1 = view.getChildAt(j);
                        if (!containedBeforeIndex(list, view1, k))
                        {
                            list.add(view1);
                        }
                    }

                }
                i++;
            }
        }
    }

    public static void captureTransitioningViews(ArrayList arraylist, View view)
    {
label0:
        {
            if (view.getVisibility() == 0)
            {
                if (!(view instanceof ViewGroup))
                {
                    break label0;
                }
                view = (ViewGroup)view;
                if (view.isTransitionGroup())
                {
                    arraylist.add(view);
                } else
                {
                    int j = view.getChildCount();
                    int i = 0;
                    while (i < j) 
                    {
                        captureTransitioningViews(arraylist, view.getChildAt(i));
                        i++;
                    }
                }
            }
            return;
        }
        arraylist.add(view);
    }

    public static Object cloneTransition(Object obj)
    {
        Transition transition = null;
        if (obj != null)
        {
            transition = ((Transition)obj).clone();
        }
        return transition;
    }

    private static boolean containedBeforeIndex(List list, View view, int i)
    {
        for (int j = 0; j < i; j++)
        {
            if (list.get(j) == view)
            {
                return true;
            }
        }

        return false;
    }

    private static String findKeyForValue(Map map, String s)
    {
        for (map = map.entrySet().iterator(); map.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            if (s.equals(entry.getValue()))
            {
                return (String)entry.getKey();
            }
        }

        return null;
    }

    public static void findNamedViews(Map map, View view)
    {
        if (view.getVisibility() == 0)
        {
            String s = view.getTransitionName();
            if (s != null)
            {
                map.put(s, view);
            }
            if (view instanceof ViewGroup)
            {
                view = (ViewGroup)view;
                int j = view.getChildCount();
                for (int i = 0; i < j; i++)
                {
                    findNamedViews(map, view.getChildAt(i));
                }

            }
        }
    }

    public static void getBoundsOnScreen(View view, Rect rect)
    {
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        rect.set(ai[0], ai[1], ai[0] + view.getWidth(), ai[1] + view.getHeight());
    }

    private static boolean hasSimpleTarget(Transition transition)
    {
        return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes());
    }

    private static boolean isNullOrEmpty(List list)
    {
        return list == null || list.isEmpty();
    }

    public static Object mergeTransitionsInSequence(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = null;
        obj = (Transition)obj;
        obj1 = (Transition)obj1;
        obj2 = (Transition)obj2;
        if (obj != null && obj1 != null)
        {
            obj = (new TransitionSet()).addTransition(((Transition) (obj))).addTransition(((Transition) (obj1))).setOrdering(1);
        } else
        if (obj == null)
        {
            obj = obj3;
            if (obj1 != null)
            {
                obj = obj1;
            }
        }
        if (obj2 != null)
        {
            obj1 = new TransitionSet();
            if (obj != null)
            {
                ((TransitionSet) (obj1)).addTransition(((Transition) (obj)));
            }
            ((TransitionSet) (obj1)).addTransition(((Transition) (obj2)));
            return obj1;
        } else
        {
            return obj;
        }
    }

    public static Object mergeTransitionsTogether(Object obj, Object obj1, Object obj2)
    {
        TransitionSet transitionset = new TransitionSet();
        if (obj != null)
        {
            transitionset.addTransition((Transition)obj);
        }
        if (obj1 != null)
        {
            transitionset.addTransition((Transition)obj1);
        }
        if (obj2 != null)
        {
            transitionset.addTransition((Transition)obj2);
        }
        return transitionset;
    }

    public static ArrayList prepareSetNameOverridesOptimized(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)arraylist.get(i);
            arraylist1.add(view.getTransitionName());
            view.setTransitionName(null);
        }

        return arraylist1;
    }

    public static void removeTarget(Object obj, View view)
    {
        if (obj != null)
        {
            ((Transition)obj).removeTarget(view);
        }
    }

    public static void replaceTargets(Object obj, ArrayList arraylist, ArrayList arraylist1)
    {
        obj = (Transition)obj;
        if (obj instanceof TransitionSet)
        {
            obj = (TransitionSet)obj;
            int l = ((TransitionSet) (obj)).getTransitionCount();
            for (int i = 0; i < l; i++)
            {
                replaceTargets(((TransitionSet) (obj)).getTransitionAt(i), arraylist, arraylist1);
            }

        } else
        if (!hasSimpleTarget(((Transition) (obj))))
        {
            List list = ((Transition) (obj)).getTargets();
            if (list != null && list.size() == arraylist.size() && list.containsAll(arraylist))
            {
                int j;
                int i1;
                if (arraylist1 == null)
                {
                    j = 0;
                } else
                {
                    j = arraylist1.size();
                }
                for (i1 = 0; i1 < j; i1++)
                {
                    ((Transition) (obj)).addTarget((View)arraylist1.get(i1));
                }

                for (int k = arraylist.size() - 1; k >= 0; k--)
                {
                    ((Transition) (obj)).removeTarget((View)arraylist.get(k));
                }

            }
        }
    }

    public static void scheduleHideFragmentView(Object obj, View view, ArrayList arraylist)
    {
        ((Transition)obj).addListener(new android.transition.Transition.TransitionListener(view, arraylist) {

            final ArrayList val$exitingViews;
            final View val$fragmentView;

            public void onTransitionCancel(Transition transition)
            {
            }

            public void onTransitionEnd(Transition transition)
            {
                transition.removeListener(this);
                fragmentView.setVisibility(8);
                int j = exitingViews.size();
                for (int i = 0; i < j; i++)
                {
                    ((View)exitingViews.get(i)).setVisibility(0);
                }

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

            
            {
                fragmentView = view;
                exitingViews = arraylist;
                super();
            }
        });
    }

    public static void scheduleNameReset(ViewGroup viewgroup, ArrayList arraylist, Map map)
    {
        OneShotPreDrawListener.add(viewgroup, new Runnable(arraylist, map) {

            final Map val$nameOverrides;
            final ArrayList val$sharedElementsIn;

            public void run()
            {
                int j = sharedElementsIn.size();
                for (int i = 0; i < j; i++)
                {
                    View view = (View)sharedElementsIn.get(i);
                    String s = view.getTransitionName();
                    view.setTransitionName((String)nameOverrides.get(s));
                }

            }

            
            {
                sharedElementsIn = arraylist;
                nameOverrides = map;
                super();
            }
        });
    }

    public static void scheduleRemoveTargets(Object obj, Object obj1, ArrayList arraylist, Object obj2, ArrayList arraylist1, Object obj3, ArrayList arraylist2)
    {
        ((Transition)obj).addListener(new android.transition.Transition.TransitionListener(obj1, arraylist, obj2, arraylist1, obj3, arraylist2) {

            final Object val$enterTransition;
            final ArrayList val$enteringViews;
            final Object val$exitTransition;
            final ArrayList val$exitingViews;
            final Object val$sharedElementTransition;
            final ArrayList val$sharedElementsIn;

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
                if (enterTransition != null)
                {
                    FragmentTransitionCompat21.replaceTargets(enterTransition, enteringViews, null);
                }
                if (exitTransition != null)
                {
                    FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, null);
                }
                if (sharedElementTransition != null)
                {
                    FragmentTransitionCompat21.replaceTargets(sharedElementTransition, sharedElementsIn, null);
                }
            }

            
            {
                enterTransition = obj;
                enteringViews = arraylist;
                exitTransition = obj1;
                exitingViews = arraylist1;
                sharedElementTransition = obj2;
                sharedElementsIn = arraylist2;
                super();
            }
        });
    }

    public static void setEpicenter(Object obj, Rect rect)
    {
        if (obj != null)
        {
            ((Transition)obj).setEpicenterCallback(new android.transition.Transition.EpicenterCallback(rect) {

                final Rect val$epicenter;

                public Rect onGetEpicenter(Transition transition)
                {
                    if (epicenter == null || epicenter.isEmpty())
                    {
                        return null;
                    } else
                    {
                        return epicenter;
                    }
                }

            
            {
                epicenter = rect;
                super();
            }
            });
        }
    }

    public static void setEpicenter(Object obj, View view)
    {
        if (view != null)
        {
            obj = (Transition)obj;
            Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            ((Transition) (obj)).setEpicenterCallback(new android.transition.Transition.EpicenterCallback(rect) {

                final Rect val$epicenter;

                public Rect onGetEpicenter(Transition transition)
                {
                    return epicenter;
                }

            
            {
                epicenter = rect;
                super();
            }
            });
        }
    }

    public static void setNameOverridesOptimized(View view, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, Map map)
    {
        ArrayList arraylist3;
        int i;
        int k;
        k = arraylist1.size();
        arraylist3 = new ArrayList();
        i = 0;
_L2:
        String s;
        Object obj;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        obj = (View)arraylist.get(i);
        s = ((View) (obj)).getTransitionName();
        arraylist3.add(s);
        if (s != null)
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        int j;
        ((View) (obj)).setTransitionName(null);
        obj = (String)map.get(s);
        j = 0;
_L4:
        if (j < k)
        {
label0:
            {
                if (!((String) (obj)).equals(arraylist2.get(j)))
                {
                    break label0;
                }
                ((View)arraylist1.get(j)).setTransitionName(s);
            }
        }
          goto _L3
        j++;
          goto _L4
        OneShotPreDrawListener.add(view, new Runnable(k, arraylist1, arraylist2, arraylist, arraylist3) {

            final ArrayList val$inNames;
            final int val$numSharedElements;
            final ArrayList val$outNames;
            final ArrayList val$sharedElementsIn;
            final ArrayList val$sharedElementsOut;

            public void run()
            {
                for (int l = 0; l < numSharedElements; l++)
                {
                    ((View)sharedElementsIn.get(l)).setTransitionName((String)inNames.get(l));
                    ((View)sharedElementsOut.get(l)).setTransitionName((String)outNames.get(l));
                }

            }

            
            {
                numSharedElements = i;
                sharedElementsIn = arraylist;
                inNames = arraylist1;
                sharedElementsOut = arraylist2;
                outNames = arraylist3;
                super();
            }
        });
        return;
          goto _L3
    }

    public static void setNameOverridesUnoptimized(View view, ArrayList arraylist, Map map)
    {
        OneShotPreDrawListener.add(view, new Runnable(arraylist, map) {

            final Map val$nameOverrides;
            final ArrayList val$sharedElementsIn;

            public void run()
            {
                int j = sharedElementsIn.size();
                for (int i = 0; i < j; i++)
                {
                    View view1 = (View)sharedElementsIn.get(i);
                    String s = view1.getTransitionName();
                    if (s != null)
                    {
                        view1.setTransitionName(FragmentTransitionCompat21.findKeyForValue(nameOverrides, s));
                    }
                }

            }

            
            {
                sharedElementsIn = arraylist;
                nameOverrides = map;
                super();
            }
        });
    }

    public static void setSharedElementTargets(Object obj, View view, ArrayList arraylist)
    {
        obj = (TransitionSet)obj;
        List list = ((TransitionSet) (obj)).getTargets();
        list.clear();
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            bfsAddViewChildren(list, (View)arraylist.get(i));
        }

        list.add(view);
        arraylist.add(view);
        addTargets(obj, arraylist);
    }

    public static void swapSharedElementTargets(Object obj, ArrayList arraylist, ArrayList arraylist1)
    {
        obj = (TransitionSet)obj;
        if (obj != null)
        {
            ((TransitionSet) (obj)).getTargets().clear();
            ((TransitionSet) (obj)).getTargets().addAll(arraylist1);
            replaceTargets(obj, arraylist, arraylist1);
        }
    }

    public static Object wrapTransitionInSet(Object obj)
    {
        if (obj == null)
        {
            return null;
        } else
        {
            TransitionSet transitionset = new TransitionSet();
            transitionset.addTransition((Transition)obj);
            return transitionset;
        }
    }

}
