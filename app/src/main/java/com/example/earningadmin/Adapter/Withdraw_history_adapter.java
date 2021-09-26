package com.example.earningadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.allWithdrow_response;
import com.example.earningadmin.R;

public class Withdraw_history_adapter extends RecyclerView.Adapter<Withdraw_history_adapter.AppViewHolder> {
    private allWithdrow_response withdrawList;

    public Withdraw_history_adapter(allWithdrow_response withdrawList) {
        this.withdrawList = withdrawList;
    }

    @NonNull
    @Override
    public Withdraw_history_adapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_withdraw_history, parent, false);
        return new Withdraw_history_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Withdraw_history_adapter.AppViewHolder holder, int position) {
        allWithdrow_response.history response = withdrawList.getHistory().get(position);

        holder.messageText.setText(response.getMessage());
    }

    @Override
    public int getItemCount() {
        return withdrawList.getHistory().size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageTextID);
        }
    }
}
