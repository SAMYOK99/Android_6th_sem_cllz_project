package com.example.afinal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Api extends Fragment {

    private TextView getResponseTextView;
    private TextView postResponseTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api, container, false);

        Button getDataButton = view.findViewById(R.id.get_data);
        Button postDataButton = view.findViewById(R.id.post_data);
        getResponseTextView = view.findViewById(R.id.get_response_data);
        postResponseTextView = view.findViewById(R.id.post_response_data);

        getDataButton.setOnClickListener(view1 -> sendGetRequest());

        postDataButton.setOnClickListener(view12 -> postRequest());

        return view;
    }

    private void postRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        String url = "http://192.168.10.69:8080/volly/post_data.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        post_response_text.setText("Data 1 : " + jsonObject.getString("data_1_post") + "\n");
//                        post_response_text.append("Data 2 : " + jsonObject.getString("data_2_post") + "\n");
//                        post_response_text.append("Data 3 : " + jsonObject.getString("data_3_post") + "\n");
//                        post_response_text.append("Data 4 : " + jsonObject.getString("data_4_post") + "\n");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        post_response_text.setText("POST DATA : unable to Parse Json");
//                    }
                }, error -> postResponseTextView.setText("Post Data : Response Failed"))
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("data_1_post", "Samyok");
                params.put("data_2_post", "Limbu");
                params.put("data_3_post", "BCA");
                params.put("data_4_post", "BMC");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void sendGetRequest() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = "http://192.168.10.69:8080/volly/get_data.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    getResponseTextView.setText(response);
                    // Handle GET response parsing here
                },
                error -> getResponseTextView.setText("Get Data : Response Failed"));

        queue.add(stringRequest);
    }
}
