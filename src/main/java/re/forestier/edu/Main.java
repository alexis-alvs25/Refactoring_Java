package re.forestier.edu;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.GameObject;
import re.forestier.edu.rpg.Goblin;
import re.forestier.edu.rpg.Player;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Dwarf("Florian", "Ruzberg de Rivehaute", AvatarClass.DWARF, 100);

        firstPlayer.addXp(15);
        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("Current Health Points : " + firstPlayer.getCurrentHealthPoints());
        System.out.println("Health Points : " + firstPlayer.getHealthPoints());

        System.out.println("------------------");

        firstPlayer.addXp(200);

        GameObject object = new GameObject("Draupnir", "Increases XP gained by 100%", 5, 50);
        firstPlayer.addObjectToInventory(object);

        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("Current Health Points : " + firstPlayer.getCurrentHealthPoints());
        System.out.println("Health Points : " + firstPlayer.getHealthPoints());
        
        System.out.println("------------------");

        firstPlayer.sellObject(object);
        firstPlayer.majFinDeTour();

        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("Current Health Points : " + firstPlayer.getCurrentHealthPoints());
        System.out.println("Health Points : " + firstPlayer.getHealthPoints());
    }
}