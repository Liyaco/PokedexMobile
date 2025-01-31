package com.example.pokedexmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.pokedexmobile.databinding.FragmentPokemonDetailBinding;
import java.util.ArrayList;
import java.util.List;

public class PokemonDetailFragment extends Fragment {

    private FragmentPokemonDetailBinding binding;
    private PokemonDetailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new PokemonDetailAdapter();
        binding.recyclerViewDetalles.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewDetalles.setAdapter(adapter);

        if (getArguments() != null) {
            String name = getArguments().getString("pokemon_name");
            int number = getArguments().getInt("pokemon_number");
            binding.nombrePokemonDetalle.setText(name);

            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + number + ".png")
                    .centerCrop()
                    .into(binding.fotoPokemonDetalle);

            // Simulación de datos (habilidades y estadísticas)
            List<String> detalles = new ArrayList<>();
            detalles.add("Habilidad: Overgrow");
            detalles.add("Estadística: HP - 45");
            detalles.add("Estadística: Ataque - 49");
            detalles.add("Evolución: Ivysaur");
            adapter.setDetalles(detalles);
        }
    }
}