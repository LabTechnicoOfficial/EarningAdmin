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

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earningadmin.Model.ADDMOB.ADDMOB_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.ADDMOBViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ADD_Mob_fragment extends Fragment {

    ImageView backButton;
    TextView appText, unitText;
    TextInputEditText appEditText, unitEditText;
    TextInputLayout appError, unitError;
    AppCompatButton updateButton;
    Dialog loaderDialog;
    ADDMOBViewModel addmobViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();
    }

    private void main() {
        addmobViewModel.getADDMOB().observe(getViewLifecycleOwner(), new Observer<ADDMOB_response>() {
            @Override
            public void onChanged(ADDMOB_response addmob_response) {
                appText.setText(addmob_response.getAppId());
                unitText.setText(addmob_response.getUnitId());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add__mob_fragment, container, false);

        addmobViewModel = new ViewModelProvider(this).get(ADDMOBViewModel.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        appText = (TextView) view.findViewById(R.id.appTextID);
        unitText = (TextView) view.findViewById(R.id.unitTextID);

        appEditText = (TextInputEditText) view.findViewById(R.id.appEditTextID);
        unitEditText = (TextInputEditText) view.findViewById(R.id.unitEditTextID);

        appError = (TextInputLayout) view.findViewById(R.id.appErrorID);
        unitError = (TextInputLayout) view.findViewById(R.id.unitErrorID);

        updateButton = (AppCompatButton) view.findViewById(R.id.updateButtonID);

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appID = appEditText.getText().toString().trim();
                String unitID = unitEditText.getText().toString().trim();

                appError.setErrorEnabled(false);
                unitError.setErrorEnabled(false);
                if (TextUtils.isEmpty(appID) || TextUtils.isEmpty(unitID)) {
                    if (TextUtils.isEmpty(appID)) {
                        appError.setError(" ");
                    } else if (TextUtils.isEmpty(unitID)) {
                        unitError.setError(" ");
                    }
                } else {
                    update_data(appID, unitID);
                }
            }
        });

        return view;
    }

    private void update_data(String appID, String unitID) {

        loaderDialog.show();
        addmobViewModel.updateADDMOB(appID, unitID).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loaderDialog.dismiss();
                if(s.equals("1")){
                    Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                    main();

                }else {
                    Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}