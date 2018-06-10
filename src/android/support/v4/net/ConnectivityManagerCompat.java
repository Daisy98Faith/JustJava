// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.annotation.Annotation;

public final class ConnectivityManagerCompat
{
    static class ConnectivityManagerCompatApi16Impl extends ConnectivityManagerCompatBaseImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return connectivitymanager.isActiveNetworkMetered();
        }

        ConnectivityManagerCompatApi16Impl()
        {
        }
    }

    static class ConnectivityManagerCompatApi24Impl extends ConnectivityManagerCompatApi16Impl
    {

        public int getRestrictBackgroundStatus(ConnectivityManager connectivitymanager)
        {
            return connectivitymanager.getRestrictBackgroundStatus();
        }

        ConnectivityManagerCompatApi24Impl()
        {
        }
    }

    static class ConnectivityManagerCompatBaseImpl
        implements ConnectivityManagerCompatImpl
    {

        public int getRestrictBackgroundStatus(ConnectivityManager connectivitymanager)
        {
            return 3;
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            connectivitymanager = connectivitymanager.getActiveNetworkInfo();
            if (connectivitymanager != null) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            switch (connectivitymanager.getType())
            {
            case 8: // '\b'
            default:
                return true;

            case 1: // '\001'
            case 7: // '\007'
            case 9: // '\t'
                return false;

            case 0: // '\0'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
                break;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        ConnectivityManagerCompatBaseImpl()
        {
        }
    }

    static interface ConnectivityManagerCompatImpl
    {

        public abstract int getRestrictBackgroundStatus(ConnectivityManager connectivitymanager);

        public abstract boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager);
    }

    public static interface RestrictBackgroundStatus
        extends Annotation
    {
    }


    private static final ConnectivityManagerCompatImpl IMPL;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    private ConnectivityManagerCompat()
    {
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivitymanager, Intent intent)
    {
        intent = (NetworkInfo)intent.getParcelableExtra("networkInfo");
        if (intent != null)
        {
            return connectivitymanager.getNetworkInfo(intent.getType());
        } else
        {
            return null;
        }
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager connectivitymanager)
    {
        return IMPL.getRestrictBackgroundStatus(connectivitymanager);
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
    {
        return IMPL.isActiveNetworkMetered(connectivitymanager);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            IMPL = new ConnectivityManagerCompatApi24Impl();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            IMPL = new ConnectivityManagerCompatApi16Impl();
        } else
        {
            IMPL = new ConnectivityManagerCompatBaseImpl();
        }
    }
}
