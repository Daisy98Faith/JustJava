// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package android.support.v4.app:
//            Fragment, BackStackRecord, FragmentManagerImpl, FragmentContainer, 
//            SharedElementCallback, FragmentTransitionCompat21, OneShotPreDrawListener, FragmentHostCallback

class FragmentTransition
{
    static class FragmentContainerTransition
    {

        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition()
        {
        }
    }


    private static final int INVERSE_OPS[] = {
        0, 3, 0, 1, 5, 4, 7, 6, 9, 8
    };

    FragmentTransition()
    {
    }

    private static void addSharedElementsWithMatchingNames(ArrayList arraylist, ArrayMap arraymap, Collection collection)
    {
        for (int i = arraymap.size() - 1; i >= 0; i--)
        {
            View view = (View)arraymap.valueAt(i);
            if (collection.contains(ViewCompat.getTransitionName(view)))
            {
                arraylist.add(view);
            }
        }

    }

    private static void addToFirstInLastOut(BackStackRecord backstackrecord, BackStackRecord.Op op, SparseArray sparsearray, boolean flag, boolean flag1)
    {
        Fragment fragment = op.fragment;
        if (fragment != null) goto _L2; else goto _L1
_L1:
        int j;
        return;
_L2:
        if ((j = fragment.mContainerId) == 0) goto _L1; else goto _L3
_L3:
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        Object obj;
        int i;
        if (flag)
        {
            i = INVERSE_OPS[op.cmd];
        } else
        {
            i = op.cmd;
        }
        flag9 = false;
        flag6 = false;
        flag7 = false;
        flag5 = false;
        flag2 = flag7;
        flag8 = flag9;
        flag3 = flag5;
        flag4 = flag6;
        i;
        JVM INSTR tableswitch 1 7: default 112
    //                   1 402
    //                   2 128
    //                   3 536
    //                   4 456
    //                   5 340
    //                   6 536
    //                   7 402;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L7 _L5
_L7:
        break MISSING_BLOCK_LABEL_536;
_L6:
        break; /* Loop/switch isn't completed */
_L4:
        flag4 = flag6;
        flag3 = flag5;
        flag8 = flag9;
        flag2 = flag7;
_L11:
        obj = (FragmentContainerTransition)sparsearray.get(j);
        op = ((BackStackRecord.Op) (obj));
        if (flag8)
        {
            op = ensureContainer(((FragmentContainerTransition) (obj)), sparsearray, j);
            op.lastIn = fragment;
            op.lastInIsPop = flag;
            op.lastInTransaction = backstackrecord;
        }
        if (!flag1 && flag3)
        {
            if (op != null && ((FragmentContainerTransition) (op)).firstOut == fragment)
            {
                op.firstOut = null;
            }
            obj = backstackrecord.mManager;
            if (fragment.mState < 1 && ((FragmentManagerImpl) (obj)).mCurState >= 1 && !backstackrecord.mAllowOptimization)
            {
                ((FragmentManagerImpl) (obj)).makeActive(fragment);
                ((FragmentManagerImpl) (obj)).moveToState(fragment, 1, 0, 0, false);
            }
        }
        obj = op;
        if (!flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (op != null)
        {
            obj = op;
            if (((FragmentContainerTransition) (op)).firstOut != null)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        obj = ensureContainer(op, sparsearray, j);
        obj.firstOut = fragment;
        obj.firstOutIsPop = flag;
        obj.firstOutTransaction = backstackrecord;
        if (flag1 || !flag4 || obj == null || ((FragmentContainerTransition) (obj)).lastIn != fragment) goto _L1; else goto _L10
_L10:
        obj.lastIn = null;
        return;
_L9:
        if (flag1)
        {
            if (fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded)
            {
                flag8 = true;
            } else
            {
                flag8 = false;
            }
        } else
        {
            flag8 = fragment.mHidden;
        }
        flag3 = true;
        flag2 = flag7;
        flag4 = flag6;
          goto _L11
_L5:
        if (flag1)
        {
            flag8 = fragment.mIsNewlyAdded;
        } else
        if (!fragment.mAdded && !fragment.mHidden)
        {
            flag8 = true;
        } else
        {
            flag8 = false;
        }
        flag3 = true;
        flag2 = flag7;
        flag4 = flag6;
          goto _L11
_L8:
        if (flag1)
        {
            if (fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
        } else
        if (fragment.mAdded && !fragment.mHidden)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag4 = true;
        flag8 = flag9;
        flag3 = flag5;
          goto _L11
        if (flag1)
        {
            if (!fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0F)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
        } else
        if (fragment.mAdded && !fragment.mHidden)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag4 = true;
        flag8 = flag9;
        flag3 = flag5;
          goto _L11
    }

    public static void calculateFragments(BackStackRecord backstackrecord, SparseArray sparsearray, boolean flag)
    {
        int j = backstackrecord.mOps.size();
        for (int i = 0; i < j; i++)
        {
            addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(i), sparsearray, false, flag);
        }

    }

    private static ArrayMap calculateNameOverrides(int i, ArrayList arraylist, ArrayList arraylist1, int j, int k)
    {
        ArrayMap arraymap = new ArrayMap();
        k--;
        while (k >= j) 
        {
            Object obj = (BackStackRecord)arraylist.get(k);
            if (((BackStackRecord) (obj)).interactsWith(i))
            {
                boolean flag = ((Boolean)arraylist1.get(k)).booleanValue();
                if (((BackStackRecord) (obj)).mSharedElementSourceNames != null)
                {
                    int i1 = ((BackStackRecord) (obj)).mSharedElementSourceNames.size();
                    ArrayList arraylist2;
                    ArrayList arraylist3;
                    int l;
                    if (flag)
                    {
                        arraylist3 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                        arraylist2 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
                    } else
                    {
                        arraylist2 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                        arraylist3 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
                    }
                    l = 0;
                    while (l < i1) 
                    {
                        obj = (String)arraylist2.get(l);
                        String s = (String)arraylist3.get(l);
                        String s1 = (String)arraymap.remove(s);
                        if (s1 != null)
                        {
                            arraymap.put(obj, s1);
                        } else
                        {
                            arraymap.put(obj, s);
                        }
                        l++;
                    }
                }
            }
            k--;
        }
        return arraymap;
    }

    public static void calculatePopFragments(BackStackRecord backstackrecord, SparseArray sparsearray, boolean flag)
    {
        if (backstackrecord.mManager.mContainer.onHasView())
        {
            int i = backstackrecord.mOps.size() - 1;
            while (i >= 0) 
            {
                addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(i), sparsearray, true, flag);
                i--;
            }
        }
    }

    private static void callSharedElementStartEnd(Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap, boolean flag1)
    {
        ArrayList arraylist;
label0:
        {
            if (flag)
            {
                fragment = fragment1.getEnterTransitionCallback();
            } else
            {
                fragment = fragment.getEnterTransitionCallback();
            }
            if (fragment != null)
            {
                fragment1 = new ArrayList();
                arraylist = new ArrayList();
                int i;
                int j;
                if (arraymap == null)
                {
                    i = 0;
                } else
                {
                    i = arraymap.size();
                }
                for (j = 0; j < i; j++)
                {
                    arraylist.add(arraymap.keyAt(j));
                    fragment1.add(arraymap.valueAt(j));
                }

                if (!flag1)
                {
                    break label0;
                }
                fragment.onSharedElementStart(arraylist, fragment1, null);
            }
            return;
        }
        fragment.onSharedElementEnd(arraylist, fragment1, null);
    }

    private static ArrayMap captureInSharedElements(ArrayMap arraymap, Object obj, FragmentContainerTransition fragmentcontainertransition)
    {
        Object obj1;
        View view;
        obj1 = fragmentcontainertransition.lastIn;
        view = ((Fragment) (obj1)).getView();
        if (!arraymap.isEmpty() && obj != null && view != null) goto _L2; else goto _L1
_L1:
        arraymap.clear();
        fragmentcontainertransition = null;
_L4:
        return fragmentcontainertransition;
_L2:
        ArrayMap arraymap1;
label0:
        {
            arraymap1 = new ArrayMap();
            FragmentTransitionCompat21.findNamedViews(arraymap1, view);
            obj = fragmentcontainertransition.lastInTransaction;
            int i;
            if (fragmentcontainertransition.lastInIsPop)
            {
                fragmentcontainertransition = ((Fragment) (obj1)).getExitTransitionCallback();
                obj = ((BackStackRecord) (obj)).mSharedElementSourceNames;
            } else
            {
                fragmentcontainertransition = ((Fragment) (obj1)).getEnterTransitionCallback();
                obj = ((BackStackRecord) (obj)).mSharedElementTargetNames;
            }
            arraymap1.retainAll(((Collection) (obj)));
            if (fragmentcontainertransition == null)
            {
                break label0;
            }
            fragmentcontainertransition.onMapSharedElements(((java.util.List) (obj)), arraymap1);
            i = ((ArrayList) (obj)).size() - 1;
            do
            {
                fragmentcontainertransition = arraymap1;
                if (i < 0)
                {
                    break;
                }
                obj1 = (String)((ArrayList) (obj)).get(i);
                fragmentcontainertransition = (View)arraymap1.get(obj1);
                if (fragmentcontainertransition == null)
                {
                    fragmentcontainertransition = findKeyForValue(arraymap, ((String) (obj1)));
                    if (fragmentcontainertransition != null)
                    {
                        arraymap.remove(fragmentcontainertransition);
                    }
                } else
                if (!((String) (obj1)).equals(ViewCompat.getTransitionName(fragmentcontainertransition)))
                {
                    obj1 = findKeyForValue(arraymap, ((String) (obj1)));
                    if (obj1 != null)
                    {
                        arraymap.put(obj1, ViewCompat.getTransitionName(fragmentcontainertransition));
                    }
                }
                i--;
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        retainValues(arraymap, arraymap1);
        return arraymap1;
    }

    private static ArrayMap captureOutSharedElements(ArrayMap arraymap, Object obj, FragmentContainerTransition fragmentcontainertransition)
    {
        if (!arraymap.isEmpty() && obj != null) goto _L2; else goto _L1
_L1:
        arraymap.clear();
        fragmentcontainertransition = null;
_L4:
        return fragmentcontainertransition;
_L2:
        ArrayMap arraymap1;
label0:
        {
            Object obj1 = fragmentcontainertransition.firstOut;
            arraymap1 = new ArrayMap();
            FragmentTransitionCompat21.findNamedViews(arraymap1, ((Fragment) (obj1)).getView());
            obj = fragmentcontainertransition.firstOutTransaction;
            int i;
            if (fragmentcontainertransition.firstOutIsPop)
            {
                fragmentcontainertransition = ((Fragment) (obj1)).getEnterTransitionCallback();
                obj = ((BackStackRecord) (obj)).mSharedElementTargetNames;
            } else
            {
                fragmentcontainertransition = ((Fragment) (obj1)).getExitTransitionCallback();
                obj = ((BackStackRecord) (obj)).mSharedElementSourceNames;
            }
            arraymap1.retainAll(((Collection) (obj)));
            if (fragmentcontainertransition == null)
            {
                break label0;
            }
            fragmentcontainertransition.onMapSharedElements(((java.util.List) (obj)), arraymap1);
            i = ((ArrayList) (obj)).size() - 1;
            do
            {
                fragmentcontainertransition = arraymap1;
                if (i < 0)
                {
                    break;
                }
                obj1 = (String)((ArrayList) (obj)).get(i);
                fragmentcontainertransition = (View)arraymap1.get(obj1);
                if (fragmentcontainertransition == null)
                {
                    arraymap.remove(obj1);
                } else
                if (!((String) (obj1)).equals(ViewCompat.getTransitionName(fragmentcontainertransition)))
                {
                    obj1 = (String)arraymap.remove(obj1);
                    arraymap.put(ViewCompat.getTransitionName(fragmentcontainertransition), obj1);
                }
                i--;
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        arraymap.retainAll(arraymap1.keySet());
        return arraymap1;
    }

    private static ArrayList configureEnteringExitingViews(Object obj, Fragment fragment, ArrayList arraylist, View view)
    {
        ArrayList arraylist1 = null;
        if (obj != null)
        {
            ArrayList arraylist2 = new ArrayList();
            FragmentTransitionCompat21.captureTransitioningViews(arraylist2, fragment.getView());
            if (arraylist != null)
            {
                arraylist2.removeAll(arraylist);
            }
            arraylist1 = arraylist2;
            if (!arraylist2.isEmpty())
            {
                arraylist2.add(view);
                FragmentTransitionCompat21.addTargets(obj, arraylist2);
                arraylist1 = arraylist2;
            }
        }
        return arraylist1;
    }

    private static Object configureSharedElementsOptimized(ViewGroup viewgroup, View view, ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, ArrayList arraylist, ArrayList arraylist1, Object obj, Object obj1)
    {
        Fragment fragment = fragmentcontainertransition.lastIn;
        Fragment fragment1 = fragmentcontainertransition.firstOut;
        if (fragment != null)
        {
            fragment.getView().setVisibility(0);
        }
        if (fragment == null || fragment1 == null)
        {
            return null;
        }
        boolean flag = fragmentcontainertransition.lastInIsPop;
        Object obj2;
        ArrayMap arraymap1;
        ArrayMap arraymap2;
        if (arraymap.isEmpty())
        {
            obj2 = null;
        } else
        {
            obj2 = getSharedElementTransition(fragment, fragment1, flag);
        }
        arraymap2 = captureOutSharedElements(arraymap, obj2, fragmentcontainertransition);
        arraymap1 = captureInSharedElements(arraymap, obj2, fragmentcontainertransition);
        if (arraymap.isEmpty())
        {
            arraymap = null;
            if (arraymap2 != null)
            {
                arraymap2.clear();
            }
            obj2 = arraymap;
            if (arraymap1 != null)
            {
                arraymap1.clear();
                obj2 = arraymap;
            }
        } else
        {
            addSharedElementsWithMatchingNames(arraylist, arraymap2, arraymap.keySet());
            addSharedElementsWithMatchingNames(arraylist1, arraymap1, arraymap.values());
        }
        if (obj == null && obj1 == null && obj2 == null)
        {
            return null;
        }
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap2, true);
        if (obj2 != null)
        {
            arraylist1.add(view);
            FragmentTransitionCompat21.setSharedElementTargets(obj2, view, arraylist);
            setOutEpicenter(obj2, obj1, arraymap2, fragmentcontainertransition.firstOutIsPop, fragmentcontainertransition.firstOutTransaction);
            arraylist = new Rect();
            fragmentcontainertransition = getInEpicenterView(arraymap1, fragmentcontainertransition, obj, flag);
            view = fragmentcontainertransition;
            arraymap = arraylist;
            if (fragmentcontainertransition != null)
            {
                FragmentTransitionCompat21.setEpicenter(obj, arraylist);
                arraymap = arraylist;
                view = fragmentcontainertransition;
            }
        } else
        {
            arraymap = null;
            view = null;
        }
        OneShotPreDrawListener.add(viewgroup, new Runnable(fragment, fragment1, flag, arraymap1, view, arraymap) {

            final Rect val$epicenter;
            final View val$epicenterView;
            final Fragment val$inFragment;
            final boolean val$inIsPop;
            final ArrayMap val$inSharedElements;
            final Fragment val$outFragment;

            public void run()
            {
                FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
                if (epicenterView != null)
                {
                    FragmentTransitionCompat21.getBoundsOnScreen(epicenterView, epicenter);
                }
            }

            
            {
                inFragment = fragment;
                outFragment = fragment1;
                inIsPop = flag;
                inSharedElements = arraymap;
                epicenterView = view;
                epicenter = rect;
                super();
            }
        });
        return obj2;
    }

    private static Object configureSharedElementsUnoptimized(ViewGroup viewgroup, View view, ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, ArrayList arraylist, ArrayList arraylist1, Object obj, Object obj1)
    {
        Fragment fragment = fragmentcontainertransition.lastIn;
        Fragment fragment1 = fragmentcontainertransition.firstOut;
        if (fragment == null || fragment1 == null)
        {
            return null;
        }
        boolean flag = fragmentcontainertransition.lastInIsPop;
        Object obj2;
        ArrayMap arraymap1;
        if (arraymap.isEmpty())
        {
            obj2 = null;
        } else
        {
            obj2 = getSharedElementTransition(fragment, fragment1, flag);
        }
        arraymap1 = captureOutSharedElements(arraymap, obj2, fragmentcontainertransition);
        if (arraymap.isEmpty())
        {
            obj2 = null;
        } else
        {
            arraylist.addAll(arraymap1.values());
        }
        if (obj == null && obj1 == null && obj2 == null)
        {
            return null;
        }
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap1, true);
        if (obj2 != null)
        {
            Rect rect = new Rect();
            FragmentTransitionCompat21.setSharedElementTargets(obj2, view, arraylist);
            setOutEpicenter(obj2, obj1, arraymap1, fragmentcontainertransition.firstOutIsPop, fragmentcontainertransition.firstOutTransaction);
            obj1 = rect;
            if (obj != null)
            {
                FragmentTransitionCompat21.setEpicenter(obj, rect);
                obj1 = rect;
            }
        } else
        {
            obj1 = null;
        }
        OneShotPreDrawListener.add(viewgroup, new Runnable(arraymap, obj2, fragmentcontainertransition, arraylist1, view, fragment, fragment1, flag, arraylist, obj, ((Rect) (obj1))) {

            final Object val$enterTransition;
            final Object val$finalSharedElementTransition;
            final FragmentContainerTransition val$fragments;
            final Rect val$inEpicenter;
            final Fragment val$inFragment;
            final boolean val$inIsPop;
            final ArrayMap val$nameOverrides;
            final View val$nonExistentView;
            final Fragment val$outFragment;
            final ArrayList val$sharedElementsIn;
            final ArrayList val$sharedElementsOut;

            public void run()
            {
                Object obj3 = FragmentTransition.captureInSharedElements(nameOverrides, finalSharedElementTransition, fragments);
                if (obj3 != null)
                {
                    sharedElementsIn.addAll(((ArrayMap) (obj3)).values());
                    sharedElementsIn.add(nonExistentView);
                }
                FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, ((ArrayMap) (obj3)), false);
                if (finalSharedElementTransition != null)
                {
                    FragmentTransitionCompat21.swapSharedElementTargets(finalSharedElementTransition, sharedElementsOut, sharedElementsIn);
                    obj3 = FragmentTransition.getInEpicenterView(((ArrayMap) (obj3)), fragments, enterTransition, inIsPop);
                    if (obj3 != null)
                    {
                        FragmentTransitionCompat21.getBoundsOnScreen(((View) (obj3)), inEpicenter);
                    }
                }
            }

            
            {
                nameOverrides = arraymap;
                finalSharedElementTransition = obj;
                fragments = fragmentcontainertransition;
                sharedElementsIn = arraylist;
                nonExistentView = view;
                inFragment = fragment;
                outFragment = fragment1;
                inIsPop = flag;
                sharedElementsOut = arraylist1;
                enterTransition = obj1;
                inEpicenter = rect;
                super();
            }
        });
        return obj2;
    }

    private static void configureTransitionsOptimized(FragmentManagerImpl fragmentmanagerimpl, int i, FragmentContainerTransition fragmentcontainertransition, View view, ArrayMap arraymap)
    {
        ViewGroup viewgroup = null;
        if (fragmentmanagerimpl.mContainer.onHasView())
        {
            viewgroup = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(i);
        }
        if (viewgroup != null)
        {
            Object obj3 = fragmentcontainertransition.lastIn;
            Object obj2 = fragmentcontainertransition.firstOut;
            boolean flag = fragmentcontainertransition.lastInIsPop;
            boolean flag1 = fragmentcontainertransition.firstOutIsPop;
            fragmentmanagerimpl = new ArrayList();
            ArrayList arraylist = new ArrayList();
            Object obj = getEnterTransition(((Fragment) (obj3)), flag);
            Object obj1 = getExitTransition(((Fragment) (obj2)), flag1);
            fragmentcontainertransition = ((FragmentContainerTransition) (configureSharedElementsOptimized(viewgroup, view, arraymap, fragmentcontainertransition, arraylist, fragmentmanagerimpl, obj, obj1)));
            if (obj != null || fragmentcontainertransition != null || obj1 != null)
            {
                ArrayList arraylist1 = configureEnteringExitingViews(obj1, ((Fragment) (obj2)), arraylist, view);
                view = configureEnteringExitingViews(obj, ((Fragment) (obj3)), fragmentmanagerimpl, view);
                setViewVisibility(view, 4);
                obj3 = mergeTransitions(obj, obj1, fragmentcontainertransition, ((Fragment) (obj3)), flag);
                if (obj3 != null)
                {
                    replaceHide(obj1, ((Fragment) (obj2)), arraylist1);
                    obj2 = FragmentTransitionCompat21.prepareSetNameOverridesOptimized(fragmentmanagerimpl);
                    FragmentTransitionCompat21.scheduleRemoveTargets(obj3, obj, view, obj1, arraylist1, fragmentcontainertransition, fragmentmanagerimpl);
                    FragmentTransitionCompat21.beginDelayedTransition(viewgroup, obj3);
                    FragmentTransitionCompat21.setNameOverridesOptimized(viewgroup, arraylist, fragmentmanagerimpl, ((ArrayList) (obj2)), arraymap);
                    setViewVisibility(view, 0);
                    FragmentTransitionCompat21.swapSharedElementTargets(fragmentcontainertransition, arraylist, fragmentmanagerimpl);
                    return;
                }
            }
        }
    }

    private static void configureTransitionsUnoptimized(FragmentManagerImpl fragmentmanagerimpl, int i, FragmentContainerTransition fragmentcontainertransition, View view, ArrayMap arraymap)
    {
        ViewGroup viewgroup = null;
        if (fragmentmanagerimpl.mContainer.onHasView())
        {
            viewgroup = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(i);
        }
        if (viewgroup != null)
        {
            Fragment fragment = fragmentcontainertransition.lastIn;
            Object obj2 = fragmentcontainertransition.firstOut;
            boolean flag = fragmentcontainertransition.lastInIsPop;
            boolean flag1 = fragmentcontainertransition.firstOutIsPop;
            Object obj = getEnterTransition(fragment, flag);
            fragmentmanagerimpl = ((FragmentManagerImpl) (getExitTransition(((Fragment) (obj2)), flag1)));
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist = new ArrayList();
            Object obj1 = configureSharedElementsUnoptimized(viewgroup, view, arraymap, fragmentcontainertransition, arraylist1, arraylist, obj, fragmentmanagerimpl);
            if (obj != null || obj1 != null || fragmentmanagerimpl != null)
            {
                obj2 = configureEnteringExitingViews(fragmentmanagerimpl, ((Fragment) (obj2)), arraylist1, view);
                if (obj2 == null || ((ArrayList) (obj2)).isEmpty())
                {
                    fragmentmanagerimpl = null;
                }
                FragmentTransitionCompat21.addTarget(obj, view);
                fragmentcontainertransition = ((FragmentContainerTransition) (mergeTransitions(obj, fragmentmanagerimpl, obj1, fragment, fragmentcontainertransition.lastInIsPop)));
                if (fragmentcontainertransition != null)
                {
                    ArrayList arraylist2 = new ArrayList();
                    FragmentTransitionCompat21.scheduleRemoveTargets(fragmentcontainertransition, obj, arraylist2, fragmentmanagerimpl, ((ArrayList) (obj2)), obj1, arraylist);
                    scheduleTargetChange(viewgroup, fragment, view, arraylist, obj, arraylist2, fragmentmanagerimpl, ((ArrayList) (obj2)));
                    FragmentTransitionCompat21.setNameOverridesUnoptimized(viewgroup, arraylist, arraymap);
                    FragmentTransitionCompat21.beginDelayedTransition(viewgroup, fragmentcontainertransition);
                    FragmentTransitionCompat21.scheduleNameReset(viewgroup, arraylist, arraymap);
                    return;
                }
            }
        }
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentcontainertransition, SparseArray sparsearray, int i)
    {
        FragmentContainerTransition fragmentcontainertransition1 = fragmentcontainertransition;
        if (fragmentcontainertransition == null)
        {
            fragmentcontainertransition1 = new FragmentContainerTransition();
            sparsearray.put(i, fragmentcontainertransition1);
        }
        return fragmentcontainertransition1;
    }

    private static String findKeyForValue(ArrayMap arraymap, String s)
    {
        int j = arraymap.size();
        for (int i = 0; i < j; i++)
        {
            if (s.equals(arraymap.valueAt(i)))
            {
                return (String)arraymap.keyAt(i);
            }
        }

        return null;
    }

    private static Object getEnterTransition(Fragment fragment, boolean flag)
    {
        if (fragment == null)
        {
            return null;
        }
        if (flag)
        {
            fragment = ((Fragment) (fragment.getReenterTransition()));
        } else
        {
            fragment = ((Fragment) (fragment.getEnterTransition()));
        }
        return FragmentTransitionCompat21.cloneTransition(fragment);
    }

    private static Object getExitTransition(Fragment fragment, boolean flag)
    {
        if (fragment == null)
        {
            return null;
        }
        if (flag)
        {
            fragment = ((Fragment) (fragment.getReturnTransition()));
        } else
        {
            fragment = ((Fragment) (fragment.getExitTransition()));
        }
        return FragmentTransitionCompat21.cloneTransition(fragment);
    }

    private static View getInEpicenterView(ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, Object obj, boolean flag)
    {
        fragmentcontainertransition = fragmentcontainertransition.lastInTransaction;
        if (obj != null && ((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames != null && !((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.isEmpty())
        {
            if (flag)
            {
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.get(0);
            } else
            {
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementTargetNames.get(0);
            }
            return (View)arraymap.get(fragmentcontainertransition);
        } else
        {
            return null;
        }
    }

    private static Object getSharedElementTransition(Fragment fragment, Fragment fragment1, boolean flag)
    {
        if (fragment == null || fragment1 == null)
        {
            return null;
        }
        if (flag)
        {
            fragment = ((Fragment) (fragment1.getSharedElementReturnTransition()));
        } else
        {
            fragment = ((Fragment) (fragment.getSharedElementEnterTransition()));
        }
        return FragmentTransitionCompat21.wrapTransitionInSet(FragmentTransitionCompat21.cloneTransition(fragment));
    }

    private static Object mergeTransitions(Object obj, Object obj1, Object obj2, Fragment fragment, boolean flag)
    {
        boolean flag2 = true;
        boolean flag1 = flag2;
        if (obj != null)
        {
            flag1 = flag2;
            if (obj1 != null)
            {
                flag1 = flag2;
                if (fragment != null)
                {
                    if (flag)
                    {
                        flag1 = fragment.getAllowReturnTransitionOverlap();
                    } else
                    {
                        flag1 = fragment.getAllowEnterTransitionOverlap();
                    }
                }
            }
        }
        if (flag1)
        {
            return FragmentTransitionCompat21.mergeTransitionsTogether(obj1, obj, obj2);
        } else
        {
            return FragmentTransitionCompat21.mergeTransitionsInSequence(obj1, obj, obj2);
        }
    }

    private static void replaceHide(Object obj, Fragment fragment, ArrayList arraylist)
    {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged)
        {
            fragment.setHideReplaced(true);
            FragmentTransitionCompat21.scheduleHideFragmentView(obj, fragment.getView(), arraylist);
            OneShotPreDrawListener.add(fragment.mContainer, new Runnable(arraylist) {

                final ArrayList val$exitingViews;

                public void run()
                {
                    FragmentTransition.setViewVisibility(exitingViews, 4);
                }

            
            {
                exitingViews = arraylist;
                super();
            }
            });
        }
    }

    private static void retainValues(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for (int i = arraymap.size() - 1; i >= 0; i--)
        {
            if (!arraymap1.containsKey((String)arraymap.valueAt(i)))
            {
                arraymap.removeAt(i);
            }
        }

    }

    private static void scheduleTargetChange(ViewGroup viewgroup, Fragment fragment, View view, ArrayList arraylist, Object obj, ArrayList arraylist1, Object obj1, ArrayList arraylist2)
    {
        OneShotPreDrawListener.add(viewgroup, new Runnable(obj, view, fragment, arraylist, arraylist1, arraylist2, obj1) {

            final Object val$enterTransition;
            final ArrayList val$enteringViews;
            final Object val$exitTransition;
            final ArrayList val$exitingViews;
            final Fragment val$inFragment;
            final View val$nonExistentView;
            final ArrayList val$sharedElementsIn;

            public void run()
            {
                if (enterTransition != null)
                {
                    FragmentTransitionCompat21.removeTarget(enterTransition, nonExistentView);
                    ArrayList arraylist3 = FragmentTransition.configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
                    enteringViews.addAll(arraylist3);
                }
                if (exitingViews != null)
                {
                    if (exitTransition != null)
                    {
                        ArrayList arraylist4 = new ArrayList();
                        arraylist4.add(nonExistentView);
                        FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, arraylist4);
                    }
                    exitingViews.clear();
                    exitingViews.add(nonExistentView);
                }
            }

            
            {
                enterTransition = obj;
                nonExistentView = view;
                inFragment = fragment;
                sharedElementsIn = arraylist;
                enteringViews = arraylist1;
                exitingViews = arraylist2;
                exitTransition = obj1;
                super();
            }
        });
    }

    private static void setOutEpicenter(Object obj, Object obj1, ArrayMap arraymap, boolean flag, BackStackRecord backstackrecord)
    {
        if (backstackrecord.mSharedElementSourceNames != null && !backstackrecord.mSharedElementSourceNames.isEmpty())
        {
            if (flag)
            {
                backstackrecord = (String)backstackrecord.mSharedElementTargetNames.get(0);
            } else
            {
                backstackrecord = (String)backstackrecord.mSharedElementSourceNames.get(0);
            }
            arraymap = (View)arraymap.get(backstackrecord);
            FragmentTransitionCompat21.setEpicenter(obj, arraymap);
            if (obj1 != null)
            {
                FragmentTransitionCompat21.setEpicenter(obj1, arraymap);
            }
        }
    }

    private static void setViewVisibility(ArrayList arraylist, int i)
    {
        if (arraylist != null)
        {
            int j = arraylist.size() - 1;
            while (j >= 0) 
            {
                ((View)arraylist.get(j)).setVisibility(i);
                j--;
            }
        }
    }

    static void startTransitions(FragmentManagerImpl fragmentmanagerimpl, ArrayList arraylist, ArrayList arraylist1, int i, int j, boolean flag)
    {
        if (fragmentmanagerimpl.mCurState >= 1 && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SparseArray sparsearray = new SparseArray();
        int k = i;
        while (k < j) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k);
            if (((Boolean)arraylist1.get(k)).booleanValue())
            {
                calculatePopFragments(backstackrecord, sparsearray, flag);
            } else
            {
                calculateFragments(backstackrecord, sparsearray, flag);
            }
            k++;
        }
        if (sparsearray.size() != 0)
        {
            View view = new View(fragmentmanagerimpl.mHost.getContext());
            int i1 = sparsearray.size();
            int l = 0;
            while (l < i1) 
            {
                int j1 = sparsearray.keyAt(l);
                ArrayMap arraymap = calculateNameOverrides(j1, arraylist, arraylist1, i, j);
                FragmentContainerTransition fragmentcontainertransition = (FragmentContainerTransition)sparsearray.valueAt(l);
                if (flag)
                {
                    configureTransitionsOptimized(fragmentmanagerimpl, j1, fragmentcontainertransition, view, arraymap);
                } else
                {
                    configureTransitionsUnoptimized(fragmentmanagerimpl, j1, fragmentcontainertransition, view, arraymap);
                }
                l++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }






}
