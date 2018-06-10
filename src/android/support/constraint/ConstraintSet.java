// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.constraint:
//            ConstraintLayout, Guideline

public class ConstraintSet
{
    private static class Constraint
    {

        static final int UNSET = -1;
        public float alpha;
        public boolean applyElevation;
        public int baselineToBaseline;
        public int bottomMargin;
        public int bottomToBottom;
        public int bottomToTop;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public float elevation;
        public int endMargin;
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
        public int heightDefault;
        public int heightMax;
        public int heightMin;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftMargin;
        public int leftToLeft;
        public int leftToRight;
        public int mHeight;
        boolean mIsGuideline;
        int mViewId;
        public int mWidth;
        public int orientation;
        public int rightMargin;
        public int rightToLeft;
        public int rightToRight;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public int startMargin;
        public int startToEnd;
        public int startToStart;
        public int topMargin;
        public int topToBottom;
        public int topToTop;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int visibility;
        public int widthDefault;
        public int widthMax;
        public int widthMin;

        private void fillFrom(int i, ConstraintLayout.LayoutParams layoutparams)
        {
            mViewId = i;
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
            horizontalBias = layoutparams.horizontalBias;
            verticalBias = layoutparams.verticalBias;
            dimensionRatio = layoutparams.dimensionRatio;
            editorAbsoluteX = layoutparams.editorAbsoluteX;
            editorAbsoluteY = layoutparams.editorAbsoluteY;
            orientation = layoutparams.orientation;
            guidePercent = layoutparams.guidePercent;
            guideBegin = layoutparams.guideBegin;
            guideEnd = layoutparams.guideEnd;
            mWidth = layoutparams.width;
            mHeight = layoutparams.height;
            leftMargin = layoutparams.leftMargin;
            rightMargin = layoutparams.rightMargin;
            topMargin = layoutparams.topMargin;
            bottomMargin = layoutparams.bottomMargin;
            verticalWeight = layoutparams.verticalWeight;
            horizontalWeight = layoutparams.horizontalWeight;
            verticalChainStyle = layoutparams.verticalChainStyle;
            horizontalChainStyle = layoutparams.horizontalChainStyle;
            widthDefault = layoutparams.matchConstraintDefaultWidth;
            heightDefault = layoutparams.matchConstraintDefaultHeight;
            widthMax = layoutparams.matchConstraintMaxWidth;
            heightMax = layoutparams.matchConstraintMaxHeight;
            widthMin = layoutparams.matchConstraintMinWidth;
            heightMin = layoutparams.matchConstraintMinHeight;
            if (android.os.Build.VERSION.SDK_INT >= 17)
            {
                endMargin = layoutparams.getMarginEnd();
                startMargin = layoutparams.getMarginStart();
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutparams)
        {
            layoutparams.leftToLeft = leftToLeft;
            layoutparams.leftToRight = leftToRight;
            layoutparams.rightToLeft = rightToLeft;
            layoutparams.rightToRight = rightToRight;
            layoutparams.topToTop = topToTop;
            layoutparams.topToBottom = topToBottom;
            layoutparams.bottomToTop = bottomToTop;
            layoutparams.bottomToBottom = bottomToBottom;
            layoutparams.baselineToBaseline = baselineToBaseline;
            layoutparams.startToEnd = startToEnd;
            layoutparams.startToStart = startToStart;
            layoutparams.endToStart = endToStart;
            layoutparams.endToEnd = endToEnd;
            layoutparams.leftMargin = leftMargin;
            layoutparams.rightMargin = rightMargin;
            layoutparams.topMargin = topMargin;
            layoutparams.bottomMargin = bottomMargin;
            layoutparams.goneStartMargin = goneStartMargin;
            layoutparams.goneEndMargin = goneEndMargin;
            layoutparams.horizontalBias = horizontalBias;
            layoutparams.verticalBias = verticalBias;
            layoutparams.dimensionRatio = dimensionRatio;
            layoutparams.editorAbsoluteX = editorAbsoluteX;
            layoutparams.editorAbsoluteY = editorAbsoluteY;
            layoutparams.verticalWeight = verticalWeight;
            layoutparams.horizontalWeight = horizontalWeight;
            layoutparams.verticalChainStyle = verticalChainStyle;
            layoutparams.horizontalChainStyle = horizontalChainStyle;
            layoutparams.matchConstraintDefaultWidth = widthDefault;
            layoutparams.matchConstraintDefaultHeight = heightDefault;
            layoutparams.matchConstraintMaxWidth = widthMax;
            layoutparams.matchConstraintMaxHeight = heightMax;
            layoutparams.matchConstraintMinWidth = widthMin;
            layoutparams.matchConstraintMinHeight = heightMin;
            layoutparams.orientation = orientation;
            layoutparams.guidePercent = guidePercent;
            layoutparams.guideBegin = guideBegin;
            layoutparams.guideEnd = guideEnd;
            layoutparams.width = mWidth;
            layoutparams.height = mHeight;
            if (android.os.Build.VERSION.SDK_INT >= 17)
            {
                layoutparams.setMarginStart(startMargin);
                layoutparams.setMarginEnd(endMargin);
            }
            layoutparams.validate();
        }

        public Constraint clone()
        {
            Constraint constraint = new Constraint();
            constraint.mIsGuideline = mIsGuideline;
            constraint.mWidth = mWidth;
            constraint.mHeight = mHeight;
            constraint.guideBegin = guideBegin;
            constraint.guideEnd = guideEnd;
            constraint.guidePercent = guidePercent;
            constraint.leftToLeft = leftToLeft;
            constraint.leftToRight = leftToRight;
            constraint.rightToLeft = rightToLeft;
            constraint.rightToRight = rightToRight;
            constraint.topToTop = topToTop;
            constraint.topToBottom = topToBottom;
            constraint.bottomToTop = bottomToTop;
            constraint.bottomToBottom = bottomToBottom;
            constraint.baselineToBaseline = baselineToBaseline;
            constraint.startToEnd = startToEnd;
            constraint.startToStart = startToStart;
            constraint.endToStart = endToStart;
            constraint.endToEnd = endToEnd;
            constraint.horizontalBias = horizontalBias;
            constraint.verticalBias = verticalBias;
            constraint.dimensionRatio = dimensionRatio;
            constraint.editorAbsoluteX = editorAbsoluteX;
            constraint.editorAbsoluteY = editorAbsoluteY;
            constraint.horizontalBias = horizontalBias;
            constraint.horizontalBias = horizontalBias;
            constraint.horizontalBias = horizontalBias;
            constraint.horizontalBias = horizontalBias;
            constraint.horizontalBias = horizontalBias;
            constraint.orientation = orientation;
            constraint.leftMargin = leftMargin;
            constraint.rightMargin = rightMargin;
            constraint.topMargin = topMargin;
            constraint.bottomMargin = bottomMargin;
            constraint.endMargin = endMargin;
            constraint.startMargin = startMargin;
            constraint.visibility = visibility;
            constraint.goneLeftMargin = goneLeftMargin;
            constraint.goneTopMargin = goneTopMargin;
            constraint.goneRightMargin = goneRightMargin;
            constraint.goneBottomMargin = goneBottomMargin;
            constraint.goneEndMargin = goneEndMargin;
            constraint.goneStartMargin = goneStartMargin;
            constraint.verticalWeight = verticalWeight;
            constraint.horizontalWeight = horizontalWeight;
            constraint.horizontalChainStyle = horizontalChainStyle;
            constraint.verticalChainStyle = verticalChainStyle;
            constraint.alpha = alpha;
            constraint.applyElevation = applyElevation;
            constraint.elevation = elevation;
            constraint.rotationX = rotationX;
            constraint.rotationY = rotationY;
            constraint.scaleX = scaleX;
            constraint.scaleY = scaleY;
            constraint.transformPivotX = transformPivotX;
            constraint.transformPivotY = transformPivotY;
            constraint.translationX = translationX;
            constraint.translationY = translationY;
            constraint.translationZ = translationZ;
            constraint.widthDefault = widthDefault;
            constraint.heightDefault = heightDefault;
            constraint.widthMax = widthMax;
            constraint.heightMax = heightMax;
            constraint.widthMin = widthMin;
            constraint.heightMin = heightMin;
            return constraint;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }


        private Constraint()
        {
            mIsGuideline = false;
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
            horizontalBias = 0.5F;
            verticalBias = 0.5F;
            dimensionRatio = null;
            editorAbsoluteX = -1;
            editorAbsoluteY = -1;
            orientation = -1;
            leftMargin = -1;
            rightMargin = -1;
            topMargin = -1;
            bottomMargin = -1;
            endMargin = -1;
            startMargin = -1;
            visibility = 0;
            goneLeftMargin = -1;
            goneTopMargin = -1;
            goneRightMargin = -1;
            goneBottomMargin = -1;
            goneEndMargin = -1;
            goneStartMargin = -1;
            verticalWeight = 0.0F;
            horizontalWeight = 0.0F;
            horizontalChainStyle = 0;
            verticalChainStyle = 0;
            alpha = 1.0F;
            applyElevation = false;
            elevation = 0.0F;
            rotationX = 0.0F;
            rotationY = 0.0F;
            scaleX = 1.0F;
            scaleY = 1.0F;
            transformPivotX = 0.0F;
            transformPivotY = 0.0F;
            translationX = 0.0F;
            translationY = 0.0F;
            translationZ = 0.0F;
            widthDefault = -1;
            heightDefault = -1;
            widthMax = -1;
            heightMax = -1;
            widthMin = -1;
            heightMin = -1;
        }

    }


