package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Dwarf extends Player {
    public Dwarf(String playerName, String avatarName, AvatarClass avatarClass, int money) {
        super(playerName, avatarName, avatarClass, money);
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
    protected HashMap<String, Integer> getAbilitiesByLevel(AvatarClass avatarClass, int level) {        // On surcharge la méthode pour l'adapter à l'aventurier et récupérer ses capacités
        return new HashMap<>(DWARF_ABILITIES.get(avatarClass).get(level));
    }

    @Override
    public boolean updateHealth() {
        
        if (!super.updateHealth()) {              // On traite les cas généraux dans la classe mère
            return false;                         // Arrête immédiatement si le joueur est KO
        }
        
        if (inventory.stream().anyMatch(obj -> obj instanceof GameObject && ((GameObject) obj).getObjectName().equals("Holy Elixir"))) {
            currentHealthPoints += 1;
        }
        currentHealthPoints += 1;
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
        return true;
    }
}
