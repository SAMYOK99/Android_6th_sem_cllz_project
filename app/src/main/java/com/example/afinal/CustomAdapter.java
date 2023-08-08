package com.example.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Activity activity;
    private Context context;
    private ArrayList<String> user_id, username, password;

    CustomAdapter(Activity activity, Context context, ArrayList<String> user_id, ArrayList<String> username, ArrayList<String> password) {
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final int currentPosition = holder.getAdapterPosition();
        holder.user_id_txt.setText(String.valueOf(user_id.get(currentPosition)));
        holder.username_txt.setText(String.valueOf(username.get(currentPosition)));
        holder.password_txt.setText(String.valueOf(password.get(currentPosition)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", user_id.get(currentPosition));
                intent.putExtra("username", username.get(currentPosition));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_id_txt, username_txt, password_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            username_txt = itemView.findViewById(R.id.username_txt);
            password_txt = itemView.findViewById(R.id.password_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
