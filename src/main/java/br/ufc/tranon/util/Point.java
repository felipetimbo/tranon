package br.ufc.tranon.util;

public class Point {
	   double x;
	   double y;

	   public Point(double x, double y) {
	      this.x = x;
	      this.y = y;
	   }

	   public String toString() { // return "{x,y}" 
	      return "{"+x+","+y+"}";
	   }

	   public double x() { // return x coordinate 
	      return x;
	   }

	   public double y() { // return y coordinate 
	      return y;
	   }
	}
