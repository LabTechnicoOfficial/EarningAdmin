package com.example.earningadmin.View.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earningadmin.Adapter.All_users_adapter;
import com.example.earningadmin.Model.allUser_response;
import com.example.earningadmin.Model.all_user_details_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.AllUser;

import java.util.ArrayList;
import java.util.List;

public class All_user_fragment extends Fragment {

    TextView totalUsersText, totalUserBalanceText;
    AllUser allUser;
    ImageView backButton;
    RecyclerView allUsersView;
    private List<allUser_response> userList;
    All_users_adapter adapter;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    int page=0,limit=10,end=0;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main();
       // user();
    }
private void main()
{
    allUser.getdetails("abc**def").observe(getViewLifecycleOwner(), new Observer<all_user_details_response>() {
        @Override
        public void onChanged(all_user_details_response all_user_details_response) {
           totalUsersText.setText(all_user_details_response.getTotal_user());
            totalUserBalanceText.setText(all_user_details_response.getTotal_user_balance());
            page=1;

           user(page,limit);
        }
    });
}
    private void user(int Page,int Limit) {
        progressBar.setVisibility(View.GONE);
        if(Page==1)
        {
            userList = new ArrayList<>();

            //Toast.makeText(getActivity(),String.valueOf(allUser_response.size()),Toast.LENGTH_SHORT).show();
            adapter = new All_users_adapter(userList);
            allUsersView.setAdapter(adapter);
        }
        allUser.getUser("abc**def",Page,Limit).observe(getViewLifecycleOwner(), new Observer<List<allUser_response>>() {
            @Override
            public void onChanged(List<allUser_response> allUser_response) {
                //totalUsersText.setText(allUser_response.getTotal_user());
                //totalUserBalanceText.setText(allUser_response.getTotal_user_balance());
                for (int i = 0; i < allUser_response.size(); i++) {
                   userList.add(allUser_response.get(i));
                }
                if (allUser_response.size() < Limit) {
                    end = 1;
                }

                //userList = new ArrayList<>();
               // userList = allUser_response;
               // Toast.makeText(getActivity(),String.valueOf(allUser_response.size()),Toast.LENGTH_SHORT).show();
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
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarID);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedRecyclerViewID);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                //mFloatingActionButton.show();
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    if (end == 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        page++;
                        user(page, limit);
                    }
                }
            }
        });
        return view;
    }
}