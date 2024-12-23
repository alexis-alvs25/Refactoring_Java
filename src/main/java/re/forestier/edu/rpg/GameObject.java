package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameObject {
    private String objectName;
    private String description;
    private int weight;
    private int value;

    private final static Map<String, String> likelyObjects = 
        Map.of(
            "Lookout Ring", "Prevents surprise attacks",
            "Scroll of Stupidity", "INT-2 when applied to an enemy",
            "Draupnir", "Increases XP gained by 100%",
            "Magic Charm",  "Magic +10 for 5 rounds",
            "Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?",
            "Combat Edge", "Well, that's an edge",
            "Holy Elixir", "Recover your HP"
        );

    public static Map<String, String> getLikelyobjects() {
        return likelyObjects;
    }
    
        public GameObject (String objectName, String description, int weight, int value) {
            if (weight < 0 || value < 0) {
                throw new IllegalArgumentException("Weight and value must be positive !");
            }
            this.objectName = objectName;
            this.description = description;
            this.weight = weight;
            this.value = value;
        }
    
    
        public String getObjectName() {
            return objectName;
        }
    
        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public int getWeight() {
            return weight;
        }
    
        public void setWeight(int weight) {
            this.weight = weight;
        }
    
        public int getValue() {
            return value;
        }
    
        public void setValue(int value) {
            this.value = value;
        }
    
        public static GameObject giveRandomObject() {
            Random rand = new Random();
            int randomWeight = rand.nextInt(15) + 1;
            int randomValue = rand.nextInt(15) + 1;
            
            List<String> keys = new ArrayList<>(likelyObjects.keySet());
        String randomKey = keys.get(rand.nextInt(keys.size()));
        String randomDescription = likelyObjects.get(randomKey);
        return new GameObject(randomKey, randomDescription, randomWeight, randomValue);
    }

    @Override
    public String toString() {
        return String.format("%s : %s (Weight: %d, Value: %d)", objectName, description, weight, value);
    }

}
