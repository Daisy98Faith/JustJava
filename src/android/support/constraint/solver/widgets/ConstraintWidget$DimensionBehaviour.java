// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget

public static final class  extends Enum
{

    private static final MATCH_PARENT $VALUES[];
    public static final MATCH_PARENT FIXED;
    public static final MATCH_PARENT MATCH_CONSTRAINT;
    public static final MATCH_PARENT MATCH_PARENT;
    public static final MATCH_PARENT WRAP_CONTENT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        FIXED = new <init>("FIXED", 0);
        WRAP_CONTENT = new <init>("WRAP_CONTENT", 1);
        MATCH_CONSTRAINT = new <init>("MATCH_CONSTRAINT", 2);
        MATCH_PARENT = new <init>("MATCH_PARENT", 3);
        $VALUES = (new .VALUES[] {
            FIXED, WRAP_CONTENT, MATCH_CONSTRAINT, MATCH_PARENT
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
