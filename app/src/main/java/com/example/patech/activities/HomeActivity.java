package com.example.patech.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patech.R;
import com.example.patech.adapters.ProductoAdapter;
import com.example.patech.clases.ApiClient;
import com.example.patech.clases.Producto;
import com.example.patech.interfaces.SupabaseApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final Logger log = LoggerFactory.getLogger(HomeActivity.class);
    SupabaseApi api = ApiClient.getClient().create(SupabaseApi.class);
    ProductoAdapter productoAdapter;
    RecyclerView recyclerView;
    String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Zmpod3VpemR3bHV0b3dtZWd0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwNzQzMzksImV4cCI6MjA2NDY1MDMzOX0.rNKUsLFM4cz0cbAvzPXDdJECrLHcQA48fU78jGLYaMM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Log.d("HomeActivity", "onCreate: Inicio de HomeActivity");

        inicializarVista();
        obtenerDatosDesdeApi();
    }
    private void inicializarVista() {
        recyclerView = findViewById(R.id.recyclerRecomendaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productoAdapter = new ProductoAdapter(this,new ArrayList<>());
        recyclerView.setAdapter(productoAdapter);

    }


    private void obtenerDatosDesdeApi() {
        Call<List<Producto>> call = api.obtenerProductos(apiKey, "Bearer " + apiKey);
        System.out.println("Llamada a la API iniciada");

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                System.out.println("Respuesta de la API recibida: Código " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    List<Producto> lista = response.body();
                    System.out.println("Datos obtenidos: " + lista.size() + " productos");
                    productoAdapter = new ProductoAdapter(lista);
                    recyclerView.setAdapter(productoAdapter);
                } else {
                    System.out.println("Supabase"+ "Respuesta fallida: Código " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                System.out.println("Supabase"+ "Error al conectar: " + t.getMessage());
            }
        });
    }



}
