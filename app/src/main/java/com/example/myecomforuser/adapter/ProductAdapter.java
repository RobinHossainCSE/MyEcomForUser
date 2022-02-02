package com.example.myecomforuser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myecomforuser.callback.OnProductItemClickListener;
import com.example.myecomforuser.databinding.ItemListRowBinding;
import com.example.myecomforuser.models.ProductModel;

public class ProductAdapter extends ListAdapter<ProductModel, ProductAdapter.ProductViewHolder> {
    private OnProductItemClickListener listener;

    public ProductAdapter(OnProductItemClickListener listener) {

        super(new ProductDiff());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemListRowBinding binding = ItemListRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ProductViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ItemListRowBinding binding;
        public ProductViewHolder(ItemListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.detailsProductImageView.setOnClickListener(v->{
                listener.onProductItemClicked(getItem(getAdapterPosition()).getProductId());
            });
        }
        public void bind(ProductModel productModel) {
            binding.setProduct(productModel);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<ProductModel>{
        @Override
        public boolean areItemsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }
    }

}
