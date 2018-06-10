// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint:
//            ConstraintSet, Guideline

public class ConstraintLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        public void resolveLayoutDirection(int i)
        {
            boolean flag = true;
            super.resolveLayoutDirection(i);
            resolvedRightToLeft = -1;
            resolvedRightToRight = -1;
            resolvedLeftToLeft = -1;
            resolvedLeftToRight = -1;
            resolveGoneLeftMargin = -1;
            resolveGoneRightMargin = -1;
            resolveGoneLeftMargin = goneLeftMargin;
            resolveGoneRightMargin = goneRightMargin;
            resolvedHorizontalBias = horizontalBias;
            if (1 == getLayoutDirection())
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0) goto _L2; else goto _L1
_L1:
            if (startToEnd == -1) goto _L4; else goto _L3
_L3:
            resolvedRightToLeft = startToEnd;
_L12:
            if (endToStart != -1)
            {
                resolvedLeftToRight = endToStart;
            }
            if (endToEnd != -1)
            {
                resolvedLeftToLeft = endToEnd;
            }
            if (goneStartMargin != -1)
            {
                resolveGoneRightMargin = goneStartMargin;
            }
            if (goneEndMargin != -1)
            {
                resolveGoneLeftMargin = goneEndMargin;
            }
            resolvedHorizontalBias = 1.0F - horizontalBias;
_L9:
            if (endToStart == -1 && endToEnd == -1)
            {
                if (rightToLeft != -1)
                {
                    resolvedRightToLeft = rightToLeft;
                } else
                if (rightToRight != -1)
                {
                    resolvedRightToRight = rightToRight;
                }
            }
            if (startToStart != -1 || startToEnd != -1) goto _L6; else goto _L5
_L5:
            if (leftToLeft == -1) goto _L8; else goto _L7
_L7:
            resolvedLeftToLeft = leftToLeft;
_L6:
            return;
