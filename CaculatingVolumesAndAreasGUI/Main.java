//Main java script to take commands in 
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
public class Main {
	public static void main(String[] args) {

			JButton submitButton;
			JTextField radiusField;
			JRadioButton circleR=new JRadioButton("Circle");    
			JRadioButton rectR=new JRadioButton("Rectangle");    
			JRadioButton squareR=new JRadioButton("Square");  
			JRadioButton triangleR=new JRadioButton("Triangle");  
			JRadioButton sphereR=new JRadioButton("Sphere");  
			JRadioButton cubeR=new JRadioButton("Cube");  
			JRadioButton coneR=new JRadioButton("Cone");  
			JRadioButton cylinderR=new JRadioButton("Cylinder");  
			JRadioButton torusR=new JRadioButton("Torus");  
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(circleR);
			buttonGroup.add(rectR);
			buttonGroup.add(squareR);
			buttonGroup.add(triangleR);
			buttonGroup.add(sphereR);
			buttonGroup.add(cubeR);
			buttonGroup.add(coneR);
			buttonGroup.add(cylinderR); 
			buttonGroup.add(torusR); 
			JFrame frame = new JFrame("");
			JPanel shapePanel = new JPanel(new GridLayout(0, 1));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 500);
			Border border = BorderFactory.createTitledBorder("Shape Selection:");
			shapePanel.setBorder(border);
			shapePanel.add(circleR);
			shapePanel.add(rectR);
			shapePanel.add(squareR);
			shapePanel.add(triangleR);
			shapePanel.add(sphereR);
			shapePanel.add(cubeR);
			shapePanel.add(coneR);
			shapePanel.add(cylinderR); 
			shapePanel.add(torusR); 
			frame.add(shapePanel);	
			frame.setVisible(true);
			//building variables for input
			ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				String selection = aButton.getText();
				switch (selection){
					case "Circle":
						JFrame circleFrame = new JFrame("Caculate Area of Circle");
						JTextField radiusField = new JTextField(20);
						JButton submitButton = new JButton("Submit");
						JLabel radiusLabel = new JLabel("Radius:");
						JPanel circlePanel = new JPanel();
						circleFrame.setSize(350,400);
						circlePanel.add(radiusLabel);
						circlePanel.add(radiusField);
						circlePanel.add(submitButton);
						circleFrame.add(circlePanel);
						circleFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double radius = Double.parseDouble(radiusField.getText());
								Circle circleObj = new Circle(radius);
								circleObj.caculateArea(radius);
								JLabel radiusLabel = new JLabel("Results for Area = " + String.valueOf(circleObj.getArea()));
								circlePanel.add(radiusLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Circle.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									circlePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								circlePanel.revalidate();
								circlePanel.repaint();
							}
						});
						break;
					case "Rectangle":
						JFrame rectFrame = new JFrame("Caculate Area of Rectangle");
						JTextField widthField = new JTextField(20);
						JTextField lengthField = new JTextField(20);
						submitButton = new JButton("Submit");
						JLabel widthLabel = new JLabel("Width:");
						JLabel lengthLabel = new JLabel("Length:");
						JPanel rectPanel = new JPanel();
						rectFrame.setSize(650,300);
						rectPanel.add(widthLabel);
						rectPanel.add(widthField);
						rectPanel.add(lengthLabel);
						rectPanel.add(lengthField);
						rectPanel.add(submitButton);
						rectFrame.add(rectPanel);
						rectFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double width = Double.parseDouble(widthField.getText());
								double length = Double.parseDouble(lengthField.getText());
								Rectangle recteObj = new Rectangle(width, length);
								recteObj.caculateArea(width, length);
								JLabel areaLabel = new JLabel("Results for Area = " + String.valueOf(recteObj.getArea()));
								rectPanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Rectangle.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									rectPanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								rectPanel.revalidate();
								rectPanel.repaint();
							}
						});
						break;
					case "Square":
						JFrame squareFrame = new JFrame("Caculate Area of Square");
						JTextField sideField = new JTextField(20);
						submitButton = new JButton("Submit");
						JLabel sideLabel = new JLabel("Side:");
						JPanel squarePanel = new JPanel();
						squareFrame.setSize(650,300);
						squarePanel.add(sideLabel);
						squarePanel.add(sideField);
						squarePanel.add(submitButton);
						squareFrame.add(squarePanel);
						squareFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double side = Double.parseDouble(sideField.getText());
								Square squareObj = new Square(side);
								squareObj.caculateArea(side);
								JLabel areaLabel = new JLabel("Results for Area = " + String.valueOf(squareObj.getArea()));
								squarePanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Square.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									squarePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								squarePanel.revalidate();
								squarePanel.repaint();
							}
						});
						break;
					case "Triangle":
						JFrame triangleFrame = new JFrame("Caculate Area of Triangle");
						JTextField baseField = new JTextField(20);
						JTextField heightField = new JTextField(20);
						submitButton = new JButton("Submit");
						JLabel baseLabel = new JLabel("Base:");
						JLabel heightLabel = new JLabel("Height:");
						JPanel trianglePanel = new JPanel();
						triangleFrame.setSize(650,300);
						trianglePanel.add(baseLabel);
						trianglePanel.add(baseField);
						trianglePanel.add(heightLabel);
						trianglePanel.add(heightField);
						trianglePanel.add(submitButton);
						triangleFrame.add(trianglePanel);
						triangleFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double base = Double.parseDouble(baseField.getText());
								double height = Double.parseDouble(heightField.getText());
								Triangle triangleObj = new Triangle(base, height);
								triangleObj.caculateArea(base, height);
								JLabel areaLabel = new JLabel("Results for Area = " + String.valueOf(triangleObj.getArea()));
								trianglePanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Triangle.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									trianglePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								trianglePanel.revalidate();
								trianglePanel.repaint();
							}
						});
						break;
					case "Sphere":
						JFrame sphereFrame = new JFrame("Vaculate Volume of Sphere");
						radiusField = new JTextField(20);
						submitButton = new JButton("Submit");
						radiusLabel = new JLabel("Radius:");
						JPanel spherePanel = new JPanel();
						sphereFrame.setSize(650,300);
						spherePanel.add(radiusLabel);
						spherePanel.add(radiusField);
						spherePanel.add(submitButton);
						sphereFrame.add(spherePanel);
						sphereFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double radius = Double.parseDouble(radiusField.getText());
								Sphere sphereObj = new Sphere(radius);
								sphereObj.caculateVolume(radius);
								JLabel areaLabel = new JLabel("Results for Volume = " + String.valueOf(sphereObj.getVolume()));
								spherePanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Sphere.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									spherePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								spherePanel.revalidate();
								spherePanel.repaint();
							}
						});
						break;
					case "Cube":
						JFrame cubeFrame = new JFrame("Caculate Volume of Cube");
						sideField = new JTextField(20);
						submitButton = new JButton("Submit");
						sideLabel = new JLabel("Side:");
						JPanel cubePanel = new JPanel();
						cubeFrame.setSize(650,300);
						cubePanel.add(sideLabel);
						cubePanel.add(sideField);
						cubePanel.add(submitButton);
						cubeFrame.add(cubePanel);
						cubeFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double side = Double.parseDouble(sideField.getText());
								Cube cubeObj = new Cube(side);
								cubeObj.caculateVolume(side);
								JLabel areaLabel = new JLabel("Results for Volume = " + String.valueOf(cubeObj.getVolume()));
								cubePanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Cube.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									cubePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								cubePanel.revalidate();
								cubePanel.repaint();
							}
						});
						break;
					case "Cone":
						JFrame coneFrame = new JFrame("Caculate Volume of Cone");
						radiusField = new JTextField(20);
						submitButton = new JButton("Submit");
						radiusLabel = new JLabel("Radius:");
						heightField = new JTextField(20);
						heightLabel = new JLabel("Height:");
						JPanel conePanel = new JPanel();
						coneFrame.setSize(650,300);
						conePanel.add(radiusLabel);
						conePanel.add(radiusField);
						conePanel.add(heightLabel);
						conePanel.add(heightField);
						conePanel.add(submitButton);
						coneFrame.add(conePanel);
						coneFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double radius = Double.parseDouble(radiusField.getText());
								double height = Double.parseDouble(heightField.getText());
								Cone coneObj = new Cone(radius, height);
								coneObj.caculateVolume(radius, height);
								JLabel areaLabel = new JLabel("Results for Volume = " + String.valueOf(coneObj.getVolume()));
								conePanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Cone.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									conePanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								conePanel.revalidate();
								conePanel.repaint();
							}
						});
						break;
					case "Cylinder":
						JFrame cylinderFrame = new JFrame("Caculate Volume of Cylinder ");
						radiusField = new JTextField(20);
						submitButton = new JButton("Submit");
						radiusLabel = new JLabel("Radius:");
						heightField = new JTextField(20);
						heightLabel = new JLabel("Height:");
						JPanel cylinderPanel = new JPanel();
						cylinderFrame.setSize(650,300);
						cylinderPanel.add(radiusLabel);
						cylinderPanel.add(radiusField);
						cylinderPanel.add(heightLabel);
						cylinderPanel.add(heightField);
						cylinderPanel.add(submitButton);
						cylinderFrame.add(cylinderPanel);
						cylinderFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double radius = Double.parseDouble(radiusField.getText());
								double height = Double.parseDouble(heightField.getText());
								Cylinder clyinderObj = new Cylinder(radius, height);
								clyinderObj.caculateVolume(radius, height);
								JLabel areaLabel = new JLabel("Results for Volume = " + String.valueOf(clyinderObj.getVolume()));
								cylinderPanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Cylinder.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									cylinderPanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								cylinderPanel.revalidate();
								cylinderPanel.repaint();
							}
						});
						break;
					case "Torus":
						JFrame torusFrame = new JFrame("Caculate Volume of Torus ");
						JTextField smallRField = new JTextField(20);
						submitButton = new JButton("Submit");
						JLabel smallRLabel = new JLabel("Small Radius:");
						JTextField largeRField = new JTextField(20);
						JLabel largeRLabel = new JLabel("Large Radius:");
						JPanel torusPanel = new JPanel();
						torusFrame.setSize(650,300);
						torusPanel.add(smallRLabel);
						torusPanel.add(smallRField);
						torusPanel.add(largeRLabel);
						torusPanel.add(largeRField);
						torusPanel.add(submitButton);
						torusFrame.add(torusPanel);
						torusFrame.setVisible(true);
						submitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								double sRadius = Double.parseDouble(smallRField.getText());
								double lRadius = Double.parseDouble(largeRField.getText());
								Torus torusObj = new Torus(sRadius, lRadius);
								torusObj.caculateVolume(sRadius, lRadius);
								JLabel areaLabel = new JLabel("Results for Volume = " + String.valueOf(torusObj.getVolume()));
								torusPanel.add(areaLabel);	
								try{
									BufferedImage image = ImageIO.read(new File("Torus.png"));
									JLabel label = new JLabel(new ImageIcon(image));
									torusPanel.add(label);
								}
								catch(IOException error) {
									error.printStackTrace();
								}
								torusPanel.revalidate();
								torusPanel.repaint();
							}
						});
						break;
					}
			  }
			};
			circleR.addActionListener(sliceActionListener);
			rectR.addActionListener(sliceActionListener);
			squareR.addActionListener(sliceActionListener);
			triangleR.addActionListener(sliceActionListener);
			sphereR.addActionListener(sliceActionListener);
			cubeR.addActionListener(sliceActionListener);
			coneR.addActionListener(sliceActionListener);
			cylinderR.addActionListener(sliceActionListener);
			torusR.addActionListener(sliceActionListener);
	}
}