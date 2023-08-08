package com.example.afinal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class fragmentS extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_s, container, false);

        ListView listView = rootView.findViewById(R.id.students);

        String json_stu = getListData();
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            JSONObject jsObj = new JSONObject(json_stu);
            JSONArray jsArray = jsObj.getJSONArray("Students");
            for (int i = 0; i < jsArray.length(); i++) {
                HashMap<String, String> stu = new HashMap<>();
                JSONObject obj = jsArray.getJSONObject(i);
                stu.put("name", obj.getString("name"));
                stu.put("Branch", obj.getString("Branch"));
                stu.put("institute", obj.getString("institute"));
                userList.add(stu);
            }
            ListAdapter simpleAdapter = new SimpleAdapter(requireContext(), userList, R.layout.list_item, new String[]{"name", "Branch", "institute"}, new int[]{R.id.StudentName, R.id.Branch, R.id.institute});
            listView.setAdapter(simpleAdapter);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return rootView;
    }

    private String getListData() {
        String json_stu1 = "{ \"Students\" :[" +
                "{\"name\":\"Akshita Shrivastava \",\"Branch\":\"Computer Science\",\"institute\":\"IIT I\"}" +
                ",{\"name\":\"Peter Potter\",\"Branch\":\"Civil\",\"institute\":\" IIT R\"}" +
                ",{\"name\":\"Md Farman \",\"Branch\":\"Information Technology\",\"institute\":\" Bits Pilani\"}" +
                ",{\"name\":\"Vipul Soni\",\"Branch\":\"Mechanical\",\"institute\":\" MIT\"}" +
                ",{\"name\":\"Shikha Jain\",\"Branch\":\"Textile\",\"institute\":\" IIIT\"}" +
                ",{\"name\":\"John Samuel\",\"Branch\":\"Electrical\",\"institute\":\" IIT B\"}" +
                ",{\"name\":\"Rhea Sharma\",\"Branch\":\"Mechanical\",\"institute\":\"NIT\"}] }";
        return json_stu1;
    }
}
