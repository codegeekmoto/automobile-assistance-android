package com.automobile.assistance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.databinding.ItemTransactionBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private Context context;
    private List<Transaction> transactionList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    private boolean isHistory = false;

    public JobsAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemTransactionBinding binding = ItemTransactionBinding.inflate(LayoutInflater.from(context),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
//        holder.binding.serviceName.setText(transaction.getServiceName());
//        holder.binding.companyName.setText(transaction.getCompanyName());
        holder.binding.mechanicName.setText(" Mechanic Name: "+ transaction.getMechanicFname() + " " + transaction.getMechanicLname());
        holder.binding.code.setText("Transaction Code: "+ transaction.getCode());
        holder.binding.btnComplete.setOnClickListener(v -> {
            itemClickListener.onClick(transaction);
        });

        if (isHistory) {
            holder.binding.step1.setVisibility(View.GONE);
            holder.binding.step2.setVisibility(View.GONE);
            holder.binding.step3.setVisibility(View.GONE);
            holder.binding.div.setVisibility(View.GONE);
            holder.binding.btnComplete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemTransactionBinding binding;

        public ViewHolder(@NonNull @NotNull ItemTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemClickListener {
        void onClick(Transaction transaction);
    }
}
