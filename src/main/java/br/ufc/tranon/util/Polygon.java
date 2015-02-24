package br.ufc.tranon.util;

import java.util.*;
import java.awt.Graphics;
import java.awt.geom.Line2D;

public class Polygon {
	
   private Vector<Point> points = new Vector<Point>();

   public Polygon() { }

   // append a vertex 
   public void add(Point p) {
      points.addElement(p);
   }

   // return the Point at the ith vertex 
   public Point get(int i) {
      return (Point) points.elementAt(i);
   }

   // insert a new vertex before the ith vertex 
   public void insert(int i, Point p) {
      points.insertElementAt(p, i);
   }

   // remove the ith vertex 
   public void remove(int i) {
      points.removeElementAt(i);
   }

   // remove all vertices 
   public void removeAll() {
      points.removeAllElements();
   }

   // set a different value for the ith vertex 
   public void set(int i, Point p) {
      points.setElementAt(p, i);
   }

   // copy from another polygon 
   public void set(Polygon p) {
      removeAll();
      for (int i = 0; i < p.size(); i++)
         points.addElement(p.get(i));
   }

   // return number of vertices 
   public int size() {
      return points.size();
   }

   // get x coord of ith vertex 
   public double x(int i) {
      Point p = get(i);
      return p.x();
   }

   // get y coord of ith vertex 
   public double y(int i) {
      Point p = get(i);
      return p.y();
   }

   // draw polygon outline 
   public void draw(Graphics g) {
      int[] xs = new int[size()+1];
      int[] ys = new int[size()+1];
      for (int i = 0; i < size(); i++) {
         xs[i] = (int) x(i);
         ys[i] = -(int) y(i);
      }
      xs[size()] = (int) x(0);
      ys[size()] = -(int) y(0);
      g.drawPolygon(xs, ys, size()+1);
   }

   // fill polygon region 
   public void fill(Graphics g) {
      int[] xs = new int[size()+1];
      int[] ys = new int[size()+1];
      for (int i = 0; i < size(); i++) {
         xs[i] = (int) x(i);
         ys[i] = -(int) y(i);
      }
      xs[size()] = (int) x(0);
      ys[size()] = -(int) y(0);
      g.fillPolygon(xs, ys, size()+1);
   }

   public void move(double dx, double dy) {
      for (int i = 0; i < size(); i++) {
         Point p = get(i);
         points.setElementAt(new Point(p.x()+dx, p.y()+dy), i);
      }
   }

   // return polygon's area 
   public double area() {
      double a = 0.0;
      for (int i = 0; i < size(); i++) {
         int j = (i+1)%size();
         a += (x(j)-x(i))*(y(j)+y(i));
      }
      return a/2;
   }

   // convert to string
   public String toString() {
      StringBuffer s = new StringBuffer("[ ");
      for (int i = 0; i < size(); i++)
         s.append(get(i)).append("\n");
      s.append("]");
      return s.toString();
   }

   // intersect with another polygon 
   public void intersect(Polygon p) {
      for (int i = 0; i < p.size(); i++) {
         int j = (i+1)%p.size();
         HalfPlane h = new HalfPlane(p.get(i), p.get(j));
         intersect(h);
      }
   }

   // does it contain the specified point? 
   public boolean contains(Point p) {
      for (int i = 0; i < size(); i++) {
         int j = (i+1)%size();
         HalfPlane h = new HalfPlane(get(i), get(j));
         if (h.eval(p) < 0)
            return false;
      }
      return true;
   }

   // intersect with a half-plane
   private void intersect(HalfPlane h) {
      Polygon p = new Polygon();
      double val = h.eval(get(0));
      for (int i = 1; i < size()+1; i++) {
         Point pt = get(i%size());
         double v = h.eval(pt);
         if (v > 0 && val < 0)
            p.add(h.intersect(new HalfPlane(get(i-1), pt)));
         if (v >= 0)
            p.add(pt);
         if (v < 0 && val > 0)
            p.add(h.intersect(new HalfPlane(get(i-1), pt)));
         val = v;
      }
      set(p);
   }
   
   public void midpoint(Point p1, Point p2, Point p3) {
	   Line2D line1 = new Line2D.Double(100, 100, 200, 200);
	   Line2D line2 = new Line2D.Double(150, 150, 150, 200);
	   
	   
	   boolean result = line2.intersectsLine(line1);
	   System.out.println(result); // => true
	    
	}
}
