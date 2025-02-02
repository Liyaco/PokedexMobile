package com.example.pokedexmobile;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pokedexmobile.databinding.FragmentMainActivityBinding;

public class MainActivityFragment extends Fragment {

    private FragmentMainActivityBinding binding;
    private ListaPokemonAdapter listaPokemonAdapter;
    private PokeViewModel pokeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainActivityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaPokemonAdapter = new ListaPokemonAdapter();
        binding.recyclerView.setAdapter(listaPokemonAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(binding.mainActivityFragment.getContext(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);

        pokeViewModel = new ViewModelProvider(this).get(PokeViewModel.class);
        pokeViewModel.getPokemonList().observe(getViewLifecycleOwner(), pokemons -> {
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