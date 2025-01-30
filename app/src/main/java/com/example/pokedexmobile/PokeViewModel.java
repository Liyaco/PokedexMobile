package com.example.pokedexmobile;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeViewModel extends ViewModel {
    private final MutableLiveData<List<Pokemon>> pokemonList = new MutableLiveData<>(new ArrayList<>());
    private final PokeapiService service;
    private int offset = 0;
    private static final int LIMIT = 20;
    private boolean isLoading = false;

    public PokeViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PokeapiService.class);
        fetchPokemon();
    }

    public LiveData<List<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public void fetchPokemon() {
        if (isLoading) return;
        isLoading = true;

        service.obtenerListaPokemon(LIMIT, offset).enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Clonar la lista actual
                    List<Pokemon> currentList = new ArrayList<>(pokemonList.getValue());
                    // Agregar nuevos datos
                    currentList.addAll(response.body().getResults());
                    // Actualizar LiveData
                    pokemonList.setValue(currentList);
                    // Incrementar el offset
                    offset += LIMIT;
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e("PokeViewModel", "Error al obtener datos", t);
                isLoading = false;
            }
        });
    }
}
