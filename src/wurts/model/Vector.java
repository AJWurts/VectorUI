package wurts.model;

import java.lang.Math;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vector {
	double i, j, k;
	
	private final int DECIMAL_PRECISION = 3;

	public Vector(double i, double j, double k) {
		this.i = i;
		this.j = j;
		this.k = k;
	}

	public Vector(double i, double j) {
		this.i = i;
		this.j = j;
	}
	
	public Vector add(Vector v) {
		return new Vector(i + v.i, j + v.j, k + v.k);
	}
	
	public Vector sub(Vector v) {
		return new Vector(i - v.i, j - v.j, k - v.k);
	}
	
	public Vector mul(Double a) {
		return new Vector(i * a, j * a, k * a);
	}
	
	public Vector div(Double a) {
		return new Vector(i / a, j / a, k / a);
	}
	
	public double dot(Vector v) {
		return i * v.i + j * v.j + k * v.k;
	}
	
	public Vector cross(Vector v) {
		return new Vector(j * v.k - k * v.j,
                -(i * v.k - v.i * k),
                i * v.j - v.i * j);
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2));
	}
	
	public double project(Vector v) {
		return this.dot(v.div(v.magnitude()));
	}
	
	public Vector toUnit() {
		return this.div(magnitude());
	}
	
	public String toString() {
		return "<" + format(i)+ "," + format(j) + "," + format(k) + ">";
	}
	
	private String format(double num) {
		return String.format("%." + String.valueOf(DECIMAL_PRECISION) + "f", num);
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}
	
	
	public StringProperty getStringProperty() {
		return new SimpleStringProperty(toString());
	}
}


class Examples {
	public Examples(){}
	
	public static void main(String[] args) {
		Vector v = new Vector(10, 10, 10);
		Vector v1 = new Vector(5, 5, 5);
		Vector v2 = new Vector(1, 3, 5);
		System.out.println("Magnitude: \n\tActual: " + v.magnitude() + "\n\tExpected: 17.3205");
		System.out.println("Addition: \n\tActual: " + v.add(v1) + "\n\tExpected: <15,15,15>");
		System.out.println("Subtraction: \n\tActual: " + v.sub(v1) + "\n\tExpected: <5,5,5>");
		System.out.println("Division: \n\tActual: " + v.div(5d) + "\n\tExpected: <2,2,2>");
		System.out.println("Magnitude: \n\tActual: " + v.mul(5d) + "\n\tExpected: <50,50,50>");
		System.out.println("Dot Product: \n\tActual: " + v.dot(v1) + "\n\tExpected: 150");
		System.out.println("Cross Product: \n\tActual: " + v.cross(v2) + "\n\tExpected: <20,-40,20>");
		System.out.println("Project: \n\tActual: " + v.project(v1) + "\n\tExpected: 17.3205");
	}
}
