package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Keypad extends Fragment {

    private ViewModelCalci viewModelCalci;

    private Integer flagError = 0;
    private EditText txtResult;
    Button btn ;
    Boolean equalFlag=false;
    public Keypad() {
    }

    public static Keypad newInstance() {
        return new Keypad();
    }

    public static Keypad newInstance(String param1, String param2)
    {
        Keypad fragment = new Keypad();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_keypad, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModelCalci = ViewModelProviders.of(requireActivity()).get(ViewModelCalci.class);
        txtResult = view.findViewById(R.id.txtKeypad);

        txtResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModelCalci.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void btnClicked(View v)
    {
        if(flagError==1)
        {
            txtResult.setText("");
            flagError = 0;
        }
        Button btn = (Button) v.findViewById(v.getId());
        txtResult = v.getRootView().findViewById(R.id.txtKeypad);
        String temp = btn.getText().toString();

            if (temp.equals("=")) {
                if(!(String.valueOf(txtResult.getText()).isEmpty())) {
                    if(MyUtil.isValidExpression(String.valueOf(txtResult.getText()))) {
                        Float tempResult = MyUtil.Evaluatestring(String.valueOf(txtResult.getText()));
                        txtResult.setText(String.valueOf(tempResult));
                        equalFlag = true;
                    }
                    else
                    {
                        txtResult.setText("Invalid Expression");
                        flagError = 1;
                    }
                }
            } else if (temp.equals("Del")) {
                if(!(String.valueOf(txtResult.getText()).isEmpty())) {
                    temp = String.valueOf(txtResult.getText()).substring(0, (String.valueOf(txtResult.getText()).length() - 1));
                    txtResult.setText(temp);
                }
            }
            else if(temp.equals("AC"))
            {
                txtResult.setText("");
            }
            else {
                temp = txtResult.getText() + temp;
                txtResult.setText(temp);
            }

    }

}