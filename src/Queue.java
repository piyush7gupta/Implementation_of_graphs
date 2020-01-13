/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class Queue <T>
{
    int capacity=10;
    int size=0;
    
    int index=0;
    int first=0;
    Object[] arr=new Object[capacity];
    
    public void add(T obj)
    {
        if(size==capacity)
        {
            capacity=2*capacity;
             Object[] arr1=new Object[capacity];
             
             int temp = first % (capacity/2);
            int k =0;
             for(int j=temp;j<capacity/2;j++)
            {
                arr1[k++]=arr[j];
            }
             for(int j=0; j<temp; j++)
			{
				arr1[k++]=arr[j];
			}
			
             arr=arr1;
             first=0;
             index=capacity/2;
        }
         
        arr[index%capacity]=obj;
        index++;
        size++;
        
        
        

    }
    public T pop()
    {
        T val=(T)arr[first%capacity];
        arr[first%capacity]=null;
        first++;
        size--;
        return val;
        
    }
}
