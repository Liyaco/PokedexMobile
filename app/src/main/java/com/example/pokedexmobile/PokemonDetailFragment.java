package com.example.pokedexmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.pokedexmobile.databinding.FragmentPokemonDetailBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetailFragment extends Fragment {

    private String[] type;
    private HashMap stats;
    private String[] abilities;
    private FragmentPokemonDetailBinding binding;
    private PokemonDetailAdapter adapter;
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stats = new HashMap();

        adapter = new PokemonDetailAdapter();
        binding.recyclerViewDetalles.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewDetalles.setAdapter(adapter);

        if (getArguments() != null) {
            String name = getArguments().getString("pokemon_name");
            int number = getArguments().getInt("pokemon_number");
            binding.nombrePokemonDetalle.setText(name);

            assert name != null;
            Glide.with(this)
                    .load("https://img.pokemondb.net/sprites/black-white/normal/" + name.toLowerCase() + ".png")
                    .centerCrop()
                    .into(binding.fotoPokemonDetalle);

            obtenerDetallesPokemon(number);
        }
    }

    private void obtenerDetallesPokemon(int number) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonDetailResponse> call = service.obtenerPorIdPokemon(number);

        call.enqueue(new Callback<PokemonDetailResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailResponse> call, Response<PokemonDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonDetailResponse pokemon = response.body();
                    List<String> detalles = new ArrayList<>();
                    for (PokemonDetailResponse.PokemonType type : pokemon.getTypes()) {
                        detalles.add("Tipo: " + type.getType().getName());
                    }
                    for (PokemonDetailResponse.PokemonAbility habilidad : pokemon.getAbilities()) {
                        if (habilidad.isIs_hidden()) {
                            detalles.add("Habilidad Oculta: " + habilidad.getAbility().getName());
                        } else {
                            detalles.add("Habilidad: " + habilidad.getAbility().getName());
                        }
                    }
                    for (PokemonDetailResponse.PokemonStat stat : pokemon.getStats()) {
                        detalles.add(stat.getStat().getName() + " = " + stat.getBase_stat() + " - " + stat.getEffort());
                    }
                    adapter.setDetalles(detalles);
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailResponse> call, Throwable t) {
                Log.e("PokemonDetail", "Error al obtener datos", t);
            }
        });
    }
}