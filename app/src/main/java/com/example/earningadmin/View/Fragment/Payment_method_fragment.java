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

import com.example.earningadmin.Adapter.Method_list_adapter;
import com.example.earningadmin.Model.Method.Method_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.MethodViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Payment_method_fragment extends Fragment implements Method_list_adapter.OnItemDelete {

    ImageView backButton;
    RecyclerView methodView;
    FloatingActionButton addMethodButton;
    MethodViewModel methodViewModel;
    Dialog loader;
    private List<Method_response> methodList;
    private Method_list_adapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main();

        addMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.method_add_alert);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();

                ImageView closeButton = dialog.findViewById(R.id.closeButtonID);
                TextInputEditText methodText = dialog.findViewById(R.id.methodTextID);
                TextInputLayout methodError = dialog.findViewById(R.id.methodErrorID);
                AppCompatButton addButton = dialog.findViewById(R.id.AddButtonID);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String method = methodText.getText().toString().trim();

                        methodError.setErrorEnabled(false);
                        if (TextUtils.isEmpty(method)) {
                            methodError.setError(" ");
                        } else {
                            loader.show();
                            methodViewModel.addMethod(method).observe(getViewLifecycleOwner(), new Observer<String>() {
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
    }

    private void main() {
        methodViewModel.getMethod().observe(getViewLifecycleOwner(), new Observer<List<Method_response>>() {
            @Override
            public void onChanged(List<Method_response> method_responses) {
                methodList = new ArrayList<>();
                methodList = method_responses;
                adapter = new Method_list_adapter(methodList);
                adapter.setOnDeleteListener(Payment_method_fragment.this::OnItemDelete);
                methodView.setAdapter(adapter);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_method_fragment, container, false);

        methodViewModel = new ViewModelProvider(this).get(MethodViewModel.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        methodView = (RecyclerView) view.findViewById(R.id.methodViewID);
        methodView.setHasFixedSize(true);
        methodView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addMethodButton = (FloatingActionButton) view.findViewById(R.id.addMethodButtonID);

        loader = new Dialog(getActivity());
        loader.setContentView(R.layout.loader_alert);
        loader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loader.setCancelable(false);

        return view;
    }

    @Override
    public void OnItemDelete(int position) {
        Method_response response = methodList.get(position);
        String methodID = response.getId();

        loader.show();
        methodViewModel.deleteMethod(methodID).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loader.dismiss();
                if (s.equals("1")) {

                    methodList.remove(position);
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