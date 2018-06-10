// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;

import java.io.PrintStream;
import java.util.Arrays;

// Referenced classes of package android.support.constraint.solver:
//            SolverVariable, Cache, ArrayRow

public class ArrayLinkedVariables
{

    private static final boolean DEBUG = false;
    private static final int NONE = -1;
    private int ROW_SIZE;
    private SolverVariable candidate;
    int currentSize;
    private int mArrayIndices[];
    private int mArrayNextIndices[];
    private float mArrayValues[];
    private final Cache mCache;
    private boolean mDidFillOnce;
    private int mHead;
    private int mLast;
    private final ArrayRow mRow;

    ArrayLinkedVariables(ArrayRow arrayrow, Cache cache)
    {
        currentSize = 0;
        ROW_SIZE = 8;
        candidate = null;
        mArrayIndices = new int[ROW_SIZE];
        mArrayNextIndices = new int[ROW_SIZE];
        mArrayValues = new float[ROW_SIZE];
        mHead = -1;
        mLast = -1;
        mDidFillOnce = false;
        mRow = arrayrow;
        mCache = cache;
    }

    public final void add(SolverVariable solvervariable, float f)
    {
        if (f != 0.0F) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mHead != -1)
        {
            break; /* Loop/switch isn't completed */
        }
        mHead = 0;
        mArrayValues[mHead] = f;
        mArrayIndices[mHead] = solvervariable.id;
        mArrayNextIndices[mHead] = -1;
        currentSize = currentSize + 1;
        if (!mDidFillOnce)
        {
            mLast = mLast + 1;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        int i;
        int j;
        int j1;
        i = mHead;
        j1 = -1;
        j = 0;
_L6:
        if (i == -1 || j >= currentSize) goto _L5; else goto _L4
_L4:
label0:
        {
            int l = mArrayIndices[i];
            if (l != solvervariable.id)
            {
                break label0;
            }
            solvervariable = mArrayValues;
            solvervariable[i] = solvervariable[i] + f;
            if (mArrayValues[i] == 0.0F)
            {
                if (i == mHead)
                {
                    mHead = mArrayNextIndices[i];
                } else
                {
                    mArrayNextIndices[j1] = mArrayNextIndices[i];
                }
                mCache.mIndexedVariables[l].removeClientEquation(mRow);
                if (mDidFillOnce)
                {
                    mLast = i;
                }
                currentSize = currentSize - 1;
                return;
            }
        }
          goto _L1
        if (mArrayIndices[i] < solvervariable.id)
        {
            j1 = i;
        }
        i = mArrayNextIndices[i];
        j++;
          goto _L6
_L5:
        int i1;
        i = mLast + 1;
        int k;
        if (mDidFillOnce)
        {
            if (mArrayIndices[mLast] == -1)
            {
                i = mLast;
            } else
            {
                i = mArrayIndices.length;
            }
        }
        k = i;
        if (i < mArrayIndices.length) goto _L8; else goto _L7
_L7:
        k = i;
        if (currentSize >= mArrayIndices.length) goto _L8; else goto _L9
_L9:
        i1 = 0;
_L10:
        k = i;
        if (i1 < mArrayIndices.length)
        {
            if (mArrayIndices[i1] != -1)
            {
                break MISSING_BLOCK_LABEL_538;
            }
            k = i1;
        }
_L8:
        i = k;
        if (k >= mArrayIndices.length)
        {
            i = mArrayIndices.length;
            ROW_SIZE = ROW_SIZE * 2;
            mDidFillOnce = false;
            mLast = i - 1;
            mArrayValues = Arrays.copyOf(mArrayValues, ROW_SIZE);
            mArrayIndices = Arrays.copyOf(mArrayIndices, ROW_SIZE);
            mArrayNextIndices = Arrays.copyOf(mArrayNextIndices, ROW_SIZE);
        }
        mArrayIndices[i] = solvervariable.id;
        mArrayValues[i] = f;
        if (j1 != -1)
        {
            mArrayNextIndices[i] = mArrayNextIndices[j1];
            mArrayNextIndices[j1] = i;
        } else
        {
            mArrayNextIndices[i] = mHead;
            mHead = i;
        }
        currentSize = currentSize + 1;
        if (!mDidFillOnce)
        {
            mLast = mLast + 1;
        }
        if (mLast >= mArrayIndices.length)
        {
            mDidFillOnce = true;
            mLast = mArrayIndices.length - 1;
            return;
        }
          goto _L1
        i1++;
          goto _L10
    }

