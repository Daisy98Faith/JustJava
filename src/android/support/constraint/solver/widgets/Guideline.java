// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget, Rectangle, ConstraintWidgetContainer, ConstraintAnchor

public class Guideline extends ConstraintWidget
{

    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNWON = -1;
    public static final int VERTICAL = 1;
    private ConstraintAnchor mAnchor;
    private Rectangle mHead;
    private int mHeadSize;
    private boolean mIsPositionRelaxed;
    private int mMinimumPosition;
    private int mOrientation;
    protected int mRelativeBegin;
    protected int mRelativeEnd;
    protected float mRelativePercent;

    public Guideline()
    {
        mRelativePercent = -1F;
        mRelativeBegin = -1;
        mRelativeEnd = -1;
        mAnchor = mTop;
        mOrientation = 0;
        mIsPositionRelaxed = false;
        mMinimumPosition = 0;
        mHead = new Rectangle();
        mHeadSize = 8;
        mAnchors.clear();
        mAnchors.add(mAnchor);
    }

    public void addToSolver(LinearSystem linearsystem, int i)
    {
        ConstraintWidgetContainer constraintwidgetcontainer = (ConstraintWidgetContainer)getParent();
        if (constraintwidgetcontainer != null)
        {
            ConstraintAnchor constraintanchor = constraintwidgetcontainer.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor constraintanchor1 = constraintwidgetcontainer.getAnchor(ConstraintAnchor.Type.RIGHT);
            if (mOrientation == 0)
            {
                constraintanchor = constraintwidgetcontainer.getAnchor(ConstraintAnchor.Type.TOP);
                constraintanchor1 = constraintwidgetcontainer.getAnchor(ConstraintAnchor.Type.BOTTOM);
            }
            if (mRelativeBegin != -1)
            {
                linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, linearsystem.createObjectVariable(mAnchor), linearsystem.createObjectVariable(constraintanchor), mRelativeBegin, false));
                return;
            }
            if (mRelativeEnd != -1)
            {
                linearsystem.addConstraint(LinearSystem.createRowEquals(linearsystem, linearsystem.createObjectVariable(mAnchor), linearsystem.createObjectVariable(constraintanchor1), -mRelativeEnd, false));
                return;
            }
            if (mRelativePercent != -1F)
            {
                linearsystem.addConstraint(LinearSystem.createRowDimensionPercent(linearsystem, linearsystem.createObjectVariable(mAnchor), linearsystem.createObjectVariable(constraintanchor), linearsystem.createObjectVariable(constraintanchor1), mRelativePercent, mIsPositionRelaxed));
                return;
            }
        }
    }

    public void cyclePosition()
    {
        if (mRelativeBegin != -1)
        {
            inferRelativePercentPosition();
        } else
        {
            if (mRelativePercent != -1F)
            {
                inferRelativeEndPosition();
                return;
            }
            if (mRelativeEnd != -1)
            {
                inferRelativeBeginPosition();
                return;
            }
        }
    }

    public ConstraintAnchor getAnchor()
    {
        return mAnchor;
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
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[type.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 42
    //                   2 42
    //                   3 55
    //                   4 55;
           goto _L1 _L2 _L2 _L3 _L3
_L1:
        return null;
_L2:
        if (mOrientation == 1)
        {
            return mAnchor;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mOrientation == 0)
        {
            return mAnchor;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public ArrayList getAnchors()
    {
        return mAnchors;
    }

    public Rectangle getHead()
    {
        mHead.setBounds(getDrawX() - mHeadSize, getDrawY() - mHeadSize * 2, mHeadSize * 2, mHeadSize * 2);
        if (getOrientation() == 0)
        {
            mHead.setBounds(getDrawX() - mHeadSize * 2, getDrawY() - mHeadSize, mHeadSize * 2, mHeadSize * 2);
        }
        return mHead;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getRelativeBegin()
    {
        return mRelativeBegin;
    }

    public int getRelativeBehaviour()
    {
        byte byte0 = -1;
        if (mRelativePercent != -1F)
        {
            byte0 = 0;
        } else
        {
            if (mRelativeBegin != -1)
            {
                return 1;
            }
            if (mRelativeEnd != -1)
            {
                return 2;
            }
        }
        return byte0;
    }

    public int getRelativeEnd()
    {
        return mRelativeEnd;
    }

    public float getRelativePercent()
    {
        return mRelativePercent;
    }

    public String getType()
    {
        return "Guideline";
    }

    void inferRelativeBeginPosition()
    {
        int i = getX();
        if (mOrientation == 0)
        {
            i = getY();
        }
        setGuideBegin(i);
    }

    void inferRelativeEndPosition()
    {
        int i = getParent().getWidth() - getX();
        if (mOrientation == 0)
        {
            i = getParent().getHeight() - getY();
        }
        setGuideEnd(i);
    }

    void inferRelativePercentPosition()
    {
        float f = (float)getX() / (float)getParent().getWidth();
        if (mOrientation == 0)
        {
            f = (float)getY() / (float)getParent().getHeight();
        }
        setGuidePercent(f);
    }

    public void setDrawOrigin(int i, int j)
    {
        if (mOrientation != 1) goto _L2; else goto _L1
_L1:
        i -= mOffsetX;
        if (mRelativeBegin == -1) goto _L4; else goto _L3
_L3:
        setGuideBegin(i);
_L6:
        return;
_L4:
        if (mRelativeEnd != -1)
        {
            setGuideEnd(getParent().getWidth() - i);
            return;
        }
        if (mRelativePercent != -1F)
        {
            setGuidePercent((float)i / (float)getParent().getWidth());
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        i = j - mOffsetY;
        if (mRelativeBegin != -1)
        {
            setGuideBegin(i);
            return;
        }
        if (mRelativeEnd != -1)
        {
            setGuideEnd(getParent().getHeight() - i);
            return;
        }
        if (mRelativePercent != -1F)
        {
            setGuidePercent((float)i / (float)getParent().getHeight());
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setGuideBegin(int i)
    {
        if (i > -1)
        {
            mRelativePercent = -1F;
            mRelativeBegin = i;
            mRelativeEnd = -1;
        }
    }

    public void setGuideEnd(int i)
    {
        if (i > -1)
        {
            mRelativePercent = -1F;
            mRelativeBegin = -1;
            mRelativeEnd = i;
        }
    }

    public void setGuidePercent(float f)
    {
        if (f > -1F)
        {
            mRelativePercent = f;
            mRelativeBegin = -1;
            mRelativeEnd = -1;
        }
    }

    public void setGuidePercent(int i)
    {
        setGuidePercent((float)i / 100F);
    }

    public void setMinimumPosition(int i)
    {
        mMinimumPosition = i;
    }

    public void setOrientation(int i)
    {
        if (mOrientation == i)
        {
            return;
        }
        mOrientation = i;
        mAnchors.clear();
        if (mOrientation == 1)
        {
            mAnchor = mLeft;
        } else
        {
            mAnchor = mTop;
        }
        mAnchors.add(mAnchor);
    }

    public void setPositionRelaxed(boolean flag)
    {
        if (mIsPositionRelaxed == flag)
        {
            return;
        } else
        {
            mIsPositionRelaxed = flag;
            return;
        }
    }

    public void updateFromSolver(LinearSystem linearsystem, int i)
    {
        if (getParent() == null)
        {
            return;
        }
        i = linearsystem.getObjectVariableValue(mAnchor);
        if (mOrientation == 1)
        {
            setX(i);
            setY(0);
            setHeight(getParent().getHeight());
            setWidth(0);
            return;
        } else
        {
            setX(0);
            setY(i);
            setWidth(getParent().getWidth());
            setHeight(0);
            return;
        }
    }
}
