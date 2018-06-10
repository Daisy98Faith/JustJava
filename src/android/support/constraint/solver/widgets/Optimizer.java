// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget, ConstraintAnchor, ConstraintWidgetContainer, Guideline

public class Optimizer
{

    public Optimizer()
    {
    }

    static void applyDirectResolutionHorizontalChain(ConstraintWidgetContainer constraintwidgetcontainer, LinearSystem linearsystem, int i, ConstraintWidget constraintwidget)
    {
        int k1 = 0;
        ConstraintWidget constraintwidget6 = null;
        int l1 = 0;
        float f2 = 0.0F;
        ConstraintWidget constraintwidget1 = constraintwidget;
        do
        {
            if (constraintwidget1 == null)
            {
                break;
            }
            float f;
            ConstraintWidget constraintwidget3;
            int j;
            int l;
            int i2;
            if (constraintwidget1.getVisibility() == 8)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            i2 = l1;
            f = f2;
            j = k1;
            if (l == 0)
            {
                i2 = l1 + 1;
                if (constraintwidget1.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    l1 = constraintwidget1.getWidth();
                    ConstraintWidget constraintwidget5;
                    if (constraintwidget1.mLeft.mTarget != null)
                    {
                        j = constraintwidget1.mLeft.getMargin();
                    } else
                    {
                        j = 0;
                    }
                    if (constraintwidget1.mRight.mTarget != null)
                    {
                        l = constraintwidget1.mRight.getMargin();
                    } else
                    {
                        l = 0;
                    }
                    j = k1 + l1 + j + l;
                    f = f2;
                } else
                {
                    f = f2 + constraintwidget1.mHorizontalWeight;
                    j = k1;
                }
            }
            constraintwidget5 = constraintwidget1;
            if (constraintwidget1.mRight.mTarget != null)
            {
                constraintwidget3 = constraintwidget1.mRight.mTarget.mOwner;
            } else
            {
                constraintwidget3 = null;
            }
            l1 = i2;
            constraintwidget6 = constraintwidget5;
            f2 = f;
            k1 = j;
            constraintwidget1 = constraintwidget3;
            if (constraintwidget3 == null)
            {
                continue;
            }
            if (constraintwidget3.mLeft.mTarget != null)
            {
                l1 = i2;
                constraintwidget6 = constraintwidget5;
                f2 = f;
                k1 = j;
                constraintwidget1 = constraintwidget3;
                if (constraintwidget3.mLeft.mTarget == null)
                {
                    continue;
                }
                l1 = i2;
                constraintwidget6 = constraintwidget5;
                f2 = f;
                k1 = j;
                constraintwidget1 = constraintwidget3;
                if (constraintwidget3.mLeft.mTarget.mOwner == constraintwidget5)
                {
                    continue;
                }
            }
            constraintwidget1 = null;
            l1 = i2;
            constraintwidget6 = constraintwidget5;
            f2 = f;
            k1 = j;
        } while (true);
        int i1 = 0;
        float f1;
        float f3;
        float f6;
        int k;
        if (constraintwidget6 != null)
        {
            float f4;
            ConstraintWidget constraintwidget4;
            if (constraintwidget6.mRight.mTarget != null)
            {
                k = constraintwidget6.mRight.mTarget.mOwner.getX();
            } else
            {
                k = 0;
            }
            i1 = k;
            if (constraintwidget6.mRight.mTarget != null)
            {
                i1 = k;
                if (constraintwidget6.mRight.mTarget.mOwner == constraintwidgetcontainer)
                {
                    i1 = constraintwidgetcontainer.getRight();
                }
            }
        }
        f6 = (float)(i1 - 0) - (float)k1;
        f3 = f6 / (float)(l1 + 1);
        f1 = 0.0F;
        if (i == 0)
        {
            f1 = f3;
        } else
        {
            f3 = f6 / (float)i;
        }
        do
        {
            if (constraintwidget == null)
            {
                break;
            }
            float f5;
            ConstraintWidget constraintwidget2;
            int j1;
            if (constraintwidget.mLeft.mTarget != null)
            {
                k = constraintwidget.mLeft.getMargin();
            } else
            {
                k = 0;
            }
            if (constraintwidget.mRight.mTarget != null)
            {
                j1 = constraintwidget.mRight.getMargin();
            } else
            {
                j1 = 0;
            }
            if (constraintwidget.getVisibility() != 8)
            {
                f1 += k;
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, (int)(0.5F + f1));
                if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    if (f2 == 0.0F)
                    {
                        f1 += f3 - (float)k - (float)j1;
                    } else
                    {
                        f1 += (constraintwidget.mHorizontalWeight * f6) / f2 - (float)k - (float)j1;
                    }
                } else
                {
                    f1 += constraintwidget.getWidth();
                }
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, (int)(0.5F + f1));
                f4 = f1;
                if (i == 0)
                {
                    f4 = f1 + f3;
                }
                f5 += j1;
            } else
            {
                f5 = f1 - f3 / 2.0F;
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, (int)(0.5F + f5));
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, (int)(0.5F + f5));
                f5 = f1;
            }
            if (constraintwidget.mRight.mTarget != null)
            {
                constraintwidget2 = constraintwidget.mRight.mTarget.mOwner;
            } else
            {
                constraintwidget2 = null;
            }
            constraintwidget4 = constraintwidget2;
            if (constraintwidget2 != null)
            {
                constraintwidget4 = constraintwidget2;
                if (constraintwidget2.mLeft.mTarget != null)
                {
                    constraintwidget4 = constraintwidget2;
                    if (constraintwidget2.mLeft.mTarget.mOwner != constraintwidget)
                    {
                        constraintwidget4 = null;
                    }
                }
            }
            f1 = f5;
            constraintwidget = constraintwidget4;
            if (constraintwidget4 == constraintwidgetcontainer)
            {
                constraintwidget = null;
                f1 = f5;
            }
        } while (true);
    }

    static void applyDirectResolutionVerticalChain(ConstraintWidgetContainer constraintwidgetcontainer, LinearSystem linearsystem, int i, ConstraintWidget constraintwidget)
    {
        int k1 = 0;
        ConstraintWidget constraintwidget6 = null;
        int l1 = 0;
        float f2 = 0.0F;
        ConstraintWidget constraintwidget1 = constraintwidget;
        do
        {
            if (constraintwidget1 == null)
            {
                break;
            }
            float f;
            ConstraintWidget constraintwidget3;
            int j;
            int l;
            int i2;
            if (constraintwidget1.getVisibility() == 8)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            i2 = l1;
            f = f2;
            j = k1;
            if (l == 0)
            {
                i2 = l1 + 1;
                if (constraintwidget1.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    l1 = constraintwidget1.getHeight();
                    ConstraintWidget constraintwidget5;
                    if (constraintwidget1.mTop.mTarget != null)
                    {
                        j = constraintwidget1.mTop.getMargin();
                    } else
                    {
                        j = 0;
                    }
                    if (constraintwidget1.mBottom.mTarget != null)
                    {
                        l = constraintwidget1.mBottom.getMargin();
                    } else
                    {
                        l = 0;
                    }
                    j = k1 + l1 + j + l;
                    f = f2;
                } else
                {
                    f = f2 + constraintwidget1.mVerticalWeight;
                    j = k1;
                }
            }
            constraintwidget5 = constraintwidget1;
            if (constraintwidget1.mBottom.mTarget != null)
            {
                constraintwidget3 = constraintwidget1.mBottom.mTarget.mOwner;
            } else
            {
                constraintwidget3 = null;
            }
            l1 = i2;
            constraintwidget6 = constraintwidget5;
            f2 = f;
            k1 = j;
            constraintwidget1 = constraintwidget3;
            if (constraintwidget3 == null)
            {
                continue;
            }
            if (constraintwidget3.mTop.mTarget != null)
            {
                l1 = i2;
                constraintwidget6 = constraintwidget5;
                f2 = f;
                k1 = j;
                constraintwidget1 = constraintwidget3;
                if (constraintwidget3.mTop.mTarget == null)
                {
                    continue;
                }
                l1 = i2;
                constraintwidget6 = constraintwidget5;
                f2 = f;
                k1 = j;
                constraintwidget1 = constraintwidget3;
                if (constraintwidget3.mTop.mTarget.mOwner == constraintwidget5)
                {
                    continue;
                }
            }
            constraintwidget1 = null;
            l1 = i2;
            constraintwidget6 = constraintwidget5;
            f2 = f;
            k1 = j;
        } while (true);
        int i1 = 0;
        float f1;
        float f3;
        float f6;
        int k;
        if (constraintwidget6 != null)
        {
            float f4;
            ConstraintWidget constraintwidget4;
            if (constraintwidget6.mBottom.mTarget != null)
            {
                k = constraintwidget6.mBottom.mTarget.mOwner.getX();
            } else
            {
                k = 0;
            }
            i1 = k;
            if (constraintwidget6.mBottom.mTarget != null)
            {
                i1 = k;
                if (constraintwidget6.mBottom.mTarget.mOwner == constraintwidgetcontainer)
                {
                    i1 = constraintwidgetcontainer.getBottom();
                }
            }
        }
        f6 = (float)(i1 - 0) - (float)k1;
        f3 = f6 / (float)(l1 + 1);
        f1 = 0.0F;
        if (i == 0)
        {
            f1 = f3;
        } else
        {
            f3 = f6 / (float)i;
        }
        do
        {
            if (constraintwidget == null)
            {
                break;
            }
            float f5;
            ConstraintWidget constraintwidget2;
            int j1;
            if (constraintwidget.mTop.mTarget != null)
            {
                k = constraintwidget.mTop.getMargin();
            } else
            {
                k = 0;
            }
            if (constraintwidget.mBottom.mTarget != null)
            {
                j1 = constraintwidget.mBottom.getMargin();
            } else
            {
                j1 = 0;
            }
            if (constraintwidget.getVisibility() != 8)
            {
                f1 += k;
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, (int)(0.5F + f1));
                if (constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                {
                    if (f2 == 0.0F)
                    {
                        f1 += f3 - (float)k - (float)j1;
                    } else
                    {
                        f1 += (constraintwidget.mVerticalWeight * f6) / f2 - (float)k - (float)j1;
                    }
                } else
                {
                    f1 += constraintwidget.getHeight();
                }
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, (int)(0.5F + f1));
                f4 = f1;
                if (i == 0)
                {
                    f4 = f1 + f3;
                }
                f5 += j1;
            } else
            {
                f5 = f1 - f3 / 2.0F;
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, (int)(0.5F + f5));
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, (int)(0.5F + f5));
                f5 = f1;
            }
            if (constraintwidget.mBottom.mTarget != null)
            {
                constraintwidget2 = constraintwidget.mBottom.mTarget.mOwner;
            } else
            {
                constraintwidget2 = null;
            }
            constraintwidget4 = constraintwidget2;
            if (constraintwidget2 != null)
            {
                constraintwidget4 = constraintwidget2;
                if (constraintwidget2.mTop.mTarget != null)
                {
                    constraintwidget4 = constraintwidget2;
                    if (constraintwidget2.mTop.mTarget.mOwner != constraintwidget)
                    {
                        constraintwidget4 = null;
                    }
                }
            }
            f1 = f5;
            constraintwidget = constraintwidget4;
            if (constraintwidget4 == constraintwidgetcontainer)
            {
                constraintwidget = null;
                f1 = f5;
            }
        } while (true);
    }

    static void checkHorizontalSimpleDependency(ConstraintWidgetContainer constraintwidgetcontainer, LinearSystem linearsystem, ConstraintWidget constraintwidget)
    {
        if (constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
            constraintwidget.mHorizontalResolution = 1;
        } else
        {
            if (constraintwidgetcontainer.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
            {
                constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                int i = constraintwidget.mLeft.mMargin;
                int i2 = constraintwidgetcontainer.getWidth() - constraintwidget.mRight.mMargin;
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, i);
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, i2);
                constraintwidget.setHorizontalDimension(i, i2);
                constraintwidget.mHorizontalResolution = 2;
                return;
            }
            if (constraintwidget.mLeft.mTarget != null && constraintwidget.mRight.mTarget != null)
            {
                if (constraintwidget.mLeft.mTarget.mOwner == constraintwidgetcontainer && constraintwidget.mRight.mTarget.mOwner == constraintwidgetcontainer)
                {
                    int j = constraintwidget.mLeft.getMargin();
                    int j2 = constraintwidget.mRight.getMargin();
                    if (constraintwidgetcontainer.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                    {
                        j2 = constraintwidgetcontainer.getWidth() - j2;
                    } else
                    {
                        int l3 = constraintwidget.getWidth();
                        j += (int)((float)(constraintwidgetcontainer.getWidth() - j - j2 - l3) * constraintwidget.mHorizontalBiasPercent + 0.5F);
                        j2 = j + constraintwidget.getWidth();
                    }
                    constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                    constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                    linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, j);
                    linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, j2);
                    constraintwidget.mHorizontalResolution = 2;
                    constraintwidget.setHorizontalDimension(j, j2);
                    return;
                } else
                {
                    constraintwidget.mHorizontalResolution = 1;
                    return;
                }
            }
            if (constraintwidget.mLeft.mTarget != null && constraintwidget.mLeft.mTarget.mOwner == constraintwidgetcontainer)
            {
                int k = constraintwidget.mLeft.getMargin();
                int k2 = k + constraintwidget.getWidth();
                constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, k);
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, k2);
                constraintwidget.mHorizontalResolution = 2;
                constraintwidget.setHorizontalDimension(k, k2);
                return;
            }
            if (constraintwidget.mRight.mTarget != null && constraintwidget.mRight.mTarget.mOwner == constraintwidgetcontainer)
            {
                constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                int l = constraintwidgetcontainer.getWidth() - constraintwidget.mRight.getMargin();
                int l2 = l - constraintwidget.getWidth();
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, l2);
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, l);
                constraintwidget.mHorizontalResolution = 2;
                constraintwidget.setHorizontalDimension(l2, l);
                return;
            }
            if (constraintwidget.mLeft.mTarget != null && constraintwidget.mLeft.mTarget.mOwner.mHorizontalResolution == 2)
            {
                constraintwidgetcontainer = constraintwidget.mLeft.mTarget.mSolverVariable;
                constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                int i1 = (int)(((SolverVariable) (constraintwidgetcontainer)).computedValue + (float)constraintwidget.mLeft.getMargin() + 0.5F);
                int i3 = i1 + constraintwidget.getWidth();
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, i1);
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, i3);
                constraintwidget.mHorizontalResolution = 2;
                constraintwidget.setHorizontalDimension(i1, i3);
                return;
            }
            if (constraintwidget.mRight.mTarget != null && constraintwidget.mRight.mTarget.mOwner.mHorizontalResolution == 2)
            {
                constraintwidgetcontainer = constraintwidget.mRight.mTarget.mSolverVariable;
                constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                int j1 = (int)((((SolverVariable) (constraintwidgetcontainer)).computedValue - (float)constraintwidget.mRight.getMargin()) + 0.5F);
                int j3 = j1 - constraintwidget.getWidth();
                linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, j3);
                linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, j1);
                constraintwidget.mHorizontalResolution = 2;
                constraintwidget.setHorizontalDimension(j3, j1);
                return;
            }
            int k1;
            boolean flag;
            if (constraintwidget.mLeft.mTarget != null)
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            if (constraintwidget.mRight.mTarget != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (k1 == 0 && !flag)
            {
                if (constraintwidget instanceof Guideline)
                {
                    Guideline guideline = (Guideline)constraintwidget;
                    if (guideline.getOrientation() == 1)
                    {
                        constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                        constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                        float f;
                        if (guideline.getRelativeBegin() != -1)
                        {
                            f = guideline.getRelativeBegin();
                        } else
                        if (guideline.getRelativeEnd() != -1)
                        {
                            f = constraintwidgetcontainer.getWidth() - guideline.getRelativeEnd();
                        } else
                        {
                            f = (float)constraintwidgetcontainer.getWidth() * guideline.getRelativePercent();
                        }
                        k1 = (int)(0.5F + f);
                        linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, k1);
                        linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, k1);
                        constraintwidget.mHorizontalResolution = 2;
                        constraintwidget.mVerticalResolution = 2;
                        constraintwidget.setHorizontalDimension(k1, k1);
                        constraintwidget.setVerticalDimension(0, constraintwidgetcontainer.getHeight());
                        return;
                    }
                } else
                {
                    constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
                    constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
                    int l1 = constraintwidget.getX();
                    int k3 = constraintwidget.getWidth();
                    linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, l1);
                    linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, l1 + k3);
                    constraintwidget.mHorizontalResolution = 2;
                    return;
                }
            }
        }
    }

    static void checkMatchParent(ConstraintWidgetContainer constraintwidgetcontainer, LinearSystem linearsystem, ConstraintWidget constraintwidget)
    {
        if (constraintwidgetcontainer.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintwidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
        {
            constraintwidget.mLeft.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mLeft);
            constraintwidget.mRight.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mRight);
            int i = constraintwidget.mLeft.mMargin;
            int k = constraintwidgetcontainer.getWidth() - constraintwidget.mRight.mMargin;
            linearsystem.addEquality(constraintwidget.mLeft.mSolverVariable, i);
            linearsystem.addEquality(constraintwidget.mRight.mSolverVariable, k);
            constraintwidget.setHorizontalDimension(i, k);
            constraintwidget.mHorizontalResolution = 2;
        }
        if (constraintwidgetcontainer.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
        {
            constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
            constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
            int j = constraintwidget.mTop.mMargin;
            int l = constraintwidgetcontainer.getHeight() - constraintwidget.mBottom.mMargin;
            linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, j);
            linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, l);
            if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
            {
                constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + j);
            }
            constraintwidget.setVerticalDimension(j, l);
            constraintwidget.mVerticalResolution = 2;
        }
    }

    static void checkVerticalSimpleDependency(ConstraintWidgetContainer constraintwidgetcontainer, LinearSystem linearsystem, ConstraintWidget constraintwidget)
    {
        if (constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
            constraintwidget.mVerticalResolution = 1;
        } else
        {
            if (constraintwidgetcontainer.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintwidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
            {
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                int i = constraintwidget.mTop.mMargin;
                int j2 = constraintwidgetcontainer.getHeight() - constraintwidget.mBottom.mMargin;
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, i);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, j2);
                if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                {
                    constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                    linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + i);
                }
                constraintwidget.setVerticalDimension(i, j2);
                constraintwidget.mVerticalResolution = 2;
                return;
            }
            if (constraintwidget.mTop.mTarget != null && constraintwidget.mBottom.mTarget != null)
            {
                if (constraintwidget.mTop.mTarget.mOwner == constraintwidgetcontainer && constraintwidget.mBottom.mTarget.mOwner == constraintwidgetcontainer)
                {
                    int k2 = constraintwidget.mTop.getMargin();
                    int j = constraintwidget.mBottom.getMargin();
                    if (constraintwidgetcontainer.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
                    {
                        j = k2 + constraintwidget.getHeight();
                    } else
                    {
                        int j4 = constraintwidget.getHeight();
                        int k4 = constraintwidgetcontainer.getHeight();
                        k2 = (int)((float)k2 + (float)(k4 - k2 - j - j4) * constraintwidget.mVerticalBiasPercent + 0.5F);
                        j = k2 + constraintwidget.getHeight();
                    }
                    constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                    constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                    linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, k2);
                    linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, j);
                    if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                    {
                        constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                        linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + k2);
                    }
                    constraintwidget.mVerticalResolution = 2;
                    constraintwidget.setVerticalDimension(k2, j);
                    return;
                } else
                {
                    constraintwidget.mVerticalResolution = 1;
                    return;
                }
            }
            if (constraintwidget.mTop.mTarget != null && constraintwidget.mTop.mTarget.mOwner == constraintwidgetcontainer)
            {
                int k = constraintwidget.mTop.getMargin();
                int l2 = k + constraintwidget.getHeight();
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, k);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, l2);
                if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                {
                    constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                    linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + k);
                }
                constraintwidget.mVerticalResolution = 2;
                constraintwidget.setVerticalDimension(k, l2);
                return;
            }
            if (constraintwidget.mBottom.mTarget != null && constraintwidget.mBottom.mTarget.mOwner == constraintwidgetcontainer)
            {
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                int l = constraintwidgetcontainer.getHeight() - constraintwidget.mBottom.getMargin();
                int i3 = l - constraintwidget.getHeight();
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, i3);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, l);
                if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                {
                    constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                    linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + i3);
                }
                constraintwidget.mVerticalResolution = 2;
                constraintwidget.setVerticalDimension(i3, l);
                return;
            }
            if (constraintwidget.mTop.mTarget != null && constraintwidget.mTop.mTarget.mOwner.mVerticalResolution == 2)
            {
                constraintwidgetcontainer = constraintwidget.mTop.mTarget.mSolverVariable;
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                int i1 = (int)(((SolverVariable) (constraintwidgetcontainer)).computedValue + (float)constraintwidget.mTop.getMargin() + 0.5F);
                int j3 = i1 + constraintwidget.getHeight();
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, i1);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, j3);
                if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                {
                    constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                    linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + i1);
                }
                constraintwidget.mVerticalResolution = 2;
                constraintwidget.setVerticalDimension(i1, j3);
                return;
            }
            if (constraintwidget.mBottom.mTarget != null && constraintwidget.mBottom.mTarget.mOwner.mVerticalResolution == 2)
            {
                constraintwidgetcontainer = constraintwidget.mBottom.mTarget.mSolverVariable;
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                int j1 = (int)((((SolverVariable) (constraintwidgetcontainer)).computedValue - (float)constraintwidget.mBottom.getMargin()) + 0.5F);
                int k3 = j1 - constraintwidget.getHeight();
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, k3);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, j1);
                if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                {
                    constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                    linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + k3);
                }
                constraintwidget.mVerticalResolution = 2;
                constraintwidget.setVerticalDimension(k3, j1);
                return;
            }
            if (constraintwidget.mBaseline.mTarget != null && constraintwidget.mBaseline.mTarget.mOwner.mVerticalResolution == 2)
            {
                constraintwidgetcontainer = constraintwidget.mBaseline.mTarget.mSolverVariable;
                constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                int k1 = (int)((((SolverVariable) (constraintwidgetcontainer)).computedValue - (float)constraintwidget.mBaselineDistance) + 0.5F);
                int l3 = k1 + constraintwidget.getHeight();
                linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, k1);
                linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, l3);
                constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + k1);
                constraintwidget.mVerticalResolution = 2;
                constraintwidget.setVerticalDimension(k1, l3);
                return;
            }
            int l1;
            boolean flag;
            boolean flag1;
            if (constraintwidget.mBaseline.mTarget != null)
            {
                l1 = 1;
            } else
            {
                l1 = 0;
            }
            if (constraintwidget.mTop.mTarget != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (constraintwidget.mBottom.mTarget != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (l1 == 0 && !flag && !flag1)
            {
                if (constraintwidget instanceof Guideline)
                {
                    Guideline guideline = (Guideline)constraintwidget;
                    if (guideline.getOrientation() == 0)
                    {
                        constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                        constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                        float f;
                        if (guideline.getRelativeBegin() != -1)
                        {
                            f = guideline.getRelativeBegin();
                        } else
                        if (guideline.getRelativeEnd() != -1)
                        {
                            f = constraintwidgetcontainer.getHeight() - guideline.getRelativeEnd();
                        } else
                        {
                            f = (float)constraintwidgetcontainer.getHeight() * guideline.getRelativePercent();
                        }
                        l1 = (int)(0.5F + f);
                        linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, l1);
                        linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, l1);
                        constraintwidget.mVerticalResolution = 2;
                        constraintwidget.mHorizontalResolution = 2;
                        constraintwidget.setVerticalDimension(l1, l1);
                        constraintwidget.setHorizontalDimension(0, constraintwidgetcontainer.getWidth());
                        return;
                    }
                } else
                {
                    constraintwidget.mTop.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mTop);
                    constraintwidget.mBottom.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBottom);
                    int i2 = constraintwidget.getY();
                    int i4 = constraintwidget.getHeight();
                    linearsystem.addEquality(constraintwidget.mTop.mSolverVariable, i2);
                    linearsystem.addEquality(constraintwidget.mBottom.mSolverVariable, i2 + i4);
                    if (constraintwidget.mBaselineDistance > 0 || constraintwidget.getVisibility() == 8)
                    {
                        constraintwidget.mBaseline.mSolverVariable = linearsystem.createObjectVariable(constraintwidget.mBaseline);
                        linearsystem.addEquality(constraintwidget.mBaseline.mSolverVariable, constraintwidget.mBaselineDistance + i2);
                    }
                    constraintwidget.mVerticalResolution = 2;
                    return;
                }
            }
        }
    }
}
