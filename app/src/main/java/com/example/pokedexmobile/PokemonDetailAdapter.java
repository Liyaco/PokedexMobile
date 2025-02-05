package com.example.pokedexmobile;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokedexmobile.databinding.ItemPokemonDetailBinding;
import java.util.ArrayList;
import java.util.List;

public class PokemonDetailAdapter extends RecyclerView.Adapter<PokemonDetailAdapter.ViewHolder> {
    private List<String> detalles = new ArrayList<>();

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPokemonDetailBinding binding = ItemPokemonDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tipoTextView.setText(detalles.get(position));
    }

    @Override
    public int getItemCount() {
        return detalles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemPokemonDetailBinding binding;

        public ViewHolder(@NonNull ItemPokemonDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
