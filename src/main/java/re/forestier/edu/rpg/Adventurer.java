package re.forestier.edu.rpg;

import java.util.ArrayList;

public class Adventurer extends Player {
    public Adventurer(String playerName, String avatarName, AvatarClass avatarClass, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, avatarClass, money, inventory);
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
