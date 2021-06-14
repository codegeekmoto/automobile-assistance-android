package com.automobile.assistance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.Service22;
import com.automobile.assistance.databinding.ItemServiceBinding;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Context context;
    private List<Service22> service22List;
    private ServiceClickListener serviceClickListener;

    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.service22List = service22List;
    }

    public void setServiceClickListener(ServiceClickListener serviceClickListener) {
        this.serviceClickListener = serviceClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServiceBinding binding = ItemServiceBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service22 service22 = service22List.get(position);
        holder.binding.name.setText(service22.getName());
    }

    @Override
    public int getItemCount() {
        return service22List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemServiceBinding binding;

        public ViewHolder(@NonNull ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ServiceClickListener {
        void onServiceClick(Service22 service22);
    }
}
