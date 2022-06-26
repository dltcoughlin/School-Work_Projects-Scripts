//Circle class extending TwoDimensionalShape
public class Circle extends TwoDimensionalShape{
	double area;
	double radius;
	
	public Circle(double radius){
		super("Circle");
		this.radius = radius;
	}
	public void caculateArea(double radius){
		this.area = Math.pow(radius, 2) * 3.14;
	}
	public double getArea(){
		return area;
	}
		
}
		