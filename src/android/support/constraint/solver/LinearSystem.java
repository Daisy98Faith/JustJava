// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// Referenced classes of package android.support.constraint.solver:
//            Goal, SolverVariable, ArrayRow, Cache, 
//            ArrayLinkedVariables

public class LinearSystem
{

    private static final boolean DEBUG = false;
    private static int POOL_SIZE = 1000;
    private int TABLE_SIZE;
    private boolean mAlreadyTestedCandidates[];
    final Cache mCache = new Cache();
    private Goal mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    private int mNumRows;
    private SolverVariable mPoolVariables[];
    private int mPoolVariablesCount;
    private ArrayRow mRows[];
    private HashMap mVariables;
    int mVariablesID;
    private ArrayRow tempClientsCopy[];

    public LinearSystem()
    {
        mVariablesID = 0;
        mVariables = null;
        mGoal = new Goal();
        TABLE_SIZE = 32;
        mMaxColumns = TABLE_SIZE;
        mRows = null;
        mAlreadyTestedCandidates = new boolean[TABLE_SIZE];
        mNumColumns = 1;
        mNumRows = 0;
        mMaxRows = TABLE_SIZE;
        mPoolVariables = new SolverVariable[POOL_SIZE];
        mPoolVariablesCount = 0;
        tempClientsCopy = new ArrayRow[TABLE_SIZE];
        mRows = new ArrayRow[TABLE_SIZE];
        releaseRows();
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type)
    {
        Object obj = (SolverVariable)mCache.solverVariablePool.acquire();
        int i;
        if (obj == null)
        {
            type = new SolverVariable(type);
        } else
        {
            ((SolverVariable) (obj)).reset();
            ((SolverVariable) (obj)).setType(type);
            type = ((SolverVariable.Type) (obj));
        }
        if (mPoolVariablesCount >= POOL_SIZE)
        {
            POOL_SIZE *= 2;
            mPoolVariables = (SolverVariable[])Arrays.copyOf(mPoolVariables, POOL_SIZE);
        }
        obj = mPoolVariables;
        i = mPoolVariablesCount;
        mPoolVariablesCount = i + 1;
        obj[i] = type;
        return type;
    }

    private void addError(ArrayRow arrayrow)
    {
        arrayrow.addError(createErrorVariable(), createErrorVariable());
    }

    private void addSingleError(ArrayRow arrayrow, int i)
    {
        arrayrow.addSingleError(createErrorVariable(), i);
    }

    private void computeValues()
    {
        for (int i = 0; i < mNumRows; i++)
        {
            ArrayRow arrayrow = mRows[i];
            arrayrow.variable.computedValue = arrayrow.constantValue;
        }

    }

    public static ArrayRow createRowCentering(LinearSystem linearsystem, SolverVariable solvervariable, SolverVariable solvervariable1, int i, float f, SolverVariable solvervariable2, SolverVariable solvervariable3, int j, 
            boolean flag)
    {
        ArrayRow arrayrow = linearsystem.createRow();
        arrayrow.createRowCentering(solvervariable, solvervariable1, i, f, solvervariable2, solvervariable3, j);
        if (flag)
        {
            solvervariable = linearsystem.createErrorVariable();
            linearsystem = linearsystem.createErrorVariable();
            solvervariable.strength = 4;
            linearsystem.strength = 4;
            arrayrow.addError(solvervariable, linearsystem);
        }
        return arrayrow;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearsystem, SolverVariable solvervariable, SolverVariable solvervariable1, SolverVariable solvervariable2, float f, boolean flag)
    {
        ArrayRow arrayrow = linearsystem.createRow();
        if (flag)
        {
            linearsystem.addError(arrayrow);
        }
        return arrayrow.createRowDimensionPercent(solvervariable, solvervariable1, solvervariable2, f);
    }

