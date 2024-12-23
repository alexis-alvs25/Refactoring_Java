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
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass adventurer, int level) {
        return new HashMap<>(ADVENTURER_ABILITIES.get(adventurer).get(level));
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

        // Personnalisation de la méthode majFinDeTour pour le gobelin
        currenthealthpoints += 1;
        if (inventory.stream().anyMatch(obj -> obj instanceof GameObject && ((GameObject) obj).getObjectName().equals("Magic Bow"))) {
            currenthealthpoints -= 3;
        }
        if (currenthealthpoints > healthpoints) {
            currenthealthpoints = healthpoints;
        }
    }
}
