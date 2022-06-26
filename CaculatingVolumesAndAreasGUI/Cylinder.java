//Cylinder class extending ThreeDimensionalShape
public class Cylinder extends ThreeDimensionalShape {
    double radius;
	double height;
    public Cylinder(double radius, double height) {
        super("Cylinder");
        this.radius = radius;
        this.height = height;
    }
    public void caculateVolume(double radius, double height) {
        this.volume = (3.14 * Math.pow(radius, 2) * height);
    }
	public double getVolume(){
		return volume;
	}

}