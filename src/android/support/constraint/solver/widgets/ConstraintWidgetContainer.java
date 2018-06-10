// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package android.support.constraint.solver.widgets:
//            WidgetContainer, ConstraintWidget, ConstraintAnchor, Optimizer, 
//            Rectangle, Guideline, Snapshot

public class ConstraintWidgetContainer extends WidgetContainer
{

    static boolean ALLOW_ROOT_GROUP = false;
    private static final int CHAIN_FIRST = 0;
    private static final int CHAIN_FIRST_VISIBLE = 2;
    private static final int CHAIN_LAST = 1;
    private static final int CHAIN_LAST_VISIBLE = 3;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_OPTIMIZE = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_CHAIN_OPTIMIZE = 0;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_SNAPSHOT = true;
    private static final boolean USE_THREAD = false;
    private boolean flags[];
    protected LinearSystem mBackgroundSystem;
    private ConstraintWidget mChainEnds[];
    private boolean mHeightMeasuredTooSmall;
    private ConstraintWidget mHorizontalChainsArray[];
    private int mHorizontalChainsSize;
    private ConstraintWidget mMatchConstraintsChainedWidgets[];
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    private ConstraintWidget mVerticalChainsArray[];
    private int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;
    int mWrapHeight;
    int mWrapWidth;

    public ConstraintWidgetContainer()
    {
        mSystem = new LinearSystem();
        mBackgroundSystem = null;
        mHorizontalChainsSize = 0;
        mVerticalChainsSize = 0;
        mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        mVerticalChainsArray = new ConstraintWidget[4];
        mHorizontalChainsArray = new ConstraintWidget[4];
        mOptimizationLevel = 2;
        flags = new boolean[3];
        mChainEnds = new ConstraintWidget[4];
        mWidthMeasuredTooSmall = false;
        mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int j)
    {
        super(i, j);
        mSystem = new LinearSystem();
        mBackgroundSystem = null;
        mHorizontalChainsSize = 0;
        mVerticalChainsSize = 0;
        mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        mVerticalChainsArray = new ConstraintWidget[4];
        mHorizontalChainsArray = new ConstraintWidget[4];
        mOptimizationLevel = 2;
        flags = new boolean[3];
        mChainEnds = new ConstraintWidget[4];
        mWidthMeasuredTooSmall = false;
        mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        mSystem = new LinearSystem();
        mBackgroundSystem = null;
        mHorizontalChainsSize = 0;
        mVerticalChainsSize = 0;
        mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        mVerticalChainsArray = new ConstraintWidget[4];
        mHorizontalChainsArray = new ConstraintWidget[4];
        mOptimizationLevel = 2;
        flags = new boolean[3];
        mChainEnds = new ConstraintWidget[4];
        mWidthMeasuredTooSmall = false;
        mHeightMeasuredTooSmall = false;
    }

    private void addHorizontalChain(ConstraintWidget constraintwidget)
    {
        for (int i = 0; i < mHorizontalChainsSize; i++)
        {
            if (mHorizontalChainsArray[i] == constraintwidget)
            {
                return;
            }
        }

        if (mHorizontalChainsSize + 1 >= mHorizontalChainsArray.length)
        {
            mHorizontalChainsArray = (ConstraintWidget[])Arrays.copyOf(mHorizontalChainsArray, mHorizontalChainsArray.length * 2);
        }
        mHorizontalChainsArray[mHorizontalChainsSize] = constraintwidget;
        mHorizontalChainsSize = mHorizontalChainsSize + 1;
    }

    private void addVerticalChain(ConstraintWidget constraintwidget)
    {
        for (int i = 0; i < mVerticalChainsSize; i++)
        {
            if (mVerticalChainsArray[i] == constraintwidget)
            {
                return;
            }
        }

        if (mVerticalChainsSize + 1 >= mVerticalChainsArray.length)
        {
            mVerticalChainsArray = (ConstraintWidget[])Arrays.copyOf(mVerticalChainsArray, mVerticalChainsArray.length * 2);
        }
        mVerticalChainsArray[mVerticalChainsSize] = constraintwidget;
        mVerticalChainsSize = mVerticalChainsSize + 1;
    }

