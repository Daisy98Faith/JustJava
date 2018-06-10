// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.Guideline;
import android.util.AttributeSet;

// Referenced classes of package android.support.constraint:
//            ConstraintLayout

public static class t extends android.view..LayoutParams
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
        super.esolveLayoutDirection(i);
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

    public t(int i, int j)
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

    public t(Context context, AttributeSet attributeset)
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
        context = context.obtainStyledAttributes(attributeset, ayout);
        k = context.getIndexCount();
        i = 0;
_L2:
        int j;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_1803;
        }
        j = context.getIndex(i);
        if (j != ayout_layout_constraintLeft_toLeftOf)
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
        if (j == ayout_layout_constraintLeft_toRightOf)
        {
            leftToRight = context.getResourceId(j, leftToRight);
            if (leftToRight == -1)
            {
                leftToRight = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintRight_toLeftOf)
        {
            rightToLeft = context.getResourceId(j, rightToLeft);
            if (rightToLeft == -1)
            {
                rightToLeft = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintRight_toRightOf)
        {
            rightToRight = context.getResourceId(j, rightToRight);
            if (rightToRight == -1)
            {
                rightToRight = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintTop_toTopOf)
        {
            topToTop = context.getResourceId(j, topToTop);
            if (topToTop == -1)
            {
                topToTop = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintTop_toBottomOf)
        {
            topToBottom = context.getResourceId(j, topToBottom);
            if (topToBottom == -1)
            {
                topToBottom = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintBottom_toTopOf)
        {
            bottomToTop = context.getResourceId(j, bottomToTop);
            if (bottomToTop == -1)
            {
                bottomToTop = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintBottom_toBottomOf)
        {
            bottomToBottom = context.getResourceId(j, bottomToBottom);
            if (bottomToBottom == -1)
            {
                bottomToBottom = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintBaseline_toBaselineOf)
        {
            baselineToBaseline = context.getResourceId(j, baselineToBaseline);
            if (baselineToBaseline == -1)
            {
                baselineToBaseline = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_editor_absoluteX)
        {
            editorAbsoluteX = context.getDimensionPixelOffset(j, editorAbsoluteX);
        } else
        if (j == ayout_layout_editor_absoluteY)
        {
            editorAbsoluteY = context.getDimensionPixelOffset(j, editorAbsoluteY);
        } else
        if (j == ayout_layout_constraintGuide_begin)
        {
            guideBegin = context.getDimensionPixelOffset(j, guideBegin);
        } else
        if (j == ayout_layout_constraintGuide_end)
        {
            guideEnd = context.getDimensionPixelOffset(j, guideEnd);
        } else
        if (j == ayout_layout_constraintGuide_percent)
        {
            guidePercent = context.getFloat(j, guidePercent);
        } else
        if (j == ayout_android_orientation)
        {
            orientation = context.getInt(j, orientation);
        } else
        if (j == ayout_layout_constraintStart_toEndOf)
        {
            startToEnd = context.getResourceId(j, startToEnd);
            if (startToEnd == -1)
            {
                startToEnd = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintStart_toStartOf)
        {
            startToStart = context.getResourceId(j, startToStart);
            if (startToStart == -1)
            {
                startToStart = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintEnd_toStartOf)
        {
            endToStart = context.getResourceId(j, endToStart);
            if (endToStart == -1)
            {
                endToStart = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_constraintEnd_toEndOf)
        {
            endToEnd = context.getResourceId(j, endToEnd);
            if (endToEnd == -1)
            {
                endToEnd = context.getInt(j, -1);
            }
        } else
        if (j == ayout_layout_goneMarginLeft)
        {
            goneLeftMargin = context.getDimensionPixelSize(j, goneLeftMargin);
        } else
        if (j == ayout_layout_goneMarginTop)
        {
            goneTopMargin = context.getDimensionPixelSize(j, goneTopMargin);
        } else
        if (j == ayout_layout_goneMarginRight)
        {
            goneRightMargin = context.getDimensionPixelSize(j, goneRightMargin);
        } else
        if (j == ayout_layout_goneMarginBottom)
        {
            goneBottomMargin = context.getDimensionPixelSize(j, goneBottomMargin);
        } else
        if (j == ayout_layout_goneMarginStart)
        {
            goneStartMargin = context.getDimensionPixelSize(j, goneStartMargin);
        } else
        if (j == ayout_layout_goneMarginEnd)
        {
            goneEndMargin = context.getDimensionPixelSize(j, goneEndMargin);
        } else
        if (j == ayout_layout_constraintHorizontal_bias)
        {
            horizontalBias = context.getFloat(j, horizontalBias);
        } else
        {
label0:
            {
                if (j != ayout_layout_constraintVertical_bias)
                {
                    break label0;
                }
                verticalBias = context.getFloat(j, verticalBias);
            }
        }
          goto _L3
        if (j != ayout_layout_constraintDimensionRatio)
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
        if (j == ayout_layout_constraintHorizontal_weight)
        {
            horizontalWeight = context.getFloat(j, 0.0F);
        } else
        if (j == ayout_layout_constraintVertical_weight)
        {
            verticalWeight = context.getFloat(j, 0.0F);
        } else
        if (j == ayout_layout_constraintHorizontal_chainStyle)
        {
            horizontalChainStyle = context.getInt(j, 0);
        } else
        if (j == ayout_layout_constraintVertical_chainStyle)
        {
            verticalChainStyle = context.getInt(j, 0);
        } else
        if (j == ayout_layout_constraintWidth_default)
        {
            matchConstraintDefaultWidth = context.getInt(j, 0);
        } else
        if (j == ayout_layout_constraintHeight_default)
        {
            matchConstraintDefaultHeight = context.getInt(j, 0);
        } else
        if (j == ayout_layout_constraintWidth_min)
        {
            matchConstraintMinWidth = context.getDimensionPixelSize(j, matchConstraintMinWidth);
        } else
        if (j == ayout_layout_constraintWidth_max)
        {
            matchConstraintMaxWidth = context.getDimensionPixelSize(j, matchConstraintMaxWidth);
        } else
        if (j == ayout_layout_constraintHeight_min)
        {
            matchConstraintMinHeight = context.getDimensionPixelSize(j, matchConstraintMinHeight);
        } else
        if (j == ayout_layout_constraintHeight_max)
        {
            matchConstraintMaxHeight = context.getDimensionPixelSize(j, matchConstraintMaxHeight);
        } else
        if (j == ayout_layout_constraintLeft_creator || j == ayout_layout_constraintTop_creator || j == ayout_layout_constraintRight_creator || j == ayout_layout_constraintBottom_creator || j != ayout_layout_constraintBaseline_creator);
          goto _L3
        context.recycle();
        validate();
        return;
    }

    public validate(validate validate1)
    {
        super(validate1);
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
        guideBegin = validate1.guideBegin;
        guideEnd = validate1.guideEnd;
        guidePercent = validate1.guidePercent;
        leftToLeft = validate1.leftToLeft;
        leftToRight = validate1.leftToRight;
        rightToLeft = validate1.rightToLeft;
        rightToRight = validate1.rightToRight;
        topToTop = validate1.topToTop;
        topToBottom = validate1.topToBottom;
        bottomToTop = validate1.bottomToTop;
        bottomToBottom = validate1.bottomToBottom;
        baselineToBaseline = validate1.baselineToBaseline;
        startToEnd = validate1.startToEnd;
        startToStart = validate1.startToStart;
        endToStart = validate1.endToStart;
        endToEnd = validate1.endToEnd;
        goneLeftMargin = validate1.goneLeftMargin;
        goneTopMargin = validate1.goneTopMargin;
        goneRightMargin = validate1.goneRightMargin;
        goneBottomMargin = validate1.goneBottomMargin;
        goneStartMargin = validate1.goneStartMargin;
        goneEndMargin = validate1.goneEndMargin;
        horizontalBias = validate1.horizontalBias;
        verticalBias = validate1.verticalBias;
        dimensionRatio = validate1.dimensionRatio;
        dimensionRatioValue = validate1.dimensionRatioValue;
        dimensionRatioSide = validate1.dimensionRatioSide;
        horizontalWeight = validate1.horizontalWeight;
        verticalWeight = validate1.verticalWeight;
        horizontalChainStyle = validate1.horizontalChainStyle;
        verticalChainStyle = validate1.verticalChainStyle;
        matchConstraintDefaultWidth = validate1.matchConstraintDefaultWidth;
        matchConstraintDefaultHeight = validate1.matchConstraintDefaultHeight;
        matchConstraintMinWidth = validate1.matchConstraintMinWidth;
        matchConstraintMaxWidth = validate1.matchConstraintMaxWidth;
        matchConstraintMinHeight = validate1.matchConstraintMinHeight;
        matchConstraintMaxHeight = validate1.matchConstraintMaxHeight;
        editorAbsoluteX = validate1.editorAbsoluteX;
        editorAbsoluteY = validate1.editorAbsoluteY;
        orientation = validate1.orientation;
        horizontalDimensionFixed = validate1.horizontalDimensionFixed;
        verticalDimensionFixed = validate1.verticalDimensionFixed;
        needsBaseline = validate1.needsBaseline;
        isGuideline = validate1.isGuideline;
        resolvedLeftToLeft = validate1.resolvedLeftToLeft;
        resolvedLeftToRight = validate1.resolvedLeftToRight;
        resolvedRightToLeft = validate1.resolvedRightToLeft;
        resolvedRightToRight = validate1.resolvedRightToRight;
        resolveGoneLeftMargin = validate1.resolveGoneLeftMargin;
        resolveGoneRightMargin = validate1.resolveGoneRightMargin;
        resolvedHorizontalBias = validate1.resolvedHorizontalBias;
        widget = validate1.widget;
    }

    public widget(android.view..LayoutParams layoutparams)
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
