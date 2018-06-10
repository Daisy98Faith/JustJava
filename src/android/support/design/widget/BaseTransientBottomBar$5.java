// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Handler;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls0
    implements AttachStateChangeListener
{

    final BaseTransientBottomBar this$0;

    public void onViewAttachedToWindow(View view)
    {
    }

    public void onViewDetachedFromWindow(View view)
    {
        if (isShownOrQueued())
        {
            BaseTransientBottomBar.sHandler.post(new Runnable() {

                final BaseTransientBottomBar._cls5 this$1;

                public void run()
                {
                    onViewHidden(3);
                }

            
            {
                this$1 = BaseTransientBottomBar._cls5.this;
                super();
            }
            });
        }
    }

    _cls1.this._cls1()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
