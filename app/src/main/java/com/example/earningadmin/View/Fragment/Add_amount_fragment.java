package com.example.earningadmin.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.earningadmin.Adapter.Amount_adapter;
import com.example.earningadmin.Model.AddAmount.AddAmount_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AddAmountViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Add_amount_fragment extends Fragment implements Amount_adapter.OnItemDelete{

    ImageView backButton;
    RecyclerView amountView;
    AddAmountViewModel addAmountViewModel;
    private List<AddAmount_response> amountList;
    private Amount_adapter adapter;
    FloatingActionButton addAmountButton;
    Dialog loader;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_amount_alert);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                ImageView closeButton = dialog.findViewById(R.id.closeButtonID);
                TextInputEditText amountText = dialog.findViewById(R.id.amountTextID);
                TextInputLayout amountError = dialog.findViewById(R.id.amountErrorID);
                AppCompatButton addButton = dialog.findViewById(R.id.AddButtonID);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String amount = amountText.getText().toString().trim();

                        amountError.setErrorEnabled(false);

                        if (TextUtils.isEmpty(amount)) {
                            amountError.setError(" ");
                        } else {
                            loader.show();
                            addAmountViewModel.addAmounts(amount).observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    loader.dismiss();
                                    if (s.equals("1")) {
                                        dialog.dismiss();
                                        main();
                                        Toast.makeText(getActivity(), "Method added", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        main();
    }

    private void main() {
        addAmountViewModel.getAmounts().observe(getViewLifecycleOwner(), new Observer<List<AddAmount_response>>() {
            @Override
            public void onChanged(List<AddAmount_response> addAmount_responses) {
                amountList = new ArrayList<>();
                amountList = addAmount_responses;
                adapter = new Amount_adapter(amountList);
                amountView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_amount_fragment, container, false);
        addAmountViewModel = new ViewModelProvider(this).get(AddAmountViewModel.class);
        addAmountButton = (FloatingActionButton) view.findViewById(R.id.addAmountButtonID);
        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        amountView = (RecyclerView) view.findViewById(R.id.amountViewID);
        amountView.setHasFixedSize(true);
        amountView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loader = new Dialog(getActivity());
        loader.setContentView(R.layout.loader_alert);
        loader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loader.setCancelable(false);

        return view;
    }

    @Override
    public void OnItemDelete(int position) {
        AddAmount_response response = amountList.get(position);
        String amountID = response.getId();

        loader.show();
        addAmountViewModel.deleteAmount(amountID).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loader.dismiss();
                if (s.equals("1")) {

                    amountList.remove(position);
                    //adapter = new Method_list_adapter(methodList);
                    adapter.notifyDataSetChanged();
                    // methodView.setAdapter(adapter);

                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}