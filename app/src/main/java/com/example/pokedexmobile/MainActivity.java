package com.example.pokedexmobile;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokedexmobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListaPokemonAdapter listaPokemonAdapter;
    private PokeViewModel pokeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaPokemonAdapter = new ListaPokemonAdapter();
        binding.recyclerView.setAdapter(listaPokemonAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(layoutManager);

        pokeViewModel = new ViewModelProvider(this).get(PokeViewModel.class);
        pokeViewModel.getPokemonList().observe(this, pokemons -> {
            listaPokemonAdapter.añadirListaPokemon(pokemons);
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    pokeViewModel.fetchPokemon();
                }
            }
        });
    }
}