package com.example.pokedexmobile;

import androidx.annotation.NonNull;

import java.util.List;

public class PokemonDetailResponse {
    private int id;
    private String name;
    private List<PokemonType> types;
    private List<PokemonAbility> abilities;
    private List<PokemonStat> stats;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    public static class PokemonType {
        private int slot;
        private NamedApiResource type;

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public NamedApiResource getType() {
            return type;
        }

        public void setType(NamedApiResource type) {
            this.type = type;
        }
    }

    public static class PokemonAbility {
        private NamedApiResource ability;
        private boolean is_hidden;
        private int slot;

        public NamedApiResource getAbility() {
            return ability;
        }

        public void setAbility(NamedApiResource ability) {
            this.ability = ability;
        }

        public boolean isIs_hidden() {
            return is_hidden;
        }

        public void setIs_hidden(boolean is_hidden) {
            this.is_hidden = is_hidden;
        }

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }
    }

    public static class PokemonStat {
        private int base_stat;
        private int effort;
        private NamedApiResource stat;

        public int getBase_stat() {
            return base_stat;
        }

        public void setBase_stat(int base_stat) {
            this.base_stat = base_stat;
        }

        public int getEffort() {
            return effort;
        }

        public void setEffort(int effort) {
            this.effort = effort;
        }

        public NamedApiResource getStat() {
            return stat;
        }

        public void setStat(NamedApiResource stat) {
            this.stat = stat;
        }
    }

    public static class NamedApiResource {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
