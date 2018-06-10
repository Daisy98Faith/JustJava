// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintAnchor, ConstraintWidgetContainer, WidgetContainer

public class ConstraintWidget
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
            return (ContentAlignment)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintWidget$ContentAlignment, s);
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

    public static final class DimensionBehaviour extends Enum
    {

        private static final DimensionBehaviour $VALUES[];
        public static final DimensionBehaviour FIXED;
        public static final DimensionBehaviour MATCH_CONSTRAINT;
        public static final DimensionBehaviour MATCH_PARENT;
        public static final DimensionBehaviour WRAP_CONTENT;

        public static DimensionBehaviour valueOf(String s)
        {
            return (DimensionBehaviour)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour, s);
        }

        public static DimensionBehaviour[] values()
        {
            return (DimensionBehaviour[])$VALUES.clone();
        }

        static 
        {
            FIXED = new DimensionBehaviour("FIXED", 0);
            WRAP_CONTENT = new DimensionBehaviour("WRAP_CONTENT", 1);
            MATCH_CONSTRAINT = new DimensionBehaviour("MATCH_CONSTRAINT", 2);
            MATCH_PARENT = new DimensionBehaviour("MATCH_PARENT", 3);
            $VALUES = (new DimensionBehaviour[] {
                FIXED, WRAP_CONTENT, MATCH_CONSTRAINT, MATCH_PARENT
            });
        }

        private DimensionBehaviour(String s, int i)
        {
            super(s, i);
        }
    }


    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0F;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    protected ArrayList mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    DimensionBehaviour mHorizontalDimensionBehaviour;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    float mHorizontalWeight;
    boolean mHorizontalWrapVisited;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    private int mSolverBottom;
    private int mSolverLeft;
    private int mSolverRight;
    private int mSolverTop;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    DimensionBehaviour mVerticalDimensionBehaviour;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    float mVerticalWeight;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public ConstraintWidget()
    {
        mHorizontalResolution = -1;
        mVerticalResolution = -1;
        mMatchConstraintDefaultWidth = 0;
        mMatchConstraintDefaultHeight = 0;
        mMatchConstraintMinWidth = 0;
        mMatchConstraintMaxWidth = 0;
        mMatchConstraintMinHeight = 0;
        mMatchConstraintMaxHeight = 0;
        mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        mAnchors = new ArrayList();
        mParent = null;
        mWidth = 0;
        mHeight = 0;
        mDimensionRatio = 0.0F;
        mDimensionRatioSide = -1;
        mSolverLeft = 0;
        mSolverTop = 0;
        mSolverRight = 0;
        mSolverBottom = 0;
        mX = 0;
        mY = 0;
        mDrawX = 0;
        mDrawY = 0;
        mDrawWidth = 0;
        mDrawHeight = 0;
        mOffsetX = 0;
        mOffsetY = 0;
        mBaselineDistance = 0;
        mHorizontalBiasPercent = DEFAULT_BIAS;
        mVerticalBiasPercent = DEFAULT_BIAS;
        mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        mContainerItemSkip = 0;
        mVisibility = 0;
        mDebugName = null;
        mType = null;
        mHorizontalChainStyle = 0;
        mVerticalChainStyle = 0;
        mHorizontalWeight = 0.0F;
        mVerticalWeight = 0.0F;
        mHorizontalNextWidget = null;
        mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int j)
    {
        this(0, 0, i, j);
    }

    public ConstraintWidget(int i, int j, int k, int l)
    {
        mHorizontalResolution = -1;
        mVerticalResolution = -1;
        mMatchConstraintDefaultWidth = 0;
        mMatchConstraintDefaultHeight = 0;
        mMatchConstraintMinWidth = 0;
        mMatchConstraintMaxWidth = 0;
        mMatchConstraintMinHeight = 0;
        mMatchConstraintMaxHeight = 0;
        mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        mAnchors = new ArrayList();
        mParent = null;
        mWidth = 0;
        mHeight = 0;
        mDimensionRatio = 0.0F;
        mDimensionRatioSide = -1;
        mSolverLeft = 0;
        mSolverTop = 0;
        mSolverRight = 0;
        mSolverBottom = 0;
        mX = 0;
        mY = 0;
        mDrawX = 0;
        mDrawY = 0;
        mDrawWidth = 0;
        mDrawHeight = 0;
        mOffsetX = 0;
        mOffsetY = 0;
        mBaselineDistance = 0;
        mHorizontalBiasPercent = DEFAULT_BIAS;
        mVerticalBiasPercent = DEFAULT_BIAS;
        mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        mContainerItemSkip = 0;
        mVisibility = 0;
        mDebugName = null;
        mType = null;
        mHorizontalChainStyle = 0;
        mVerticalChainStyle = 0;
        mHorizontalWeight = 0.0F;
        mVerticalWeight = 0.0F;
        mHorizontalNextWidget = null;
        mVerticalNextWidget = null;
        mX = i;
        mY = j;
        mWidth = k;
        mHeight = l;
        addAnchors();
        forceUpdateDrawPosition();
    }

    private void addAnchors()
    {
        mAnchors.add(mLeft);
        mAnchors.add(mTop);
        mAnchors.add(mRight);
        mAnchors.add(mBottom);
        mAnchors.add(mCenterX);
        mAnchors.add(mCenterY);
        mAnchors.add(mBaseline);
    }

    private void applyConstraints(LinearSystem linearsystem, boolean flag, boolean flag1, ConstraintAnchor constraintanchor, ConstraintAnchor constraintanchor1, int i, int j, 
            int k, int l, float f, boolean flag2, boolean flag3, int i1, int j1, 
            int k1)
    {
        SolverVariable solvervariable;
        SolverVariable solvervariable1;
        SolverVariable solvervariable2;
        SolverVariable solvervariable3;
        int l1;
        int i2;
        solvervariable2 = linearsystem.createObjectVariable(constraintanchor);
        solvervariable = linearsystem.createObjectVariable(constraintanchor1);
        solvervariable3 = linearsystem.createObjectVariable(constraintanchor.getTarget());
        solvervariable1 = linearsystem.createObjectVariable(constraintanchor1.getTarget());
        l1 = constraintanchor.getMargin();
        i2 = constraintanchor1.getMargin();
        if (mVisibility == 8)
        {
            k = 0;
            flag1 = true;
        }
        if (solvervariable3 != null || solvervariable1 != null) goto _L2; else goto _L1
_L1:
        linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable2, i));
        if (flag2) goto _L4; else goto _L3
_L3:
        if (!flag) goto _L6; else goto _L5
_L5:
        linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, solvervariable, solvervariable2, l, true));
_L4:
        return;
_L6:
        if (flag1)
        {
            linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, solvervariable, solvervariable2, k, false));
            return;
        } else
        {
            linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, j));
            return;
        }
_L2:
        if (solvervariable3 == null || solvervariable1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable2, solvervariable3, l1));
        if (flag)
        {
            linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, solvervariable, solvervariable2, l, true));
            return;
        }
        if (!flag2)
        {
            if (flag1)
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable2, k));
                return;
            } else
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, j));
                return;
            }
        }
        if (true) goto _L4; else goto _L7
_L7:
        if (solvervariable3 != null || solvervariable1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable1, i2 * -1));
        if (flag)
        {
            linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, solvervariable, solvervariable2, l, true));
            return;
        }
        if (!flag2)
        {
            if (flag1)
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable2, k));
                return;
            } else
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable2, i));
                return;
            }
        }
        if (true) goto _L4; else goto _L8
