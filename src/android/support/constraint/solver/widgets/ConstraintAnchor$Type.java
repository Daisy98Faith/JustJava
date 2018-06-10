// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintAnchor

public static final class  extends Enum
{

    private static final CENTER_Y $VALUES[];
    public static final CENTER_Y BASELINE;
    public static final CENTER_Y BOTTOM;
    public static final CENTER_Y CENTER;
    public static final CENTER_Y CENTER_X;
    public static final CENTER_Y CENTER_Y;
    public static final CENTER_Y LEFT;
    public static final CENTER_Y NONE;
    public static final CENTER_Y RIGHT;
    public static final CENTER_Y TOP;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NONE = new <init>("NONE", 0);
        LEFT = new <init>("LEFT", 1);
        TOP = new <init>("TOP", 2);
        RIGHT = new <init>("RIGHT", 3);
        BOTTOM = new <init>("BOTTOM", 4);
        BASELINE = new <init>("BASELINE", 5);
        CENTER = new <init>("CENTER", 6);
        CENTER_X = new <init>("CENTER_X", 7);
        CENTER_Y = new <init>("CENTER_Y", 8);
        $VALUES = (new .VALUES[] {
            NONE, LEFT, TOP, RIGHT, BOTTOM, BASELINE, CENTER, CENTER_X, CENTER_Y
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
