// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

// Referenced classes of package android.support.design.widget:
//            BottomSheetDialog

public class BottomSheetDialogFragment extends AppCompatDialogFragment
{

    public BottomSheetDialogFragment()
    {
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
