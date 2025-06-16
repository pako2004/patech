package com.example.patech.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.patech.R;
import com.example.patech.clases.Producto;

public class DetalleProductoActivity extends AppCompatActivity {

    private ImageView imagenProducto;
    private TextView nombreProducto, precioProducto, descripcionProducto, valoracionProducto;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_producto);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        precioProducto = findViewById(R.id.precioProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        valoracionProducto = findViewById(R.id.valoracionProducto);
        btnAgregar = findViewById(R.id.btnAgregarAlCarrito);

        Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        if (producto != null) {
            nombreProducto.setText(producto.getNombre());
            precioProducto.setText(String.format("Precio: %.2f", producto.getPrecio()));
            descripcionProducto.setText(producto.getDescripcion());
            valoracionProducto.setText(String.format("Valoración: %.1f", producto.getValoracion()));
            Glide.with(this)
                    .load(producto.getImagenUrl())// opcional: imagen si falla
                    .into(imagenProducto);

        }


        btnAgregar.setOnClickListener(v -> {
            // En el próximo paso: lógica para agregar al carrito
            Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
        });


    }


}
