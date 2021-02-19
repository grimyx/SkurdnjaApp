package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;

public class ProductSpinnerAdapter extends ArrayAdapter<Product> {

    private Context mContext;
    private int mResource;
    private List<Product> mData;

    public ProductSpinnerAdapter(@NonNull Context context, int resource, List<Product> data) {
        super(context, resource);
        mContext = context;
        mResource = resource;
        mData = data;
        Log.v("Data size", Integer.toString(mData.size()));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public int getPosition(@Nullable Product item) {
        return mData.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
