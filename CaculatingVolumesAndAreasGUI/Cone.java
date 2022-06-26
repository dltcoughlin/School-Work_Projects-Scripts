//Cone class extending ThreeDimensionalShape
public class Cone extends ThreeDimensionalShape{
	double height;
	double radius;
	String direction = "facing-down";
	public Cone(double radius, double height) {
        super("Cone");
        this.radius = radius;
        this.height = height;
    }
    public void caculateVolume(double radius, double height) {
        this.volume = (3.14 * Math.pow(radius, 2) * height) / 3;
    }
	public void changeDirection(String direction){
		this.direction = direction;
	}
	public double getVolume(){
		return volume;
	}

}