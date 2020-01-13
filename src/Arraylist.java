/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Arraylist<T> {
    
    Object[] arr;
    int size=0;
    int i=0;
    Arraylist()
    {
        size=0;i=0;
        arr=new Object[0];
    }
    void add(T obj)
    {
        if(i==arr.length)
        {
            Object arr1[];
            if(i==0)
                arr1=new Object[1];
            else
                arr1=new Object[2*i];
            
            for(int j=0;j<i;j++)
            {
                arr1[j]=arr[j];
            }
           
            arr=arr1;
            
        }
        
        arr[i]=obj;
        i++;
        
    }
    
    T get(int a)
    {
        return (T)arr[a];
    }
    void delete(int a)
            
    {
        for(int j=a;j<i-1;j++)
        {
            arr[j]=arr[j+1];
        }
        arr[i-1]=null;
        i--;
        return ;
    }
    void print()
    {
        System.out.println("----------------------");
        for(int k=0;k<i;k++)
        {
            System.out.println(arr[k]);
        }
    }
    
}
