// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            LoaderManagerImpl, FragmentManagerImpl, FragmentHostCallback, FragmentActivity, 
//            SuperNotCalledException, FragmentManagerNonConfig, FragmentManager, SharedElementCallback, 
//            LoaderManager, FragmentContainer

public class Fragment
    implements ComponentCallbacks, android.view.View.OnCreateContextMenuListener
{
    static class AnimationInfo
    {

        private Boolean mAllowEnterTransitionOverlap;
        private Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        private Object mEnterTransition;
        SharedElementCallback mEnterTransitionCallback;
        boolean mEnterTransitionPostponed;
        private Object mExitTransition;
        SharedElementCallback mExitTransitionCallback;
        boolean mIsHideReplaced;
        int mNextAnim;
        int mNextTransition;
        int mNextTransitionStyle;
        private Object mReenterTransition;
        private Object mReturnTransition;
        private Object mSharedElementEnterTransition;
        private Object mSharedElementReturnTransition;
        OnStartEnterTransitionListener mStartEnterTransitionListener;
        int mStateAfterAnimating;



/*
        static Object access$002(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mEnterTransition = obj;
            return obj;
        }

*/



/*
        static Object access$102(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mReturnTransition = obj;
            return obj;
        }

*/



/*
        static Object access$202(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mExitTransition = obj;
            return obj;
        }

*/



/*
        static Object access$302(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mReenterTransition = obj;
            return obj;
        }

*/



/*
        static Object access$402(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mSharedElementEnterTransition = obj;
            return obj;
        }

*/



/*
        static Object access$502(AnimationInfo animationinfo, Object obj)
        {
            animationinfo.mSharedElementReturnTransition = obj;
            return obj;
        }

*/



/*
        static Boolean access$602(AnimationInfo animationinfo, Boolean boolean1)
        {
            animationinfo.mAllowEnterTransitionOverlap = boolean1;
            return boolean1;
        }

*/



/*
        static Boolean access$702(AnimationInfo animationinfo, Boolean boolean1)
        {
            animationinfo.mAllowReturnTransitionOverlap = boolean1;
            return boolean1;
        }

*/

        AnimationInfo()
        {
            mEnterTransition = null;
            mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            mExitTransition = null;
            mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
            mSharedElementEnterTransition = null;
            mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            mEnterTransitionCallback = null;
            mExitTransitionCallback = null;
        }
    }

    public static class InstantiationException extends RuntimeException
    {

        public InstantiationException(String s, Exception exception)
        {
            super(s, exception);
        }
    }

    static interface OnStartEnterTransitionListener
    {

        public abstract void onStartEnterTransition();

        public abstract void startListening();
    }

    public static class SavedState
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        final Bundle mState;

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeBundle(mState);
        }


        SavedState(Bundle bundle)
        {
            mState = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classloader)
        {
            mState = parcel.readBundle();
            if (classloader != null && mState != null)
            {
                mState.setClassLoader(classloader);
            }
        }
    }


    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    private static final SimpleArrayMap sClassMap = new SimpleArrayMap();
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    FragmentManagerNonConfig mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    boolean mIsNewlyAdded;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;

    public Fragment()
    {
        mState = 0;
        mIndex = -1;
        mTargetIndex = -1;
        mMenuVisible = true;
        mUserVisibleHint = true;
    }

    private void callStartTransitionListener()
    {
        OnStartEnterTransitionListener onstartentertransitionlistener;
        if (mAnimationInfo == null)
        {
            onstartentertransitionlistener = null;
        } else
        {
            mAnimationInfo.mEnterTransitionPostponed = false;
            onstartentertransitionlistener = mAnimationInfo.mStartEnterTransitionListener;
            mAnimationInfo.mStartEnterTransitionListener = null;
        }
        if (onstartentertransitionlistener != null)
        {
            onstartentertransitionlistener.onStartEnterTransition();
        }
    }

    private AnimationInfo ensureAnimationInfo()
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        return mAnimationInfo;
    }

    public static Fragment instantiate(Context context, String s)
    {
        return instantiate(context, s, null);
    }

    public static Fragment instantiate(Context context, String s, Bundle bundle)
    {
        Class class1;
        Class class2;
        try
        {
            class2 = (Class)sClassMap.get(s);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        class1 = class2;
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        class1 = context.getClassLoader().loadClass(s);
        sClassMap.put(s, class1);
        context = (Fragment)class1.newInstance();
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        bundle.setClassLoader(context.getClass().getClassLoader());
        context.setArguments(bundle);
        return context;
    }

    static boolean isSupportFragmentClass(Context context, String s)
    {
        Class class1;
        Class class2;
        boolean flag;
        try
        {
            class2 = (Class)sClassMap.get(s);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        class1 = class2;
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        class1 = context.getClassLoader().loadClass(s);
        sClassMap.put(s, class1);
        flag = android/support/v4/app/Fragment.isAssignableFrom(class1);
        return flag;
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mFragmentId=#");
        printwriter.print(Integer.toHexString(mFragmentId));
        printwriter.print(" mContainerId=#");
        printwriter.print(Integer.toHexString(mContainerId));
        printwriter.print(" mTag=");
        printwriter.println(mTag);
        printwriter.print(s);
        printwriter.print("mState=");
        printwriter.print(mState);
        printwriter.print(" mIndex=");
        printwriter.print(mIndex);
        printwriter.print(" mWho=");
        printwriter.print(mWho);
        printwriter.print(" mBackStackNesting=");
        printwriter.println(mBackStackNesting);
        printwriter.print(s);
        printwriter.print("mAdded=");
        printwriter.print(mAdded);
        printwriter.print(" mRemoving=");
        printwriter.print(mRemoving);
        printwriter.print(" mFromLayout=");
        printwriter.print(mFromLayout);
        printwriter.print(" mInLayout=");
        printwriter.println(mInLayout);
        printwriter.print(s);
        printwriter.print("mHidden=");
        printwriter.print(mHidden);
        printwriter.print(" mDetached=");
        printwriter.print(mDetached);
        printwriter.print(" mMenuVisible=");
        printwriter.print(mMenuVisible);
        printwriter.print(" mHasMenu=");
        printwriter.println(mHasMenu);
        printwriter.print(s);
        printwriter.print("mRetainInstance=");
        printwriter.print(mRetainInstance);
        printwriter.print(" mRetaining=");
        printwriter.print(mRetaining);
        printwriter.print(" mUserVisibleHint=");
        printwriter.println(mUserVisibleHint);
        if (mFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.print("mFragmentManager=");
            printwriter.println(mFragmentManager);
        }
        if (mHost != null)
        {
            printwriter.print(s);
            printwriter.print("mHost=");
            printwriter.println(mHost);
        }
        if (mParentFragment != null)
        {
            printwriter.print(s);
            printwriter.print("mParentFragment=");
            printwriter.println(mParentFragment);
        }
        if (mArguments != null)
        {
            printwriter.print(s);
            printwriter.print("mArguments=");
            printwriter.println(mArguments);
        }
        if (mSavedFragmentState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedFragmentState=");
            printwriter.println(mSavedFragmentState);
        }
        if (mSavedViewState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedViewState=");
            printwriter.println(mSavedViewState);
        }
        if (mTarget != null)
        {
            printwriter.print(s);
            printwriter.print("mTarget=");
            printwriter.print(mTarget);
            printwriter.print(" mTargetRequestCode=");
            printwriter.println(mTargetRequestCode);
        }
        if (getNextAnim() != 0)
        {
            printwriter.print(s);
            printwriter.print("mNextAnim=");
            printwriter.println(getNextAnim());
        }
        if (mContainer != null)
        {
            printwriter.print(s);
            printwriter.print("mContainer=");
            printwriter.println(mContainer);
        }
        if (mView != null)
        {
            printwriter.print(s);
            printwriter.print("mView=");
            printwriter.println(mView);
        }
        if (mInnerView != null)
        {
            printwriter.print(s);
            printwriter.print("mInnerView=");
            printwriter.println(mView);
        }
        if (getAnimatingAway() != null)
        {
            printwriter.print(s);
            printwriter.print("mAnimatingAway=");
            printwriter.println(getAnimatingAway());
            printwriter.print(s);
            printwriter.print("mStateAfterAnimating=");
            printwriter.println(getStateAfterAnimating());
        }
        if (mLoaderManager != null)
        {
            printwriter.print(s);
            printwriter.println("Loader Manager:");
            mLoaderManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
        if (mChildFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.println((new StringBuilder()).append("Child ").append(mChildFragmentManager).append(":").toString());
            mChildFragmentManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
    }

    public final boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    Fragment findFragmentByWho(String s)
    {
        if (s.equals(mWho))
        {
            return this;
        }
        if (mChildFragmentManager != null)
        {
            return mChildFragmentManager.findFragmentByWho(s);
        } else
        {
            return null;
        }
    }

    public final FragmentActivity getActivity()
    {
        if (mHost == null)
        {
            return null;
        } else
        {
            return (FragmentActivity)mHost.getActivity();
        }
    }

    public boolean getAllowEnterTransitionOverlap()
    {
        if (mAnimationInfo == null || mAnimationInfo.mAllowEnterTransitionOverlap == null)
        {
            return true;
        } else
        {
            return mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
        }
    }

    public boolean getAllowReturnTransitionOverlap()
    {
        if (mAnimationInfo == null || mAnimationInfo.mAllowReturnTransitionOverlap == null)
        {
            return true;
        } else
        {
            return mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
        }
    }

    View getAnimatingAway()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mAnimatingAway;
        }
    }

    public final Bundle getArguments()
    {
        return mArguments;
    }

    public final FragmentManager getChildFragmentManager()
    {
        if (mChildFragmentManager != null) goto _L2; else goto _L1
_L1:
        instantiateChildFragmentManager();
        if (mState < 5) goto _L4; else goto _L3
_L3:
        mChildFragmentManager.dispatchResume();
_L2:
        return mChildFragmentManager;
_L4:
        if (mState >= 4)
        {
            mChildFragmentManager.dispatchStart();
        } else
        if (mState >= 2)
        {
            mChildFragmentManager.dispatchActivityCreated();
        } else
        if (mState >= 1)
        {
            mChildFragmentManager.dispatchCreate();
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public Context getContext()
    {
        if (mHost == null)
        {
            return null;
        } else
        {
            return mHost.getContext();
        }
    }

    public Object getEnterTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mEnterTransition;
        }
    }

    SharedElementCallback getEnterTransitionCallback()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mEnterTransitionCallback;
        }
    }

    public Object getExitTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mExitTransition;
        }
    }

    SharedElementCallback getExitTransitionCallback()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mExitTransitionCallback;
        }
    }

    public final FragmentManager getFragmentManager()
    {
        return mFragmentManager;
    }

    public final Object getHost()
    {
        if (mHost == null)
        {
            return null;
        } else
        {
            return mHost.onGetHost();
        }
    }

    public final int getId()
    {
        return mFragmentId;
    }

    public LayoutInflater getLayoutInflater(Bundle bundle)
    {
        bundle = mHost.onGetLayoutInflater();
        getChildFragmentManager();
        LayoutInflaterCompat.setFactory2(bundle, mChildFragmentManager.getLayoutInflaterFactory());
        return bundle;
    }

    public LoaderManager getLoaderManager()
    {
        if (mLoaderManager != null)
        {
            return mLoaderManager;
        }
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, true);
            return mLoaderManager;
        }
    }

    int getNextAnim()
    {
        if (mAnimationInfo == null)
        {
            return 0;
        } else
        {
            return mAnimationInfo.mNextAnim;
        }
    }

    int getNextTransition()
    {
        if (mAnimationInfo == null)
        {
            return 0;
        } else
        {
            return mAnimationInfo.mNextTransition;
        }
    }

    int getNextTransitionStyle()
    {
        if (mAnimationInfo == null)
        {
            return 0;
        } else
        {
            return mAnimationInfo.mNextTransitionStyle;
        }
    }

    public final Fragment getParentFragment()
    {
        return mParentFragment;
    }

    public Object getReenterTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        }
        if (mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION)
        {
            return getExitTransition();
        } else
        {
            return mAnimationInfo.mReenterTransition;
        }
    }

    public final Resources getResources()
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            return mHost.getContext().getResources();
        }
    }

    public final boolean getRetainInstance()
    {
        return mRetainInstance;
    }

    public Object getReturnTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        }
        if (mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION)
        {
            return getEnterTransition();
        } else
        {
            return mAnimationInfo.mReturnTransition;
        }
    }

    public Object getSharedElementEnterTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        } else
        {
            return mAnimationInfo.mSharedElementEnterTransition;
        }
    }

    public Object getSharedElementReturnTransition()
    {
        if (mAnimationInfo == null)
        {
            return null;
        }
        if (mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION)
        {
            return getSharedElementEnterTransition();
        } else
        {
            return mAnimationInfo.mSharedElementReturnTransition;
        }
    }

    int getStateAfterAnimating()
    {
        if (mAnimationInfo == null)
        {
            return 0;
        } else
        {
            return mAnimationInfo.mStateAfterAnimating;
        }
    }

    public final String getString(int i)
    {
        return getResources().getString(i);
    }

    public final transient String getString(int i, Object aobj[])
    {
        return getResources().getString(i, aobj);
    }

    public final String getTag()
    {
        return mTag;
    }

    public final Fragment getTargetFragment()
    {
        return mTarget;
    }

    public final int getTargetRequestCode()
    {
        return mTargetRequestCode;
    }

    public final CharSequence getText(int i)
    {
        return getResources().getText(i);
    }

    public boolean getUserVisibleHint()
    {
        return mUserVisibleHint;
    }

    public View getView()
    {
        return mView;
    }

    public final boolean hasOptionsMenu()
    {
        return mHasMenu;
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    void initState()
    {
        mIndex = -1;
        mWho = null;
        mAdded = false;
        mRemoving = false;
        mFromLayout = false;
        mInLayout = false;
        mRestored = false;
        mBackStackNesting = 0;
        mFragmentManager = null;
        mChildFragmentManager = null;
        mHost = null;
        mFragmentId = 0;
        mContainerId = 0;
        mTag = null;
        mHidden = false;
        mDetached = false;
        mRetaining = false;
        mLoaderManager = null;
        mLoadersStarted = false;
        mCheckedForLoaderManager = false;
    }

    void instantiateChildFragmentManager()
    {
        if (mHost == null)
        {
            throw new IllegalStateException("Fragment has not been attached yet.");
        } else
        {
            mChildFragmentManager = new FragmentManagerImpl();
            mChildFragmentManager.attachController(mHost, new FragmentContainer() {

                final Fragment this$0;

                public Fragment instantiate(Context context, String s, Bundle bundle)
                {
                    return mHost.instantiate(context, s, bundle);
                }

                public View onFindViewById(int i)
                {
                    if (mView == null)
                    {
                        throw new IllegalStateException("Fragment does not have a view");
                    } else
                    {
                        return mView.findViewById(i);
                    }
                }

                public boolean onHasView()
                {
                    return mView != null;
                }

            
            {
                this$0 = Fragment.this;
                super();
            }
            }, this);
            return;
        }
    }

    public final boolean isAdded()
    {
        return mHost != null && mAdded;
    }

    public final boolean isDetached()
    {
        return mDetached;
    }

    public final boolean isHidden()
    {
        return mHidden;
    }

    boolean isHideReplaced()
    {
        if (mAnimationInfo == null)
        {
            return false;
        } else
        {
            return mAnimationInfo.mIsHideReplaced;
        }
    }

    final boolean isInBackStack()
    {
        return mBackStackNesting > 0;
    }

    public final boolean isInLayout()
    {
        return mInLayout;
    }

    public final boolean isMenuVisible()
    {
        return mMenuVisible;
    }

    boolean isPostponed()
    {
        if (mAnimationInfo == null)
        {
            return false;
        } else
        {
            return mAnimationInfo.mEnterTransitionPostponed;
        }
    }

    public final boolean isRemoving()
    {
        return mRemoving;
    }

    public final boolean isResumed()
    {
        return mState >= 5;
    }

    public final boolean isStateSaved()
    {
        if (mFragmentManager == null)
        {
            return false;
        } else
        {
            return mFragmentManager.isStateSaved();
        }
    }

    public final boolean isVisible()
    {
        return isAdded() && !isHidden() && mView != null && mView.getWindowToken() != null && mView.getVisibility() == 0;
    }

    public void onActivityCreated(Bundle bundle)
    {
        mCalled = true;
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
    }

    public void onAttach(Activity activity)
    {
        mCalled = true;
    }

    public void onAttach(Context context)
    {
        mCalled = true;
        if (mHost == null)
        {
            context = null;
        } else
        {
            context = mHost.getActivity();
        }
        if (context != null)
        {
            mCalled = false;
            onAttach(((Activity) (context)));
        }
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onCreate(Bundle bundle)
    {
        mCalled = true;
        restoreChildFragmentState(bundle);
        if (mChildFragmentManager != null && !mChildFragmentManager.isStateAtLeast(1))
        {
            mChildFragmentManager.dispatchCreate();
        }
    }

    public Animation onCreateAnimation(int i, boolean flag, int j)
    {
        return null;
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        getActivity().onCreateContextMenu(contextmenu, view, contextmenuinfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public void onDestroy()
    {
        mCalled = true;
        if (!mCheckedForLoaderManager)
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
        }
        if (mLoaderManager != null)
        {
            mLoaderManager.doDestroy();
        }
    }

    public void onDestroyOptionsMenu()
    {
    }

    public void onDestroyView()
    {
        mCalled = true;
    }

    public void onDetach()
    {
        mCalled = true;
    }

    public void onHiddenChanged(boolean flag)
    {
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
    }

    public void onInflate(Context context, AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
        if (mHost == null)
        {
            context = null;
        } else
        {
            context = mHost.getActivity();
        }
        if (context != null)
        {
            mCalled = false;
            onInflate(((Activity) (context)), attributeset, bundle);
        }
    }

    public void onLowMemory()
    {
        mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean flag)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
    }

    public void onPause()
    {
        mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
    }

    public void onResume()
    {
        mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    public void onStart()
    {
        mCalled = true;
        if (!mLoadersStarted)
        {
            mLoadersStarted = true;
            if (!mCheckedForLoaderManager)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
            }
            if (mLoaderManager != null)
            {
                mLoaderManager.doStart();
            }
        }
    }

    public void onStop()
    {
        mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle)
    {
    }

    public void onViewStateRestored(Bundle bundle)
    {
        mCalled = true;
    }

    FragmentManager peekChildFragmentManager()
    {
        return mChildFragmentManager;
    }

    void performActivityCreated(Bundle bundle)
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }
        mState = 2;
        mCalled = false;
        onActivityCreated(bundle);
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onActivityCreated()").toString());
        }
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchActivityCreated();
        }
    }

    void performConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchConfigurationChanged(configuration);
        }
    }

    boolean performContextItemSelected(MenuItem menuitem)
    {
        while (!mHidden && (onContextItemSelected(menuitem) || mChildFragmentManager != null && mChildFragmentManager.dispatchContextItemSelected(menuitem))) 
        {
            return true;
        }
        return false;
    }

    void performCreate(Bundle bundle)
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }
        mState = 1;
        mCalled = false;
        onCreate(bundle);
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onCreate()").toString());
        } else
        {
            return;
        }
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag1 = false;
        boolean flag2 = false;
        if (!mHidden)
        {
            boolean flag = flag2;
            if (mHasMenu)
            {
                flag = flag2;
                if (mMenuVisible)
                {
                    flag = true;
                    onCreateOptionsMenu(menu, menuinflater);
                }
            }
            flag1 = flag;
            if (mChildFragmentManager != null)
            {
                flag1 = flag | mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
            }
        }
        return flag1;
    }

    View performCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }
        mPerformedCreateView = true;
        return onCreateView(layoutinflater, viewgroup, bundle);
    }

    void performDestroy()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchDestroy();
        }
        mState = 0;
        mCalled = false;
        onDestroy();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroy()").toString());
        } else
        {
            mChildFragmentManager = null;
            return;
        }
    }

    void performDestroyView()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchDestroyView();
        }
        mState = 1;
        mCalled = false;
        onDestroyView();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroyView()").toString());
        }
        if (mLoaderManager != null)
        {
            mLoaderManager.doReportNextStart();
        }
        mPerformedCreateView = false;
    }

    void performDetach()
    {
        mCalled = false;
        onDetach();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDetach()").toString());
        }
        if (mChildFragmentManager != null)
        {
            if (!mRetaining)
            {
                throw new IllegalStateException((new StringBuilder()).append("Child FragmentManager of ").append(this).append(" was not ").append(" destroyed and this fragment is not retaining instance").toString());
            }
            mChildFragmentManager.dispatchDestroy();
            mChildFragmentManager = null;
        }
    }

    void performLowMemory()
    {
        onLowMemory();
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchLowMemory();
        }
    }

    void performMultiWindowModeChanged(boolean flag)
    {
        onMultiWindowModeChanged(flag);
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchMultiWindowModeChanged(flag);
        }
    }

    boolean performOptionsItemSelected(MenuItem menuitem)
    {
        while (!mHidden && (mHasMenu && mMenuVisible && onOptionsItemSelected(menuitem) || mChildFragmentManager != null && mChildFragmentManager.dispatchOptionsItemSelected(menuitem))) 
        {
            return true;
        }
        return false;
    }

    void performOptionsMenuClosed(Menu menu)
    {
        if (!mHidden)
        {
            if (mHasMenu && mMenuVisible)
            {
                onOptionsMenuClosed(menu);
            }
            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchOptionsMenuClosed(menu);
            }
        }
    }

    void performPause()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchPause();
        }
        mState = 4;
        mCalled = false;
        onPause();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onPause()").toString());
        } else
        {
            return;
        }
    }

    void performPictureInPictureModeChanged(boolean flag)
    {
        onPictureInPictureModeChanged(flag);
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchPictureInPictureModeChanged(flag);
        }
    }

    boolean performPrepareOptionsMenu(Menu menu)
    {
        boolean flag1 = false;
        boolean flag2 = false;
        if (!mHidden)
        {
            boolean flag = flag2;
            if (mHasMenu)
            {
                flag = flag2;
                if (mMenuVisible)
                {
                    flag = true;
                    onPrepareOptionsMenu(menu);
                }
            }
            flag1 = flag;
            if (mChildFragmentManager != null)
            {
                flag1 = flag | mChildFragmentManager.dispatchPrepareOptionsMenu(menu);
            }
        }
        return flag1;
    }

    void performReallyStop()
    {
label0:
        {
            if (mChildFragmentManager != null)
            {
                mChildFragmentManager.dispatchReallyStop();
            }
            mState = 2;
            if (mLoadersStarted)
            {
                mLoadersStarted = false;
                if (!mCheckedForLoaderManager)
                {
                    mCheckedForLoaderManager = true;
                    mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
                }
                if (mLoaderManager != null)
                {
                    if (!mHost.getRetainLoaders())
                    {
                        break label0;
                    }
                    mLoaderManager.doRetain();
                }
            }
            return;
        }
        mLoaderManager.doStop();
    }

    void performResume()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mState = 5;
        mCalled = false;
        onResume();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onResume()").toString());
        }
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchResume();
            mChildFragmentManager.execPendingActions();
        }
    }

    void performSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        if (mChildFragmentManager != null)
        {
            Parcelable parcelable = mChildFragmentManager.saveAllState();
            if (parcelable != null)
            {
                bundle.putParcelable("android:support:fragments", parcelable);
            }
        }
    }

    void performStart()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mState = 4;
        mCalled = false;
        onStart();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStart()").toString());
        }
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchStart();
        }
        if (mLoaderManager != null)
        {
            mLoaderManager.doReportStart();
        }
    }

    void performStop()
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchStop();
        }
        mState = 3;
        mCalled = false;
        onStop();
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStop()").toString());
        } else
        {
            return;
        }
    }

    public void postponeEnterTransition()
    {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String as[], int i)
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onRequestPermissionsFromFragment(this, as, i);
            return;
        }
    }

    void restoreChildFragmentState(Bundle bundle)
    {
        if (bundle != null)
        {
            bundle = bundle.getParcelable("android:support:fragments");
            if (bundle != null)
            {
                if (mChildFragmentManager == null)
                {
                    instantiateChildFragmentManager();
                }
                mChildFragmentManager.restoreAllState(bundle, mChildNonConfig);
                mChildNonConfig = null;
                mChildFragmentManager.dispatchCreate();
            }
        }
    }

    final void restoreViewState(Bundle bundle)
    {
        if (mSavedViewState != null)
        {
            mInnerView.restoreHierarchyState(mSavedViewState);
            mSavedViewState = null;
        }
        mCalled = false;
        onViewStateRestored(bundle);
        if (!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onViewStateRestored()").toString());
        } else
        {
            return;
        }
    }

    public void setAllowEnterTransitionOverlap(boolean flag)
    {
        ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(flag);
    }

    public void setAllowReturnTransitionOverlap(boolean flag)
    {
        ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(flag);
    }

    void setAnimatingAway(View view)
    {
        ensureAnimationInfo().mAnimatingAway = view;
    }

    public void setArguments(Bundle bundle)
    {
        if (mIndex >= 0 && isStateSaved())
        {
            throw new IllegalStateException("Fragment already active and state has been saved");
        } else
        {
            mArguments = bundle;
            return;
        }
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        ensureAnimationInfo().mEnterTransitionCallback = sharedelementcallback;
    }

    public void setEnterTransition(Object obj)
    {
        ensureAnimationInfo().mEnterTransition = obj;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        ensureAnimationInfo().mExitTransitionCallback = sharedelementcallback;
    }

    public void setExitTransition(Object obj)
    {
        ensureAnimationInfo().mExitTransition = obj;
    }

    public void setHasOptionsMenu(boolean flag)
    {
        if (mHasMenu != flag)
        {
            mHasMenu = flag;
            if (isAdded() && !isHidden())
            {
                mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setHideReplaced(boolean flag)
    {
        ensureAnimationInfo().mIsHideReplaced = flag;
    }

    final void setIndex(int i, Fragment fragment)
    {
        mIndex = i;
        if (fragment != null)
        {
            mWho = (new StringBuilder()).append(fragment.mWho).append(":").append(mIndex).toString();
            return;
        } else
        {
            mWho = (new StringBuilder()).append("android:fragment:").append(mIndex).toString();
            return;
        }
    }

    public void setInitialSavedState(SavedState savedstate)
    {
        if (mIndex >= 0)
        {
            throw new IllegalStateException("Fragment already active");
        }
        if (savedstate != null && savedstate.mState != null)
        {
            savedstate = savedstate.mState;
        } else
        {
            savedstate = null;
        }
        mSavedFragmentState = savedstate;
    }

    public void setMenuVisibility(boolean flag)
    {
        if (mMenuVisible != flag)
        {
            mMenuVisible = flag;
            if (mHasMenu && isAdded() && !isHidden())
            {
                mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setNextAnim(int i)
    {
        if (mAnimationInfo == null && i == 0)
        {
            return;
        } else
        {
            ensureAnimationInfo().mNextAnim = i;
            return;
        }
    }

    void setNextTransition(int i, int j)
    {
        if (mAnimationInfo == null && i == 0 && j == 0)
        {
            return;
        } else
        {
            ensureAnimationInfo();
            mAnimationInfo.mNextTransition = i;
            mAnimationInfo.mNextTransitionStyle = j;
            return;
        }
    }

    void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        ensureAnimationInfo();
        if (onstartentertransitionlistener != mAnimationInfo.mStartEnterTransitionListener)
        {
            if (onstartentertransitionlistener != null && mAnimationInfo.mStartEnterTransitionListener != null)
            {
                throw new IllegalStateException((new StringBuilder()).append("Trying to set a replacement startPostponedEnterTransition on ").append(this).toString());
            }
            if (mAnimationInfo.mEnterTransitionPostponed)
            {
                mAnimationInfo.mStartEnterTransitionListener = onstartentertransitionlistener;
            }
            if (onstartentertransitionlistener != null)
            {
                onstartentertransitionlistener.startListening();
                return;
            }
        }
    }

    public void setReenterTransition(Object obj)
    {
        ensureAnimationInfo().mReenterTransition = obj;
    }

    public void setRetainInstance(boolean flag)
    {
        mRetainInstance = flag;
    }

    public void setReturnTransition(Object obj)
    {
        ensureAnimationInfo().mReturnTransition = obj;
    }

    public void setSharedElementEnterTransition(Object obj)
    {
        ensureAnimationInfo().mSharedElementEnterTransition = obj;
    }

    public void setSharedElementReturnTransition(Object obj)
    {
        ensureAnimationInfo().mSharedElementReturnTransition = obj;
    }

    void setStateAfterAnimating(int i)
    {
        ensureAnimationInfo().mStateAfterAnimating = i;
    }

    public void setTargetFragment(Fragment fragment, int i)
    {
        FragmentManager fragmentmanager1 = getFragmentManager();
        FragmentManager fragmentmanager;
        if (fragment != null)
        {
            fragmentmanager = fragment.getFragmentManager();
        } else
        {
            fragmentmanager = null;
        }
        if (fragmentmanager1 != null && fragmentmanager != null && fragmentmanager1 != fragmentmanager)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Fragment ").append(fragment).append(" must share the same FragmentManager to be set as a target fragment").toString());
        }
        for (Fragment fragment1 = fragment; fragment1 != null; fragment1 = fragment1.getTargetFragment())
        {
            if (fragment1 == this)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Setting ").append(fragment).append(" as the target of ").append(this).append(" would create a target cycle").toString());
            }
        }

        mTarget = fragment;
        mTargetRequestCode = i;
    }

    public void setUserVisibleHint(boolean flag)
    {
        if (!mUserVisibleHint && flag && mState < 4 && mFragmentManager != null && isAdded())
        {
            mFragmentManager.performPendingDeferredStart(this);
        }
        mUserVisibleHint = flag;
        if (mState < 4 && !flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mDeferStart = flag;
    }

    public boolean shouldShowRequestPermissionRationale(String s)
    {
        if (mHost != null)
        {
            return mHost.onShouldShowRequestPermissionRationale(s);
        } else
        {
            return false;
        }
    }

    public void startActivity(Intent intent)
    {
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartActivityFromFragment(this, intent, -1, bundle);
            return;
        }
    }

    public void startActivityForResult(Intent intent, int i)
    {
        startActivityForResult(intent, i, null);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartActivityFromFragment(this, intent, i, bundle);
            return;
        }
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartIntentSenderFromFragment(this, intentsender, i, intent, j, k, l, bundle);
            return;
        }
    }

    public void startPostponedEnterTransition()
    {
        if (mFragmentManager == null || mFragmentManager.mHost == null)
        {
            ensureAnimationInfo().mEnterTransitionPostponed = false;
            return;
        }
        if (Looper.myLooper() != mFragmentManager.mHost.getHandler().getLooper())
        {
            mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable() {

                final Fragment this$0;

                public void run()
                {
                    callStartTransitionListener();
                }

            
            {
                this$0 = Fragment.this;
                super();
            }
            });
            return;
        } else
        {
            callStartTransitionListener();
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, stringbuilder);
        if (mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if (mFragmentId != 0)
        {
            stringbuilder.append(" id=0x");
            stringbuilder.append(Integer.toHexString(mFragmentId));
        }
        if (mTag != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mTag);
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }


}
