package re.forestier.edu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class UnitTests {

    @Test
    @DisplayName("Add money")
    void testAddMoney() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.addMoney(50);
        assertThat(player.money, is(150));
    }

    @Test
    @DisplayName("Remove Money")
    void testRemoveMoney() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.removeMoney(50);
        assertThat(player.money, is(50));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

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
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertEquals(p.money, 0);
    }

    @Test
    @DisplayName("Class test Adventurer")
    void testPlayerClassAdventurer() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getAvatarClass(), is("ADVENTURER"));
    }

    @Test
    @DisplayName("Class test Archer")
    void testPlayerClassArcher() {
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        assertThat(player.getAvatarClass(), is("ARCHER"));
    }

    @Test
    @DisplayName("Class test Dwarf")
    void testPlayerClassDwarf() {
        player player = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        assertThat(player.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName("Invalid avatar class should not initialize the player")
    void testInvalidAvatarClass() {
        player player = new player("Florian", "Grognak le barbare", "GUERRIER", 100, new ArrayList<>());
        assertNull(player.getAvatarClass(), "AvatarClass should be null for invalid class");
    }

    @Test
    @DisplayName("Get xp")
    void testGetXp() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getXp(), is(0));
    }

    @Test
    @DisplayName("Add 0xp should do nothing")
    void testAddXp() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 0);
        assertThat(player.getXp(), is(0));
    }

    @Test
    @DisplayName("Retrieve Level 1")
    void testRetrieveLevel1() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.retrieveLevel(), is(1));
    }

    @Test
    @DisplayName("Retrieve Level 2")
    void testRetrieveLevel2() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 10);
        assertThat(player.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Retrieve Level 3")
    void testRetrieveLevel3() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 27);
        assertThat(player.retrieveLevel(), is(3));
    }

    @Test
    @DisplayName("Retrieve Level 4")
    void testRetrieveLevel4() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 57);
        assertThat(player.retrieveLevel(), is(4));
    }

    @Test
    @DisplayName("Retrieve Level 5")
    void testRetrieveLevel5() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 111);
        assertThat(player.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Current health points == 0")
    void testMajFinDeTour() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(0));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Dwarf, Holy Elixir")
    void testMajFinDeTour2() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 4;
        p.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Dwarf")
    void testMajFinDeTour3() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Archer, Magic Bow")
    void testMajFinDeTour4() {
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 15;
        p.healthpoints = 34;
        p.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(17));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Archer")
    void testMajFinDeTour5() {
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 15;
        p.healthpoints = 34;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(16));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Adventurer, retrieveLevel < 3")
    void testMajFinDeTour6() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 4;
        p.retrieveLevel();
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints/2, Adventurer, retrieveLevel > 3")
    void testMajFinDeTour7() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 100;
        UpdatePlayer.addXp(p, 30);
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("currenthealthpoints >= healthpoints/2")
    void testMajFinDeTour8() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 4;
        p.healthpoints = 4;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(4));
    }

    @Test
    @DisplayName("currenthealthpoints < healthpoints")
    void testMajFinDeTour9() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 1;
        p.healthpoints = 2;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(1));
    }

    @Test
    @DisplayName("Print test")
    void testAffichage() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.inventory.add("Magic Bow");
        p.inventory.add("Healing Potion");

        String result = Affichage.afficherJoueur(p);

        // Utilise AssertJ pour vérifier les éléments de l'inventaire
        assertThat(result, result.contains("Magic Bow"));
        assertThat(result, result.contains("Healing Potion"));

        // Vérifie que les éléments sont formatés correctement
        assertThat(result, result.contains("\n   Magic Bow"));
        assertThat(result, result.contains("\n   Healing Potion"));
    }
}
