// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget

public static final class Q extends Enum
{

    private static final RIGHT $VALUES[];
    public static final RIGHT BEGIN;
    public static final RIGHT BOTTOM;
    public static final RIGHT END;
    public static final RIGHT LEFT;
    public static final RIGHT MIDDLE;
    public static final RIGHT RIGHT;
    public static final RIGHT TOP;
    public static final RIGHT VERTICAL_MIDDLE;

    public static Q valueOf(String s)
    {
        return (Q)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintWidget$ContentAlignment, s);
    }

    public static Q[] values()
    {
        return (Q[])$VALUES.clone();
    }

    static 
    {
        BEGIN = new <init>("BEGIN", 0);
        MIDDLE = new <init>("MIDDLE", 1);
        END = new <init>("END", 2);
        TOP = new <init>("TOP", 3);
        VERTICAL_MIDDLE = new <init>("VERTICAL_MIDDLE", 4);
        BOTTOM = new <init>("BOTTOM", 5);
        LEFT = new <init>("LEFT", 6);
        RIGHT = new <init>("RIGHT", 7);
        $VALUES = (new .VALUES[] {
            BEGIN, MIDDLE, END, TOP, VERTICAL_MIDDLE, BOTTOM, LEFT, RIGHT
        });
    }

    private Q(String s, int i)
    {
        super(s, i);
    }
}