_L4:
            if (startToStart != -1)
            {
                resolvedRightToRight = startToStart;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (startToEnd != -1)
            {
                resolvedLeftToRight = startToEnd;
            }
            if (startToStart != -1)
            {
                resolvedLeftToLeft = startToStart;
            }
            if (endToStart != -1)
            {
                resolvedRightToLeft = endToStart;
            }
            if (endToEnd != -1)
            {
                resolvedRightToRight = endToEnd;
            }
            if (goneStartMargin != -1)
            {
                resolveGoneLeftMargin = goneStartMargin;
            }
            if (goneEndMargin != -1)
            {
                resolveGoneRightMargin = goneEndMargin;
            }
              goto _L9
_L8:
            if (leftToRight == -1) goto _L6; else goto _L10
_L10:
            resolvedLeftToRight = leftToRight;
            return;
            if (true) goto _L12; else goto _L11
_L11:
        }

        public void validate()
        {
            isGuideline = false;
            horizontalDimensionFixed = true;
            verticalDimensionFixed = true;
            if (width == 0 || width == -1)
            {
                horizontalDimensionFixed = false;
            }
            if (height == 0 || height == -1)
            {
                verticalDimensionFixed = false;
            }
            if (guidePercent != -1F || guideBegin != -1 || guideEnd != -1)
            {
                isGuideline = true;
                horizontalDimensionFixed = true;
                verticalDimensionFixed = true;
                if (!(widget instanceof Guideline))
                {
                    widget = new Guideline();
                }
                ((Guideline)widget).setOrientation(orientation);
            }
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            guideBegin = -1;
            guideEnd = -1;
            guidePercent = -1F;
            leftToLeft = -1;
            leftToRight = -1;
            rightToLeft = -1;
            rightToRight = -1;
            topToTop = -1;
            topToBottom = -1;
            bottomToTop = -1;
            bottomToBottom = -1;
            baselineToBaseline = -1;
            startToEnd = -1;
            startToStart = -1;
            endToStart = -1;
            endToEnd = -1;
            goneLeftMargin = -1;
            goneTopMargin = -1;
            goneRightMargin = -1;
            goneBottomMargin = -1;
            goneStartMargin = -1;
            goneEndMargin = -1;
            horizontalBias = 0.5F;
            verticalBias = 0.5F;
            dimensionRatio = null;
            dimensionRatioValue = 0.0F;
            dimensionRatioSide = 1;
            horizontalWeight = 0.0F;
            verticalWeight = 0.0F;
            horizontalChainStyle = 0;
            verticalChainStyle = 0;
            matchConstraintDefaultWidth = 0;
            matchConstraintDefaultHeight = 0;
            matchConstraintMinWidth = 0;
            matchConstraintMinHeight = 0;
            matchConstraintMaxWidth = 0;
            matchConstraintMaxHeight = 0;
            editorAbsoluteX = -1;
            editorAbsoluteY = -1;
            orientation = -1;
            horizontalDimensionFixed = true;
            verticalDimensionFixed = true;
            needsBaseline = false;
            isGuideline = false;
            resolvedLeftToLeft = -1;
            resolvedLeftToRight = -1;
            resolvedRightToLeft = -1;
            resolvedRightToRight = -1;
            resolveGoneLeftMargin = -1;
            resolveGoneRightMargin = -1;
            resolvedHorizontalBias = 0.5F;
            widget = new ConstraintWidget();
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            int i;
            int k;
            super(context, attributeset);
            guideBegin = -1;
            guideEnd = -1;
            guidePercent = -1F;
            leftToLeft = -1;
            leftToRight = -1;
            rightToLeft = -1;
            rightToRight = -1;
            topToTop = -1;
            topToBottom = -1;
            bottomToTop = -1;
            bottomToBottom = -1;
            baselineToBaseline = -1;
            startToEnd = -1;
            startToStart = -1;
            endToStart = -1;
            endToEnd = -1;
            goneLeftMargin = -1;
            goneTopMargin = -1;
            goneRightMargin = -1;
            goneBottomMargin = -1;
            goneStartMargin = -1;
            goneEndMargin = -1;
            horizontalBias = 0.5F;
            verticalBias = 0.5F;
            dimensionRatio = null;
            dimensionRatioValue = 0.0F;
            dimensionRatioSide = 1;
            horizontalWeight = 0.0F;
            verticalWeight = 0.0F;
            horizontalChainStyle = 0;
            verticalChainStyle = 0;
            matchConstraintDefaultWidth = 0;
            matchConstraintDefaultHeight = 0;
            matchConstraintMinWidth = 0;
            matchConstraintMinHeight = 0;
            matchConstraintMaxWidth = 0;
            matchConstraintMaxHeight = 0;
            editorAbsoluteX = -1;
            editorAbsoluteY = -1;
            orientation = -1;
            horizontalDimensionFixed = true;
            verticalDimensionFixed = true;
            needsBaseline = false;
            isGuideline = false;
            resolvedLeftToLeft = -1;
            resolvedLeftToRight = -1;
            resolvedRightToLeft = -1;
            resolvedRightToRight = -1;
            resolveGoneLeftMargin = -1;
            resolveGoneRightMargin = -1;
            resolvedHorizontalBias = 0.5F;
            widget = new ConstraintWidget();
            context = context.obtainStyledAttributes(attributeset, R.styleable.ConstraintLayout_Layout);
            k = context.getIndexCount();
            i = 0;
_L2:
            int j;
            if (i >= k)
            {
                break MISSING_BLOCK_LABEL_1803;
            }
            j = context.getIndex(i);
            if (j != R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf)
            {
                break; /* Loop/switch isn't completed */
            }
            leftToLeft = context.getResourceId(j, leftToLeft);
            if (leftToLeft == -1)
            {
                leftToLeft = context.getInt(j, -1);
            }
_L3:
            i++;
            if (true) goto _L2; else goto _L1
_L1:
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf)
            {
                leftToRight = context.getResourceId(j, leftToRight);
                if (leftToRight == -1)
                {
                    leftToRight = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf)
            {
                rightToLeft = context.getResourceId(j, rightToLeft);
                if (rightToLeft == -1)
                {
                    rightToLeft = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf)
            {
                rightToRight = context.getResourceId(j, rightToRight);
                if (rightToRight == -1)
                {
                    rightToRight = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf)
            {
                topToTop = context.getResourceId(j, topToTop);
                if (topToTop == -1)
                {
                    topToTop = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf)
            {
                topToBottom = context.getResourceId(j, topToBottom);
                if (topToBottom == -1)
                {
                    topToBottom = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf)
            {
                bottomToTop = context.getResourceId(j, bottomToTop);
                if (bottomToTop == -1)
                {
                    bottomToTop = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf)
            {
                bottomToBottom = context.getResourceId(j, bottomToBottom);
                if (bottomToBottom == -1)
                {
                    bottomToBottom = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf)
            {
                baselineToBaseline = context.getResourceId(j, baselineToBaseline);
                if (baselineToBaseline == -1)
                {
                    baselineToBaseline = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX)
            {
                editorAbsoluteX = context.getDimensionPixelOffset(j, editorAbsoluteX);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY)
            {
                editorAbsoluteY = context.getDimensionPixelOffset(j, editorAbsoluteY);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin)
            {
                guideBegin = context.getDimensionPixelOffset(j, guideBegin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end)
            {
                guideEnd = context.getDimensionPixelOffset(j, guideEnd);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent)
            {
                guidePercent = context.getFloat(j, guidePercent);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_android_orientation)
            {
                orientation = context.getInt(j, orientation);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf)
            {
                startToEnd = context.getResourceId(j, startToEnd);
                if (startToEnd == -1)
                {
                    startToEnd = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf)
            {
                startToStart = context.getResourceId(j, startToStart);
                if (startToStart == -1)
                {
                    startToStart = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf)
            {
                endToStart = context.getResourceId(j, endToStart);
                if (endToStart == -1)
                {
                    endToStart = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf)
            {
                endToEnd = context.getResourceId(j, endToEnd);
                if (endToEnd == -1)
                {
                    endToEnd = context.getInt(j, -1);
                }
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft)
            {
                goneLeftMargin = context.getDimensionPixelSize(j, goneLeftMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginTop)
            {
                goneTopMargin = context.getDimensionPixelSize(j, goneTopMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginRight)
            {
                goneRightMargin = context.getDimensionPixelSize(j, goneRightMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom)
            {
                goneBottomMargin = context.getDimensionPixelSize(j, goneBottomMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginStart)
            {
                goneStartMargin = context.getDimensionPixelSize(j, goneStartMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd)
            {
                goneEndMargin = context.getDimensionPixelSize(j, goneEndMargin);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias)
            {
                horizontalBias = context.getFloat(j, horizontalBias);
            } else
            {
label0:
                {
                    if (j != R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias)
                    {
                        break label0;
                    }
                    verticalBias = context.getFloat(j, verticalBias);
                }
            }
              goto _L3
            if (j != R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio)
            {
                break MISSING_BLOCK_LABEL_1528;
            }
            dimensionRatio = context.getString(j);
            dimensionRatioValue = (0.0F / 0.0F);
            dimensionRatioSide = -1;
            if (dimensionRatio == null) goto _L3; else goto _L4
_L4:
            float f;
            float f1;
            int l = dimensionRatio.length();
            j = dimensionRatio.indexOf(',');
            if (j > 0 && j < l - 1)
            {
                attributeset = dimensionRatio.substring(0, j);
                String s;
                int i1;
                if (attributeset.equalsIgnoreCase("W"))
                {
                    dimensionRatioSide = 0;
                } else
                if (attributeset.equalsIgnoreCase("H"))
                {
                    dimensionRatioSide = 1;
                }
                j++;
            } else
            {
                j = 0;
            }
            i1 = dimensionRatio.indexOf(':');
            if (i1 < 0 || i1 >= l - 1)
            {
                break MISSING_BLOCK_LABEL_1496;
            }
            attributeset = dimensionRatio.substring(j, i1);
            s = dimensionRatio.substring(i1 + 1);
            if (attributeset.length() <= 0 || s.length() <= 0) goto _L3; else goto _L5
_L5:
            f = Float.parseFloat(attributeset);
            f1 = Float.parseFloat(s);
            if (f <= 0.0F || f1 <= 0.0F) goto _L3; else goto _L6
_L6:
label1:
            {
                if (dimensionRatioSide != 1)
                {
                    break label1;
                }
                dimensionRatioValue = Math.abs(f1 / f);
            }
              goto _L3
            f /= f1;
            try
            {
                dimensionRatioValue = Math.abs(f);
            }
            // Misplaced declaration of an exception variable
            catch (AttributeSet attributeset) { }
              goto _L3
            attributeset = dimensionRatio.substring(j);
            if (attributeset.length() > 0)
            {
                try
                {
                    dimensionRatioValue = Float.parseFloat(attributeset);
                }
                // Misplaced declaration of an exception variable
                catch (AttributeSet attributeset) { }
            }
              goto _L3
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight)
            {
                horizontalWeight = context.getFloat(j, 0.0F);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight)
            {
                verticalWeight = context.getFloat(j, 0.0F);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle)
            {
                horizontalChainStyle = context.getInt(j, 0);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle)
            {
                verticalChainStyle = context.getInt(j, 0);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default)
            {
                matchConstraintDefaultWidth = context.getInt(j, 0);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default)
            {
                matchConstraintDefaultHeight = context.getInt(j, 0);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min)
            {
                matchConstraintMinWidth = context.getDimensionPixelSize(j, matchConstraintMinWidth);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max)
            {
                matchConstraintMaxWidth = context.getDimensionPixelSize(j, matchConstraintMaxWidth);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min)
            {
                matchConstraintMinHeight = context.getDimensionPixelSize(j, matchConstraintMinHeight);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max)
            {
                matchConstraintMaxHeight = context.getDimensionPixelSize(j, matchConstraintMaxHeight);
            } else
            if (j == R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator || j == R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator || j == R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator || j == R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator || j != R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator);
              goto _L3
            context.recycle();
            validate();
            return;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            guideBegin = -1;
            guideEnd = -1;
            guidePercent = -1F;
            leftToLeft = -1;
            leftToRight = -1;
            rightToLeft = -1;
            rightToRight = -1;
            topToTop = -1;
            topToBottom = -1;
            bottomToTop = -1;
            bottomToBottom = -1;
            baselineToBaseline = -1;
            startToEnd = -1;
            startToStart = -1;
            endToStart = -1;
            endToEnd = -1;
            goneLeftMargin = -1;
            goneTopMargin = -1;
            goneRightMargin = -1;
            goneBottomMargin = -1;
            goneStartMargin = -1;
            goneEndMargin = -1;
            horizontalBias = 0.5F;
            verticalBias = 0.5F;
            dimensionRatio = null;
            dimensionRatioValue = 0.0F;
            dimensionRatioSide = 1;
            horizontalWeight = 0.0F;
            verticalWeight = 0.0F;
            horizontalChainStyle = 0;
            verticalChainStyle = 0;
            matchConstraintDefaultWidth = 0;
            matchConstraintDefaultHeight = 0;
            matchConstraintMinWidth = 0;
            matchConstraintMinHeight = 0;
            matchConstraintMaxWidth = 0;
            matchConstraintMaxHeight = 0;
            editorAbsoluteX = -1;
            editorAbsoluteY = -1;
            orientation = -1;
            horizontalDimensionFixed = true;
            verticalDimensionFixed = true;
            needsBaseline = false;
            isGuideline = false;
            resolvedLeftToLeft = -1;
            resolvedLeftToRight = -1;
            resolvedRightToLeft = -1;
            resolvedRightToRight = -1;
            resolveGoneLeftMargin = -1;
            resolveGoneRightMargin = -1;
            resolvedHorizontalBias = 0.5F;
            widget = new ConstraintWidget();
            guideBegin = layoutparams.guideBegin;
            guideEnd = layoutparams.guideEnd;
            guidePercent = layoutparams.guidePercent;
            leftToLeft = layoutparams.leftToLeft;
            leftToRight = layoutparams.leftToRight;
            rightToLeft = layoutparams.rightToLeft;
            rightToRight = layoutparams.rightToRight;
            topToTop = layoutparams.topToTop;
            topToBottom = layoutparams.topToBottom;
            bottomToTop = layoutparams.bottomToTop;
            bottomToBottom = layoutparams.bottomToBottom;
            baselineToBaseline = layoutparams.baselineToBaseline;
            startToEnd = layoutparams.startToEnd;
            startToStart = layoutparams.startToStart;
            endToStart = layoutparams.endToStart;
            endToEnd = layoutparams.endToEnd;
            goneLeftMargin = layoutparams.goneLeftMargin;
            goneTopMargin = layoutparams.goneTopMargin;
            goneRightMargin = layoutparams.goneRightMargin;
            goneBottomMargin = layoutparams.goneBottomMargin;
            goneStartMargin = layoutparams.goneStartMargin;
            goneEndMargin = layoutparams.goneEndMargin;
            horizontalBias = layoutparams.horizontalBias;
            verticalBias = layoutparams.verticalBias;
            dimensionRatio = layoutparams.dimensionRatio;
            dimensionRatioValue = layoutparams.dimensionRatioValue;
            dimensionRatioSide = layoutparams.dimensionRatioSide;
            horizontalWeight = layoutparams.horizontalWeight;
            verticalWeight = layoutparams.verticalWeight;
            horizontalChainStyle = layoutparams.horizontalChainStyle;
            verticalChainStyle = layoutparams.verticalChainStyle;
            matchConstraintDefaultWidth = layoutparams.matchConstraintDefaultWidth;
            matchConstraintDefaultHeight = layoutparams.matchConstraintDefaultHeight;
            matchConstraintMinWidth = layoutparams.matchConstraintMinWidth;
            matchConstraintMaxWidth = layoutparams.matchConstraintMaxWidth;
            matchConstraintMinHeight = layoutparams.matchConstraintMinHeight;
            matchConstraintMaxHeight = layoutparams.matchConstraintMaxHeight;
            editorAbsoluteX = layoutparams.editorAbsoluteX;
            editorAbsoluteY = layoutparams.editorAbsoluteY;
            orientation = layoutparams.orientation;
            horizontalDimensionFixed = layoutparams.horizontalDimensionFixed;
            verticalDimensionFixed = layoutparams.verticalDimensionFixed;
            needsBaseline = layoutparams.needsBaseline;
            isGuideline = layoutparams.isGuideline;
            resolvedLeftToLeft = layoutparams.resolvedLeftToLeft;
            resolvedLeftToRight = layoutparams.resolvedLeftToRight;
            resolvedRightToLeft = layoutparams.resolvedRightToLeft;
            resolvedRightToRight = layoutparams.resolvedRightToRight;
            resolveGoneLeftMargin = layoutparams.resolveGoneLeftMargin;
            resolveGoneRightMargin = layoutparams.resolveGoneRightMargin;
            resolvedHorizontalBias = layoutparams.resolvedHorizontalBias;
            widget = layoutparams.widget;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            guideBegin = -1;
            guideEnd = -1;
            guidePercent = -1F;
            leftToLeft = -1;
            leftToRight = -1;
            rightToLeft = -1;
            rightToRight = -1;
            topToTop = -1;
            topToBottom = -1;
            bottomToTop = -1;
            bottomToBottom = -1;
            baselineToBaseline = -1;
            startToEnd = -1;
            startToStart = -1;
            endToStart = -1;
            endToEnd = -1;
            goneLeftMargin = -1;
            goneTopMargin = -1;
            goneRightMargin = -1;
            goneBottomMargin = -1;
            goneStartMargin = -1;
            goneEndMargin = -1;
            horizontalBias = 0.5F;
            verticalBias = 0.5F;
            dimensionRatio = null;
            dimensionRatioValue = 0.0F;
            dimensionRatioSide = 1;
            horizontalWeight = 0.0F;
            verticalWeight = 0.0F;
            horizontalChainStyle = 0;
            verticalChainStyle = 0;
            matchConstraintDefaultWidth = 0;
            matchConstraintDefaultHeight = 0;
            matchConstraintMinWidth = 0;
            matchConstraintMinHeight = 0;
            matchConstraintMaxWidth = 0;
            matchConstraintMaxHeight = 0;
            editorAbsoluteX = -1;
            editorAbsoluteY = -1;
            orientation = -1;
            horizontalDimensionFixed = true;
            verticalDimensionFixed = true;
            needsBaseline = false;
            isGuideline = false;
            resolvedLeftToLeft = -1;
            resolvedLeftToRight = -1;
            resolvedRightToLeft = -1;
            resolvedRightToRight = -1;
            resolveGoneLeftMargin = -1;
            resolveGoneRightMargin = -1;
            resolvedHorizontalBias = 0.5F;
            widget = new ConstraintWidget();
        }
    }


    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean SIMPLE_LAYOUT = true;
    private static final String TAG = "ConstraintLayout";
    public static final String VERSION = "ConstraintLayout-1.0.0";
    SparseArray mChildrenByIds;
    private ConstraintSet mConstraintSet;
    private boolean mDirtyHierarchy;
    ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mMinHeight;
    private int mMinWidth;
    private int mOptimizationLevel;
    private final ArrayList mVariableDimensionsWidgets;

    public ConstraintLayout(Context context)
    {
        super(context);
        mChildrenByIds = new SparseArray();
        mVariableDimensionsWidgets = new ArrayList(100);
        mLayoutWidget = new ConstraintWidgetContainer();
        mMinWidth = 0;
        mMinHeight = 0;
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        mDirtyHierarchy = true;
        mOptimizationLevel = 2;
        mConstraintSet = null;
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mChildrenByIds = new SparseArray();
        mVariableDimensionsWidgets = new ArrayList(100);
        mLayoutWidget = new ConstraintWidgetContainer();
        mMinWidth = 0;
        mMinHeight = 0;
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        mDirtyHierarchy = true;
        mOptimizationLevel = 2;
        mConstraintSet = null;
        init(attributeset);
    }

    public ConstraintLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mChildrenByIds = new SparseArray();
        mVariableDimensionsWidgets = new ArrayList(100);
        mLayoutWidget = new ConstraintWidgetContainer();
        mMinWidth = 0;
        mMinHeight = 0;
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        mDirtyHierarchy = true;
        mOptimizationLevel = 2;
        mConstraintSet = null;
        init(attributeset);
    }

    private final ConstraintWidget getTargetWidget(int i)
    {
        if (i == 0)
        {
            return mLayoutWidget;
        }
        View view = (View)mChildrenByIds.get(i);
        if (view == this)
        {
            return mLayoutWidget;
        }
        if (view == null)
        {
            return null;
        } else
        {
            return ((LayoutParams)view.getLayoutParams()).widget;
        }
    }

    private final ConstraintWidget getViewWidget(View view)
    {
        if (view == this)
        {
            return mLayoutWidget;
        }
        if (view == null)
        {
            return null;
        } else
        {
            return ((LayoutParams)view.getLayoutParams()).widget;
        }
    }

    private void init(AttributeSet attributeset)
    {
        mLayoutWidget.setCompanionWidget(this);
        mChildrenByIds.put(getId(), this);
        mConstraintSet = null;
        if (attributeset != null)
        {
            attributeset = getContext().obtainStyledAttributes(attributeset, R.styleable.ConstraintLayout_Layout);
            int j = attributeset.getIndexCount();
            int i = 0;
            while (i < j) 
            {
                int k = attributeset.getIndex(i);
                if (k == R.styleable.ConstraintLayout_Layout_android_minWidth)
                {
                    mMinWidth = attributeset.getDimensionPixelOffset(k, mMinWidth);
                } else
                if (k == R.styleable.ConstraintLayout_Layout_android_minHeight)
                {
                    mMinHeight = attributeset.getDimensionPixelOffset(k, mMinHeight);
                } else
                if (k == R.styleable.ConstraintLayout_Layout_android_maxWidth)
                {
                    mMaxWidth = attributeset.getDimensionPixelOffset(k, mMaxWidth);
                } else
                if (k == R.styleable.ConstraintLayout_Layout_android_maxHeight)
                {
                    mMaxHeight = attributeset.getDimensionPixelOffset(k, mMaxHeight);
                } else
                if (k == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel)
                {
                    mOptimizationLevel = attributeset.getInt(k, mOptimizationLevel);
                } else
                if (k == R.styleable.ConstraintLayout_Layout_constraintSet)
                {
                    k = attributeset.getResourceId(k, 0);
                    mConstraintSet = new ConstraintSet();
                    mConstraintSet.load(getContext(), k);
                }
                i++;
            }
            attributeset.recycle();
        }
        mLayoutWidget.setOptimizationLevel(mOptimizationLevel);
    }

    private void internalMeasureChildren(int i, int j)
    {
        int i2 = getPaddingTop() + getPaddingBottom();
        int j2 = getPaddingLeft() + getPaddingRight();
        int k2 = getChildCount();
        int l = 0;
        while (l < k2) 
        {
            View view = getChildAt(l);
            if (view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                ConstraintWidget constraintwidget = layoutparams.widget;
                if (!layoutparams.isGuideline)
                {
                    int l1 = layoutparams.width;
                    int k1 = layoutparams.height;
                    int k;
                    boolean flag;
                    int i1;
                    int j1;
                    boolean flag1;
                    boolean flag2;
                    boolean flag3;
                    if (layoutparams.horizontalDimensionFixed || layoutparams.verticalDimensionFixed || !layoutparams.horizontalDimensionFixed && layoutparams.matchConstraintDefaultWidth == 1 || layoutparams.width == -1 || !layoutparams.verticalDimensionFixed && (layoutparams.matchConstraintDefaultHeight == 1 || layoutparams.height == -1))
                    {
                        k = 1;
                    } else
                    {
                        k = 0;
                    }
                    flag1 = false;
                    flag3 = false;
                    flag = false;
                    flag2 = false;
                    j1 = k1;
                    i1 = l1;
                    if (k != 0)
                    {
                        if (l1 == 0 || l1 == -1)
                        {
                            i1 = getChildMeasureSpec(i, j2, -2);
                            k = 1;
                        } else
                        {
                            i1 = getChildMeasureSpec(i, j2, l1);
                            k = ((flag3) ? 1 : 0);
                        }
                        if (k1 == 0 || k1 == -1)
                        {
                            j1 = getChildMeasureSpec(j, i2, -2);
                            flag = true;
                        } else
                        {
                            j1 = getChildMeasureSpec(j, i2, k1);
                            flag = flag2;
                        }
                        view.measure(i1, j1);
                        i1 = view.getMeasuredWidth();
                        j1 = view.getMeasuredHeight();
                        flag1 = k;
                    }
                    constraintwidget.setWidth(i1);
                    constraintwidget.setHeight(j1);
                    if (flag1)
                    {
                        constraintwidget.setWrapWidth(i1);
                    }
                    if (flag)
                    {
                        constraintwidget.setWrapHeight(j1);
                    }
                    if (layoutparams.needsBaseline)
                    {
                        k = view.getBaseline();
                        if (k != -1)
                        {
                            constraintwidget.setBaselineDistance(k);
                        }
                    }
                }
            }
            l++;
        }
    }

    private void setChildrenConstraints()
    {
        if (mConstraintSet != null)
        {
            mConstraintSet.applyToInternal(this);
        }
        int j3 = getChildCount();
        mLayoutWidget.removeAllChildren();
        int i1 = 0;
        do
        {
            if (i1 < j3)
            {
                View view = getChildAt(i1);
                Object obj = getViewWidget(view);
                if (obj != null)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    ((ConstraintWidget) (obj)).reset();
                    ((ConstraintWidget) (obj)).setVisibility(view.getVisibility());
                    ((ConstraintWidget) (obj)).setCompanionWidget(view);
                    mLayoutWidget.add(((ConstraintWidget) (obj)));
                    if (!layoutparams.verticalDimensionFixed || !layoutparams.horizontalDimensionFixed)
                    {
                        mVariableDimensionsWidgets.add(obj);
                    }
                    if (layoutparams.isGuideline)
                    {
                        obj = (Guideline)obj;
                        if (layoutparams.guideBegin != -1)
                        {
                            ((Guideline) (obj)).setGuideBegin(layoutparams.guideBegin);
                        }
                        if (layoutparams.guideEnd != -1)
                        {
                            ((Guideline) (obj)).setGuideEnd(layoutparams.guideEnd);
                        }
                        if (layoutparams.guidePercent != -1F)
                        {
                            ((Guideline) (obj)).setGuidePercent(layoutparams.guidePercent);
                        }
                    } else
                    if (layoutparams.resolvedLeftToLeft != -1 || layoutparams.resolvedLeftToRight != -1 || layoutparams.resolvedRightToLeft != -1 || layoutparams.resolvedRightToRight != -1 || layoutparams.topToTop != -1 || layoutparams.topToBottom != -1 || layoutparams.bottomToTop != -1 || layoutparams.bottomToBottom != -1 || layoutparams.baselineToBaseline != -1 || layoutparams.editorAbsoluteX != -1 || layoutparams.editorAbsoluteY != -1 || layoutparams.width == -1 || layoutparams.height == -1)
                    {
                        int l1 = layoutparams.resolvedLeftToLeft;
                        int i2 = layoutparams.resolvedLeftToRight;
                        int k = layoutparams.resolvedRightToLeft;
                        int l = layoutparams.resolvedRightToRight;
                        int j1 = layoutparams.resolveGoneLeftMargin;
                        int k1 = layoutparams.resolveGoneRightMargin;
                        float f = layoutparams.resolvedHorizontalBias;
                        if (android.os.Build.VERSION.SDK_INT < 17)
                        {
                            k = layoutparams.leftToLeft;
                            l = layoutparams.leftToRight;
                            int l2 = layoutparams.rightToLeft;
                            int i3 = layoutparams.rightToRight;
                            int j2 = layoutparams.goneLeftMargin;
                            int k2 = layoutparams.goneRightMargin;
                            float f1 = layoutparams.horizontalBias;
                            int i = k;
                            int j = l;
                            if (k == -1)
                            {
                                i = k;
                                j = l;
                                ConstraintWidget constraintwidget;
                                Object obj1;
                                if (l == -1)
                                {
                                    if (layoutparams.startToStart != -1)
                                    {
                                        i = layoutparams.startToStart;
                                        j = l;
                                    } else
                                    {
                                        i = k;
                                        j = l;
                                        if (layoutparams.startToEnd != -1)
                                        {
                                            j = layoutparams.startToEnd;
                                            i = k;
                                        }
                                    }
                                }
                            }
                            j1 = j2;
                            k1 = k2;
                            f = f1;
                            l1 = i;
                            i2 = j;
                            k = l2;
                            l = i3;
                            if (l2 == -1)
                            {
                                j1 = j2;
                                k1 = k2;
                                f = f1;
                                l1 = i;
                                i2 = j;
                                k = l2;
                                l = i3;
                                if (i3 == -1)
                                {
                                    if (layoutparams.endToStart != -1)
                                    {
                                        k = layoutparams.endToStart;
                                        l = i3;
                                        i2 = j;
                                        l1 = i;
                                        f = f1;
                                        k1 = k2;
                                        j1 = j2;
                                    } else
                                    {
                                        j1 = j2;
                                        k1 = k2;
                                        f = f1;
                                        l1 = i;
                                        i2 = j;
                                        k = l2;
                                        l = i3;
                                        if (layoutparams.endToEnd != -1)
                                        {
                                            l = layoutparams.endToEnd;
                                            j1 = j2;
                                            k1 = k2;
                                            f = f1;
                                            l1 = i;
                                            i2 = j;
                                            k = l2;
                                        }
                                    }
                                }
                            }
                        }
                        if (l1 != -1)
                        {
                            constraintwidget = getTargetWidget(l1);
                            if (constraintwidget != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT, constraintwidget, android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT, layoutparams.leftMargin, j1);
                            }
                        } else
                        if (i2 != -1)
                        {
                            ConstraintWidget constraintwidget1 = getTargetWidget(i2);
                            if (constraintwidget1 != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT, constraintwidget1, android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT, layoutparams.leftMargin, j1);
                            }
                        }
                        if (k != -1)
                        {
                            constraintwidget = getTargetWidget(k);
                            if (constraintwidget != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT, constraintwidget, android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT, layoutparams.rightMargin, k1);
                            }
                        } else
                        if (l != -1)
                        {
                            ConstraintWidget constraintwidget2 = getTargetWidget(l);
                            if (constraintwidget2 != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT, constraintwidget2, android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT, layoutparams.rightMargin, k1);
                            }
                        }
                        if (layoutparams.topToTop != -1)
                        {
                            constraintwidget = getTargetWidget(layoutparams.topToTop);
                            if (constraintwidget != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP, constraintwidget, android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP, layoutparams.topMargin, layoutparams.goneTopMargin);
                            }
                        } else
                        if (layoutparams.topToBottom != -1)
                        {
                            ConstraintWidget constraintwidget3 = getTargetWidget(layoutparams.topToBottom);
                            if (constraintwidget3 != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP, constraintwidget3, android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM, layoutparams.topMargin, layoutparams.goneTopMargin);
                            }
                        }
                        if (layoutparams.bottomToTop != -1)
                        {
                            constraintwidget = getTargetWidget(layoutparams.bottomToTop);
                            if (constraintwidget != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM, constraintwidget, android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP, layoutparams.bottomMargin, layoutparams.goneBottomMargin);
                            }
                        } else
                        if (layoutparams.bottomToBottom != -1)
                        {
                            ConstraintWidget constraintwidget4 = getTargetWidget(layoutparams.bottomToBottom);
                            if (constraintwidget4 != null)
                            {
                                ((ConstraintWidget) (obj)).immediateConnect(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM, constraintwidget4, android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM, layoutparams.bottomMargin, layoutparams.goneBottomMargin);
                            }
                        }
                        if (layoutparams.baselineToBaseline != -1)
                        {
                            obj1 = (View)mChildrenByIds.get(layoutparams.baselineToBaseline);
                            constraintwidget = getTargetWidget(layoutparams.baselineToBaseline);
                            if (constraintwidget != null && obj1 != null && (((View) (obj1)).getLayoutParams() instanceof LayoutParams))
                            {
                                obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
                                layoutparams.needsBaseline = true;
                                obj1.needsBaseline = true;
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE).connect(constraintwidget.getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE), 0, -1, android.support.constraint.solver.widgets.ConstraintAnchor.Strength.STRONG, 0, true);
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP).reset();
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM).reset();
                            }
                        }
                        if (f >= 0.0F && f != 0.5F)
                        {
                            ((ConstraintWidget) (obj)).setHorizontalBiasPercent(f);
                        }
                        if (layoutparams.verticalBias >= 0.0F && layoutparams.verticalBias != 0.5F)
                        {
                            ((ConstraintWidget) (obj)).setVerticalBiasPercent(layoutparams.verticalBias);
                        }
                        if (isInEditMode() && (layoutparams.editorAbsoluteX != -1 || layoutparams.editorAbsoluteY != -1))
                        {
                            ((ConstraintWidget) (obj)).setOrigin(layoutparams.editorAbsoluteX, layoutparams.editorAbsoluteY);
                        }
                        if (!layoutparams.horizontalDimensionFixed)
                        {
                            if (layoutparams.width == -1)
                            {
                                ((ConstraintWidget) (obj)).setHorizontalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT).mMargin = layoutparams.leftMargin;
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT).mMargin = layoutparams.rightMargin;
                            } else
                            {
                                ((ConstraintWidget) (obj)).setHorizontalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                                ((ConstraintWidget) (obj)).setWidth(0);
                            }
                        } else
                        {
                            ((ConstraintWidget) (obj)).setHorizontalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED);
                            ((ConstraintWidget) (obj)).setWidth(layoutparams.width);
                        }
                        if (!layoutparams.verticalDimensionFixed)
                        {
                            if (layoutparams.height == -1)
                            {
                                ((ConstraintWidget) (obj)).setVerticalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP).mMargin = layoutparams.topMargin;
                                ((ConstraintWidget) (obj)).getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM).mMargin = layoutparams.bottomMargin;
                            } else
                            {
                                ((ConstraintWidget) (obj)).setVerticalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                                ((ConstraintWidget) (obj)).setHeight(0);
                            }
                        } else
                        {
                            ((ConstraintWidget) (obj)).setVerticalDimensionBehaviour(android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED);
                            ((ConstraintWidget) (obj)).setHeight(layoutparams.height);
                        }
                        if (layoutparams.dimensionRatio != null)
                        {
                            ((ConstraintWidget) (obj)).setDimensionRatio(layoutparams.dimensionRatio);
                        }
                        ((ConstraintWidget) (obj)).setHorizontalWeight(layoutparams.horizontalWeight);
                        ((ConstraintWidget) (obj)).setVerticalWeight(layoutparams.verticalWeight);
                        ((ConstraintWidget) (obj)).setHorizontalChainStyle(layoutparams.horizontalChainStyle);
                        ((ConstraintWidget) (obj)).setVerticalChainStyle(layoutparams.verticalChainStyle);
                        ((ConstraintWidget) (obj)).setHorizontalMatchStyle(layoutparams.matchConstraintDefaultWidth, layoutparams.matchConstraintMinWidth, layoutparams.matchConstraintMaxWidth);
                        ((ConstraintWidget) (obj)).setVerticalMatchStyle(layoutparams.matchConstraintDefaultHeight, layoutparams.matchConstraintMinHeight, layoutparams.matchConstraintMaxHeight);
                    }
                }
            } else
            {
                return;
            }
            i1++;
        } while (true);
    }

    private void setSelfDimensionBehaviour(int i, int j)
    {
        android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour dimensionbehaviour;
        android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour dimensionbehaviour1;
        boolean flag;
        boolean flag1;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        j1 = android.view.View.MeasureSpec.getMode(i);
        i = android.view.View.MeasureSpec.getSize(i);
        k = android.view.View.MeasureSpec.getMode(j);
        j = android.view.View.MeasureSpec.getSize(j);
        l = getPaddingTop();
        i1 = getPaddingBottom();
        k1 = getPaddingLeft();
        l1 = getPaddingRight();
        dimensionbehaviour = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        dimensionbehaviour1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        flag1 = false;
        flag = false;
        getLayoutParams();
        j1;
        JVM INSTR lookupswitch 3: default 104
    //                   -2147483648: 239
    //                   0: 246
    //                   1073741824: 256;
           goto _L1 _L2 _L3 _L4
_L1:
        i = ((flag1) ? 1 : 0);
_L9:
        k;
        JVM INSTR lookupswitch 3: default 144
    //                   -2147483648: 274
    //                   0: 282
    //                   1073741824: 293;
           goto _L5 _L6 _L7 _L8
_L5:
        j = ((flag) ? 1 : 0);
_L10:
        mLayoutWidget.setMinWidth(0);
        mLayoutWidget.setMinHeight(0);
        mLayoutWidget.setHorizontalDimensionBehaviour(dimensionbehaviour);
        mLayoutWidget.setWidth(i);
        mLayoutWidget.setVerticalDimensionBehaviour(dimensionbehaviour1);
        mLayoutWidget.setHeight(j);
        mLayoutWidget.setMinWidth(mMinWidth - getPaddingLeft() - getPaddingRight());
        mLayoutWidget.setMinHeight(mMinHeight - getPaddingTop() - getPaddingBottom());
        return;
_L2:
        dimensionbehaviour = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
          goto _L9
_L3:
        dimensionbehaviour = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        i = ((flag1) ? 1 : 0);
          goto _L9
_L4:
        i = Math.min(mMaxWidth, i) - (k1 + l1);
          goto _L9
_L6:
        dimensionbehaviour1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
          goto _L10
_L7:
        dimensionbehaviour1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        j = ((flag) ? 1 : 0);
          goto _L10
_L8:
        j = Math.min(mMaxHeight, j) - (l + i1);
          goto _L10
    }

    private void updateHierarchy()
    {
        int j = getChildCount();
        boolean flag1 = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    if (!getChildAt(i).isLayoutRequested())
                    {
                        break label0;
                    }
                    flag = true;
                }
                if (flag)
                {
                    mVariableDimensionsWidgets.clear();
                    setChildrenConstraints();
                }
                return;
            }
            i++;
        } while (true);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if (android.os.Build.VERSION.SDK_INT < 14)
        {
            onViewAdded(view);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public int getMaxHeight()
    {
        return mMaxHeight;
    }

    public int getMaxWidth()
    {
        return mMaxWidth;
    }

    public int getMinHeight()
    {
        return mMinHeight;
    }

    public int getMinWidth()
    {
        return mMinWidth;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = getChildCount();
        flag = isInEditMode();
        i = 0;
        while (i < j) 
        {
            View view = getChildAt(i);
            Object obj = (LayoutParams)view.getLayoutParams();
            if (view.getVisibility() != 8 || ((LayoutParams) (obj)).isGuideline || flag)
            {
                obj = ((LayoutParams) (obj)).widget;
                k = ((ConstraintWidget) (obj)).getDrawX();
                l = ((ConstraintWidget) (obj)).getDrawY();
                view.layout(k, l, k + ((ConstraintWidget) (obj)).getWidth(), l + ((ConstraintWidget) (obj)).getHeight());
            }
            i++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int l = getPaddingLeft();
        int j1 = getPaddingTop();
        mLayoutWidget.setX(l);
        mLayoutWidget.setY(j1);
        setSelfDimensionBehaviour(i, j);
        if (mDirtyHierarchy)
        {
            mDirtyHierarchy = false;
            updateHierarchy();
        }
        internalMeasureChildren(i, j);
        if (getChildCount() > 0)
        {
            solveLinearSystem();
        }
        int i1 = 0;
        int k = 0;
        int i2 = mVariableDimensionsWidgets.size();
        int j2 = j1 + getPaddingBottom();
        int k2 = l + getPaddingRight();
        if (i2 > 0)
        {
            l = 0;
            boolean flag;
            boolean flag1;
            int k1;
            if (mLayoutWidget.getHorizontalDimensionBehaviour() == android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (mLayoutWidget.getVerticalDimensionBehaviour() == android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            k1 = 0;
            while (k1 < i2) 
            {
                ConstraintWidget constraintwidget = (ConstraintWidget)mVariableDimensionsWidgets.get(k1);
                int l1;
                if (constraintwidget instanceof Guideline)
                {
                    l1 = l;
                    i1 = k;
                } else
                {
                    View view = (View)constraintwidget.getCompanionWidget();
                    i1 = k;
                    l1 = l;
                    if (view != null)
                    {
                        i1 = k;
                        l1 = l;
                        if (view.getVisibility() != 8)
                        {
                            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                            if (layoutparams.width == -2)
                            {
                                i1 = getChildMeasureSpec(i, k2, layoutparams.width);
                            } else
                            {
                                i1 = android.view.View.MeasureSpec.makeMeasureSpec(constraintwidget.getWidth(), 0x40000000);
                            }
                            if (layoutparams.height == -2)
                            {
                                l1 = getChildMeasureSpec(j, j2, layoutparams.height);
                            } else
                            {
                                l1 = android.view.View.MeasureSpec.makeMeasureSpec(constraintwidget.getHeight(), 0x40000000);
                            }
                            view.measure(i1, l1);
                            i1 = view.getMeasuredWidth();
                            l1 = view.getMeasuredHeight();
                            if (i1 != constraintwidget.getWidth())
                            {
                                constraintwidget.setWidth(i1);
                                if (flag && constraintwidget.getRight() > mLayoutWidget.getWidth())
                                {
                                    l = constraintwidget.getRight();
                                    i1 = constraintwidget.getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT).getMargin();
                                    mLayoutWidget.setWidth(Math.max(mMinWidth, l + i1));
                                }
                                l = 1;
                            }
                            i1 = l;
                            if (l1 != constraintwidget.getHeight())
                            {
                                constraintwidget.setHeight(l1);
                                if (flag1 && constraintwidget.getBottom() > mLayoutWidget.getHeight())
                                {
                                    l = constraintwidget.getBottom();
                                    i1 = constraintwidget.getAnchor(android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM).getMargin();
                                    mLayoutWidget.setHeight(Math.max(mMinHeight, l + i1));
                                }
                                i1 = 1;
                            }
                            l = i1;
                            if (layoutparams.needsBaseline)
                            {
                                l1 = view.getBaseline();
                                l = i1;
                                if (l1 != -1)
                                {
                                    l = i1;
                                    if (l1 != constraintwidget.getBaselineDistance())
                                    {
                                        constraintwidget.setBaselineDistance(l1);
                                        l = 1;
                                    }
                                }
                            }
                            i1 = k;
                            l1 = l;
                            if (android.os.Build.VERSION.SDK_INT >= 11)
                            {
                                i1 = combineMeasuredStates(k, view.getMeasuredState());
                                l1 = l;
                            }
                        }
                    }
                }
                k1++;
                k = i1;
                l = l1;
            }
            i1 = k;
            if (l != 0)
            {
                solveLinearSystem();
                i1 = k;
            }
        }
        k = mLayoutWidget.getWidth() + k2;
        l = mLayoutWidget.getHeight() + j2;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            i = resolveSizeAndState(k, i, i1);
            j = resolveSizeAndState(l, j, i1 << 16);
            i = Math.min(mMaxWidth, i);
            k = Math.min(mMaxHeight, j);
            j = i & 0xffffff;
            k &= 0xffffff;
            i = j;
            if (mLayoutWidget.isWidthMeasuredTooSmall())
            {
                i = j | 0x1000000;
            }
            j = k;
            if (mLayoutWidget.isHeightMeasuredTooSmall())
            {
                j = k | 0x1000000;
            }
            setMeasuredDimension(i, j);
            return;
        } else
        {
            setMeasuredDimension(k, l);
            return;
        }
    }

    public void onViewAdded(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            super.onViewAdded(view);
        }
        ConstraintWidget constraintwidget = getViewWidget(view);
        if ((view instanceof android.support.constraint.Guideline) && !(constraintwidget instanceof Guideline))
        {
            Object obj = (LayoutParams)view.getLayoutParams();
            obj.widget = new Guideline();
            obj.isGuideline = true;
            ((Guideline)((LayoutParams) (obj)).widget).setOrientation(((LayoutParams) (obj)).orientation);
            obj = ((LayoutParams) (obj)).widget;
        }
        mChildrenByIds.put(view.getId(), view);
        mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            super.onViewRemoved(view);
        }
        mChildrenByIds.remove(view.getId());
        mLayoutWidget.remove(getViewWidget(view));
        mDirtyHierarchy = true;
    }

    public void removeView(View view)
    {
        super.removeView(view);
        if (android.os.Build.VERSION.SDK_INT < 14)
        {
            onViewRemoved(view);
        }
    }

    public void requestLayout()
    {
        super.requestLayout();
        mDirtyHierarchy = true;
    }

    public void setConstraintSet(ConstraintSet constraintset)
    {
        mConstraintSet = constraintset;
    }

    public void setId(int i)
    {
        mChildrenByIds.remove(getId());
        super.setId(i);
        mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i)
    {
        if (i == mMaxHeight)
        {
            return;
        } else
        {
            mMaxHeight = i;
            requestLayout();
            return;
        }
    }

    public void setMaxWidth(int i)
    {
        if (i == mMaxWidth)
        {
            return;
        } else
        {
            mMaxWidth = i;
            requestLayout();
            return;
        }
    }

    public void setMinHeight(int i)
    {
        if (i == mMinHeight)
        {
            return;
        } else
        {
            mMinHeight = i;
            requestLayout();
            return;
        }
    }

    public void setMinWidth(int i)
    {
        if (i == mMinWidth)
        {
            return;
        } else
        {
            mMinWidth = i;
            requestLayout();
            return;
        }
    }

    public void setOptimizationLevel(int i)
    {
        mLayoutWidget.setOptimizationLevel(i);
    }

    protected void solveLinearSystem()
    {
        mLayoutWidget.layout();
    }
}
