// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers, MapCollections

public final class ArraySet
    implements Collection, Set
{

    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int INT[] = new int[0];
    private static final Object OBJECT[] = new Object[0];
    private static final String TAG = "ArraySet";
    static Object sBaseCache[];
    static int sBaseCacheSize;
    static Object sTwiceBaseCache[];
    static int sTwiceBaseCacheSize;
    Object mArray[];
    MapCollections mCollections;
    int mHashes[];
    final boolean mIdentityHashCode;
    int mSize;

    public ArraySet()
    {
        this(0, false);
    }

    public ArraySet(int i)
    {
        this(i, false);
    }

    public ArraySet(int i, boolean flag)
    {
        mIdentityHashCode = flag;
        if (i == 0)
        {
            mHashes = INT;
            mArray = OBJECT;
        } else
        {
            allocArrays(i);
        }
        mSize = 0;
    }

    public ArraySet(ArraySet arrayset)
    {
        this();
        if (arrayset != null)
        {
            addAll(arrayset);
        }
    }

    public ArraySet(Collection collection)
    {
        this();
        if (collection != null)
        {
            addAll(collection);
        }
    }

    private void allocArrays(int i)
    {
        if (i != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        Object aobj[];
        if (sTwiceBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        aobj = sTwiceBaseCache;
        mArray = aobj;
        sTwiceBaseCache = (Object[])(Object[])aobj[0];
        mHashes = (int[])(int[])aobj[1];
        aobj[1] = null;
        aobj[0] = null;
        sTwiceBaseCacheSize--;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i];
        return;
        Exception exception;
        exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if (i != 4) goto _L4; else goto _L3
_L3:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        exception = ((Exception) (sBaseCache));
        mArray = exception;
        sBaseCache = (Object[])(Object[])exception[0];
        mHashes = (int[])(int[])exception[1];
        exception[1] = null;
        exception[0] = null;
        sBaseCacheSize--;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
          goto _L4
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if (ai.length != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sTwiceBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (sTwiceBaseCache));
            break MISSING_BLOCK_LABEL_24;
        }
          goto _L3
_L8:
        sTwiceBaseCache = aobj;
        sTwiceBaseCacheSize++;
_L3:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw ai;
_L2:
        if (ai.length != 4) goto _L5; else goto _L4
_L4:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sBaseCacheSize >= 10) goto _L7; else goto _L6
_L6:
        aobj[0] = ((Object) (sBaseCache));
        aobj[1] = ai;
        i--;
        break MISSING_BLOCK_LABEL_130;
_L9:
        sBaseCache = aobj;
        sBaseCacheSize++;
_L7:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw ai;
        aobj[1] = ai;
        i--;
        while (i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L8
_L5:
        return;
        while (i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L9
    }

    private MapCollections getCollection()
    {
        if (mCollections == null)
        {
            mCollections = new MapCollections() {

                final ArraySet this$0;

                protected void colClear()
                {
                    clear();
                }

                protected Object colGetEntry(int i, int j)
                {
                    return mArray[i];
                }

                protected Map colGetMap()
                {
                    throw new UnsupportedOperationException("not a map");
                }

                protected int colGetSize()
                {
                    return mSize;
                }

                protected int colIndexOfKey(Object obj)
                {
                    return indexOf(obj);
                }

                protected int colIndexOfValue(Object obj)
                {
                    return indexOf(obj);
                }

                protected void colPut(Object obj, Object obj1)
                {
                    add(obj);
                }

                protected void colRemoveAt(int i)
                {
                    removeAt(i);
                }

                protected Object colSetValue(int i, Object obj)
                {
                    throw new UnsupportedOperationException("not a map");
                }

            
            {
                this$0 = ArraySet.this;
                super();
            }
            };
        }
        return mCollections;
    }

    private int indexOf(Object obj, int i)
    {
        int i1 = mSize;
        int j;
        if (i1 == 0)
        {
            j = -1;
        } else
        {
            int l = ContainerHelpers.binarySearch(mHashes, i1, i);
            j = l;
            if (l >= 0)
            {
                j = l;
                if (!obj.equals(mArray[l]))
                {
                    int k;
                    for (k = l + 1; k < i1 && mHashes[k] == i; k++)
                    {
                        if (obj.equals(mArray[k]))
                        {
                            return k;
                        }
                    }

                    for (l--; l >= 0 && mHashes[l] == i; l--)
                    {
                        if (obj.equals(mArray[l]))
                        {
                            return l;
                        }
                    }

                    return ~k;
                }
            }
        }
        return j;
    }

    private int indexOfNull()
    {
        int l = mSize;
        int i;
        if (l == 0)
        {
            i = -1;
        } else
        {
            int k = ContainerHelpers.binarySearch(mHashes, l, 0);
            i = k;
            if (k >= 0)
            {
                i = k;
                if (mArray[k] != null)
                {
                    int j;
                    for (j = k + 1; j < l && mHashes[j] == 0; j++)
                    {
                        if (mArray[j] == null)
                        {
                            return j;
                        }
                    }

                    for (k--; k >= 0 && mHashes[k] == 0; k--)
                    {
                        if (mArray[k] == null)
                        {
                            return k;
                        }
                    }

                    return ~j;
                }
            }
        }
        return i;
    }

    public boolean add(Object obj)
    {
        int k;
        byte byte0;
        int i1;
        byte0 = 8;
        int i;
        if (obj == null)
        {
            k = 0;
            i = indexOfNull();
        } else
        {
            int l;
            if (mIdentityHashCode)
            {
                i = System.identityHashCode(obj);
            } else
            {
                i = obj.hashCode();
            }
            l = indexOf(obj, i);
            k = i;
            i = l;
        }
        if (i >= 0)
        {
            return false;
        }
        i1 = ~i;
        if (mSize < mHashes.length) goto _L2; else goto _L1
_L1:
        if (mSize < 8) goto _L4; else goto _L3
_L3:
        int j = mSize + (mSize >> 1);
_L6:
        int ai[] = mHashes;
        Object aobj[] = mArray;
        allocArrays(j);
        if (mHashes.length > 0)
        {
            System.arraycopy(ai, 0, mHashes, 0, ai.length);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
        }
        freeArrays(ai, aobj, mSize);
_L2:
        if (i1 < mSize)
        {
            System.arraycopy(mHashes, i1, mHashes, i1 + 1, mSize - i1);
            System.arraycopy(((Object) (mArray)), i1, ((Object) (mArray)), i1 + 1, mSize - i1);
        }
        mHashes[i1] = k;
        mArray[i1] = obj;
        mSize = mSize + 1;
        return true;
_L4:
        j = byte0;
        if (mSize < 4)
        {
            j = 4;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void addAll(ArraySet arrayset)
    {
        int j = arrayset.mSize;
        ensureCapacity(mSize + j);
        if (mSize == 0)
        {
            if (j > 0)
            {
                System.arraycopy(arrayset.mHashes, 0, mHashes, 0, j);
                System.arraycopy(((Object) (arrayset.mArray)), 0, ((Object) (mArray)), 0, j);
                mSize = j;
            }
        } else
        {
            int i = 0;
            while (i < j) 
            {
                add(arrayset.valueAt(i));
                i++;
            }
        }
    }

    public boolean addAll(Collection collection)
    {
        ensureCapacity(mSize + collection.size());
        boolean flag = false;
        for (collection = collection.iterator(); collection.hasNext();)
        {
            flag |= add(collection.next());
        }

        return flag;
    }

    public void append(Object obj)
    {
        int j = mSize;
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        if (mIdentityHashCode)
        {
            i = System.identityHashCode(obj);
        } else
        {
            i = obj.hashCode();
        }
        if (j >= mHashes.length)
        {
            throw new IllegalStateException("Array is full");
        }
        if (j > 0 && mHashes[j - 1] > i)
        {
            add(obj);
            return;
        } else
        {
            mSize = j + 1;
            mHashes[j] = i;
            mArray[j] = obj;
            return;
        }
    }

    public void clear()
    {
        if (mSize != 0)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = INT;
            mArray = OBJECT;
            mSize = 0;
        }
    }

    public boolean contains(Object obj)
    {
        return indexOf(obj) >= 0;
    }

    public boolean containsAll(Collection collection)
    {
        for (collection = collection.iterator(); collection.hasNext();)
        {
            if (!contains(collection.next()))
            {
                return false;
            }
        }

        return true;
    }

    public void ensureCapacity(int i)
    {
        if (mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if (mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, mSize);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, mSize);
            }
            freeArrays(ai, aobj, mSize);
        }
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof Set))
            {
                break label0;
            }
            obj = (Set)obj;
            if (size() != ((Set) (obj)).size())
            {
                return false;
            }
            int i = 0;
            do
            {
                boolean flag;
                try
                {
                    if (i >= mSize)
                    {
                        break;
                    }
                    flag = ((Set) (obj)).contains(valueAt(i));
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    return false;
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    return false;
                }
                if (!flag)
                {
                    return false;
                }
                i++;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        int j = 0;
        int i = 0;
        for (int k = mSize; i < k; i++)
        {
            j += ai[i];
        }

        return j;
    }

    public int indexOf(Object obj)
    {
        if (obj == null)
        {
            return indexOfNull();
        }
        int i;
        if (mIdentityHashCode)
        {
            i = System.identityHashCode(obj);
        } else
        {
            i = obj.hashCode();
        }
        return indexOf(obj, i);
    }

    public boolean isEmpty()
    {
        return mSize <= 0;
    }

    public Iterator iterator()
    {
        return getCollection().getKeySet().iterator();
    }

    public boolean remove(Object obj)
    {
        int i = indexOf(obj);
        if (i >= 0)
        {
            removeAt(i);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean removeAll(ArraySet arrayset)
    {
        int j = arrayset.mSize;
        int k = mSize;
        for (int i = 0; i < j; i++)
        {
            remove(arrayset.valueAt(i));
        }

        return k != mSize;
    }

    public boolean removeAll(Collection collection)
    {
        boolean flag = false;
        for (collection = collection.iterator(); collection.hasNext();)
        {
            flag |= remove(collection.next());
        }

        return flag;
    }

    public Object removeAt(int i)
    {
        int j = 8;
        Object obj = mArray[i];
        if (mSize <= 1)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = INT;
            mArray = OBJECT;
            mSize = 0;
        } else
        if (mHashes.length > 8 && mSize < mHashes.length / 3)
        {
            if (mSize > 8)
            {
                j = mSize + (mSize >> 1);
            }
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(j);
            mSize = mSize - 1;
            if (i > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, i);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i);
            }
            if (i < mSize)
            {
                System.arraycopy(ai, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (aobj)), i + 1, ((Object) (mArray)), i, mSize - i);
                return obj;
            }
        } else
        {
            mSize = mSize - 1;
            if (i < mSize)
            {
                System.arraycopy(mHashes, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (mArray)), i + 1, ((Object) (mArray)), i, mSize - i);
            }
            mArray[mSize] = null;
            return obj;
        }
        return obj;
    }

    public boolean retainAll(Collection collection)
    {
        boolean flag = false;
        for (int i = mSize - 1; i >= 0; i--)
        {
            if (!collection.contains(mArray[i]))
            {
                removeAt(i);
                flag = true;
            }
        }

        return flag;
    }

    public int size()
    {
        return mSize;
    }

    public Object[] toArray()
    {
        Object aobj[] = new Object[mSize];
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj)), 0, mSize);
        return aobj;
    }

    public Object[] toArray(Object aobj[])
    {
        Object aobj1[] = aobj;
        if (aobj.length < mSize)
        {
            aobj1 = (Object[])(Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), mSize);
        }
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj1)), 0, mSize);
        if (aobj1.length > mSize)
        {
            aobj1[mSize] = null;
        }
        return aobj1;
    }

    public String toString()
    {
        if (isEmpty())
        {
            return "{}";
        }
        StringBuilder stringbuilder = new StringBuilder(mSize * 14);
        stringbuilder.append('{');
        int i = 0;
        while (i < mSize) 
        {
            if (i > 0)
            {
                stringbuilder.append(", ");
            }
            Object obj = valueAt(i);
            if (obj != this)
            {
                stringbuilder.append(obj);
            } else
            {
                stringbuilder.append("(this Set)");
            }
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public Object valueAt(int i)
    {
        return mArray[i];
    }

}
