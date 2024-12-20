package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Player;
import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 200, new ArrayList<>());
        player.addXp(20);
        player.inventory = new ArrayList<>();

        verify(player.toString());
    }
}