    public static ArrayRow createRowEquals(LinearSystem linearsystem, SolverVariable solvervariable, SolverVariable solvervariable1, int i, boolean flag)
    {
        ArrayRow arrayrow = linearsystem.createRow();
        arrayrow.createRowEquals(solvervariable, solvervariable1, i);
        if (flag)
        {
            linearsystem.addSingleError(arrayrow, 1);
        }
        return arrayrow;
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearsystem, SolverVariable solvervariable, SolverVariable solvervariable1, int i, boolean flag)
    {
        SolverVariable solvervariable2 = linearsystem.createSlackVariable();
        ArrayRow arrayrow = linearsystem.createRow();
        arrayrow.createRowGreaterThan(solvervariable, solvervariable1, solvervariable2, i);
        if (flag)
        {
            linearsystem.addSingleError(arrayrow, (int)(-1F * arrayrow.variables.get(solvervariable2)));
        }
        return arrayrow;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearsystem, SolverVariable solvervariable, SolverVariable solvervariable1, int i, boolean flag)
    {
        SolverVariable solvervariable2 = linearsystem.createSlackVariable();
        ArrayRow arrayrow = linearsystem.createRow();
        arrayrow.createRowLowerThan(solvervariable, solvervariable1, solvervariable2, i);
        if (flag)
        {
            linearsystem.addSingleError(arrayrow, (int)(-1F * arrayrow.variables.get(solvervariable2)));
        }
        return arrayrow;
    }

    private SolverVariable createVariable(String s, SolverVariable.Type type)
    {
        if (mNumColumns + 1 >= mMaxColumns)
        {
            increaseTableSize();
        }
        type = acquireSolverVariable(type);
        type.setName(s);
        mVariablesID = mVariablesID + 1;
        mNumColumns = mNumColumns + 1;
        type.id = mVariablesID;
        if (mVariables == null)
        {
            mVariables = new HashMap();
        }
        mVariables.put(s, type);
        mCache.mIndexedVariables[mVariablesID] = type;
        return type;
    }

    private void displayRows()
    {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < mNumRows; i++)
        {
            s = (new StringBuilder()).append(s).append(mRows[i]).toString();
            s = (new StringBuilder()).append(s).append("\n").toString();
        }

