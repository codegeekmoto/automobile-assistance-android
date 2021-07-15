package com.automobile.assistance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.databinding.ItemJobMechanicBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MechanicJobAdapter extends RecyclerView.Adapter<MechanicJobAdapter.ViewHolder> {

    private Context context;
    private List<Transaction> transactionList = new ArrayList<>();
    private ItemClick itemClick;

    public MechanicJobAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @NotNull
    @Override
    public MechanicJobAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemJobMechanicBinding binding = ItemJobMechanicBinding.inflate(LayoutInflater.from(context),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
//        holder.binding.serviceName.setText(transaction.getServiceName());
//        holder.binding.companyName.setText(transaction.getCompanyName());

        holder.binding.clientName.setText(" Client Name: "+ transaction.getClientFname() + " " + transaction.getClientLname());
        holder.binding.code.setText("Transaction Code: "+ transaction.getCode());
        holder.binding.getRoot().setClickable(true);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.onClick(transaction);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemJobMechanicBinding binding;

        public ViewHolder(@NonNull @NotNull ItemJobMechanicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemClick {
        void onClick(Transaction transaction);
    }
}
