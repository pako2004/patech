package com.example.patech.adapters;

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

import com.bumptech.glide.Glide;
import com.example.patech.R;
import com.example.patech.activities.DetalleProductoActivity;
import com.example.patech.clases.Producto;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;
    private Context context;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del item (debe existir item_producto.xml en res/layout)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombreProducto.setText(producto.getNombre());
        @SuppressLint("DefaultLocale") String precioFormateado = String.format("%.2f", producto.getPrecio());
        holder.precioProducto.setText(precioFormateado);
        @SuppressLint("DefaultLocale") String valoracionFormateada = String.format("%.1f", producto.getValoracion());
        holder.valoracionProducto.setText(valoracionFormateada);

        // Cargar imagen desde Supabase con Glide
        Glide.with(holder.itemView.getContext())
                .load(producto.getImagenUrl()) // asegúrate de que la URL sea válida
                .into(holder.imagenProducto);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleProductoActivity.class);
            intent.putExtra("producto", producto); // Asegúrate que Producto implemente Serializable
            v.getContext().startActivity(intent);        });
        // Aquí puedes agregar más campos si tienes más atributos como precio, imagen, etc.
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProducto;
        TextView precioProducto;
        TextView valoracionProducto;
        TextView descripcionProducto;
        ImageView imagenProducto;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.tv_nombreProducto);
            precioProducto = itemView.findViewById(R.id.tv_precioProducto);
            valoracionProducto = itemView.findViewById(R.id.tv_valoracionProducto);
            imagenProducto = itemView.findViewById(R.id.iv_imagenProducto);

        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        notifyDataSetChanged(); // Actualizar vista si cambia la lista
    }

}
