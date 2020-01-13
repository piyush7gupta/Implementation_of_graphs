/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Edge implements EdgeInterface{

    
    Point[] arr=new Point[2];
    Arraylist<Triangle> tri =new Arraylist();
    Point p1,p2;
    
    Edge(Point p1,Point p2)
    {
        this.p1=p1;this.p2=p2;
        arr[0]=p1;arr[1]=p2;
        
    }
    @Override
    public PointInterface[] edgeEndPoints() {
        return arr;
    }
    @Override
    public boolean equals(Object e1)
    {
        Edge tempedge=(Edge)e1;
        if(p1.equals(tempedge.p1) && p2.equals(tempedge.p2))
        return true;
        if(p2.equals(tempedge.p1) && p1.equals(tempedge.p2))
        return true;
     
        
      return false;
    }
    @Override
    public String toString()
    {
        String s="["+p1.toString()+"],[" +p2.toString()+"] "+tri.i;
        return s;
    }
    @Override
    public int hashCode()
    {
        return p1.hashCode()+p2.hashCode();
    }
    
    public float distance1()
        {
            
            float x=p1.getX()-p2.getX();
            float y=p1.getY()-p2.getY();
               float z=p1.getZ()-p2.getZ();
            x=x*x;
            y=y*y;
            z=z*z;
            float dis=x+y+z;
            return dis;
        }
     
    
}