_L8:
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, solvervariable, solvervariable2, l, true));
        } else
        {
            linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable2, k));
        }
        if (constraintanchor.getStrength() != constraintanchor1.getStrength())
        {
            if (constraintanchor.getStrength() == ConstraintAnchor.Strength.STRONG)
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable2, solvervariable3, l1));
                constraintanchor = linearsystem.createSlackVariable();
                constraintanchor1 = linearsystem.createRow();
                constraintanchor1.createRowLowerThan(solvervariable, solvervariable1, constraintanchor, i2 * -1);
                linearsystem.addConstraint(constraintanchor1);
                return;
            } else
            {
                constraintanchor = linearsystem.createSlackVariable();
                constraintanchor1 = linearsystem.createRow();
                constraintanchor1.createRowGreaterThan(solvervariable2, solvervariable3, constraintanchor, l1);
                linearsystem.addConstraint(constraintanchor1);
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable1, i2 * -1));
                return;
            }
        }
        if (solvervariable3 == solvervariable1)
        {
            linearsystem.addConstraint(LinearSystem.createRowCentering(linearsystem, solvervariable2, solvervariable3, 0, 0.5F, solvervariable1, solvervariable, 0, true));
            return;
        }
        if (!flag3)
        {
            if (constraintanchor.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            linearsystem.addConstraint(LinearSystem.createRowGreaterThan(linearsystem, solvervariable2, solvervariable3, l1, flag));
            if (constraintanchor1.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            linearsystem.addConstraint(LinearSystem.createRowLowerThan(linearsystem, solvervariable, solvervariable1, i2 * -1, flag));
            linearsystem.addConstraint(LinearSystem.createRowCentering(linearsystem, solvervariable2, solvervariable3, l1, f, solvervariable1, solvervariable, i2, false));
            return;
        }
        if (true) goto _L4; else goto _L9
_L9:
        if (flag2)
        {
            linearsystem.addGreaterThan(solvervariable2, solvervariable3, l1, 3);
            linearsystem.addLowerThan(solvervariable, solvervariable1, i2 * -1, 3);
            linearsystem.addConstraint(LinearSystem.createRowCentering(linearsystem, solvervariable2, solvervariable3, l1, f, solvervariable1, solvervariable, i2, true));
            return;
        }
        if (!flag3)
        {
            if (i1 == 1)
            {
                i = k;
                if (j1 > k)
                {
                    i = j1;
                }
                j = i;
                if (k1 > 0)
                {
                    if (k1 < i)
                    {
                        j = k1;
                    } else
                    {
                        linearsystem.addLowerThan(solvervariable, solvervariable2, k1, 3);
                        j = i;
                    }
                }
                linearsystem.addEquality(solvervariable, solvervariable2, j, 3);
                linearsystem.addGreaterThan(solvervariable2, solvervariable3, l1, 2);
                linearsystem.addLowerThan(solvervariable, solvervariable1, -i2, 2);
                linearsystem.addCentering(solvervariable2, solvervariable3, l1, f, solvervariable1, solvervariable, i2, 4);
                return;
            }
            if (j1 == 0 && k1 == 0)
            {
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable2, solvervariable3, l1));
                linearsystem.addConstraint(linearsystem.createRow().createRowEquals(solvervariable, solvervariable1, i2 * -1));
                return;
            }
            if (k1 > 0)
            {
                linearsystem.addLowerThan(solvervariable, solvervariable2, k1, 3);
            }
            linearsystem.addGreaterThan(solvervariable2, solvervariable3, l1, 2);
            linearsystem.addLowerThan(solvervariable, solvervariable1, -i2, 2);
            linearsystem.addCentering(solvervariable2, solvervariable3, l1, f, solvervariable1, solvervariable, i2, 4);
            return;
        }
        if (true) goto _L4; else goto _L10