        String s1 = s;
        if (mGoal.variables.size() != 0)
        {
            s1 = (new StringBuilder()).append(s).append(mGoal).append("\n").toString();
        }
        System.out.println(s1);
    }

    private void displaySolverVariables()
    {
        String s = (new StringBuilder()).append("Display Rows (").append(mNumRows).append("x").append(mNumColumns).append(") :\n\t | C | ").toString();
        for (int i = 1; i <= mNumColumns; i++)
        {
            SolverVariable solvervariable = mCache.mIndexedVariables[i];
            s = (new StringBuilder()).append(s).append(solvervariable).toString();
            s = (new StringBuilder()).append(s).append(" | ").toString();
        }

        s = (new StringBuilder()).append(s).append("\n").toString();
        System.out.println(s);
    }

    private int enforceBFS(Goal goal)
        throws Exception
    {
        int k;
        int l;
        int i1;
        l = 0;
        i1 = 0;
        k = 0;
_L3:
        int i;
        i = i1;
        if (k >= mNumRows)
        {
            break; /* Loop/switch isn't completed */
        }
          goto _L1
_L5:
        k++;
        if (true) goto _L3; else goto _L2
_L1:
        if (mRows[k].variable.mType == SolverVariable.Type.UNRESTRICTED || mRows[k].constantValue >= 0.0F) goto _L5; else goto _L4
_L4:
        i = 1;
_L2:
        k = l;
        if (!i) goto _L7; else goto _L6
_L6:
        boolean flag;
        flag = false;
        i = 0;
_L19:
        float f;
        int l2;
        k = i;
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        l2 = i + 1;
        f = 3.402823E+38F;
        l = 0;
        k = -1;
        i = -1;
        i1 = 0;
_L9:
        float f1;
        ArrayRow arrayrow;
        int k1;
        int l1;
        int j2;
        if (i1 >= mNumRows)
        {
            break MISSING_BLOCK_LABEL_396;
        }
        arrayrow = mRows[i1];
        if (arrayrow.variable.mType != SolverVariable.Type.UNRESTRICTED)
        {
            break; /* Loop/switch isn't completed */
        }
        j2 = l;
        l1 = k;
        k1 = i;
        f1 = f;
_L11:
        i1++;
        f = f1;
        i = k1;
        k = l1;
        l = j2;
        if (true) goto _L9; else goto _L8
_L8:
        f1 = f;
        k1 = i;
        l1 = k;
        j2 = l;
        if (arrayrow.constantValue >= 0.0F) goto _L11; else goto _L10
_L10:
        int j1 = 1;
_L15:
        f1 = f;
        k1 = i;
        l1 = k;
        j2 = l;
        if (j1 >= mNumColumns) goto _L11; else goto _L12
_L12:
        float f3;
        SolverVariable solvervariable;
        solvervariable = mCache.mIndexedVariables[j1];
        f3 = arrayrow.variables.get(solvervariable);
        if (f3 > 0.0F) goto _L14; else goto _L13
_L13:
        int k2;
        l1 = l;
        j2 = k;
        k2 = i;
        f1 = f;
_L17:
        j1++;
        f = f1;
        i = k2;
        k = j2;
        l = l1;
          goto _L15
_L14:
        l1 = 0;
        k1 = i;
        i = l1;
_L18:
        f1 = f;
        k2 = k1;
        j2 = k;
        l1 = l;
        if (i >= 6) goto _L17; else goto _L16
_L16:
        int i2;
label0:
        {
            float f2 = solvervariable.strengthVector[i] / f3;
            if (f2 >= f || i != l)
            {
                i2 = l;
                if (i <= l)
                {
                    break label0;
                }
            }
            f = f2;
            k = i1;
            k1 = j1;
            i2 = i;
        }
        i++;
        l = i2;
          goto _L18
          goto _L17
        if (k != -1)
        {
            ArrayRow arrayrow1 = mRows[k];
            arrayrow1.variable.definitionId = -1;
            arrayrow1.pivot(mCache.mIndexedVariables[i]);
            arrayrow1.variable.definitionId = k;
            for (i = 0; i < mNumRows; i++)
            {
                mRows[i].updateRowWithEquation(arrayrow1);
            }

            goal.updateFromSystem(this);
            i = l2;
        } else
        {
            flag = true;
            i = l2;
        }
        if (true) goto _L19; else goto _L7
_L7:
        for (int j = 0; j < mNumRows && (mRows[j].variable.mType == SolverVariable.Type.UNRESTRICTED || mRows[j].constantValue >= 0.0F); j++) { }
        return k;
    }

    private String getDisplaySize(int i)
    {
        int j = (i * 4) / 1024 / 1024;
        if (j > 0)
        {
            return (new StringBuilder()).append("").append(j).append(" Mb").toString();
        }
        j = (i * 4) / 1024;
        if (j > 0)
        {
            return (new StringBuilder()).append("").append(j).append(" Kb").toString();
        } else
        {
            return (new StringBuilder()).append("").append(i * 4).append(" bytes").toString();
        }
    }

    private void increaseTableSize()
    {
        TABLE_SIZE = TABLE_SIZE * 2;
        mRows = (ArrayRow[])Arrays.copyOf(mRows, TABLE_SIZE);
        mCache.mIndexedVariables = (SolverVariable[])Arrays.copyOf(mCache.mIndexedVariables, TABLE_SIZE);
        mAlreadyTestedCandidates = new boolean[TABLE_SIZE];
        mMaxColumns = TABLE_SIZE;
        mMaxRows = TABLE_SIZE;
        mGoal.variables.clear();
    }

    private int optimize(Goal goal)
    {
        boolean flag = false;
        int k = 0;
        for (int i = 0; i < mNumColumns; i++)
        {
            mAlreadyTestedCandidates[i] = false;
        }

        int i1 = 0;
        int j = ((flag) ? 1 : 0);
        do
        {
            if (j != 0)
            {
                break;
            }
            int k1 = k + 1;
            Object obj1 = goal.getPivotCandidate();
            int l = j;
            Object obj = obj1;
            k = i1;
            if (obj1 != null)
            {
                if (mAlreadyTestedCandidates[((SolverVariable) (obj1)).id])
                {
                    obj = null;
                    k = i1;
                    l = j;
                } else
                {
                    mAlreadyTestedCandidates[((SolverVariable) (obj1)).id] = true;
                    i1++;
                    l = j;
                    obj = obj1;
                    k = i1;
                    if (i1 >= mNumColumns)
                    {
                        l = 1;
                        obj = obj1;
                        k = i1;
                    }
                }
            }
            if (obj != null)
            {
                float f = 3.402823E+38F;
                i1 = -1;
                j = 0;
                while (j < mNumRows) 
                {
                    obj1 = mRows[j];
                    float f1;
                    int j1;
                    if (((ArrayRow) (obj1)).variable.mType == SolverVariable.Type.UNRESTRICTED)
                    {
                        j1 = i1;
                        f1 = f;
                    } else
                    {
                        f1 = f;
                        j1 = i1;
                        if (((ArrayRow) (obj1)).hasVariable(((SolverVariable) (obj))))
                        {
                            float f2 = ((ArrayRow) (obj1)).variables.get(((SolverVariable) (obj)));
                            f1 = f;
                            j1 = i1;
                            if (f2 < 0.0F)
                            {
                                f2 = -((ArrayRow) (obj1)).constantValue / f2;
                                f1 = f;
                                j1 = i1;
                                if (f2 < f)
                                {
                                    f1 = f2;
                                    j1 = j;
                                }
                            }
                        }
                    }
                    j++;
                    f = f1;
                    i1 = j1;
                }
                if (i1 > -1)
                {
                    ArrayRow arrayrow = mRows[i1];
                    arrayrow.variable.definitionId = -1;
                    arrayrow.pivot(((SolverVariable) (obj)));
                    arrayrow.variable.definitionId = i1;
                    for (j = 0; j < mNumRows; j++)
                    {
                        mRows[j].updateRowWithEquation(arrayrow);
                    }

                    goal.updateFromSystem(this);
                    try
                    {
                        enforceBFS(goal);
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                        j = l;
                        i1 = k;
                        k = k1;
                        continue;
                    }
                    j = l;
                    i1 = k;
                    k = k1;
                    continue;
                }
                j = 1;
                i1 = k;
                k = k1;
                continue;
            }
            j = 1;
            i1 = k;
            k = k1;
        } while (true);
        return k;
    }

    private void releaseRows()
    {
        for (int i = 0; i < mRows.length; i++)
        {
            ArrayRow arrayrow = mRows[i];
            if (arrayrow != null)
            {
                mCache.arrayRowPool.release(arrayrow);
            }
            mRows[i] = null;
        }

    }

    private void updateRowFromVariables(ArrayRow arrayrow)
    {
        if (mNumRows > 0)
        {
            arrayrow.variables.updateFromSystem(arrayrow, mRows);
            if (arrayrow.variables.currentSize == 0)
            {
                arrayrow.isSimpleDefinition = true;
            }
        }
    }

    public void addCentering(SolverVariable solvervariable, SolverVariable solvervariable1, int i, float f, SolverVariable solvervariable2, SolverVariable solvervariable3, int j, 
            int k)
    {
        ArrayRow arrayrow = createRow();
        arrayrow.createRowCentering(solvervariable, solvervariable1, i, f, solvervariable2, solvervariable3, j);
        solvervariable = createErrorVariable();
        solvervariable1 = createErrorVariable();
        solvervariable.strength = k;
        solvervariable1.strength = k;
        arrayrow.addError(solvervariable, solvervariable1);
        addConstraint(arrayrow);
    }

    public void addConstraint(ArrayRow arrayrow)
    {
        if (arrayrow != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mNumRows + 1 >= mMaxRows || mNumColumns + 1 >= mMaxColumns)
        {
            increaseTableSize();
        }
        if (arrayrow.isSimpleDefinition)
        {
            break; /* Loop/switch isn't completed */
        }
        updateRowFromVariables(arrayrow);
        arrayrow.ensurePositiveConstant();
        arrayrow.pickRowVariable();
        if (!arrayrow.hasKeyVariable()) goto _L1; else goto _L3
_L3:
        if (mRows[mNumRows] != null)
        {
            mCache.arrayRowPool.release(mRows[mNumRows]);
        }
        if (!arrayrow.isSimpleDefinition)
        {
            arrayrow.updateClientEquations();
        }
        mRows[mNumRows] = arrayrow;
        arrayrow.variable.definitionId = mNumRows;
        mNumRows = mNumRows + 1;
        int k = arrayrow.variable.mClientEquationsCount;
        if (k > 0)
        {
            for (; tempClientsCopy.length < k; tempClientsCopy = new ArrayRow[tempClientsCopy.length * 2]) { }
            ArrayRow aarrayrow[] = tempClientsCopy;
            for (int i = 0; i < k; i++)
            {
                aarrayrow[i] = arrayrow.variable.mClientEquations[i];
            }

            int j = 0;
            while (j < k) 
            {
                ArrayRow arrayrow1 = aarrayrow[j];
                if (arrayrow1 != arrayrow)
                {
                    arrayrow1.variables.updateFromRow(arrayrow1, arrayrow);
                    arrayrow1.updateClientEquations();
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public ArrayRow addEquality(SolverVariable solvervariable, SolverVariable solvervariable1, int i, int j)
    {
        ArrayRow arrayrow = createRow();
        arrayrow.createRowEquals(solvervariable, solvervariable1, i);
        solvervariable = createErrorVariable();
        solvervariable1 = createErrorVariable();
        solvervariable.strength = j;
        solvervariable1.strength = j;
        arrayrow.addError(solvervariable, solvervariable1);
        addConstraint(arrayrow);
        return arrayrow;
    }

    public void addEquality(SolverVariable solvervariable, int i)
    {
        int j = solvervariable.definitionId;
        if (solvervariable.definitionId != -1)
        {
            ArrayRow arrayrow = mRows[j];
            if (arrayrow.isSimpleDefinition)
            {
                arrayrow.constantValue = i;
                return;
            } else
            {
                ArrayRow arrayrow1 = createRow();
                arrayrow1.createRowEquals(solvervariable, i);
                addConstraint(arrayrow1);
                return;
            }
        } else
        {
            ArrayRow arrayrow2 = createRow();
            arrayrow2.createRowDefinition(solvervariable, i);
            addConstraint(arrayrow2);
            return;
        }
    }

    public void addGreaterThan(SolverVariable solvervariable, SolverVariable solvervariable1, int i, int j)
    {
        ArrayRow arrayrow = createRow();
        SolverVariable solvervariable2 = createSlackVariable();
        solvervariable2.strength = j;
        arrayrow.createRowGreaterThan(solvervariable, solvervariable1, solvervariable2, i);
        addConstraint(arrayrow);
    }

    public void addLowerThan(SolverVariable solvervariable, SolverVariable solvervariable1, int i, int j)
    {
        ArrayRow arrayrow = createRow();
        SolverVariable solvervariable2 = createSlackVariable();
        solvervariable2.strength = j;
        arrayrow.createRowLowerThan(solvervariable, solvervariable1, solvervariable2, i);
        addConstraint(arrayrow);
    }

    public SolverVariable createErrorVariable()
    {
        if (mNumColumns + 1 >= mMaxColumns)
        {
            increaseTableSize();
        }
        SolverVariable solvervariable = acquireSolverVariable(SolverVariable.Type.ERROR);
        mVariablesID = mVariablesID + 1;
        mNumColumns = mNumColumns + 1;
        solvervariable.id = mVariablesID;
        mCache.mIndexedVariables[mVariablesID] = solvervariable;
        return solvervariable;
    }

    public SolverVariable createObjectVariable(Object obj)
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        SolverVariable solvervariable1 = null;
_L4:
        return solvervariable1;
_L2:
        if (mNumColumns + 1 >= mMaxColumns)
        {
            increaseTableSize();
        }
        solvervariable1 = null;
        if (!(obj instanceof ConstraintAnchor)) goto _L4; else goto _L3
_L3:
        SolverVariable solvervariable;
        solvervariable1 = ((ConstraintAnchor)obj).getSolverVariable();
        solvervariable = solvervariable1;
        if (solvervariable1 == null)
        {
            ((ConstraintAnchor)obj).resetSolverVariable(mCache);
            solvervariable = ((ConstraintAnchor)obj).getSolverVariable();
        }
        if (solvervariable.id == -1 || solvervariable.id > mVariablesID)
        {
            break; /* Loop/switch isn't completed */
        }
        solvervariable1 = solvervariable;
        if (mCache.mIndexedVariables[solvervariable.id] != null) goto _L4; else goto _L5
_L5:
        if (solvervariable.id != -1)
        {
            solvervariable.reset();
        }
        mVariablesID = mVariablesID + 1;
        mNumColumns = mNumColumns + 1;
        solvervariable.id = mVariablesID;
        solvervariable.mType = SolverVariable.Type.UNRESTRICTED;
        mCache.mIndexedVariables[mVariablesID] = solvervariable;
        return solvervariable;
    }

    public ArrayRow createRow()
    {
        ArrayRow arrayrow = (ArrayRow)mCache.arrayRowPool.acquire();
        if (arrayrow == null)
        {
            return new ArrayRow(mCache);
        } else
        {
            arrayrow.reset();
            return arrayrow;
        }
    }

    public SolverVariable createSlackVariable()
    {
        if (mNumColumns + 1 >= mMaxColumns)
        {
            increaseTableSize();
        }
        SolverVariable solvervariable = acquireSolverVariable(SolverVariable.Type.SLACK);
        mVariablesID = mVariablesID + 1;
        mNumColumns = mNumColumns + 1;
        solvervariable.id = mVariablesID;
        mCache.mIndexedVariables[mVariablesID] = solvervariable;
        return solvervariable;
    }

    void displayReadableRows()
    {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < mNumRows; i++)
        {
            s = (new StringBuilder()).append(s).append(mRows[i].toReadableString()).toString();
            s = (new StringBuilder()).append(s).append("\n").toString();
        }

        String s1 = s;
        if (mGoal != null)
        {
            s1 = (new StringBuilder()).append(s).append(mGoal).append("\n").toString();
        }
        System.out.println(s1);
    }

    void displaySystemInformations()
    {
        int i = 0;
        for (int j = 0; j < TABLE_SIZE;)
        {
            int l = i;
            if (mRows[j] != null)
            {
                l = i + mRows[j].sizeInBytes();
            }
            j++;
            i = l;
        }

        int i1 = 0;
        for (int k = 0; k < mNumRows;)
        {
            int j1 = i1;
            if (mRows[k] != null)
            {
                j1 = i1 + mRows[k].sizeInBytes();
            }
            k++;
            i1 = j1;
        }

        System.out.println((new StringBuilder()).append("Linear System -> Table size: ").append(TABLE_SIZE).append(" (").append(getDisplaySize(TABLE_SIZE * TABLE_SIZE)).append(") -- row sizes: ").append(getDisplaySize(i)).append(", actual size: ").append(getDisplaySize(i1)).append(" rows: ").append(mNumRows).append("/").append(mMaxRows).append(" cols: ").append(mNumColumns).append("/").append(mMaxColumns).append(" ").append(0).append(" occupied cells, ").append(getDisplaySize(0)).toString());
    }

    public void displayVariablesReadableRows()
    {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < mNumRows;)
        {
            String s1 = s;
            if (mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED)
            {
                s = (new StringBuilder()).append(s).append(mRows[i].toReadableString()).toString();
                s1 = (new StringBuilder()).append(s).append("\n").toString();
            }
            i++;
            s = s1;
        }

        String s2 = s;
        if (mGoal.variables.size() != 0)
        {
            s2 = (new StringBuilder()).append(s).append(mGoal).append("\n").toString();
        }
        System.out.println(s2);
    }

    public Cache getCache()
    {
        return mCache;
    }

    Goal getGoal()
    {
        return mGoal;
    }

    public int getMemoryUsed()
    {
        int j = 0;
        for (int i = 0; i < mNumRows;)
        {
            int k = j;
            if (mRows[i] != null)
            {
                k = j + mRows[i].sizeInBytes();
            }
            i++;
            j = k;
        }

        return j;
    }

    public int getNumEquations()
    {
        return mNumRows;
    }

    public int getNumVariables()
    {
        return mVariablesID;
    }

    public int getObjectVariableValue(Object obj)
    {
        obj = ((ConstraintAnchor)obj).getSolverVariable();
        if (obj != null)
        {
            return (int)(((SolverVariable) (obj)).computedValue + 0.5F);
        } else
        {
            return 0;
        }
    }

    ArrayRow getRow(int i)
    {
        return mRows[i];
    }

    float getValueFor(String s)
    {
        s = getVariable(s, SolverVariable.Type.UNRESTRICTED);
        if (s == null)
        {
            return 0.0F;
        } else
        {
            return ((SolverVariable) (s)).computedValue;
        }
    }

    SolverVariable getVariable(String s, SolverVariable.Type type)
    {
        if (mVariables == null)
        {
            mVariables = new HashMap();
        }
        SolverVariable solvervariable1 = (SolverVariable)mVariables.get(s);
        SolverVariable solvervariable = solvervariable1;
        if (solvervariable1 == null)
        {
            solvervariable = createVariable(s, type);
        }
        return solvervariable;
    }

    public void minimize()
        throws Exception
    {
        minimizeGoal(mGoal);
    }

    void minimizeGoal(Goal goal)
        throws Exception
    {
        goal.updateFromSystem(this);
        enforceBFS(goal);
        optimize(goal);
        computeValues();
    }

    void rebuildGoalFromErrors()
    {
        mGoal.updateFromSystem(this);
    }

    public void reset()
    {
        for (int i = 0; i < mCache.mIndexedVariables.length; i++)
        {
            SolverVariable solvervariable = mCache.mIndexedVariables[i];
            if (solvervariable != null)
            {
                solvervariable.reset();
            }
        }

        mCache.solverVariablePool.releaseAll(mPoolVariables, mPoolVariablesCount);
        mPoolVariablesCount = 0;
        Arrays.fill(mCache.mIndexedVariables, null);
        if (mVariables != null)
        {
            mVariables.clear();
        }
        mVariablesID = 0;
        mGoal.variables.clear();
        mNumColumns = 1;
        for (int j = 0; j < mNumRows; j++)
        {
            mRows[j].used = false;
        }

        releaseRows();
        mNumRows = 0;
    }

}
