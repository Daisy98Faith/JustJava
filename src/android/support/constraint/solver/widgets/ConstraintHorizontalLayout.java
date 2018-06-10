// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidgetContainer, ConstraintWidget

public class ConstraintHorizontalLayout extends ConstraintWidgetContainer
{
    public static final class ContentAlignment extends Enum
    {

        private static final ContentAlignment $VALUES[];
        public static final ContentAlignment BEGIN;
        public static final ContentAlignment BOTTOM;
        public static final ContentAlignment END;
        public static final ContentAlignment LEFT;
        public static final ContentAlignment MIDDLE;
        public static final ContentAlignment RIGHT;
        public static final ContentAlignment TOP;
        public static final ContentAlignment VERTICAL_MIDDLE;

        public static ContentAlignment valueOf(String s)
        {
            return (ContentAlignment)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintHorizontalLayout$ContentAlignment, s);
        }

        public static ContentAlignment[] values()
        {
            return (ContentAlignment[])$VALUES.clone();
        }

        static 
        {
            BEGIN = new ContentAlignment("BEGIN", 0);
            MIDDLE = new ContentAlignment("MIDDLE", 1);
            END = new ContentAlignment("END", 2);
            TOP = new ContentAlignment("TOP", 3);
            VERTICAL_MIDDLE = new ContentAlignment("VERTICAL_MIDDLE", 4);
            BOTTOM = new ContentAlignment("BOTTOM", 5);
            LEFT = new ContentAlignment("LEFT", 6);
            RIGHT = new ContentAlignment("RIGHT", 7);
            $VALUES = (new ContentAlignment[] {
                BEGIN, MIDDLE, END, TOP, VERTICAL_MIDDLE, BOTTOM, LEFT, RIGHT
            });
        }

        private ContentAlignment(String s, int i)
        {
            super(s, i);
        }
    }


    private ContentAlignment mAlignment;

    public ConstraintHorizontalLayout()
    {
        mAlignment = ContentAlignment.MIDDLE;
    }

    public ConstraintHorizontalLayout(int i, int j)
    {
        super(i, j);
        mAlignment = ContentAlignment.MIDDLE;
    }

    public ConstraintHorizontalLayout(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        mAlignment = ContentAlignment.MIDDLE;
    }

    public void addToSolver(LinearSystem linearsystem, int i)
    {
        if (mChildren.size() != 0)
        {
            Object obj = this;
            int j = 0;
            int k = mChildren.size();
            while (j < k) 
            {
                ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(j);
                if (obj != this)
                {
                    constraintwidget.connect(ConstraintAnchor.Type.LEFT, ((ConstraintWidget) (obj)), ConstraintAnchor.Type.RIGHT);
                    ((ConstraintWidget) (obj)).connect(ConstraintAnchor.Type.RIGHT, constraintwidget, ConstraintAnchor.Type.LEFT);
                } else
                {
                    ConstraintAnchor.Strength strength = ConstraintAnchor.Strength.STRONG;
                    if (mAlignment == ContentAlignment.END)
                    {
                        strength = ConstraintAnchor.Strength.WEAK;
                    }
                    constraintwidget.connect(ConstraintAnchor.Type.LEFT, ((ConstraintWidget) (obj)), ConstraintAnchor.Type.LEFT, 0, strength);
                }
                constraintwidget.connect(ConstraintAnchor.Type.TOP, this, ConstraintAnchor.Type.TOP);
                constraintwidget.connect(ConstraintAnchor.Type.BOTTOM, this, ConstraintAnchor.Type.BOTTOM);
                obj = constraintwidget;
                j++;
            }
            if (obj != this)
            {
                ConstraintAnchor.Strength strength1 = ConstraintAnchor.Strength.STRONG;
                if (mAlignment == ContentAlignment.BEGIN)
                {
                    strength1 = ConstraintAnchor.Strength.WEAK;
                }
                ((ConstraintWidget) (obj)).connect(ConstraintAnchor.Type.RIGHT, this, ConstraintAnchor.Type.RIGHT, 0, strength1);
            }
        }
        super.addToSolver(linearsystem, i);
    }
}
