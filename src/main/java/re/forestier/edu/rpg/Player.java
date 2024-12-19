package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String playerName;
    private String avatarName;
    private AvatarClass avatarClass;
    private int money;
    protected int xp;
    private int level;
    private int healthpoints;
    private int currenthealthpoints;
    protected HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public Player(String playerName, String avatarName, AvatarClass avatarClass, int money, ArrayList<String> inventory) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.level = 1;
        this.healthpoints = 100;
        this.currenthealthpoints = 100;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(avatarClass.name()).get(1);
    }

    // ------------------- Getters -------------------

    public String getPlayerName() {
        return this.playerName;
    }

    public String getAvatarName() {
        return this.avatarName;
    }

    public AvatarClass getAvatarClass () {
        return this.avatarClass;
    }

    public int getMoney() {
        return this.money;
    }

    public int getXp() {
        return this.xp;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealthPoints() {
        return this.healthpoints;
    }

    public int getCurrentHealthPoints() {
        return this.currenthealthpoints;
    }

    public ArrayList<String> getInventory() {
        return this.inventory;
    }

    // ------------------- Setters -------------------

    public void setHealthPoints (int healthpoints) {
        this.healthpoints = healthpoints;
    }

    public void setCurrentHealthPoints(int currenthealthpoints) {
        this.currenthealthpoints = currenthealthpoints;
    }

    // ------------------- Methods -------------------

    public void addInventory(String object) {
        this.inventory.add(object);
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