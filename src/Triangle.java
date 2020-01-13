/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Triangle implements TriangleInterface {
    
    Point[] arr=new Point[3];
    Edge[] ed =new Edge[3];
    Point p1,p2,p3;
    Edge e1,e2,e3;
    int counter=0;
    boolean marked=false;
    Arraylist<Triangle> neigh=new Arraylist();
    Hashtable<Triangle> exneigh=new Hashtable();
    public Triangle (Point p1,Point p2,Point p3)
    {
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
       e1=new Edge(p1,p2);
       e2=new Edge(p3,p2);
       e3=new Edge(p1,p3);
       arr[0]=p1;
       arr[1]=p2;
       arr[2]=p3;
       ed[0]=e1;
       ed[1]=e2;
       ed[2]=e3;
    }

    @Override
    public PointInterface[] triangle_coord() {
        return arr;
    }
    @Override
    public boolean equals(Object t1)
    {
        Triangle t=(Triangle)t1;
        for(int i=0;i<3;i++)
        {
             if(t.arr[(i%3)].equals(p1) && t.arr[(i+1)%3].equals(p2) && t.arr[(i+2)%3].equals(p3) )
                 return true;
             
             if(t.arr[(i%3)].equals(p1) && t.arr[(i+2)%3].equals(p2) && t.arr[(i+1)%3].equals(p3) )
                 return true;
        }
        return false;
    }
    public int hashCode()
    {
        return p1.hashCode()+p2.hashCode()+p3.hashCode();
    }
    
    @Override
    public String toString()
    {
         String s="["+p1.toString() +"]["+p2.toString()+"]["+p3.toString()+"]"+" "+counter;
        return s;
    }
    
    
}
