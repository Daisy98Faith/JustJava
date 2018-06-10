// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;


// Referenced classes of package android.support.constraint.solver:
//            SolverVariable

public static final class  extends Enum
{

    private static final UNKNOWN $VALUES[];
    public static final UNKNOWN CONSTANT;
    public static final UNKNOWN ERROR;
    public static final UNKNOWN SLACK;
    public static final UNKNOWN UNKNOWN;
    public static final UNKNOWN UNRESTRICTED;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/support/constraint/solver/SolverVariable$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNRESTRICTED = new <init>("UNRESTRICTED", 0);
        CONSTANT = new <init>("CONSTANT", 1);
        SLACK = new <init>("SLACK", 2);
        ERROR = new <init>("ERROR", 3);
        UNKNOWN = new <init>("UNKNOWN", 4);
        $VALUES = (new .VALUES[] {
            UNRESTRICTED, CONSTANT, SLACK, ERROR, UNKNOWN
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
