// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Handler;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls1
    implements Runnable
{

    final ewHidden this$1;

    public void run()
    {
        onViewHidden(3);
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class android/support/design/widget/BaseTransientBottomBar$5

/* anonymous class */
    class BaseTransientBottomBar._cls5
        implements BaseTransientBottomBar.OnAttachStateChangeListener
    {

        final BaseTransientBottomBar this$0;

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            if (isShownOrQueued())
            {
                BaseTransientBottomBar.sHandler.post(new BaseTransientBottomBar._cls5._cls1());
            }
        }

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
    }

}
