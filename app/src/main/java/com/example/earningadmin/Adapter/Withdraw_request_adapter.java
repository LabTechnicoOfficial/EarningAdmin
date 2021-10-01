package com.example.earningadmin.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.RequestApprove.Phone_response;
import com.example.earningadmin.Model.Session_Management;
import com.example.earningadmin.Model.pendingWithdrowRequest_response;
import com.example.earningadmin.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Withdraw_request_adapter extends RecyclerView.Adapter<Withdraw_request_adapter.AppViewHolder> {

    private pendingWithdrowRequest_response pendingList;

    public Withdraw_request_adapter(pendingWithdrowRequest_response pendingList) {
        this.pendingList = pendingList;
    }

    @NonNull
    @Override
    public Withdraw_request_adapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_pending_request, parent, false);
        return new Withdraw_request_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Withdraw_request_adapter.AppViewHolder holder, int position) {
        pendingWithdrowRequest_response.pendingRequest response = pendingList.getPending_list().get(position);
        holder.nameText.setText(response.getUser_name());
        holder.amountText.setText(response.getAmount());
        holder.phoneText.setText(response.getNumber());
        holder.requestedDateText.setText(response.getRequest_date());
        holder.methodText.setText(response.getMethod());
    }

    @Override
    public int getItemCount() {
        return pendingList.getPending_list().size();
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, amountText, requestedDateText, methodText;
        AppCompatButton approvedButton;
        TextInputEditText phoneEditText;
        TextInputLayout phoneError;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameTextID);
            phoneText = itemView.findViewById(R.id.phoneTextID);
            amountText = itemView.findViewById(R.id.amountTextID);
            methodText = itemView.findViewById(R.id.methodTextID);
            requestedDateText = itemView.findViewById(R.id.requestedDateTextID);
            approvedButton = itemView.findViewById(R.id.approvedButtonID);
            phoneEditText = itemView.findViewById(R.id.phoneEditTextID);
            phoneError = itemView.findViewById(R.id.phoneErrorID);

            approvedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Session_Management session_management = new Session_Management(itemView.getContext());
                    phoneError.setErrorEnabled(false);
                    if (TextUtils.isEmpty(phoneEditText.getText().toString().trim())) {
                        phoneError.setError(" ");
                    } else {
                        session_management.saveAcceptPhone(phoneEditText.getText().toString().trim());
                    }
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
