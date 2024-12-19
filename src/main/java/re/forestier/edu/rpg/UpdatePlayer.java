package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    private static final Random random = new Random();

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();

        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
        HashMap<String, Integer> adventurerLevel1 = new HashMap<>();
        adventurerLevel1.put("INT", 1);
        adventurerLevel1.put("DEF", 1);
        adventurerLevel1.put("ATK", 3);
        adventurerLevel1.put("CHA", 2);
        adventurerMap.put(1, adventurerLevel1);

        HashMap<String, Integer> adventurerLevel2 = new HashMap<>();
        adventurerLevel2.put("INT", 2);
        adventurerLevel2.put("CHA", 3);
        adventurerMap.put(2, adventurerLevel2);

        HashMap<String, Integer> adventurerLevel3 = new HashMap<>();
        adventurerLevel3.put("ATK", 5);
        adventurerLevel3.put("ALC", 1);
        adventurerMap.put(3, adventurerLevel3);

        HashMap<String, Integer> adventurerLevel4 = new HashMap<>();
        adventurerLevel4.put("DEF", 3);
        adventurerMap.put(4, adventurerLevel4);

        HashMap<String, Integer> adventurerLevel5 = new HashMap<>();
        adventurerLevel5.put("VIS", 1);
        adventurerLevel5.put("DEF", 4);
        adventurerMap.put(5, adventurerLevel5);

        abilitiesPerTypeAndLevel.put("ADVENTURER", adventurerMap);


        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
        HashMap<String, Integer> archerLevel1 = new HashMap<>();
        archerLevel1.put("INT", 1);
        archerLevel1.put("ATK", 3);
        archerLevel1.put("CHA", 1);
        archerLevel1.put("VIS", 3);
        archerMap.put(1, archerLevel1);

        HashMap<String, Integer> archerLevel2 = new HashMap<>();
        archerLevel2.put("DEF", 1);
        archerLevel2.put("CHA", 2);
        archerMap.put(2, archerLevel2);

        HashMap<String, Integer> archerLevel3 = new HashMap<>();
        archerLevel3.put("ATK", 3);
        archerMap.put(3, archerLevel3);

        HashMap<String, Integer> archerLevel4 = new HashMap<>();
        archerLevel4.put("DEF", 2);
        archerMap.put(4, archerLevel4);

        HashMap<String, Integer> archerLevel5 = new HashMap<>();
        archerLevel5.put("ATK", 4);
        archerMap.put(5, archerLevel5);

        abilitiesPerTypeAndLevel.put("ARCHER", archerMap);


        HashMap<Integer, HashMap<String, Integer>> dwarf = new HashMap<>();
        HashMap<String, Integer> dwarfLevel1 = new HashMap<>();
        dwarfLevel1.put("ALC", 4);
        dwarfLevel1.put("INT", 1);
        dwarfLevel1.put("ATK", 3);
        dwarf.put(1, dwarfLevel1);

        HashMap<String, Integer> dwarfLevel2 = new HashMap<>();
        dwarfLevel2.put("DEF", 1);
        dwarfLevel2.put("ALC", 5);
        dwarf.put(2, dwarfLevel2);

        HashMap<String, Integer> dwarfLevel3 = new HashMap<>();
        dwarfLevel3.put("ATK", 4);
        dwarf.put(3, dwarfLevel3);

        HashMap<String, Integer> dwarfLevel4 = new HashMap<>();
        dwarfLevel4.put("DEF", 2);
        dwarf.put(4, dwarfLevel4);

        HashMap<String, Integer> dwarfLevel5 = new HashMap<>();
        dwarfLevel5.put("CHA", 1);
        dwarf.put(5, dwarfLevel5);

        abilitiesPerTypeAndLevel.put("DWARF", dwarf);

        return abilitiesPerTypeAndLevel;
    }
    
    public static boolean addXp(Player player, int xp) {
        if (xp < 0) {
            throw new IllegalArgumentException("XP cannot be negative");
        }
        player.xp += xp;
        int currentLevel = player.getLevel();
        int newLevel = player.retrieveLevel();

        if (newLevel > currentLevel) {                                              // Player leveled-up!
            player.addInventory(objectList[random.nextInt(objectList.length)]);     // Give a random object

            // Add/upgrade abilities to player
            HashMap<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass().name()).get(newLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, abilities.get(ability));
            });
            return true;
        }
        return false;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if(player.getCurrentHealthPoints() == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if(player.getCurrentHealthPoints() < player.getHealthPoints()/2) {
            if(!player.getAvatarClass().name().equals("ADVENTURER")) {
                if(player.getAvatarClass().name().equals("DWARF")) {
                    if(player.getInventory().contains("Holy Elixir")) {
                        player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 1);
                    }
                    player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 1);
                } else if(player.getAvatarClass().name().equals("ADVENTURER")) {
                    player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 2);
                }


                if(player.getAvatarClass().name().equals("ARCHER")) {
                    player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 1);
                    if(player.getInventory().contains("Magic Bow")) {
                        player.setCurrentHealthPoints(player.getCurrentHealthPoints() + player.getCurrentHealthPoints()/8-1);
                    }
                }
            } else {
                player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 2);
                if(player.retrieveLevel() < 3) {
                    player.setCurrentHealthPoints(player.getCurrentHealthPoints() - 1);
                }
            }
        } else if(player.getCurrentHealthPoints() >= player.getHealthPoints()/2){
            if(player.getCurrentHealthPoints() >= player.getHealthPoints()) {
                player.setCurrentHealthPoints(player.getHealthPoints());
                return;
            }
        }


        if(player.getCurrentHealthPoints() >= player.getHealthPoints()) {
            player.setCurrentHealthPoints(player.getHealthPoints());
        }
    }
}