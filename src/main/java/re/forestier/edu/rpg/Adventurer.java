package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Adventurer extends Player {
    public Adventurer(String playerName, String avatarName, AvatarClass avatarClass, int money) {
        super(playerName, avatarName, avatarClass, money);
    }
    
    public static final Map<AvatarClass, Map<Integer, Map<String, Integer>>> ADVENTURER_ABILITIES = Map.of(
        AvatarClass.ADVENTURER, Map.of(
            1, Map.of("INT", 1, "DEF", 1, "ATK", 3, "CHA", 2),
            2, Map.of("INT", 2, "CHA", 3),
            3, Map.of("ATK", 5, "ALC", 1),
            4, Map.of("DEF", 3),
            5, Map.of("VIS", 1, "DEF", 4)
    ));

    @Override
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {        // On surcharge la méthode pour l'adapter à l'aventurier et récupérer ses capacités
        return new HashMap<>(ADVENTURER_ABILITIES.get(avatarClass).get(level));
    }

    @Override
    public boolean updateHealth() {
        
        if (!super.updateHealth()) {              // On traite les cas généraux dans la classe mère
            return false;                         // Arrête immédiatement si le joueur est KO
        }

        currentHealthPoints += 2;
        if (retrieveLevel() < 3) {
            currentHealthPoints -= 1;
        }
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
        return true;
    }
}