    public final void clear()
    {
        mHead = -1;
        mLast = -1;
        mDidFillOnce = false;
        currentSize = 0;
    }

    final boolean containsKey(SolverVariable solvervariable)
    {
        if (mHead != -1)
        {
            int j = mHead;
            int i = 0;
            while (j != -1 && i < currentSize) 
            {
                if (mArrayIndices[j] == solvervariable.id)
                {
                    return true;
                }
                j = mArrayNextIndices[j];
                i++;
            }
        }
        return false;
    }

    public void display()
    {
        int j = currentSize;
        System.out.print("{ ");
        int i = 0;
        while (i < j) 
        {
            SolverVariable solvervariable = getVariable(i);
            if (solvervariable != null)
            {
                System.out.print((new StringBuilder()).append(solvervariable).append(" = ").append(getVariableValue(i)).append(" ").toString());
            }
            i++;
        }
        System.out.println(" }");
    }

    void divideByAmount(float f)
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            float af[] = mArrayValues;
            af[j] = af[j] / f;
            j = mArrayNextIndices[j];
        }

    }

    public final float get(SolverVariable solvervariable)
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            if (mArrayIndices[j] == solvervariable.id)
            {
                return mArrayValues[j];
            }
            j = mArrayNextIndices[j];
        }

        return 0.0F;
    }

    SolverVariable getPivotCandidate()
    {
        SolverVariable solvervariable1;
        if (candidate == null)
        {
            int j = mHead;
            int i = 0;
            SolverVariable solvervariable = null;
label0:
            do
            {
label1:
                {
                    solvervariable1 = solvervariable;
                    if (j == -1)
                    {
                        break label0;
                    }
                    solvervariable1 = solvervariable;
                    if (i >= currentSize)
                    {
                        break label0;
                    }
                    solvervariable1 = solvervariable;
                    if (mArrayValues[j] >= 0.0F)
                    {
                        break label1;
                    }
                    SolverVariable solvervariable2 = mCache.mIndexedVariables[mArrayIndices[j]];
                    if (solvervariable != null)
                    {
                        solvervariable1 = solvervariable;
                        if (solvervariable.strength >= solvervariable2.strength)
                        {
                            break label1;
                        }
                    }
                    solvervariable1 = solvervariable2;
                }
                j = mArrayNextIndices[j];
                i++;
                solvervariable = solvervariable1;
            } while (true);
        } else
        {
            solvervariable1 = candidate;
        }
        return solvervariable1;
    }

    final SolverVariable getVariable(int i)
    {
        int k = mHead;
        for (int j = 0; k != -1 && j < currentSize; j++)
        {
            if (j == i)
            {
                return mCache.mIndexedVariables[mArrayIndices[k]];
            }
            k = mArrayNextIndices[k];
        }

        return null;
    }

    final float getVariableValue(int i)
    {
        int k = mHead;
        for (int j = 0; k != -1 && j < currentSize; j++)
        {
            if (j == i)
            {
                return mArrayValues[k];
            }
            k = mArrayNextIndices[k];
        }

        return 0.0F;
    }

    boolean hasAtLeastOnePositiveVariable()
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            if (mArrayValues[j] > 0.0F)
            {
                return true;
            }
            j = mArrayNextIndices[j];
        }

        return false;
    }

    void invert()
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            float af[] = mArrayValues;
            af[j] = af[j] * -1F;
            j = mArrayNextIndices[j];
        }

    }

    SolverVariable pickPivotCandidate()
    {
        SolverVariable solvervariable;
        SolverVariable solvervariable1;
        int i;
        int j;
        solvervariable1 = null;
        solvervariable = null;
        j = mHead;
        i = 0;
_L2:
        float f;
        SolverVariable solvervariable2;
        SolverVariable solvervariable3;
        SolverVariable solvervariable4;
        if (j == -1 || i >= currentSize)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        float f1 = mArrayValues[j];
        if (f1 < 0.0F)
        {
            f = f1;
            if (f1 > -0.001F)
            {
                mArrayValues[j] = 0.0F;
                f = 0.0F;
            }
        } else
        {
            f = f1;
            if (f1 < 0.001F)
            {
                mArrayValues[j] = 0.0F;
                f = 0.0F;
            }
        }
        solvervariable3 = solvervariable1;
        solvervariable4 = solvervariable;
        if (f != 0.0F)
        {
            solvervariable2 = mCache.mIndexedVariables[mArrayIndices[j]];
            if (solvervariable2.mType != SolverVariable.Type.UNRESTRICTED)
            {
                break; /* Loop/switch isn't completed */
            }
            if (f < 0.0F)
            {
                return solvervariable2;
            }
            solvervariable3 = solvervariable1;
            solvervariable4 = solvervariable;
            if (solvervariable == null)
            {
                solvervariable4 = solvervariable2;
                solvervariable3 = solvervariable1;
            }
        }
_L4:
        j = mArrayNextIndices[j];
        i++;
        solvervariable1 = solvervariable3;
        solvervariable = solvervariable4;
        if (true) goto _L2; else goto _L1
_L1:
        solvervariable3 = solvervariable1;
        solvervariable4 = solvervariable;
        if (f >= 0.0F) goto _L4; else goto _L3
_L3:
        if (solvervariable1 == null) goto _L6; else goto _L5
_L5:
        solvervariable3 = solvervariable1;
        solvervariable4 = solvervariable;
        if (solvervariable2.strength >= solvervariable1.strength) goto _L4; else goto _L6
_L6:
        solvervariable3 = solvervariable2;
        solvervariable4 = solvervariable;
          goto _L4
        if (solvervariable != null)
        {
            return solvervariable;
        } else
        {
            return solvervariable1;
        }
    }

    public final void put(SolverVariable solvervariable, float f)
    {
        if (f != 0.0F) goto _L2; else goto _L1
_L1:
        remove(solvervariable);
_L4:
        return;
_L2:
        if (mHead != -1)
        {
            break; /* Loop/switch isn't completed */
        }
        mHead = 0;
        mArrayValues[mHead] = f;
        mArrayIndices[mHead] = solvervariable.id;
        mArrayNextIndices[mHead] = -1;
        currentSize = currentSize + 1;
        if (!mDidFillOnce)
        {
            mLast = mLast + 1;
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i;
        int l;
        i = mHead;
        int i1 = -1;
        for (int j = 0; i != -1 && j < currentSize; j++)
        {
            if (mArrayIndices[i] == solvervariable.id)
            {
                mArrayValues[i] = f;
                return;
            }
            if (mArrayIndices[i] < solvervariable.id)
            {
                i1 = i;
            }
            i = mArrayNextIndices[i];
        }

        i = mLast + 1;
        int k;
        if (mDidFillOnce)
        {
            if (mArrayIndices[mLast] == -1)
            {
                i = mLast;
            } else
            {
                i = mArrayIndices.length;
            }
        }
        k = i;
        if (i < mArrayIndices.length) goto _L6; else goto _L5
_L5:
        k = i;
        if (currentSize >= mArrayIndices.length) goto _L6; else goto _L7
_L7:
        l = 0;
_L8:
        k = i;
        if (l < mArrayIndices.length)
        {
            if (mArrayIndices[l] != -1)
            {
                break MISSING_BLOCK_LABEL_439;
            }
            k = l;
        }
_L6:
        i = k;
        if (k >= mArrayIndices.length)
        {
            i = mArrayIndices.length;
            ROW_SIZE = ROW_SIZE * 2;
            mDidFillOnce = false;
            mLast = i - 1;
            mArrayValues = Arrays.copyOf(mArrayValues, ROW_SIZE);
            mArrayIndices = Arrays.copyOf(mArrayIndices, ROW_SIZE);
            mArrayNextIndices = Arrays.copyOf(mArrayNextIndices, ROW_SIZE);
        }
        mArrayIndices[i] = solvervariable.id;
        mArrayValues[i] = f;
        if (i1 != -1)
        {
            mArrayNextIndices[i] = mArrayNextIndices[i1];
            mArrayNextIndices[i1] = i;
        } else
        {
            mArrayNextIndices[i] = mHead;
            mHead = i;
        }
        currentSize = currentSize + 1;
        if (!mDidFillOnce)
        {
            mLast = mLast + 1;
        }
        if (currentSize >= mArrayIndices.length)
        {
            mDidFillOnce = true;
            return;
        }
          goto _L4
        l++;
          goto _L8
    }

    public final float remove(SolverVariable solvervariable)
    {
        if (candidate == solvervariable)
        {
            candidate = null;
        }
        if (mHead != -1)
        {
            int i = mHead;
            int k = -1;
            int j = 0;
            while (i != -1 && j < currentSize) 
            {
                int l = mArrayIndices[i];
                if (l == solvervariable.id)
                {
                    if (i == mHead)
                    {
                        mHead = mArrayNextIndices[i];
                    } else
                    {
                        mArrayNextIndices[k] = mArrayNextIndices[i];
                    }
                    mCache.mIndexedVariables[l].removeClientEquation(mRow);
                    currentSize = currentSize - 1;
                    mArrayIndices[i] = -1;
                    if (mDidFillOnce)
                    {
                        mLast = i;
                    }
                    return mArrayValues[i];
                }
                k = i;
                i = mArrayNextIndices[i];
                j++;
            }
        }
        return 0.0F;
    }

    int sizeInBytes()
    {
        return 0 + mArrayIndices.length * 4 * 3 + 36;
    }

    public String toString()
    {
        String s = "";
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            s = (new StringBuilder()).append(s).append(" -> ").toString();
            s = (new StringBuilder()).append(s).append(mArrayValues[j]).append(" : ").toString();
            s = (new StringBuilder()).append(s).append(mCache.mIndexedVariables[mArrayIndices[j]]).toString();
            j = mArrayNextIndices[j];
        }

        return s;
    }

    void updateClientEquations(ArrayRow arrayrow)
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize; i++)
        {
            mCache.mIndexedVariables[mArrayIndices[j]].addClientEquation(arrayrow);
            j = mArrayNextIndices[j];
        }

    }

    void updateFromRow(ArrayRow arrayrow, ArrayRow arrayrow1)
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize;)
        {
            if (mArrayIndices[j] == arrayrow1.variable.id)
            {
                float f = mArrayValues[j];
                remove(arrayrow1.variable);
                ArrayLinkedVariables arraylinkedvariables = arrayrow1.variables;
                j = arraylinkedvariables.mHead;
                for (i = 0; j != -1 && i < arraylinkedvariables.currentSize; i++)
                {
                    add(mCache.mIndexedVariables[arraylinkedvariables.mArrayIndices[j]], arraylinkedvariables.mArrayValues[j] * f);
                    j = arraylinkedvariables.mArrayNextIndices[j];
                }

                arrayrow.constantValue = arrayrow.constantValue + arrayrow1.constantValue * f;
                arrayrow1.variable.removeClientEquation(arrayrow);
                j = mHead;
                i = 0;
            } else
            {
                j = mArrayNextIndices[j];
                i++;
            }
        }

    }

    void updateFromSystem(ArrayRow arrayrow, ArrayRow aarrayrow[])
    {
        int j = mHead;
        for (int i = 0; j != -1 && i < currentSize;)
        {
            Object obj = mCache.mIndexedVariables[mArrayIndices[j]];
            if (((SolverVariable) (obj)).definitionId != -1)
            {
                float f = mArrayValues[j];
                remove(((SolverVariable) (obj)));
                obj = aarrayrow[((SolverVariable) (obj)).definitionId];
                if (!((ArrayRow) (obj)).isSimpleDefinition)
                {
                    ArrayLinkedVariables arraylinkedvariables = ((ArrayRow) (obj)).variables;
                    j = arraylinkedvariables.mHead;
                    for (i = 0; j != -1 && i < arraylinkedvariables.currentSize; i++)
                    {
                        add(mCache.mIndexedVariables[arraylinkedvariables.mArrayIndices[j]], arraylinkedvariables.mArrayValues[j] * f);
                        j = arraylinkedvariables.mArrayNextIndices[j];
                    }

                }
                arrayrow.constantValue = arrayrow.constantValue + ((ArrayRow) (obj)).constantValue * f;
                ((ArrayRow) (obj)).variable.removeClientEquation(arrayrow);
                j = mHead;
                i = 0;
            } else
            {
                j = mArrayNextIndices[j];
                i++;
            }
        }

    }
}
