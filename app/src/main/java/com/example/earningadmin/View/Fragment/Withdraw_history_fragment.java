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

import com.example.earningadmin.Adapter.Withdraw_history_adapter;
import com.example.earningadmin.Adapter.Withdraw_request_adapter;
import com.example.earningadmin.Model.allWithdrow_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AllWithdrow;

public class Withdraw_history_fragment extends Fragment {

    TextView totalWithdrawText;
    RecyclerView withdrawView;
    ImageView backButton;
    AllWithdrow allWithdrow;
    private allWithdrow_response withdrawList;
    private Withdraw_history_adapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main();
    }

    private void main() {
        allWithdrow.getWithdrow("abc**def").observe(getViewLifecycleOwner(), new Observer<allWithdrow_response>() {
            @Override
            public void onChanged(allWithdrow_response allWithdrow_response) {
                totalWithdrawText.setText(allWithdrow_response.getTotal_withdrow());

                withdrawList = new allWithdrow_response();
                withdrawList = allWithdrow_response;
                adapter = new Withdraw_history_adapter(withdrawList);
                withdrawView.setAdapter(adapter);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.withdraw_history_fragment, container, false);

        allWithdrow = new ViewModelProvider(this).get(AllWithdrow.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out// popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        totalWithdrawText = (TextView) view.findViewById(R.id.totalWithdrawTextID);

        withdrawView = (RecyclerView) view.findViewById(R.id.withdrawViewID);
        withdrawView.setHasFixedSize(true);
        withdrawView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}