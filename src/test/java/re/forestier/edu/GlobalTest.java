package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.AvatarClass;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 200, new ArrayList<>());
        player.addXp(20);
        player.inventory = new ArrayList<>();

        verify(player.toString());
    }
}
