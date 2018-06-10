// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV14, TwilightManager

final class sNight
{

    private BroadcastReceiver mAutoTimeChangeReceiver;
    private IntentFilter mAutoTimeChangeReceiverFilter;
    private boolean mIsNight;
    private TwilightManager mTwilightManager;
    final AppCompatDelegateImplV14 this$0;

    final void cleanup()
    {
        if (mAutoTimeChangeReceiver != null)
        {
            mContext.unregisterReceiver(mAutoTimeChangeReceiver);
            mAutoTimeChangeReceiver = null;
        }
    }

    final void dispatchTimeChanged()
    {
        boolean flag = mTwilightManager.isNight();
        if (flag != mIsNight)
        {
            mIsNight = flag;
            applyDayNight();
        }
    }

    final int getApplyableNightMode()
    {
        mIsNight = mTwilightManager.isNight();
        return !mIsNight ? 1 : 2;
    }

    final void setup()
    {
        cleanup();
        if (mAutoTimeChangeReceiver == null)
        {
            mAutoTimeChangeReceiver = new BroadcastReceiver() {

                final AppCompatDelegateImplV14.AutoNightModeManager this$1;

                public void onReceive(Context context, Intent intent)
                {
                    dispatchTimeChanged();
                }

            
            {
                this$1 = AppCompatDelegateImplV14.AutoNightModeManager.this;
                super();
            }
            };
        }
        if (mAutoTimeChangeReceiverFilter == null)
        {
            mAutoTimeChangeReceiverFilter = new IntentFilter();
            mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
            mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
        }
        mContext.registerReceiver(mAutoTimeChangeReceiver, mAutoTimeChangeReceiverFilter);
    }

    this._cls1(TwilightManager twilightmanager)
    {
        this$0 = AppCompatDelegateImplV14.this;
        super();
        mTwilightManager = twilightmanager;
        mIsNight = twilightmanager.isNight();
    }
}
