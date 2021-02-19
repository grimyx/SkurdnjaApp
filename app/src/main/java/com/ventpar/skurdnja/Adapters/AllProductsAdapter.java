package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;

import java.util.List;
import java.util.zip.Inflater;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ViewHandler> {

    private final LayoutInflater mInflater;
    private List<Product> mAllproducts;
    private OnItemClickListener mListener;

    public AllProductsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.all_products_layout,parent,false);
        return new ViewHandler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, final int position) {
        holder.productName.setText(mAllproducts.get(position).getName());
        holder.productPrice.setText(Double.toString(mAllproducts.get(position).getPrice()));
    }

    public void setProducts(List<Product> products){

        mAllproducts = products;
        notifyDataSetChanged();
    }

    public Product getProduct(int pos){
        return mAllproducts.get(pos);
    }

    @Override
    public int getItemCount() {
        if( mAllproducts != null){
            return mAllproducts.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private View mView;
        public TextView productName;
        public TextView productPrice;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            productName = itemView.findViewById(R.id.text_product_info);
            productPrice = itemView.findViewById(R.id.text_product_price);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(getProduct(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
