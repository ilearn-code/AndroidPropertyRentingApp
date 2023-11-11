package com.example.myapplication.buttom_nav_fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myapplication.AccountFragment;
import com.example.myapplication.ContactFragment;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;

public class ProfileFragment extends Fragment {

    private RelativeLayout relativeLayout_contact,relativeLayout_account;
private Button LogoutButton;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        LogoutButton = view.findViewById(R.id.buttonLogout);
//
//LogoutButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        SharedPreferences.Editor editor = getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
//        editor.remove("token");
//        editor.apply();
//        Intent intent= new Intent(getActivity(), LoginActivity.class);
//        startActivity(intent);
//    }
//});
        relativeLayout_contact = view.findViewById(R.id.contact_rl);
        relativeLayout_account = view.findViewById(R.id.account_rl);
        relativeLayout_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform some action when the RelativeLayout is clicked
                // For example, start a new fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, new ContactFragment());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        relativeLayout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform some action when the RelativeLayout is clicked
                // For example, start a new fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, new AccountFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }







    }



