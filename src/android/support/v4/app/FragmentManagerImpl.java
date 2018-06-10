// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.os.BuildCompat;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, Fragment, FragmentTransition, BackStackRecord, 
//            FragmentHostCallback, LoaderManagerImpl, SuperNotCalledException, FragmentContainer, 
//            FragmentManagerState, FragmentManagerNonConfig, FragmentState, BackStackState, 
//            FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
    implements android.view.LayoutInflater.Factory2
{
    static class AnimateOnHWLayerIfNeededListener
        implements android.view.animation.Animation.AnimationListener
    {

        private android.view.animation.Animation.AnimationListener mOriginalListener;
        private boolean mShouldRunOnHWLayer;
        View mView;

        public void onAnimationEnd(Animation animation)
        {
            if (mView != null && mShouldRunOnHWLayer)
            {
                if (ViewCompat.isAttachedToWindow(mView) || BuildCompat.isAtLeastN())
                {
                    mView.post(new Runnable() {

                        final AnimateOnHWLayerIfNeededListener this$0;

                        public void run()
                        {
                            mView.setLayerType(0, null);
                        }

            
            {
                this$0 = AnimateOnHWLayerIfNeededListener.this;
                super();
            }
                    });
                } else
                {
                    mView.setLayerType(0, null);
                }
            }
            if (mOriginalListener != null)
            {
                mOriginalListener.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation)
        {
            if (mOriginalListener != null)
            {
                mOriginalListener.onAnimationRepeat(animation);
            }
        }

        public void onAnimationStart(Animation animation)
        {
            if (mOriginalListener != null)
            {
                mOriginalListener.onAnimationStart(animation);
            }
        }

        public AnimateOnHWLayerIfNeededListener(View view, Animation animation)
        {
            if (view == null || animation == null)
            {
                return;
            } else
            {
                mView = view;
                return;
            }
        }

        public AnimateOnHWLayerIfNeededListener(View view, Animation animation, android.view.animation.Animation.AnimationListener animationlistener)
        {
            if (view == null || animation == null)
            {
                return;
            } else
            {
                mOriginalListener = animationlistener;
                mView = view;
                mShouldRunOnHWLayer = true;
                return;
            }
        }
    }

    static class FragmentTag
    {

        public static final int Fragment[] = {
            0x1010003, 0x10100d0, 0x10100d1
        };
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;


        FragmentTag()
        {
        }
    }

    static interface OpGenerator
    {

        public abstract boolean generateOps(ArrayList arraylist, ArrayList arraylist1);
    }

    private class PopBackStackState
        implements OpGenerator
    {

        final int mFlags;
        final int mId;
        final String mName;
        final FragmentManagerImpl this$0;

        public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
        {
            if (mPrimaryNav != null && mId < 0 && mName == null)
            {
                FragmentManager fragmentmanager = mPrimaryNav.peekChildFragmentManager();
                if (fragmentmanager != null && fragmentmanager.popBackStackImmediate())
                {
                    return false;
                }
            }
            return popBackStackState(arraylist, arraylist1, mName, mId, mFlags);
        }

        PopBackStackState(String s, int i, int j)
        {
            this$0 = FragmentManagerImpl.this;
            super();
            mName = s;
            mId = i;
            mFlags = j;
        }
    }

    static class StartEnterTransitionListener
        implements Fragment.OnStartEnterTransitionListener
    {

        private final boolean mIsBack;
        private int mNumPostponed;
        private final BackStackRecord mRecord;

        public void cancelTransaction()
        {
            mRecord.mManager.completeExecute(mRecord, mIsBack, false, false);
        }

        public void completeTransaction()
        {
            boolean flag1 = false;
            FragmentManagerImpl fragmentmanagerimpl;
            boolean flag;
            int j;
            if (mNumPostponed > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            fragmentmanagerimpl = mRecord.mManager;
            j = fragmentmanagerimpl.mAdded.size();
            for (int i = 0; i < j; i++)
            {
                Fragment fragment = (Fragment)fragmentmanagerimpl.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (flag && fragment.isPostponed())
                {
                    fragment.startPostponedEnterTransition();
                }
            }

            fragmentmanagerimpl = mRecord.mManager;
            BackStackRecord backstackrecord = mRecord;
            boolean flag2 = mIsBack;
            if (!flag)
            {
                flag1 = true;
            }
            fragmentmanagerimpl.completeExecute(backstackrecord, flag2, flag1, true);
        }

        public boolean isReady()
        {
            return mNumPostponed == 0;
        }

        public void onStartEnterTransition()
        {
            mNumPostponed = mNumPostponed - 1;
            if (mNumPostponed != 0)
            {
                return;
            } else
            {
                mRecord.mManager.scheduleCommit();
                return;
            }
        }

        public void startListening()
        {
            mNumPostponed = mNumPostponed + 1;
        }



        StartEnterTransitionListener(BackStackRecord backstackrecord, boolean flag)
        {
            mIsBack = flag;
            mRecord = backstackrecord;
        }
    }


    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    static final boolean HONEYCOMB;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField = null;
    ArrayList mActive;
    ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mAvailIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private CopyOnWriteArrayList mLifecycleCallbacks;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    Fragment mPrimaryNav;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable mTmpActions[];
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;

    FragmentManagerImpl()
    {
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new Runnable() {

            final FragmentManagerImpl this$0;

            public void run()
            {
                execPendingActions();
            }

            
            {
                this$0 = FragmentManagerImpl.this;
                super();
            }
        };
    }

    private void addAddedFragments(ArraySet arrayset)
    {
        if (mCurState >= 1)
        {
            int k = Math.min(mCurState, 4);
            int i;
            int j;
            if (mAdded == null)
            {
                i = 0;
            } else
            {
                i = mAdded.size();
            }
            j = 0;
            while (j < i) 
            {
                Fragment fragment = (Fragment)mAdded.get(j);
                if (fragment.mState < k)
                {
                    moveToState(fragment, k, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
                    {
                        arrayset.add(fragment);
                    }
                }
                j++;
            }
        }
    }

    private void checkStateLoss()
    {
        if (mStateSaved)
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (mNoTransactionsBecause != null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Can not perform this action inside of ").append(mNoTransactionsBecause).toString());
        } else
        {
            return;
        }
    }

    private void cleanupExec()
    {
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
    }

    private void completeExecute(BackStackRecord backstackrecord, boolean flag, boolean flag1, boolean flag2)
    {
        ArrayList arraylist = new ArrayList(1);
        ArrayList arraylist1 = new ArrayList(1);
        arraylist.add(backstackrecord);
        arraylist1.add(Boolean.valueOf(flag));
        executeOps(arraylist, arraylist1, 0, 1);
        if (flag1)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, 0, 1, true);
        }
        if (flag2)
        {
            moveToState(mCurState, true);
        }
        if (mActive != null)
        {
            int j = mActive.size();
            int i = 0;
            while (i < j) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backstackrecord.interactsWith(fragment.mContainerId))
                {
                    if (android.os.Build.VERSION.SDK_INT >= 11 && fragment.mPostponedAlpha > 0.0F)
                    {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (flag2)
                    {
                        fragment.mPostponedAlpha = 0.0F;
                    } else
                    {
                        fragment.mPostponedAlpha = -1F;
                        fragment.mIsNewlyAdded = false;
                    }
                }
                i++;
            }
        }
    }

    private void endAnimatingAwayFragments()
    {
        int i;
        int j;
        if (mActive == null)
        {
            i = 0;
        } else
        {
            i = mActive.size();
        }
        for (j = 0; j < i; j++)
        {
            Fragment fragment = (Fragment)mActive.get(j);
            if (fragment == null || fragment.getAnimatingAway() == null)
            {
                continue;
            }
            int k = fragment.getStateAfterAnimating();
            Object obj = fragment.getAnimatingAway();
            fragment.setAnimatingAway(null);
            obj = ((View) (obj)).getAnimation();
            if (obj != null)
            {
                ((Animation) (obj)).cancel();
            }
            moveToState(fragment, k, 0, 0, false);
        }

    }

    private void ensureExecReady(boolean flag)
    {
        if (mExecutingActions)
        {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != mHost.getHandler().getLooper())
        {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!flag)
        {
            checkStateLoss();
        }
        if (mTmpRecords == null)
        {
            mTmpRecords = new ArrayList();
            mTmpIsPop = new ArrayList();
        }
        mExecutingActions = true;
        executePostponedTransaction(null, null);
        mExecutingActions = false;
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private static void executeOps(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        while (i < j) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if (((Boolean)arraylist1.get(i)).booleanValue())
            {
                backstackrecord.bumpBackStackNesting(-1);
                boolean flag;
                if (i == j - 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                backstackrecord.executePopOps(flag);
            } else
            {
                backstackrecord.bumpBackStackNesting(1);
                backstackrecord.executeOps();
            }
            i++;
        }
    }

    private void executeOpsTogether(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        boolean flag1 = ((BackStackRecord)arraylist.get(i)).mAllowOptimization;
        boolean flag = false;
        Fragment fragment;
        int k;
        if (mTmpAddedFragments == null)
        {
            mTmpAddedFragments = new ArrayList();
        } else
        {
            mTmpAddedFragments.clear();
        }
        if (mAdded != null)
        {
            mTmpAddedFragments.addAll(mAdded);
        }
        fragment = getPrimaryNavigationFragment();
        k = i;
        while (k < j) 
        {
            BackStackRecord backstackrecord1 = (BackStackRecord)arraylist.get(k);
            if (!((Boolean)arraylist1.get(k)).booleanValue())
            {
                fragment = backstackrecord1.expandOps(mTmpAddedFragments, fragment);
            } else
            {
                fragment = backstackrecord1.trackAddedFragmentsInPop(mTmpAddedFragments, fragment);
            }
            if (flag || backstackrecord1.mAddToBackStack)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            k++;
        }
        mTmpAddedFragments.clear();
        if (!flag1)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, j, false);
        }
        executeOps(arraylist, arraylist1, i, j);
        k = j;
        if (flag1)
        {
            ArraySet arrayset = new ArraySet();
            addAddedFragments(arrayset);
            k = postponePostponableTransactions(arraylist, arraylist1, i, j, arrayset);
            makeRemovedFragmentsInvisible(arrayset);
        }
        if (k != i && flag1)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, k, true);
            moveToState(mCurState, true);
        }
        for (; i < j; i++)
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if (((Boolean)arraylist1.get(i)).booleanValue() && backstackrecord.mIndex >= 0)
            {
                freeBackStackIndex(backstackrecord.mIndex);
                backstackrecord.mIndex = -1;
            }
            backstackrecord.runOnCommitRunnables();
        }

        if (flag)
        {
            reportBackStackChanged();
        }
    }

    private void executePostponedTransaction(ArrayList arraylist, ArrayList arraylist1)
    {
        StartEnterTransitionListener startentertransitionlistener;
        int i;
        int j;
        int k;
        int l;
        if (mPostponedTransactions == null)
        {
            i = 0;
        } else
        {
            i = mPostponedTransactions.size();
        }
        k = 0;
        j = i;
        i = k;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_241;
        }
        startentertransitionlistener = (StartEnterTransitionListener)mPostponedTransactions.get(i);
        if (arraylist == null || startentertransitionlistener.mIsBack)
        {
            break; /* Loop/switch isn't completed */
        }
        k = arraylist.indexOf(startentertransitionlistener.mRecord);
        if (k == -1 || !((Boolean)arraylist1.get(k)).booleanValue())
        {
            break; /* Loop/switch isn't completed */
        }
        startentertransitionlistener.cancelTransaction();
        l = j;
        k = i;
