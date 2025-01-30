package com.example.pokedexmobile;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexmobile.databinding.ItemPokemonBinding;
import java.util.ArrayList;
import java.util.List;

public class ListaPokemonAdapter extends ListAdapter<Pokemon, ListaPokemonAdapter.ViewHolder> {
    private final List<Pokemon> dataset = new ArrayList<>();

    public ListaPokemonAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getNumber() == newItem.getNumber();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    public void añadirListaPokemon(List<Pokemon> listaPokemon) {
        // Evitar duplicados
        for (Pokemon pokemon : listaPokemon) {
            if (!dataset.contains(pokemon)) {
                dataset.add(pokemon);
            }
        }
        submitList(new ArrayList<>(dataset));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPokemonBinding binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        holder.binding.nombreTextView.setText(pokemon.getName());
        Glide.with(holder.binding.getRoot().getContext())
                .load("https://img.pokemondb.net/sprites/black-white/normal/" + pokemon.getName().toLowerCase() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.fotoImagen);
    }

    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final ItemPokemonBinding binding;

        public ViewHolder(@NonNull ItemPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
