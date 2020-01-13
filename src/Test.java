/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Test {
    public static void main(String[] args) {
    Queue<Integer> q=new Queue();
    for(int i=0;i<25;i++)
    {
        q.add(i);
    }
    for(int i=0;i<10;i++)
    {
        //System.out.println(q.pop());
    }
    Arraylist <Integer> arr =new Arraylist();
    for(int i=0;i<25;i++)
    {
        arr.add(i);
    }
    arr.print();
     for(int i=0;i<25;i++)
    {
        System.out.println(arr.get(i)+" "+arr.i+" "+arr.size);
    }
     for(int i=0;i<10;i++)
    {
        arr.delete(i);
    }
     arr.print();
    for(int i=0;i<15;i++)
    {
        //System.out.println(arr.get(i)+arr.i);
    }
    
    LinkedList<Integer> arr1=new LinkedList();
    for(int i=0;i<25;i++)
    {
        arr1.add(i);
    }
    arr1.print();
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    
     Hashtable<Point> AllPoint = new Hashtable();
        Hashtable<Edge> AllEdge = new Hashtable();
        Hashtable<Triangle> AllTriangle = new Hashtable();
        int globalcounter=1;
        Arraylist<Component> AllComp=new Arraylist();
        
        Point p1=new Point(-1, 0, 0);
        Point p2=new Point(0 ,-1 ,0);
        Point p3=new Point(0,0,3);
        Edge e1=new Edge(p1,p2);
         Edge e2=new Edge(p1,p3); Edge e3=new Edge(p3,p2);
         Triangle t =new Triangle(p1,p2,p3);
        Component c1=new Component(t);
        if(c1.edge.search(t.e1)==null)
        {
            System.out.println("pop");
        }
        else
        {
             System.out.println("popeye");
        }
    
    Hashtable<Integer> arr2=new Hashtable();
    for(int i=0;i<14;i++)
    {
        arr2.add(i);
    }
     for(int i=0;i<arr2.size;i++)
    {
        if(arr2.arr[i]!=null)
        {
            arr2.arr[i].print();
        
        System.out.println(arr2.size+" "+arr2.count+" "+i);
        }
    }
    }
}
