// Generated by view binder compiler. Do not edit!
package com.tukorea.waiter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tukorea.waiter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStoreListPageBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ListView StorelistView;

  @NonNull
  public final ImageButton menubtn;

  private ActivityStoreListPageBinding(@NonNull LinearLayout rootView,
      @NonNull ListView StorelistView, @NonNull ImageButton menubtn) {
    this.rootView = rootView;
    this.StorelistView = StorelistView;
    this.menubtn = menubtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStoreListPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStoreListPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_store_list_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStoreListPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.StorelistView;
      ListView StorelistView = ViewBindings.findChildViewById(rootView, id);
      if (StorelistView == null) {
        break missingId;
      }

      id = R.id.menubtn;
      ImageButton menubtn = ViewBindings.findChildViewById(rootView, id);
      if (menubtn == null) {
        break missingId;
      }

      return new ActivityStoreListPageBinding((LinearLayout) rootView, StorelistView, menubtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
