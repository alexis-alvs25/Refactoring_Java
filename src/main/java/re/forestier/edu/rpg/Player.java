package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Player {
    private String playerName;
    private String avatarName;
    protected AvatarClass avatarClass;
    private int money;
    protected int xp;
    private int level;
    protected int healthPoints;
    protected int currentHealthPoints;
    protected HashMap<String, Integer> abilities;
    protected ArrayList<GameObject> inventory;
    private int maxWeight;
    private int currentWeight;


    public Player(String playerName, String avatarName, AvatarClass avatarClass, int money) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.level = 1;
        this.healthPoints = 100;
        this.currentHealthPoints = 40;
        this.inventory = new ArrayList<>();
        this.maxWeight = 40;
        this.currentWeight = 0;
        this.abilities = getAbilitiesByLevel(avatarClass, 1);
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
        return this.healthPoints;
    }

    public int getCurrentHealthPoints() {
        return this.currentHealthPoints;
    }

    public ArrayList<GameObject> getInventory() {
        return this.inventory;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    // ------------------- Setters -------------------

    public void setHealthPoints (int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public void setInventory(ArrayList<GameObject> inventory) {
        this.inventory = inventory;
    }

    // ------------------- Methods -------------------

    public void addObjectToInventory(GameObject object) {
        if (object.getWeight() < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (currentWeight + object.getWeight() > maxWeight) {
            throw new IllegalArgumentException("Adding this object would exceed the maximum weight !");
        }
        this.inventory.add(object);
        this.currentWeight += object.getWeight();
    }

    public void sellObject(GameObject object) {
        if (!hasObject(object)) {
            throw new IllegalArgumentException("This object is not in the inventory !");
        }
        this.inventory.remove(object);
        this.money += object.getValue();
        this.currentWeight -= object.getWeight();
    }

    public boolean hasObject(GameObject object) {
        return inventory.contains(object); // Utilise equals() de GameObject
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

    protected abstract HashMap<String, Integer> getAbilitiesByLevel(AvatarClass adventurer, int level);

    public int retrieveLevel() {
        HashMap<Integer, Integer> levels = new HashMap<>();
        int maximumLevel = 5;                  // Niveau maximum choisi arbitrairement
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

    public boolean updateHealth() {
        boolean nextCase = true;

        if (currentHealthPoints == 0) {
            System.out.println("Le joueur est KO !");
            nextCase = false;
        }
        
        if (isHealthBetweenHalfAndFull()) {
            nextCase = false;
        }

        if (currentHealthPoints >= healthPoints) {
            currentHealthPoints = healthPoints;
        }
        return nextCase;
    }

    private boolean isHealthBetweenHalfAndFull() {
        return (currentHealthPoints >= healthPoints / 2) && currentHealthPoints < healthPoints;
    }

    public boolean addXp(int xp) {
        if (xp < 0) {
            throw new IllegalArgumentException("XP cannot be negative");
        }
        
        boolean leveledUp = false;
        this.xp += xp;
        int newLevel = retrieveLevel();

        if (newLevel > this.level) {                                                                           // Player leveled-up!
            this.level = newLevel;
            addObjectToInventory(GameObject.giveRandomObject());
            getNewAbilities();
            leveledUp = true;
        }
        return leveledUp;
    }

    private void getNewAbilities() {
        // Add/upgrade abilities to player
        HashMap<String, Integer> newAbilities = new HashMap<>(this.abilities);

        getAbilitiesByLevel(this.avatarClass, this.level).forEach((ability, level) -> {
            newAbilities.put(ability, level);
        });
        this.abilities = newAbilities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Joueur ").append(avatarName).append(" joué par ").append(playerName)
          .append("\nNiveau : ").append(retrieveLevel()).append(" (XP totale : ").append(xp).append(")")
          .append("\n\nCapacités :");
    
        List<String> abilityOrder = List.of("DEF", "ATK", "CHA", "INT", "ALC", "VIS");
    
        abilityOrder.stream()
            .filter(abilities::containsKey) // Ne garde que les capacités présentes
            .forEach(name -> sb.append("\n   ").append(name).append(" : ").append(abilities.get(name)));
    
        sb.append("\n\nInventaire :");
        inventory.stream().forEach(item -> sb.append("\n   ").append(item));
    
        return sb.toString();
    }

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();

        sb.append("## Joueur ").append(avatarName).append(" joué par ").append(playerName)
          .append("\n### Niveau : ").append(retrieveLevel()).append(" (XP totale : ").append(xp).append(")")
          .append("\n#### Capacités :");
    
        List<String> abilityOrder = List.of("DEF", "ATK", "CHA", "INT", "ALC", "VIS");
    
        abilityOrder.stream()
            .filter(abilities::containsKey) // Ne garde que les capacités présentes
            .forEach(name -> sb.append("\n- `").append(name).append("` : ").append(abilities.get(name)));
    
        sb.append("\n#### Inventaire :");
        inventory.stream().forEach(item -> sb.append("\n- **").append(item.getObjectName()).append("** : ").append("*").append(item.getDescription()).append(" (Weight: ").append(item.getWeight()).append(", Value: ").append(item.getValue()).append(")*"));
    
        return sb.toString();
    }
}