_L10:
    }

    public void addToSolver(LinearSystem linearsystem)
    {
        addToSolver(linearsystem, 0x7fffffff);
    }

    public void addToSolver(LinearSystem linearsystem, int i)
    {
        SolverVariable solvervariable;
        SolverVariable solvervariable1;
        SolverVariable solvervariable2;
        SolverVariable solvervariable3;
        Object obj;
        boolean flag4;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        solvervariable = null;
        solvervariable1 = null;
        solvervariable2 = null;
        solvervariable3 = null;
        obj = null;
        if (i == 0x7fffffff || mLeft.mGroup == i)
        {
            solvervariable = linearsystem.createObjectVariable(mLeft);
        }
        if (i == 0x7fffffff || mRight.mGroup == i)
        {
            solvervariable1 = linearsystem.createObjectVariable(mRight);
        }
        if (i == 0x7fffffff || mTop.mGroup == i)
        {
            solvervariable2 = linearsystem.createObjectVariable(mTop);
        }
        if (i == 0x7fffffff || mBottom.mGroup == i)
        {
            solvervariable3 = linearsystem.createObjectVariable(mBottom);
        }
        if (i == 0x7fffffff || mBaseline.mGroup == i)
        {
            obj = linearsystem.createObjectVariable(mBaseline);
        }
        flag8 = false;
        flag4 = false;
        flag7 = false;
        flag6 = false;
        if (mParent == null) goto _L2; else goto _L1
_L1:
        boolean flag2;
label0:
        {
            if (mLeft.mTarget == null || mLeft.mTarget.mTarget != mLeft)
            {
                flag2 = flag4;
                if (mRight.mTarget == null)
                {
                    break label0;
                }
                flag2 = flag4;
                if (mRight.mTarget.mTarget != mRight)
                {
                    break label0;
                }
            }
            ((ConstraintWidgetContainer)mParent).addChain(this, 0);
            flag2 = true;
        }
label1:
        {
            if (mTop.mTarget == null || mTop.mTarget.mTarget != mTop)
            {
                flag4 = flag6;
                if (mBottom.mTarget == null)
                {
                    break label1;
                }
                flag4 = flag6;
                if (mBottom.mTarget.mTarget != mBottom)
                {
                    break label1;
                }
            }
            ((ConstraintWidgetContainer)mParent).addChain(this, 1);
            flag4 = true;
        }
        if (mParent.getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT || flag2) goto _L4; else goto _L3
_L3:
        if (mLeft.mTarget != null && mLeft.mTarget.mOwner == mParent) goto _L6; else goto _L5
_L5:
        SolverVariable solvervariable4 = linearsystem.createObjectVariable(mParent.mLeft);
        ArrayRow arrayrow = linearsystem.createRow();
        arrayrow.createRowGreaterThan(solvervariable, solvervariable4, linearsystem.createSlackVariable(), 0);
        linearsystem.addConstraint(arrayrow);
_L12:
        float f;
        float f1;
        int j;
        int k;
        int i1;
        int j1;
        boolean flag;
        int k1;
        boolean flag1;
        int l1;
        boolean flag9;
        if (mRight.mTarget == null || mRight.mTarget.mOwner != mParent)
        {
            SolverVariable solvervariable5 = linearsystem.createObjectVariable(mParent.mRight);
            ArrayRow arrayrow1 = linearsystem.createRow();
            arrayrow1.createRowGreaterThan(solvervariable5, solvervariable1, linearsystem.createSlackVariable(), 0);
            linearsystem.addConstraint(arrayrow1);
        } else
        if (mRight.mTarget != null && mRight.mTarget.mOwner == mParent)
        {
            mRight.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
        }
_L4:
        flag8 = flag2;
        flag7 = flag4;
        if (mParent.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT)
        {
            flag8 = flag2;
            flag7 = flag4;
            if (!flag4)
            {
                SolverVariable solvervariable8;
                SolverVariable solvervariable11;
                SolverVariable solvervariable14;
                SolverVariable solvervariable17;
                if (mTop.mTarget == null || mTop.mTarget.mOwner != mParent)
                {
                    SolverVariable solvervariable6 = linearsystem.createObjectVariable(mParent.mTop);
                    ArrayRow arrayrow2 = linearsystem.createRow();
                    arrayrow2.createRowGreaterThan(solvervariable2, solvervariable6, linearsystem.createSlackVariable(), 0);
                    linearsystem.addConstraint(arrayrow2);
                } else
                if (mTop.mTarget != null && mTop.mTarget.mOwner == mParent)
                {
                    mTop.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                }
                if (mBottom.mTarget == null || mBottom.mTarget.mOwner != mParent)
                {
                    SolverVariable solvervariable7 = linearsystem.createObjectVariable(mParent.mBottom);
                    ArrayRow arrayrow3 = linearsystem.createRow();
                    arrayrow3.createRowGreaterThan(solvervariable7, solvervariable3, linearsystem.createSlackVariable(), 0);
                    linearsystem.addConstraint(arrayrow3);
                    flag7 = flag4;
                    flag8 = flag2;
                } else
                {
                    flag8 = flag2;
                    flag7 = flag4;
                    if (mBottom.mTarget != null)
                    {
                        flag8 = flag2;
                        flag7 = flag4;
                        if (mBottom.mTarget.mOwner == mParent)
                        {
                            mBottom.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                            flag8 = flag2;
                            flag7 = flag4;
                        }
                    }
                }
            }
        }
_L2:
label2:
        {
            j = mWidth;
            k = j;
            if (j < mMinWidth)
            {
                k = mMinWidth;
            }
            j = mHeight;
            j1 = j;
            if (j < mMinHeight)
            {
                j1 = mMinHeight;
            }
            if (mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT)
            {
                flag6 = true;
            } else
            {
                flag6 = false;
            }
            flag2 = flag4;
            if (flag4)
            {
                break label2;
            }
            flag2 = flag4;
            if (mLeft == null)
            {
                break label2;
            }
            flag2 = flag4;
            if (mRight == null)
            {
                break label2;
            }
            if (mLeft.mTarget != null)
            {
                flag2 = flag4;
                if (mRight.mTarget != null)
                {
                    break label2;
                }
            }
            flag2 = true;
        }
label3:
        {
            flag4 = flag6;
            if (flag6)
            {
                break label3;
            }
            flag4 = flag6;
            if (mTop == null)
            {
                break label3;
            }
            flag4 = flag6;
            if (mBottom == null)
            {
                break label3;
            }
            if (mTop.mTarget != null)
            {
                flag4 = flag6;
                if (mBottom.mTarget != null)
                {
                    break label3;
                }
            }
            if (mBaselineDistance != 0)
            {
                flag4 = flag6;
                if (mBaseline == null)
                {
                    break label3;
                }
                if (mTop.mTarget != null)
                {
                    flag4 = flag6;
                    if (mBaseline.mTarget != null)
                    {
                        break label3;
                    }
                }
            }
            flag4 = true;
        }
        flag1 = false;
        l1 = mDimensionRatioSide;
        f1 = mDimensionRatio;
        flag9 = flag2;
        k1 = k;
        flag6 = flag4;
        i1 = j1;
        f = f1;
        j = l1;
        flag = flag1;
        if (mDimensionRatio > 0.0F)
        {
            flag9 = flag2;
            k1 = k;
            flag6 = flag4;
            i1 = j1;
            f = f1;
            j = l1;
            flag = flag1;
            if (mVisibility != 8)
            {
                if (mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    flag1 = true;
                    if (flag2 && !flag4)
                    {
                        j = 0;
                        flag = flag1;
                        f = f1;
                        i1 = j1;
                        flag6 = flag4;
                        k1 = k;
                        flag9 = flag2;
                    } else
                    {
                        flag9 = flag2;
                        k1 = k;
                        flag6 = flag4;
                        i1 = j1;
                        f = f1;
                        j = l1;
                        flag = flag1;
                        if (!flag2)
                        {
                            flag9 = flag2;
                            k1 = k;
                            flag6 = flag4;
                            i1 = j1;
                            f = f1;
                            j = l1;
                            flag = flag1;
                            if (flag4)
                            {
                                l1 = 1;
                                flag9 = flag2;
                                k1 = k;
                                flag6 = flag4;
                                i1 = j1;
                                f = f1;
                                j = l1;
                                flag = flag1;
                                if (mDimensionRatioSide == -1)
                                {
                                    f = 1.0F / f1;
                                    flag9 = flag2;
                                    k1 = k;
                                    flag6 = flag4;
                                    i1 = j1;
                                    j = l1;
                                    flag = flag1;
                                }
                            }
                        }
                    }
                } else
                if (mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    j = 0;
                    k1 = (int)((float)mHeight * f1);
                    flag9 = true;
                    flag6 = flag4;
                    i1 = j1;
                    f = f1;
                    flag = flag1;
                } else
                {
                    flag9 = flag2;
                    k1 = k;
                    flag6 = flag4;
                    i1 = j1;
                    f = f1;
                    j = l1;
                    flag = flag1;
                    if (mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)
                    {
                        j = 1;
                        f = f1;
                        if (mDimensionRatioSide == -1)
                        {
                            f = 1.0F / f1;
                        }
                        i1 = (int)((float)mWidth * f);
                        flag6 = true;
                        flag9 = flag2;
                        k1 = k;
                        flag = flag1;
                    }
                }
            }
        }
        if (flag && (j == 0 || j == -1))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer))
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (mHorizontalResolution != 2 && (i == 0x7fffffff || mLeft.mGroup == i && mRight.mGroup == i))
        {
            if (flag2 && mLeft.mTarget != null && mRight.mTarget != null)
            {
                solvervariable8 = linearsystem.createObjectVariable(mLeft);
                solvervariable11 = linearsystem.createObjectVariable(mRight);
                solvervariable14 = linearsystem.createObjectVariable(mLeft.getTarget());
                solvervariable17 = linearsystem.createObjectVariable(mRight.getTarget());
                linearsystem.addGreaterThan(solvervariable8, solvervariable14, mLeft.getMargin(), 3);
                linearsystem.addLowerThan(solvervariable11, solvervariable17, mRight.getMargin() * -1, 3);
                if (!flag8)
                {
                    linearsystem.addCentering(solvervariable8, solvervariable14, mLeft.getMargin(), mHorizontalBiasPercent, solvervariable17, solvervariable11, mRight.getMargin(), 4);
                }
            } else
            {
                applyConstraints(linearsystem, flag4, flag9, mLeft, mRight, mX, mX + k1, k1, mMinWidth, mHorizontalBiasPercent, flag2, flag8, mMatchConstraintDefaultWidth, mMatchConstraintMinWidth, mMatchConstraintMaxWidth);
            }
        }
        if (mVerticalResolution != 2) goto _L8; else goto _L7
_L7:
        return;
