package PriorityQueue;

import java.util.List;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {


    //public ArrayList A= new ArrayList<T>();
    //ArrayList<Integer> A1= new ArrayList();
    
    public ArrayList<T> B=new ArrayList();
    public int size1=0;
    
    public void insert1(T element)
    {
        B.add(element);
        size1++;
        
        int a=size1-1;
        int b=(a-1)/2;
        while(b>=0)
        {
             T s1=B.get(b);
            
           
            T s2=(T)B.get(a);
            if(s1.compareTo(s2)<0)
            {
                B.set(b,s2);
                B.set(a,s1);
                
                //A1.set(b,A1.get(a));
                //A1.set(a, q);
                int x=b;
                b=(b-1)/2;
                a=x;
            }
            else
            break;
        }
    }
    public T extractMax1 ()
    {
        int a=size1;
        if(a==0)
        return null;
        T value=(T)B.get(0);
        
         a--;
        //T temp=(T)A2.get(a).first;
         //A.set(0, temp);
         T s1 =B.get(a);
         B.set(0,s1);
         //A1.set(0,A1.get(a));
         B.remove(a);
         //A1.remove(a);
        //A.remove(a);
        size1--;
        
           int b=0;
        
        if(b<size1)
        {
             s1=B.get(b);
             
             
            while(s1!=null)
            {
                T s=B.get(b);
                
                T s2 =null,s3=null;
                int k=2,l=2;
                if(2*b+1<size1)
                {
                   // p2=A2.get(2*b+1);
                    //a2=p2.second;
                     s1=(T)B.get(2*b+1);
                    k=s.compareTo(s1);          
                }
                 if(2*b+2<size1)
                {
                   // p3=A2.get(2*b+2);
                   // a3=p3.second;
                    s2=(T)B.get(2*b+2);
                    l=s.compareTo(s2);          
                }
                
                 if(k>0 && l>0)
                 {
                     break;
                 }
                else
                 {
                     if(s2!=null)
                     {

                       
                        // System.out.println(b1);
                         if(s1.compareTo(s2)>0 )
                         {
                             //T swap=(T)(A2.get(2*b+1).first);
                             //A.set(2*b+1, A.get(b));
                             //A.set(b, swap);

                             //int swap1=(A2.get(2*b+1).second);
                             T swap2=s1;
                             B.set(2*b+1, s);
                             B.set(b, swap2);

                             //A1.set(2*b+1, A1.get(b));
                             //A1.set(b, swap1);
                             b=2*b+1;
                         }
                         else 
                         {
                             //T swap=(T)(A2.get(2*b+2).first);
                             //A.set(2*b+2, A.get(b));
                             //A.set(b, swap);

                             //int swap2=(A2.get(2*b+2).second);
                             T swap3=s2;
                             B.set(2*b+2, s);
                             B.set(b, swap3);

                             //A1.set(2*b+2, A1.get(b));
                             //A1.set(b, swap2);
                             b=2*b+2;

                         }
                         


                     }
                     else
                     {
                              //T swap=(T)(A2.get(2*b+1).first);
                             //A.set(2*b+1, A.get(b));
                             //A.set(b, swap);

                             //int swap1=(A2.get(2*b+1).second);
                             T swap2=s1;
                             B.set(2*b+1, s);
                             B.set(b, swap2);

                             //A1.set(2*b+1, A1.get(b));
                             //A1.set(b, swap1);
                             b=2*b+1;
                     } 

                 }



            }
        }
        
        /*for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(sa.getName()+" ");
        }
                System.out.println("piyush");
        for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(A1.get(i)+" ");
        }
        System.out.println();*/
        return value;
        
        
    }
    
    
    ArrayList<Pair> A2=new ArrayList();
    int val=0;
    int size=0;
    @Override
    public void insert(T element) {
        
        val++;
        Pair p=new Pair(element,val);
        
        //A.add(element);
        //A1.add(val);
        A2.add(p);
        size++;
        int a=size-1;
        int b=(a-1)/2;
        while(b>=0)
        {
             Pair p1=A2.get(b);
            T s1 =(T)p1.first;
            Pair p2=A2.get(a);
            T s2=(T)p2.first;
            if(s1.compareTo(s2)<0)
            {
                //T temp=s1;
                //A.set(b,s2);
                //A.set(a, temp);
                //int q=A2.get(b).second;
               // int q1=A2.get(a).second;
                
               
                A2.set(b,p2);
                A2.set(a,p1);
                
                //A1.set(b,A1.get(a));
                //A1.set(a, q);
                int x=b;
                b=(b-1)/2;
                a=x;
            }
            else
            break;
        }
        /*
        for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(sa.getName()+" ");
        }
        System.out.println();
        */
        
    }

    @Override
    public T extractMax() {
        /* for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(sa.getName()+" ");
        }
        System.out.println();
         for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(A1.get(i)+" ");
        }
        System.out.println();
        */
        
         int a=size;
        if(a==0)
        return null;
        T value=(T)A2.get(0).first;
       
        a--;
        //T temp=(T)A2.get(a).first;
         //A.set(0, temp);
         Pair p1=A2.get(a);
         A2.set(0,p1);
         //A1.set(0,A1.get(a));
         A2.remove(a);
         //A1.remove(a);
        //A.remove(a);
        size--;
        
        
        int b=0;
        
        if(b<size)
        {
             p1=A2.get(b);
             Pair p2=null,p3=null;
             
            while(p1!=null)
            {
                T s=(T)p1.first;
                T s1=null;
                T s2=null;
                int a1=0,a2=0,a3=0;
                a1=p1.second;
                int k=2,l=2;
                if(2*b+1<size)
                {
                    p2=A2.get(2*b+1);
                    a2=p2.second;
                     s1=(T)p2.first;
                    k=s.compareTo(s1);          
                }
                 if(2*b+2<size)
                {
                    p3=A2.get(2*b+2);
                    a3=p3.second;
                     s2=(T)p3.first;
                    l=s.compareTo(s2);          
                }
                // System.out.println(k+" "+l+" "+size);
                if(k==0)
                {
                    if(a1<a2)
                        k++;
                }
                if(l==0)
                {
                      if(a1<a3)
                        l++;
                }
                 if(k>0 && l>0)
                 {
                     break;
                 }
                else
                 {
                     if(s2!=null)
                     {

                        int b1=0;
                         if(s1.compareTo(s2)==0)
                         {
                                b1++;
                            if(a2<a3)
                            {
                                b1+=10;
                            }
                            else
                            {
                                b1+=100;
                            }
                         }
                        // System.out.println(b1);
                         if(s1.compareTo(s2)>0 || b1==11)
                         {
                             //T swap=(T)(A2.get(2*b+1).first);
                             //A.set(2*b+1, A.get(b));
                             //A.set(b, swap);

                             //int swap1=(A2.get(2*b+1).second);
                             Pair swap2=p2;
                             A2.set(2*b+1, p1);
                             A2.set(b, swap2);

                             //A1.set(2*b+1, A1.get(b));
                             //A1.set(b, swap1);
                             b=2*b+1;
                         }
                         else if(s1.compareTo(s2)<0 || b1==101)
                         {
                             //T swap=(T)(A2.get(2*b+2).first);
                             //A.set(2*b+2, A.get(b));
                             //A.set(b, swap);

                             //int swap2=(A2.get(2*b+2).second);
                             Pair swap3=p3;
                             A2.set(2*b+2, p1);
                             A2.set(b, swap3);

                             //A1.set(2*b+2, A1.get(b));
                             //A1.set(b, swap2);
                             b=2*b+2;

                         }


                     }
                     else
                     {
                              //T swap=(T)(A2.get(2*b+1).first);
                             //A.set(2*b+1, A.get(b));
                             //A.set(b, swap);

                             //int swap1=(A2.get(2*b+1).second);
                             Pair swap2=p2;
                             A2.set(2*b+1, p1);
                             A2.set(b, swap2);

                             b=2*b+1;
                     } 

                 }



            }
        }
        
        /*for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(sa.getName()+" ");
        }
                System.out.println("piyush");
        for(int i=0;i<A.size();i++)
        {
            Student sa=(Student)A.get(i);
           System.out.print(A1.get(i)+" ");
        }
        System.out.println();*/
        return value;
    }

}