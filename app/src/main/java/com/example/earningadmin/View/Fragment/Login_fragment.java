package com.example.earningadmin.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.earningadmin.Model.Session_Management;
import com.example.earningadmin.Model.response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.Login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login_fragment extends Fragment {
    TextView registerButton;
    AppCompatButton signInButton;
    TextInputEditText phoneText, passwordText;
    TextInputLayout phoneError, passwordError;
    Session_Management session_management;
    Dialog loaderDialog;
    Login login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        login = new ViewModelProvider(this).get(Login.class);

        session_management = new Session_Management(getActivity());
        String phone = session_management.getPhone();

        if (!phone.equals("-1")) {
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                    R.anim.fade_in,  // enter
                    R.anim.fade_out // popExit
            ).replace(R.id.frame_container, new Main_fragment()).commit();
        }

        registerButton = (TextView) view.findViewById(R.id.registerButtonID);
        signInButton = (AppCompatButton) view.findViewById(R.id.signInButtonID);
        phoneText = (TextInputEditText) view.findViewById(R.id.phoneTextID);
        passwordText = (TextInputEditText) view.findViewById(R.id.passwordTextID);

        phoneError = (TextInputLayout) view.findViewById(R.id.phoneErrorID);
        passwordError = (TextInputLayout) view.findViewById(R.id.passwordErrorID);

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                phoneError.setErrorEnabled(false);
                passwordError.setErrorEnabled(false);

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    if (TextUtils.isEmpty(phone)) {
                        phoneError.setError(" ");
                    } else if (TextUtils.isEmpty(password)) {
                        passwordError.setError(" ");
                    }
                } else {
                    sendData(phone, password);
                }
            }
        });

        return view;
    }

    private void sendData(String phone, String password) {

        loaderDialog.show();
        login.login(phone, password).observe(getViewLifecycleOwner(), new Observer<response>() {
            @Override
            public void onChanged(response response) {
                String message = response.getMessage();
                loaderDialog.dismiss();
                if (message.equals("successfull")) {
                    session_management.saveSession(phone, password);
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                            R.anim.fade_in,  // enter
                            R.anim.fade_out // popExit
                    ).replace(R.id.frame_container, new Main_fragment()).commit();
                } else {
                    Toast.makeText(getActivity(), "Invalid phone/password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
