/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Hashtable <T >
{
    
    int size=15;
    LinkedList<T>[] arr= new LinkedList[size];
    public Arraylist<T> list=new Arraylist();
    int count=0;
    
   
    
    public void add(T obj)
    {
        if(search(obj)!=null)
        {
           // System.out.println("main madarchod hoon---------------------------------");
            return;
        }
       
            int val=(obj.hashCode()%size+size)%size;
            if(arr[val]==null)
            
            {
                count++;
                arr[val]=new LinkedList();
            }
            
            arr[val].add(obj);
            list.add(obj);
            
            if((float)count/(float)size>0.75)
            {
                size=size*2;
                LinkedList<T>[] temparr= new LinkedList[size];
                int a=list.i;
                count=0;
                for(int i=0;i<a;i++)
                {
                    T var=list.get(i);
                    int val1=(var.hashCode()%size+size)%size;
                    if(temparr[val1]==null)
                    {
                        count++;
                        temparr[val1]=new LinkedList();
                    }
                    temparr[val1].add(var);
                }
                arr=temparr;
                

            }
        
        
    }
    
    public T search(T obj)
    {
        int val=(obj.hashCode()%size+size)%size;
       // System.out.println(val);
        if(arr[val]!=null)
        {
//            System.out.println("yeh wala null"+obj.toString());
            return (T)arr[val].search(obj);
        }
        else
            return null;
     
    }
    public T search1(T obj)
    {
        int val=(obj.hashCode()%size+size)%size;
//        System.out.println(val+" "+obj.toString());
        if(arr[val]!=null)
        {
//              System.out.println("a");
            return (T)arr[val].search(obj);
        }
        else{
            
//         System.out.println("b");
            return null;
        }
     
    }
    public void print()
    {
        list.print();
    }
    
    
}
