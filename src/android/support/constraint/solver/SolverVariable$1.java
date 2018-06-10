// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;


// Referenced classes of package android.support.constraint.solver:
//            SolverVariable

static class pe
{

    static final int $SwitchMap$android$support$constraint$solver$SolverVariable$Type[];

    static 
    {
        $SwitchMap$android$support$constraint$solver$SolverVariable$Type = new int[pe.values().length];
        try
        {
            $SwitchMap$android$support$constraint$solver$SolverVariable$Type[pe.UNRESTRICTED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$android$support$constraint$solver$SolverVariable$Type[pe.CONSTANT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$android$support$constraint$solver$SolverVariable$Type[pe.SLACK.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$android$support$constraint$solver$SolverVariable$Type[pe.ERROR.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror)
        {
            return;
        }
    }
}
