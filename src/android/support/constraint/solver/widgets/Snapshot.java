// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget, ConstraintAnchor

public class Snapshot
{
    static class Connection
    {

        private ConstraintAnchor mAnchor;
        private int mCreator;
        private int mMargin;
        private ConstraintAnchor.Strength mStrengh;
        private ConstraintAnchor mTarget;

        public void applyTo(ConstraintWidget constraintwidget)
        {
            constraintwidget.getAnchor(mAnchor.getType()).connect(mTarget, mMargin, mStrengh, mCreator);
        }

        public void updateFrom(ConstraintWidget constraintwidget)
        {
            mAnchor = constraintwidget.getAnchor(mAnchor.getType());
            if (mAnchor != null)
            {
                mTarget = mAnchor.getTarget();
                mMargin = mAnchor.getMargin();
                mStrengh = mAnchor.getStrength();
                mCreator = mAnchor.getConnectionCreator();
                return;
            } else
            {
                mTarget = null;
                mMargin = 0;
                mStrengh = ConstraintAnchor.Strength.STRONG;
                mCreator = 0;
                return;
            }
        }

        public Connection(ConstraintAnchor constraintanchor)
        {
            mAnchor = constraintanchor;
            mTarget = constraintanchor.getTarget();
            mMargin = constraintanchor.getMargin();
            mStrengh = constraintanchor.getStrength();
            mCreator = constraintanchor.getConnectionCreator();
        }
    }


    private ArrayList mConnections;
    private int mHeight;
    private int mWidth;
    private int mX;
    private int mY;

    public Snapshot(ConstraintWidget constraintwidget)
    {
        mConnections = new ArrayList();
        mX = constraintwidget.getX();
        mY = constraintwidget.getY();
        mWidth = constraintwidget.getWidth();
        mHeight = constraintwidget.getHeight();
        constraintwidget = constraintwidget.getAnchors();
        int i = 0;
        for (int j = constraintwidget.size(); i < j; i++)
        {
            ConstraintAnchor constraintanchor = (ConstraintAnchor)constraintwidget.get(i);
            mConnections.add(new Connection(constraintanchor));
        }

    }

    public void applyTo(ConstraintWidget constraintwidget)
    {
        constraintwidget.setX(mX);
        constraintwidget.setY(mY);
        constraintwidget.setWidth(mWidth);
        constraintwidget.setHeight(mHeight);
        int i = 0;
        for (int j = mConnections.size(); i < j; i++)
        {
            ((Connection)mConnections.get(i)).applyTo(constraintwidget);
        }

    }

    public void updateFrom(ConstraintWidget constraintwidget)
    {
        mX = constraintwidget.getX();
        mY = constraintwidget.getY();
        mWidth = constraintwidget.getWidth();
        mHeight = constraintwidget.getHeight();
        int j = mConnections.size();
        for (int i = 0; i < j; i++)
        {
            ((Connection)mConnections.get(i)).updateFrom(constraintwidget);
        }

    }
}
