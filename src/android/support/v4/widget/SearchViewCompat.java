// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;

public final class SearchViewCompat
{
    public static interface OnCloseListener
    {

        public abstract boolean onClose();
    }

    public static abstract class OnCloseListenerCompat
        implements OnCloseListener
    {

        public boolean onClose()
        {
            return false;
        }

        public OnCloseListenerCompat()
        {
        }
    }

    public static interface OnQueryTextListener
    {

        public abstract boolean onQueryTextChange(String s);

        public abstract boolean onQueryTextSubmit(String s);
    }

    public static abstract class OnQueryTextListenerCompat
        implements OnQueryTextListener
    {

        public boolean onQueryTextChange(String s)
        {
            return false;
        }

        public boolean onQueryTextSubmit(String s)
        {
            return false;
        }

        public OnQueryTextListenerCompat()
        {
        }
    }


    private SearchViewCompat(Context context)
    {
    }

    private static void checkIfLegalArg(View view)
    {
        if (view == null)
        {
            throw new IllegalArgumentException("searchView must be non-null");
        }
        if (!(view instanceof SearchView))
        {
            throw new IllegalArgumentException("searchView must be an instance of android.widget.SearchView");
        } else
        {
            return;
        }
    }

    public static CharSequence getQuery(View view)
    {
        checkIfLegalArg(view);
        return ((SearchView)view).getQuery();
    }

    public static boolean isIconified(View view)
    {
        checkIfLegalArg(view);
        return ((SearchView)view).isIconified();
    }

    public static boolean isQueryRefinementEnabled(View view)
    {
        checkIfLegalArg(view);
        return ((SearchView)view).isQueryRefinementEnabled();
    }

    public static boolean isSubmitButtonEnabled(View view)
    {
        checkIfLegalArg(view);
        return ((SearchView)view).isSubmitButtonEnabled();
    }

    private static android.widget.SearchView.OnCloseListener newOnCloseListener(OnCloseListener oncloselistener)
    {
        return new android.widget.SearchView.OnCloseListener(oncloselistener) {

            final OnCloseListener val$listener;

            public boolean onClose()
            {
                return listener.onClose();
            }

            
            {
                listener = oncloselistener;
                super();
            }
        };
    }

    private static android.widget.SearchView.OnQueryTextListener newOnQueryTextListener(OnQueryTextListener onquerytextlistener)
    {
        return new android.widget.SearchView.OnQueryTextListener(onquerytextlistener) {

            final OnQueryTextListener val$listener;

            public boolean onQueryTextChange(String s)
            {
                return listener.onQueryTextChange(s);
            }

            public boolean onQueryTextSubmit(String s)
            {
                return listener.onQueryTextSubmit(s);
            }

            
            {
                listener = onquerytextlistener;
                super();
            }
        };
    }

    public static View newSearchView(Context context)
    {
        return new SearchView(context);
    }

    public static void setIconified(View view, boolean flag)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setIconified(flag);
    }

    public static void setImeOptions(View view, int i)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setImeOptions(i);
    }

    public static void setInputType(View view, int i)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setInputType(i);
    }

    public static void setMaxWidth(View view, int i)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setMaxWidth(i);
    }

    public static void setOnCloseListener(View view, OnCloseListener oncloselistener)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setOnCloseListener(newOnCloseListener(oncloselistener));
    }

    public static void setOnQueryTextListener(View view, OnQueryTextListener onquerytextlistener)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setOnQueryTextListener(newOnQueryTextListener(onquerytextlistener));
    }

    public static void setQuery(View view, CharSequence charsequence, boolean flag)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setQuery(charsequence, flag);
    }

    public static void setQueryHint(View view, CharSequence charsequence)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setQueryHint(charsequence);
    }

    public static void setQueryRefinementEnabled(View view, boolean flag)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setQueryRefinementEnabled(flag);
    }

    public static void setSearchableInfo(View view, ComponentName componentname)
    {
        checkIfLegalArg(view);
        SearchManager searchmanager = (SearchManager)view.getContext().getSystemService("search");
        ((SearchView)view).setSearchableInfo(searchmanager.getSearchableInfo(componentname));
    }

    public static void setSubmitButtonEnabled(View view, boolean flag)
    {
        checkIfLegalArg(view);
        ((SearchView)view).setSubmitButtonEnabled(flag);
    }
}
