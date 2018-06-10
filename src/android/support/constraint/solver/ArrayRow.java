// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;


// Referenced classes of package android.support.constraint.solver:
//            ArrayLinkedVariables, SolverVariable, Cache

public class ArrayRow
{

    private static final boolean DEBUG = false;
    float constantValue;
    boolean isSimpleDefinition;
    boolean used;
    SolverVariable variable;
    final ArrayLinkedVariables variables;

    public ArrayRow(Cache cache)
    {
        variable = null;
        constantValue = 0.0F;
        used = false;
        isSimpleDefinition = false;
        variables = new ArrayLinkedVariables(this, cache);
    }

    public ArrayRow addError(SolverVariable solvervariable, SolverVariable solvervariable1)
    {
        variables.put(solvervariable, 1.0F);
        variables.put(solvervariable1, -1F);
        return this;
    }

    ArrayRow addSingleError(SolverVariable solvervariable, int i)
    {
        variables.put(solvervariable, i);
        return this;
    }

    ArrayRow createRowCentering(SolverVariable solvervariable, SolverVariable solvervariable1, int i, float f, SolverVariable solvervariable2, SolverVariable solvervariable3, int j)
    {
        if (solvervariable1 != solvervariable2) goto _L2; else goto _L1
_L1:
        variables.put(solvervariable, 1.0F);
        variables.put(solvervariable3, 1.0F);
        variables.put(solvervariable1, -2F);
_L4:
        return this;
_L2:
        if (f != 0.5F)
        {
            break; /* Loop/switch isn't completed */
        }
        variables.put(solvervariable, 1.0F);
        variables.put(solvervariable1, -1F);
        variables.put(solvervariable2, -1F);
        variables.put(solvervariable3, 1.0F);
        if (i > 0 || j > 0)
        {
            constantValue = -i + j;
            return this;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (f <= 0.0F)
        {
            variables.put(solvervariable, -1F);
            variables.put(solvervariable1, 1.0F);
            constantValue = i;
            return this;
        }
        if (f >= 1.0F)
        {
            variables.put(solvervariable2, -1F);
            variables.put(solvervariable3, 1.0F);
            constantValue = j;
            return this;
        }
        variables.put(solvervariable, (1.0F - f) * 1.0F);
        variables.put(solvervariable1, (1.0F - f) * -1F);
        variables.put(solvervariable2, -1F * f);
        variables.put(solvervariable3, 1.0F * f);
        if (i > 0 || j > 0)
        {
            constantValue = (float)(-i) * (1.0F - f) + (float)j * f;
            return this;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    ArrayRow createRowDefinition(SolverVariable solvervariable, int i)
    {
        variable = solvervariable;
        solvervariable.computedValue = i;
        constantValue = i;
        isSimpleDefinition = true;
        return this;
    }

    ArrayRow createRowDimensionPercent(SolverVariable solvervariable, SolverVariable solvervariable1, SolverVariable solvervariable2, float f)
    {
        variables.put(solvervariable, -1F);
        variables.put(solvervariable1, 1.0F - f);
        variables.put(solvervariable2, f);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solvervariable, SolverVariable solvervariable1, SolverVariable solvervariable2, SolverVariable solvervariable3, float f)
    {
        variables.put(solvervariable, -1F);
        variables.put(solvervariable1, 1.0F);
        variables.put(solvervariable2, f);
        variables.put(solvervariable3, -f);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f, float f1, float f2, SolverVariable solvervariable, int i, SolverVariable solvervariable1, int j, 
            SolverVariable solvervariable2, int k, SolverVariable solvervariable3, int l)
    {
        if (f1 == 0.0F || f == f2)
        {
            constantValue = (-i - j) + k + l;
            variables.put(solvervariable, 1.0F);
            variables.put(solvervariable1, -1F);
            variables.put(solvervariable3, 1.0F);
            variables.put(solvervariable2, -1F);
            return this;
        } else
        {
            f = f / f1 / (f2 / f1);
            constantValue = (float)(-i - j) + (float)k * f + (float)l * f;
            variables.put(solvervariable, 1.0F);
            variables.put(solvervariable1, -1F);
            variables.put(solvervariable3, f);
            variables.put(solvervariable2, -f);
            return this;
        }
    }

    public ArrayRow createRowEquals(SolverVariable solvervariable, int i)
    {
        if (i < 0)
        {
            constantValue = i * -1;
            variables.put(solvervariable, 1.0F);
            return this;
        } else
        {
            constantValue = i;
            variables.put(solvervariable, -1F);
            return this;
        }
    }

    public ArrayRow createRowEquals(SolverVariable solvervariable, SolverVariable solvervariable1, int i)
    {
        int j = 0;
        int k = 0;
        if (i != 0)
        {
            j = i;
            i = k;
            k = j;
            if (j < 0)
            {
                k = j * -1;
                i = 1;
            }
            constantValue = k;
            j = i;
        }
        if (j == 0)
        {
            variables.put(solvervariable, -1F);
            variables.put(solvervariable1, 1.0F);
            return this;
        } else
        {
            variables.put(solvervariable, 1.0F);
            variables.put(solvervariable1, -1F);
            return this;
        }
    }

    public ArrayRow createRowGreaterThan(SolverVariable solvervariable, SolverVariable solvervariable1, SolverVariable solvervariable2, int i)
    {
        int j = 0;
        int k = 0;
        if (i != 0)
        {
            j = i;
            i = k;
            k = j;
            if (j < 0)
            {
                k = j * -1;
                i = 1;
            }
            constantValue = k;
            j = i;
        }
        if (j == 0)
        {
            variables.put(solvervariable, -1F);
            variables.put(solvervariable1, 1.0F);
            variables.put(solvervariable2, 1.0F);
            return this;
        } else
        {
            variables.put(solvervariable, 1.0F);
            variables.put(solvervariable1, -1F);
            variables.put(solvervariable2, -1F);
            return this;
        }
    }

    public ArrayRow createRowLowerThan(SolverVariable solvervariable, SolverVariable solvervariable1, SolverVariable solvervariable2, int i)
    {
        int j = 0;
        int k = 0;
        if (i != 0)
        {
            j = i;
            i = k;
            k = j;
            if (j < 0)
            {
                k = j * -1;
                i = 1;
            }
            constantValue = k;
            j = i;
        }
        if (j == 0)
        {
            variables.put(solvervariable, -1F);
            variables.put(solvervariable1, 1.0F);
            variables.put(solvervariable2, -1F);
            return this;
        } else
        {
            variables.put(solvervariable, 1.0F);
            variables.put(solvervariable1, -1F);
            variables.put(solvervariable2, 1.0F);
            return this;
        }
    }

    void ensurePositiveConstant()
    {
        if (constantValue < 0.0F)
        {
            constantValue = constantValue * -1F;
            variables.invert();
        }
    }

    boolean hasAtLeastOnePositiveVariable()
    {
        return variables.hasAtLeastOnePositiveVariable();
    }

    boolean hasKeyVariable()
    {
        return variable != null && (variable.mType == SolverVariable.Type.UNRESTRICTED || constantValue >= 0.0F);
    }

    boolean hasVariable(SolverVariable solvervariable)
    {
        return variables.containsKey(solvervariable);
    }

    void pickRowVariable()
    {
        SolverVariable solvervariable = variables.pickPivotCandidate();
        if (solvervariable != null)
        {
            pivot(solvervariable);
        }
        if (variables.currentSize == 0)
        {
            isSimpleDefinition = true;
        }
    }

    void pivot(SolverVariable solvervariable)
    {
        if (variable != null)
        {
            variables.put(variable, -1F);
            variable = null;
        }
        float f = variables.remove(solvervariable) * -1F;
        variable = solvervariable;
        if (f == 1.0F)
        {
            return;
        } else
        {
            constantValue = constantValue / f;
            variables.divideByAmount(f);
            return;
        }
    }

    public void reset()
    {
        variable = null;
        variables.clear();
        constantValue = 0.0F;
        isSimpleDefinition = false;
    }

    int sizeInBytes()
    {
        int i = 0;
        if (variable != null)
        {
            i = 0 + 4;
        }
        return i + 4 + 4 + variables.sizeInBytes();
    }

    String toReadableString()
    {
        String s;
        Object obj;
        boolean flag;
        int i;
        int j;
        if (variable == null)
        {
            s = (new StringBuilder()).append("").append("0").toString();
        } else
        {
            s = (new StringBuilder()).append("").append(variable).toString();
        }
        obj = (new StringBuilder()).append(s).append(" = ").toString();
        flag = false;
        s = ((String) (obj));
        if (constantValue != 0.0F)
        {
            s = (new StringBuilder()).append(((String) (obj))).append(constantValue).toString();
            flag = true;
        }
        j = variables.currentSize;
        i = 0;
        while (i < j) 
        {
            obj = variables.getVariable(i);
            if (obj != null)
            {
                float f1 = variables.getVariableValue(i);
                String s1 = ((SolverVariable) (obj)).toString();
                float f;
                if (!flag)
                {
                    f = f1;
                    obj = s;
                    if (f1 < 0.0F)
                    {
                        obj = (new StringBuilder()).append(s).append("- ").toString();
                        f = f1 * -1F;
                    }
                } else
                if (f1 > 0.0F)
                {
                    obj = (new StringBuilder()).append(s).append(" + ").toString();
                    f = f1;
                } else
                {
                    obj = (new StringBuilder()).append(s).append(" - ").toString();
                    f = f1 * -1F;
                }
                if (f == 1.0F)
                {
                    s = (new StringBuilder()).append(((String) (obj))).append(s1).toString();
                } else
                {
                    s = (new StringBuilder()).append(((String) (obj))).append(f).append(" ").append(s1).toString();
                }
                flag = true;
            }
            i++;
        }
        obj = s;
        if (!flag)
        {
            obj = (new StringBuilder()).append(s).append("0.0").toString();
        }
        return ((String) (obj));
    }

    public String toString()
    {
        return toReadableString();
    }

    void updateClientEquations()
    {
        variables.updateClientEquations(this);
    }

    boolean updateRowWithEquation(ArrayRow arrayrow)
    {
        variables.updateFromRow(this, arrayrow);
        return true;
    }
}
