//Cube class extending ThreeDimensionalShape
public class Cube extends ThreeDimensionalShape{
    double side;
	double volume;
    public Cube(double side) {
        super("Cube");
        this.side = side;
    }
    public void caculateVolume(double side) {
        this.volume = Math.pow(side, 3);
    }
	public double getVolume(){
		return volume;
	}
}