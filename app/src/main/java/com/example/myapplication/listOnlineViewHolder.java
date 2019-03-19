package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class listOnlineViewHolder extends RecyclerView.ViewHolder {
   public TextView txtEmail;
    public listOnlineViewHolder(@NonNull View itemView) {
        super(itemView);
        txtEmail = (TextView)itemView.findViewById(R.id.email_text);

    }
}
