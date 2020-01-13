/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Point implements PointInterface{

    float x,y,z;
    
    float arr[]=new float[3];
     Arraylist<Triangle> tri =new Arraylist();
     Hashtable<Point> pointneigh=new Hashtable();
     Hashtable<Edge> edgeneigh=new Hashtable();
    Point(float a, float b, float c)
    {
        x=a;
        y=b;
        z=c;
        arr[0]=a;
        arr[1]=b;
        arr[2]=c;
    }
    
    
    
    
    @Override
    public float getX() 
    {
         return x;    
    }

    @Override
    public float getY() 
    {
        return y;    
    }

    @Override
    public float getZ() 
    {
        return z;
    }

    @Override
    public float[] getXYZcoordinate() 
    {
        return arr;
    }
    public boolean equals (Object p1)
    {
        Point p=(Point)p1;
        if(p.x==x && p.y==y && p.z==z)
            return true;
        
        else
            return false;
    }
    public int comparator(Point p)
    {
        if(x>p.x)
            return 1;
        else if(x<p.x)
            return -1;
        
         if(y>p.y)
            return 1;
        else if(y<p.y)
            return -1;
         
          if(z>p.z)
            return 1;
        else if(z<p.z)
            return -1;
        
           return 0;
    }
    
    public String toString()
    {
        String s=x+"," +y+","+z;
        return s;
    }
    public int hashCode()
    {
        int a=Float.hashCode(x)+Float.hashCode(y)+Float.hashCode(z);
        return a;
    }
    
    
}
