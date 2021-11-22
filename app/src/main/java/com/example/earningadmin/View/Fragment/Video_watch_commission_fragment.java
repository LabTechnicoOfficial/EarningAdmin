package com.example.earningadmin.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.CommissionViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Video_watch_commission_fragment extends Fragment {

    String current_rate;
    ImageView backButton;
    TextView currentRateText, rateText;
    TextInputLayout rateError;
    AppCompatButton updateButton;
    Dialog loaderDialog;
    CommissionViewModel commissionViewModel;

    public Video_watch_commission_fragment(String current_rate) {
        this.current_rate = current_rate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.video_watch_commission_fragment, container, false);

        commissionViewModel = new ViewModelProvider(this).get(CommissionViewModel.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        rateText = (TextInputEditText) view.findViewById(R.id.rateTextID);
        rateError = (TextInputLayout) view.findViewById(R.id.rateErrorID);
        updateButton = (AppCompatButton) view.findViewById(R.id.updateButtonID);
        currentRateText = (TextView) view.findViewById(R.id.currentRateID);
        currentRateText.setText(current_rate);

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rate = rateText.getText().toString().trim();

                rateError.setErrorEnabled(false);
                if (TextUtils.isEmpty(rate)) {
                    rateError.setError(" ");
                } else {
                    update_Watch_commission(rate);
                }
            }
        });

        return view;
    }

    private void update_Watch_commission(String rate) {
        //Toast.makeText(getActivity(), rate, Toast.LENGTH_SHORT).show();
        loaderDialog.show();
        commissionViewModel.updateVideoWatchCommission(rate).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loaderDialog.dismiss();
                if (s.equals("1")) {
                    Toast.makeText(getActivity(), "Commission rate updated", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out // popExit
                    ).replace(R.id.frame_container, new Main_fragment()).commit();
                }else {
                    Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}