package com.example.assignment2;


public class SpellModel {
    public String spell;
    public String use;

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "Spell: " + spell + "\nUse: " + use;
    }
}