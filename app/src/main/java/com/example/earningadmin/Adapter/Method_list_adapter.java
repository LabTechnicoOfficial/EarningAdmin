package com.example.earningadmin.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.Method.Method_response;
import com.example.earningadmin.Model.Session_Management;
import com.example.earningadmin.R;

import java.util.List;

public class Method_list_adapter extends RecyclerView.Adapter<Method_list_adapter.AppViewHolder> {

    private List<Method_response> methodList;

    public Method_list_adapter(List<Method_response> methodList) {
        this.methodList = methodList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_method, parent, false);
        return new Method_list_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        Method_response response = methodList.get(position);
        holder.methodText.setText(response.getMethod());

    }

    @Override
    public int getItemCount() {
        return methodList.size();
    }

    private Method_list_adapter.OnItemDelete mListener;

    public interface OnItemDelete {
        void OnItemDelete(int position);
    }

    public void setOnDeleteListener(Method_list_adapter.OnItemDelete mListener) {
        this.mListener = mListener;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        TextView methodText;
        ImageView deleteButton;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            methodText = itemView.findViewById(R.id.methodTextID);
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
