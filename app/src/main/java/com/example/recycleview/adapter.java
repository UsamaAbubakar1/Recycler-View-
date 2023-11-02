package com.example.recycleview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewHolder> {
    Context context;
    ArrayList<infoModel>arrInfo;
    adapter (Context context, ArrayList<infoModel> arrInfo){
        this.context = context;
        this.arrInfo = arrInfo;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.single_row, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.img.setImageResource(arrInfo.get(position).img);
        holder.address.setText(arrInfo.get(position).address);
        holder.contact.setText(arrInfo.get(position).number);
        holder.name.setText(arrInfo.get(position).name);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update);

                EditText editName = dialog.findViewById(R.id.edtName);
                EditText editPhone = dialog.findViewById(R.id.edtNumber);
                EditText editAddress = dialog.findViewById(R.id.edtAddress);
                Button addBtn = dialog.findViewById(R.id.btnAction);
                TextView txtTitle = dialog.findViewById(R.id.title);

                txtTitle.setText("Update Contact");

                addBtn.setText("Update");

                editName.setText(arrInfo.get(holder.getAdapterPosition()).name);
                editPhone.setText(arrInfo.get(holder.getAdapterPosition()).number);
                editAddress.setText(arrInfo.get(holder.getAdapterPosition()).address);

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editName.getText().toString();
                        String number = editPhone.getText().toString();
                        String address = editAddress.getText().toString();

                        if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
                            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            arrInfo.set(holder.getAdapterPosition(), new infoModel(name, number, address));
                            notifyItemChanged(holder.getAdapterPosition());
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete?")
                        .setIcon(R.drawable.deleteicon)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrInfo.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrInfo.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name, contact, address;
        ImageView img;
        LinearLayout llRow;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            contact = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            img = itemView.findViewById(R.id.image);
            llRow = itemView.findViewById(R.id.llRow);

        }
    }
}