_L4:
        i = k + 1;
        j = l;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_21;
_L1:
        if (!startentertransitionlistener.isReady())
        {
            k = i;
            l = j;
            if (arraylist == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            k = i;
            l = j;
            if (!startentertransitionlistener.mRecord.interactsWith(arraylist, 0, arraylist.size()))
            {
                continue; /* Loop/switch isn't completed */
            }
        }
label0:
        {
            mPostponedTransactions.remove(i);
            k = i - 1;
            l = j - 1;
            if (arraylist == null || startentertransitionlistener.mIsBack)
            {
                break label0;
            }
            i = arraylist.indexOf(startentertransitionlistener.mRecord);
            if (i == -1 || !((Boolean)arraylist1.get(i)).booleanValue())
            {
                break label0;
            }
            startentertransitionlistener.cancelTransaction();
        }
        continue; /* Loop/switch isn't completed */
        startentertransitionlistener.completeTransaction();
        if (true) goto _L4; else goto _L3
_L3:
    }

    private Fragment findFragmentUnder(Fragment fragment)
    {
        Object obj;
        ViewGroup viewgroup;
        viewgroup = fragment.mContainer;
        obj = fragment.mView;
        if (viewgroup != null && obj != null) goto _L2; else goto _L1
_L1:
        fragment = null;
_L6:
        return fragment;
_L2:
        int i = mAdded.indexOf(fragment) - 1;
_L7:
        if (i < 0) goto _L4; else goto _L3
_L3:
        obj = (Fragment)mAdded.get(i);
        if (((Fragment) (obj)).mContainer != viewgroup)
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = ((Fragment) (obj));
        if (((Fragment) (obj)).mView != null) goto _L6; else goto _L5
_L5:
        i--;
          goto _L7
_L4:
        return null;
    }

    private void forcePostponedTransactions()
    {
        if (mPostponedTransactions != null)
        {
            for (; !mPostponedTransactions.isEmpty(); ((StartEnterTransitionListener)mPostponedTransactions.remove(0)).completeTransaction()) { }
        }
    }

    private boolean generateOpsForPendingActions(ArrayList arraylist, ArrayList arraylist1)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if (mPendingActions != null && mPendingActions.size() != 0)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        int j = mPendingActions.size();
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        flag |= ((OpGenerator)mPendingActions.get(i)).generateOps(arraylist, arraylist1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        mPendingActions.clear();
        mHost.getHandler().removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        return flag;
        arraylist;
        this;
        JVM INSTR monitorexit ;
        throw arraylist;
    }

    static Animation makeFadeAnimation(Context context, float f, float f1)
    {
        context = new AlphaAnimation(f, f1);
        context.setInterpolator(DECELERATE_CUBIC);
        context.setDuration(220L);
        return context;
    }

    static Animation makeOpenCloseAnimation(Context context, float f, float f1, float f2, float f3)
    {
        context = new AnimationSet(false);
        Object obj = new ScaleAnimation(f, f1, f, f1, 1, 0.5F, 1, 0.5F);
        ((ScaleAnimation) (obj)).setInterpolator(DECELERATE_QUINT);
        ((ScaleAnimation) (obj)).setDuration(220L);
        context.addAnimation(((Animation) (obj)));
        obj = new AlphaAnimation(f2, f3);
        ((AlphaAnimation) (obj)).setInterpolator(DECELERATE_CUBIC);
        ((AlphaAnimation) (obj)).setDuration(220L);
        context.addAnimation(((Animation) (obj)));
        return context;
    }

    private void makeRemovedFragmentsInvisible(ArraySet arrayset)
    {
        int j = arrayset.size();
        int i = 0;
        while (i < j) 
        {
            Fragment fragment = (Fragment)arrayset.valueAt(i);
            if (!fragment.mAdded)
            {
                View view = fragment.getView();
                if (android.os.Build.VERSION.SDK_INT < 11)
                {
                    fragment.getView().setVisibility(4);
                } else
                {
                    fragment.mPostponedAlpha = view.getAlpha();
                    view.setAlpha(0.0F);
                }
            }
            i++;
        }
    }

    static boolean modifiesAlpha(Animation animation)
    {
        if (!(animation instanceof AlphaAnimation)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(animation instanceof AnimationSet))
            {
                break label0;
            }
            animation = ((AnimationSet)animation).getAnimations();
            int i = 0;
            do
            {
                if (i >= animation.size())
                {
                    break label0;
                }
                if (animation.get(i) instanceof AlphaAnimation)
                {
                    break;
                }
                i++;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    private void optimizeAndExecuteOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if (arraylist != null && !arraylist.isEmpty())
        {
            if (arraylist1 == null || arraylist.size() != arraylist1.size())
            {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(arraylist, arraylist1);
            int i1 = arraylist.size();
            int k = 0;
            for (int i = 0; i < i1;)
            {
                int l = i;
                int j = k;
                if (!((BackStackRecord)arraylist.get(i)).mAllowOptimization)
                {
                    if (k != i)
                    {
                        executeOpsTogether(arraylist, arraylist1, k, i);
                    }
                    k = i + 1;
                    j = k;
                    if (((Boolean)arraylist1.get(i)).booleanValue())
                    {
                        do
                        {
                            j = k;
                            if (k >= i1)
                            {
                                break;
                            }
                            j = k;
                            if (!((Boolean)arraylist1.get(k)).booleanValue())
                            {
                                break;
                            }
                            j = k;
                            if (((BackStackRecord)arraylist.get(k)).mAllowOptimization)
                            {
                                break;
                            }
                            k++;
                        } while (true);
                    }
                    executeOpsTogether(arraylist, arraylist1, i, j);
                    i = j;
                    l = j - 1;
                    j = i;
                }
                i = l + 1;
                k = j;
            }

            if (k != i1)
            {
                executeOpsTogether(arraylist, arraylist1, k, i1);
                return;
            }
        }
    }

    private boolean popBackStackImmediate(String s, int i, int j)
    {
        boolean flag;
        execPendingActions();
        ensureExecReady(true);
        if (mPrimaryNav != null && i < 0 && s == null)
        {
            FragmentManager fragmentmanager = mPrimaryNav.peekChildFragmentManager();
            if (fragmentmanager != null && fragmentmanager.popBackStackImmediate())
            {
                return true;
            }
        }
        flag = popBackStackState(mTmpRecords, mTmpIsPop, s, i, j);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        mExecutingActions = true;
        optimizeAndExecuteOps(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        return flag;
        s;
        cleanupExec();
        throw s;
    }

    private int postponePostponableTransactions(ArrayList arraylist, ArrayList arraylist1, int i, int j, ArraySet arrayset)
    {
        int l = j;
        int k = j - 1;
        while (k >= i) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k);
            boolean flag1 = ((Boolean)arraylist1.get(k)).booleanValue();
            int i1;
            boolean flag;
            if (backstackrecord.isPostponed() && !backstackrecord.interactsWith(arraylist, k + 1, j))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            i1 = l;
            if (flag)
            {
                if (mPostponedTransactions == null)
                {
                    mPostponedTransactions = new ArrayList();
                }
                StartEnterTransitionListener startentertransitionlistener = new StartEnterTransitionListener(backstackrecord, flag1);
                mPostponedTransactions.add(startentertransitionlistener);
                backstackrecord.setOnStartPostponedListener(startentertransitionlistener);
                if (flag1)
                {
                    backstackrecord.executeOps();
                } else
                {
                    backstackrecord.executePopOps(false);
                }
                i1 = l - 1;
                if (k != i1)
                {
                    arraylist.remove(k);
                    arraylist.add(i1, backstackrecord);
                }
                addAddedFragments(arrayset);
            }
            k--;
            l = i1;
        }
        return l;
    }

    public static int reverseTransit(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 4097: 
            return 8194;

        case 8194: 
            return 4097;

        case 4099: 
            return 4099;
        }
    }

    private void scheduleCommit()
    {
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (mPostponedTransactions != null && !mPostponedTransactions.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mPendingActions == null || mPendingActions.size() != 1)
        {
            flag1 = false;
        }
          goto _L1
_L2:
        mHost.getHandler().removeCallbacks(mExecCommit);
        mHost.getHandler().post(mExecCommit);
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        if (!flag && !flag1) goto _L3; else goto _L2
    }

    private void setHWLayerAnimListenerIfAlpha(View view, Animation animation)
    {
        android.view.animation.Animation.AnimationListener animationlistener;
        while (view == null || animation == null || !shouldRunOnHWLayer(view, animation)) 
        {
            return;
        }
        animationlistener = null;
        android.view.animation.Animation.AnimationListener animationlistener1;
        if (sAnimationListenerField == null)
        {
            sAnimationListenerField = android/view/animation/Animation.getDeclaredField("mListener");
            sAnimationListenerField.setAccessible(true);
        }
        animationlistener1 = (android.view.animation.Animation.AnimationListener)sAnimationListenerField.get(animation);
        animationlistener = animationlistener1;
_L2:
        view.setLayerType(2, null);
        animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animation, animationlistener));
        return;
        Object obj;
        obj;
        Log.e("FragmentManager", "No field with the name mListener is found in Animation class", ((Throwable) (obj)));
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("FragmentManager", "Cannot access Animation's mListener field", ((Throwable) (obj)));
        if (true) goto _L2; else goto _L1
