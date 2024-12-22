package re.forestier.edu;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Player;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Adventurer("Florian", "Ruzberg de Rivehaute", AvatarClass.ADVENTURER, 200);
        firstPlayer.addMoney(400);

        firstPlayer.addXp(15);
        System.out.println(firstPlayer);
        // System.out.println("\nCurrent Health Points : " + firstPlayer.getCurrentHealthPoints());
        // System.out.println("HealthPoints : " + firstPlayer.getHealthPoints()); 
        System.out.println("------------------");
        firstPlayer.addXp(25);
        // // firstPlayer.majFinDeTour();
        System.out.println(firstPlayer);
        // System.out.println("\nCurrent Health Points : " + firstPlayer.getCurrentHealthPoints());
        // System.out.println("HealthPoints : " + firstPlayer.getHealthPoints()); 
    }
}