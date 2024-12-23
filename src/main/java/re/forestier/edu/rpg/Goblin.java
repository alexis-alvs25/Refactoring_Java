package re.forestier.edu.rpg;


import java.util.HashMap;
import java.util.Map;

public class Goblin extends Player {
    public Goblin(String playerName, String avatarName, AvatarClass avatarClass, int money) {
        super(playerName, avatarName, avatarClass, money);
    }

    public static final Map<AvatarClass, Map<Integer, Map<String, Integer>>> ADVENTURER_ABILITIES = Map.of(
        AvatarClass.ADVENTURER, Map.of(
            1, Map.of("INT", 2, "ATK", 2, "ALC", 1),
            2, Map.of("ATK", 3, "ALC", 4),
            3, Map.of("VIS", 1),
            4, Map.of("DEF", 1),
            5, Map.of("DEF", 2, "ATK", 4)
    ));

    @Override
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass adventurer, int level) {        // On surcharge la méthode pour l'adapter à l'aventurier et récupérer ses capacités
        return new HashMap<>(ADVENTURER_ABILITIES.get(adventurer).get(level));
    }

    @Override
    public boolean updateHealth() {
        
        if (!super.updateHealth()) {           // On traite les cas généraux dans la classe mère
            return false;                         // Arrête immédiatement si le joueur est KO
        }

        // Personnalisation de la méthode updateHealth pour le gobelin
        currentHealthPoints += 1;
        if (inventory.stream().anyMatch(obj -> obj instanceof GameObject && ((GameObject) obj).getObjectName().equals("Magic Bow"))) {
            currentHealthPoints -= 3;
        }
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
        return true;
    }
}
