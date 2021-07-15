package com.automobile.assistance.ui.mechanic;

import    android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.data.remote.pojo.Jobs;
import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.databinding.FragmentMechanicBinding;
import com.automobile.assistance.ui.adapter.JobsAdapter;
import com.automobile.assistance.ui.adapter.MechanicJobAdapter;
import com.automobile.assistance.ui.client.getassistance.TransactionVM;
import com.automobile.assistance.ui.vmfactory.TransactionVMFactory;
import com.automobile.assistance.util.ResultObserver;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MechanicFragment extends Fragment implements MechanicJobAdapter.ItemClick {

    @Inject
    TransactionVMFactory factory;
    private TransactionVM transactionVM;

    private FragmentMechanicBinding binding;

    public MechanicFragment() {
        // Required empty public constructor
    }

    public static MechanicFragment newInstance() {
        return new MechanicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        transactionVM = new ViewModelProvider(this, factory).get(TransactionVM.class);

        binding = FragmentMechanicBinding.inflate(inflater, container, false);

        setTransaction();

        return binding.getRoot();
    }

    public void setTransaction() {
        transactionVM.getTransactionResult().observe(getViewLifecycleOwner(), new ResultObserver<Jobs>() {

            @Override
            public void onSuccess(Jobs jobs) {
                MechanicJobAdapter adapter = new MechanicJobAdapter(getContext(), jobs.getTransactions());
                binding.transactionList.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.setItemClick(MechanicFragment.this);
                binding.transactionList.setAdapter(adapter);

                if (jobs.getTransactions().size() == 0) {
                    binding.noTransaction.setVisibility(View.VISIBLE);
                }

                MechanicJobAdapter histAdapter = new MechanicJobAdapter(getContext(), jobs.getHistory());
                binding.historyList.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.historyList.setAdapter(histAdapter);

                if (jobs.getHistory().size() == 0) {
                    binding.noHistory.setVisibility(View.VISIBLE);
                }

                binding.progressLayout.parent.setVisibility(View.GONE);
            }
        });

        transactionVM.getTransaction("mechanic_id");
    }

    @Override
    public void onClick(Transaction transaction) {
        Bundle bundle = new Bundle();
        bundle.putString("transaction", new Gson().toJson(transaction));

        Navigation.findNavController(getActivity(), R.id.mechanic_nav_host_fragment)
                .navigate(R.id.nav_mechanic_map, bundle);
    }
}