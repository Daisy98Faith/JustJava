// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class DirectedAcyclicGraph
{

    private final SimpleArrayMap mGraph = new SimpleArrayMap();
    private final android.support.v4.util.Pools.Pool mListPool = new android.support.v4.util.Pools.SimplePool(10);
    private final ArrayList mSortResult = new ArrayList();
    private final HashSet mSortTmpMarked = new HashSet();

    DirectedAcyclicGraph()
    {
    }

    private void dfs(Object obj, ArrayList arraylist, HashSet hashset)
    {
        if (arraylist.contains(obj))
        {
            return;
        }
        if (hashset.contains(obj))
        {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashset.add(obj);
        ArrayList arraylist1 = (ArrayList)mGraph.get(obj);
        if (arraylist1 != null)
        {
            int i = 0;
            for (int j = arraylist1.size(); i < j; i++)
            {
                dfs(arraylist1.get(i), arraylist, hashset);
            }

        }
        hashset.remove(obj);
        arraylist.add(obj);
    }

    private ArrayList getEmptyList()
    {
        ArrayList arraylist1 = (ArrayList)mListPool.acquire();
        ArrayList arraylist = arraylist1;
        if (arraylist1 == null)
        {
            arraylist = new ArrayList();
        }
        return arraylist;
    }

    private void poolList(ArrayList arraylist)
    {
        arraylist.clear();
        mListPool.release(arraylist);
    }

    void addEdge(Object obj, Object obj1)
    {
        if (!mGraph.containsKey(obj) || !mGraph.containsKey(obj1))
        {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arraylist1 = (ArrayList)mGraph.get(obj);
        ArrayList arraylist = arraylist1;
        if (arraylist1 == null)
        {
            arraylist = getEmptyList();
            mGraph.put(obj, arraylist);
        }
        arraylist.add(obj1);
    }

    void addNode(Object obj)
    {
        if (!mGraph.containsKey(obj))
        {
            mGraph.put(obj, null);
        }
    }

    void clear()
    {
        int i = 0;
        for (int j = mGraph.size(); i < j; i++)
        {
            ArrayList arraylist = (ArrayList)mGraph.valueAt(i);
            if (arraylist != null)
            {
                poolList(arraylist);
            }
        }

        mGraph.clear();
    }

    boolean contains(Object obj)
    {
        return mGraph.containsKey(obj);
    }

    List getIncomingEdges(Object obj)
    {
        return (List)mGraph.get(obj);
    }

    List getOutgoingEdges(Object obj)
    {
        ArrayList arraylist = null;
        int i = 0;
        for (int j = mGraph.size(); i < j;)
        {
            ArrayList arraylist2 = (ArrayList)mGraph.valueAt(i);
            ArrayList arraylist1 = arraylist;
            if (arraylist2 != null)
            {
                arraylist1 = arraylist;
                if (arraylist2.contains(obj))
                {
                    arraylist1 = arraylist;
                    if (arraylist == null)
                    {
                        arraylist1 = new ArrayList();
                    }
                    arraylist1.add(mGraph.keyAt(i));
                }
            }
            i++;
            arraylist = arraylist1;
        }

        return arraylist;
    }

    ArrayList getSortedList()
    {
        mSortResult.clear();
        mSortTmpMarked.clear();
        int i = 0;
        for (int j = mGraph.size(); i < j; i++)
        {
            dfs(mGraph.keyAt(i), mSortResult, mSortTmpMarked);
        }

        return mSortResult;
    }

    boolean hasOutgoingEdges(Object obj)
    {
        int i = 0;
        for (int j = mGraph.size(); i < j; i++)
        {
            ArrayList arraylist = (ArrayList)mGraph.valueAt(i);
            if (arraylist != null && arraylist.contains(obj))
            {
                return true;
            }
        }

        return false;
    }

    int size()
    {
        return mGraph.size();
    }
}
