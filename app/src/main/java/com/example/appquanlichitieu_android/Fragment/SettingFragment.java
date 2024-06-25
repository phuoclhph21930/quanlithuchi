package com.example.appquanlichitieu_android.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.appquanlichitieu_android.Actiity_Khac.Catalog_Yeah_Activity;
import com.example.appquanlichitieu_android.Actiity_Khac.DM_KiActivity;
import com.example.appquanlichitieu_android.Actiity_Khac.KiActivity;
import com.example.appquanlichitieu_android.Actiity_Khac.SearchActivity;
import com.example.appquanlichitieu_android.Actiity_Khac.YeahActivity;
import com.example.appquanlichitieu_android.R;

public class SettingFragment extends Fragment {

    private LinearLayout reportDM_ki,reportYeah,reportCatalog,reportKi,reportSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        reportDM_ki =view.findViewById(R.id.report_DM_ki);
        reportCatalog = view.findViewById(R.id.reportCatalog);
        reportYeah = view.findViewById(R.id.reportYeah);
        reportKi = view.findViewById(R.id.reportKi);
        reportSearch  =view.findViewById(R.id.report_Search);
        reportCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Catalog_Yeah_Activity.class);
                startActivity(intent);
            }
        });
        reportYeah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), YeahActivity.class);
                startActivity(intent);
            }
        });
        reportKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KiActivity.class);
                startActivity(intent);


            }
        });
        reportDM_ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DM_KiActivity.class);
                startActivity(intent);
            }
        });
        reportSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}