/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piyus
 */
public class LinkedList<T > {
    
    class Node<T> 
    {
        T data;
        Node next;
        public Node(T d)
        {
           data=d;
           next=null;
           
        }
        public Node()
        {
            data=null;
            next=null;
        }
        
    }
    Node start;
    
    public void add(T key)
    {
        Node new1=new Node(key);
        if(start==null)
        {
            start=new1;
            return;
        }
        new1.next=start;
        start=new1;
        return;
    }
    public T search(T key)
    {
        Node temp=start;
        
        while(temp!=null)
        {
            //System.out.println(temp.data.toString()+" "+key.toString()+"ppppppppppppppppppppppppppppppppppp");
            if(temp.data.equals(key))
            {
                //System.out.println(temp.data.toString()+" "+key.toString()+"ppppppppppppppppppppppppppppppppppp");
                //System.out.println("========found===================");
                return (T)temp.data;
            }
            temp=temp.next;
        }
        return null;
    }
    public void print()
    {
         Node temp=start;
        System.out.println("-----------------------");
        while(temp!=null)
        {
            System.out.println(temp.data);
              temp=temp.next;
        }
    }
    
}
