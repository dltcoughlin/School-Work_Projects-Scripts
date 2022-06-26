//Torus class extending ThreeDimensionalShape
public class Torus extends ThreeDimensionalShape {
    double smallCircleRadius;
	double largeCircleRadius;
	double volume;
	String direction = "vertical";
    public Torus(double smallCircleRadius, double largeCircleRadius) {
        super("Torus");
        this.smallCircleRadius = smallCircleRadius;
        this.largeCircleRadius = largeCircleRadius;
    }
    public void caculateVolume(double smallCircleRadius, double largeCircleRadius) {
        this.volume = 2 * Math.pow(3.14, 2) * Math.pow(smallCircleRadius,2) * largeCircleRadius;
    }
	public void changeDirection(String direction){
		this.direction = direction;
	}
	public double getVolume(){
		return volume;
	}
}