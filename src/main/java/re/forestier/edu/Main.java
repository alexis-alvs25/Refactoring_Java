package re.forestier.edu;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Adventurer("Florian", "Ruzberg de Rivehaute", AvatarClass.ADVENTURER, 200, new ArrayList<>());
        firstPlayer.addMoney(400);

        UpdatePlayer.addXp(firstPlayer, 15);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("\nCurrent Health Points : " + firstPlayer.getCurrentHealthPoints());
        System.out.println("HealthPoints : " + firstPlayer.getHealthPoints()); 
        firstPlayer.majFinDeTour();
        System.out.println("------------------");
        UpdatePlayer.addXp(firstPlayer, 20);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("\nCurrent Health Points : " + firstPlayer.getCurrentHealthPoints());
        System.out.println("HealthPoints : " + firstPlayer.getHealthPoints());  
    }
}