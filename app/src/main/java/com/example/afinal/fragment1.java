package com.example.afinal;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class fragment1 extends Fragment {
    View view;
    private RecyclerView recyclerView;
    DBHelper myDB;
    ArrayList<String> user_id, username, password;
    CustomAdapter customAdapter;
    private ImageView empty_imageview;
    private TextView no_data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        empty_imageview = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);
        myDB = new DBHelper(requireContext());
        user_id = new ArrayList<>();
        username = new ArrayList<>();
        password = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), requireContext(), user_id, username, password);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Button sendDataButton = view.findViewById(R.id.sendData1btn);
        Button contextMenuButton = view.findViewById(R.id.contextmenu);

        getParentFragmentManager().setFragmentResultListener("dataFrom2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("df2");
                TextView textView = view.findViewById(R.id.dataFrom2);
                textView.setText(data);
            }
        });

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.fragment1Data);
                String dataToSend = editText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("df1", dataToSend);

                getParentFragmentManager().setFragmentResult("dataFrom1", bundle);

                editText.setText("");
                replaceFragment(new fragment2());
            }
        });

        registerForContextMenu(contextMenuButton);

        return view;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                username.add(cursor.getString(1));
                password.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu); // Create a context menu XML file (context_menu.xml) in your 'res/menu' folder.
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_settings) {
            Toast settingsToast = Toast.makeText(requireContext(), "Settings is clicked", Toast.LENGTH_LONG);
            settingsToast.show();
            return true;
        } else if (itemId == R.id.action_share) {

            Toast shareToast = Toast.makeText(requireContext(), "Share is clicked", Toast.LENGTH_LONG);
            shareToast.show();
        }
        return super.onContextItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
