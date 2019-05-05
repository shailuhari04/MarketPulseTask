package com.marketpulsetask.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.marketpulsetask.pojo.CriteriaItem;
import com.marketpulsetask.ui.view.CriteriaListActivity;

import java.util.List;

public class Navigator {

    public static void navigateToCriteriaListScreen(Context context, List<CriteriaItem> criteriaList) {
        Intent i = new Intent(context, CriteriaListActivity.class);
        Bundle arg = new Bundle();
        arg.putParcelable(Constants.Companion.getCRITERIA_BUNDLE(), (Parcelable) criteriaList);
        i.putExtra(Constants.Companion.getBUNDLE_DATA(), arg);
        context.startActivity(i);
    }

    public static void exitActivity(Activity context) {
        context.finishAffinity();
    }
/*
    public static void navigateToProfileActivity(Context context) {
        Intent i = new Intent(context, ProfileActivity.class);
        context.startActivity(i);
    }

    public static void navigateToMainActivity(Context context, String title) {
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra(TOOLBAR_TITLE, title);
        context.startActivity(i);
    }*/
}
