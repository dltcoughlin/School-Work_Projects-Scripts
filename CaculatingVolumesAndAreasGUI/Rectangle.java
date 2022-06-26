//Rectangle class extending TwoDimensionalShape
public class Rectangle extends TwoDimensionalShape {
    double area;
	double width;
	double length;
	String direction = "horizontal";
	String type = "Parallelogram";
    public Rectangle(double length, double width) {
        super("Rectangle");
        this.length = length;
        this.width = width;
    }
    public void caculateArea(double length, double width) {
        this.area = length * width;
    }
	public void changeDirection(String direction){
		this.direction = direction;
	}
	public void changeType(String type){
		this.type = type;
	}
	public double getArea(){
		return area;
	}
}