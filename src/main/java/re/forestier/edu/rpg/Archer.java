package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Player {
    public Archer(String playerName, String avatarName, AvatarClass avatarClass, int money) {
        super(playerName, avatarName, avatarClass, money);
    }
    
    public static final Map<AvatarClass, Map<Integer, Map<String, Integer>>> ARCHER_ABILITIES = Map.of(
        AvatarClass.ARCHER, Map.of(
            1, Map.of("INT", 1, "ATK", 3, "CHA", 1, "VIS", 3),
            2, Map.of("DEF", 1, "CHA", 2),
            3, Map.of("ATK", 3),
            4, Map.of("DEF", 2),
            5, Map.of("ATK", 4)
    ));

    @Override
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {        // On surcharge la méthode pour l'adapter à l'aventurier et récupérer ses capacités
        return new HashMap<>(ARCHER_ABILITIES.get(avatarClass).get(level));
    }

    @Override
    public boolean updateHealth() {
        
        if (!super.updateHealth()) {              // On traite les cas généraux dans la classe mère
            return false;                         // Arrête immédiatement si le joueur est KO
        }
         
        currentHealthPoints += 1;
        if (inventory.stream().anyMatch(obj -> obj instanceof GameObject && ((GameObject) obj).getObjectName().equals("Magic Bow"))) {
            currentHealthPoints += currentHealthPoints / 8 - 1;
        }
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
        return true;
    }
}
