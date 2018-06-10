// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidgetContainer, ConstraintWidget, ConstraintAnchor, Guideline

public class ConstraintTableLayout extends ConstraintWidgetContainer
{
    class HorizontalSlice
    {

        ConstraintWidget bottom;
        int padding;
        final ConstraintTableLayout this$0;
        ConstraintWidget top;

        HorizontalSlice()
        {
            this$0 = ConstraintTableLayout.this;
            super();
        }
    }

    class VerticalSlice
    {

        int alignment;
        ConstraintWidget left;
        int padding;
        ConstraintWidget right;
        final ConstraintTableLayout this$0;

        VerticalSlice()
        {
            this$0 = ConstraintTableLayout.this;
            super();
            alignment = 1;
        }
    }


    public static final int ALIGN_CENTER = 0;
    private static final int ALIGN_FULL = 3;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    private ArrayList mHorizontalGuidelines;
    private ArrayList mHorizontalSlices;
    private int mNumCols;
    private int mNumRows;
    private int mPadding;
    private boolean mVerticalGrowth;
    private ArrayList mVerticalGuidelines;
    private ArrayList mVerticalSlices;
    private LinearSystem system;

    public ConstraintTableLayout()
    {
        mVerticalGrowth = true;
        mNumCols = 0;
        mNumRows = 0;
        mPadding = 8;
        mVerticalSlices = new ArrayList();
        mHorizontalSlices = new ArrayList();
        mVerticalGuidelines = new ArrayList();
        mHorizontalGuidelines = new ArrayList();
        system = null;
    }

    public ConstraintTableLayout(int i, int j)
    {
        super(i, j);
        mVerticalGrowth = true;
        mNumCols = 0;
        mNumRows = 0;
        mPadding = 8;
        mVerticalSlices = new ArrayList();
        mHorizontalSlices = new ArrayList();
        mVerticalGuidelines = new ArrayList();
        mHorizontalGuidelines = new ArrayList();
        system = null;
    }

    public ConstraintTableLayout(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        mVerticalGrowth = true;
        mNumCols = 0;
        mNumRows = 0;
        mPadding = 8;
        mVerticalSlices = new ArrayList();
        mHorizontalSlices = new ArrayList();
        mVerticalGuidelines = new ArrayList();
        mHorizontalGuidelines = new ArrayList();
        system = null;
    }

    private void setChildrenConnections()
    {
        int i;
        int j;
        int k;
        k = mChildren.size();
        j = 0;
        i = 0;
_L5:
        ConstraintWidget constraintwidget;
        Object obj;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_385;
        }
        constraintwidget = (ConstraintWidget)mChildren.get(i);
        j += constraintwidget.getContainerItemSkip();
        int l = mNumCols;
        int i1 = j / mNumCols;
        obj = (HorizontalSlice)mHorizontalSlices.get(i1);
        VerticalSlice verticalslice = (VerticalSlice)mVerticalSlices.get(j % l);
        ConstraintWidget constraintwidget1 = verticalslice.left;
        ConstraintWidget constraintwidget2 = verticalslice.right;
        ConstraintWidget constraintwidget3 = ((HorizontalSlice) (obj)).top;
        obj = ((HorizontalSlice) (obj)).bottom;
        constraintwidget.getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintwidget1.getAnchor(ConstraintAnchor.Type.LEFT), mPadding);
        if (constraintwidget2 instanceof Guideline)
        {
            constraintwidget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintwidget2.getAnchor(ConstraintAnchor.Type.LEFT), mPadding);
        } else
        {
            constraintwidget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintwidget2.getAnchor(ConstraintAnchor.Type.RIGHT), mPadding);
        }
        verticalslice.alignment;
        JVM INSTR tableswitch 1 3: default 196
    //                   1 301
    //                   2 330
    //                   3 291;
           goto _L1 _L2 _L3 _L4
_L1:
        constraintwidget.getAnchor(ConstraintAnchor.Type.TOP).connect(constraintwidget3.getAnchor(ConstraintAnchor.Type.TOP), mPadding);
        if (obj instanceof Guideline)
        {
            constraintwidget.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(((ConstraintWidget) (obj)).getAnchor(ConstraintAnchor.Type.TOP), mPadding);
        } else
        {
            constraintwidget.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(((ConstraintWidget) (obj)).getAnchor(ConstraintAnchor.Type.BOTTOM), mPadding);
        }
        j++;
        i++;
        if (true) goto _L5; else goto _L4
_L4:
        constraintwidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
          goto _L1
_L2:
        constraintwidget.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.STRONG);
        constraintwidget.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.WEAK);
          goto _L1
