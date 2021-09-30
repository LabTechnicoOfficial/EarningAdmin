package com.example.earningadmin.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.earningadmin.Model.RequestApprove.Phone_response;
import com.example.earningadmin.Model.RequestApprove.Request_approve_response;
import com.example.earningadmin.Model.Session_Management;
import com.example.earningadmin.Model.allWithdrow_response;
import com.example.earningadmin.Model.pendingWithdrowRequest_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AllWithdrow;
import com.example.earningadmin.ViewModel.ApproveRequestViewModel;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Withdraw_request_fragment extends Fragment implements Withdraw_request_adapter.OnItemClickListener {

    RecyclerView withdrawView;
    ImageView backButton;
    MaterialButtonToggleGroup toggleGroupButton;
    AllWithdrow allWithdrow;
    TextView totalAmountText;
    private pendingWithdrowRequest_response pendingList;
    private Withdraw_request_adapter adapter;
    Session_Management session_management;
    ApproveRequestViewModel approveRequestViewModel;
    Dialog loaderDialog;

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
                adapter.setOnClickListener(Withdraw_request_fragment.this::OnItemClick);
                withdrawView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.withdraw_request_fragment, container, false);

        session_management = new Session_Management(getActivity());

        allWithdrow = new ViewModelProvider(this).get(AllWithdrow.class);
        approveRequestViewModel = new ViewModelProvider(this).get(ApproveRequestViewModel.class);

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

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

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

    @Override
    public void OnItemClick(int position) {
        pendingWithdrowRequest_response.pendingRequest response = pendingList.getPending_list().get(position);

        String requestID = response.getRequest_id();
        String userID = response.getUser_id();
        String userName = response.getUser_name();
        String w_amount = response.getAmount();
        String w_number = response.getNumber();
        String accept_number = session_management.getAcceptPhone();
        String req_date = response.getRequest_date();
        String accept_date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        //String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        loaderDialog.show();
        approveRequestViewModel.approveRequest(requestID, userID, userName, w_amount, w_number, accept_number, req_date, accept_date).observe(getViewLifecycleOwner(), new Observer<Request_approve_response>() {
            @Override
            public void onChanged(Request_approve_response request_approve_response) {
                loaderDialog.dismiss();
                if(request_approve_response.getMessage().equals("added successfully")){
                    //pendingList.getPending_list().remove(position);
                   // adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), request_approve_response.getMessage(), Toast.LENGTH_SHORT).show();
                    pending_list_func();
                }else if(request_approve_response.getMessage().equals("fail to add")){
                    Toast.makeText(getActivity(), request_approve_response.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}