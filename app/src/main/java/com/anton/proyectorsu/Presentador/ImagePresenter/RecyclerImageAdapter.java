package com.anton.proyectorsu.Presentador.ImagePresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anton.proyectorsu.Modelo.ImageModel;
import com.anton.proyectorsu.Vista.ImageView.NewImageActivity;
import com.anton.proyectorsu.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ImageModel> imageModelArrayList;

    public RecyclerImageAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(imageModelArrayList.get(position).getImageurl()).into(holder.imageView);

        holder.txt_nombre.setText(imageModelArrayList.get(position).getNombre());
        holder.txt_descripcion.setText(imageModelArrayList.get(position).getDescripcion());
        holder.txt_precio.setText(imageModelArrayList.get(position).getNumero());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewImageActivity.class);
                intent.putExtra("Image@#", imageModelArrayList.get(position).getImageurl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_nombre;
        TextView txt_descripcion;
        TextView txt_precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion);
            txt_precio = itemView.findViewById(R.id.txt_precio);
        }
    }
}
