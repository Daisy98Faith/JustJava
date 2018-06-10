// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;

import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver:
//            LinearSystem, Cache, SolverVariable, ArrayRow, 
//            ArrayLinkedVariables

public class Goal
{

    ArrayList variables;

    public Goal()
    {
        variables = new ArrayList();
    }

    private void initFromSystemErrors(LinearSystem linearsystem)
    {
        variables.clear();
        int i = 1;
        while (i < linearsystem.mNumColumns) 
        {
            SolverVariable solvervariable = linearsystem.mCache.mIndexedVariables[i];
            for (int j = 0; j < 6; j++)
            {
                solvervariable.strengthVector[j] = 0.0F;
            }

            solvervariable.strengthVector[solvervariable.strength] = 1.0F;
            if (solvervariable.mType == SolverVariable.Type.ERROR)
            {
                variables.add(solvervariable);
            }
            i++;
        }
    }

    SolverVariable getPivotCandidate()
    {
        int i1 = variables.size();
        SolverVariable solvervariable1 = null;
        int j = 0;
        for (int l = 0; l < i1; l++)
        {
            SolverVariable solvervariable2 = (SolverVariable)variables.get(l);
            for (int i = 5; i >= 0; i--)
            {
                float f = solvervariable2.strengthVector[i];
                SolverVariable solvervariable = solvervariable1;
                int k = j;
                if (solvervariable1 == null)
                {
                    solvervariable = solvervariable1;
                    k = j;
                    if (f < 0.0F)
                    {
                        solvervariable = solvervariable1;
                        k = j;
                        if (i >= j)
                        {
                            k = i;
                            solvervariable = solvervariable2;
                        }
                    }
                }
                solvervariable1 = solvervariable;
                j = k;
                if (f <= 0.0F)
                {
                    continue;
                }
                solvervariable1 = solvervariable;
                j = k;
                if (i > k)
                {
                    j = i;
                    solvervariable1 = null;
                }
            }

        }

        return solvervariable1;
    }

    public String toString()
    {
        String s = "Goal: ";
        int j = variables.size();
        for (int i = 0; i < j; i++)
        {
            SolverVariable solvervariable = (SolverVariable)variables.get(i);
            s = (new StringBuilder()).append(s).append(solvervariable.strengthsToString()).toString();
        }

        return s;
    }

    void updateFromSystem(LinearSystem linearsystem)
    {
        initFromSystemErrors(linearsystem);
        int l = variables.size();
        for (int i = 0; i < l; i++)
        {
            SolverVariable solvervariable = (SolverVariable)variables.get(i);
            if (solvervariable.definitionId == -1)
            {
                continue;
            }
            ArrayLinkedVariables arraylinkedvariables = linearsystem.getRow(solvervariable.definitionId).variables;
            int i1 = arraylinkedvariables.currentSize;
            int j = 0;
            while (j < i1) 
            {
                SolverVariable solvervariable1 = arraylinkedvariables.getVariable(j);
                if (solvervariable1 != null)
                {
                    float f = arraylinkedvariables.getVariableValue(j);
                    for (int k = 0; k < 6; k++)
                    {
                        float af[] = solvervariable1.strengthVector;
                        af[k] = af[k] + solvervariable.strengthVector[k] * f;
                    }

                    if (!variables.contains(solvervariable1))
                    {
                        variables.add(solvervariable1);
                    }
                }
                j++;
            }
            solvervariable.clearStrengths();
        }

    }
}
