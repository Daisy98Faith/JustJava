// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;


// Referenced classes of package android.support.constraint.solver:
//            SolverVariable

public class Cache
{

    Pools.Pool arrayRowPool;
    SolverVariable mIndexedVariables[];
    Pools.Pool solverVariablePool;

    public Cache()
    {
        arrayRowPool = new Pools.SimplePool(256);
        solverVariablePool = new Pools.SimplePool(256);
        mIndexedVariables = new SolverVariable[32];
    }
}
