/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Component {
    
    Hashtable<Edge> edge =new Hashtable();
     Hashtable<Triangle> triangle =new Hashtable();
      Hashtable<Point> point =new Hashtable();
      
      public Component (Triangle t)
      {
          edge.add(t.e1);edge.add(t.e2);edge.add(t.e3);
          point.add(t.p1);point.add(t.p2);point.add(t.p3);
          triangle.add(t);
      }
      public void add(Triangle t)
      {
          edge.add(t.e1);edge.add(t.e2);edge.add(t.e3);
          point.add(t.p1);point.add(t.p2);point.add(t.p3);
          triangle.add(t);
      }
      public void print()
      {
          System.out.println("Printing Triangles");
          triangle.print();
          System.out.println("Printing Edges");
          edge.print();
          System.out.println("Printing Point");
          point.print();
      }
      
}
