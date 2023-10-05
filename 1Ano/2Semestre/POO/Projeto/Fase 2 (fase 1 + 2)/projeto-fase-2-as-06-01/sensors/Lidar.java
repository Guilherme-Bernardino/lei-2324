package sensors;

import coords.Position;
import locals.DistributionCenter;
import locals.Local;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lidar extends Sensor{

    private double RANGE = 20;
    private double ANGLE = 30;

    public Lidar(){
        super();
    }

    @Override
    public void detect(DistributionCenter distributionCenter) {
        detections.clear();

        for (Local local : distributionCenter.getLocals()) {
            if (isWithinRange(local, distributionCenter)) {
                List<Direction> directions = calculatePath(local);
                updateDetection(local, directions);
            }
        }
    }

    private boolean isWithinRange(Local local, DistributionCenter distributionCenter) {
        double localDistance = calculateDistance(local.getPosition(), distributionCenter.getPosition());
        return localDistance <= RANGE;
    }

    private double calculateDistance(Position pos1, Position pos2) {
        double deltaX = pos2.getX() - pos1.getX();
        double deltaY = pos2.getY() - pos1.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public double getRange() {
        return RANGE;
    }

    @Override
    public double getAngle() {
        return ANGLE;
    }

    private List<Direction> calculatePath(Local local) {
        List<Direction> directions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            directions.add(Direction.values()[random.nextInt(Direction.values().length)]);
        }
        return directions;
    }
}
