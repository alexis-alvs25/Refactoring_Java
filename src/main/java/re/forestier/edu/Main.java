package re.forestier.edu;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.GameObject;
import re.forestier.edu.rpg.Player;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Adventurer("Florian", "Ruzberg de Rivehaute", AvatarClass.ADVENTURER, 100);

        firstPlayer.addXp(15);
        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("HealthPoints : " + firstPlayer.getCurrentHealthPoints() + " / " + firstPlayer.getHealthPoints());

        System.out.println("------------------");

        firstPlayer.addXp(400);

        GameObject object = new GameObject("Draupnir", "Increases XP gained by 100%", 5, 50);
        firstPlayer.addObjectToInventory(object);

        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("HealthPoints : " + firstPlayer.getCurrentHealthPoints() + " / " + firstPlayer.getHealthPoints());

        System.out.println("------------------");

        firstPlayer.sellObject(object);
        firstPlayer.updateHealth();

        System.out.println(firstPlayer);
        System.out.println("\nMoney : " + firstPlayer.getMoney());
        System.out.println("HealthPoints : " + firstPlayer.getCurrentHealthPoints() + " / " + firstPlayer.getHealthPoints());

        System.out.println("\n\n" + firstPlayer.toMarkdown());
    }
}