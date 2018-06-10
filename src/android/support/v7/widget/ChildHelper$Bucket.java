// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            ChildHelper

static class mData
{

    static final int BITS_PER_WORD = 64;
    static final long LAST_BIT = 0x8000000000000000L;
    long mData;
    mData mNext;

    private void ensureNext()
    {
        if (mNext == null)
        {
            mNext = new <init>();
        }
    }

    void clear(int i)
    {
        if (i >= 64)
        {
            if (mNext != null)
            {
                mNext.clear(i - 64);
            }
            return;
        } else
        {
            mData = mData & ~(1L << i);
            return;
        }
    }

    int countOnesBefore(int i)
    {
        if (mNext == null)
        {
            if (i >= 64)
            {
                return Long.bitCount(mData);
            } else
            {
                return Long.bitCount(mData & (1L << i) - 1L);
            }
        }
        if (i < 64)
        {
            return Long.bitCount(mData & (1L << i) - 1L);
        } else
        {
            return mNext.countOnesBefore(i - 64) + Long.bitCount(mData);
        }
    }

    boolean get(int i)
    {
        if (i >= 64)
        {
            ensureNext();
            return mNext.get(i - 64);
        }
        return (mData & 1L << i) != 0L;
    }

    void insert(int i, boolean flag)
    {
        if (i >= 64)
        {
            ensureNext();
            mNext.insert(i - 64, flag);
        } else
        {
            long l;
            boolean flag1;
            if ((mData & 0x8000000000000000L) != 0L)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            l = (1L << i) - 1L;
            mData = mData & l | (mData & (-1L ^ l)) << 1;
            if (flag)
            {
                set(i);
            } else
            {
                clear(i);
            }
            if (flag1 || mNext != null)
            {
                ensureNext();
                mNext.insert(0, flag1);
                return;
            }
        }
    }

    boolean remove(int i)
    {
        boolean flag1;
        if (i >= 64)
        {
            ensureNext();
            flag1 = mNext.remove(i - 64);
        } else
        {
            long l = 1L << i;
            boolean flag;
            if ((mData & l) != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mData = mData & (-1L ^ l);
            l--;
            mData = mData & l | Long.rotateRight(mData & (-1L ^ l), 1);
            flag1 = flag;
            if (mNext != null)
            {
                if (mNext.get(0))
                {
                    set(63);
                }
                mNext.remove(0);
                return flag;
            }
        }
        return flag1;
    }

    void reset()
    {
        mData = 0L;
        if (mNext != null)
        {
            mNext.reset();
        }
    }

    void set(int i)
    {
        if (i >= 64)
        {
            ensureNext();
            mNext.set(i - 64);
            return;
        } else
        {
            mData = mData | 1L << i;
            return;
        }
    }

    public String toString()
    {
        if (mNext == null)
        {
            return Long.toBinaryString(mData);
        } else
        {
            return (new StringBuilder()).append(mNext.toString()).append("xx").append(Long.toBinaryString(mData)).toString();
        }
    }

    ()
    {
        mData = 0L;
    }
}
