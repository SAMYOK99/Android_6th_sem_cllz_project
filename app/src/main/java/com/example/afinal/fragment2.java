package com.example.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class fragment2 extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        Button button = view.findViewById(R.id.sendData2btn);
        button.setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.fragment2Data);
            String dataToPass = editText.getText().toString();

            Bundle result = new Bundle();
            result.putString("df2", dataToPass);
            getParentFragmentManager().setFragmentResult("dataFrom2", result);

            editText.setText("");
            replaceFragment(new fragment1());

        });

        getParentFragmentManager().setFragmentResultListener("dataFrom1", this, (requestKey, result) -> {
            String data = result.getString("df1");
            TextView textView = view.findViewById(R.id.dataFrom1);
            textView.setText(data);
        });

        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
