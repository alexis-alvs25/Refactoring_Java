package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dwarf extends Player {
    public Dwarf(String playerName, String avatarName, AvatarClass avatarClass, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, avatarClass, money, inventory);
    }

    public static final Map<AvatarClass, Map<Integer, Map<String, Integer>>> DWARF_ABILITIES = Map.of(
        AvatarClass.DWARF, Map.of(
            1, Map.of("ALC", 4, "INT", 1, "ATK", 3),
            2, Map.of("DEF", 1, "ALC", 5),
            3, Map.of("ATK", 4),
            4, Map.of("DEF", 2),
            5, Map.of("CHA", 1)
    ));

    @Override
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {
        return new HashMap<>(DWARF_ABILITIES.get(avatarClass).get(level));
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
        
        if (inventory.contains("Holy Elixir")) {
            currenthealthpoints += 1;
        }
        currenthealthpoints += 1;
        if (currenthealthpoints > healthpoints) {
            currenthealthpoints = healthpoints;
        }
    }
}
