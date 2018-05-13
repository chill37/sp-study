package test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class product_prefer {

	public static void main(String[] args) {
		
		Product []product = {
				new Product("pen1", 2000, new int[] {1,2,3,2,3,1,3}, 0.0),
				new Product("pen2", 5000, new int[] {9,5,3,2,3,7,3}, 0.0),
				new Product("pen3", 4000, new int[] {2,7,3,2,3,4,3}, 0.0)
				};

		avg(product);
		iwant(product, 4500);
	}

	private static void iwant(Product[] product, int money) {
		
		ArrayList<Double> al=avg(product);
		double high=0;
		int highrow=-1;
		
		for(int i=0; i<product.length; i++) {
			if(product[i].getPrice() > 4500) continue;
			int []arr=product[i].getScore();
			Double dd=Arrays.stream(arr).average().getAsDouble();
			Double av=Math.round(dd*100)/100.0;
			if(av>high) {
				high=av;
				highrow=i;
			}
			
			
		}
		String name=product[highrow].getName();
		System.out.println(name);
	}

	private static ArrayList<Double> avg(Product[] product) {
		ArrayList<Double> al=new ArrayList<Double>();
		
		for(int i=0; i<product.length; i++) {
			int []arr=product[i].getScore();
			Double dd=Arrays.stream(arr).average().getAsDouble();
			Double av=Math.round(dd*100)/100.0;
			al.add(av);
			System.out.println(av);
		}
		return al;
		
		
	}

}

class Product{
	String name;
	int price;
	int []score;
	double satisfaction;
	public Product(String name, int price, int[] score, double satisfaction) {
		super();
		this.name = name;
		this.price = price;
		this.score = score;
		this.satisfaction = satisfaction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int[] getScore() {
		return score;
	}
	public void setScore(int[] score) {
		this.score = score;
	}
	public double getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(double satisfaction) {
		this.satisfaction = satisfaction;
	}
	
}