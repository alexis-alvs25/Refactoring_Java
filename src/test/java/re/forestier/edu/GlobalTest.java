package re.forestier.edu;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Player;
import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 200);
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(player.toString());
    }
}
