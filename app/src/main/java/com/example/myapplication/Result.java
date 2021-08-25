package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Result extends Fragment {

    public ViewModelCalci viewModelCalci;

    private TextView editTxtResult;

    public Result() {

    }

    public static Result newInstance() {
        return new Result();
    }

    public static Result newInstance(String param1, String param2) {
        Result fragment = new Result();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelCalci = ViewModelProviders.of(requireActivity()).get(ViewModelCalci.class);
        editTxtResult = view.findViewById(R.id.editTxtResult);
        viewModelCalci.getName().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editTxtResult.setText(s);
            }
        });

    }



/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModelCalci = ViewModelProviders.of(getActivity()).get(ViewModelCalci.class);
        viewModelCalci.getName().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                editTxtResult.setText(charSequence);
            }
        });
    }*/
}