// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.example.android.justjava;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{

    int max;
    int min;
    int price;
    int quantity;

    public MainActivity()
    {
        min = 0;
        max = 25;
        price = 5;
        quantity = min;
    }

    private void display(int i)
    {
        ((TextView)findViewById(0x7f0d007b)).setText(String.valueOf(i));
    }

    private void displayPrice(int i)
    {
        ((TextView)findViewById(0x7f0d007c)).setText(NumberFormat.getCurrencyInstance().format(price * i));
    }

    public void decrement(View view)
    {
        if (quantity > min)
        {
            quantity = quantity - 1;
        }
        display(quantity);
    }

    public void increment(View view)
    {
        if (quantity < max)
        {
            quantity = quantity + 1;
        }
        display(quantity);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001b);
        bundle = (TextView)findViewById(0x7f0d007b);
        TextView textview = (TextView)findViewById(0x7f0d007c);
        bundle.setText(String.valueOf(quantity));
        textview.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(quantity)));
        bundle.addTextChangedListener(new TextWatcher() {

            final MainActivity this$0;

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                displayPrice(quantity);
            }

            
            {
                this$0 = MainActivity.this;
                super();
            }
        });
    }

    public void submitOrder(View view)
    {
        display(quantity);
        Snackbar.make(view, (new StringBuilder()).append("Confirm Order:\n").append(quantity).append(" cups at ").append(NumberFormat.getCurrencyInstance().format(quantity * price)).toString(), -2).setAction("CONFIRM", new android.view.View.OnClickListener() {

            final MainActivity this$0;

            public void onClick(View view1)
            {
                Toast.makeText(MainActivity.this, (new StringBuilder()).append(quantity).append(" cups ordered at ").append(NumberFormat.getCurrencyInstance().format(quantity * price)).toString(), 0).show();
                quantity = min;
                ((TextView)findViewById(0x7f0d007b)).setText(String.valueOf(quantity));
            }

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }).show();
    }

}
