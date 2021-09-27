package com.example.earningadmin.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earningadmin.Model.Commission.Commission_response;
import com.example.earningadmin.Model.Session_Management;
import com.example.earningadmin.Model.balance_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.Balance;
import com.example.earningadmin.ViewModel.CommissionViewModel;
import com.google.android.material.navigation.NavigationView;

public class Main_fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout withdrawRequestButton, usersButton, withdrawHistoryButton, withdrawCommissionButton, videoCommissionButton;
    Session_Management session_management;
    TextView balanceText, withdrawCommissionText, videoCommissionText;
    Balance balance;
    CommissionViewModel commissionViewModel;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main();
    }

    private void main() {
        String phone = session_management.getPhone();
        String password = session_management.getPassword();

        balance.getBalance(phone, password).observe(getViewLifecycleOwner(), new Observer<balance_response>() {
            @Override
            public void onChanged(balance_response balance_response) {
                balanceText.setText(balance_response.getBalance());
            }
        });

        commissionViewModel.getCommission().observe(getViewLifecycleOwner(), new Observer<Commission_response>() {
            @Override
            public void onChanged(Commission_response commission_response) {
                withdrawCommissionText.setText(String.valueOf(commission_response.getComission()) + "%");
            }
        });

        commissionViewModel.getVideoCommission().observe(getViewLifecycleOwner(), new Observer<Commission_response>() {
            @Override
            public void onChanged(Commission_response commission_response) {
                videoCommissionText.setText(commission_response.getComission());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        commissionViewModel = new ViewModelProvider(this).get(CommissionViewModel.class);
        balance = new ViewModelProvider(this).get(Balance.class);

        balanceText = (TextView) view.findViewById(R.id.balanceTextID);

        session_management = new Session_Management(getActivity());

        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //headerView = (LinearLayout) view.findViewById(R.id.headerViewID);
        //View header = navigationView.inflateHeaderView(R.layout.header);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        //ActionBar
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        withdrawRequestButton = (LinearLayout) view.findViewById(R.id.withdrawRequestButtonID);
        usersButton = (LinearLayout) view.findViewById(R.id.usersButtonID);
        withdrawHistoryButton = (LinearLayout) view.findViewById(R.id.withdrawHistoryButtonID);
        withdrawCommissionButton = (LinearLayout) view.findViewById(R.id.withdrawCommissionButtonID);
        videoCommissionButton = (LinearLayout) view.findViewById(R.id.videoCommissionButtonID);

        withdrawCommissionText = (TextView) view.findViewById(R.id.withdrawCommissionTextID);
        videoCommissionText = (TextView) view.findViewById(R.id.videoCommissionTextID);

        withdrawHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out // popExit
                ).replace(R.id.frame_container, new Withdraw_history_fragment()).addToBackStack(null).commit();
            }
        });

        withdrawRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out  // popExit
                ).replace(R.id.frame_container, new Withdraw_request_fragment()).addToBackStack(null).commit();
            }
        });

        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out // popExit
                ).replace(R.id.frame_container, new All_user_fragment()).addToBackStack(null).commit();
            }
        });

        withdrawCommissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out // popExit
                ).replace(R.id.frame_container, new Withdraw_commission_update_fragment()).addToBackStack(null).commit();
            }
        });

        videoCommissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out // popExit
                ).replace(R.id.frame_container, new Video_commission_fragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.log_out) {
            session_management.removeSession();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}