package re.forestier.edu.rpg;

public class Object {
    
    private final static String[] objectList = {
        "Lookout Ring : Prevents surprise attacks",
        "Scroll of Stupidity : INT-2 when applied to an enemy",
        "Draupnir : Increases XP gained by 100%",
        "Magic Charm : Magic +10 for 5 rounds",
        "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?",
        "Combat Edge : Well, that's an edge",
        "Holy Elixir : Recover your HP"
    };

    public static String[] getObjectlist() {
        return objectList;
    }

}
