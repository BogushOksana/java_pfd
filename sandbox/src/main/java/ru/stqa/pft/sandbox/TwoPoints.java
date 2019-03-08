package ru.stqa.pft.sandbox;

public class TwoPoints {

  public static void main(String args[]) {
    Point p1 = new Point(5, 20);
    Point p2 = new Point(8, 20);


    System.out.println("x = " + p1.x + " y = " + p1.y);
    System.out.println("x = " + p2.x + " y = " + p2.y);
    System.out.println("Расстояние между двумя точками = " + p1.distance(p2));
  }
}


