package com.example.earningadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.allUser_response;
import com.example.earningadmin.R;

import java.util.List;

public class All_users_adapter extends RecyclerView.Adapter<All_users_adapter.AppViewHolder> {

    private List<allUser_response> userList;

    public All_users_adapter(List<allUser_response> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_all_users, parent, false);
        return new All_users_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        allUser_response response = userList.get(position);
        holder.nameText.setText(response.getUser_name());
        holder.phoneText.setText(response.getUser_phone());
        holder.mailText.setText(response.getUser_phone());
        holder.balanceText.setText(response.getUser_balance());
       /* if(response.getUser_status().equals("1")){
            holder.statusText.setText("Active");
        }else{
            holder.statusText.setText("Inactive");
        }*/
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, statusText, mailText, balanceText;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameTextID);
            phoneText = itemView.findViewById(R.id.phoneTextID);
            mailText = itemView.findViewById(R.id.mailTextID);
           // statusText = itemView.findViewById(R.id.statusTextID);
            balanceText = itemView.findViewById(R.id.balanceTextID);
        }
    }
}