_L3:
        constraintwidget.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.WEAK);
        constraintwidget.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.STRONG);
          goto _L1
    }

    private void setHorizontalSlices()
    {
        mHorizontalSlices.clear();
        float f1 = 100F / (float)mNumRows;
        float f = f1;
        Object obj = this;
        int i = 0;
        while (i < mNumRows) 
        {
            HorizontalSlice horizontalslice = new HorizontalSlice();
            horizontalslice.top = ((ConstraintWidget) (obj));
            if (i < mNumRows - 1)
            {
                obj = new Guideline();
                ((Guideline) (obj)).setOrientation(0);
                ((Guideline) (obj)).setParent(this);
                ((Guideline) (obj)).setGuidePercent((int)f);
                f += f1;
                horizontalslice.bottom = ((ConstraintWidget) (obj));
                mHorizontalGuidelines.add(obj);
            } else
            {
                horizontalslice.bottom = this;
            }
            obj = horizontalslice.bottom;
            mHorizontalSlices.add(horizontalslice);
            i++;
        }
        updateDebugSolverNames();
    }

    private void setVerticalSlices()
    {
        mVerticalSlices.clear();
        Object obj = this;
        float f1 = 100F / (float)mNumCols;
        float f = f1;
        int i = 0;
        while (i < mNumCols) 
        {
            VerticalSlice verticalslice = new VerticalSlice();
            verticalslice.left = ((ConstraintWidget) (obj));
            if (i < mNumCols - 1)
            {
                obj = new Guideline();
                ((Guideline) (obj)).setOrientation(1);
                ((Guideline) (obj)).setParent(this);
                ((Guideline) (obj)).setGuidePercent((int)f);
                f += f1;
                verticalslice.right = ((ConstraintWidget) (obj));
                mVerticalGuidelines.add(obj);
            } else
            {
                verticalslice.right = this;
            }
            obj = verticalslice.right;
            mVerticalSlices.add(verticalslice);
            i++;
        }
        updateDebugSolverNames();
    }

    private void updateDebugSolverNames()
    {
        if (system != null)
        {
            int k = mVerticalGuidelines.size();
            for (int i = 0; i < k; i++)
            {
                ((Guideline)mVerticalGuidelines.get(i)).setDebugSolverName(system, (new StringBuilder()).append(getDebugName()).append(".VG").append(i).toString());
            }

            k = mHorizontalGuidelines.size();
            int j = 0;
            while (j < k) 
            {
                ((Guideline)mHorizontalGuidelines.get(j)).setDebugSolverName(system, (new StringBuilder()).append(getDebugName()).append(".HG").append(j).toString());
                j++;
            }
        }
    }

    public void addToSolver(LinearSystem linearsystem, int i)
    {
        int k;
        super.addToSolver(linearsystem, i);
        k = mChildren.size();
        if (k != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        setTableDimensions();
        if (linearsystem == mSystem)
        {
            int l = mVerticalGuidelines.size();
            int j = 0;
            while (j < l) 
            {
                Guideline guideline = (Guideline)mVerticalGuidelines.get(j);
                boolean flag;
                if (getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                guideline.setPositionRelaxed(flag);
                guideline.addToSolver(linearsystem, i);
                j++;
            }
            l = mHorizontalGuidelines.size();
            j = 0;
            while (j < l) 
            {
                Guideline guideline1 = (Guideline)mHorizontalGuidelines.get(j);
                boolean flag1;
                if (getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                guideline1.setPositionRelaxed(flag1);
                guideline1.addToSolver(linearsystem, i);
                j++;
            }
            j = 0;
            while (j < k) 
            {
                ((ConstraintWidget)mChildren.get(j)).addToSolver(linearsystem, i);
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void computeGuidelinesPercentPositions()
    {
        int k = mVerticalGuidelines.size();
        for (int i = 0; i < k; i++)
        {
            ((Guideline)mVerticalGuidelines.get(i)).inferRelativePercentPosition();
        }

        k = mHorizontalGuidelines.size();
        for (int j = 0; j < k; j++)
        {
            ((Guideline)mHorizontalGuidelines.get(j)).inferRelativePercentPosition();
        }

    }

    public void cycleColumnAlignment(int i)
    {
        VerticalSlice verticalslice = (VerticalSlice)mVerticalSlices.get(i);
        verticalslice.alignment;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 65
    //                   1 49
    //                   2 57;
           goto _L1 _L2 _L3 _L4
_L1:
        setChildrenConnections();
        return;
_L3:
        verticalslice.alignment = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        verticalslice.alignment = 1;
        continue; /* Loop/switch isn't completed */
_L2:
        verticalslice.alignment = 2;
        if (true) goto _L1; else goto _L5
_L5:
    }

    public String getColumnAlignmentRepresentation(int i)
    {
        VerticalSlice verticalslice = (VerticalSlice)mVerticalSlices.get(i);
        if (verticalslice.alignment == 1)
        {
            return "L";
        }
        if (verticalslice.alignment == 0)
        {
            return "C";
        }
        if (verticalslice.alignment == 3)
        {
            return "F";
        }
        if (verticalslice.alignment == 2)
        {
            return "R";
        } else
        {
            return "!";
        }
    }

    public String getColumnsAlignmentRepresentation()
    {
        int j = mVerticalSlices.size();
        String s1 = "";
        int i = 0;
        while (i < j) 
        {
            VerticalSlice verticalslice = (VerticalSlice)mVerticalSlices.get(i);
            String s;
            if (verticalslice.alignment == 1)
            {
                s = (new StringBuilder()).append(s1).append("L").toString();
            } else
            if (verticalslice.alignment == 0)
            {
                s = (new StringBuilder()).append(s1).append("C").toString();
            } else
            if (verticalslice.alignment == 3)
            {
                s = (new StringBuilder()).append(s1).append("F").toString();
            } else
            {
                s = s1;
                if (verticalslice.alignment == 2)
                {
                    s = (new StringBuilder()).append(s1).append("R").toString();
                }
            }
            i++;
            s1 = s;
        }
        return s1;
    }

    public ArrayList getHorizontalGuidelines()
    {
        return mHorizontalGuidelines;
    }

    public int getNumCols()
    {
        return mNumCols;
    }

    public int getNumRows()
    {
        return mNumRows;
    }

    public int getPadding()
    {
        return mPadding;
    }

    public String getType()
    {
        return "ConstraintTableLayout";
    }

    public ArrayList getVerticalGuidelines()
    {
        return mVerticalGuidelines;
    }

    public boolean handlesInternalConstraints()
    {
        return true;
    }

    public boolean isVerticalGrowth()
    {
        return mVerticalGrowth;
    }

    public void setColumnAlignment(int i, int j)
    {
        if (i < mVerticalSlices.size())
        {
            ((VerticalSlice)mVerticalSlices.get(i)).alignment = j;
            setChildrenConnections();
        }
    }

    public void setColumnAlignment(String s)
    {
        int i = 0;
        int j = s.length();
        while (i < j) 
        {
            char c = s.charAt(i);
            if (c == 'L')
            {
                setColumnAlignment(i, 1);
            } else
            if (c == 'C')
            {
                setColumnAlignment(i, 0);
            } else
            if (c == 'F')
            {
                setColumnAlignment(i, 3);
            } else
            if (c == 'R')
            {
                setColumnAlignment(i, 2);
            } else
            {
                setColumnAlignment(i, 0);
            }
            i++;
        }
    }

    public void setDebugSolverName(LinearSystem linearsystem, String s)
    {
        system = linearsystem;
        super.setDebugSolverName(linearsystem, s);
        updateDebugSolverNames();
    }

    public void setNumCols(int i)
    {
        if (mVerticalGrowth && mNumCols != i)
        {
            mNumCols = i;
            setVerticalSlices();
            setTableDimensions();
        }
    }

    public void setNumRows(int i)
    {
        if (!mVerticalGrowth && mNumCols != i)
        {
            mNumRows = i;
            setHorizontalSlices();
            setTableDimensions();
        }
    }

    public void setPadding(int i)
    {
        if (i > 1)
        {
            mPadding = i;
        }
    }

    public void setTableDimensions()
    {
        int k1;
        int l = 0;
        k1 = mChildren.size();
        for (int i = 0; i < k1; i++)
        {
            l += ((ConstraintWidget)mChildren.get(i)).getContainerItemSkip();
        }

        k1 += l;
        if (!mVerticalGrowth) goto _L2; else goto _L1
_L1:
        int j;
        if (mNumCols == 0)
        {
            setNumCols(1);
        }
        int i1 = k1 / mNumCols;
        j = i1;
        if (mNumCols * i1 < k1)
        {
            j = i1 + 1;
        }
        if (mNumRows != j || mVerticalGuidelines.size() != mNumCols - 1) goto _L4; else goto _L3
_L3:
        return;
_L4:
        mNumRows = j;
        setHorizontalSlices();
_L6:
        setChildrenConnections();
        return;
_L2:
        if (mNumRows == 0)
        {
            setNumRows(1);
        }
        int j1 = k1 / mNumRows;
        int k = j1;
        if (mNumRows * j1 < k1)
        {
            k = j1 + 1;
        }
        if (mNumCols == k && mHorizontalGuidelines.size() == mNumRows - 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        mNumCols = k;
        setVerticalSlices();
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L3; else goto _L7
_L7:
    }

    public void setVerticalGrowth(boolean flag)
    {
        mVerticalGrowth = flag;
    }

    public void updateFromSolver(LinearSystem linearsystem, int i)
    {
        super.updateFromSolver(linearsystem, i);
        if (linearsystem == mSystem)
        {
            int l = mVerticalGuidelines.size();
            for (int j = 0; j < l; j++)
            {
                ((Guideline)mVerticalGuidelines.get(j)).updateFromSolver(linearsystem, i);
            }

            l = mHorizontalGuidelines.size();
            for (int k = 0; k < l; k++)
            {
                ((Guideline)mHorizontalGuidelines.get(k)).updateFromSolver(linearsystem, i);
            }

        }
    }
}
