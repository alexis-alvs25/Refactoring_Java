package re.forestier.edu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Player;

public class UnitTests {

    @Test
    @DisplayName("Add money")
    void testAddMoney() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addMoney(50);
        assertThat(player.getMoney(), is(150));
    }

    @Test
    @DisplayName("Remove Money")
    void testRemoveMoney() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.removeMoney(50);
        assertThat(player.getMoney(), is(50));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Remove money equal 0 - Pitest Mutation")
    void testNegativeMoneyMutation() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.removeMoney(100);
        assertEquals(p.getMoney(), 0);
    }

    @Test
    @DisplayName("Class test Adventurer")
    void testPlayerClassAdventurer() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        assertThat(player.getAvatarClass(), is(AvatarClass.ADVENTURER));
    }

    @Test
    @DisplayName("Class test Archer")
    void testPlayerClassArcher() {
        Player player = new Archer("Florian", "Grognak le barbare", AvatarClass.ARCHER, 100);
        assertThat(player.getAvatarClass(), is(AvatarClass.ARCHER));
    }

    @Test
    @DisplayName("Class test Dwarf")
    void testPlayerClassDwarf() {
        Player player = new Dwarf("Florian", "Grognak le barbare", AvatarClass.DWARF, 100);
        assertThat(player.getAvatarClass(), is(AvatarClass.DWARF));
    }

    @Test
    @DisplayName("Get xp")
    void testGetXp() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        assertThat(player.getXp(), is(0));
    }

    @Test
    @DisplayName("Add 0xp should do nothing")
    void testAddXp() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addXp(0);
        assertThat(player.getXp(), is(0));
    }

    @Test
    @DisplayName("Retrieve Level 1")
    void testRetrieveLevel1() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        assertThat(player.retrieveLevel(), is(1));
    }

    @Test
    @DisplayName("Retrieve Level 2")
    void testRetrieveLevel2() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addXp(10);
        assertThat(player.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Retrieve Level 3")
    void testRetrieveLevel3() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addXp(27);
        assertThat(player.retrieveLevel(), is(3));
    }

    @Test
    @DisplayName("Retrieve Level 4")
    void testRetrieveLevel4() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addXp(57);
        assertThat(player.retrieveLevel(), is(4));
    }

    @Test
    @DisplayName("Retrieve Level 5")
    void testRetrieveLevel5() {
        Player player = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        player.addXp(111);
        assertThat(player.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Current health points == 0")
    void testMajFinDeTour() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.setCurrentHealthPoints(0);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(0));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Dwarf, Holy Elixir")
    void testMajFinDeTour2() {
        Player p = new Dwarf("Florian", "Grognak le barbare", AvatarClass.DWARF, 100);
        p.setCurrentHealthPoints(1);
        p.setHealthPoints(4);
        p.addInventory("Holy Elixir");
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(3));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Dwarf")
    void testMajFinDeTour3() {
        Player p = new Dwarf("Florian", "Grognak le barbare", AvatarClass.DWARF, 100);
        p.setCurrentHealthPoints(1);
        p.setHealthPoints(4);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(2));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Archer, Magic Bow")
    void testMajFinDeTour4() {
        Player p = new Archer("Florian", "Grognak le barbare", AvatarClass.ARCHER, 100);
        p.setCurrentHealthPoints(15);
        p.setHealthPoints(34);
        p.addInventory("Magic Bow");
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(17));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Archer")
    void testMajFinDeTour5() {
        Player p = new Archer("Florian", "Grognak le barbare", AvatarClass.ARCHER, 100);
        p.setCurrentHealthPoints(15);
        p.setHealthPoints(34);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(16));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Adventurer, retrieveLevel < 3")
    void testMajFinDeTour6() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.setCurrentHealthPoints(1);
        p.setHealthPoints(4);
        p.retrieveLevel();
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(2));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Adventurer, retrieveLevel > 3")
    void testMajFinDeTour7() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.setCurrentHealthPoints(1);
        p.setHealthPoints(100);
        p.addXp(30);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(3));
    }

    @Test
    @DisplayName("currenthealthpoints >= healthpoints/2")
    void testMajFinDeTour8() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.setCurrentHealthPoints(4);
        p.setHealthPoints(4);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(4));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints")
    void testMajFinDeTour9() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.setCurrentHealthPoints(1);
        p.setHealthPoints(2);
        p.majFinDeTour();
        assertThat(p.getCurrentHealthPoints(), is(1));
    }

    @Test
    @DisplayName("Print test")
    void testAffichage() {
        Player p = new Adventurer("Florian", "Grognak le barbare", AvatarClass.ADVENTURER, 100);
        p.addInventory("Magic Bow");
        p.addInventory("Healing Potion");

        String result = p.toString();

        // Utilise AssertJ pour vérifier les éléments de l'inventaire
        assertThat(result, result.contains("Magic Bow"));
        assertThat(result, result.contains("Healing Potion"));

        // Vérifie que les éléments sont formatés correctement
        assertThat(result, result.contains("\n   Magic Bow"));
        assertThat(result, result.contains("\n   Healing Potion"));
    }
}
