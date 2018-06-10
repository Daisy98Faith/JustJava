// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene
{

    private Context mContext;
    private Runnable mEnterAction;
    private Runnable mExitAction;
    private View mLayout;
    private int mLayoutId;
    private ViewGroup mSceneRoot;

    public Scene(ViewGroup viewgroup)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
    }

    private Scene(ViewGroup viewgroup, int i, Context context)
    {
        mLayoutId = -1;
        mContext = context;
        mSceneRoot = viewgroup;
        mLayoutId = i;
    }

    public Scene(ViewGroup viewgroup, View view)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
        mLayout = view;
    }

    static Scene getCurrentScene(View view)
    {
        return (Scene)view.getTag(R.id.transition_current_scene);
    }

    public static Scene getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        Object obj = (SparseArray)viewgroup.getTag(R.id.transition_scene_layoutid_cache);
        SparseArray sparsearray = ((SparseArray) (obj));
        if (obj == null)
        {
            sparsearray = new SparseArray();
            viewgroup.setTag(R.id.transition_scene_layoutid_cache, sparsearray);
        }
        obj = (Scene)sparsearray.get(i);
        if (obj != null)
        {
            return ((Scene) (obj));
        } else
        {
            viewgroup = new Scene(viewgroup, i, context);
            sparsearray.put(i, viewgroup);
            return viewgroup;
        }
    }

    static void setCurrentScene(View view, Scene scene)
    {
        view.setTag(R.id.transition_current_scene, scene);
    }

    public void enter()
    {
        if (mLayoutId > 0 || mLayout != null)
        {
            getSceneRoot().removeAllViews();
            if (mLayoutId > 0)
            {
                LayoutInflater.from(mContext).inflate(mLayoutId, mSceneRoot);
            } else
            {
                mSceneRoot.addView(mLayout);
            }
        }
        if (mEnterAction != null)
        {
            mEnterAction.run();
        }
        setCurrentScene(mSceneRoot, this);
    }

    public void exit()
    {
        if (getCurrentScene(mSceneRoot) == this && mExitAction != null)
        {
            mExitAction.run();
        }
    }

    public ViewGroup getSceneRoot()
    {
        return mSceneRoot;
    }

    boolean isCreatedFromLayoutResource()
    {
        return mLayoutId > 0;
    }

    public void setEnterAction(Runnable runnable)
    {
        mEnterAction = runnable;
    }

    public void setExitAction(Runnable runnable)
    {
        mExitAction = runnable;
    }
}
