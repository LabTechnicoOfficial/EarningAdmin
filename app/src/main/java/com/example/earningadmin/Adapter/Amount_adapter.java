package com.example.earningadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.AddAmount.AddAmount_response;
import com.example.earningadmin.R;

import java.util.List;

public class Amount_adapter extends RecyclerView.Adapter<Amount_adapter.AppViewHolder> {

    private List<AddAmount_response> amountList;

    public Amount_adapter(List<AddAmount_response> amountList) {
        this.amountList = amountList;
    }

    @NonNull
    @Override
    public Amount_adapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_amount, parent, false);
        return new Amount_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Amount_adapter.AppViewHolder holder, int position) {
        AddAmount_response response = amountList.get(position);
        holder.amountText.setText(response.getAmount());
    }

    @Override
    public int getItemCount() {
        return amountList.size();
    }

    private Amount_adapter.OnItemDelete mListener;

    public interface OnItemDelete {
        void OnItemDelete(int position);
    }

    public void setOnDeleteListener(Amount_adapter.OnItemDelete mListener) {
        this.mListener = mListener;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView amountText;
        ImageView deleteButton;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            amountText = itemView.findViewById(R.id.amountTextID);
            deleteButton = itemView.findViewById(R.id.deleteButtonID);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.OnItemDelete(position);
                        }
                    }
                }
            });
        }
    }
}