    private void applyHorizontalChain(LinearSystem linearsystem)
    {
        int k1 = 0;
        while (k1 < mHorizontalChainsSize) 
        {
            ConstraintWidget constraintwidget8 = mHorizontalChainsArray[k1];
            int k3 = countMatchConstraintsChainedWidgets(linearsystem, mChainEnds, mHorizontalChainsArray[k1], 0, flags);
            Object obj2 = mChainEnds[2];
            if (obj2 != null)
            {
                if (flags[1])
                {
                    int i = constraintwidget8.getDrawX();
                    while (obj2 != null) 
                    {
                        linearsystem.addEquality(((ConstraintWidget) (obj2)).mLeft.mSolverVariable, i);
                        ConstraintWidget constraintwidget = ((ConstraintWidget) (obj2)).mHorizontalNextWidget;
                        i += ((ConstraintWidget) (obj2)).mLeft.getMargin() + ((ConstraintWidget) (obj2)).getWidth() + ((ConstraintWidget) (obj2)).mRight.getMargin();
                        obj2 = constraintwidget;
                    }
                } else
                {
                    boolean flag;
                    boolean flag1;
                    boolean flag2;
                    if (constraintwidget8.mHorizontalChainStyle == 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (constraintwidget8.mHorizontalChainStyle == 2)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if ((mOptimizationLevel == 2 || mOptimizationLevel == 8) && flags[0] && constraintwidget8.mHorizontalChainFixedPosition && !flag1 && !flag2 && constraintwidget8.mHorizontalChainStyle == 0)
                    {
                        Optimizer.applyDirectResolutionHorizontalChain(this, linearsystem, k3, constraintwidget8);
                    } else
                    if (k3 == 0 || flag1)
                    {
                        ConstraintWidget constraintwidget3 = null;
                        ConstraintWidget constraintwidget6 = null;
                        boolean flag3 = false;
                        Object obj = obj2;
                        do
                        {
                            ConstraintWidget constraintwidget7 = ((ConstraintWidget) (obj));
                            if (constraintwidget7 == null)
                            {
                                break;
                            }
                            ConstraintWidget constraintwidget5 = constraintwidget7.mHorizontalNextWidget;
                            if (constraintwidget5 == null)
                            {
                                constraintwidget6 = mChainEnds[1];
                                flag3 = true;
                            }
                            if (flag1)
                            {
                                obj = constraintwidget7.mLeft;
                                int l3 = ((ConstraintAnchor) (obj)).getMargin();
                                k3 = l3;
                                if (constraintwidget3 != null)
                                {
                                    k3 = l3 + constraintwidget3.mRight.getMargin();
                                }
                                l3 = 1;
                                if (obj2 != constraintwidget7)
                                {
                                    l3 = 3;
                                }
                                linearsystem.addGreaterThan(((ConstraintAnchor) (obj)).mSolverVariable, ((ConstraintAnchor) (obj)).mTarget.mSolverVariable, k3, l3);
                                constraintwidget3 = constraintwidget5;
                                if (constraintwidget7.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    ConstraintAnchor constraintanchor = constraintwidget7.mRight;
                                    if (constraintwidget7.mMatchConstraintDefaultWidth == 1)
                                    {
                                        k3 = Math.max(constraintwidget7.mMatchConstraintMinWidth, constraintwidget7.getWidth());
                                        linearsystem.addEquality(constraintanchor.mSolverVariable, ((ConstraintAnchor) (obj)).mSolverVariable, k3, 3);
                                        constraintwidget3 = constraintwidget5;
                                    } else
                                    {
                                        linearsystem.addGreaterThan(((ConstraintAnchor) (obj)).mSolverVariable, ((ConstraintAnchor) (obj)).mTarget.mSolverVariable, ((ConstraintAnchor) (obj)).mMargin, 3);
                                        linearsystem.addLowerThan(constraintanchor.mSolverVariable, ((ConstraintAnchor) (obj)).mSolverVariable, constraintwidget7.mMatchConstraintMinWidth, 3);
                                        constraintwidget3 = constraintwidget5;
                                    }
                                }
                            } else
                            if (!flag && flag3 && constraintwidget3 != null)
                            {
                                if (constraintwidget7.mRight.mTarget == null)
                                {
                                    linearsystem.addEquality(constraintwidget7.mRight.mSolverVariable, constraintwidget7.getDrawRight());
                                    constraintwidget3 = constraintwidget5;
                                } else
                                {
                                    k3 = constraintwidget7.mRight.getMargin();
                                    linearsystem.addEquality(constraintwidget7.mRight.mSolverVariable, constraintwidget6.mRight.mTarget.mSolverVariable, -k3, 5);
                                    constraintwidget3 = constraintwidget5;
                                }
                            } else
                            if (!flag && !flag3 && constraintwidget3 == null)
                            {
                                if (constraintwidget7.mLeft.mTarget == null)
                                {
                                    linearsystem.addEquality(constraintwidget7.mLeft.mSolverVariable, constraintwidget7.getDrawX());
                                    constraintwidget3 = constraintwidget5;
                                } else
                                {
                                    k3 = constraintwidget7.mLeft.getMargin();
                                    linearsystem.addEquality(constraintwidget7.mLeft.mSolverVariable, constraintwidget8.mLeft.mTarget.mSolverVariable, k3, 5);
                                    constraintwidget3 = constraintwidget5;
                                }
                            } else
                            {
                                ConstraintAnchor constraintanchor3 = constraintwidget7.mLeft;
                                ConstraintAnchor constraintanchor4 = constraintwidget7.mRight;
                                k3 = constraintanchor3.getMargin();
                                int i4 = constraintanchor4.getMargin();
                                linearsystem.addGreaterThan(constraintanchor3.mSolverVariable, constraintanchor3.mTarget.mSolverVariable, k3, 1);
                                linearsystem.addLowerThan(constraintanchor4.mSolverVariable, constraintanchor4.mTarget.mSolverVariable, -i4, 1);
                                android.support.constraint.solver.SolverVariable solvervariable3;
                                if (constraintanchor3.mTarget != null)
                                {
                                    solvervariable3 = constraintanchor3.mTarget.mSolverVariable;
                                } else
                                {
                                    solvervariable3 = null;
                                }
                                if (constraintwidget3 == null)
                                {
                                    if (constraintwidget8.mLeft.mTarget != null)
                                    {
                                        solvervariable3 = constraintwidget8.mLeft.mTarget.mSolverVariable;
                                    } else
                                    {
                                        solvervariable3 = null;
                                    }
                                }
                                obj = constraintwidget5;
                                if (constraintwidget5 == null)
                                {
                                    if (constraintwidget6.mRight.mTarget != null)
                                    {
                                        obj = constraintwidget6.mRight.mTarget.mOwner;
                                    } else
                                    {
                                        obj = null;
                                    }
                                }
                                constraintwidget3 = ((ConstraintWidget) (obj));
                                if (obj != null)
                                {
                                    android.support.constraint.solver.SolverVariable solvervariable4 = ((ConstraintWidget) (obj)).mLeft.mSolverVariable;
                                    if (flag3)
                                    {
                                        if (constraintwidget6.mRight.mTarget != null)
                                        {
                                            solvervariable4 = constraintwidget6.mRight.mTarget.mSolverVariable;
                                        } else
                                        {
                                            solvervariable4 = null;
                                        }
                                    }
                                    constraintwidget3 = ((ConstraintWidget) (obj));
                                    if (solvervariable3 != null)
                                    {
                                        constraintwidget3 = ((ConstraintWidget) (obj));
                                        if (solvervariable4 != null)
                                        {
                                            linearsystem.addCentering(constraintanchor3.mSolverVariable, solvervariable3, k3, 0.5F, solvervariable4, constraintanchor4.mSolverVariable, i4, 4);
                                            constraintwidget3 = ((ConstraintWidget) (obj));
                                        }
                                    }
                                }
                            }
                            if (flag3)
                            {
                                obj = null;
                            } else
                            {
                                obj = constraintwidget3;
                            }
                            constraintwidget3 = constraintwidget7;
                        } while (true);
                        if (flag1)
                        {
                            ConstraintAnchor constraintanchor1 = ((ConstraintWidget) (obj2)).mLeft;
                            ConstraintAnchor constraintanchor2 = constraintwidget6.mRight;
                            int j = constraintanchor1.getMargin();
                            int l1 = constraintanchor2.getMargin();
                            android.support.constraint.solver.SolverVariable solvervariable;
                            if (constraintwidget8.mLeft.mTarget != null)
                            {
                                solvervariable = constraintwidget8.mLeft.mTarget.mSolverVariable;
                            } else
                            {
                                solvervariable = null;
                            }
                            if (constraintwidget6.mRight.mTarget != null)
                            {
                                obj2 = constraintwidget6.mRight.mTarget.mSolverVariable;
                            } else
                            {
                                obj2 = null;
                            }
                            if (solvervariable != null && obj2 != null)
                            {
                                linearsystem.addLowerThan(constraintanchor2.mSolverVariable, ((android.support.constraint.solver.SolverVariable) (obj2)), -l1, 1);
                                linearsystem.addCentering(constraintanchor1.mSolverVariable, solvervariable, j, constraintwidget8.mHorizontalBiasPercent, ((android.support.constraint.solver.SolverVariable) (obj2)), constraintanchor2.mSolverVariable, l1, 4);
                            }
                        }
                    } else
                    {
                        Object obj1 = null;
                        float f = 0.0F;
                        while (obj2 != null) 
                        {
                            if (((ConstraintWidget) (obj2)).mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                            {
                                int i2 = ((ConstraintWidget) (obj2)).mLeft.getMargin();
                                int k = i2;
                                if (obj1 != null)
                                {
                                    k = i2 + ((ConstraintWidget) (obj1)).mRight.getMargin();
                                }
                                i2 = 3;
                                if (((ConstraintWidget) (obj2)).mLeft.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    i2 = 2;
                                }
                                linearsystem.addGreaterThan(((ConstraintWidget) (obj2)).mLeft.mSolverVariable, ((ConstraintWidget) (obj2)).mLeft.mTarget.mSolverVariable, k, i2);
                                i2 = ((ConstraintWidget) (obj2)).mRight.getMargin();
                                k = i2;
                                if (((ConstraintWidget) (obj2)).mRight.mTarget.mOwner.mLeft.mTarget != null)
                                {
                                    k = i2;
                                    if (((ConstraintWidget) (obj2)).mRight.mTarget.mOwner.mLeft.mTarget.mOwner == obj2)
                                    {
                                        k = i2 + ((ConstraintWidget) (obj2)).mRight.mTarget.mOwner.mLeft.getMargin();
                                    }
                                }
                                i2 = 3;
                                if (((ConstraintWidget) (obj2)).mRight.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    i2 = 2;
                                }
                                linearsystem.addLowerThan(((ConstraintWidget) (obj2)).mRight.mSolverVariable, ((ConstraintWidget) (obj2)).mRight.mTarget.mSolverVariable, -k, i2);
                            } else
                            {
                                f += ((ConstraintWidget) (obj2)).mHorizontalWeight;
                                int l = 0;
                                if (((ConstraintWidget) (obj2)).mRight.mTarget != null)
                                {
                                    int j2 = ((ConstraintWidget) (obj2)).mRight.getMargin();
                                    l = j2;
                                    if (obj2 != mChainEnds[3])
                                    {
                                        l = j2 + ((ConstraintWidget) (obj2)).mRight.mTarget.mOwner.mLeft.getMargin();
                                    }
                                }
                                linearsystem.addGreaterThan(((ConstraintWidget) (obj2)).mRight.mSolverVariable, ((ConstraintWidget) (obj2)).mLeft.mSolverVariable, 0, 1);
                                linearsystem.addLowerThan(((ConstraintWidget) (obj2)).mRight.mSolverVariable, ((ConstraintWidget) (obj2)).mRight.mTarget.mSolverVariable, -l, 1);
                            }
                            obj1 = obj2;
                            obj2 = ((ConstraintWidget) (obj2)).mHorizontalNextWidget;
                        }
                        if (k3 == 1)
                        {
                            ConstraintWidget constraintwidget1 = mMatchConstraintsChainedWidgets[0];
                            int k2 = constraintwidget1.mLeft.getMargin();
                            int i1 = k2;
                            if (constraintwidget1.mLeft.mTarget != null)
                            {
                                i1 = k2 + constraintwidget1.mLeft.mTarget.getMargin();
                            }
                            int i3 = constraintwidget1.mRight.getMargin();
                            k2 = i3;
                            if (constraintwidget1.mRight.mTarget != null)
                            {
                                k2 = i3 + constraintwidget1.mRight.mTarget.getMargin();
                            }
                            android.support.constraint.solver.SolverVariable solvervariable1 = constraintwidget8.mRight.mTarget.mSolverVariable;
                            if (constraintwidget1 == mChainEnds[3])
                            {
                                solvervariable1 = mChainEnds[1].mRight.mTarget.mSolverVariable;
                            }
                            if (constraintwidget1.mMatchConstraintDefaultWidth == 1)
                            {
                                linearsystem.addGreaterThan(constraintwidget8.mLeft.mSolverVariable, constraintwidget8.mLeft.mTarget.mSolverVariable, i1, 1);
                                linearsystem.addLowerThan(constraintwidget8.mRight.mSolverVariable, solvervariable1, -k2, 1);
                                linearsystem.addEquality(constraintwidget8.mRight.mSolverVariable, constraintwidget8.mLeft.mSolverVariable, constraintwidget8.getWidth(), 2);
                            } else
                            {
                                linearsystem.addEquality(constraintwidget1.mLeft.mSolverVariable, constraintwidget1.mLeft.mTarget.mSolverVariable, i1, 1);
                                linearsystem.addEquality(constraintwidget1.mRight.mSolverVariable, solvervariable1, -k2, 1);
                            }
                        } else
                        {
                            int j1 = 0;
                            while (j1 < k3 - 1) 
                            {
                                ConstraintWidget constraintwidget2 = mMatchConstraintsChainedWidgets[j1];
                                ConstraintWidget constraintwidget4 = mMatchConstraintsChainedWidgets[j1 + 1];
                                android.support.constraint.solver.SolverVariable solvervariable5 = constraintwidget2.mLeft.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable6 = constraintwidget2.mRight.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable7 = constraintwidget4.mLeft.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable2 = constraintwidget4.mRight.mSolverVariable;
                                if (constraintwidget4 == mChainEnds[3])
                                {
                                    solvervariable2 = mChainEnds[1].mRight.mSolverVariable;
                                }
                                int j3 = constraintwidget2.mLeft.getMargin();
                                int l2 = j3;
                                if (constraintwidget2.mLeft.mTarget != null)
                                {
                                    l2 = j3;
                                    if (constraintwidget2.mLeft.mTarget.mOwner.mRight.mTarget != null)
                                    {
                                        l2 = j3;
                                        if (constraintwidget2.mLeft.mTarget.mOwner.mRight.mTarget.mOwner == constraintwidget2)
                                        {
                                            l2 = j3 + constraintwidget2.mLeft.mTarget.mOwner.mRight.getMargin();
                                        }
                                    }
                                }
                                linearsystem.addGreaterThan(solvervariable5, constraintwidget2.mLeft.mTarget.mSolverVariable, l2, 2);
                                j3 = constraintwidget2.mRight.getMargin();
                                l2 = j3;
                                if (constraintwidget2.mRight.mTarget != null)
                                {
                                    l2 = j3;
                                    if (constraintwidget2.mHorizontalNextWidget != null)
                                    {
                                        Object obj3;
                                        if (constraintwidget2.mHorizontalNextWidget.mLeft.mTarget != null)
                                        {
                                            l2 = constraintwidget2.mHorizontalNextWidget.mLeft.getMargin();
                                        } else
                                        {
                                            l2 = 0;
                                        }
                                        l2 = j3 + l2;
                                    }
                                }
                                linearsystem.addLowerThan(solvervariable6, constraintwidget2.mRight.mTarget.mSolverVariable, -l2, 2);
                                if (j1 + 1 == k3 - 1)
                                {
                                    j3 = constraintwidget4.mLeft.getMargin();
                                    l2 = j3;
                                    if (constraintwidget4.mLeft.mTarget != null)
                                    {
                                        l2 = j3;
                                        if (constraintwidget4.mLeft.mTarget.mOwner.mRight.mTarget != null)
                                        {
                                            l2 = j3;
                                            if (constraintwidget4.mLeft.mTarget.mOwner.mRight.mTarget.mOwner == constraintwidget4)
                                            {
                                                l2 = j3 + constraintwidget4.mLeft.mTarget.mOwner.mRight.getMargin();
                                            }
                                        }
                                    }
                                    linearsystem.addGreaterThan(solvervariable7, constraintwidget4.mLeft.mTarget.mSolverVariable, l2, 2);
                                    obj3 = constraintwidget4.mRight;
                                    if (constraintwidget4 == mChainEnds[3])
                                    {
                                        obj3 = mChainEnds[1].mRight;
                                    }
                                    j3 = ((ConstraintAnchor) (obj3)).getMargin();
                                    l2 = j3;
                                    if (((ConstraintAnchor) (obj3)).mTarget != null)
                                    {
                                        l2 = j3;
                                        if (((ConstraintAnchor) (obj3)).mTarget.mOwner.mLeft.mTarget != null)
                                        {
                                            l2 = j3;
                                            if (((ConstraintAnchor) (obj3)).mTarget.mOwner.mLeft.mTarget.mOwner == constraintwidget4)
                                            {
                                                l2 = j3 + ((ConstraintAnchor) (obj3)).mTarget.mOwner.mLeft.getMargin();
                                            }
                                        }
                                    }
                                    linearsystem.addLowerThan(solvervariable2, ((ConstraintAnchor) (obj3)).mTarget.mSolverVariable, -l2, 2);
                                }
                                if (constraintwidget8.mMatchConstraintMaxWidth > 0)
                                {
                                    linearsystem.addLowerThan(solvervariable6, solvervariable5, constraintwidget8.mMatchConstraintMaxWidth, 2);
                                }
                                obj3 = linearsystem.createRow();
                                ((ArrayRow) (obj3)).createRowEqualDimension(constraintwidget2.mHorizontalWeight, f, constraintwidget4.mHorizontalWeight, solvervariable5, constraintwidget2.mLeft.getMargin(), solvervariable6, constraintwidget2.mRight.getMargin(), solvervariable7, constraintwidget4.mLeft.getMargin(), solvervariable2, constraintwidget4.mRight.getMargin());
                                linearsystem.addConstraint(((ArrayRow) (obj3)));
                                j1++;
                            }
                        }
                    }
                }
            }
            k1++;
        }
    }

    private void applyVerticalChain(LinearSystem linearsystem)
    {
        int k2 = 0;
        while (k2 < mVerticalChainsSize) 
        {
            ConstraintWidget constraintwidget8 = mVerticalChainsArray[k2];
            int k4 = countMatchConstraintsChainedWidgets(linearsystem, mChainEnds, mVerticalChainsArray[k2], 1, flags);
            Object obj2 = mChainEnds[2];
            if (obj2 != null)
            {
                if (flags[1])
                {
                    int i = constraintwidget8.getDrawY();
                    while (obj2 != null) 
                    {
                        linearsystem.addEquality(((ConstraintWidget) (obj2)).mTop.mSolverVariable, i);
                        ConstraintWidget constraintwidget = ((ConstraintWidget) (obj2)).mVerticalNextWidget;
                        i += ((ConstraintWidget) (obj2)).mTop.getMargin() + ((ConstraintWidget) (obj2)).getHeight() + ((ConstraintWidget) (obj2)).mBottom.getMargin();
                        obj2 = constraintwidget;
                    }
                } else
                {
                    boolean flag;
                    boolean flag1;
                    boolean flag2;
                    if (constraintwidget8.mVerticalChainStyle == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (constraintwidget8.mVerticalChainStyle == 2)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if ((mOptimizationLevel == 2 || mOptimizationLevel == 8) && flags[0] && constraintwidget8.mVerticalChainFixedPosition && !flag2 && !flag && constraintwidget8.mVerticalChainStyle == 0)
                    {
                        Optimizer.applyDirectResolutionVerticalChain(this, linearsystem, k4, constraintwidget8);
                    } else
                    if (k4 == 0 || flag2)
                    {
                        ConstraintWidget constraintwidget3 = null;
                        ConstraintWidget constraintwidget6 = null;
                        k4 = 0;
                        Object obj = obj2;
                        do
                        {
                            ConstraintWidget constraintwidget7 = ((ConstraintWidget) (obj));
                            if (constraintwidget7 == null)
                            {
                                break;
                            }
                            ConstraintWidget constraintwidget5 = constraintwidget7.mVerticalNextWidget;
                            if (constraintwidget5 == null)
                            {
                                constraintwidget6 = mChainEnds[1];
                                k4 = 1;
                            }
                            if (flag2)
                            {
                                ConstraintAnchor constraintanchor2 = constraintwidget7.mTop;
                                int l4 = constraintanchor2.getMargin();
                                int j = l4;
                                if (constraintwidget3 != null)
                                {
                                    j = l4 + constraintwidget3.mBottom.getMargin();
                                }
                                l4 = 1;
                                if (obj2 != constraintwidget7)
                                {
                                    l4 = 3;
                                }
                                obj = null;
                                android.support.constraint.solver.SolverVariable solvervariable3 = null;
                                int j5;
                                if (constraintanchor2.mTarget != null)
                                {
                                    obj = constraintanchor2.mSolverVariable;
                                    solvervariable3 = constraintanchor2.mTarget.mSolverVariable;
                                    j5 = j;
                                } else
                                {
                                    j5 = j;
                                    if (constraintwidget7.mBaseline.mTarget != null)
                                    {
                                        obj = constraintwidget7.mBaseline.mSolverVariable;
                                        solvervariable3 = constraintwidget7.mBaseline.mTarget.mSolverVariable;
                                        j5 = j - constraintanchor2.getMargin();
                                    }
                                }
                                if (obj != null && solvervariable3 != null)
                                {
                                    linearsystem.addGreaterThan(((android.support.constraint.solver.SolverVariable) (obj)), solvervariable3, j5, l4);
                                }
                                constraintwidget3 = constraintwidget5;
                                if (constraintwidget7.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    obj = constraintwidget7.mBottom;
                                    if (constraintwidget7.mMatchConstraintDefaultHeight == 1)
                                    {
                                        j = Math.max(constraintwidget7.mMatchConstraintMinHeight, constraintwidget7.getHeight());
                                        linearsystem.addEquality(((ConstraintAnchor) (obj)).mSolverVariable, constraintanchor2.mSolverVariable, j, 3);
                                        constraintwidget3 = constraintwidget5;
                                    } else
                                    {
                                        linearsystem.addGreaterThan(constraintanchor2.mSolverVariable, constraintanchor2.mTarget.mSolverVariable, constraintanchor2.mMargin, 3);
                                        linearsystem.addLowerThan(((ConstraintAnchor) (obj)).mSolverVariable, constraintanchor2.mSolverVariable, constraintwidget7.mMatchConstraintMinHeight, 3);
                                        constraintwidget3 = constraintwidget5;
                                    }
                                }
                            } else
                            if (!flag1 && k4 != 0 && constraintwidget3 != null)
                            {
                                if (constraintwidget7.mBottom.mTarget == null)
                                {
                                    linearsystem.addEquality(constraintwidget7.mBottom.mSolverVariable, constraintwidget7.getDrawBottom());
                                    constraintwidget3 = constraintwidget5;
                                } else
                                {
                                    int k = constraintwidget7.mBottom.getMargin();
                                    linearsystem.addEquality(constraintwidget7.mBottom.mSolverVariable, constraintwidget6.mBottom.mTarget.mSolverVariable, -k, 5);
                                    constraintwidget3 = constraintwidget5;
                                }
                            } else
                            if (!flag1 && k4 == 0 && constraintwidget3 == null)
                            {
                                if (constraintwidget7.mTop.mTarget == null)
                                {
                                    linearsystem.addEquality(constraintwidget7.mTop.mSolverVariable, constraintwidget7.getDrawY());
                                    constraintwidget3 = constraintwidget5;
                                } else
                                {
                                    int l = constraintwidget7.mTop.getMargin();
                                    linearsystem.addEquality(constraintwidget7.mTop.mSolverVariable, constraintwidget8.mTop.mTarget.mSolverVariable, l, 5);
                                    constraintwidget3 = constraintwidget5;
                                }
                            } else
                            {
                                ConstraintAnchor constraintanchor3 = constraintwidget7.mTop;
                                ConstraintAnchor constraintanchor4 = constraintwidget7.mBottom;
                                int i1 = constraintanchor3.getMargin();
                                int i5 = constraintanchor4.getMargin();
                                linearsystem.addGreaterThan(constraintanchor3.mSolverVariable, constraintanchor3.mTarget.mSolverVariable, i1, 1);
                                linearsystem.addLowerThan(constraintanchor4.mSolverVariable, constraintanchor4.mTarget.mSolverVariable, -i5, 1);
                                android.support.constraint.solver.SolverVariable solvervariable4;
                                if (constraintanchor3.mTarget != null)
                                {
                                    solvervariable4 = constraintanchor3.mTarget.mSolverVariable;
                                } else
                                {
                                    solvervariable4 = null;
                                }
                                if (constraintwidget3 == null)
                                {
                                    if (constraintwidget8.mTop.mTarget != null)
                                    {
                                        solvervariable4 = constraintwidget8.mTop.mTarget.mSolverVariable;
                                    } else
                                    {
                                        solvervariable4 = null;
                                    }
                                }
                                obj = constraintwidget5;
                                if (constraintwidget5 == null)
                                {
                                    if (constraintwidget6.mBottom.mTarget != null)
                                    {
                                        obj = constraintwidget6.mBottom.mTarget.mOwner;
                                    } else
                                    {
                                        obj = null;
                                    }
                                }
                                constraintwidget3 = ((ConstraintWidget) (obj));
                                if (obj != null)
                                {
                                    android.support.constraint.solver.SolverVariable solvervariable5 = ((ConstraintWidget) (obj)).mTop.mSolverVariable;
                                    if (k4 != 0)
                                    {
                                        if (constraintwidget6.mBottom.mTarget != null)
                                        {
                                            solvervariable5 = constraintwidget6.mBottom.mTarget.mSolverVariable;
                                        } else
                                        {
                                            solvervariable5 = null;
                                        }
                                    }
                                    constraintwidget3 = ((ConstraintWidget) (obj));
                                    if (solvervariable4 != null)
                                    {
                                        constraintwidget3 = ((ConstraintWidget) (obj));
                                        if (solvervariable5 != null)
                                        {
                                            linearsystem.addCentering(constraintanchor3.mSolverVariable, solvervariable4, i1, 0.5F, solvervariable5, constraintanchor4.mSolverVariable, i5, 4);
                                            constraintwidget3 = ((ConstraintWidget) (obj));
                                        }
                                    }
                                }
                            }
                            if (k4 != 0)
                            {
                                obj = null;
                            } else
                            {
                                obj = constraintwidget3;
                            }
                            constraintwidget3 = constraintwidget7;
                        } while (true);
                        if (flag2)
                        {
                            ConstraintAnchor constraintanchor = ((ConstraintWidget) (obj2)).mTop;
                            ConstraintAnchor constraintanchor1 = constraintwidget6.mBottom;
                            int j1 = constraintanchor.getMargin();
                            int l2 = constraintanchor1.getMargin();
                            android.support.constraint.solver.SolverVariable solvervariable;
                            if (constraintwidget8.mTop.mTarget != null)
                            {
                                solvervariable = constraintwidget8.mTop.mTarget.mSolverVariable;
                            } else
                            {
                                solvervariable = null;
                            }
                            if (constraintwidget6.mBottom.mTarget != null)
                            {
                                obj2 = constraintwidget6.mBottom.mTarget.mSolverVariable;
                            } else
                            {
                                obj2 = null;
                            }
                            if (solvervariable != null && obj2 != null)
                            {
                                linearsystem.addLowerThan(constraintanchor1.mSolverVariable, ((android.support.constraint.solver.SolverVariable) (obj2)), -l2, 1);
                                linearsystem.addCentering(constraintanchor.mSolverVariable, solvervariable, j1, constraintwidget8.mVerticalBiasPercent, ((android.support.constraint.solver.SolverVariable) (obj2)), constraintanchor1.mSolverVariable, l2, 4);
                            }
                        }
                    } else
                    {
                        Object obj1 = null;
                        float f = 0.0F;
                        while (obj2 != null) 
                        {
                            if (((ConstraintWidget) (obj2)).mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                            {
                                int i3 = ((ConstraintWidget) (obj2)).mTop.getMargin();
                                int k1 = i3;
                                if (obj1 != null)
                                {
                                    k1 = i3 + ((ConstraintWidget) (obj1)).mBottom.getMargin();
                                }
                                i3 = 3;
                                if (((ConstraintWidget) (obj2)).mTop.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    i3 = 2;
                                }
                                linearsystem.addGreaterThan(((ConstraintWidget) (obj2)).mTop.mSolverVariable, ((ConstraintWidget) (obj2)).mTop.mTarget.mSolverVariable, k1, i3);
                                i3 = ((ConstraintWidget) (obj2)).mBottom.getMargin();
                                k1 = i3;
                                if (((ConstraintWidget) (obj2)).mBottom.mTarget.mOwner.mTop.mTarget != null)
                                {
                                    k1 = i3;
                                    if (((ConstraintWidget) (obj2)).mBottom.mTarget.mOwner.mTop.mTarget.mOwner == obj2)
                                    {
                                        k1 = i3 + ((ConstraintWidget) (obj2)).mBottom.mTarget.mOwner.mTop.getMargin();
                                    }
                                }
                                i3 = 3;
                                if (((ConstraintWidget) (obj2)).mBottom.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                                {
                                    i3 = 2;
                                }
                                linearsystem.addLowerThan(((ConstraintWidget) (obj2)).mBottom.mSolverVariable, ((ConstraintWidget) (obj2)).mBottom.mTarget.mSolverVariable, -k1, i3);
                            } else
                            {
                                f += ((ConstraintWidget) (obj2)).mVerticalWeight;
                                int l1 = 0;
                                if (((ConstraintWidget) (obj2)).mBottom.mTarget != null)
                                {
                                    int j3 = ((ConstraintWidget) (obj2)).mBottom.getMargin();
                                    l1 = j3;
                                    if (obj2 != mChainEnds[3])
                                    {
                                        l1 = j3 + ((ConstraintWidget) (obj2)).mBottom.mTarget.mOwner.mTop.getMargin();
                                    }
                                }
                                linearsystem.addGreaterThan(((ConstraintWidget) (obj2)).mBottom.mSolverVariable, ((ConstraintWidget) (obj2)).mTop.mSolverVariable, 0, 1);
                                linearsystem.addLowerThan(((ConstraintWidget) (obj2)).mBottom.mSolverVariable, ((ConstraintWidget) (obj2)).mBottom.mTarget.mSolverVariable, -l1, 1);
                            }
                            obj1 = obj2;
                            obj2 = ((ConstraintWidget) (obj2)).mVerticalNextWidget;
                        }
                        if (k4 == 1)
                        {
                            ConstraintWidget constraintwidget1 = mMatchConstraintsChainedWidgets[0];
                            int k3 = constraintwidget1.mTop.getMargin();
                            int i2 = k3;
                            if (constraintwidget1.mTop.mTarget != null)
                            {
                                i2 = k3 + constraintwidget1.mTop.mTarget.getMargin();
                            }
                            int i4 = constraintwidget1.mBottom.getMargin();
                            k3 = i4;
                            if (constraintwidget1.mBottom.mTarget != null)
                            {
                                k3 = i4 + constraintwidget1.mBottom.mTarget.getMargin();
                            }
                            android.support.constraint.solver.SolverVariable solvervariable1 = constraintwidget8.mBottom.mTarget.mSolverVariable;
                            if (constraintwidget1 == mChainEnds[3])
                            {
                                solvervariable1 = mChainEnds[1].mBottom.mTarget.mSolverVariable;
                            }
                            if (constraintwidget1.mMatchConstraintDefaultHeight == 1)
                            {
                                linearsystem.addGreaterThan(constraintwidget8.mTop.mSolverVariable, constraintwidget8.mTop.mTarget.mSolverVariable, i2, 1);
                                linearsystem.addLowerThan(constraintwidget8.mBottom.mSolverVariable, solvervariable1, -k3, 1);
                                linearsystem.addEquality(constraintwidget8.mBottom.mSolverVariable, constraintwidget8.mTop.mSolverVariable, constraintwidget8.getHeight(), 2);
                            } else
                            {
                                linearsystem.addEquality(constraintwidget1.mTop.mSolverVariable, constraintwidget1.mTop.mTarget.mSolverVariable, i2, 1);
                                linearsystem.addEquality(constraintwidget1.mBottom.mSolverVariable, solvervariable1, -k3, 1);
                            }
                        } else
                        {
                            int j2 = 0;
                            while (j2 < k4 - 1) 
                            {
                                ConstraintWidget constraintwidget2 = mMatchConstraintsChainedWidgets[j2];
                                ConstraintWidget constraintwidget4 = mMatchConstraintsChainedWidgets[j2 + 1];
                                android.support.constraint.solver.SolverVariable solvervariable6 = constraintwidget2.mTop.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable7 = constraintwidget2.mBottom.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable8 = constraintwidget4.mTop.mSolverVariable;
                                android.support.constraint.solver.SolverVariable solvervariable2 = constraintwidget4.mBottom.mSolverVariable;
                                if (constraintwidget4 == mChainEnds[3])
                                {
                                    solvervariable2 = mChainEnds[1].mBottom.mSolverVariable;
                                }
                                int j4 = constraintwidget2.mTop.getMargin();
                                int l3 = j4;
                                if (constraintwidget2.mTop.mTarget != null)
                                {
                                    l3 = j4;
                                    if (constraintwidget2.mTop.mTarget.mOwner.mBottom.mTarget != null)
                                    {
                                        l3 = j4;
                                        if (constraintwidget2.mTop.mTarget.mOwner.mBottom.mTarget.mOwner == constraintwidget2)
                                        {
                                            l3 = j4 + constraintwidget2.mTop.mTarget.mOwner.mBottom.getMargin();
                                        }
                                    }
                                }
                                linearsystem.addGreaterThan(solvervariable6, constraintwidget2.mTop.mTarget.mSolverVariable, l3, 2);
                                j4 = constraintwidget2.mBottom.getMargin();
                                l3 = j4;
                                if (constraintwidget2.mBottom.mTarget != null)
                                {
                                    l3 = j4;
                                    if (constraintwidget2.mVerticalNextWidget != null)
                                    {
                                        Object obj3;
                                        if (constraintwidget2.mVerticalNextWidget.mTop.mTarget != null)
                                        {
                                            l3 = constraintwidget2.mVerticalNextWidget.mTop.getMargin();
                                        } else
                                        {
                                            l3 = 0;
                                        }
                                        l3 = j4 + l3;
                                    }
                                }
                                linearsystem.addLowerThan(solvervariable7, constraintwidget2.mBottom.mTarget.mSolverVariable, -l3, 2);
                                if (j2 + 1 == k4 - 1)
                                {
                                    j4 = constraintwidget4.mTop.getMargin();
                                    l3 = j4;
                                    if (constraintwidget4.mTop.mTarget != null)
                                    {
                                        l3 = j4;
                                        if (constraintwidget4.mTop.mTarget.mOwner.mBottom.mTarget != null)
                                        {
                                            l3 = j4;
                                            if (constraintwidget4.mTop.mTarget.mOwner.mBottom.mTarget.mOwner == constraintwidget4)
                                            {
                                                l3 = j4 + constraintwidget4.mTop.mTarget.mOwner.mBottom.getMargin();
                                            }
                                        }
                                    }
                                    linearsystem.addGreaterThan(solvervariable8, constraintwidget4.mTop.mTarget.mSolverVariable, l3, 2);
                                    obj3 = constraintwidget4.mBottom;
                                    if (constraintwidget4 == mChainEnds[3])
                                    {
                                        obj3 = mChainEnds[1].mBottom;
                                    }
                                    j4 = ((ConstraintAnchor) (obj3)).getMargin();
                                    l3 = j4;
                                    if (((ConstraintAnchor) (obj3)).mTarget != null)
                                    {
                                        l3 = j4;
                                        if (((ConstraintAnchor) (obj3)).mTarget.mOwner.mTop.mTarget != null)
                                        {
                                            l3 = j4;
                                            if (((ConstraintAnchor) (obj3)).mTarget.mOwner.mTop.mTarget.mOwner == constraintwidget4)
                                            {
                                                l3 = j4 + ((ConstraintAnchor) (obj3)).mTarget.mOwner.mTop.getMargin();
                                            }
                                        }
                                    }
                                    linearsystem.addLowerThan(solvervariable2, ((ConstraintAnchor) (obj3)).mTarget.mSolverVariable, -l3, 2);
                                }
                                if (constraintwidget8.mMatchConstraintMaxHeight > 0)
                                {
                                    linearsystem.addLowerThan(solvervariable7, solvervariable6, constraintwidget8.mMatchConstraintMaxHeight, 2);
                                }
                                obj3 = linearsystem.createRow();
                                ((ArrayRow) (obj3)).createRowEqualDimension(constraintwidget2.mVerticalWeight, f, constraintwidget4.mVerticalWeight, solvervariable6, constraintwidget2.mTop.getMargin(), solvervariable7, constraintwidget2.mBottom.getMargin(), solvervariable8, constraintwidget4.mTop.getMargin(), solvervariable2, constraintwidget4.mBottom.getMargin());
                                linearsystem.addConstraint(((ArrayRow) (obj3)));
                                j2++;
                            }
                        }
                    }
                }
            }
            k2++;
        }
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem linearsystem, ConstraintWidget aconstraintwidget[], ConstraintWidget constraintwidget, int i, boolean aflag[])
    {
        int j;
        int k;
        k = 0;
        j = 0;
        aflag[0] = true;
        aflag[1] = false;
        aconstraintwidget[0] = null;
        aconstraintwidget[2] = null;
        aconstraintwidget[1] = null;
        aconstraintwidget[3] = null;
        if (i != 0) goto _L2; else goto _L1
_L1:
        ConstraintWidget constraintwidget1;
        ConstraintWidget constraintwidget2;
        ConstraintWidget constraintwidget3;
        ConstraintWidget constraintwidget5;
        boolean flag;
        boolean flag1 = true;
        constraintwidget5 = null;
        flag = flag1;
        if (constraintwidget.mLeft.mTarget != null)
        {
            flag = flag1;
            if (constraintwidget.mLeft.mTarget.mOwner != this)
            {
                flag = false;
            }
        }
        constraintwidget.mHorizontalNextWidget = null;
        constraintwidget1 = null;
        if (constraintwidget.getVisibility() != 8)
        {
            constraintwidget1 = constraintwidget;
        }
        constraintwidget2 = constraintwidget1;
        constraintwidget3 = constraintwidget;
        i = j;
_L9:
        ConstraintWidget constraintwidget4;
        ConstraintWidget constraintwidget6;
        k = i;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mRight.mTarget == null) goto _L4; else goto _L3
_L3:
        constraintwidget3.mHorizontalNextWidget = null;
        boolean flag2;
        if (constraintwidget3.getVisibility() != 8)
        {
            constraintwidget4 = constraintwidget1;
            if (constraintwidget1 == null)
            {
                constraintwidget4 = constraintwidget3;
            }
            if (constraintwidget2 != null && constraintwidget2 != constraintwidget3)
            {
                constraintwidget2.mHorizontalNextWidget = constraintwidget3;
            }
            constraintwidget2 = constraintwidget3;
            constraintwidget1 = constraintwidget4;
        } else
        {
            linearsystem.addEquality(constraintwidget3.mLeft.mSolverVariable, constraintwidget3.mLeft.mTarget.mSolverVariable, 0, 5);
            linearsystem.addEquality(constraintwidget3.mRight.mSolverVariable, constraintwidget3.mLeft.mSolverVariable, 0, 5);
        }
        j = i;
        if (constraintwidget3.getVisibility() != 8)
        {
            j = i;
            if (constraintwidget3.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
            {
                if (constraintwidget3.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    aflag[0] = false;
                }
                j = i;
                if (constraintwidget3.mDimensionRatio <= 0.0F)
                {
                    aflag[0] = false;
                    if (i + 1 >= mMatchConstraintsChainedWidgets.length)
                    {
                        mMatchConstraintsChainedWidgets = (ConstraintWidget[])Arrays.copyOf(mMatchConstraintsChainedWidgets, mMatchConstraintsChainedWidgets.length * 2);
                    }
                    mMatchConstraintsChainedWidgets[i] = constraintwidget3;
                    j = i + 1;
                }
            }
        }
        if (constraintwidget3.mRight.mTarget.mOwner.mLeft.mTarget != null) goto _L6; else goto _L5
_L5:
        constraintwidget6 = constraintwidget2;
        constraintwidget4 = constraintwidget1;
        k = j;
_L4:
        flag2 = flag;
        if (constraintwidget3.mRight.mTarget != null)
        {
            flag2 = flag;
            if (constraintwidget3.mRight.mTarget.mOwner != this)
            {
                flag2 = false;
            }
        }
        if (constraintwidget.mLeft.mTarget == null || constraintwidget5.mRight.mTarget == null)
        {
            aflag[1] = true;
        }
        constraintwidget.mHorizontalChainFixedPosition = flag2;
        constraintwidget5.mHorizontalNextWidget = null;
        aconstraintwidget[0] = constraintwidget;
        aconstraintwidget[2] = constraintwidget4;
        aconstraintwidget[1] = constraintwidget5;
        aconstraintwidget[3] = constraintwidget6;
        return k;
_L6:
        k = j;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintwidget3) goto _L4; else goto _L7
_L7:
        k = j;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mRight.mTarget.mOwner == constraintwidget3) goto _L4; else goto _L8
_L8:
        constraintwidget3 = constraintwidget3.mRight.mTarget.mOwner;
        constraintwidget5 = constraintwidget3;
        i = j;
        if (true) goto _L9; else goto _L2
_L2:
        boolean flag3 = true;
        constraintwidget5 = null;
        flag = flag3;
        if (constraintwidget.mTop.mTarget != null)
        {
            flag = flag3;
            if (constraintwidget.mTop.mTarget.mOwner != this)
            {
                flag = false;
            }
        }
        constraintwidget.mVerticalNextWidget = null;
        constraintwidget1 = null;
        if (constraintwidget.getVisibility() != 8)
        {
            constraintwidget1 = constraintwidget;
        }
        constraintwidget2 = constraintwidget1;
        constraintwidget3 = constraintwidget;
        i = k;
_L17:
        k = i;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mBottom.mTarget == null) goto _L11; else goto _L10
_L10:
        constraintwidget3.mVerticalNextWidget = null;
        boolean flag4;
        if (constraintwidget3.getVisibility() != 8)
        {
            constraintwidget4 = constraintwidget1;
            if (constraintwidget1 == null)
            {
                constraintwidget4 = constraintwidget3;
            }
            if (constraintwidget2 != null && constraintwidget2 != constraintwidget3)
            {
                constraintwidget2.mVerticalNextWidget = constraintwidget3;
            }
            constraintwidget2 = constraintwidget3;
            constraintwidget1 = constraintwidget4;
        } else
        {
            linearsystem.addEquality(constraintwidget3.mTop.mSolverVariable, constraintwidget3.mTop.mTarget.mSolverVariable, 0, 5);
            linearsystem.addEquality(constraintwidget3.mBottom.mSolverVariable, constraintwidget3.mTop.mSolverVariable, 0, 5);
        }
        j = i;
        if (constraintwidget3.getVisibility() != 8)
        {
            j = i;
            if (constraintwidget3.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
            {
                if (constraintwidget3.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    aflag[0] = false;
                }
                j = i;
                if (constraintwidget3.mDimensionRatio <= 0.0F)
                {
                    aflag[0] = false;
                    if (i + 1 >= mMatchConstraintsChainedWidgets.length)
                    {
                        mMatchConstraintsChainedWidgets = (ConstraintWidget[])Arrays.copyOf(mMatchConstraintsChainedWidgets, mMatchConstraintsChainedWidgets.length * 2);
                    }
                    mMatchConstraintsChainedWidgets[i] = constraintwidget3;
                    j = i + 1;
                }
            }
        }
        if (constraintwidget3.mBottom.mTarget.mOwner.mTop.mTarget != null) goto _L13; else goto _L12
_L12:
        constraintwidget6 = constraintwidget2;
        constraintwidget4 = constraintwidget1;
        k = j;
_L11:
        flag4 = flag;
        if (constraintwidget3.mBottom.mTarget != null)
        {
            flag4 = flag;
            if (constraintwidget3.mBottom.mTarget.mOwner != this)
            {
                flag4 = false;
            }
        }
        if (constraintwidget.mTop.mTarget == null || constraintwidget5.mBottom.mTarget == null)
        {
            aflag[1] = true;
        }
        constraintwidget.mVerticalChainFixedPosition = flag4;
        constraintwidget5.mVerticalNextWidget = null;
        aconstraintwidget[0] = constraintwidget;
        aconstraintwidget[2] = constraintwidget4;
        aconstraintwidget[1] = constraintwidget5;
        aconstraintwidget[3] = constraintwidget6;
        return k;
_L13:
        k = j;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintwidget3) goto _L11; else goto _L14
_L14:
        k = j;
        constraintwidget4 = constraintwidget1;
        constraintwidget6 = constraintwidget2;
        if (constraintwidget3.mBottom.mTarget.mOwner == constraintwidget3) goto _L11; else goto _L15
_L15:
        constraintwidget3 = constraintwidget3.mBottom.mTarget.mOwner;
        constraintwidget5 = constraintwidget3;
        i = j;
        if (true) goto _L17; else goto _L16
_L16:
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer constraintwidgetcontainer, String s, ArrayList arraylist, int i)
    {
        Rectangle rectangle = getBounds(arraylist);
        if (rectangle.width != 0 && rectangle.height != 0) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        if (i > 0)
        {
            int l = Math.min(rectangle.x, rectangle.y);
            int j = i;
            if (i > l)
            {
                j = l;
            }
            rectangle.grow(j, j);
        }
        constraintwidgetcontainer.setOrigin(rectangle.x, rectangle.y);
        constraintwidgetcontainer.setDimension(rectangle.width, rectangle.height);
        constraintwidgetcontainer.setDebugName(s);
        ConstraintWidget constraintwidget = ((ConstraintWidget)arraylist.get(0)).getParent();
        i = 0;
        int k = arraylist.size();
        do
        {
            s = constraintwidgetcontainer;
            if (i >= k)
            {
                continue;
            }
            s = (ConstraintWidget)arraylist.get(i);
            if (s.getParent() == constraintwidget)
            {
                constraintwidgetcontainer.add(s);
                s.setX(s.getX() - rectangle.x);
                s.setY(s.getY() - rectangle.y);
            }
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private boolean optimize(LinearSystem linearsystem)
    {
        boolean flag;
        int k1;
        int i2;
        int k2;
        int k3;
        k3 = mChildren.size();
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        int j = 0;
        do
        {
            k1 = ((flag3) ? 1 : 0);
            flag = flag1;
            i2 = ((flag2) ? 1 : 0);
            k2 = ((flag4) ? 1 : 0);
            if (j >= k3)
            {
                break;
            }
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(j);
            constraintwidget.mHorizontalResolution = -1;
            constraintwidget.mVerticalResolution = -1;
            if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
            {
                constraintwidget.mHorizontalResolution = 1;
                constraintwidget.mVerticalResolution = 1;
            }
            j++;
        } while (true);
          goto _L1
_L6:
        int k;
        int i1;
        if (i1 != 0 || k != 0) goto _L3; else goto _L2
_L2:
        int l2;
        flag = true;
        k2 = l2;
        i2 = i1;
        k1 = k;
_L1:
        int i3;
        int j3;
        j3 = i2;
        i3 = k1;
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = 0;
        k = 0;
        l2 = k2 + 1;
        k1 = 0;
        while (k1 < k3) 
        {
            ConstraintWidget constraintwidget1 = (ConstraintWidget)mChildren.get(k1);
            if (constraintwidget1.mHorizontalResolution == -1)
            {
                if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget1.mHorizontalResolution = 1;
                } else
                {
                    Optimizer.checkHorizontalSimpleDependency(this, linearsystem, constraintwidget1);
                }
            }
            if (constraintwidget1.mVerticalResolution == -1)
            {
                if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget1.mVerticalResolution = 1;
                } else
                {
                    Optimizer.checkVerticalSimpleDependency(this, linearsystem, constraintwidget1);
                }
            }
            i2 = i1;
            if (constraintwidget1.mVerticalResolution == -1)
            {
                i2 = i1 + 1;
            }
            i1 = k;
            if (constraintwidget1.mHorizontalResolution == -1)
            {
                i1 = k + 1;
            }
            k1++;
            k = i1;
            i1 = i2;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        k1 = k;
        i2 = i1;
        k2 = l2;
        if (j3 == i1)
        {
            k1 = k;
            i2 = i1;
            k2 = l2;
            if (i3 == k)
            {
                flag = true;
                k1 = k;
                i2 = i1;
                k2 = l2;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
        int l1 = 0;
        int i = 0;
        for (int l = 0; l < k3;)
        {
            int j1;
label0:
            {
                linearsystem = (ConstraintWidget)mChildren.get(l);
                if (((ConstraintWidget) (linearsystem)).mHorizontalResolution != 1)
                {
                    j1 = l1;
                    if (((ConstraintWidget) (linearsystem)).mHorizontalResolution != -1)
                    {
                        break label0;
                    }
                }
                j1 = l1 + 1;
            }
            int j2;
label1:
            {
                if (((ConstraintWidget) (linearsystem)).mVerticalResolution != 1)
                {
                    j2 = i;
                    if (((ConstraintWidget) (linearsystem)).mVerticalResolution != -1)
                    {
                        break label1;
                    }
                }
                j2 = i + 1;
            }
            l++;
            l1 = j1;
            i = j2;
        }

        if (l1 == 0 && i == 0)
        {
            return true;
        }
        return false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private void resetChains()
    {
        mHorizontalChainsSize = 0;
        mVerticalChainsSize = 0;
    }

    static int setGroup(ConstraintAnchor constraintanchor, int i)
    {
        int l = constraintanchor.mGroup;
        int j;
        if (constraintanchor.mOwner.getParent() == null)
        {
            j = i;
        } else
        {
            j = l;
            if (l > i)
            {
                constraintanchor.mGroup = i;
                ConstraintAnchor constraintanchor1 = constraintanchor.getOpposite();
                ConstraintAnchor constraintanchor2 = constraintanchor.mTarget;
                int k = i;
                if (constraintanchor1 != null)
                {
                    k = setGroup(constraintanchor1, i);
                }
                i = k;
                if (constraintanchor2 != null)
                {
                    i = setGroup(constraintanchor2, k);
                }
                k = i;
                if (constraintanchor1 != null)
                {
                    k = setGroup(constraintanchor1, i);
                }
                constraintanchor.mGroup = k;
                return k;
            }
        }
        return j;
    }

    void addChain(ConstraintWidget constraintwidget, int i)
    {
        if (i == 0)
        {
            for (; constraintwidget.mLeft.mTarget != null && constraintwidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintwidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintwidget.mLeft && constraintwidget.mLeft.mTarget.mOwner != constraintwidget; constraintwidget = constraintwidget.mLeft.mTarget.mOwner) { }
            addHorizontalChain(constraintwidget);
        } else
        if (i == 1)
        {
            for (; constraintwidget.mTop.mTarget != null && constraintwidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintwidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintwidget.mTop && constraintwidget.mTop.mTarget.mOwner != constraintwidget; constraintwidget = constraintwidget.mTop.mTarget.mOwner) { }
            addVerticalChain(constraintwidget);
            return;
        }
    }

    public boolean addChildrenToSolver(LinearSystem linearsystem, int i)
    {
        addToSolver(linearsystem, i);
        int k = mChildren.size();
        boolean flag = false;
        if (mOptimizationLevel == 2 || mOptimizationLevel == 4)
        {
            if (optimize(linearsystem))
            {
                return false;
            }
        } else
        {
            flag = true;
        }
        int j = 0;
        while (j < k) 
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(j);
            if (constraintwidget instanceof ConstraintWidgetContainer)
            {
                ConstraintWidget.DimensionBehaviour dimensionbehaviour = constraintwidget.mHorizontalDimensionBehaviour;
                ConstraintWidget.DimensionBehaviour dimensionbehaviour1 = constraintwidget.mVerticalDimensionBehaviour;
                if (dimensionbehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionbehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintwidget.addToSolver(linearsystem, i);
                if (dimensionbehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget.setHorizontalDimensionBehaviour(dimensionbehaviour);
                }
                if (dimensionbehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    constraintwidget.setVerticalDimensionBehaviour(dimensionbehaviour1);
                }
            } else
            {
                if (flag)
                {
                    Optimizer.checkMatchParent(this, linearsystem, constraintwidget);
                }
                constraintwidget.addToSolver(linearsystem, i);
            }
            j++;
        }
        if (mHorizontalChainsSize > 0)
        {
            applyHorizontalChain(linearsystem);
        }
        if (mVerticalChainsSize > 0)
        {
            applyVerticalChain(linearsystem);
        }
        return true;
    }

    public void findHorizontalWrapRecursive(ConstraintWidget constraintwidget, boolean aflag[])
    {
        ConstraintWidget constraintwidget1;
        ConstraintWidget constraintwidget2;
        int j;
        int l;
        boolean flag1;
        flag1 = false;
        if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mDimensionRatio > 0.0F)
        {
            aflag[0] = false;
            return;
        }
        l = constraintwidget.getOptimizerWrapWidth();
        if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mDimensionRatio > 0.0F)
        {
            aflag[0] = false;
            return;
        }
        j = l;
        constraintwidget2 = null;
        constraintwidget1 = null;
        constraintwidget.mHorizontalWrapVisited = true;
        if (!(constraintwidget instanceof Guideline)) goto _L2; else goto _L1
_L1:
        int i;
        aflag = (Guideline)constraintwidget;
        i = l;
        if (aflag.getOrientation() != 1) goto _L4; else goto _L3
_L3:
        int k;
        k = 0;
        j = 0;
        if (aflag.getRelativeBegin() == -1) goto _L6; else goto _L5
_L5:
        i = aflag.getRelativeBegin();
_L4:
        l = i;
        k = j;
        if (constraintwidget.getVisibility() == 8)
        {
            l = i - constraintwidget.mWidth;
            k = j - constraintwidget.mWidth;
        }
        constraintwidget.mDistToLeft = l;
        constraintwidget.mDistToRight = k;
        return;
_L6:
        i = k;
        if (aflag.getRelativeEnd() != -1)
        {
            j = aflag.getRelativeEnd();
            i = k;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (constraintwidget.mRight.isConnected() || constraintwidget.mLeft.isConnected())
        {
            break; /* Loop/switch isn't completed */
        }
        i = l + constraintwidget.getX();
        if (true) goto _L4; else goto _L7
_L7:
        if (constraintwidget.mRight.mTarget != null && constraintwidget.mLeft.mTarget != null && (constraintwidget.mRight.mTarget == constraintwidget.mLeft.mTarget || constraintwidget.mRight.mTarget.mOwner == constraintwidget.mLeft.mTarget.mOwner && constraintwidget.mRight.mTarget.mOwner != constraintwidget.mParent))
        {
            aflag[0] = false;
            return;
        }
        i = j;
        if (constraintwidget.mRight.mTarget != null)
        {
            ConstraintWidget constraintwidget3 = constraintwidget.mRight.mTarget.mOwner;
            j += constraintwidget.mRight.getMargin();
            i = j;
            constraintwidget1 = constraintwidget3;
            if (!constraintwidget3.isRoot())
            {
                i = j;
                constraintwidget1 = constraintwidget3;
                if (!constraintwidget3.mHorizontalWrapVisited)
                {
                    findHorizontalWrapRecursive(constraintwidget3, aflag);
                    constraintwidget1 = constraintwidget3;
                    i = j;
                }
            }
        }
        k = l;
        if (constraintwidget.mLeft.mTarget != null)
        {
            ConstraintWidget constraintwidget4 = constraintwidget.mLeft.mTarget.mOwner;
            j = l + constraintwidget.mLeft.getMargin();
            k = j;
            constraintwidget2 = constraintwidget4;
            if (!constraintwidget4.isRoot())
            {
                k = j;
                constraintwidget2 = constraintwidget4;
                if (!constraintwidget4.mHorizontalWrapVisited)
                {
                    findHorizontalWrapRecursive(constraintwidget4, aflag);
                    constraintwidget2 = constraintwidget4;
                    k = j;
                }
            }
        }
        l = i;
        if (constraintwidget.mRight.mTarget == null) goto _L9; else goto _L8
_L8:
        l = i;
        if (constraintwidget1.isRoot()) goto _L9; else goto _L10
_L10:
        int i1;
        boolean flag;
        if (constraintwidget.mRight.mTarget.mType == ConstraintAnchor.Type.RIGHT)
        {
            j = i + (constraintwidget1.mDistToRight - constraintwidget1.getOptimizerWrapWidth());
        } else
        {
            j = i;
            if (constraintwidget.mRight.mTarget.getType() == ConstraintAnchor.Type.LEFT)
            {
                j = i + constraintwidget1.mDistToRight;
            }
        }
        if (constraintwidget1.mRightHasCentered || constraintwidget1.mLeft.mTarget != null && constraintwidget1.mRight.mTarget != null && constraintwidget1.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        constraintwidget.mRightHasCentered = flag;
        l = j;
        if (!constraintwidget.mRightHasCentered) goto _L9; else goto _L11
_L11:
        if (constraintwidget1.mLeft.mTarget != null) goto _L13; else goto _L12
_L12:
        l = j + (j - constraintwidget1.mDistToRight);
_L9:
        i = k;
        j = l;
        if (constraintwidget.mLeft.mTarget == null) goto _L4; else goto _L14
_L14:
        i = k;
        j = l;
        if (constraintwidget2.isRoot()) goto _L4; else goto _L15
_L15:
label0:
        {
            if (constraintwidget.mLeft.mTarget.getType() == ConstraintAnchor.Type.LEFT)
            {
                i1 = k + (constraintwidget2.mDistToLeft - constraintwidget2.getOptimizerWrapWidth());
            } else
            {
                i1 = k;
                if (constraintwidget.mLeft.mTarget.getType() == ConstraintAnchor.Type.RIGHT)
                {
                    i1 = k + constraintwidget2.mDistToLeft;
                }
            }
            if (!constraintwidget2.mLeftHasCentered)
            {
                flag = flag1;
                if (constraintwidget2.mLeft.mTarget == null)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mRight.mTarget == null)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    break label0;
                }
            }
            flag = true;
        }
        constraintwidget.mLeftHasCentered = flag;
        i = i1;
        j = l;
        if (!constraintwidget.mLeftHasCentered) goto _L4; else goto _L16
_L16:
        if (constraintwidget2.mRight.mTarget != null) goto _L18; else goto _L17
_L17:
        i = i1 + (i1 - constraintwidget2.mDistToLeft);
        j = l;
        break; /* Loop/switch isn't completed */
_L13:
        l = j;
        if (constraintwidget1.mLeft.mTarget.mOwner == constraintwidget) goto _L9; else goto _L12
_L18:
        i = i1;
        j = l;
        if (constraintwidget2.mRight.mTarget.mOwner == constraintwidget) goto _L4; else goto _L17
    }

    public void findVerticalWrapRecursive(ConstraintWidget constraintwidget, boolean aflag[])
    {
        ConstraintWidget constraintwidget1;
        ConstraintWidget constraintwidget2;
        int i;
        int j;
        int k;
        boolean flag1;
        flag1 = false;
        if (constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.mDimensionRatio > 0.0F)
        {
            aflag[0] = false;
            return;
        }
        i = constraintwidget.getOptimizerWrapHeight();
        k = i;
        j = i;
        constraintwidget1 = null;
        constraintwidget2 = null;
        constraintwidget.mVerticalWrapVisited = true;
        if (!(constraintwidget instanceof Guideline)) goto _L2; else goto _L1
_L1:
        aflag = (Guideline)constraintwidget;
        i = j;
        j = k;
        if (aflag.getOrientation() != 0) goto _L4; else goto _L3
_L3:
        k = 0;
        i = 0;
        if (aflag.getRelativeBegin() == -1) goto _L6; else goto _L5
_L5:
        j = aflag.getRelativeBegin();
_L4:
        int l = i;
        k = j;
        if (constraintwidget.getVisibility() == 8)
        {
            k = j - constraintwidget.mHeight;
            l = i - constraintwidget.mHeight;
        }
        constraintwidget.mDistToTop = k;
        constraintwidget.mDistToBottom = l;
        return;
_L6:
        j = k;
        if (aflag.getRelativeEnd() != -1)
        {
            i = aflag.getRelativeEnd();
            j = k;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (constraintwidget.mBaseline.mTarget != null || constraintwidget.mTop.mTarget != null || constraintwidget.mBottom.mTarget != null)
        {
            break; /* Loop/switch isn't completed */
        }
        k += constraintwidget.getY();
        i = j;
        j = k;
        if (true) goto _L4; else goto _L7
_L7:
        int j1;
        if (constraintwidget.mBottom.mTarget != null && constraintwidget.mTop.mTarget != null && (constraintwidget.mBottom.mTarget == constraintwidget.mTop.mTarget || constraintwidget.mBottom.mTarget.mOwner == constraintwidget.mTop.mTarget.mOwner && constraintwidget.mBottom.mTarget.mOwner != constraintwidget.mParent))
        {
            aflag[0] = false;
            return;
        }
        if (constraintwidget.mBaseline.isConnected())
        {
            constraintwidget1 = constraintwidget.mBaseline.mTarget.getOwner();
            if (!constraintwidget1.mVerticalWrapVisited)
            {
                findVerticalWrapRecursive(constraintwidget1, aflag);
            }
            k = Math.max((constraintwidget1.mDistToTop - constraintwidget1.mHeight) + i, i);
            int i1 = Math.max((constraintwidget1.mDistToBottom - constraintwidget1.mHeight) + i, i);
            j = i1;
            i = k;
            if (constraintwidget.getVisibility() == 8)
            {
                i = k - constraintwidget.mHeight;
                j = i1 - constraintwidget.mHeight;
            }
            constraintwidget.mDistToTop = i;
            constraintwidget.mDistToBottom = j;
            return;
        }
        i = k;
        if (constraintwidget.mTop.isConnected())
        {
            ConstraintWidget constraintwidget3 = constraintwidget.mTop.mTarget.getOwner();
            k += constraintwidget.mTop.getMargin();
            i = k;
            constraintwidget1 = constraintwidget3;
            if (!constraintwidget3.isRoot())
            {
                i = k;
                constraintwidget1 = constraintwidget3;
                if (!constraintwidget3.mVerticalWrapVisited)
                {
                    findVerticalWrapRecursive(constraintwidget3, aflag);
                    constraintwidget1 = constraintwidget3;
                    i = k;
                }
            }
        }
        k = j;
        if (constraintwidget.mBottom.isConnected())
        {
            ConstraintWidget constraintwidget4 = constraintwidget.mBottom.mTarget.getOwner();
            j += constraintwidget.mBottom.getMargin();
            constraintwidget2 = constraintwidget4;
            k = j;
            if (!constraintwidget4.isRoot())
            {
                constraintwidget2 = constraintwidget4;
                k = j;
                if (!constraintwidget4.mVerticalWrapVisited)
                {
                    findVerticalWrapRecursive(constraintwidget4, aflag);
                    k = j;
                    constraintwidget2 = constraintwidget4;
                }
            }
        }
        j1 = i;
        if (constraintwidget.mTop.mTarget == null) goto _L9; else goto _L8
_L8:
        j1 = i;
        if (constraintwidget1.isRoot()) goto _L9; else goto _L10
_L10:
        int k1;
        boolean flag;
        if (constraintwidget.mTop.mTarget.getType() == ConstraintAnchor.Type.TOP)
        {
            j = i + (constraintwidget1.mDistToTop - constraintwidget1.getOptimizerWrapHeight());
        } else
        {
            j = i;
            if (constraintwidget.mTop.mTarget.getType() == ConstraintAnchor.Type.BOTTOM)
            {
                j = i + constraintwidget1.mDistToTop;
            }
        }
        if (constraintwidget1.mTopHasCentered || constraintwidget1.mTop.mTarget != null && constraintwidget1.mTop.mTarget.mOwner != constraintwidget && constraintwidget1.mBottom.mTarget != null && constraintwidget1.mBottom.mTarget.mOwner != constraintwidget && constraintwidget1.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        constraintwidget.mTopHasCentered = flag;
        j1 = j;
        if (!constraintwidget.mTopHasCentered) goto _L9; else goto _L11
_L11:
        if (constraintwidget1.mBottom.mTarget != null) goto _L13; else goto _L12
_L12:
        j1 = j + (j - constraintwidget1.mDistToTop);
_L9:
        i = k;
        j = j1;
        if (constraintwidget.mBottom.mTarget == null) goto _L4; else goto _L14
_L14:
        i = k;
        j = j1;
        if (constraintwidget2.isRoot()) goto _L4; else goto _L15
_L15:
label0:
        {
            if (constraintwidget.mBottom.mTarget.getType() == ConstraintAnchor.Type.BOTTOM)
            {
                k1 = k + (constraintwidget2.mDistToBottom - constraintwidget2.getOptimizerWrapHeight());
            } else
            {
                k1 = k;
                if (constraintwidget.mBottom.mTarget.getType() == ConstraintAnchor.Type.TOP)
                {
                    k1 = k + constraintwidget2.mDistToBottom;
                }
            }
            if (!constraintwidget2.mBottomHasCentered)
            {
                flag = flag1;
                if (constraintwidget2.mTop.mTarget == null)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mTop.mTarget.mOwner == constraintwidget)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mBottom.mTarget == null)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mBottom.mTarget.mOwner == constraintwidget)
                {
                    break label0;
                }
                flag = flag1;
                if (constraintwidget2.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    break label0;
                }
            }
            flag = true;
        }
        constraintwidget.mBottomHasCentered = flag;
        i = k1;
        j = j1;
        if (!constraintwidget.mBottomHasCentered) goto _L4; else goto _L16
_L16:
        if (constraintwidget2.mTop.mTarget != null) goto _L18; else goto _L17
_L17:
        i = k1 + (k1 - constraintwidget2.mDistToBottom);
        j = j1;
        break; /* Loop/switch isn't completed */
_L13:
        j1 = j;
        if (constraintwidget1.mBottom.mTarget.mOwner == constraintwidget) goto _L9; else goto _L12
_L18:
        i = k1;
        j = j1;
        if (constraintwidget2.mTop.mTarget.mOwner == constraintwidget) goto _L4; else goto _L17
    }

    public void findWrapSize(ArrayList arraylist, boolean aflag[])
    {
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        k1 = 0;
        j2 = 0;
        i2 = 0;
        l1 = 0;
        j1 = 0;
        l = 0;
        k2 = arraylist.size();
        aflag[0] = true;
        i1 = 0;
_L2:
        ConstraintWidget constraintwidget;
        if (i1 >= k2)
        {
            break MISSING_BLOCK_LABEL_273;
        }
        constraintwidget = (ConstraintWidget)arraylist.get(i1);
        if (!constraintwidget.isRoot())
        {
            break; /* Loop/switch isn't completed */
        }
_L5:
        i1++;
        if (true) goto _L2; else goto _L1
_L1:
        if (!constraintwidget.mHorizontalWrapVisited)
        {
            findHorizontalWrapRecursive(constraintwidget, aflag);
        }
        if (!constraintwidget.mVerticalWrapVisited)
        {
            findVerticalWrapRecursive(constraintwidget, aflag);
        }
        if (aflag[0]) goto _L4; else goto _L3
_L3:
        return;
_L4:
        int i = (constraintwidget.mDistToLeft + constraintwidget.mDistToRight) - constraintwidget.getWidth();
        int k = (constraintwidget.mDistToTop + constraintwidget.mDistToBottom) - constraintwidget.getHeight();
        if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
        {
            i = constraintwidget.getWidth() + constraintwidget.mLeft.mMargin + constraintwidget.mRight.mMargin;
        }
        if (constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
        {
            k = constraintwidget.getHeight() + constraintwidget.mTop.mMargin + constraintwidget.mBottom.mMargin;
        }
        if (constraintwidget.getVisibility() == 8)
        {
            i = 0;
            k = 0;
        }
        j2 = Math.max(j2, constraintwidget.mDistToLeft);
        i2 = Math.max(i2, constraintwidget.mDistToRight);
        l1 = Math.max(l1, constraintwidget.mDistToBottom);
        k1 = Math.max(k1, constraintwidget.mDistToTop);
        j1 = Math.max(j1, i);
        l = Math.max(l, k);
          goto _L5
        int j = Math.max(j2, i2);
        mWrapWidth = Math.max(mMinWidth, Math.max(j, j1));
        j = Math.max(k1, l1);
        mWrapHeight = Math.max(mMinHeight, Math.max(j, l));
        j = 0;
        while (j < k2) 
        {
            aflag = (ConstraintWidget)arraylist.get(j);
            aflag.mHorizontalWrapVisited = false;
            aflag.mVerticalWrapVisited = false;
            aflag.mLeftHasCentered = false;
            aflag.mRightHasCentered = false;
            aflag.mTopHasCentered = false;
            aflag.mBottomHasCentered = false;
            j++;
        }
          goto _L3
    }

    public ArrayList getHorizontalGuidelines()
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        for (int j = mChildren.size(); i < j; i++)
        {
            Object obj = (ConstraintWidget)mChildren.get(i);
            if (!(obj instanceof Guideline))
            {
                continue;
            }
            obj = (Guideline)obj;
            if (((Guideline) (obj)).getOrientation() == 0)
            {
                arraylist.add(obj);
            }
        }

        return arraylist;
    }

    public LinearSystem getSystem()
    {
        return mSystem;
    }

    public String getType()
    {
        return "ConstraintLayout";
    }

    public ArrayList getVerticalGuidelines()
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        for (int j = mChildren.size(); i < j; i++)
        {
            Object obj = (ConstraintWidget)mChildren.get(i);
            if (!(obj instanceof Guideline))
            {
                continue;
            }
            obj = (Guideline)obj;
            if (((Guideline) (obj)).getOrientation() == 1)
            {
                arraylist.add(obj);
            }
        }

        return arraylist;
    }

    public boolean handlesInternalConstraints()
    {
        return false;
    }

    public boolean isHeightMeasuredTooSmall()
    {
        return mHeightMeasuredTooSmall;
    }

    public boolean isWidthMeasuredTooSmall()
    {
        return mWidthMeasuredTooSmall;
    }

    public void layout()
    {
        ConstraintWidget.DimensionBehaviour dimensionbehaviour;
        ConstraintWidget.DimensionBehaviour dimensionbehaviour1;
        int j;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        boolean flag;
        boolean flag2;
label0:
        {
            boolean flag1;
label1:
            {
                i2 = mX;
                j2 = mY;
                k2 = Math.max(0, getWidth());
                l2 = Math.max(0, getHeight());
                mWidthMeasuredTooSmall = false;
                mHeightMeasuredTooSmall = false;
                ConstraintWidget constraintwidget;
                int i;
                if (mParent != null)
                {
                    if (mSnapshot == null)
                    {
                        mSnapshot = new Snapshot(this);
                    }
                    mSnapshot.updateFrom(this);
                    setX(mPaddingLeft);
                    setY(mPaddingTop);
                    resetAnchors();
                    resetSolverVariables(mSystem.getCache());
                } else
                {
                    mX = 0;
                    mY = 0;
                }
                flag1 = false;
                dimensionbehaviour = mVerticalDimensionBehaviour;
                dimensionbehaviour1 = mHorizontalDimensionBehaviour;
                flag = flag1;
                if (mOptimizationLevel != 2)
                {
                    break label0;
                }
                if (mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    flag = flag1;
                    if (mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                    {
                        break label0;
                    }
                }
                findWrapSize(mChildren, flags);
                flag = flags[0];
                flag1 = flag;
                if (k2 <= 0)
                {
                    break label1;
                }
                flag1 = flag;
                if (l2 <= 0)
                {
                    break label1;
                }
                if (mWrapWidth <= k2)
                {
                    flag1 = flag;
                    if (mWrapHeight <= l2)
                    {
                        break label1;
                    }
                }
                flag1 = false;
            }
            flag = flag1;
            if (flag1)
            {
                if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (k2 > 0 && k2 < mWrapWidth)
                    {
                        mWidthMeasuredTooSmall = true;
                        setWidth(k2);
                    } else
                    {
                        setWidth(Math.max(mMinWidth, mWrapWidth));
                    }
                }
                flag = flag1;
                if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (l2 > 0 && l2 < mWrapHeight)
                    {
                        mHeightMeasuredTooSmall = true;
                        setHeight(l2);
                        flag = flag1;
                    } else
                    {
                        setHeight(Math.max(mMinHeight, mWrapHeight));
                        flag = flag1;
                    }
                }
            }
        }
        resetChains();
        i3 = mChildren.size();
        for (i = 0; i < i3; i++)
        {
            constraintwidget = (ConstraintWidget)mChildren.get(i);
            if (constraintwidget instanceof WidgetContainer)
            {
                ((WidgetContainer)constraintwidget).layout();
            }
        }

        flag2 = true;
        j = 0;
_L7:
        int l1;
        boolean flag3;
        if (!flag2)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = j + 1;
        flag3 = flag2;
        mSystem.reset();
        flag3 = flag2;
        flag2 = addChildrenToSolver(mSystem, 0x7fffffff);
        flag3 = flag2;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_501;
        }
        flag3 = flag2;
        mSystem.minimize();
        flag3 = flag2;
_L3:
        if (!flag3) goto _L2; else goto _L1
_L1:
        updateChildrenFromSolver(mSystem, 0x7fffffff, flags);
_L4:
        int k1;
        boolean flag9;
        boolean flag4 = false;
        flag9 = false;
        flag3 = flag4;
        flag2 = flag;
        if (l1 >= 8)
        {
            break MISSING_BLOCK_LABEL_866;
        }
        flag3 = flag4;
        flag2 = flag;
        if (!flags[2])
        {
            break MISSING_BLOCK_LABEL_866;
        }
        k1 = 0;
        j = 0;
        for (int l = 0; l < i3; l++)
        {
            ConstraintWidget constraintwidget1 = (ConstraintWidget)mChildren.get(l);
            k1 = Math.max(k1, constraintwidget1.mX + constraintwidget1.getWidth());
            j = Math.max(j, constraintwidget1.mY + constraintwidget1.getHeight());
        }

        break MISSING_BLOCK_LABEL_742;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L3
_L2:
        updateFromSolver(mSystem, 0x7fffffff);
        j = 0;
_L5:
        if (j < i3)
        {
            ConstraintWidget constraintwidget2 = (ConstraintWidget)mChildren.get(j);
            if (constraintwidget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget2.getWidth() < constraintwidget2.getWrapWidth())
            {
                flags[2] = true;
            } else
            {
label2:
                {
                    if (constraintwidget2.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintwidget2.getHeight() >= constraintwidget2.getWrapHeight())
                    {
                        break label2;
                    }
                    flags[2] = true;
                }
            }
        }
          goto _L4
        j++;
          goto _L5
        int i1 = Math.max(mMinWidth, k1);
        j = Math.max(mMinHeight, j);
        boolean flag7 = flag9;
        boolean flag5 = flag;
        if (dimensionbehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
        {
            flag7 = flag9;
            flag5 = flag;
            if (getWidth() < i1)
            {
                setWidth(i1);
                mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                flag5 = true;
                flag7 = true;
            }
        }
        flag3 = flag7;
        flag2 = flag5;
        if (dimensionbehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
        {
            flag3 = flag7;
            flag2 = flag5;
            if (getHeight() < j)
            {
                setHeight(j);
                mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                flag2 = true;
                flag3 = true;
            }
        }
        j = Math.max(mMinWidth, getWidth());
        if (j > getWidth())
        {
            setWidth(j);
            mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
            flag2 = true;
            flag3 = true;
        }
        j = Math.max(mMinHeight, getHeight());
        boolean flag6 = flag2;
        if (j > getHeight())
        {
            setHeight(j);
            mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
            flag6 = true;
            flag3 = true;
        }
        j = l1;
        flag2 = flag3;
        flag = flag6;
        if (!flag6)
        {
            boolean flag10 = flag3;
            boolean flag8 = flag6;
            if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
            {
                flag10 = flag3;
                flag8 = flag6;
                if (k2 > 0)
                {
                    flag10 = flag3;
                    flag8 = flag6;
                    if (getWidth() > k2)
                    {
                        mWidthMeasuredTooSmall = true;
                        flag8 = true;
                        mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        setWidth(k2);
                        flag10 = true;
                    }
                }
            }
            j = l1;
            flag2 = flag10;
            flag = flag8;
            if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
            {
                j = l1;
                flag2 = flag10;
                flag = flag8;
                if (l2 > 0)
                {
                    j = l1;
                    flag2 = flag10;
                    flag = flag8;
                    if (getHeight() > l2)
                    {
                        mHeightMeasuredTooSmall = true;
                        flag = true;
                        mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        setHeight(l2);
                        flag2 = true;
                        j = l1;
                    }
                }
            }
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (mParent != null)
        {
            int k = Math.max(mMinWidth, getWidth());
            int j1 = Math.max(mMinHeight, getHeight());
            mSnapshot.applyTo(this);
            setWidth(mPaddingLeft + k + mPaddingRight);
            setHeight(mPaddingTop + j1 + mPaddingBottom);
        } else
        {
            mX = i2;
            mY = j2;
        }
        if (flag)
        {
            mHorizontalDimensionBehaviour = dimensionbehaviour1;
            mVerticalDimensionBehaviour = dimensionbehaviour;
        }
        resetSolverVariables(mSystem.getCache());
        if (this == getRootConstraintContainer())
        {
            updateDrawPosition();
        }
        return;
    }

    public int layoutFindGroups()
    {
        ConstraintAnchor.Type atype[];
        int i1;
        boolean flag1;
        int l2;
        int j3;
        atype = new ConstraintAnchor.Type[5];
        atype[0] = ConstraintAnchor.Type.LEFT;
        atype[1] = ConstraintAnchor.Type.RIGHT;
        atype[2] = ConstraintAnchor.Type.TOP;
        atype[3] = ConstraintAnchor.Type.BASELINE;
        atype[4] = ConstraintAnchor.Type.BOTTOM;
        int i = 1;
        j3 = mChildren.size();
        int l1 = 0;
        while (l1 < j3) 
        {
            Object obj = (ConstraintWidget)mChildren.get(l1);
            ConstraintAnchor constraintanchor1 = ((ConstraintWidget) (obj)).mLeft;
            int l;
            if (constraintanchor1.mTarget != null)
            {
                l = i;
                if (setGroup(constraintanchor1, i) == i)
                {
                    l = i + 1;
                }
            } else
            {
                constraintanchor1.mGroup = 0x7fffffff;
                l = i;
            }
            constraintanchor1 = ((ConstraintWidget) (obj)).mTop;
            if (constraintanchor1.mTarget != null)
            {
                i = l;
                if (setGroup(constraintanchor1, l) == l)
                {
                    i = l + 1;
                }
            } else
            {
                constraintanchor1.mGroup = 0x7fffffff;
                i = l;
            }
            constraintanchor1 = ((ConstraintWidget) (obj)).mRight;
            if (constraintanchor1.mTarget != null)
            {
                l = i;
                if (setGroup(constraintanchor1, i) == i)
                {
                    l = i + 1;
                }
            } else
            {
                constraintanchor1.mGroup = 0x7fffffff;
                l = i;
            }
            constraintanchor1 = ((ConstraintWidget) (obj)).mBottom;
            if (constraintanchor1.mTarget != null)
            {
                i = l;
                if (setGroup(constraintanchor1, l) == l)
                {
                    i = l + 1;
                }
            } else
            {
                constraintanchor1.mGroup = 0x7fffffff;
                i = l;
            }
            obj = ((ConstraintWidget) (obj)).mBaseline;
            if (((ConstraintAnchor) (obj)).mTarget != null)
            {
                l = i;
                if (setGroup(((ConstraintAnchor) (obj)), i) == i)
                {
                    l = i + 1;
                }
            } else
            {
                obj.mGroup = 0x7fffffff;
                l = i;
            }
            l1++;
            i = l;
        }
        flag1 = true;
        l2 = 0;
        i1 = 0;
_L2:
        int j;
        boolean flag;
        int k2;
        int i3;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_691;
        }
        flag = false;
        i3 = l2 + 1;
        k2 = 0;
        j = i1;
_L10:
        l2 = i3;
        i1 = j;
        flag1 = flag;
        if (k2 >= j3) goto _L2; else goto _L1
_L1:
        ConstraintWidget constraintwidget;
        constraintwidget = (ConstraintWidget)mChildren.get(k2);
        l2 = 0;
_L9:
        ConstraintAnchor constraintanchor;
        ConstraintAnchor.Type type;
        if (l2 >= atype.length)
        {
            break MISSING_BLOCK_LABEL_682;
        }
        type = atype[l2];
        constraintanchor = null;
        static class _cls2
        {

            static final int $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[];

            static 
            {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type = new int[ConstraintAnchor.Type.values().length];
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls2..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[type.ordinal()];
        JVM INSTR tableswitch 1 5: default 440
    //                   1 460
    //                   2 468
    //                   3 476
    //                   4 484
    //                   5 492;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        ConstraintAnchor constraintanchor3 = constraintanchor.mTarget;
        if (constraintanchor3 != null)
        {
            int j1 = j;
            boolean flag2 = flag;
            if (constraintanchor3.mOwner.getParent() != null)
            {
                j1 = j;
                flag2 = flag;
                if (constraintanchor3.mGroup != constraintanchor.mGroup)
                {
                    if (constraintanchor.mGroup > constraintanchor3.mGroup)
                    {
                        j1 = constraintanchor3.mGroup;
                    } else
                    {
                        j1 = constraintanchor.mGroup;
                    }
                    constraintanchor.mGroup = j1;
                    constraintanchor3.mGroup = j1;
                    j1 = j + 1;
                    flag2 = true;
                }
            }
            constraintanchor3 = constraintanchor3.getOpposite();
            j = j1;
            flag = flag2;
            if (constraintanchor3 != null)
            {
                j = j1;
                flag = flag2;
                if (constraintanchor3.mGroup != constraintanchor.mGroup)
                {
                    if (constraintanchor.mGroup > constraintanchor3.mGroup)
                    {
                        j = constraintanchor3.mGroup;
                    } else
                    {
                        j = constraintanchor.mGroup;
                    }
                    constraintanchor.mGroup = j;
                    constraintanchor3.mGroup = j;
                    j = j1 + 1;
                    flag = true;
                }
            }
        }
        l2++;
        if (true) goto _L9; else goto _L4
_L4:
        constraintanchor = constraintwidget.mLeft;
          goto _L3
_L5:
        constraintanchor = constraintwidget.mTop;
          goto _L3
_L6:
        constraintanchor = constraintwidget.mRight;
          goto _L3
_L7:
        constraintanchor = constraintwidget.mBottom;
          goto _L3
_L8:
        constraintanchor = constraintwidget.mBaseline;
          goto _L3
        k2++;
          goto _L10
        int ai[] = new int[mChildren.size() * atype.length + 1];
        Arrays.fill(ai, -1);
        int i2 = 0;
        int k = 0;
        while (i2 < j3) 
        {
            Object obj1 = (ConstraintWidget)mChildren.get(i2);
            ConstraintAnchor constraintanchor2 = ((ConstraintWidget) (obj1)).mLeft;
            int k1;
            if (constraintanchor2.mGroup != 0x7fffffff)
            {
                int j2 = constraintanchor2.mGroup;
                if (ai[j2] == -1)
                {
                    k1 = k + 1;
                    ai[j2] = k;
                } else
                {
                    k1 = k;
                }
                constraintanchor2.mGroup = ai[j2];
            } else
            {
                k1 = k;
            }
            constraintanchor2 = ((ConstraintWidget) (obj1)).mTop;
            k = k1;
            if (constraintanchor2.mGroup != 0x7fffffff)
            {
                j2 = constraintanchor2.mGroup;
                k = k1;
                if (ai[j2] == -1)
                {
                    ai[j2] = k1;
                    k = k1 + 1;
                }
                constraintanchor2.mGroup = ai[j2];
            }
            constraintanchor2 = ((ConstraintWidget) (obj1)).mRight;
            k1 = k;
            if (constraintanchor2.mGroup != 0x7fffffff)
            {
                j2 = constraintanchor2.mGroup;
                k1 = k;
                if (ai[j2] == -1)
                {
                    ai[j2] = k;
                    k1 = k + 1;
                }
                constraintanchor2.mGroup = ai[j2];
            }
            constraintanchor2 = ((ConstraintWidget) (obj1)).mBottom;
            k = k1;
            if (constraintanchor2.mGroup != 0x7fffffff)
            {
                j2 = constraintanchor2.mGroup;
                k = k1;
                if (ai[j2] == -1)
                {
                    ai[j2] = k1;
                    k = k1 + 1;
                }
                constraintanchor2.mGroup = ai[j2];
            }
            obj1 = ((ConstraintWidget) (obj1)).mBaseline;
            k1 = k;
            if (((ConstraintAnchor) (obj1)).mGroup != 0x7fffffff)
            {
                j2 = ((ConstraintAnchor) (obj1)).mGroup;
                k1 = k;
                if (ai[j2] == -1)
                {
                    ai[j2] = k;
                    k1 = k + 1;
                }
                obj1.mGroup = ai[j2];
            }
            i2++;
            k = k1;
        }
        return k;
    }

    public int layoutFindGroupsSimple()
    {
        int j = mChildren.size();
        for (int i = 0; i < j; i++)
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(i);
            constraintwidget.mLeft.mGroup = 0;
            constraintwidget.mRight.mGroup = 0;
            constraintwidget.mTop.mGroup = 1;
            constraintwidget.mBottom.mGroup = 1;
            constraintwidget.mBaseline.mGroup = 1;
        }

        return 2;
    }

    public void layoutWithGroup(int i)
    {
        int i1 = mX;
        int j1 = mY;
        int k1;
        if (mParent != null)
        {
            if (mSnapshot == null)
            {
                mSnapshot = new Snapshot(this);
            }
            mSnapshot.updateFrom(this);
            mX = 0;
            mY = 0;
            resetAnchors();
            resetSolverVariables(mSystem.getCache());
        } else
        {
            mX = 0;
            mY = 0;
        }
        k1 = mChildren.size();
        for (int j = 0; j < k1; j++)
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(j);
            if (constraintwidget instanceof WidgetContainer)
            {
                ((WidgetContainer)constraintwidget).layout();
            }
        }

        mLeft.mGroup = 0;
        mRight.mGroup = 0;
        mTop.mGroup = 1;
        mBottom.mGroup = 1;
        mSystem.reset();
        int k = 0;
        while (k < i) 
        {
            try
            {
                addToSolver(mSystem, k);
                mSystem.minimize();
                updateFromSolver(mSystem, k);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            updateFromSolver(mSystem, -2);
            k++;
        }
        if (mParent != null)
        {
            i = getWidth();
            int l = getHeight();
            mSnapshot.applyTo(this);
            setWidth(i);
            setHeight(l);
        } else
        {
            mX = i1;
            mY = j1;
        }
        if (this == getRootConstraintContainer())
        {
            updateDrawPosition();
        }
    }

    public void reset()
    {
        mSystem.reset();
        mPaddingLeft = 0;
        mPaddingRight = 0;
        mPaddingTop = 0;
        mPaddingBottom = 0;
        super.reset();
    }

    public void setOptimizationLevel(int i)
    {
        mOptimizationLevel = i;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        mPaddingLeft = i;
        mPaddingTop = j;
        mPaddingRight = k;
        mPaddingBottom = l;
    }

    public void updateChildrenFromSolver(LinearSystem linearsystem, int i, boolean aflag[])
    {
        aflag[2] = false;
        updateFromSolver(linearsystem, i);
        int k = mChildren.size();
        for (int j = 0; j < k; j++)
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(j);
            constraintwidget.updateFromSolver(linearsystem, i);
            if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.getWidth() < constraintwidget.getWrapWidth())
            {
                aflag[2] = true;
            }
            if (constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintwidget.getHeight() < constraintwidget.getWrapHeight())
            {
                aflag[2] = true;
            }
        }

    }

    static 
    {
        ALLOW_ROOT_GROUP = true;
    }
}
