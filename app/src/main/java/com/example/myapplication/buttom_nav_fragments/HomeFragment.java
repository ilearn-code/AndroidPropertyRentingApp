package com.example.myapplication.buttom_nav_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.adapter;
import com.example.myapplication.adapter.adapter2;
import com.example.myapplication.adapter.adapter3;
import com.mikhaellopez.circularimageview.CircularImageView;


import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;

    // Array list for recycler view data source
    ArrayList<String> source;
    ArrayList<String> source2;
    ArrayList<String> source3;

    // Layout Managers
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerView.LayoutManager RecyclerViewLayoutManager2;
    RecyclerView.LayoutManager RecyclerViewLayoutManager3;

    // Adapter classes objects
    com.example.myapplication.adapter.adapter adapter;
    adapter2 adapter2;
    com.example.myapplication.adapter.adapter3 adapter3;

    // Linear Layout Managers
    LinearLayoutManager HorizontalLayout;
    LinearLayoutManager HorizontalLayout2;
    LinearLayoutManager HorizontalLayout3;

    View ChildView;
    int RecyclerViewItemPosition;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        CircularImageView userImage = view.findViewById(R.id.user_image);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDropDown(v);
            }
        });


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView3 = view.findViewById(R.id.recyclerview3);

        recyclerView.setPadding(40, 0, 140, 0);
        recyclerView2.setPadding(40, 0, 140, 0);
        recyclerView3.setPadding(40, 0, 140, 0);

        // Set Layout Managers on Recycler Views
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        RecyclerViewLayoutManager2 = new LinearLayoutManager(getContext());
        RecyclerViewLayoutManager3 = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        recyclerView2.setLayoutManager(RecyclerViewLayoutManager2);
        recyclerView3.setLayoutManager(RecyclerViewLayoutManager3);

        // Adding items to RecyclerView ArrayList
        AddItemsToRecyclerViewArrayList();
        AddItemsToRecyclerViewArrayList2();
        AddItemsToRecyclerViewArrayList3();

        // Creating Adapters
        adapter = new adapter(source);
        adapter2 = new adapter2(source2);
        adapter3 = new adapter3(source3);

        // Set Horizontal Layout Managers for Recycler Views
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayout3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView2.setLayoutManager(HorizontalLayout2);
        recyclerView3.setLayoutManager(HorizontalLayout3);

        // Set Adapters on Recycler Views
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);
        return view;
    }

    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("1");
        source.add("2");
        source.add("3");

    }
    public void AddItemsToRecyclerViewArrayList2()
    {
        // Adding items to ArrayList
        source2 = new ArrayList<>();

        source2.add("4");
        source2.add("5");
        source2.add("6");

    }
    public void AddItemsToRecyclerViewArrayList3()
    {
        // Adding items to ArrayList
        source3 = new ArrayList<>();

        source3.add("7");
        source3.add("8");
        source3.add("9");
    }
    public void showDropDown(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.profile_image_dropdown);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_1:
                        // Handle menu item 1 click
                        return true;
                    case R.id.menu_item_2:
                        // Handle menu item 2 click
                        return true;
                    case R.id.menu_item_3:
                        // Handle menu item 3 click
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }


}
