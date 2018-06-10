// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, ParcelableVolumeInfo

class this._cls0 extends android.support.v4.media.lBase._cls1
{

    final ndVolumeInfoChanged this$0;

    public void onVolumeChanged(VolumeProviderCompat volumeprovidercompat)
    {
        if (olumeProvider != volumeprovidercompat)
        {
            return;
        } else
        {
            volumeprovidercompat = new ParcelableVolumeInfo(olumeType, ocalStream, volumeprovidercompat.getVolumeControl(), volumeprovidercompat.getMaxVolume(), volumeprovidercompat.getCurrentVolume());
            ndVolumeInfoChanged(volumeprovidercompat);
            return;
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
