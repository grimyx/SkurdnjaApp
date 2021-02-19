package com.ventpar.skurdnja;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ventpar.skurdnja.Adapters.ProductSpinnerAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaViewModels.AddProductViewModel;

import java.util.List;

public class SelectProductDialog extends DialogFragment {

    ProductSelectDialogInterface listener;

    private Spinner mProductSelector;
    private EditText mQuantity;
    private AddProductViewModel mViewModel;

    public interface ProductSelectDialogInterface {
        public void onDialogPositiveClick(int quantity, Product product);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_select_product,null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int quantity;

                        if (mQuantity.getText().toString().isEmpty()){
                            quantity = 0;
                        }
                        else
                        {
                            quantity = Integer.parseInt(mQuantity.getText().toString());
                        }
                        listener.onDialogPositiveClick(quantity, (Product) mProductSelector.getSelectedItem());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectProductDialog.this.getDialog().cancel();
                    }
                });



        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ProductSelectDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement ProductSelectDialogFragmenInterface");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mProductSelector = getDialog().findViewById(R.id.product_select_spinner);
        mQuantity = getDialog().findViewById(R.id.product_quantity);
         // ovde ide kod za Spiner Adapter

        mViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);
        mViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(getDialog().getContext(),
                        android.R.layout.simple_spinner_item,
                        products);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mProductSelector.setAdapter(adapter);
        }

});
    }
}
