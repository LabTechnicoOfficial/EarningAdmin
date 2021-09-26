package com.example.earningadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.pendingWithdrowRequest_response;
import com.example.earningadmin.R;

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
    }

    @Override
    public int getItemCount() {
        return pendingList.getPending_list().size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, amountText, requestedDateText;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameTextID);
            phoneText = itemView.findViewById(R.id.phoneTextID);
            amountText = itemView.findViewById(R.id.amountTextID);
            requestedDateText = itemView.findViewById(R.id.requestedDateTextID);
        }
    }
}
