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
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {
        return new HashMap<>(ADVENTURER_ABILITIES.get(avatarClass).get(level));
    }

    @Override
    public void majFinDeTour() {
        if (currenthealthpoints == 0) {
            return;                         // Arrête immédiatement si le joueur est KO
        }

        if ((currenthealthpoints >= healthpoints / 2) && currenthealthpoints < healthpoints) {
            return;
        }

        super.majFinDeTour();

        currenthealthpoints += 2;
        if (retrieveLevel() < 3) {
            currenthealthpoints -= 1;
        }
        if (currenthealthpoints > healthpoints) {
            currenthealthpoints = healthpoints;
        }
    }
}