_L6:
        if (mLeft.mTarget != null && mLeft.mTarget.mOwner == mParent)
        {
            mLeft.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        boolean flag3;
        boolean flag5;
        if (mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag && (j == 1 || j == -1))
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        if (mBaselineDistance > 0)
        {
            ConstraintAnchor constraintanchor = mBottom;
            if (i == 0x7fffffff || mBottom.mGroup == i && mBaseline.mGroup == i)
            {
                linearsystem.addEquality(((SolverVariable) (obj)), solvervariable2, getBaselineDistance(), 5);
            }
            obj = constraintanchor;
            int l = i1;
            if (mBaseline.mTarget != null)
            {
                l = mBaselineDistance;
                obj = mBaseline;
            }
            if (i == 0x7fffffff || mTop.mGroup == i && ((ConstraintAnchor) (obj)).mGroup == i)
            {
                if (flag5 && mTop.mTarget != null && mBottom.mTarget != null)
                {
                    obj = linearsystem.createObjectVariable(mTop);
                    SolverVariable solvervariable9 = linearsystem.createObjectVariable(mBottom);
                    SolverVariable solvervariable12 = linearsystem.createObjectVariable(mTop.getTarget());
                    SolverVariable solvervariable15 = linearsystem.createObjectVariable(mBottom.getTarget());
                    linearsystem.addGreaterThan(((SolverVariable) (obj)), solvervariable12, mTop.getMargin(), 3);
                    linearsystem.addLowerThan(solvervariable9, solvervariable15, mBottom.getMargin() * -1, 3);
                    if (!flag7)
                    {
                        linearsystem.addCentering(((SolverVariable) (obj)), solvervariable12, mTop.getMargin(), mVerticalBiasPercent, solvervariable15, solvervariable9, mBottom.getMargin(), 4);
                    }
                } else
                {
                    applyConstraints(linearsystem, flag3, flag6, mTop, ((ConstraintAnchor) (obj)), mY, mY + l, l, mMinHeight, mVerticalBiasPercent, flag5, flag7, mMatchConstraintDefaultHeight, mMatchConstraintMinHeight, mMatchConstraintMaxHeight);
                    linearsystem.addEquality(solvervariable3, solvervariable2, i1, 5);
                }
            }
        } else
        if (i == 0x7fffffff || mTop.mGroup == i && mBottom.mGroup == i)
        {
            if (flag5 && mTop.mTarget != null && mBottom.mTarget != null)
            {
                obj = linearsystem.createObjectVariable(mTop);
                SolverVariable solvervariable10 = linearsystem.createObjectVariable(mBottom);
                SolverVariable solvervariable13 = linearsystem.createObjectVariable(mTop.getTarget());
                SolverVariable solvervariable16 = linearsystem.createObjectVariable(mBottom.getTarget());
                linearsystem.addGreaterThan(((SolverVariable) (obj)), solvervariable13, mTop.getMargin(), 3);
                linearsystem.addLowerThan(solvervariable10, solvervariable16, mBottom.getMargin() * -1, 3);
                if (!flag7)
                {
                    linearsystem.addCentering(((SolverVariable) (obj)), solvervariable13, mTop.getMargin(), mVerticalBiasPercent, solvervariable16, solvervariable10, mBottom.getMargin(), 4);
                }
            } else
            {
                applyConstraints(linearsystem, flag3, flag6, mTop, mBottom, mY, mY + i1, i1, mMinHeight, mVerticalBiasPercent, flag5, flag7, mMatchConstraintDefaultHeight, mMatchConstraintMinHeight, mMatchConstraintMaxHeight);
            }
        }
        if (!flag) goto _L7; else goto _L9
_L9:
        obj = linearsystem.createRow();
        if (i != 0x7fffffff && (mLeft.mGroup != i || mRight.mGroup != i)) goto _L7; else goto _L10
_L10:
        if (j == 0)
        {
            linearsystem.addConstraint(((ArrayRow) (obj)).createRowDimensionRatio(solvervariable1, solvervariable, solvervariable3, solvervariable2, f));
            return;
        }
        if (j == 1)
        {
            linearsystem.addConstraint(((ArrayRow) (obj)).createRowDimensionRatio(solvervariable3, solvervariable2, solvervariable1, solvervariable, f));
            return;
        }
        if (mMatchConstraintMinWidth > 0)
        {
            linearsystem.addGreaterThan(solvervariable1, solvervariable, mMatchConstraintMinWidth, 3);
        }
        if (mMatchConstraintMinHeight > 0)
        {
            linearsystem.addGreaterThan(solvervariable3, solvervariable2, mMatchConstraintMinHeight, 3);
        }
        ((ArrayRow) (obj)).createRowDimensionRatio(solvervariable1, solvervariable, solvervariable3, solvervariable2, f);
        solvervariable = linearsystem.createErrorVariable();
        solvervariable1 = linearsystem.createErrorVariable();
        solvervariable.strength = 4;
        solvervariable1.strength = 4;
        ((ArrayRow) (obj)).addError(solvervariable, solvervariable1);
        linearsystem.addConstraint(((ArrayRow) (obj)));
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintwidget, ConstraintAnchor.Type type1)
    {
        connect(type, constraintwidget, type1, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintwidget, ConstraintAnchor.Type type1, int i)
    {
        connect(type, constraintwidget, type1, i, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintwidget, ConstraintAnchor.Type type1, int i, ConstraintAnchor.Strength strength)
    {
        connect(type, constraintwidget, type1, i, strength, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintwidget, ConstraintAnchor.Type type1, int i, ConstraintAnchor.Strength strength, int j)
    {
        if (type != ConstraintAnchor.Type.CENTER) goto _L2; else goto _L1
_L1:
        if (type1 != ConstraintAnchor.Type.CENTER) goto _L4; else goto _L3
_L3:
        int k;
label0:
        {
            type = getAnchor(ConstraintAnchor.Type.LEFT);
            type1 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor constraintanchor = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor constraintanchor2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            k = 0;
            boolean flag = false;
            if (type != null)
            {
                i = k;
                if (type.isConnected())
                {
                    break label0;
                }
            }
            if (type1 != null && type1.isConnected())
            {
                i = k;
            } else
            {
                connect(ConstraintAnchor.Type.LEFT, constraintwidget, ConstraintAnchor.Type.LEFT, 0, strength, j);
                connect(ConstraintAnchor.Type.RIGHT, constraintwidget, ConstraintAnchor.Type.RIGHT, 0, strength, j);
                i = 1;
            }
        }
label1:
        {
            if (constraintanchor != null)
            {
                k = ((flag) ? 1 : 0);
                if (constraintanchor.isConnected())
                {
                    break label1;
                }
            }
            if (constraintanchor2 != null && constraintanchor2.isConnected())
            {
                k = ((flag) ? 1 : 0);
            } else
            {
                connect(ConstraintAnchor.Type.TOP, constraintwidget, ConstraintAnchor.Type.TOP, 0, strength, j);
                connect(ConstraintAnchor.Type.BOTTOM, constraintwidget, ConstraintAnchor.Type.BOTTOM, 0, strength, j);
                k = 1;
            }
        }
        if (i == 0 || !k) goto _L6; else goto _L5
_L5:
        getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.CENTER), 0, j);
_L8:
        return;
_L6:
        if (i != 0)
        {
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, j);
            return;
        }
        if (!k) goto _L8; else goto _L7
_L7:
        getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, j);
        return;
_L4:
        if (type1 == ConstraintAnchor.Type.LEFT || type1 == ConstraintAnchor.Type.RIGHT)
        {
            connect(ConstraintAnchor.Type.LEFT, constraintwidget, type1, 0, strength, j);
            connect(ConstraintAnchor.Type.RIGHT, constraintwidget, type1, 0, strength, j);
            getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintwidget.getAnchor(type1), 0, j);
            return;
        }
        if (type1 != ConstraintAnchor.Type.TOP && type1 != ConstraintAnchor.Type.BOTTOM) goto _L8; else goto _L9
_L9:
        connect(ConstraintAnchor.Type.TOP, constraintwidget, type1, 0, strength, j);
        connect(ConstraintAnchor.Type.BOTTOM, constraintwidget, type1, 0, strength, j);
        getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintwidget.getAnchor(type1), 0, j);
        return;
