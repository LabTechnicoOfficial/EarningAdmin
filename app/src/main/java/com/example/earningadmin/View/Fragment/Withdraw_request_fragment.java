package com.example.earningadmin.View.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earningadmin.Adapter.Withdraw_request_adapter;
import com.example.earningadmin.Model.allWithdrow_response;
import com.example.earningadmin.Model.pendingWithdrowRequest_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AllWithdrow;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class Withdraw_request_fragment extends Fragment {

    RecyclerView withdrawView;
    ImageView backButton;
    MaterialButtonToggleGroup toggleGroupButton;
    AllWithdrow allWithdrow;
    TextView totalAmountText;
    private pendingWithdrowRequest_response pendingList;
    private Withdraw_request_adapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pending_list_func();
    }

    public void pending_list_func() {
        allWithdrow.getpendingRequest("abc**xyz").observe(getViewLifecycleOwner(), new Observer<pendingWithdrowRequest_response>() {
            @Override
            public void onChanged(pendingWithdrowRequest_response pendingWithdrowRequest_response) {
                totalAmountText.setText(pendingWithdrowRequest_response.getTotal_amount());

                pendingList = new pendingWithdrowRequest_response();
                pendingList = pendingWithdrowRequest_response;
                adapter = new Withdraw_request_adapter(pendingList);
                withdrawView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.withdraw_request_fragment, container, false);

        allWithdrow = new ViewModelProvider(this).get(AllWithdrow.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        withdrawView = (RecyclerView) view.findViewById(R.id.withdrawViewID);
        withdrawView.setHasFixedSize(true);
        withdrawView.setLayoutManager(new LinearLayoutManager(getActivity()));

        totalAmountText = (TextView) view.findViewById(R.id.totalAmountTextID);
        toggleGroupButton = (MaterialButtonToggleGroup) view.findViewById(R.id.toggleGroupButtonID);

        toggleGroupButton.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (checkedId == R.id.pendingButtonID) {
                    Toast.makeText(getActivity(), "Pending", Toast.LENGTH_SHORT).show();
                    pending_list_func();
                } else if (checkedId == R.id.approvedButtonID) {
                    Toast.makeText(getActivity(), "Approved", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }
}