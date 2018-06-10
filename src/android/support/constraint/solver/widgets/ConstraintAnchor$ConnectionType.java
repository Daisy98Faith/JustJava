// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintAnchor

public static final class  extends Enum
{

    private static final STRICT $VALUES[];
    public static final STRICT RELAXED;
    public static final STRICT STRICT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$ConnectionType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        RELAXED = new <init>("RELAXED", 0);
        STRICT = new <init>("STRICT", 1);
        $VALUES = (new .VALUES[] {
            RELAXED, STRICT
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
