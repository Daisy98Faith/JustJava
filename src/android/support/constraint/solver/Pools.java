// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;


final class Pools
{
    static interface Pool
    {

        public abstract Object acquire();

        public abstract boolean release(Object obj);

        public abstract void releaseAll(Object aobj[], int i);
    }

    static class SimplePool
        implements Pool
    {

        private final Object mPool[];
        private int mPoolSize;

        private boolean isInPool(Object obj)
        {
            for (int i = 0; i < mPoolSize; i++)
            {
                if (mPool[i] == obj)
                {
                    return true;
                }
            }

            return false;
        }

        public Object acquire()
        {
            if (mPoolSize > 0)
            {
                int i = mPoolSize - 1;
                Object obj = mPool[i];
                mPool[i] = null;
                mPoolSize = mPoolSize - 1;
                return obj;
            } else
            {
                return null;
            }
        }

        public boolean release(Object obj)
        {
            if (mPoolSize < mPool.length)
            {
                mPool[mPoolSize] = obj;
                mPoolSize = mPoolSize + 1;
                return true;
            } else
            {
                return false;
            }
        }

        public void releaseAll(Object aobj[], int i)
        {
            int j = i;
            if (i > aobj.length)
            {
                j = aobj.length;
            }
            for (i = 0; i < j; i++)
            {
                Object obj = aobj[i];
                if (mPoolSize < mPool.length)
                {
                    mPool[mPoolSize] = obj;
                    mPoolSize = mPoolSize + 1;
                }
            }

        }

        SimplePool(int i)
        {
            if (i <= 0)
            {
                throw new IllegalArgumentException("The max pool size must be > 0");
            } else
            {
                mPool = new Object[i];
                return;
            }
        }
    }


    private static final boolean DEBUG = false;

    private Pools()
    {
    }
}
