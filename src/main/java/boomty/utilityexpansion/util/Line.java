package boomty.utilityexpansion.util;

public class Line {
    private final double minX, minY, maxX, maxY;
    private final double slope;
    private final double intercept;

    public Line(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        slope = (maxY-minY)/(maxX-minX);
        intercept = maxY - slope * maxX;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }
}