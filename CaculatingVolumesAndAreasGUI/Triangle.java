//Rectangle class extending TwoDimensionalShape
public class Triangle extends TwoDimensionalShape {
    double area;
	double height;
	double base;
	String type = "right";
    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }
    public void caculateArea(double base, double height) {
        this.area = 0.5 * base * height;
    }
	public void changeType(String type){
		this.type = type;
	}
	public double getArea(){
		return area;
	}

}