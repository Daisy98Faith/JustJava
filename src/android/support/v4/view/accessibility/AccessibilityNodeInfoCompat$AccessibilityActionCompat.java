// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public static class mAction
{

    public static final getActionSetProgress ACTION_ACCESSIBILITY_FOCUS = new <init>(64, null);
    public static final <init> ACTION_CLEAR_ACCESSIBILITY_FOCUS = new <init>(128, null);
    public static final <init> ACTION_CLEAR_FOCUS = new <init>(2, null);
    public static final <init> ACTION_CLEAR_SELECTION = new <init>(8, null);
    public static final <init> ACTION_CLICK = new <init>(16, null);
    public static final <init> ACTION_COLLAPSE = new <init>(0x80000, null);
    public static final <init> ACTION_CONTEXT_CLICK;
    public static final <init> ACTION_COPY = new <init>(16384, null);
    public static final <init> ACTION_CUT = new <init>(0x10000, null);
    public static final <init> ACTION_DISMISS = new <init>(0x100000, null);
    public static final <init> ACTION_EXPAND = new <init>(0x40000, null);
    public static final <init> ACTION_FOCUS = new <init>(1, null);
    public static final <init> ACTION_LONG_CLICK = new <init>(32, null);
    public static final <init> ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new <init>(256, null);
    public static final <init> ACTION_NEXT_HTML_ELEMENT = new <init>(1024, null);
    public static final <init> ACTION_PASTE = new <init>(32768, null);
    public static final <init> ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new <init>(512, null);
    public static final <init> ACTION_PREVIOUS_HTML_ELEMENT = new <init>(2048, null);
    public static final <init> ACTION_SCROLL_BACKWARD = new <init>(8192, null);
    public static final <init> ACTION_SCROLL_DOWN;
    public static final <init> ACTION_SCROLL_FORWARD = new <init>(4096, null);
    public static final <init> ACTION_SCROLL_LEFT;
    public static final <init> ACTION_SCROLL_RIGHT;
    public static final <init> ACTION_SCROLL_TO_POSITION;
    public static final <init> ACTION_SCROLL_UP;
    public static final <init> ACTION_SELECT = new <init>(4, null);
    public static final <init> ACTION_SET_PROGRESS;
    public static final <init> ACTION_SET_SELECTION = new <init>(0x20000, null);
    public static final <init> ACTION_SET_TEXT = new <init>(0x200000, null);
    public static final <init> ACTION_SHOW_ON_SCREEN;
    final Object mAction;

    public int getId()
    {
        return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(mAction);
    }

    public CharSequence getLabel()
    {
        return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(mAction);
    }

    static 
    {
        ACTION_SHOW_ON_SCREEN = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionShowOnScreen());
        ACTION_SCROLL_TO_POSITION = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionScrollToPosition());
        ACTION_SCROLL_UP = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionScrollUp());
        ACTION_SCROLL_LEFT = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionScrollLeft());
        ACTION_SCROLL_DOWN = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionScrollDown());
        ACTION_SCROLL_RIGHT = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionScrollRight());
        ACTION_CONTEXT_CLICK = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionContextClick());
        ACTION_SET_PROGRESS = new <init>(AccessibilityNodeInfoCompat.IMPL.getActionSetProgress());
    }

    public a(int i, CharSequence charsequence)
    {
        this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(i, charsequence));
    }

    newAccessibilityAction(Object obj)
    {
        mAction = obj;
    }
}
