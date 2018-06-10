// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            ActivityCompat, SharedElementCallback

private static class mCallback extends 
{

    private SharedElementCallback mCallback;

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
    {
        return mCallback.onCaptureSharedElementSnapshot(view, matrix, rectf);
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable)
    {
        return mCallback.onCreateSnapshotView(context, parcelable);
    }

    public void onMapSharedElements(List list, Map map)
    {
        mCallback.onMapSharedElements(list, map);
    }

    public void onRejectSharedElements(List list)
    {
        mCallback.onRejectSharedElements(list);
    }

    public void onSharedElementEnd(List list, List list1, List list2)
    {
        mCallback.onSharedElementEnd(list, list1, list2);
    }

    public void onSharedElementStart(List list, List list1, List list2)
    {
        mCallback.onSharedElementStart(list, list1, list2);
    }

    public void onSharedElementsArrived(List list, List list1, final stenerBridge listener)
    {
        mCallback.onSharedElementsArrived(list, list1, new SharedElementCallback.OnSharedElementsReadyListener() {

            final ActivityCompat.SharedElementCallback23Impl this$0;
            final ActivityCompatApi23.OnSharedElementsReadyListenerBridge val$listener;

            public void onSharedElementsReady()
            {
                listener.onSharedElementsReady();
            }

            
            {
                this$0 = ActivityCompat.SharedElementCallback23Impl.this;
                listener = onsharedelementsreadylistenerbridge;
                super();
            }
        });
    }

    public _cls1.val.listener(SharedElementCallback sharedelementcallback)
    {
        mCallback = sharedelementcallback;
    }
}
