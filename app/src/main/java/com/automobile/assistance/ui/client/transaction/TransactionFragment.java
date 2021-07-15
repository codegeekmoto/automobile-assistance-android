package com.automobile.assistance.ui.client.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.automobile.assistance.app.App;
import com.automobile.assistance.data.remote.pojo.Jobs;
import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.databinding.FragmentTransactionsBinding;
import com.automobile.assistance.ui.adapter.JobsAdapter;
import com.automobile.assistance.ui.client.getassistance.TransactionVM;
import com.automobile.assistance.ui.vmfactory.TransactionVMFactory;
import com.automobile.assistance.util.ResultObserver;

import javax.inject.Inject;

public class TransactionFragment extends Fragment implements JobsAdapter.ItemClickListener {

    @Inject
    TransactionVMFactory factory;
    private TransactionVM transactionVM;

    private FragmentTransactionsBinding binding;

    private JobsAdapter jobsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        transactionVM = new ViewModelProvider(this, factory).get(TransactionVM.class);
        binding = FragmentTransactionsBinding.inflate(inflater, container, false);

        setTransaction();

        transactionVM.getCompleteJobResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                binding.progressLayout.parent.setVisibility(View.VISIBLE);
                setTransaction();
            }
        });

        return binding.getRoot();
    }

    public void setTransaction() {
        transactionVM.getTransactionResult().observe(getViewLifecycleOwner(), new ResultObserver<Jobs>() {

            @Override
            public void onSuccess(Jobs jobs) {
                jobsAdapter = new JobsAdapter(getContext(), jobs.getTransactions());
                jobsAdapter.setItemClickListener(TransactionFragment.this);
                binding.transactionList.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.transactionList.setAdapter(jobsAdapter);

                JobsAdapter histAdapter = new JobsAdapter(getContext(), jobs.getHistory());
                histAdapter.setHistory(true);
                binding.historyList.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.historyList.setAdapter(histAdapter);
                binding.progressLayout.parent.setVisibility(View.GONE);

                if (jobs.getHistory().size() == 0) {
                    binding.noHistory.setVisibility(View.VISIBLE);
                } else {
                    binding.noHistory.setVisibility(View.GONE);
                }

                if (jobs.getTransactions().size() == 0) {
                    binding.noTransac.setVisibility(View.VISIBLE);
                } else {
                    binding.noTransac.setVisibility(View.GONE);
                }
            }
        });

        transactionVM.getTransaction("client_id");
    }

    @Override
    public void onClick(Transaction transaction) {
        transactionVM.completeJob(String.valueOf(transaction.getId()));
    }
}
