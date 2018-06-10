// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserServiceCompatApi23, MediaBrowserServiceCompatApi21

class <init> extends <init>
    implements <init>
{

    final MediaBrowserServiceCompat this$0;

    public void onCreate()
    {
        mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
        MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }

    public void onLoadItem(final String final_obj, mServiceObj mserviceobj)
    {
        mserviceobj = new MediaBrowserServiceCompat.Result(mserviceobj) {

            final MediaBrowserServiceCompat.MediaBrowserServiceImplApi23 this$1;
            final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            public void detach()
            {
                resultWrapper.detach();
            }

            void onResultSent(MediaBrowserCompat.MediaItem mediaitem, int i)
            {
                if (mediaitem == null)
                {
                    resultWrapper.sendResult(null);
                    return;
                } else
                {
                    Parcel parcel = Parcel.obtain();
                    mediaitem.writeToParcel(parcel, 0);
                    resultWrapper.sendResult(parcel);
                    return;
                }
            }

            volatile void onResultSent(Object obj, int i)
            {
                onResultSent((MediaBrowserCompat.MediaItem)obj, i);
            }

            
            {
                this$1 = MediaBrowserServiceCompat.MediaBrowserServiceImplApi23.this;
                resultWrapper = resultwrapper;
                super(final_obj);
            }
        };
        MediaBrowserServiceCompat.this.onLoadItem(final_obj, mserviceobj);
    }

    _cls1.val.resultWrapper()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super(MediaBrowserServiceCompat.this);
    }
}