_L2:
        ConstraintAnchor constraintanchor1;
        if (type == ConstraintAnchor.Type.CENTER_X && (type1 == ConstraintAnchor.Type.LEFT || type1 == ConstraintAnchor.Type.RIGHT))
        {
            type = getAnchor(ConstraintAnchor.Type.LEFT);
            constraintwidget = constraintwidget.getAnchor(type1);
            type1 = getAnchor(ConstraintAnchor.Type.RIGHT);
            type.connect(constraintwidget, 0, j);
            type1.connect(constraintwidget, 0, j);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintwidget, 0, j);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_Y && (type1 == ConstraintAnchor.Type.TOP || type1 == ConstraintAnchor.Type.BOTTOM))
        {
            type = constraintwidget.getAnchor(type1);
            getAnchor(ConstraintAnchor.Type.TOP).connect(type, 0, j);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(type, 0, j);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(type, 0, j);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_X && type1 == ConstraintAnchor.Type.CENTER_X)
        {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.LEFT), 0, j);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.RIGHT), 0, j);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintwidget.getAnchor(type1), 0, j);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_Y && type1 == ConstraintAnchor.Type.CENTER_Y)
        {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.TOP), 0, j);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintwidget.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, j);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintwidget.getAnchor(type1), 0, j);
            return;
        }
        constraintanchor1 = getAnchor(type);
        constraintwidget = constraintwidget.getAnchor(type1);
        if (!constraintanchor1.isValidConnection(constraintwidget)) goto _L8; else goto _L10
_L10:
        if (type != ConstraintAnchor.Type.BASELINE) goto _L12; else goto _L11
_L11:
        type = getAnchor(ConstraintAnchor.Type.TOP);
        type1 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        if (type != null)
        {
            type.reset();
        }
        if (type1 != null)
        {
            type1.reset();
        }
        k = 0;
_L14:
        constraintanchor1.connect(constraintwidget, k, strength, j);
        constraintwidget.getOwner().connectedTo(constraintanchor1.getOwner());
        return;
