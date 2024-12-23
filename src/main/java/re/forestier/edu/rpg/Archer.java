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
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {
        return new HashMap<>(ARCHER_ABILITIES.get(avatarClass).get(level));
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
        
        currenthealthpoints += 1;
        if (inventory.stream().anyMatch(obj -> obj instanceof GameObject && ((GameObject) obj).getObjectName().equals("Magic Bow"))) {
            currenthealthpoints += currenthealthpoints / 8 - 1;
        }
        if (currenthealthpoints > healthpoints) {
            currenthealthpoints = healthpoints;
        }
    }
}
