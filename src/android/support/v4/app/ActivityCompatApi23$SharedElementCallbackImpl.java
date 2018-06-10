// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            ActivityCompatApi23

private static class mCallback extends SharedElementCallback
{

    private redElementsReady mCallback;

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
    {
        return mCallback.CaptureSharedElementSnapshot(view, matrix, rectf);
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable)
    {
        return mCallback.CreateSnapshotView(context, parcelable);
    }

    public void onMapSharedElements(List list, Map map)
    {
        mCallback.MapSharedElements(list, map);
    }

    public void onRejectSharedElements(List list)
    {
        mCallback.RejectSharedElements(list);
    }

    public void onSharedElementEnd(List list, List list1, List list2)
    {
        mCallback.SharedElementEnd(list, list1, list2);
    }

    public void onSharedElementStart(List list, List list1, List list2)
    {
        mCallback.SharedElementStart(list, list1, list2);
    }

    public void onSharedElementsArrived(List list, List list1, final android.app.tener listener)
    {
        mCallback.SharedElementsArrived(list, list1, new ActivityCompatApi23.OnSharedElementsReadyListenerBridge() {

            final ActivityCompatApi23.SharedElementCallbackImpl this$0;
            final android.app.SharedElementCallback.OnSharedElementsReadyListener val$listener;

            public void onSharedElementsReady()
            {
                listener.onSharedElementsReady();
            }

            
            {
                this$0 = ActivityCompatApi23.SharedElementCallbackImpl.this;
                listener = onsharedelementsreadylistener;
                super();
            }
        });
    }

    public _cls1.val.listener(_cls1.val.listener listener)
    {
        mCallback = listener;
    }
}