    private static final int ALPHA = 43;
    public static final int BASELINE = 5;
    private static final int BASELINE_TO_BASELINE = 1;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    public static final int GONE = 8;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    public static final int INVISIBLE = 4;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 60;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    private static final int VISIBILITY_FLAGS[] = {
        0, 4, 8
    };
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    public static final int WRAP_CONTENT = -2;
    private static SparseIntArray mapToConstant;
    private HashMap mConstraints;

    public ConstraintSet()
    {
        mConstraints = new HashMap();
    }

    private void createHorizontalChain(int i, int j, int k, int l, int ai[], float af[], int i1, 
            int j1, int k1)
    {
        if (ai.length < 2)
        {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (af != null && af.length != ai.length)
        {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (af != null)
        {
            get(ai[0]).verticalWeight = af[0];
        }
        get(ai[0]).horizontalChainStyle = i1;
        connect(ai[0], j1, i, j, -1);
        for (i = 1; i < ai.length; i++)
        {
            j = ai[i];
            connect(ai[i], j1, ai[i - 1], k1, -1);
            connect(ai[i - 1], k1, ai[i], j1, -1);
            if (af != null)
            {
                get(ai[i]).horizontalWeight = af[i];
            }
        }

        connect(ai[ai.length - 1], k1, k, l, -1);
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attributeset)
    {
        Constraint constraint = new Constraint();
        context = context.obtainStyledAttributes(attributeset, R.styleable.ConstraintSet);
        populateConstraint(constraint, context);
        context.recycle();
        return constraint;
    }

    private Constraint get(int i)
    {
        if (!mConstraints.containsKey(Integer.valueOf(i)))
        {
            mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        return (Constraint)mConstraints.get(Integer.valueOf(i));
    }

    private static int lookupID(TypedArray typedarray, int i, int j)
    {
        int k = typedarray.getResourceId(i, j);
        j = k;
        if (k == -1)
        {
            j = typedarray.getInt(i, -1);
        }
        return j;
    }

    private void populateConstraint(Constraint constraint, TypedArray typedarray)
    {
        int i;
        int j;
        j = typedarray.getIndexCount();
        i = 0;
_L57:
        int k;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_1302;
        }
        k = typedarray.getIndex(i);
        mapToConstant.get(k);
        JVM INSTR tableswitch 1 60: default 284
    //                   1 474
    //                   2 882
    //                   3 457
    //                   4 440
    //                   5 1239
    //                   6 491
    //                   7 508
    //                   8 848
    //                   9 644
    //                   10 627
    //                   11 712
    //                   12 746
    //                   13 661
    //                   14 695
    //                   15 729
    //                   16 678
    //                   17 525
    //                   18 542
    //                   19 559
    //                   20 763
    //                   21 916
    //                   22 933
    //                   23 899
    //                   24 797
    //                   25 338
    //                   26 355
    //                   27 576
    //                   28 814
    //                   29 372
    //                   30 389
    //                   31 831
    //                   32 593
    //                   33 610
    //                   34 865
    //                   35 423
    //                   36 406
    //                   37 780
    //                   38 1222
    //                   39 1171
    //                   40 1154
    //                   41 1205
    //                   42 1188
    //                   43 962
    //                   44 979
    //                   45 1001
    //                   46 1018
    //                   47 1035
    //                   48 1052
    //                   49 1069
    //                   50 1086
    //                   51 1103
    //                   52 1120
    //                   53 1137
    //                   54 284
    //                   55 284
    //                   56 284
    //                   57 284
    //                   58 284
    //                   59 284
    //                   60 1252;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L1 _L1 _L1 _L1 _L1 _L1 _L55
_L55:
        break MISSING_BLOCK_LABEL_1252;
_L26:
        break; /* Loop/switch isn't completed */
_L1:
        Log.w("ConstraintSet", (new StringBuilder()).append("Unknown attribute 0x").append(Integer.toHexString(k)).append("   ").append(mapToConstant.get(k)).toString());
_L58:
        i++;
        if (true) goto _L57; else goto _L56
_L56:
        constraint.leftToLeft = lookupID(typedarray, k, constraint.leftToLeft);
          goto _L58
_L27:
        constraint.leftToRight = lookupID(typedarray, k, constraint.leftToRight);
          goto _L58
_L30:
        constraint.rightToLeft = lookupID(typedarray, k, constraint.rightToLeft);
          goto _L58
_L31:
        constraint.rightToRight = lookupID(typedarray, k, constraint.rightToRight);
          goto _L58
_L37:
        constraint.topToTop = lookupID(typedarray, k, constraint.topToTop);
          goto _L58
_L36:
        constraint.topToBottom = lookupID(typedarray, k, constraint.topToBottom);
          goto _L58
_L5:
        constraint.bottomToTop = lookupID(typedarray, k, constraint.bottomToTop);
          goto _L58
_L4:
        constraint.bottomToBottom = lookupID(typedarray, k, constraint.bottomToBottom);
          goto _L58
_L2:
        constraint.baselineToBaseline = lookupID(typedarray, k, constraint.baselineToBaseline);
          goto _L58
_L7:
        constraint.editorAbsoluteX = typedarray.getDimensionPixelOffset(k, constraint.editorAbsoluteX);
          goto _L58
_L8:
        constraint.editorAbsoluteY = typedarray.getDimensionPixelOffset(k, constraint.editorAbsoluteY);
          goto _L58
_L18:
        constraint.guideBegin = typedarray.getDimensionPixelOffset(k, constraint.guideBegin);
          goto _L58
_L19:
        constraint.guideEnd = typedarray.getDimensionPixelOffset(k, constraint.guideEnd);
          goto _L58
_L20:
        constraint.guidePercent = typedarray.getFloat(k, constraint.guidePercent);
          goto _L58
_L28:
        constraint.orientation = typedarray.getInt(k, constraint.orientation);
          goto _L58
_L33:
        constraint.startToEnd = lookupID(typedarray, k, constraint.startToEnd);
          goto _L58
_L34:
        constraint.startToStart = lookupID(typedarray, k, constraint.startToStart);
          goto _L58
_L11:
        constraint.endToStart = lookupID(typedarray, k, constraint.endToStart);
          goto _L58
_L10:
        constraint.bottomToTop = lookupID(typedarray, k, constraint.endToEnd);
          goto _L58
_L14:
        constraint.goneLeftMargin = typedarray.getDimensionPixelSize(k, constraint.goneLeftMargin);
          goto _L58
_L17:
        constraint.goneTopMargin = typedarray.getDimensionPixelSize(k, constraint.goneTopMargin);
          goto _L58
_L15:
        constraint.goneRightMargin = typedarray.getDimensionPixelSize(k, constraint.goneRightMargin);
          goto _L58
_L12:
        constraint.goneBottomMargin = typedarray.getDimensionPixelSize(k, constraint.goneBottomMargin);
          goto _L58
_L16:
        constraint.goneStartMargin = typedarray.getDimensionPixelSize(k, constraint.goneStartMargin);
          goto _L58
_L13:
        constraint.goneEndMargin = typedarray.getDimensionPixelSize(k, constraint.goneEndMargin);
          goto _L58
_L21:
        constraint.horizontalBias = typedarray.getFloat(k, constraint.horizontalBias);
          goto _L58
_L38:
        constraint.verticalBias = typedarray.getFloat(k, constraint.verticalBias);
          goto _L58
_L25:
        constraint.leftMargin = typedarray.getDimensionPixelSize(k, constraint.leftMargin);
          goto _L58
_L29:
        constraint.rightMargin = typedarray.getDimensionPixelSize(k, constraint.rightMargin);
          goto _L58
_L32:
        constraint.startMargin = typedarray.getDimensionPixelSize(k, constraint.startMargin);
          goto _L58
_L9:
        constraint.endMargin = typedarray.getDimensionPixelSize(k, constraint.endMargin);
          goto _L58
_L35:
        constraint.topMargin = typedarray.getDimensionPixelSize(k, constraint.topMargin);
          goto _L58
_L3:
        constraint.bottomMargin = typedarray.getDimensionPixelSize(k, constraint.bottomMargin);
          goto _L58
_L24:
        constraint.mWidth = typedarray.getLayoutDimension(k, constraint.mWidth);
          goto _L58
_L22:
        constraint.mHeight = typedarray.getLayoutDimension(k, constraint.mHeight);
          goto _L58
_L23:
        constraint.visibility = typedarray.getInt(k, constraint.visibility);
        constraint.visibility = VISIBILITY_FLAGS[constraint.visibility];
          goto _L58
_L44:
        constraint.alpha = typedarray.getFloat(k, constraint.alpha);
          goto _L58
_L45:
        constraint.applyElevation = true;
        constraint.elevation = typedarray.getFloat(k, constraint.elevation);
          goto _L58
_L46:
        constraint.rotationX = typedarray.getFloat(k, constraint.rotationX);
          goto _L58
_L47:
        constraint.rotationY = typedarray.getFloat(k, constraint.rotationY);
          goto _L58
_L48:
        constraint.scaleX = typedarray.getFloat(k, constraint.scaleX);
          goto _L58
_L49:
        constraint.scaleY = typedarray.getFloat(k, constraint.scaleY);
          goto _L58
_L50:
        constraint.transformPivotX = typedarray.getFloat(k, constraint.transformPivotX);
          goto _L58
_L51:
        constraint.transformPivotY = typedarray.getFloat(k, constraint.transformPivotY);
          goto _L58
_L52:
        constraint.translationX = typedarray.getFloat(k, constraint.translationX);
          goto _L58
_L53:
        constraint.translationY = typedarray.getFloat(k, constraint.translationY);
          goto _L58
_L54:
        constraint.translationZ = typedarray.getFloat(k, constraint.translationZ);
          goto _L58
_L41:
        constraint.verticalWeight = typedarray.getFloat(k, constraint.verticalWeight);
          goto _L58
_L40:
        constraint.horizontalWeight = typedarray.getFloat(k, constraint.horizontalWeight);
          goto _L58
_L43:
        constraint.verticalChainStyle = typedarray.getInt(k, constraint.verticalChainStyle);
          goto _L58
_L42:
        constraint.horizontalChainStyle = typedarray.getInt(k, constraint.horizontalChainStyle);
          goto _L58
_L39:
        constraint.mViewId = typedarray.getResourceId(k, constraint.mViewId);
          goto _L58
_L6:
        constraint.dimensionRatio = typedarray.getString(k);
          goto _L58
        Log.w("ConstraintSet", (new StringBuilder()).append("unused attribute 0x").append(Integer.toHexString(k)).append("   ").append(mapToConstant.get(k)).toString());
          goto _L58
    }

    private String sideToString(int i)
    {
        switch (i)
        {
        default:
            return "undefined";

        case 1: // '\001'
            return "left";

        case 2: // '\002'
            return "right";

        case 3: // '\003'
            return "top";

        case 4: // '\004'
            return "bottom";

        case 5: // '\005'
            return "baseline";

        case 6: // '\006'
            return "start";

        case 7: // '\007'
            return "end";
        }
    }

    public void addToHorizontalChain(int i, int j, int k)
    {
        int l;
        if (j == 0)
        {
            l = 1;
        } else
        {
            l = 2;
        }
        connect(i, 1, j, l, 0);
        if (k == 0)
        {
            l = 2;
        } else
        {
            l = 1;
        }
        connect(i, 2, k, l, 0);
        if (j != 0)
        {
            connect(j, 2, i, 1, 0);
        }
        if (k != 0)
        {
            connect(k, 1, i, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i, int j, int k)
    {
        byte byte0;
        if (j == 0)
        {
            byte0 = 6;
        } else
        {
            byte0 = 7;
        }
        connect(i, 6, j, byte0, 0);
        if (k == 0)
        {
            byte0 = 7;
        } else
        {
            byte0 = 6;
        }
        connect(i, 7, k, byte0, 0);
        if (j != 0)
        {
            connect(j, 7, i, 6, 0);
        }
        if (k != 0)
        {
            connect(k, 6, i, 7, 0);
        }
    }

    public void addToVerticalChain(int i, int j, int k)
    {
        byte byte0;
        if (j == 0)
        {
            byte0 = 3;
        } else
        {
            byte0 = 4;
        }
        connect(i, 3, j, byte0, 0);
        if (k == 0)
        {
            byte0 = 4;
        } else
        {
            byte0 = 3;
        }
        connect(i, 4, k, byte0, 0);
        if (j != 0)
        {
            connect(j, 4, i, 3, 0);
        }
        if (j != 0)
        {
            connect(k, 3, i, 4, 0);
        }
    }

    public void applyTo(ConstraintLayout constraintlayout)
    {
        applyToInternal(constraintlayout);
        constraintlayout.setConstraintSet(null);
    }

    void applyToInternal(ConstraintLayout constraintlayout)
    {
        int j = constraintlayout.getChildCount();
        Object obj = new HashSet(mConstraints.keySet());
        for (int i = 0; i < j; i++)
        {
            View view = constraintlayout.getChildAt(i);
            int k = view.getId();
            if (!mConstraints.containsKey(Integer.valueOf(k)))
            {
                continue;
            }
            ((HashSet) (obj)).remove(Integer.valueOf(k));
            Constraint constraint1 = (Constraint)mConstraints.get(Integer.valueOf(k));
            ConstraintLayout.LayoutParams layoutparams = (ConstraintLayout.LayoutParams)view.getLayoutParams();
            constraint1.applyTo(layoutparams);
            view.setLayoutParams(layoutparams);
            view.setVisibility(constraint1.visibility);
            if (android.os.Build.VERSION.SDK_INT < 17)
            {
                continue;
            }
            view.setAlpha(constraint1.alpha);
            view.setRotationX(constraint1.rotationX);
            view.setRotationY(constraint1.rotationY);
            view.setScaleX(constraint1.scaleX);
            view.setScaleY(constraint1.scaleY);
            view.setPivotX(constraint1.transformPivotX);
            view.setPivotY(constraint1.transformPivotY);
            view.setTranslationX(constraint1.translationX);
            view.setTranslationY(constraint1.translationY);
            if (android.os.Build.VERSION.SDK_INT < 21)
            {
                continue;
            }
            view.setTranslationZ(constraint1.translationZ);
            if (constraint1.applyElevation)
            {
                view.setElevation(constraint1.elevation);
            }
        }

        obj = ((HashSet) (obj)).iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Object obj1 = (Integer)((Iterator) (obj)).next();
            Constraint constraint = (Constraint)mConstraints.get(obj1);
            if (constraint.mIsGuideline)
            {
                Guideline guideline = new Guideline(constraintlayout.getContext());
                guideline.setId(((Integer) (obj1)).intValue());
                obj1 = constraintlayout.generateDefaultLayoutParams();
                constraint.applyTo(((ConstraintLayout.LayoutParams) (obj1)));
                constraintlayout.addView(guideline, ((android.view.ViewGroup.LayoutParams) (obj1)));
            }
        } while (true);
    }

    public void center(int i, int j, int k, int l, int i1, int j1, int k1, 
            float f)
    {
        if (l < 0)
        {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (k1 < 0)
        {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f <= 0.0F || f > 1.0F)
        {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (k == 1 || k == 2)
        {
            connect(i, 1, j, k, l);
            connect(i, 2, i1, j1, k1);
            ((Constraint)mConstraints.get(Integer.valueOf(i))).horizontalBias = f;
            return;
        }
        if (k == 6 || k == 7)
        {
            connect(i, 6, j, k, l);
            connect(i, 7, i1, j1, k1);
            ((Constraint)mConstraints.get(Integer.valueOf(i))).horizontalBias = f;
            return;
        } else
        {
            connect(i, 3, j, k, l);
            connect(i, 4, i1, j1, k1);
            ((Constraint)mConstraints.get(Integer.valueOf(i))).verticalBias = f;
            return;
        }
    }

    public void centerHorizontally(int i, int j)
    {
        if (j == 0)
        {
            center(i, 0, 1, 0, 0, 2, 0, 0.5F);
            return;
        } else
        {
            center(i, j, 2, 0, j, 1, 0, 0.5F);
            return;
        }
    }

    public void centerHorizontally(int i, int j, int k, int l, int i1, int j1, int k1, 
            float f)
    {
        connect(i, 1, j, k, l);
        connect(i, 2, i1, j1, k1);
        ((Constraint)mConstraints.get(Integer.valueOf(i))).horizontalBias = f;
    }

    public void centerHorizontallyRtl(int i, int j)
    {
        if (j == 0)
        {
            center(i, 0, 6, 0, 0, 7, 0, 0.5F);
            return;
        } else
        {
            center(i, j, 7, 0, j, 6, 0, 0.5F);
            return;
        }
    }

    public void centerHorizontallyRtl(int i, int j, int k, int l, int i1, int j1, int k1, 
            float f)
    {
        connect(i, 6, j, k, l);
        connect(i, 7, i1, j1, k1);
        ((Constraint)mConstraints.get(Integer.valueOf(i))).horizontalBias = f;
    }

    public void centerVertically(int i, int j)
    {
        if (j == 0)
        {
            center(i, 0, 3, 0, 0, 4, 0, 0.5F);
            return;
        } else
        {
            center(i, j, 4, 0, j, 3, 0, 0.5F);
            return;
        }
    }

    public void centerVertically(int i, int j, int k, int l, int i1, int j1, int k1, 
            float f)
    {
        connect(i, 3, j, k, l);
        connect(i, 4, i1, j1, k1);
        ((Constraint)mConstraints.get(Integer.valueOf(i))).verticalBias = f;
    }

    public void clear(int i)
    {
        mConstraints.remove(Integer.valueOf(i));
    }

    public void clear(int i, int j)
    {
        Constraint constraint;
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
label4:
                        {
label5:
                            {
                                if (mConstraints.containsKey(Integer.valueOf(i)))
                                {
                                    constraint = (Constraint)mConstraints.get(Integer.valueOf(i));
                                    switch (j)
                                    {
                                    default:
                                        throw new IllegalArgumentException("unknown constraint");

                                    case 2: // '\002'
                                        break label5;

                                    case 3: // '\003'
                                        break label4;

                                    case 4: // '\004'
                                        break label3;

                                    case 5: // '\005'
                                        break label2;

                                    case 6: // '\006'
                                        break label1;

                                    case 7: // '\007'
                                        break label0;

                                    case 1: // '\001'
                                        constraint.leftToRight = -1;
                                        constraint.leftToLeft = -1;
                                        constraint.leftMargin = -1;
                                        constraint.goneLeftMargin = -1;
                                        break;
                                    }
                                }
                                return;
                            }
                            constraint.rightToRight = -1;
                            constraint.rightToLeft = -1;
                            constraint.rightMargin = -1;
                            constraint.goneRightMargin = -1;
                            return;
                        }
                        constraint.topToBottom = -1;
                        constraint.topToTop = -1;
                        constraint.topMargin = -1;
                        constraint.goneTopMargin = -1;
                        return;
                    }
                    constraint.bottomToTop = -1;
                    constraint.bottomToBottom = -1;
                    constraint.bottomMargin = -1;
                    constraint.goneBottomMargin = -1;
                    return;
                }
                constraint.baselineToBaseline = -1;
                return;
            }
            constraint.startToEnd = -1;
            constraint.startToStart = -1;
            constraint.startMargin = -1;
            constraint.goneStartMargin = -1;
            return;
        }
        constraint.endToStart = -1;
        constraint.endToEnd = -1;
        constraint.endMargin = -1;
        constraint.goneEndMargin = -1;
    }

    public void clone(Context context, int i)
    {
        clone((ConstraintLayout)LayoutInflater.from(context).inflate(i, null));
    }

    public void clone(ConstraintLayout constraintlayout)
    {
        int j = constraintlayout.getChildCount();
        mConstraints.clear();
        for (int i = 0; i < j; i++)
        {
            View view = constraintlayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutparams = (ConstraintLayout.LayoutParams)view.getLayoutParams();
            int k = view.getId();
            if (!mConstraints.containsKey(Integer.valueOf(k)))
            {
                mConstraints.put(Integer.valueOf(k), new Constraint());
            }
            Constraint constraint = (Constraint)mConstraints.get(Integer.valueOf(k));
            constraint.fillFrom(k, layoutparams);
            constraint.visibility = view.getVisibility();
            if (android.os.Build.VERSION.SDK_INT < 17)
            {
                continue;
            }
            constraint.alpha = view.getAlpha();
            constraint.rotationX = view.getRotationX();
            constraint.rotationY = view.getRotationY();
            constraint.scaleX = view.getScaleX();
            constraint.scaleY = view.getScaleY();
            constraint.transformPivotX = view.getPivotX();
            constraint.transformPivotY = view.getPivotY();
            constraint.translationX = view.getTranslationX();
            constraint.translationY = view.getTranslationY();
            if (android.os.Build.VERSION.SDK_INT < 21)
            {
                continue;
            }
            constraint.translationZ = view.getTranslationZ();
            if (constraint.applyElevation)
            {
                constraint.elevation = view.getElevation();
            }
        }

    }

    public void clone(ConstraintSet constraintset)
    {
        mConstraints.clear();
        Integer integer;
        for (Iterator iterator = constraintset.mConstraints.keySet().iterator(); iterator.hasNext(); mConstraints.put(integer, ((Constraint)constraintset.mConstraints.get(integer)).clone()))
        {
            integer = (Integer)iterator.next();
        }

    }

    public void connect(int i, int j, int k, int l)
    {
        if (!mConstraints.containsKey(Integer.valueOf(i)))
        {
            mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = (Constraint)mConstraints.get(Integer.valueOf(i));
        switch (j)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append(sideToString(j)).append(" to ").append(sideToString(l)).append(" unknown").toString());

        case 1: // '\001'
            if (l == 1)
            {
                constraint.leftToLeft = k;
                constraint.leftToRight = -1;
                return;
            }
            if (l == 2)
            {
                constraint.leftToRight = k;
                constraint.leftToLeft = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("left to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 2: // '\002'
            if (l == 1)
            {
                constraint.rightToLeft = k;
                constraint.rightToRight = -1;
                return;
            }
            if (l == 2)
            {
                constraint.rightToRight = k;
                constraint.rightToLeft = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 3: // '\003'
            if (l == 3)
            {
                constraint.topToTop = k;
                constraint.topToBottom = -1;
                constraint.baselineToBaseline = -1;
                return;
            }
            if (l == 4)
            {
                constraint.topToBottom = k;
                constraint.topToTop = -1;
                constraint.baselineToBaseline = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 4: // '\004'
            if (l == 4)
            {
                constraint.bottomToBottom = k;
                constraint.bottomToTop = -1;
                constraint.baselineToBaseline = -1;
                return;
            }
            if (l == 3)
            {
                constraint.bottomToTop = k;
                constraint.bottomToBottom = -1;
                constraint.baselineToBaseline = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 5: // '\005'
            if (l == 5)
            {
                constraint.baselineToBaseline = k;
                constraint.bottomToBottom = -1;
                constraint.bottomToTop = -1;
                constraint.topToTop = -1;
                constraint.topToBottom = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 6: // '\006'
            if (l == 6)
            {
                constraint.startToStart = k;
                constraint.startToEnd = -1;
                return;
            }
            if (l == 7)
            {
                constraint.startToEnd = k;
                constraint.startToStart = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 7: // '\007'
            break;
        }
        if (l == 7)
        {
            constraint.endToEnd = k;
            constraint.endToStart = -1;
            return;
        }
        if (l == 6)
        {
            constraint.endToStart = k;
            constraint.endToEnd = -1;
            return;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
        }
    }

    public void connect(int i, int j, int k, int l, int i1)
    {
        if (!mConstraints.containsKey(Integer.valueOf(i)))
        {
            mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = (Constraint)mConstraints.get(Integer.valueOf(i));
        switch (j)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append(sideToString(j)).append(" to ").append(sideToString(l)).append(" unknown").toString());

        case 1: // '\001'
            if (l == 1)
            {
                constraint.leftToLeft = k;
                constraint.leftToRight = -1;
            } else
            if (l == 2)
            {
                constraint.leftToRight = k;
                constraint.leftToLeft = -1;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Left to ").append(sideToString(l)).append(" undefined").toString());
            }
            constraint.leftMargin = i1;
            return;

        case 2: // '\002'
            if (l == 1)
            {
                constraint.rightToLeft = k;
                constraint.rightToRight = -1;
            } else
            if (l == 2)
            {
                constraint.rightToRight = k;
                constraint.rightToLeft = -1;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }
            constraint.rightMargin = i1;
            return;

        case 3: // '\003'
            if (l == 3)
            {
                constraint.topToTop = k;
                constraint.topToBottom = -1;
                constraint.baselineToBaseline = -1;
            } else
            if (l == 4)
            {
                constraint.topToBottom = k;
                constraint.topToTop = -1;
                constraint.baselineToBaseline = -1;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }
            constraint.topMargin = i1;
            return;

        case 4: // '\004'
            if (l == 4)
            {
                constraint.bottomToBottom = k;
                constraint.bottomToTop = -1;
                constraint.baselineToBaseline = -1;
            } else
            if (l == 3)
            {
                constraint.bottomToTop = k;
                constraint.bottomToBottom = -1;
                constraint.baselineToBaseline = -1;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }
            constraint.bottomMargin = i1;
            return;

        case 5: // '\005'
            if (l == 5)
            {
                constraint.baselineToBaseline = k;
                constraint.bottomToBottom = -1;
                constraint.bottomToTop = -1;
                constraint.topToTop = -1;
                constraint.topToBottom = -1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }

        case 6: // '\006'
            if (l == 6)
            {
                constraint.startToStart = k;
                constraint.startToEnd = -1;
            } else
            if (l == 7)
            {
                constraint.startToEnd = k;
                constraint.startToStart = -1;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
            }
            constraint.startMargin = i1;
            return;

        case 7: // '\007'
            break;
        }
        if (l == 7)
        {
            constraint.endToEnd = k;
            constraint.endToStart = -1;
        } else
        if (l == 6)
        {
            constraint.endToStart = k;
            constraint.endToEnd = -1;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("right to ").append(sideToString(l)).append(" undefined").toString());
        }
        constraint.endMargin = i1;
    }

    public void constrainDefaultHeight(int i, int j)
    {
        get(i).heightDefault = j;
    }

    public void constrainDefaultWidth(int i, int j)
    {
        get(i).widthDefault = j;
    }

    public void constrainHeight(int i, int j)
    {
        get(i).mHeight = j;
    }

    public void constrainMaxHeight(int i, int j)
    {
        get(i).heightMax = j;
    }

    public void constrainMaxWidth(int i, int j)
    {
        get(i).widthMax = j;
    }

    public void constrainMinHeight(int i, int j)
    {
        get(i).heightMin = j;
    }

    public void constrainMinWidth(int i, int j)
    {
        get(i).widthMin = j;
    }

    public void constrainWidth(int i, int j)
    {
        get(i).mWidth = j;
    }

    public void create(int i, int j)
    {
        Constraint constraint = get(i);
        constraint.mIsGuideline = true;
        constraint.orientation = j;
    }

    public void createHorizontalChain(int i, int j, int k, int l, int ai[], float af[], int i1)
    {
        createHorizontalChain(i, j, k, l, ai, af, i1, 1, 2);
    }

    public void createHorizontalChainRtl(int i, int j, int k, int l, int ai[], float af[], int i1)
    {
        createHorizontalChain(i, j, k, l, ai, af, i1, 6, 7);
    }

    public void createVerticalChain(int i, int j, int k, int l, int ai[], float af[], int i1)
    {
        if (ai.length < 2)
        {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (af != null && af.length != ai.length)
        {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (af != null)
        {
            get(ai[0]).verticalWeight = af[0];
        }
        get(ai[0]).verticalChainStyle = i1;
        connect(ai[0], 3, i, j, 0);
        for (i = 1; i < ai.length; i++)
        {
            j = ai[i];
            connect(ai[i], 3, ai[i - 1], 4, 0);
            connect(ai[i - 1], 4, ai[i], 3, 0);
            if (af != null)
            {
                get(ai[i]).verticalWeight = af[i];
            }
        }

        connect(ai[ai.length - 1], 4, k, l, 0);
    }

    public boolean getApplyElevation(int i)
    {
        return get(i).applyElevation;
    }

    public void load(Context context, int i)
    {
        android.content.res.XmlResourceParser xmlresourceparser = context.getResources().getXml(i);
        i = xmlresourceparser.getEventType();
          goto _L1
_L2:
        i = xmlresourceparser.next();
          goto _L1
_L5:
        xmlresourceparser.getName();
          goto _L2
_L6:
        String s = xmlresourceparser.getName();
        Constraint constraint = fillFromAttributeList(context, Xml.asAttributeSet(xmlresourceparser));
        if (s.equalsIgnoreCase("Guideline"))
        {
            constraint.mIsGuideline = true;
        }
        mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
          goto _L2
        context;
        context.printStackTrace();
_L4:
        return;
        context;
        context.printStackTrace();
        return;
_L1:
        if (i == 1) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 0 3: default 148
    //                   0 29
    //                   1 19
    //                   2 39
    //                   3 102;
           goto _L2 _L5 _L2 _L6 _L2
    }

    public void removeFromHorizontalChain(int i)
    {
        if (!mConstraints.containsKey(Integer.valueOf(i))) goto _L2; else goto _L1
_L1:
        Constraint constraint;
        int j;
        int k;
        constraint = (Constraint)mConstraints.get(Integer.valueOf(i));
        j = constraint.leftToRight;
        k = constraint.rightToLeft;
        if (j == -1 && k == -1) goto _L4; else goto _L3
_L3:
        if (j == -1 || k == -1) goto _L6; else goto _L5
_L5:
        connect(j, 2, k, 1, 0);
        connect(k, 1, j, 2, 0);
_L7:
        clear(i, 1);
        clear(i, 2);
_L2:
        return;
_L6:
        if (j != -1 || k != -1)
        {
            if (constraint.rightToRight != -1)
            {
                connect(j, 2, constraint.rightToRight, 2, 0);
            } else
            if (constraint.leftToLeft != -1)
            {
                connect(k, 1, constraint.leftToLeft, 1, 0);
            }
        }
        if (true) goto _L7; else goto _L4
_L4:
        int l;
        k = constraint.startToEnd;
        l = constraint.endToStart;
        if (k == -1 && l == -1) goto _L9; else goto _L8
_L8:
        if (k == -1 || l == -1) goto _L11; else goto _L10
_L10:
        connect(k, 7, l, 6, 0);
        connect(l, 6, j, 7, 0);
_L9:
        clear(i, 6);
        clear(i, 7);
        return;
_L11:
        if (j != -1 || l != -1)
        {
            if (constraint.rightToRight != -1)
            {
                connect(j, 7, constraint.rightToRight, 7, 0);
            } else
            if (constraint.leftToLeft != -1)
            {
                connect(l, 6, constraint.leftToLeft, 6, 0);
            }
        }
        if (true) goto _L9; else goto _L12
_L12:
    }

    public void removeFromVerticalChain(int i)
    {
        if (!mConstraints.containsKey(Integer.valueOf(i))) goto _L2; else goto _L1
_L1:
        Constraint constraint;
        int j;
        int k;
        constraint = (Constraint)mConstraints.get(Integer.valueOf(i));
        j = constraint.topToBottom;
        k = constraint.bottomToTop;
        if (j == -1 && k == -1) goto _L2; else goto _L3
_L3:
        if (j == -1 || k == -1) goto _L5; else goto _L4
_L4:
        connect(j, 4, k, 3, 0);
        connect(k, 3, j, 4, 0);
_L2:
        clear(i, 3);
        clear(i, 4);
        return;
_L5:
        if (j != -1 || k != -1)
        {
            if (constraint.bottomToBottom != -1)
            {
                connect(j, 4, constraint.bottomToBottom, 4, 0);
            } else
            if (constraint.topToTop != -1)
            {
                connect(k, 3, constraint.topToTop, 3, 0);
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public void setAlpha(int i, float f)
    {
        get(i).alpha = f;
    }

    public void setApplyElevation(int i, boolean flag)
    {
        get(i).applyElevation = flag;
    }

    public void setDimensionRatio(int i, String s)
    {
        get(i).dimensionRatio = s;
    }

    public void setElevation(int i, float f)
    {
        get(i).elevation = f;
        get(i).applyElevation = true;
    }

    public void setGoneMargin(int i, int j, int k)
    {
        Constraint constraint = get(i);
        switch (j)
        {
        default:
            throw new IllegalArgumentException("unknown constraint");

        case 1: // '\001'
            constraint.goneLeftMargin = k;
            return;

        case 2: // '\002'
            constraint.goneRightMargin = k;
            return;

        case 3: // '\003'
            constraint.goneTopMargin = k;
            return;

        case 4: // '\004'
            constraint.goneBottomMargin = k;
            return;

        case 5: // '\005'
            throw new IllegalArgumentException("baseline does not support margins");

        case 6: // '\006'
            constraint.goneStartMargin = k;
            return;

        case 7: // '\007'
            constraint.goneEndMargin = k;
            break;
        }
    }

    public void setGuidelineBegin(int i, int j)
    {
        get(i).guideBegin = j;
        get(i).guideEnd = -1;
        get(i).guidePercent = -1F;
    }

    public void setGuidelineEnd(int i, int j)
    {
        get(i).guideEnd = j;
        get(i).guideBegin = -1;
        get(i).guidePercent = -1F;
    }

    public void setGuidelinePercent(int i, float f)
    {
        get(i).guidePercent = f;
        get(i).guideEnd = -1;
        get(i).guideBegin = -1;
    }

    public void setHorizontalBias(int i, float f)
    {
        get(i).horizontalBias = f;
    }

    public void setHorizontalChainStyle(int i, int j)
    {
        get(i).horizontalChainStyle = j;
    }

    public void setHorizontalWeight(int i, float f)
    {
        get(i).horizontalWeight = f;
    }

    public void setMargin(int i, int j, int k)
    {
        Constraint constraint = get(i);
        switch (j)
        {
        default:
            throw new IllegalArgumentException("unknown constraint");

        case 1: // '\001'
            constraint.leftMargin = k;
            return;

        case 2: // '\002'
            constraint.rightMargin = k;
            return;

        case 3: // '\003'
            constraint.topMargin = k;
            return;

        case 4: // '\004'
            constraint.bottomMargin = k;
            return;

        case 5: // '\005'
            throw new IllegalArgumentException("baseline does not support margins");

        case 6: // '\006'
            constraint.startMargin = k;
            return;

        case 7: // '\007'
            constraint.endMargin = k;
            break;
        }
    }

    public void setRotationX(int i, float f)
    {
        get(i).rotationX = f;
    }

    public void setRotationY(int i, float f)
    {
        get(i).rotationY = f;
    }

    public void setScaleX(int i, float f)
    {
        get(i).scaleX = f;
    }

    public void setScaleY(int i, float f)
    {
        get(i).scaleY = f;
    }

    public void setTransformPivot(int i, float f, float f1)
    {
        Constraint constraint = get(i);
        constraint.transformPivotY = f1;
        constraint.transformPivotX = f;
    }

    public void setTransformPivotX(int i, float f)
    {
        get(i).transformPivotX = f;
    }

    public void setTransformPivotY(int i, float f)
    {
        get(i).transformPivotY = f;
    }

    public void setTranslation(int i, float f, float f1)
    {
        Constraint constraint = get(i);
        constraint.translationX = f;
        constraint.translationY = f1;
    }

    public void setTranslationX(int i, float f)
    {
        get(i).translationX = f;
    }

    public void setTranslationY(int i, float f)
    {
        get(i).translationY = f;
    }

    public void setTranslationZ(int i, float f)
    {
        get(i).translationZ = f;
    }

    public void setVerticalBias(int i, float f)
    {
        get(i).verticalBias = f;
    }

    public void setVerticalChainStyle(int i, int j)
    {
        get(i).verticalChainStyle = j;
    }

    public void setVerticalWeight(int i, float f)
    {
        get(i).verticalWeight = f;
    }

    public void setVisibility(int i, int j)
    {
        get(i).visibility = j;
    }

    static 
    {
        mapToConstant = new SparseIntArray();
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
        mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
        mapToConstant.append(R.styleable.ConstraintSet_android_orientation, 27);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginTop, 16);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginRight, 14);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginStart, 15);
        mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_creator, 60);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_creator, 60);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_creator, 60);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_creator, 60);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_creator, 60);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginLeft, 24);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginRight, 28);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginStart, 31);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginEnd, 8);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginTop, 34);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginBottom, 2);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_width, 23);
        mapToConstant.append(R.styleable.ConstraintSet_android_layout_height, 21);
        mapToConstant.append(R.styleable.ConstraintSet_android_visibility, 22);
        mapToConstant.append(R.styleable.ConstraintSet_android_alpha, 43);
        mapToConstant.append(R.styleable.ConstraintSet_android_elevation, 44);
        mapToConstant.append(R.styleable.ConstraintSet_android_rotationX, 45);
        mapToConstant.append(R.styleable.ConstraintSet_android_rotationY, 46);
        mapToConstant.append(R.styleable.ConstraintSet_android_scaleX, 47);
        mapToConstant.append(R.styleable.ConstraintSet_android_scaleY, 48);
        mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotX, 49);
        mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotY, 50);
        mapToConstant.append(R.styleable.ConstraintSet_android_translationX, 51);
        mapToConstant.append(R.styleable.ConstraintSet_android_translationY, 52);
        mapToConstant.append(R.styleable.ConstraintSet_android_translationZ, 53);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
        mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
        mapToConstant.append(R.styleable.ConstraintSet_android_id, 38);
    }
}
