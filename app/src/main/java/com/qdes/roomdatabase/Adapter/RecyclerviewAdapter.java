package com.qdes.roomdatabase.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qdes.roomdatabase.Database.RoomDB;
import com.qdes.roomdatabase.Model.UserData;
import com.qdes.roomdatabase.R;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    Context context;
    List<UserData> model;
    RoomDB database;

    public RecyclerviewAdapter(Context context, List<UserData> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserData list = model.get(position);

        database = RoomDB.getInstance(context);

        holder.id.setText("ID :" + list.getId());
        holder.name.setText("Name :" + list.getName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sID = list.getId();
                database.mainDao().update(sID, "Jose");
                model.clear();
                model.addAll(database.mainDao().getAll());
                notifyDataSetChanged();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDao().delete(list);
                model.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, model.size());
            }
        });


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        ImageView delete;
        ImageView edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
