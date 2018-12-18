package com.egecius.electriccars.paging;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.egecius.electriccars.mainactivity.CarRecyclerViewAdapter;
import com.egecius.electriccars.room.Car;

public class MyPagedListAdapter extends PagedListAdapter<Car, CarRecyclerViewAdapter.MyViewHolder> {

    private static final DiffUtil.ItemCallback<Car> DIFF_CALLBACK = new DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected MyPagedListAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public CarRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CarRecyclerViewAdapter.MyViewHolder holder, int position) {

    }
}
