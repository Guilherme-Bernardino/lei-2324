package sensors;

import coords.Position;
import locals.DistributionCenter;
import locals.Local;
import products.Transportable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Sensor {

    Map<Local, List<Direction>> detections;

    public Sensor(){
        detections = new HashMap<>();
    }

    public abstract void detect(DistributionCenter distributionCenter);

    public abstract double getRange();

    public abstract double getAngle();

    protected void updateDetection(Local local, List<Direction> directions) {
        detections.put(local, directions);
    }
}
