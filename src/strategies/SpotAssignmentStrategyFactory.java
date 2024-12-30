package strategies;
import models.SpotAssignmentstrategyType;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(SpotAssignmentstrategyType spotAssignmentstrategyType){
        return new RandomSpotAssignmentStrategy();
    }
}
