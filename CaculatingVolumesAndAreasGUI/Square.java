//Rectangle class extending TwoDimensionalShape
public class Square extends TwoDimensionalShape {
    double area;
	double side;
    public Square(double side) {
        super("Square");
        this.side = side;
    }
    public void caculateArea(double side) {
        this.area = Math.pow(side, 2);
    }
	public double getArea(){
		return area;
	}
}