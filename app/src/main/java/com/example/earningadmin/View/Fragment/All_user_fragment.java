package com.example.earningadmin.View.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.earningadmin.Adapter.All_users_adapter;
import com.example.earningadmin.Model.allUser_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AllUser;

import java.util.ArrayList;

public class All_user_fragment extends Fragment {

    TextView totalUsersText, totalUserBalanceText;
    AllUser allUser;
    ImageView backButton;
    RecyclerView allUsersView;
    private allUser_response userList;
    All_users_adapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main();
    }

    private void main() {
        allUser.getUser("abc**def").observe(getViewLifecycleOwner(), new Observer<allUser_response>() {
            @Override
            public void onChanged(allUser_response allUser_response) {
                totalUsersText.setText(allUser_response.getTotal_user());
                totalUserBalanceText.setText(allUser_response.getTotal_user_balance());

                userList = new allUser_response();
                userList = allUser_response;

                adapter = new All_users_adapter(userList);
                allUsersView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_user_fragment, container, false);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        allUser = new ViewModelProvider(this).get(AllUser.class);

        totalUsersText = (TextView) view.findViewById(R.id.totalUsersTextID);
        totalUserBalanceText = (TextView) view.findViewById(R.id.totalUserBalanceTextID);

        allUsersView = (RecyclerView) view.findViewById(R.id.allUsersViewID);
        allUsersView.setHasFixedSize(true);
        allUsersView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}