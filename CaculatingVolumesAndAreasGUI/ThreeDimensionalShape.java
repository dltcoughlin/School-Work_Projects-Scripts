// ThreeDimensionalShape extending shape parent
public class ThreeDimensionalShape extends Shape{
	int numOfDimensions;
	String nameOfShape;
	double volume;
	public ThreeDimensionalShape(String nameOfShape){
		super(nameOfShape, 3);
	}
}