package com.example.patech.interfaces;

import com.example.patech.clases.Producto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SupabaseApi {

        @GET("productos")
        Call<List<Producto>> obtenerProductos(
                @Header("apikey") String apiKey,
                @Header("Authorization") String authorization
        );

        @GET("usuarios")
        Call<List<Producto>> obtenerUsuarios(
                @Header("apikey") String apiKey,
                @Header("Authorization") String authorization
        );

        @POST("usuarios")
        Call<Void> insertarUsuario(
                @Header("apikey") String apiKey,
                @Header("Authorization") String authorization,
                @Body Map<String, Object> usuario
        );


}
