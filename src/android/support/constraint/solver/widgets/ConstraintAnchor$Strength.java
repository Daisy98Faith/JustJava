// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintAnchor

public static final class I extends Enum
{

    private static final WEAK $VALUES[];
    public static final WEAK NONE;
    public static final WEAK STRONG;
    public static final WEAK WEAK;

    public static I valueOf(String s)
    {
        return (I)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$Strength, s);
    }

    public static I[] values()
    {
        return (I[])$VALUES.clone();
    }

    static 
    {
        NONE = new <init>("NONE", 0);
        STRONG = new <init>("STRONG", 1);
        WEAK = new <init>("WEAK", 2);
        $VALUES = (new .VALUES[] {
            NONE, STRONG, WEAK
        });
    }

    private I(String s, int i)
    {
        super(s, i);
    }
}