_L1:
    }

    static boolean shouldRunOnHWLayer(View view, Animation animation)
    {
        return android.os.Build.VERSION.SDK_INT >= 19 && view.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(view) && modifiesAlpha(animation);
    }

    private void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        Log.e("FragmentManager", "Activity state:");
        Object obj = new PrintWriter(new LogWriter("FragmentManager"));
        if (mHost != null)
        {
            try
            {
                mHost.onDump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                Log.e("FragmentManager", "Failed dumping state", ((Throwable) (obj)));
            }
        } else
        {
            try
            {
                dump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            catch (Exception exception)
            {
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        }
        throw runtimeexception;
    }

    public static int transitToStyleIndex(int i, boolean flag)
    {
        switch (i)
        {
        default:
            return -1;

        case 4097: 
            return !flag ? 2 : 1;

        case 8194: 
            return !flag ? 4 : 3;

        case 4099: 
            break;
        }
        return !flag ? 6 : 5;
    }

    void addBackStackState(BackStackRecord backstackrecord)
    {
        if (mBackStack == null)
        {
            mBackStack = new ArrayList();
        }
        mBackStack.add(backstackrecord);
        reportBackStackChanged();
    }

    public void addFragment(Fragment fragment, boolean flag)
    {
        if (mAdded == null)
        {
            mAdded = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("add: ").append(fragment).toString());
        }
        makeActive(fragment);
        if (!fragment.mDetached)
        {
            if (mAdded.contains(fragment))
            {
                throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
            }
            mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null)
            {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            if (flag)
            {
                moveToState(fragment);
            }
        }
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners == null)
        {
            mBackStackChangeListeners = new ArrayList();
        }
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        i = mBackStackIndices.size();
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(i, backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        backstackrecord;
        this;
        JVM INSTR monitorexit ;
        throw backstackrecord;
    }

    public void attachController(FragmentHostCallback fragmenthostcallback, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if (mHost != null)
        {
            throw new IllegalStateException("Already attached");
        } else
        {
            mHost = fragmenthostcallback;
            mContainer = fragmentcontainer;
            mParent = fragment;
            return;
        }
    }

    public void attachFragment(Fragment fragment)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("attach: ").append(fragment).toString());
        }
        if (fragment.mDetached)
        {
            fragment.mDetached = false;
            if (!fragment.mAdded)
            {
                if (mAdded == null)
                {
                    mAdded = new ArrayList();
                }
                if (mAdded.contains(fragment))
                {
                    throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
                }
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("add from attach: ").append(fragment).toString());
                }
                mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    void completeShowHideFragment(Fragment fragment)
    {
        if (fragment.mView != null)
        {
            int i = fragment.getNextTransition();
            Animation animation;
            boolean flag;
            if (!fragment.mHidden)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            animation = loadAnimation(fragment, i, flag, fragment.getNextTransitionStyle());
            if (animation != null)
            {
                setHWLayerAnimListenerIfAlpha(fragment.mView, animation);
                fragment.mView.startAnimation(animation);
                setHWLayerAnimListenerIfAlpha(fragment.mView, animation);
                animation.start();
            }
            if (fragment.mHidden && !fragment.isHideReplaced())
            {
                i = 8;
            } else
            {
                i = 0;
            }
            fragment.mView.setVisibility(i);
            if (fragment.isHideReplaced())
            {
                fragment.setHideReplaced(false);
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
        {
            mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public void detachFragment(Fragment fragment)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("detach: ").append(fragment).toString());
        }
        if (!fragment.mDetached)
        {
            fragment.mDetached = true;
            if (fragment.mAdded)
            {
                if (mAdded != null)
                {
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("remove from detach: ").append(fragment).toString());
                    }
                    mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated()
    {
        mStateSaved = false;
        mExecutingActions = true;
        moveToState(2, false);
        mExecutingActions = false;
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performConfigurationChanged(configuration);
                }
            }

        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null && fragment.performContextItemSelected(menuitem))
                {
                    return true;
                }
            }

        }
        return false;
    }

    public void dispatchCreate()
    {
        mStateSaved = false;
        mExecutingActions = true;
        moveToState(1, false);
        mExecutingActions = false;
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag1 = false;
        boolean flag = false;
        ArrayList arraylist1 = null;
        ArrayList arraylist = null;
        if (mAdded != null)
        {
            int i = 0;
            do
            {
                arraylist1 = arraylist;
                flag1 = flag;
                if (i >= mAdded.size())
                {
                    break;
                }
                Fragment fragment = (Fragment)mAdded.get(i);
                arraylist1 = arraylist;
                flag1 = flag;
                if (fragment != null)
                {
                    arraylist1 = arraylist;
                    flag1 = flag;
                    if (fragment.performCreateOptionsMenu(menu, menuinflater))
                    {
                        flag1 = true;
                        arraylist1 = arraylist;
                        if (arraylist == null)
                        {
                            arraylist1 = new ArrayList();
                        }
                        arraylist1.add(fragment);
                    }
                }
                i++;
                arraylist = arraylist1;
                flag = flag1;
            } while (true);
        }
        if (mCreatedMenus != null)
        {
            for (int j = 0; j < mCreatedMenus.size(); j++)
            {
                menu = (Fragment)mCreatedMenus.get(j);
                if (arraylist1 == null || !arraylist1.contains(menu))
                {
                    menu.onDestroyOptionsMenu();
                }
            }

        }
        mCreatedMenus = arraylist1;
        return flag1;
    }

    public void dispatchDestroy()
    {
        mDestroyed = true;
        execPendingActions();
        mExecutingActions = true;
        moveToState(0, false);
        mExecutingActions = false;
        mHost = null;
        mContainer = null;
        mParent = null;
    }

    public void dispatchDestroyView()
    {
        mExecutingActions = true;
        moveToState(1, false);
        mExecutingActions = false;
    }

    public void dispatchLowMemory()
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performLowMemory();
                }
            }

        }
    }

    public void dispatchMultiWindowModeChanged(boolean flag)
    {
        if (mAdded != null)
        {
            int i = mAdded.size() - 1;
            while (i >= 0) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performMultiWindowModeChanged(flag);
                }
                i--;
            }
        }
    }

    void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentActivityCreated(fragment, bundle, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentActivityCreated(this, fragment, bundle);
                }
            }
        }
    }

    void dispatchOnFragmentAttached(Fragment fragment, Context context, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentAttached(fragment, context, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentAttached(this, fragment, context);
                }
            }
        }
    }

    void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentCreated(fragment, bundle, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentCreated(this, fragment, bundle);
                }
            }
        }
    }

    void dispatchOnFragmentDestroyed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDestroyed(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDestroyed(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentDetached(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDetached(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDetached(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentPaused(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPaused(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPaused(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentPreAttached(Fragment fragment, Context context, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPreAttached(fragment, context, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreAttached(this, fragment, context);
                }
            }
        }
    }

    void dispatchOnFragmentResumed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentResumed(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentResumed(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentSaveInstanceState(this, fragment, bundle);
                }
            }
        }
    }

    void dispatchOnFragmentStarted(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStarted(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStarted(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentStopped(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStopped(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStopped(this, fragment);
                }
            }
        }
    }

    void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewCreated(this, fragment, view, bundle);
                }
            }
        }
    }

    void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if (fragmentmanager instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewDestroyed(fragment, true);
            }
        }
        if (mLifecycleCallbacks != null)
        {
            Iterator iterator = mLifecycleCallbacks.iterator();
            while (iterator.hasNext()) 
            {
                Pair pair = (Pair)iterator.next();
                if (!flag || ((Boolean)pair.second).booleanValue())
                {
                    ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewDestroyed(this, fragment);
                }
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null && fragment.performOptionsItemSelected(menuitem))
                {
                    return true;
                }
            }

        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performOptionsMenuClosed(menu);
                }
            }

        }
    }

    public void dispatchPause()
    {
        mExecutingActions = true;
        moveToState(4, false);
        mExecutingActions = false;
    }

    public void dispatchPictureInPictureModeChanged(boolean flag)
    {
        if (mAdded != null)
        {
            int i = mAdded.size() - 1;
            while (i >= 0) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performPictureInPictureModeChanged(flag);
                }
                i--;
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        boolean flag1 = false;
        boolean flag = false;
        if (mAdded != null)
        {
            int i = 0;
            do
            {
                flag1 = flag;
                if (i >= mAdded.size())
                {
                    break;
                }
                Fragment fragment = (Fragment)mAdded.get(i);
                flag1 = flag;
                if (fragment != null)
                {
                    flag1 = flag;
                    if (fragment.performPrepareOptionsMenu(menu))
                    {
                        flag1 = true;
                    }
                }
                i++;
                flag = flag1;
            } while (true);
        }
        return flag1;
    }

    public void dispatchReallyStop()
    {
        mExecutingActions = true;
        moveToState(2, false);
        mExecutingActions = false;
    }

    public void dispatchResume()
    {
        mStateSaved = false;
        mExecutingActions = true;
        moveToState(5, false);
        mExecutingActions = false;
    }

    public void dispatchStart()
    {
        mStateSaved = false;
        mExecutingActions = true;
        moveToState(4, false);
        mExecutingActions = false;
    }

    public void dispatchStop()
    {
        mStateSaved = true;
        mExecutingActions = true;
        moveToState(3, false);
        mExecutingActions = false;
    }

    void doPendingDeferredStart()
    {
        if (mHavePendingDeferredStart)
        {
            boolean flag = false;
            for (int i = 0; i < mActive.size();)
            {
                Fragment fragment = (Fragment)mActive.get(i);
                boolean flag1 = flag;
                if (fragment != null)
                {
                    flag1 = flag;
                    if (fragment.mLoaderManager != null)
                    {
                        flag1 = flag | fragment.mLoaderManager.hasRunningLoaders();
                    }
                }
                i++;
                flag = flag1;
            }

            if (!flag)
            {
                mHavePendingDeferredStart = false;
                startPendingDeferredFragments();
            }
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if (mActive != null)
        {
            int k1 = mActive.size();
            if (k1 > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for (int i = 0; i < k1; i++)
                {
                    Fragment fragment = (Fragment)mActive.get(i);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.print(": ");
                    printwriter.println(fragment);
                    if (fragment != null)
                    {
                        fragment.dump(s1, filedescriptor, printwriter, as);
                    }
                }

            }
        }
        if (mAdded != null)
        {
            int l1 = mAdded.size();
            if (l1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Added Fragments:");
                for (int j = 0; j < l1; j++)
                {
                    Fragment fragment1 = (Fragment)mAdded.get(j);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j);
                    printwriter.print(": ");
                    printwriter.println(fragment1.toString());
                }

            }
        }
        if (mCreatedMenus != null)
        {
            int i2 = mCreatedMenus.size();
            if (i2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for (int k = 0; k < i2; k++)
                {
                    Fragment fragment2 = (Fragment)mCreatedMenus.get(k);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(k);
                    printwriter.print(": ");
                    printwriter.println(fragment2.toString());
                }

            }
        }
        if (mBackStack != null)
        {
            int j2 = mBackStack.size();
            if (j2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack:");
                for (int l = 0; l < j2; l++)
                {
                    BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(l);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord.toString());
                    backstackrecord.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        this;
        JVM INSTR monitorenter ;
        if (mBackStackIndices == null) goto _L2; else goto _L1
_L1:
        int k2 = mBackStackIndices.size();
        if (k2 <= 0) goto _L2; else goto _L3
_L3:
        printwriter.print(s);
        printwriter.println("Back Stack Indices:");
        int i1 = 0;
_L4:
        if (i1 >= k2)
        {
            break; /* Loop/switch isn't completed */
        }
        filedescriptor = (BackStackRecord)mBackStackIndices.get(i1);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(i1);
        printwriter.print(": ");
        printwriter.println(filedescriptor);
        i1++;
        if (true) goto _L4; else goto _L2
_L2:
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if (mPendingActions != null)
        {
            int l2 = mPendingActions.size();
            if (l2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for (int j1 = 0; j1 < l2; j1++)
                {
                    filedescriptor = (OpGenerator)mPendingActions.get(j1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j1);
                    printwriter.print(": ");
                    printwriter.println(filedescriptor);
                }

            }
        }
        break MISSING_BLOCK_LABEL_706;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mHost=");
        printwriter.println(mHost);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if (mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if (mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        if (mNoTransactionsBecause != null)
        {
            printwriter.print(s);
            printwriter.print("  mNoTransactionsBecause=");
            printwriter.println(mNoTransactionsBecause);
        }
        if (mAvailIndices != null && mAvailIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("  mAvailIndices: ");
            printwriter.println(Arrays.toString(mAvailIndices.toArray()));
        }
        return;
    }

    public void enqueueAction(OpGenerator opgenerator, boolean flag)
    {
        if (!flag)
        {
            checkStateLoss();
        }
        this;
        JVM INSTR monitorenter ;
        if (mDestroyed || mHost == null)
        {
            throw new IllegalStateException("Activity has been destroyed");
        }
        break MISSING_BLOCK_LABEL_40;
        opgenerator;
        this;
        JVM INSTR monitorexit ;
        throw opgenerator;
        if (mPendingActions == null)
        {
            mPendingActions = new ArrayList();
        }
        mPendingActions.add(opgenerator);
        scheduleCommit();
        this;
        JVM INSTR monitorexit ;
    }

    void ensureInflatedFragmentView(Fragment fragment)
    {
label0:
        {
            if (fragment.mFromLayout && !fragment.mPerformedCreateView)
            {
                fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
                if (fragment.mView == null)
                {
                    break label0;
                }
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if (fragment.mHidden)
                {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
            }
            return;
        }
        fragment.mInnerView = null;
    }

    public boolean execPendingActions()
    {
        boolean flag;
        ensureExecReady(true);
        flag = false;
_L2:
        if (!generateOpsForPendingActions(mTmpRecords, mTmpIsPop))
        {
            break; /* Loop/switch isn't completed */
        }
        mExecutingActions = true;
        optimizeAndExecuteOps(mTmpRecords, mTmpIsPop);
        cleanupExec();
        flag = true;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        cleanupExec();
        throw exception;
_L1:
        doPendingDeferredStart();
        return flag;
    }

    public void execSingleAction(OpGenerator opgenerator, boolean flag)
    {
        ensureExecReady(flag);
        if (!opgenerator.generateOps(mTmpRecords, mTmpIsPop))
        {
            break MISSING_BLOCK_LABEL_43;
        }
        mExecutingActions = true;
        optimizeAndExecuteOps(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        return;
        opgenerator;
        cleanupExec();
        throw opgenerator;
    }

    public boolean executePendingTransactions()
    {
        boolean flag = execPendingActions();
        forcePostponedTransactions();
        return flag;
    }

    public Fragment findFragmentById(int i)
    {
        if (mAdded == null) goto _L2; else goto _L1
_L1:
        int j = mAdded.size() - 1;
_L11:
        if (j < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(j);
        if (fragment == null || fragment.mFragmentId != i) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        j--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (mActive == null)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        j = mActive.size() - 1;
_L9:
        if (j < 0) goto _L7; else goto _L6
_L6:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.get(j);
        if (fragment1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = fragment1;
        if (fragment1.mFragmentId == i) goto _L4; else goto _L8
_L8:
        j--;
          goto _L9
_L7:
        return null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public Fragment findFragmentByTag(String s)
    {
        if (mAdded == null || s == null) goto _L2; else goto _L1
_L1:
        int i = mAdded.size() - 1;
_L11:
        if (i < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(i);
        if (fragment == null || !s.equals(fragment.mTag)) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        i--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (mActive == null || s == null)
        {
            break MISSING_BLOCK_LABEL_132;
        }
        i = mActive.size() - 1;
_L9:
        if (i < 0) goto _L7; else goto _L6
_L6:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.get(i);
        if (fragment1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = fragment1;
        if (s.equals(fragment1.mTag)) goto _L4; else goto _L8
_L8:
        i--;
          goto _L9
_L7:
        return null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public Fragment findFragmentByWho(String s)
    {
        if (mActive != null && s != null)
        {
            for (int i = mActive.size() - 1; i >= 0; i--)
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment == null)
                {
                    continue;
                }
                fragment = fragment.findFragmentByWho(s);
                if (fragment != null)
                {
                    return fragment;
                }
            }

        }
        return null;
    }

    public void freeBackStackIndex(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing back stack index ").append(i).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public int getBackStackEntryCount()
    {
        if (mBackStack != null)
        {
            return mBackStack.size();
        } else
        {
            return 0;
        }
    }

    public Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        if (i == -1)
        {
            bundle = null;
        } else
        {
            if (i >= mActive.size())
            {
                throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
            }
            Fragment fragment = (Fragment)mActive.get(i);
            bundle = fragment;
            if (fragment == null)
            {
                throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
                return fragment;
            }
        }
        return bundle;
    }

    public List getFragments()
    {
        ArrayList arraylist = new ArrayList();
        if (mActive != null)
        {
            arraylist.addAll(mActive);
        }
        return arraylist;
    }

    android.view.LayoutInflater.Factory2 getLayoutInflaterFactory()
    {
        return this;
    }

    public Fragment getPrimaryNavigationFragment()
    {
        return mPrimaryNav;
    }

    public void hideFragment(Fragment fragment)
    {
        boolean flag = true;
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("hide: ").append(fragment).toString());
        }
        if (!fragment.mHidden)
        {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged)
            {
                flag = false;
            }
            fragment.mHiddenChanged = flag;
        }
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    boolean isStateAtLeast(int i)
    {
        return mCurState >= i;
    }

    public boolean isStateSaved()
    {
        return mStateSaved;
    }

    Animation loadAnimation(Fragment fragment, int i, boolean flag, int j)
    {
        Animation animation = fragment.onCreateAnimation(i, flag, fragment.getNextAnim());
        if (animation != null)
        {
            return animation;
        }
        if (fragment.getNextAnim() != 0)
        {
            fragment = AnimationUtils.loadAnimation(mHost.getContext(), fragment.getNextAnim());
            if (fragment != null)
            {
                return fragment;
            }
        }
        if (i == 0)
        {
            return null;
        }
        i = transitToStyleIndex(i, flag);
        if (i < 0)
        {
            return null;
        }
        switch (i)
        {
        default:
            i = j;
            if (j == 0)
            {
                i = j;
                if (mHost.onHasWindowAnimations())
                {
                    i = mHost.onGetWindowAnimations();
                }
            }
            if (i == 0)
            {
                return null;
            } else
            {
                return null;
            }

        case 1: // '\001'
            return makeOpenCloseAnimation(mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);

        case 2: // '\002'
            return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);

        case 3: // '\003'
            return makeOpenCloseAnimation(mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);

        case 4: // '\004'
            return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);

        case 5: // '\005'
            return makeFadeAnimation(mHost.getContext(), 0.0F, 1.0F);

        case 6: // '\006'
            return makeFadeAnimation(mHost.getContext(), 1.0F, 0.0F);
        }
    }

    void makeActive(Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            if (mAvailIndices == null || mAvailIndices.size() <= 0)
            {
                if (mActive == null)
                {
                    mActive = new ArrayList();
                }
                fragment.setIndex(mActive.size(), mParent);
                mActive.add(fragment);
            } else
            {
                fragment.setIndex(((Integer)mAvailIndices.remove(mAvailIndices.size() - 1)).intValue(), mParent);
                mActive.set(fragment.mIndex, fragment);
            }
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Allocated fragment index ").append(fragment).toString());
                return;
            }
        }
    }

    void makeInactive(Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            return;
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing fragment index ").append(fragment).toString());
        }
        mActive.set(fragment.mIndex, null);
        if (mAvailIndices == null)
        {
            mAvailIndices = new ArrayList();
        }
        mAvailIndices.add(Integer.valueOf(fragment.mIndex));
        mHost.inactivateFragment(fragment.mWho);
        fragment.initState();
    }

    void moveFragmentToExpectedState(Fragment fragment)
    {
        if (fragment != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = mCurState;
        int i = j;
        Object obj;
        if (fragment.mRemoving)
        {
            if (fragment.isInBackStack())
            {
                i = Math.min(j, 1);
            } else
            {
                i = Math.min(j, 0);
            }
        }
        moveToState(fragment, i, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = findFragmentUnder(fragment);
        if (obj != null)
        {
            obj = ((Fragment) (obj)).mView;
            ViewGroup viewgroup = fragment.mContainer;
            i = viewgroup.indexOfChild(((View) (obj)));
            j = viewgroup.indexOfChild(fragment.mView);
            if (j < i)
            {
                viewgroup.removeViewAt(j);
                viewgroup.addView(fragment.mView, i);
            }
        }
        if (!fragment.mIsNewlyAdded || fragment.mContainer == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (android.os.Build.VERSION.SDK_INT < 11)
        {
            fragment.mView.setVisibility(0);
        } else
        if (fragment.mPostponedAlpha > 0.0F)
        {
            fragment.mView.setAlpha(fragment.mPostponedAlpha);
        }
        fragment.mPostponedAlpha = 0.0F;
        fragment.mIsNewlyAdded = false;
        obj = loadAnimation(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
        if (obj != null)
        {
            setHWLayerAnimListenerIfAlpha(fragment.mView, ((Animation) (obj)));
            fragment.mView.startAnimation(((Animation) (obj)));
        }
        if (!fragment.mHiddenChanged) goto _L1; else goto _L3
_L3:
        completeShowHideFragment(fragment);
        return;
    }

    void moveToState(int i, boolean flag)
    {
        if (mHost == null && i != 0)
        {
            throw new IllegalStateException("No activity");
        }
        if (flag || i != mCurState) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mCurState = i;
        if (mActive == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
        int j = 0;
        if (mAdded != null)
        {
            int i1 = mAdded.size();
            int k = 0;
            do
            {
                i = j;
                if (k >= i1)
                {
                    break;
                }
                Fragment fragment = (Fragment)mAdded.get(k);
                moveFragmentToExpectedState(fragment);
                i = j;
                if (fragment.mLoaderManager != null)
                {
                    i = j | fragment.mLoaderManager.hasRunningLoaders();
                }
                k++;
                j = i;
            } while (true);
        }
        int j1 = mActive.size();
        boolean flag1 = false;
        j = i;
        for (i = ((flag1) ? 1 : 0); i < j1;)
        {
            int l;
label0:
            {
                Fragment fragment1 = (Fragment)mActive.get(i);
                l = j;
                if (fragment1 == null)
                {
                    break label0;
                }
                if (!fragment1.mRemoving)
                {
                    l = j;
                    if (!fragment1.mDetached)
                    {
                        break label0;
                    }
                }
                l = j;
                if (!fragment1.mIsNewlyAdded)
                {
                    moveFragmentToExpectedState(fragment1);
                    l = j;
                    if (fragment1.mLoaderManager != null)
                    {
                        l = j | fragment1.mLoaderManager.hasRunningLoaders();
                    }
                }
            }
            i++;
            j = l;
        }

        if (j == 0)
        {
            startPendingDeferredFragments();
        }
        if (mNeedMenuInvalidate && mHost != null && mCurState == 5)
        {
            mHost.onSupportInvalidateOptionsMenu();
            mNeedMenuInvalidate = false;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    void moveToState(Fragment fragment)
    {
        moveToState(fragment, mCurState, 0, 0, false);
    }

    void moveToState(Fragment fragment, int i, int j, int k, boolean flag)
    {
        int l;
label0:
        {
            if (fragment.mAdded)
            {
                l = i;
                if (!fragment.mDetached)
                {
                    break label0;
                }
            }
            l = i;
            if (i > 1)
            {
                l = 1;
            }
        }
        int j1 = l;
        if (fragment.mRemoving)
        {
            j1 = l;
            if (l > fragment.mState)
            {
                j1 = fragment.mState;
            }
        }
        i = j1;
        if (fragment.mDeferStart)
        {
            i = j1;
            if (fragment.mState < 4)
            {
                i = j1;
                if (j1 > 3)
                {
                    i = 3;
                }
            }
        }
        if (fragment.mState > i) goto _L2; else goto _L1
_L1:
        if (!fragment.mFromLayout || fragment.mInLayout) goto _L4; else goto _L3
_L3:
        return;
_L4:
        int i1;
        int k1;
        if (fragment.getAnimatingAway() != null)
        {
            fragment.setAnimatingAway(null);
            moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, true);
        }
        k = i;
        i1 = i;
        k1 = i;
        j = i;
        fragment.mState;
        JVM INSTR tableswitch 0 4: default 188
    //                   0 263
    //                   1 686
    //                   2 1081
    //                   3 1100
    //                   4 1154;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L6:
        break; /* Loop/switch isn't completed */
_L5:
        i1 = i;
_L12:
        if (fragment.mState != i1)
        {
            Log.w("FragmentManager", (new StringBuilder()).append("moveToState: Fragment state for ").append(fragment).append(" not updated inline; ").append("expected state ").append(i1).append(" found ").append(fragment.mState).toString());
            fragment.mState = i1;
            return;
        }
        if (true) goto _L3; else goto _L11
_L11:
        k = i;
        if (i > 0)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("moveto CREATED: ").append(fragment).toString());
            }
            k = i;
            if (fragment.mSavedFragmentState != null)
            {
                fragment.mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                fragment.mTarget = getFragment(fragment.mSavedFragmentState, "android:target_state");
                if (fragment.mTarget != null)
                {
                    fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                }
                fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                k = i;
                if (!fragment.mUserVisibleHint)
                {
                    fragment.mDeferStart = true;
                    k = i;
                    if (i > 3)
                    {
                        k = 3;
                    }
                }
            }
            fragment.mHost = mHost;
            fragment.mParentFragment = mParent;
            FragmentManagerImpl fragmentmanagerimpl;
            if (mParent != null)
            {
                fragmentmanagerimpl = mParent.mChildFragmentManager;
            } else
            {
                fragmentmanagerimpl = mHost.getFragmentManagerImpl();
            }
            fragment.mFragmentManager = fragmentmanagerimpl;
            if (fragment.mTarget != null)
            {
                if (!mActive.contains(fragment.mTarget))
                {
                    throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" declared target fragment ").append(fragment.mTarget).append(" that does not belong to this FragmentManager!").toString());
                }
                if (fragment.mTarget.mState < 1)
                {
                    moveToState(fragment.mTarget, 1, 0, 0, true);
                }
            }
            dispatchOnFragmentPreAttached(fragment, mHost.getContext(), false);
            fragment.mCalled = false;
            fragment.onAttach(mHost.getContext());
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(fragment).append(" did not call through to super.onAttach()").toString());
            }
            ViewGroup viewgroup;
            if (fragment.mParentFragment == null)
            {
                mHost.onAttachFragment(fragment);
            } else
            {
                fragment.mParentFragment.onAttachFragment(fragment);
            }
            dispatchOnFragmentAttached(fragment, mHost.getContext(), false);
            if (!fragment.mRetaining)
            {
                fragment.performCreate(fragment.mSavedFragmentState);
                dispatchOnFragmentCreated(fragment, fragment.mSavedFragmentState, false);
            } else
            {
                fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                fragment.mState = 1;
            }
            fragment.mRetaining = false;
        }
_L7:
        ensureInflatedFragmentView(fragment);
        i1 = k;
        if (k > 1)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("moveto ACTIVITY_CREATED: ").append(fragment).toString());
            }
            if (!fragment.mFromLayout)
            {
                Object obj = null;
                if (fragment.mContainerId != 0)
                {
                    if (fragment.mContainerId == -1)
                    {
                        throwException(new IllegalArgumentException((new StringBuilder()).append("Cannot create fragment ").append(fragment).append(" for a container view with no id").toString()));
                    }
                    viewgroup = (ViewGroup)mContainer.onFindViewById(fragment.mContainerId);
                    obj = viewgroup;
                    if (viewgroup == null)
                    {
                        obj = viewgroup;
                        if (!fragment.mRestored)
                        {
                            try
                            {
                                obj = fragment.getResources().getResourceName(fragment.mContainerId);
                            }
                            catch (android.content.res.Resources.NotFoundException notfoundexception)
                            {
                                notfoundexception = "unknown";
                            }
                            throwException(new IllegalArgumentException((new StringBuilder()).append("No view found for id 0x").append(Integer.toHexString(fragment.mContainerId)).append(" (").append(((String) (obj))).append(") for fragment ").append(fragment).toString()));
                            obj = viewgroup;
                        }
                    }
                }
                fragment.mContainer = ((ViewGroup) (obj));
                fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), ((ViewGroup) (obj)), fragment.mSavedFragmentState);
                if (fragment.mView != null)
                {
                    fragment.mInnerView = fragment.mView;
                    fragment.mView.setSaveFromParentEnabled(false);
                    if (obj != null)
                    {
                        ((ViewGroup) (obj)).addView(fragment.mView);
                    }
                    if (fragment.mHidden)
                    {
                        fragment.mView.setVisibility(8);
                    }
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                    dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                    if (fragment.mView.getVisibility() == 0 && fragment.mContainer != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    fragment.mIsNewlyAdded = flag;
                } else
                {
                    fragment.mInnerView = null;
                }
            }
            fragment.performActivityCreated(fragment.mSavedFragmentState);
            dispatchOnFragmentActivityCreated(fragment, fragment.mSavedFragmentState, false);
            if (fragment.mView != null)
            {
                fragment.restoreViewState(fragment.mSavedFragmentState);
            }
            fragment.mSavedFragmentState = null;
            i1 = k;
        }
