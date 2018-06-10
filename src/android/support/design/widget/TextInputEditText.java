// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

// Referenced classes of package android.support.design.widget:
//            TextInputLayout

public class TextInputEditText extends AppCompatEditText
{

    public TextInputEditText(Context context)
    {
        super(context);
    }

    public TextInputEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public TextInputEditText(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        InputConnection inputconnection = super.onCreateInputConnection(editorinfo);
        if (inputconnection == null || editorinfo.hintText != null) goto _L2; else goto _L1
_L1:
        ViewParent viewparent = getParent();
_L7:
        if (!(viewparent instanceof View)) goto _L2; else goto _L3
_L3:
        if (!(viewparent instanceof TextInputLayout)) goto _L5; else goto _L4
_L4:
        editorinfo.hintText = ((TextInputLayout)viewparent).getHint();
_L2:
        return inputconnection;
_L5:
        viewparent = viewparent.getParent();
        if (true) goto _L7; else goto _L6
_L6:
    }
}
