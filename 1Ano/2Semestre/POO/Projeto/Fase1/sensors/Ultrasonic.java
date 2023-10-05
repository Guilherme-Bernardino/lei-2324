package sensors;

import locals.DistributionCenter;
import locals.Local;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ultrasonic extends Sensor{

    private double RANGE = 8;
    private double ANGLE = 180;

    public Ultrasonic() {
        super();
    }

    @Override
    public void detect(DistributionCenter distributionCenter) {
        detections.clear();

        for (Local local : distributionCenter.getLocals()) {
            List<Direction> directions = calculatePath(local);
            updateDetection(local, directions);
        }
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