_L8:
        k1 = i1;
        if (i1 > 2)
        {
            fragment.mState = 3;
            k1 = i1;
        }
_L9:
        j = k1;
        if (k1 > 3)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("moveto STARTED: ").append(fragment).toString());
            }
            fragment.performStart();
            dispatchOnFragmentStarted(fragment, false);
            j = k1;
        }
_L10:
        i1 = j;
        if (j > 4)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("moveto RESUMED: ").append(fragment).toString());
            }
            fragment.performResume();
            dispatchOnFragmentResumed(fragment, false);
            fragment.mSavedFragmentState = null;
            fragment.mSavedViewState = null;
            i1 = j;
        }
          goto _L12
_L2:
        i1 = i;
        if (fragment.mState <= i) goto _L12; else goto _L13
_L13:
        View view;
        switch (fragment.mState)
        {
        default:
            i1 = i;
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_1326;

        case 5: // '\005'
            if (i < 5)
            {
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom RESUMED: ").append(fragment).toString());
                }
                fragment.performPause();
                dispatchOnFragmentPaused(fragment, false);
            }
            // fall through

        case 4: // '\004'
            if (i < 4)
            {
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom STARTED: ").append(fragment).toString());
                }
                fragment.performStop();
                dispatchOnFragmentStopped(fragment, false);
            }
            // fall through

        case 3: // '\003'
            if (i < 3)
            {
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom STOPPED: ").append(fragment).toString());
                }
                fragment.performReallyStop();
            }
            break;

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_1517;
_L14:
        i1 = i;
        if (i < 1)
        {
            if (mDestroyed && fragment.getAnimatingAway() != null)
            {
                view = fragment.getAnimatingAway();
                fragment.setAnimatingAway(null);
                view.clearAnimation();
            }
            Animation animation;
            Object obj1;
            if (fragment.getAnimatingAway() != null)
            {
                fragment.setStateAfterAnimating(i);
                i1 = 1;
            } else
            {
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom CREATED: ").append(fragment).toString());
                }
                if (!fragment.mRetaining)
                {
                    fragment.performDestroy();
                    dispatchOnFragmentDestroyed(fragment, false);
                } else
                {
                    fragment.mState = 0;
                }
                fragment.performDetach();
                dispatchOnFragmentDetached(fragment, false);
                i1 = i;
                if (!flag)
                {
                    if (!fragment.mRetaining)
                    {
                        makeInactive(fragment);
                        i1 = i;
                    } else
                    {
                        fragment.mHost = null;
                        fragment.mParentFragment = null;
                        fragment.mFragmentManager = null;
                        i1 = i;
                    }
                }
            }
        }
        continue; /* Loop/switch isn't completed */
        if (i < 2)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom ACTIVITY_CREATED: ").append(fragment).toString());
            }
            if (fragment.mView != null && mHost.onShouldSaveFragmentState(fragment) && fragment.mSavedViewState == null)
            {
                saveFragmentViewState(fragment);
            }
            fragment.performDestroyView();
            dispatchOnFragmentViewDestroyed(fragment, false);
            if (fragment.mView != null && fragment.mContainer != null)
            {
                obj1 = null;
                animation = obj1;
                if (mCurState > 0)
                {
                    animation = obj1;
                    if (!mDestroyed)
                    {
                        animation = obj1;
                        if (fragment.mView.getVisibility() == 0)
                        {
                            animation = obj1;
                            if (fragment.mPostponedAlpha >= 0.0F)
                            {
                                animation = loadAnimation(fragment, j, false, k);
                            }
                        }
                    }
                }
                fragment.mPostponedAlpha = 0.0F;
                if (animation != null)
                {
                    fragment.setAnimatingAway(fragment.mView);
                    fragment.setStateAfterAnimating(i);
                    animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(animation, fragment) {

                        final FragmentManagerImpl this$0;
                        final Fragment val$fragment;

                        public void onAnimationEnd(Animation animation1)
                        {
                            super.onAnimationEnd(animation1);
                            if (fragment.getAnimatingAway() != null)
                            {
                                fragment.setAnimatingAway(null);
                                moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                            }
                        }

            
            {
                this$0 = FragmentManagerImpl.this;
                fragment = fragment1;
                super(final_view, animation);
            }
                    });
                    fragment.mView.startAnimation(animation);
                }
                fragment.mContainer.removeView(fragment.mView);
            }
            fragment.mContainer = null;
            fragment.mView = null;
            fragment.mInnerView = null;
            fragment.mInLayout = false;
        }
          goto _L14
        if (true) goto _L12; else goto _L15
