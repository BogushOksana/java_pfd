package ru.stqa.pft.sandbox;

        import org.testng.Assert;
        import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance (){
    Point p1 = new Point(1000000000, 1000000000);
    Point p2 = new Point(-1000000000, -1000000000);
    Assert.assertEquals(p1.distance(p2), 2.82842712474619E9);
  }
  {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
  {
    Point p1 = new Point(5, 6);
    Point p2 = new Point(8, 6);
    Assert.assertEquals(p1.distance(p2), 3.0);
  }
}
