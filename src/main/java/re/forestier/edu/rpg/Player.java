package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public String playerName;
    public String avatarName;
    private String avatarClass;
    public int money;
    protected int xp;
    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public Player(String playerName, String avatarName, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.level = 1;
        this.healthpoints = 100;
        this.currenthealthpoints = 100;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(avatarClass).get(1);
    }

    public String getAvatarClass () {
        return this.avatarClass;
    }

    public int getXp() {
        return this.xp;
    }

    public void removeMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }

    public void addMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        money += amount;
    }

    public int retrieveLevel() {
        HashMap<Integer, Integer> levels = new HashMap<>();
        int maximumLevel = 10;                  // Niveau maximum choisi arbitrairement
        levels.put(1, 0);
        
        for (int lvl = 2; lvl <= maximumLevel; lvl++) {
            int xpRequired = ((lvl - 1) * 10 + (lvl * levels.get(lvl - 1)) / 4);        // Formule pour calculer l'xp nécessaire pour passer au niveau suivant
            levels.put(lvl, xpRequired);        // On ajoute l'xp nécessaire pour passer au niveau suivant dans la map
            if (this.xp < levels.get(lvl)) {    // Si le joueur n'a pas assez d'xp pour passer au niveau suivant, on retourne le niveau actuel
                return lvl - 1;
            }
        }
        return maximumLevel;
    }
}