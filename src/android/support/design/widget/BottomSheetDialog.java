// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout, BottomSheetBehavior

public class BottomSheetDialog extends AppCompatDialog
{

    private BottomSheetBehavior mBehavior;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {

        final BottomSheetDialog this$0;

        public void onSlide(View view, float f)
        {
        }

        public void onStateChanged(View view, int j)
        {
            if (j == 5)
            {
                cancel();
            }
        }

            
            {
                this$0 = BottomSheetDialog.this;
                super();
            }
    };
    boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private boolean mCanceledOnTouchOutsideSet;

    public BottomSheetDialog(Context context)
    {
        this(context, 0);
    }

    public BottomSheetDialog(Context context, int i)
    {
        super(context, getThemeResId(context, i));
        mCancelable = true;
        mCanceledOnTouchOutside = true;
        supportRequestWindowFeature(1);
    }

    protected BottomSheetDialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        super(context, flag, oncancellistener);
        mCancelable = true;
        mCanceledOnTouchOutside = true;
        supportRequestWindowFeature(1);
        mCancelable = flag;
    }

    private static int getThemeResId(Context context, int i)
    {
label0:
        {
            int j = i;
            if (i == 0)
            {
                TypedValue typedvalue = new TypedValue();
                if (!context.getTheme().resolveAttribute(android.support.design.R.attr.bottomSheetDialogTheme, typedvalue, true))
                {
                    break label0;
                }
                j = typedvalue.resourceId;
            }
            return j;
        }
        return android.support.design.R.style.Theme_Design_Light_BottomSheetDialog;
    }

    private View wrapInBottomSheet(int i, View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        CoordinatorLayout coordinatorlayout = (CoordinatorLayout)View.inflate(getContext(), android.support.design.R.layout.design_bottom_sheet_dialog, null);
        View view1 = view;
        if (i != 0)
        {
            view1 = view;
            if (view == null)
            {
                view1 = getLayoutInflater().inflate(i, coordinatorlayout, false);
            }
        }
        view = (FrameLayout)coordinatorlayout.findViewById(android.support.design.R.id.design_bottom_sheet);
        mBehavior = BottomSheetBehavior.from(view);
        mBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBehavior.setHideable(mCancelable);
        if (layoutparams == null)
        {
            view.addView(view1);
        } else
        {
            view.addView(view1, layoutparams);
        }
        coordinatorlayout.findViewById(android.support.design.R.id.touch_outside).setOnClickListener(new android.view.View.OnClickListener() {

            final BottomSheetDialog this$0;

            public void onClick(View view2)
            {
                if (mCancelable && isShowing() && shouldWindowCloseOnTouchOutside())
                {
                    cancel();
                }
            }

            
            {
                this$0 = BottomSheetDialog.this;
                super();
            }
        });
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() {

            final BottomSheetDialog this$0;

            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
            {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilitynodeinfocompat);
                if (mCancelable)
                {
                    accessibilitynodeinfocompat.addAction(0x100000);
                    accessibilitynodeinfocompat.setDismissable(true);
                    return;
                } else
                {
                    accessibilitynodeinfocompat.setDismissable(false);
                    return;
                }
            }

            public boolean performAccessibilityAction(View view2, int j, Bundle bundle)
            {
                if (j == 0x100000 && mCancelable)
                {
                    cancel();
                    return true;
                } else
                {
                    return super.performAccessibilityAction(view2, j, bundle);
                }
            }

            
            {
                this$0 = BottomSheetDialog.this;
                super();
            }
        });
        view.setOnTouchListener(new android.view.View.OnTouchListener() {

            final BottomSheetDialog this$0;

            public boolean onTouch(View view2, MotionEvent motionevent)
            {
                return true;
            }

            
            {
                this$0 = BottomSheetDialog.this;
                super();
            }
        });
        return coordinatorlayout;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().setLayout(-1, -1);
    }

    protected void onStart()
    {
        super.onStart();
        if (mBehavior != null)
        {
            mBehavior.setState(4);
        }
    }

    public void setCancelable(boolean flag)
    {
        super.setCancelable(flag);
        if (mCancelable != flag)
        {
            mCancelable = flag;
            if (mBehavior != null)
            {
                mBehavior.setHideable(flag);
            }
        }
    }

    public void setCanceledOnTouchOutside(boolean flag)
    {
        super.setCanceledOnTouchOutside(flag);
        if (flag && !mCancelable)
        {
            mCancelable = true;
        }
        mCanceledOnTouchOutside = flag;
        mCanceledOnTouchOutsideSet = true;
    }

    public void setContentView(int i)
    {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    public void setContentView(View view)
    {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.setContentView(wrapInBottomSheet(0, view, layoutparams));
    }

    boolean shouldWindowCloseOnTouchOutside()
    {
        if (!mCanceledOnTouchOutsideSet)
        {
            if (android.os.Build.VERSION.SDK_INT < 11)
            {
                mCanceledOnTouchOutside = true;
            } else
            {
                TypedArray typedarray = getContext().obtainStyledAttributes(new int[] {
                    0x101035b
                });
                mCanceledOnTouchOutside = typedarray.getBoolean(0, true);
                typedarray.recycle();
            }
            mCanceledOnTouchOutsideSet = true;
        }
        return mCanceledOnTouchOutside;
    }
}
