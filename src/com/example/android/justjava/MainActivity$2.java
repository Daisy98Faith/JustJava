// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.android.justjava;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

// Referenced classes of package com.example.android.justjava:
//            MainActivity

class this._cls0
    implements android.view.tener
{

    final MainActivity this$0;

    public void onClick(View view)
    {
        Toast.makeText(MainActivity.this, (new StringBuilder()).append(quantity).append(" cups ordered at ").append(NumberFormat.getCurrencyInstance().format(quantity * price)).toString(), 0).show();
        quantity = min;
        ((TextView)findViewById(0x7f0d007b)).setText(String.valueOf(quantity));
    }

    ()
    {
        this$0 = MainActivity.this;
        super();
    }
}
