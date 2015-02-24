package br.ufc.tranon.util;

public class HalfPlane {
	   double a;
	   double b;
	   double c;

	   public HalfPlane(Point p, Point q) {
	      a = p.y() - q.y();
	      b = q.x() - p.x();
	      c = -a*p.x() + -b*p.y();
	   }

	   // evaluate a linear function, returning positive values for points
	   // to the left of the line going from p to q
	   public double eval(Point p) {
	      return a*p.x() + b*p.y() + c;
	   }

	   public Point intersect(HalfPlane h) {
	      double den = a*h.b-h.a*b;
	      return new Point((h.c*b-c*h.b)/den,(c*h.a-h.c*a)/den);
	   }
	}
