// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Context;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, Fragment, FragmentManagerImpl, FragmentHostCallback

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{
    static final class Op
    {

        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op()
        {
        }

        Op(int i, Fragment fragment1)
        {
            cmd = i;
            fragment = fragment1;
        }
    }


    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    static final boolean SUPPORTS_TRANSITIONS;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    boolean mAllowOptimization;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        mOps = new ArrayList();
        mAllowAddToBackStack = true;
        mIndex = -1;
        mAllowOptimization = false;
        mManager = fragmentmanagerimpl;
    }

    private void doAddOp(int i, Fragment fragment, String s, int j)
    {
        Class class1 = fragment.getClass();
        int k = class1.getModifiers();
        if (class1.isAnonymousClass() || !Modifier.isPublic(k) || class1.isMemberClass() && !Modifier.isStatic(k))
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(class1.getCanonicalName()).append(" must be a public static class to be  properly recreated from").append(" instance state.").toString());
        }
        fragment.mFragmentManager = mManager;
        if (s != null)
        {
            if (fragment.mTag != null && !s.equals(fragment.mTag))
            {
                throw new IllegalStateException((new StringBuilder()).append("Can't change tag of fragment ").append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            }
            fragment.mTag = s;
        }
        if (i != 0)
        {
            if (i == -1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Can't add fragment ").append(fragment).append(" with tag ").append(s).append(" to container view with no id").toString());
            }
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != i)
            {
                throw new IllegalStateException((new StringBuilder()).append("Can't change container ID of fragment ").append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        addOp(new Op(j, fragment));
    }

    private static boolean isFragmentPostponed(Op op)
    {
        op = op.fragment;
        return op != null && ((Fragment) (op)).mAdded && ((Fragment) (op)).mView != null && !((Fragment) (op)).mDetached && !((Fragment) (op)).mHidden && op.isPostponed();
    }

    public FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    void addOp(Op op)
    {
        mOps.add(op);
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View view, String s)
    {
        if (SUPPORTS_TRANSITIONS)
        {
            view = ViewCompat.getTransitionName(view);
            if (view == null)
            {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (mSharedElementSourceNames == null)
            {
                mSharedElementSourceNames = new ArrayList();
                mSharedElementTargetNames = new ArrayList();
            } else
            {
                if (mSharedElementTargetNames.contains(s))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the target name '").append(s).append("' has already been added to the transaction.").toString());
                }
                if (mSharedElementSourceNames.contains(view))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the source name '").append(view).append(" has already been added to the transaction.").toString());
                }
            }
            mSharedElementSourceNames.add(view);
            mSharedElementTargetNames.add(s);
        }
        return this;
    }

    public FragmentTransaction addToBackStack(String s)
    {
        if (!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public FragmentTransaction attach(Fragment fragment)
    {
        addOp(new Op(7, fragment));
        return this;
    }

    void bumpBackStackNesting(int i)
    {
        if (mAddToBackStack)
        {
            if (FragmentManagerImpl.DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting in ").append(this).append(" by ").append(i).toString());
            }
            int k = mOps.size();
            int j = 0;
            while (j < k) 
            {
                Op op = (Op)mOps.get(j);
                if (op.fragment != null)
                {
                    Fragment fragment = op.fragment;
                    fragment.mBackStackNesting = fragment.mBackStackNesting + i;
                    if (FragmentManagerImpl.DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
                    }
                }
                j++;
            }
        }
    }

    public int commit()
    {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    int commitInternal(boolean flag)
    {
        if (mCommitted)
        {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Commit: ").append(this).toString());
            PrintWriter printwriter = new PrintWriter(new LogWriter("FragmentManager"));
            dump("  ", null, printwriter, null);
            printwriter.close();
        }
        mCommitted = true;
        if (mAddToBackStack)
        {
            mIndex = mManager.allocBackStackIndex(this);
        } else
        {
            mIndex = -1;
        }
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    public void commitNow()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, true);
    }

    public FragmentTransaction detach(Fragment fragment)
    {
        addOp(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack()
    {
        if (mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            return this;
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, printwriter, true);
    }

    public void dump(String s, PrintWriter printwriter, boolean flag)
    {
        int i;
        int j;
        if (flag)
        {
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.print(mName);
            printwriter.print(" mIndex=");
            printwriter.print(mIndex);
            printwriter.print(" mCommitted=");
            printwriter.println(mCommitted);
            if (mTransition != 0)
            {
                printwriter.print(s);
                printwriter.print("mTransition=#");
                printwriter.print(Integer.toHexString(mTransition));
                printwriter.print(" mTransitionStyle=#");
                printwriter.println(Integer.toHexString(mTransitionStyle));
            }
            if (mEnterAnim != 0 || mExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mEnterAnim=#");
                printwriter.print(Integer.toHexString(mEnterAnim));
                printwriter.print(" mExitAnim=#");
                printwriter.println(Integer.toHexString(mExitAnim));
            }
            if (mPopEnterAnim != 0 || mPopExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mPopEnterAnim=#");
                printwriter.print(Integer.toHexString(mPopEnterAnim));
                printwriter.print(" mPopExitAnim=#");
                printwriter.println(Integer.toHexString(mPopExitAnim));
            }
            if (mBreadCrumbTitleRes != 0 || mBreadCrumbTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbTitleRes));
                printwriter.print(" mBreadCrumbTitleText=");
                printwriter.println(mBreadCrumbTitleText);
            }
            if (mBreadCrumbShortTitleRes != 0 || mBreadCrumbShortTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbShortTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
                printwriter.print(" mBreadCrumbShortTitleText=");
                printwriter.println(mBreadCrumbShortTitleText);
            }
        }
        if (mOps.isEmpty())
        {
            break MISSING_BLOCK_LABEL_736;
        }
        printwriter.print(s);
        printwriter.println("Operations:");
        (new StringBuilder()).append(s).append("    ").toString();
        j = mOps.size();
        i = 0;
_L13:
        Op op;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_736;
        }
        op = (Op)mOps.get(i);
        op.cmd;
        JVM INSTR tableswitch 0 9: default 452
    //                   0 656
    //                   1 664
    //                   2 672
    //                   3 680
    //                   4 688
    //                   5 696
    //                   6 704
    //                   7 712
    //                   8 720
    //                   9 728;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_728;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        String s1 = (new StringBuilder()).append("cmd=").append(op.cmd).toString();
_L14:
        printwriter.print(s);
        printwriter.print("  Op #");
        printwriter.print(i);
        printwriter.print(": ");
        printwriter.print(s1);
        printwriter.print(" ");
        printwriter.println(op.fragment);
        if (flag)
        {
            if (op.enterAnim != 0 || op.exitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("enterAnim=#");
                printwriter.print(Integer.toHexString(op.enterAnim));
                printwriter.print(" exitAnim=#");
                printwriter.println(Integer.toHexString(op.exitAnim));
            }
            if (op.popEnterAnim != 0 || op.popExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("popEnterAnim=#");
                printwriter.print(Integer.toHexString(op.popEnterAnim));
                printwriter.print(" popExitAnim=#");
                printwriter.println(Integer.toHexString(op.popExitAnim));
            }
        }
        i++;
        if (true) goto _L13; else goto _L12
_L12:
        s1 = "NULL";
          goto _L14
_L3:
        s1 = "ADD";
          goto _L14
_L4:
        s1 = "REPLACE";
          goto _L14
_L5:
        s1 = "REMOVE";
          goto _L14
_L6:
        s1 = "HIDE";
          goto _L14
_L7:
        s1 = "SHOW";
          goto _L14
_L8:
        s1 = "DETACH";
          goto _L14
_L9:
        s1 = "ATTACH";
          goto _L14
_L10:
        s1 = "SET_PRIMARY_NAV";
          goto _L14
        s1 = "UNSET_PRIMARY_NAV";
          goto _L14
    }

    void executeOps()
    {
        int i;
        int j;
        j = mOps.size();
        i = 0;
_L11:
        Op op;
        Fragment fragment;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_303;
        }
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        if (fragment != null)
        {
            fragment.setNextTransition(mTransition, mTransitionStyle);
        }
        op.cmd;
        JVM INSTR tableswitch 1 9: default 104
    //                   1 135
    //                   2 104
    //                   3 186
    //                   4 205
    //                   5 224
    //                   6 243
    //                   7 262
    //                   8 281
    //                   9 292;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_292;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.enterAnim);
        mManager.addFragment(fragment, false);
_L12:
        if (!mAllowOptimization && op.cmd != 1 && fragment != null)
        {
            mManager.moveFragmentToExpectedState(fragment);
        }
        i++;
        if (true) goto _L11; else goto _L10
_L10:
        fragment.setNextAnim(op.exitAnim);
        mManager.removeFragment(fragment);
          goto _L12
_L4:
        fragment.setNextAnim(op.exitAnim);
        mManager.hideFragment(fragment);
          goto _L12
_L5:
        fragment.setNextAnim(op.enterAnim);
        mManager.showFragment(fragment);
          goto _L12
_L6:
        fragment.setNextAnim(op.exitAnim);
        mManager.detachFragment(fragment);
          goto _L12
_L7:
        fragment.setNextAnim(op.enterAnim);
        mManager.attachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        if (!mAllowOptimization)
        {
            mManager.moveToState(mManager.mCurState, true);
        }
        return;
    }

    void executePopOps(boolean flag)
    {
        int i = mOps.size() - 1;
_L11:
        Op op;
        Fragment fragment;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_309;
        }
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        if (fragment != null)
        {
            fragment.setNextTransition(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
        }
        op.cmd;
        JVM INSTR tableswitch 1 9: default 108
    //                   1 139
    //                   2 108
    //                   3 191
    //                   4 211
    //                   5 230
    //                   6 249
    //                   7 268
    //                   8 287
    //                   9 298;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_298;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.popExitAnim);
        mManager.removeFragment(fragment);
_L12:
        if (!mAllowOptimization && op.cmd != 3 && fragment != null)
        {
            mManager.moveFragmentToExpectedState(fragment);
        }
        i--;
        if (true) goto _L11; else goto _L10
_L10:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.addFragment(fragment, false);
          goto _L12
_L4:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.showFragment(fragment);
          goto _L12
_L5:
        fragment.setNextAnim(op.popExitAnim);
        mManager.hideFragment(fragment);
          goto _L12
_L6:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.attachFragment(fragment);
          goto _L12
_L7:
        fragment.setNextAnim(op.popExitAnim);
        mManager.detachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        if (!mAllowOptimization && flag)
        {
            mManager.moveToState(mManager.mCurState, true);
        }
        return;
    }

    Fragment expandOps(ArrayList arraylist, Fragment fragment)
    {
        Fragment fragment1;
        int j;
        j = 0;
        fragment1 = fragment;
_L8:
        Op op;
        int i;
        if (j >= mOps.size())
        {
            break MISSING_BLOCK_LABEL_480;
        }
        op = (Op)mOps.get(j);
        i = j;
        fragment = fragment1;
        op.cmd;
        JVM INSTR tableswitch 1 8: default 88
    //                   1 105
    //                   2 183
    //                   3 124
    //                   4 94
    //                   5 94
    //                   6 124
    //                   7 105
    //                   8 446;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L4 _L2 _L6
_L6:
        break MISSING_BLOCK_LABEL_446;
_L2:
        break; /* Loop/switch isn't completed */
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        fragment = fragment1;
        i = j;
_L9:
        j = i + 1;
        fragment1 = fragment;
        if (true) goto _L8; else goto _L7
_L7:
        arraylist.add(op.fragment);
        i = j;
        fragment = fragment1;
          goto _L9
_L4:
        arraylist.remove(op.fragment);
        i = j;
        fragment = fragment1;
        if (op.fragment == fragment1)
        {
            mOps.add(j, new Op(9, op.fragment));
            i = j + 1;
            fragment = null;
        }
          goto _L9
_L3:
        Fragment fragment2 = op.fragment;
        int j1 = fragment2.mContainerId;
        boolean flag = false;
        int k = arraylist.size() - 1;
        fragment = fragment1;
        i = j;
        j = k;
        while (j >= 0) 
        {
            Fragment fragment3 = (Fragment)arraylist.get(j);
            int l = ((flag) ? 1 : 0);
            int i1 = i;
            fragment1 = fragment;
            if (fragment3.mContainerId == j1)
            {
                if (fragment3 == fragment2)
                {
                    l = 1;
                    fragment1 = fragment;
                    i1 = i;
                } else
                {
                    l = i;
                    fragment1 = fragment;
                    if (fragment3 == fragment)
                    {
                        mOps.add(i, new Op(9, fragment3));
                        l = i + 1;
                        fragment1 = null;
                    }
                    fragment = new Op(3, fragment3);
                    fragment.enterAnim = op.enterAnim;
                    fragment.popEnterAnim = op.popEnterAnim;
                    fragment.exitAnim = op.exitAnim;
                    fragment.popExitAnim = op.popExitAnim;
                    mOps.add(l, fragment);
                    arraylist.remove(fragment3);
                    i1 = l + 1;
                    l = ((flag) ? 1 : 0);
                }
            }
            j--;
            flag = l;
            i = i1;
            fragment = fragment1;
        }
        if (flag)
        {
            mOps.remove(i);
            i--;
        } else
        {
            op.cmd = 1;
            arraylist.add(fragment2);
        }
          goto _L9
        mOps.add(j, new Op(9, fragment1));
        i = j + 1;
        fragment = op.fragment;
          goto _L9
        return fragment1;
    }

    public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Run: ").append(this).toString());
        }
        arraylist.add(this);
        arraylist1.add(Boolean.valueOf(false));
        if (mAddToBackStack)
        {
            mManager.addBackStackState(this);
        }
        return true;
    }

    public CharSequence getBreadCrumbShortTitle()
    {
        if (mBreadCrumbShortTitleRes != 0)
        {
            return mManager.mHost.getContext().getText(mBreadCrumbShortTitleRes);
        } else
        {
            return mBreadCrumbShortTitleText;
        }
    }

    public int getBreadCrumbShortTitleRes()
    {
        return mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle()
    {
        if (mBreadCrumbTitleRes != 0)
        {
            return mManager.mHost.getContext().getText(mBreadCrumbTitleRes);
        } else
        {
            return mBreadCrumbTitleText;
        }
    }

    public int getBreadCrumbTitleRes()
    {
        return mBreadCrumbTitleRes;
    }

    public int getId()
    {
        return mIndex;
    }

    public String getName()
    {
        return mName;
    }

    public int getTransition()
    {
        return mTransition;
    }

    public int getTransitionStyle()
    {
        return mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment fragment)
    {
        addOp(new Op(4, fragment));
        return this;
    }

    boolean interactsWith(int i)
    {
        boolean flag1 = false;
        int l = mOps.size();
        int j = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (j < l)
                {
                    Op op = (Op)mOps.get(j);
                    int k;
                    if (op.fragment != null)
                    {
                        k = op.fragment.mContainerId;
                    } else
                    {
                        k = 0;
                    }
                    if (k == 0 || k != i)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    boolean interactsWith(ArrayList arraylist, int i, int j)
    {
        if (j == i)
        {
            return false;
        }
        int i2 = mOps.size();
        int i1 = -1;
        for (int l = 0; l < i2;)
        {
            Op op = (Op)mOps.get(l);
            int k;
            int k1;
            if (op.fragment != null)
            {
                k = op.fragment.mContainerId;
            } else
            {
                k = 0;
            }
            k1 = i1;
            if (k != 0)
            {
                k1 = i1;
                if (k != i1)
                {
                    i1 = k;
                    int j1 = i;
                    do
                    {
                        k1 = i1;
                        if (j1 >= j)
                        {
                            break;
                        }
                        BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(j1);
                        int j2 = backstackrecord.mOps.size();
                        int l1;
                        for (k1 = 0; k1 < j2; k1++)
                        {
                            Op op1 = (Op)backstackrecord.mOps.get(k1);
                            if (op1.fragment != null)
                            {
                                l1 = op1.fragment.mContainerId;
                            } else
                            {
                                l1 = 0;
                            }
                            if (l1 == k)
                            {
                                return true;
                            }
                        }

                        j1++;
                    } while (true);
                }
            }
            l++;
            i1 = k1;
        }

        return false;
    }

    public boolean isAddToBackStackAllowed()
    {
        return mAllowAddToBackStack;
    }

    public boolean isEmpty()
    {
        return mOps.isEmpty();
    }

    boolean isPostponed()
    {
        for (int i = 0; i < mOps.size(); i++)
        {
            if (isFragmentPostponed((Op)mOps.get(i)))
            {
                return true;
            }
        }

        return false;
    }

    public FragmentTransaction postOnCommit(Runnable runnable)
    {
        if (runnable == null)
        {
            throw new IllegalArgumentException("runnable cannot be null");
        }
        disallowAddToBackStack();
        if (mCommitRunnables == null)
        {
            mCommitRunnables = new ArrayList();
        }
        mCommitRunnables.add(runnable);
        return this;
    }

    public FragmentTransaction remove(Fragment fragment)
    {
        addOp(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public void runOnCommitRunnables()
    {
        if (mCommitRunnables != null)
        {
            int i = 0;
            for (int j = mCommitRunnables.size(); i < j; i++)
            {
                ((Runnable)mCommitRunnables.get(i)).run();
            }

            mCommitRunnables = null;
        }
    }

    public FragmentTransaction setAllowOptimization(boolean flag)
    {
        mAllowOptimization = flag;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i)
    {
        mBreadCrumbShortTitleRes = i;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charsequence)
    {
        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i)
    {
        mBreadCrumbTitleRes = i;
        mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence charsequence)
    {
        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i, int j)
    {
        return setCustomAnimations(i, j, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        for (int i = 0; i < mOps.size(); i++)
        {
            Op op = (Op)mOps.get(i);
            if (isFragmentPostponed(op))
            {
                op.fragment.setOnStartEnterTransitionListener(onstartentertransitionlistener);
            }
        }

    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment)
    {
        addOp(new Op(8, fragment));
        return this;
    }

    public FragmentTransaction setTransition(int i)
    {
        mTransition = i;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i)
    {
        mTransitionStyle = i;
        return this;
    }

    public FragmentTransaction show(Fragment fragment)
    {
        addOp(new Op(5, fragment));
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if (mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    Fragment trackAddedFragmentsInPop(ArrayList arraylist, Fragment fragment)
    {
        Fragment fragment1;
        int i;
        i = 0;
        fragment1 = fragment;
_L8:
        Op op;
        if (i >= mOps.size())
        {
            break MISSING_BLOCK_LABEL_145;
        }
        op = (Op)mOps.get(i);
        fragment = fragment1;
        op.cmd;
        JVM INSTR tableswitch 1 9: default 88
    //                   1 101
    //                   2 90
    //                   3 116
    //                   4 90
    //                   5 90
    //                   6 116
    //                   7 101
    //                   8 140
    //                   9 131;
           goto _L1 _L2 _L3 _L4 _L3 _L3 _L4 _L2 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_140;
_L2:
        break; /* Loop/switch isn't completed */
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        fragment = fragment1;
_L9:
        i++;
        fragment1 = fragment;
        if (true) goto _L8; else goto _L7
_L7:
        arraylist.remove(op.fragment);
        fragment = fragment1;
          goto _L9
_L4:
        arraylist.add(op.fragment);
        fragment = fragment1;
          goto _L9
_L6:
        fragment = op.fragment;
          goto _L9
        fragment = null;
          goto _L9
        return fragment1;
    }

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        SUPPORTS_TRANSITIONS = flag;
    }
}