_L15:
    }

    public void noteStateNotSaved()
    {
        mStateSaved = false;
    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        if (!"fragment".equals(s))
        {
            return null;
        }
        s = attributeset.getAttributeValue(null, "class");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, FragmentTag.Fragment);
        String s1 = s;
        if (s == null)
        {
            s1 = typedarray.getString(0);
        }
        int k = typedarray.getResourceId(1, -1);
        String s2 = typedarray.getString(2);
        typedarray.recycle();
        if (!Fragment.isSupportFragmentClass(mHost.getContext(), s1))
        {
            return null;
        }
        int i;
        if (view != null)
        {
            i = view.getId();
        } else
        {
            i = 0;
        }
        if (i == -1 && k == -1 && s2 == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(s1).toString());
        }
        if (k != -1)
        {
            s = findFragmentById(k);
        } else
        {
            s = null;
        }
        view = s;
        if (s == null)
        {
            view = s;
            if (s2 != null)
            {
                view = findFragmentByTag(s2);
            }
        }
        s = view;
        if (view == null)
        {
            s = view;
            if (i != -1)
            {
                s = findFragmentById(i);
            }
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("onCreateView: id=0x").append(Integer.toHexString(k)).append(" fname=").append(s1).append(" existing=").append(s).toString());
        }
        if (s == null)
        {
            view = mContainer.instantiate(context, s1, null);
            view.mFromLayout = true;
            int j;
            if (k != 0)
            {
                j = k;
            } else
            {
                j = i;
            }
            view.mFragmentId = j;
            view.mContainerId = i;
            view.mTag = s2;
            view.mInLayout = true;
            view.mFragmentManager = this;
            view.mHost = mHost;
            view.onInflate(mHost.getContext(), attributeset, ((Fragment) (view)).mSavedFragmentState);
            addFragment(view, true);
        } else
        {
            if (((Fragment) (s)).mInLayout)
            {
                throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(k)).append(", tag ").append(s2).append(", or parent id 0x").append(Integer.toHexString(i)).append(" with another fragment for ").append(s1).toString());
            }
            s.mInLayout = true;
            s.mHost = mHost;
            view = s;
            if (!((Fragment) (s)).mRetaining)
            {
                s.onInflate(mHost.getContext(), attributeset, ((Fragment) (s)).mSavedFragmentState);
                view = s;
            }
        }
        if (mCurState < 1 && ((Fragment) (view)).mFromLayout)
        {
            moveToState(view, 1, 0, 0, false);
        } else
        {
            moveToState(view);
        }
        if (((Fragment) (view)).mView == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(s1).append(" did not create a view.").toString());
        }
        if (k != 0)
        {
            ((Fragment) (view)).mView.setId(k);
        }
        if (((Fragment) (view)).mView.getTag() == null)
        {
            ((Fragment) (view)).mView.setTag(s2);
        }
        return ((Fragment) (view)).mView;
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return onCreateView(null, s, context, attributeset);
    }

    public void performPendingDeferredStart(Fragment fragment)
    {
label0:
        {
            if (fragment.mDeferStart)
            {
                if (!mExecutingActions)
                {
                    break label0;
                }
                mHavePendingDeferredStart = true;
            }
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, mCurState, 0, 0, false);
    }

    public void popBackStack()
    {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public void popBackStack(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        } else
        {
            enqueueAction(new PopBackStackState(null, i, j), false);
            return;
        }
    }

    public void popBackStack(String s, int i)
    {
        enqueueAction(new PopBackStackState(s, -1, i), false);
    }

    public boolean popBackStackImmediate()
    {
        checkStateLoss();
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int i, int j)
    {
        checkStateLoss();
        execPendingActions();
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        } else
        {
            return popBackStackImmediate(null, i, j);
        }
    }

    public boolean popBackStackImmediate(String s, int i)
    {
        checkStateLoss();
        return popBackStackImmediate(s, -1, i);
    }

    boolean popBackStackState(ArrayList arraylist, ArrayList arraylist1, String s, int i, int j)
    {
        if (mBackStack != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (s != null || i >= 0 || (j & 1) != 0) goto _L4; else goto _L3
_L3:
        if ((i = mBackStack.size() - 1) < 0) goto _L1; else goto _L5
_L5:
        arraylist.add(mBackStack.remove(i));
        arraylist1.add(Boolean.valueOf(true));
_L8:
        return true;
_L4:
        int k;
        int l;
        k = -1;
        if (s == null && i < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        l = mBackStack.size() - 1;
        break MISSING_BLOCK_LABEL_89;
        if (l < 0) goto _L1; else goto _L6
_L6:
        k = l;
        if ((j & 1) == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = l - 1;
        do
        {
            k = j;
            if (j < 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            backstackrecord = (BackStackRecord)mBackStack.get(j);
            if (s == null || !s.equals(backstackrecord.getName()))
            {
                k = j;
                if (i < 0)
                {
                    continue; /* Loop/switch isn't completed */
                }
                k = j;
                if (i != backstackrecord.mIndex)
                {
                    continue; /* Loop/switch isn't completed */
                }
            }
            j--;
        } while (true);
        do
        {
            if (l < 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(l);
            if (s != null && s.equals(backstackrecord.getName()) || i >= 0 && i == backstackrecord.mIndex)
            {
                continue; /* Loop/switch isn't completed */
            }
            l--;
        } while (true);
        if (k == mBackStack.size() - 1) goto _L1; else goto _L7
_L7:
        i = mBackStack.size() - 1;
        while (i > k) 
        {
            arraylist.add(mBackStack.remove(i));
            arraylist1.add(Boolean.valueOf(true));
            i--;
        }
          goto _L8
    }

    public void putFragment(Bundle bundle, String s, Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        }
        bundle.putInt(s, fragment.mIndex);
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks, boolean flag)
    {
        if (mLifecycleCallbacks == null)
        {
            mLifecycleCallbacks = new CopyOnWriteArrayList();
        }
        mLifecycleCallbacks.add(new Pair(fragmentlifecyclecallbacks, Boolean.valueOf(flag)));
    }

    public void removeFragment(Fragment fragment)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("remove: ").append(fragment).append(" nesting=").append(fragment.mBackStackNesting).toString());
        }
        boolean flag;
        if (!fragment.isInBackStack())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!fragment.mDetached || flag)
        {
            if (mAdded != null)
            {
                mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners != null)
        {
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
        }
    }

    void reportBackStackChanged()
    {
        if (mBackStackChangeListeners != null)
        {
            for (int i = 0; i < mBackStackChangeListeners.size(); i++)
            {
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();
            }

        }
    }

    void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        FragmentManagerState fragmentmanagerstate;
        if (parcelable != null)
        {
            if ((fragmentmanagerstate = (FragmentManagerState)parcelable).mActive != null)
            {
                parcelable = null;
                if (fragmentmanagernonconfig != null)
                {
                    List list1 = fragmentmanagernonconfig.getFragments();
                    List list = fragmentmanagernonconfig.getChildNonConfigs();
                    int i;
                    int j1;
                    if (list1 != null)
                    {
                        i = list1.size();
                    } else
                    {
                        i = 0;
                    }
                    j1 = 0;
                    do
                    {
                        parcelable = list;
                        if (j1 >= i)
                        {
                            break;
                        }
                        parcelable = (Fragment)list1.get(j1);
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: re-attaching retained ").append(parcelable).toString());
                        }
                        FragmentState fragmentstate = fragmentmanagerstate.mActive[((Fragment) (parcelable)).mIndex];
                        fragmentstate.mInstance = parcelable;
                        parcelable.mSavedViewState = null;
                        parcelable.mBackStackNesting = 0;
                        parcelable.mInLayout = false;
                        parcelable.mAdded = false;
                        parcelable.mTarget = null;
                        if (fragmentstate.mSavedFragmentState != null)
                        {
                            fragmentstate.mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
                            parcelable.mSavedViewState = fragmentstate.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            parcelable.mSavedFragmentState = fragmentstate.mSavedFragmentState;
                        }
                        j1++;
                    } while (true);
                }
                mActive = new ArrayList(fragmentmanagerstate.mActive.length);
                if (mAvailIndices != null)
                {
                    mAvailIndices.clear();
                }
                int j = 0;
                while (j < fragmentmanagerstate.mActive.length) 
                {
                    FragmentState fragmentstate1 = fragmentmanagerstate.mActive[j];
                    if (fragmentstate1 != null)
                    {
                        Object obj1 = null;
                        Object obj = obj1;
                        if (parcelable != null)
                        {
                            obj = obj1;
                            if (j < parcelable.size())
                            {
                                obj = (FragmentManagerNonConfig)parcelable.get(j);
                            }
                        }
                        obj = fragmentstate1.instantiate(mHost, mContainer, mParent, ((FragmentManagerNonConfig) (obj)));
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: active #").append(j).append(": ").append(obj).toString());
                        }
                        mActive.add(obj);
                        fragmentstate1.mInstance = null;
                    } else
                    {
                        mActive.add(null);
                        if (mAvailIndices == null)
                        {
                            mAvailIndices = new ArrayList();
                        }
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: avail #").append(j).toString());
                        }
                        mAvailIndices.add(Integer.valueOf(j));
                    }
                    j++;
                }
                if (fragmentmanagernonconfig != null)
                {
                    parcelable = fragmentmanagernonconfig.getFragments();
                    int k;
                    int k1;
                    if (parcelable != null)
                    {
                        k = parcelable.size();
                    } else
                    {
                        k = 0;
                    }
                    k1 = 0;
                    while (k1 < k) 
                    {
                        fragmentmanagernonconfig = (Fragment)parcelable.get(k1);
                        if (((Fragment) (fragmentmanagernonconfig)).mTargetIndex >= 0)
                        {
                            if (((Fragment) (fragmentmanagernonconfig)).mTargetIndex < mActive.size())
                            {
                                fragmentmanagernonconfig.mTarget = (Fragment)mActive.get(((Fragment) (fragmentmanagernonconfig)).mTargetIndex);
                            } else
                            {
                                Log.w("FragmentManager", (new StringBuilder()).append("Re-attaching retained fragment ").append(fragmentmanagernonconfig).append(" target no longer exists: ").append(((Fragment) (fragmentmanagernonconfig)).mTargetIndex).toString());
                                fragmentmanagernonconfig.mTarget = null;
                            }
                        }
                        k1++;
                    }
                }
                if (fragmentmanagerstate.mAdded != null)
                {
                    mAdded = new ArrayList(fragmentmanagerstate.mAdded.length);
                    for (int l = 0; l < fragmentmanagerstate.mAdded.length; l++)
                    {
                        parcelable = (Fragment)mActive.get(fragmentmanagerstate.mAdded[l]);
                        if (parcelable == null)
                        {
                            throwException(new IllegalStateException((new StringBuilder()).append("No instantiated fragment for index #").append(fragmentmanagerstate.mAdded[l]).toString()));
                        }
                        parcelable.mAdded = true;
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: added #").append(l).append(": ").append(parcelable).toString());
                        }
                        if (mAdded.contains(parcelable))
                        {
                            throw new IllegalStateException("Already added!");
                        }
                        mAdded.add(parcelable);
                    }

                } else
                {
                    mAdded = null;
                }
                if (fragmentmanagerstate.mBackStack != null)
                {
                    mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
                    for (int i1 = 0; i1 < fragmentmanagerstate.mBackStack.length; i1++)
                    {
                        parcelable = fragmentmanagerstate.mBackStack[i1].instantiate(this);
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: back stack #").append(i1).append(" (index ").append(((BackStackRecord) (parcelable)).mIndex).append("): ").append(parcelable).toString());
                            fragmentmanagernonconfig = new PrintWriter(new LogWriter("FragmentManager"));
                            parcelable.dump("  ", fragmentmanagernonconfig, false);
                            fragmentmanagernonconfig.close();
                        }
                        mBackStack.add(parcelable);
                        if (((BackStackRecord) (parcelable)).mIndex >= 0)
                        {
                            setBackStackIndex(((BackStackRecord) (parcelable)).mIndex, parcelable);
                        }
                    }

                } else
                {
                    mBackStack = null;
                }
                if (fragmentmanagerstate.mPrimaryNavActiveIndex >= 0)
                {
                    mPrimaryNav = (Fragment)mActive.get(fragmentmanagerstate.mPrimaryNavActiveIndex);
                    return;
                }
            }
        }
    }

    FragmentManagerNonConfig retainNonConfig()
    {
        Object obj3 = null;
        Object obj1 = null;
        Object obj2 = null;
        Object obj = null;
        if (mActive != null)
        {
            int i = 0;
            do
            {
                obj2 = obj;
                obj3 = obj1;
                if (i >= mActive.size())
                {
                    break;
                }
                Fragment fragment = (Fragment)mActive.get(i);
                obj3 = obj;
                Object obj4 = obj1;
                if (fragment != null)
                {
                    obj2 = obj1;
                    int j;
                    if (fragment.mRetainInstance)
                    {
                        obj3 = obj1;
                        if (obj1 == null)
                        {
                            obj3 = new ArrayList();
                        }
                        ((ArrayList) (obj3)).add(fragment);
                        fragment.mRetaining = true;
                        boolean flag;
                        if (fragment.mTarget != null)
                        {
                            j = fragment.mTarget.mIndex;
                        } else
                        {
                            j = -1;
                        }
                        fragment.mTargetIndex = j;
                        obj2 = obj3;
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("retainNonConfig: keeping retained ").append(fragment).toString());
                            obj2 = obj3;
                        }
                    }
                    flag = false;
                    j = ((flag) ? 1 : 0);
                    obj1 = obj;
                    if (fragment.mChildFragmentManager != null)
                    {
                        obj3 = fragment.mChildFragmentManager.retainNonConfig();
                        j = ((flag) ? 1 : 0);
                        obj1 = obj;
                        if (obj3 != null)
                        {
                            obj1 = obj;
                            if (obj == null)
                            {
                                obj = new ArrayList();
                                j = 0;
                                do
                                {
                                    obj1 = obj;
                                    if (j >= i)
                                    {
                                        break;
                                    }
                                    ((ArrayList) (obj)).add(null);
                                    j++;
                                } while (true);
                            }
                            ((ArrayList) (obj1)).add(obj3);
                            j = 1;
                        }
                    }
                    obj3 = obj1;
                    obj4 = obj2;
                    if (obj1 != null)
                    {
                        obj3 = obj1;
                        obj4 = obj2;
                        if (j == 0)
                        {
                            ((ArrayList) (obj1)).add(null);
                            obj4 = obj2;
                            obj3 = obj1;
                        }
                    }
                }
                i++;
                obj = obj3;
                obj1 = obj4;
            } while (true);
        }
        if (obj3 == null && obj2 == null)
        {
            return null;
        } else
        {
            return new FragmentManagerNonConfig(((List) (obj3)), ((List) (obj2)));
        }
    }

    Parcelable saveAllState()
    {
        Object obj;
        Object obj1;
        obj1 = null;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        if (HONEYCOMB)
        {
            mStateSaved = true;
        }
        obj = obj1;
        if (mActive == null) goto _L2; else goto _L1
_L1:
        if (mActive.size() > 0) goto _L4; else goto _L3
_L3:
        obj = obj1;
_L2:
        return ((Parcelable) (obj));
_L4:
        FragmentState afragmentstate[];
        int j1 = mActive.size();
        afragmentstate = new FragmentState[j1];
        boolean flag = false;
        int i = 0;
        while (i < j1) 
        {
            obj = (Fragment)mActive.get(i);
            if (obj == null)
            {
                continue;
            }
            if (((Fragment) (obj)).mIndex < 0)
            {
                throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(obj).append(" has cleared index: ").append(((Fragment) (obj)).mIndex).toString()));
            }
            boolean flag1 = true;
            FragmentState fragmentstate = new FragmentState(((Fragment) (obj)));
            afragmentstate[i] = fragmentstate;
            if (((Fragment) (obj)).mState > 0 && fragmentstate.mSavedFragmentState == null)
            {
                fragmentstate.mSavedFragmentState = saveFragmentBasicState(((Fragment) (obj)));
                if (((Fragment) (obj)).mTarget != null)
                {
                    if (((Fragment) (obj)).mTarget.mIndex < 0)
                    {
                        throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: ").append(obj).append(" has target not in fragment manager: ").append(((Fragment) (obj)).mTarget).toString()));
                    }
                    if (fragmentstate.mSavedFragmentState == null)
                    {
                        fragmentstate.mSavedFragmentState = new Bundle();
                    }
                    putFragment(fragmentstate.mSavedFragmentState, "android:target_state", ((Fragment) (obj)).mTarget);
                    if (((Fragment) (obj)).mTargetRequestCode != 0)
                    {
                        fragmentstate.mSavedFragmentState.putInt("android:target_req_state", ((Fragment) (obj)).mTargetRequestCode);
                    }
                }
            } else
            {
                fragmentstate.mSavedFragmentState = ((Fragment) (obj)).mSavedFragmentState;
            }
            flag = flag1;
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Saved state of ").append(obj).append(": ").append(fragmentstate.mSavedFragmentState).toString());
                flag = flag1;
            }
            i++;
        }
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = obj1;
        if (DEBUG)
        {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
        if (true) goto _L2; else goto _L5
_L5:
        Object aobj[] = null;
        FragmentManagerState fragmentmanagerstate = null;
        obj = ((Object) (aobj));
        if (mAdded != null)
        {
            int l = mAdded.size();
            obj = ((Object) (aobj));
            if (l > 0)
            {
                aobj = new int[l];
                int j = 0;
                do
                {
                    obj = ((Object) (aobj));
                    if (j >= l)
                    {
                        break;
                    }
                    aobj[j] = ((Fragment)mAdded.get(j)).mIndex;
                    if (aobj[j] < 0)
                    {
                        throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(mAdded.get(j)).append(" has cleared index: ").append(aobj[j]).toString()));
                    }
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding fragment #").append(j).append(": ").append(mAdded.get(j)).toString());
                    }
                    j++;
                } while (true);
            }
        }
        aobj = fragmentmanagerstate;
        if (mBackStack != null)
        {
            int i1 = mBackStack.size();
            aobj = fragmentmanagerstate;
            if (i1 > 0)
            {
                BackStackState abackstackstate[] = new BackStackState[i1];
                int k = 0;
                do
                {
                    aobj = abackstackstate;
                    if (k >= i1)
                    {
                        break;
                    }
                    abackstackstate[k] = new BackStackState((BackStackRecord)mBackStack.get(k));
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding back stack #").append(k).append(": ").append(mBackStack.get(k)).toString());
                    }
                    k++;
                } while (true);
            }
        }
        abackstackstate = new FragmentManagerState();
        abackstackstate.mActive = afragmentstate;
        abackstackstate.mAdded = ((int []) (obj));
        abackstackstate.mBackStack = ((BackStackState []) (aobj));
        obj = abackstackstate;
        if (mPrimaryNav != null)
        {
            abackstackstate.mPrimaryNavActiveIndex = mPrimaryNav.mIndex;
            return abackstackstate;
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    Bundle saveFragmentBasicState(Fragment fragment)
    {
        Bundle bundle1 = null;
        if (mStateBundle == null)
        {
            mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, mStateBundle, false);
        if (!mStateBundle.isEmpty())
        {
            bundle1 = mStateBundle;
            mStateBundle = null;
        }
        if (fragment.mView != null)
        {
            saveFragmentViewState(fragment);
        }
        Bundle bundle = bundle1;
        if (fragment.mSavedViewState != null)
        {
            bundle = bundle1;
            if (bundle1 == null)
            {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        bundle1 = bundle;
        if (!fragment.mUserVisibleHint)
        {
            bundle1 = bundle;
            if (bundle == null)
            {
                bundle1 = new Bundle();
            }
            bundle1.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle1;
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
    {
        Object obj = null;
        if (fragment.mIndex < 0)
        {
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        }
        Fragment.SavedState savedstate = obj;
        if (fragment.mState > 0)
        {
            fragment = saveFragmentBasicState(fragment);
            savedstate = obj;
            if (fragment != null)
            {
                savedstate = new Fragment.SavedState(fragment);
            }
        }
        return savedstate;
    }

    void saveFragmentViewState(Fragment fragment)
    {
        if (fragment.mInnerView != null)
        {
            if (mStateArray == null)
            {
                mStateArray = new SparseArray();
            } else
            {
                mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(mStateArray);
            if (mStateArray.size() > 0)
            {
                fragment.mSavedViewState = mStateArray;
                mStateArray = null;
                return;
            }
        }
    }

    public void setBackStackIndex(int i, BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        k = mBackStackIndices.size();
        int j = k;
        if (i >= k) goto _L2; else goto _L1
_L1:
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(i, backstackrecord);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        mBackStackIndices.add(null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Adding available back stack index ").append(j).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(j));
        j++;
        if (true) goto _L2; else goto _L3
_L3:
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
          goto _L4
        backstackrecord;
        this;
        JVM INSTR monitorexit ;
        throw backstackrecord;
    }

    public void setPrimaryNavigationFragment(Fragment fragment)
    {
        if (fragment != null && (fragment.getFragmentManager() != this || fragment.mIndex >= mActive.size() || mActive.get(fragment.mIndex) != fragment))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not an active fragment of FragmentManager ").append(this).toString());
        } else
        {
            mPrimaryNav = fragment;
            return;
        }
    }

    public void showFragment(Fragment fragment)
    {
        boolean flag = false;
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("show: ").append(fragment).toString());
        }
        if (fragment.mHidden)
        {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged)
            {
                flag = true;
            }
            fragment.mHiddenChanged = flag;
        }
    }

    void startPendingDeferredFragments()
    {
        if (mActive != null)
        {
            int i = 0;
            while (i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment != null)
                {
                    performPendingDeferredStart(fragment);
                }
                i++;
            }
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if (mParent != null)
        {
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        } else
        {
            DebugUtils.buildShortClassTag(mHost, stringbuilder);
        }
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks)
    {
        if (mLifecycleCallbacks == null)
        {
            return;
        }
        CopyOnWriteArrayList copyonwritearraylist = mLifecycleCallbacks;
        copyonwritearraylist;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = mLifecycleCallbacks.size();
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        if (((Pair)mLifecycleCallbacks.get(i)).first != fragmentlifecyclecallbacks)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        mLifecycleCallbacks.remove(i);
        copyonwritearraylist;
        JVM INSTR monitorexit ;
        return;
        fragmentlifecyclecallbacks;
        copyonwritearraylist;
        JVM INSTR monitorexit ;
        throw fragmentlifecyclecallbacks;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        boolean flag = false;
        DEBUG = false;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            flag = true;
        }
        HONEYCOMB = flag;
    }


}
