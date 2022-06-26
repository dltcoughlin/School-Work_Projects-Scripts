//Sphere class extending ThreeDimensionalShape
public class Sphere extends ThreeDimensionalShape {
    double radius;
	double volume;
    public Sphere(double radius) {
        super("Sphere");
        this.radius = radius;
    }
    public void caculateVolume(double radius) {
        this.volume = (4 / 3) * 3.14 * Math.pow(radius, 2);
    }
	public double getVolume(){
		return volume;
	}
}