_L12:
        if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM)
        {
            type1 = getAnchor(ConstraintAnchor.Type.BASELINE);
            if (type1 != null)
            {
                type1.reset();
            }
            type1 = getAnchor(ConstraintAnchor.Type.CENTER);
            if (type1.getTarget() != constraintwidget)
            {
                type1.reset();
            }
            type = getAnchor(type).getOpposite();
            type1 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            k = i;
            if (type1.isConnected())
            {
                type.reset();
                type1.reset();
                k = i;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (type != ConstraintAnchor.Type.LEFT)
        {
            k = i;
            if (type != ConstraintAnchor.Type.RIGHT)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        type1 = getAnchor(ConstraintAnchor.Type.CENTER);
        if (type1.getTarget() != constraintwidget)
        {
            type1.reset();
        }
        type = getAnchor(type).getOpposite();
        type1 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        k = i;
        if (type1.isConnected())
        {
            type.reset();
            type1.reset();
            k = i;
        }
        if (true) goto _L14; else goto _L13
_L13:
    }

    public void connect(ConstraintAnchor constraintanchor, ConstraintAnchor constraintanchor1, int i)
    {
        connect(constraintanchor, constraintanchor1, i, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintanchor, ConstraintAnchor constraintanchor1, int i, int j)
    {
        connect(constraintanchor, constraintanchor1, i, ConstraintAnchor.Strength.STRONG, j);
    }

    public void connect(ConstraintAnchor constraintanchor, ConstraintAnchor constraintanchor1, int i, ConstraintAnchor.Strength strength, int j)
    {
        if (constraintanchor.getOwner() == this)
        {
            connect(constraintanchor.getType(), constraintanchor1.getOwner(), constraintanchor1.getType(), i, strength, j);
        }
    }

    public void connectedTo(ConstraintWidget constraintwidget)
    {
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintwidget)
    {
        ArrayList arraylist = getAnchors();
        int i = 0;
        for (int j = arraylist.size(); i < j; i++)
        {
            ConstraintAnchor constraintanchor = (ConstraintAnchor)arraylist.get(i);
            if (constraintanchor.isConnected() && constraintanchor.getTarget().getOwner() == constraintwidget && constraintanchor.getConnectionCreator() == 2)
            {
                constraintanchor.reset();
            }
        }

    }

    public void disconnectWidget(ConstraintWidget constraintwidget)
    {
        ArrayList arraylist = getAnchors();
        int i = 0;
        for (int j = arraylist.size(); i < j; i++)
        {
            ConstraintAnchor constraintanchor = (ConstraintAnchor)arraylist.get(i);
            if (constraintanchor.isConnected() && constraintanchor.getTarget().getOwner() == constraintwidget)
            {
                constraintanchor.reset();
            }
        }

    }

    public void forceUpdateDrawPosition()
    {
        int i = mX;
        int j = mY;
        int k = mX;
        int l = mWidth;
        int i1 = mY;
        int j1 = mHeight;
        mDrawX = i;
        mDrawY = j;
        mDrawWidth = (k + l) - i;
        mDrawHeight = (i1 + j1) - j;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type)
    {
        static class _cls1
        {

            static final int $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[];

            static 
            {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type = new int[ConstraintAnchor.Type.values().length];
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[type.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return mLeft;

        case 2: // '\002'
            return mTop;

        case 3: // '\003'
            return mRight;

        case 4: // '\004'
            return mBottom;

        case 5: // '\005'
            return mBaseline;

        case 6: // '\006'
            return mCenterX;

        case 7: // '\007'
            return mCenterY;

        case 8: // '\b'
            return mCenter;
        }
    }

    public ArrayList getAnchors()
    {
        return mAnchors;
    }

    public int getBaselineDistance()
    {
        return mBaselineDistance;
    }

    public int getBottom()
    {
        return getY() + mHeight;
    }

    public Object getCompanionWidget()
    {
        return mCompanionWidget;
    }

    public int getContainerItemSkip()
    {
        return mContainerItemSkip;
    }

    public String getDebugName()
    {
        return mDebugName;
    }

    public float getDimensionRatio()
    {
        return mDimensionRatio;
    }

    public int getDimensionRatioSide()
    {
        return mDimensionRatioSide;
    }

    public int getDrawBottom()
    {
        return getDrawY() + mDrawHeight;
    }

    public int getDrawHeight()
    {
        return mDrawHeight;
    }

    public int getDrawRight()
    {
        return getDrawX() + mDrawWidth;
    }

    public int getDrawWidth()
    {
        return mDrawWidth;
    }

    public int getDrawX()
    {
        return mDrawX + mOffsetX;
    }

    public int getDrawY()
    {
        return mDrawY + mOffsetY;
    }

    public int getHeight()
    {
        if (mVisibility == 8)
        {
            return 0;
        } else
        {
            return mHeight;
        }
    }

    public float getHorizontalBiasPercent()
    {
        return mHorizontalBiasPercent;
    }

    public ConstraintWidget getHorizontalChainControlWidget()
    {
        ConstraintWidget constraintwidget;
        Object obj1;
        obj1 = null;
        constraintwidget = null;
        if (!isInHorizontalChain()) goto _L2; else goto _L1
_L1:
        Object obj = this;
_L8:
        obj1 = constraintwidget;
        if (constraintwidget != null) goto _L2; else goto _L3
_L3:
        obj1 = constraintwidget;
        if (obj == null) goto _L2; else goto _L4
_L4:
        obj1 = ((ConstraintWidget) (obj)).getAnchor(ConstraintAnchor.Type.LEFT);
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((ConstraintAnchor) (obj1)).getTarget();
        }
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((ConstraintAnchor) (obj1)).getOwner();
        }
        if (obj1 != getParent()) goto _L6; else goto _L5
_L5:
        obj1 = obj;
_L2:
        return ((ConstraintWidget) (obj1));
_L6:
        ConstraintAnchor constraintanchor;
        if (obj1 == null)
        {
            constraintanchor = null;
        } else
        {
            constraintanchor = ((ConstraintWidget) (obj1)).getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
        }
        if (constraintanchor != null && constraintanchor.getOwner() != obj)
        {
            constraintwidget = ((ConstraintWidget) (obj));
        } else
        {
            obj = obj1;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int getHorizontalChainStyle()
    {
        return mHorizontalChainStyle;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour()
    {
        return mHorizontalDimensionBehaviour;
    }

    public int getInternalDrawBottom()
    {
        return mDrawY + mDrawHeight;
    }

    public int getInternalDrawRight()
    {
        return mDrawX + mDrawWidth;
    }

    int getInternalDrawX()
    {
        return mDrawX;
    }

    int getInternalDrawY()
    {
        return mDrawY;
    }

    public int getLeft()
    {
        return getX();
    }

    public int getMinHeight()
    {
        return mMinHeight;
    }

    public int getMinWidth()
    {
        return mMinWidth;
    }

    public int getOptimizerWrapHeight()
    {
        int i = mHeight;
        int j = i;
        if (mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)
        {
            if (mMatchConstraintDefaultHeight == 1)
            {
                i = Math.max(mMatchConstraintMinHeight, i);
            } else
            if (mMatchConstraintMinHeight > 0)
            {
                i = mMatchConstraintMinHeight;
                mHeight = i;
            } else
            {
                i = 0;
            }
            j = i;
            if (mMatchConstraintMaxHeight > 0)
            {
                j = i;
                if (mMatchConstraintMaxHeight < i)
                {
                    j = mMatchConstraintMaxHeight;
                }
            }
        }
        return j;
    }

    public int getOptimizerWrapWidth()
    {
        int i = mWidth;
        int j = i;
        if (mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)
        {
            if (mMatchConstraintDefaultWidth == 1)
            {
                i = Math.max(mMatchConstraintMinWidth, i);
            } else
            if (mMatchConstraintMinWidth > 0)
            {
                i = mMatchConstraintMinWidth;
                mWidth = i;
            } else
            {
                i = 0;
            }
            j = i;
            if (mMatchConstraintMaxWidth > 0)
            {
                j = i;
                if (mMatchConstraintMaxWidth < i)
                {
                    j = mMatchConstraintMaxWidth;
                }
            }
        }
        return j;
    }

    public ConstraintWidget getParent()
    {
        return mParent;
    }

    public int getRight()
    {
        return getX() + mWidth;
    }

    public WidgetContainer getRootWidgetContainer()
    {
        ConstraintWidget constraintwidget;
        for (constraintwidget = this; constraintwidget.getParent() != null; constraintwidget = constraintwidget.getParent()) { }
        if (constraintwidget instanceof WidgetContainer)
        {
            return (WidgetContainer)constraintwidget;
        } else
        {
            return null;
        }
    }

    protected int getRootX()
    {
        return mX + mOffsetX;
    }

    protected int getRootY()
    {
        return mY + mOffsetY;
    }

    public int getTop()
    {
        return getY();
    }

    public String getType()
    {
        return mType;
    }

    public float getVerticalBiasPercent()
    {
        return mVerticalBiasPercent;
    }

    public ConstraintWidget getVerticalChainControlWidget()
    {
        ConstraintWidget constraintwidget;
        Object obj1;
        obj1 = null;
        constraintwidget = null;
        if (!isInVerticalChain()) goto _L2; else goto _L1
_L1:
        Object obj = this;
_L8:
        obj1 = constraintwidget;
        if (constraintwidget != null) goto _L2; else goto _L3
_L3:
        obj1 = constraintwidget;
        if (obj == null) goto _L2; else goto _L4
_L4:
        obj1 = ((ConstraintWidget) (obj)).getAnchor(ConstraintAnchor.Type.TOP);
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((ConstraintAnchor) (obj1)).getTarget();
        }
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((ConstraintAnchor) (obj1)).getOwner();
        }
        if (obj1 != getParent()) goto _L6; else goto _L5
_L5:
        obj1 = obj;
_L2:
        return ((ConstraintWidget) (obj1));
_L6:
        ConstraintAnchor constraintanchor;
        if (obj1 == null)
        {
            constraintanchor = null;
        } else
        {
            constraintanchor = ((ConstraintWidget) (obj1)).getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
        }
        if (constraintanchor != null && constraintanchor.getOwner() != obj)
        {
            constraintwidget = ((ConstraintWidget) (obj));
        } else
        {
            obj = obj1;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int getVerticalChainStyle()
    {
        return mVerticalChainStyle;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour()
    {
        return mVerticalDimensionBehaviour;
    }

    public int getVisibility()
    {
        return mVisibility;
    }

    public int getWidth()
    {
        if (mVisibility == 8)
        {
            return 0;
        } else
        {
            return mWidth;
        }
    }

    public int getWrapHeight()
    {
        return mWrapHeight;
    }

    public int getWrapWidth()
    {
        return mWrapWidth;
    }

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    public boolean hasAncestor(ConstraintWidget constraintwidget)
    {
        ConstraintWidget constraintwidget2 = getParent();
        if (constraintwidget2 != constraintwidget) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        ConstraintWidget constraintwidget1;
        constraintwidget1 = constraintwidget2;
        if (constraintwidget2 == constraintwidget.getParent())
        {
            return false;
        }
_L5:
label0:
        {
            if (constraintwidget1 == null)
            {
                break label0;
            }
            if (constraintwidget1 == constraintwidget)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (constraintwidget1 == constraintwidget.getParent())
            {
                return true;
            }
        }
        if (true) goto _L4; else goto _L3
_L4:
        constraintwidget1 = constraintwidget1.getParent();
        if (true) goto _L5; else goto _L3
_L3:
        if (true) goto _L1; else goto _L6
_L6:
        return false;
    }

    public boolean hasBaseline()
    {
        return mBaselineDistance > 0;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintwidget, ConstraintAnchor.Type type1, int i, int j)
    {
        getAnchor(type).connect(constraintwidget.getAnchor(type1), i, j, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public boolean isInHorizontalChain()
    {
        return mLeft.mTarget != null && mLeft.mTarget.mTarget == mLeft || mRight.mTarget != null && mRight.mTarget.mTarget == mRight;
    }

    public boolean isInVerticalChain()
    {
        return mTop.mTarget != null && mTop.mTarget.mTarget == mTop || mBottom.mTarget != null && mBottom.mTarget.mTarget == mBottom;
    }

    public boolean isInsideConstraintLayout()
    {
        ConstraintWidget constraintwidget;
        ConstraintWidget constraintwidget1;
        constraintwidget1 = getParent();
        constraintwidget = constraintwidget1;
        if (constraintwidget1 != null) goto _L2; else goto _L1
_L1:
        return false;
_L4:
        constraintwidget = constraintwidget.getParent();
_L2:
        if (constraintwidget == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (constraintwidget instanceof ConstraintWidgetContainer)
        {
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

    public boolean isRoot()
    {
        return mParent == null;
    }

    public boolean isRootContainer()
    {
        return (this instanceof ConstraintWidgetContainer) && (mParent == null || !(mParent instanceof ConstraintWidgetContainer));
    }

    public void reset()
    {
        mLeft.reset();
        mTop.reset();
        mRight.reset();
        mBottom.reset();
        mBaseline.reset();
        mCenterX.reset();
        mCenterY.reset();
        mCenter.reset();
        mParent = null;
        mWidth = 0;
        mHeight = 0;
        mDimensionRatio = 0.0F;
        mDimensionRatioSide = -1;
        mX = 0;
        mY = 0;
        mDrawX = 0;
        mDrawY = 0;
        mDrawWidth = 0;
        mDrawHeight = 0;
        mOffsetX = 0;
        mOffsetY = 0;
        mBaselineDistance = 0;
        mMinWidth = 0;
        mMinHeight = 0;
        mWrapWidth = 0;
        mWrapHeight = 0;
        mHorizontalBiasPercent = DEFAULT_BIAS;
        mVerticalBiasPercent = DEFAULT_BIAS;
        mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        mCompanionWidget = null;
        mContainerItemSkip = 0;
        mVisibility = 0;
        mDebugName = null;
        mType = null;
        mHorizontalWrapVisited = false;
        mVerticalWrapVisited = false;
        mHorizontalChainStyle = 0;
        mVerticalChainStyle = 0;
        mHorizontalChainFixedPosition = false;
        mVerticalChainFixedPosition = false;
        mHorizontalWeight = 0.0F;
        mVerticalWeight = 0.0F;
        mHorizontalResolution = -1;
        mVerticalResolution = -1;
    }

    public void resetAllConstraints()
    {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT)
        {
            if (getWidth() == getWrapWidth())
            {
                setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else
            if (getWidth() > getMinWidth())
            {
                setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        if (getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT) goto _L1; else goto _L3
_L3:
        if (getHeight() == getWrapHeight())
        {
            setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (getHeight() <= getMinHeight()) goto _L1; else goto _L4
_L4:
        setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
        return;
    }

    public void resetAnchor(ConstraintAnchor constraintanchor)
    {
        ConstraintAnchor constraintanchor1;
        ConstraintAnchor constraintanchor2;
        ConstraintAnchor constraintanchor3;
        ConstraintAnchor constraintanchor4;
        ConstraintAnchor constraintanchor5;
        ConstraintAnchor constraintanchor6;
        ConstraintAnchor constraintanchor7;
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
        {
            return;
        }
        constraintanchor1 = getAnchor(ConstraintAnchor.Type.LEFT);
        constraintanchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        constraintanchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        constraintanchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        constraintanchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        constraintanchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        constraintanchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintanchor != constraintanchor5) goto _L2; else goto _L1
_L1:
        if (constraintanchor1.isConnected() && constraintanchor2.isConnected() && constraintanchor1.getTarget() == constraintanchor2.getTarget())
        {
            constraintanchor1.reset();
            constraintanchor2.reset();
        }
        if (constraintanchor3.isConnected() && constraintanchor4.isConnected() && constraintanchor3.getTarget() == constraintanchor4.getTarget())
        {
            constraintanchor3.reset();
            constraintanchor4.reset();
        }
        mHorizontalBiasPercent = 0.5F;
        mVerticalBiasPercent = 0.5F;
_L4:
        constraintanchor.reset();
        return;
_L2:
        if (constraintanchor == constraintanchor6)
        {
            if (constraintanchor1.isConnected() && constraintanchor2.isConnected() && constraintanchor1.getTarget().getOwner() == constraintanchor2.getTarget().getOwner())
            {
                constraintanchor1.reset();
                constraintanchor2.reset();
            }
            mHorizontalBiasPercent = 0.5F;
        } else
        if (constraintanchor == constraintanchor7)
        {
            if (constraintanchor3.isConnected() && constraintanchor4.isConnected() && constraintanchor3.getTarget().getOwner() == constraintanchor4.getTarget().getOwner())
            {
                constraintanchor3.reset();
                constraintanchor4.reset();
            }
            mVerticalBiasPercent = 0.5F;
        } else
        if (constraintanchor == constraintanchor1 || constraintanchor == constraintanchor2)
        {
            if (constraintanchor1.isConnected() && constraintanchor1.getTarget() == constraintanchor2.getTarget())
            {
                constraintanchor5.reset();
            }
        } else
        if ((constraintanchor == constraintanchor3 || constraintanchor == constraintanchor4) && constraintanchor3.isConnected() && constraintanchor3.getTarget() == constraintanchor4.getTarget())
        {
            constraintanchor5.reset();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void resetAnchors()
    {
        ConstraintWidget constraintwidget = getParent();
        if (constraintwidget == null || !(constraintwidget instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
        {
            int i = 0;
            int j = mAnchors.size();
            while (i < j) 
            {
                ((ConstraintAnchor)mAnchors.get(i)).reset();
                i++;
            }
        }
    }

    public void resetAnchors(int i)
    {
        ConstraintWidget constraintwidget = getParent();
        if (constraintwidget == null || !(constraintwidget instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
        {
            int j = 0;
            int k = mAnchors.size();
            while (j < k) 
            {
                ConstraintAnchor constraintanchor = (ConstraintAnchor)mAnchors.get(j);
                if (i == constraintanchor.getConnectionCreator())
                {
                    if (constraintanchor.isVerticalAnchor())
                    {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else
                    {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintanchor.reset();
                }
                j++;
            }
        }
    }

    public void resetGroups()
    {
        int j = mAnchors.size();
        for (int i = 0; i < j; i++)
        {
            ((ConstraintAnchor)mAnchors.get(i)).mGroup = 0x7fffffff;
        }

    }

    public void resetSolverVariables(Cache cache)
    {
        mLeft.resetSolverVariable(cache);
        mTop.resetSolverVariable(cache);
        mRight.resetSolverVariable(cache);
        mBottom.resetSolverVariable(cache);
        mBaseline.resetSolverVariable(cache);
        mCenter.resetSolverVariable(cache);
        mCenterX.resetSolverVariable(cache);
        mCenterY.resetSolverVariable(cache);
    }

    public void setBaselineDistance(int i)
    {
        mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj)
    {
        mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i)
    {
        if (i >= 0)
        {
            mContainerItemSkip = i;
            return;
        } else
        {
            mContainerItemSkip = 0;
            return;
        }
    }

    public void setDebugName(String s)
    {
        mDebugName = s;
    }

    public void setDebugSolverName(LinearSystem linearsystem, String s)
    {
        mDebugName = s;
        SolverVariable solvervariable = linearsystem.createObjectVariable(mLeft);
        SolverVariable solvervariable1 = linearsystem.createObjectVariable(mTop);
        SolverVariable solvervariable2 = linearsystem.createObjectVariable(mRight);
        SolverVariable solvervariable3 = linearsystem.createObjectVariable(mBottom);
        solvervariable.setName((new StringBuilder()).append(s).append(".left").toString());
        solvervariable1.setName((new StringBuilder()).append(s).append(".top").toString());
        solvervariable2.setName((new StringBuilder()).append(s).append(".right").toString());
        solvervariable3.setName((new StringBuilder()).append(s).append(".bottom").toString());
        if (mBaselineDistance > 0)
        {
            linearsystem.createObjectVariable(mBaseline).setName((new StringBuilder()).append(s).append(".baseline").toString());
        }
    }

    public void setDimension(int i, int j)
    {
        mWidth = i;
        if (mWidth < mMinWidth)
        {
            mWidth = mMinWidth;
        }
        mHeight = j;
        if (mHeight < mMinHeight)
        {
            mHeight = mMinHeight;
        }
    }

    public void setDimensionRatio(float f, int i)
    {
        mDimensionRatio = f;
        mDimensionRatioSide = i;
    }

    public void setDimensionRatio(String s)
    {
        if (s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        mDimensionRatio = 0.0F;
_L4:
        return;
_L2:
        float f;
        float f1;
        float f2;
        float f3;
        int j;
        int i = -1;
        f1 = 0.0F;
        int k = s.length();
        j = s.indexOf(',');
        if (j > 0 && j < k - 1)
        {
            String s1 = s.substring(0, j);
            int l;
            if (s1.equalsIgnoreCase("W"))
            {
                i = 0;
            } else
            if (s1.equalsIgnoreCase("H"))
            {
                i = 1;
            }
            j++;
        } else
        {
            j = 0;
        }
        l = s.indexOf(':');
        if (l < 0 || l >= k - 1)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        s1 = s.substring(j, l);
        s = s.substring(l + 1);
        f = f1;
        if (s1.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        f = f1;
        if (s.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        f2 = Float.parseFloat(s1);
        f3 = Float.parseFloat(s);
        f = f1;
        if (f2 > 0.0F)
        {
            f = f1;
            if (f3 > 0.0F)
            {
                if (i != 1)
                {
                    break; /* Loop/switch isn't completed */
                }
                try
                {
                    f = Math.abs(f3 / f2);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    f = f1;
                }
            }
        }
        if (f > 0.0F)
        {
            mDimensionRatio = f;
            mDimensionRatioSide = i;
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        f = f2 / f3;
        f = Math.abs(f);
        break MISSING_BLOCK_LABEL_185;
        s = s.substring(j);
        f = f1;
        if (s.length() > 0)
        {
            try
            {
                f = Float.parseFloat(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                f = f1;
            }
        }
        break MISSING_BLOCK_LABEL_185;
    }

    public void setDrawHeight(int i)
    {
        mDrawHeight = i;
    }

    public void setDrawOrigin(int i, int j)
    {
        mDrawX = i - mOffsetX;
        mDrawY = j - mOffsetY;
        mX = mDrawX;
        mY = mDrawY;
    }

    public void setDrawWidth(int i)
    {
        mDrawWidth = i;
    }

    public void setDrawX(int i)
    {
        mDrawX = i - mOffsetX;
        mX = mDrawX;
    }

    public void setDrawY(int i)
    {
        mDrawY = i - mOffsetY;
        mY = mDrawY;
    }

    public void setFrame(int i, int j, int k, int l)
    {
        int i1 = k - i;
        k = l - j;
        mX = i;
        mY = j;
        if (mVisibility == 8)
        {
            mWidth = 0;
            mHeight = 0;
        } else
        {
            i = i1;
            if (mHorizontalDimensionBehaviour == DimensionBehaviour.FIXED)
            {
                i = i1;
                if (i1 < mWidth)
                {
                    i = mWidth;
                }
            }
            j = k;
            if (mVerticalDimensionBehaviour == DimensionBehaviour.FIXED)
            {
                j = k;
                if (k < mHeight)
                {
                    j = mHeight;
                }
            }
            mWidth = i;
            mHeight = j;
            if (mHeight < mMinHeight)
            {
                mHeight = mMinHeight;
            }
            if (mWidth < mMinWidth)
            {
                mWidth = mMinWidth;
                return;
            }
        }
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i)
    {
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[type.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            mLeft.mGoneMargin = i;
            return;

        case 2: // '\002'
            mTop.mGoneMargin = i;
            return;

        case 3: // '\003'
            mRight.mGoneMargin = i;
            return;

        case 4: // '\004'
            mBottom.mGoneMargin = i;
            break;
        }
    }

    public void setHeight(int i)
    {
        mHeight = i;
        if (mHeight < mMinHeight)
        {
            mHeight = mMinHeight;
        }
    }

    public void setHorizontalBiasPercent(float f)
    {
        mHorizontalBiasPercent = f;
    }

    public void setHorizontalChainStyle(int i)
    {
        mHorizontalChainStyle = i;
    }

    public void setHorizontalDimension(int i, int j)
    {
        mX = i;
        mWidth = j - i;
        if (mWidth < mMinWidth)
        {
            mWidth = mMinWidth;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionbehaviour)
    {
        mHorizontalDimensionBehaviour = dimensionbehaviour;
        if (mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
        {
            setWidth(mWrapWidth);
        }
    }

    public void setHorizontalMatchStyle(int i, int j, int k)
    {
        mMatchConstraintDefaultWidth = i;
        mMatchConstraintMinWidth = j;
        mMatchConstraintMaxWidth = k;
    }

    public void setHorizontalWeight(float f)
    {
        mHorizontalWeight = f;
    }

    public void setMinHeight(int i)
    {
        if (i < 0)
        {
            mMinHeight = 0;
            return;
        } else
        {
            mMinHeight = i;
            return;
        }
    }

    public void setMinWidth(int i)
    {
        if (i < 0)
        {
            mMinWidth = 0;
            return;
        } else
        {
            mMinWidth = i;
            return;
        }
    }

    public void setOffset(int i, int j)
    {
        mOffsetX = i;
        mOffsetY = j;
    }

    public void setOrigin(int i, int j)
    {
        mX = i;
        mY = j;
    }

    public void setParent(ConstraintWidget constraintwidget)
    {
        mParent = constraintwidget;
    }

    public void setType(String s)
    {
        mType = s;
    }

    public void setVerticalBiasPercent(float f)
    {
        mVerticalBiasPercent = f;
    }

    public void setVerticalChainStyle(int i)
    {
        mVerticalChainStyle = i;
    }

    public void setVerticalDimension(int i, int j)
    {
        mY = i;
        mHeight = j - i;
        if (mHeight < mMinHeight)
        {
            mHeight = mMinHeight;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionbehaviour)
    {
        mVerticalDimensionBehaviour = dimensionbehaviour;
        if (mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
        {
            setHeight(mWrapHeight);
        }
    }

    public void setVerticalMatchStyle(int i, int j, int k)
    {
        mMatchConstraintDefaultHeight = i;
        mMatchConstraintMinHeight = j;
        mMatchConstraintMaxHeight = k;
    }

    public void setVerticalWeight(float f)
    {
        mVerticalWeight = f;
    }

    public void setVisibility(int i)
    {
        mVisibility = i;
    }

    public void setWidth(int i)
    {
        mWidth = i;
        if (mWidth < mMinWidth)
        {
            mWidth = mMinWidth;
        }
    }

    public void setWrapHeight(int i)
    {
        mWrapHeight = i;
    }

    public void setWrapWidth(int i)
    {
        mWrapWidth = i;
    }

    public void setX(int i)
    {
        mX = i;
    }

    public void setY(int i)
    {
        mY = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        if (mType != null)
        {
            s = (new StringBuilder()).append("type: ").append(mType).append(" ").toString();
        } else
        {
            s = "";
        }
        stringbuilder = stringbuilder.append(s);
        if (mDebugName != null)
        {
            s = (new StringBuilder()).append("id: ").append(mDebugName).append(" ").toString();
        } else
        {
            s = "";
        }
        return stringbuilder.append(s).append("(").append(mX).append(", ").append(mY).append(") - (").append(mWidth).append(" x ").append(mHeight).append(")").append(" wrap: (").append(mWrapWidth).append(" x ").append(mWrapHeight).append(")").toString();
    }

    public void updateDrawPosition()
    {
        int i = mX;
        int j = mY;
        int k = mX;
        int l = mWidth;
        int i1 = mY;
        int j1 = mHeight;
        mDrawX = i;
        mDrawY = j;
        mDrawWidth = (k + l) - i;
        mDrawHeight = (i1 + j1) - j;
    }

    public void updateFromSolver(LinearSystem linearsystem)
    {
        updateFromSolver(linearsystem, 0x7fffffff);
    }

    public void updateFromSolver(LinearSystem linearsystem, int i)
    {
        if (i == 0x7fffffff)
        {
            setFrame(linearsystem.getObjectVariableValue(mLeft), linearsystem.getObjectVariableValue(mTop), linearsystem.getObjectVariableValue(mRight), linearsystem.getObjectVariableValue(mBottom));
        } else
        {
            if (i == -2)
            {
                setFrame(mSolverLeft, mSolverTop, mSolverRight, mSolverBottom);
                return;
            }
            if (mLeft.mGroup == i)
            {
                mSolverLeft = linearsystem.getObjectVariableValue(mLeft);
            }
            if (mTop.mGroup == i)
            {
                mSolverTop = linearsystem.getObjectVariableValue(mTop);
            }
            if (mRight.mGroup == i)
            {
                mSolverRight = linearsystem.getObjectVariableValue(mRight);
            }
            if (mBottom.mGroup == i)
            {
                mSolverBottom = linearsystem.getObjectVariableValue(mBottom);
                return;
            }
        }
    }

    static 
    {
        DEFAULT_BIAS = 0.5F;
    }
}
