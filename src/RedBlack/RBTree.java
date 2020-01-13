package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {


    RedBlackNode root=new RedBlackNode();
    
  
    public void insert(T key, E value) 
    {
        if(root.name==null)
            {
                root.b=false;
                root.name=key;
                root.A.add(value);
                root.left=null;
                root.right=null;   
            }
            else
            {
                RedBlackNode root1=root;
                RedBlackNode root2=root;
                //RedBlackNode root3=new RedBlackNode();
                RedBlackNode temp=new RedBlackNode();
                RedBlackNode temp2=new RedBlackNode();
                
                
                while(root1!=null)
                {
                    root2=root1;
                    int a=root1.name.compareTo(key);
                   
                        if(a>0)
                            root1=root1.left;
                        else if(a<0)
                            root1=root1.right;
                        else
                        {
                             root1.A.add(value);
                            return;
                        }
                }
                root1=new RedBlackNode();
                root1.parent=root2;
                
                root1.name=key;
                root1.b=true;
                root1.left=null;
                root1.right=null;
                root1.A.add(value);
                
                if(root2.name.compareTo(key)>0)
                    root2.left=root1;
                else
                    root2.right=root1;
                
                if(root2.parent!=null)
                while(root2.b)
                {
                    //System.out.println("Gupta");
                    //printrb (root);
                    //System.out.println("Piyush");
                     boolean bool=true;
                    
                      if(root2==root2.parent.left)
                        {
                            temp=root2.parent.right;
                            if(temp!=null)
                            {
                                if(temp.b)
                                    {
                                        temp.b=false;
                                        root2.b=false;
                                        root2.parent.b=true;
                                        root1=root2.parent;
                                        root2=root1.parent;
                                        bool=false;
                                    } 
                            }
                             if(bool)
                                    {
                                        if(root2.parent!=null)
                                        {
                                            if(root1==root2.right)
                                            {
                                               /*temp2=root1.left;
                                               root2.parent.left=root1;
                                               root1.left=root2;
                                               root1.parent=root2.parent;
                                               root2.parent=root1;
                                               root2.right=temp2;
                                               root1=root2;
                                               root2=root1.parent;
                                               */
                                               temp=root1.left;
                                               temp2=root1.right;
                                               root2.right=temp;
                                               if(temp!=null)
                                               temp.parent=root2;
                                               root1.right=root2.parent;
                                               root1.left=root2;
                                               root2.parent.left=temp2;
                                               if(temp2!=null)
                                               temp2.parent=root2.parent;
                                              
                                               if(root2.parent.parent!=null)
                                               {
                                                   root1.parent=root2.parent.parent;
                                                   if(root1.parent.name.compareTo(root1.name)>0)
                                                    root1.parent.left=root1;
                                                else
                                                    root1.parent.right=root1;
                                               }
                                               else
                                               {
                                                   System.out.println("piyushDd-----------");
                                                     System.out.println("root "+root.name+" piyush"+key);
                                                   
                                                     System.out.println("root "+root.name);
                                                      root=root1;
                                                   root.parent=null;
                                               }
                                                root2.parent=root1;
                                                
                                                root2=root1.parent;
                                                root1.right.b=true;
                                                root1.b=false;
                                                root1.right.parent=root1;
                                               
                                            }
                                            else if(root2.parent!=null)
                                            {
                                                temp=root2.right;
                                                root2.right=root2.parent;
                                                root2.parent.left=temp;
                                               if(temp!=null)
                                                temp.parent=root2.parent;
                                                
                                               temp2=root2.parent;
                                                if(temp2.parent!=null)
                                                {
                                                    root2.parent=temp2.parent;
                                                         if(root2.parent.name.compareTo(root2.name)>0)
                                                            root2.parent.left=root2;
                                                        else
                                                            root2.parent.right=root2;
                                                }
                                                else
                                                {
                                                   
                                                    System.out.println("piyushDd-----------");
                                                     System.out.println("root "+root.name+" piyush"+key);
                                                   
                                                     System.out.println("root "+root.name);
                                                      root=root2;
                                                    root2.parent=null;
                                                }
                                                root2.right.parent=root2;
                                                root2.b=false;
                                                root2.right.b=true;

                                            }
                                        }
                                    }

                        }
                        else
                        {
                            temp=root1.parent.parent.left;
                           
                            if(temp!=null)
                            {    
                                if(temp.b)
                                    {
                                        temp.b=false;
                                        root2.b=false;
                                        root2.parent.b=true;
                                        root1=root2.parent;
                                        root2=root1.parent;
                                        bool=false;
                                    } 
                            }
                            if(root2!=null)
                                if(root2.parent!=null)
                            if(bool)
                              {
                                        if(root2.parent!=null)
                                        {
                                            if(root1==root2.left)
                                            {
                                               /*temp2=root1.left;
                                               root2.parent.left=root1;
                                               root1.left=root2;
                                               root1.parent=root2.parent;
                                               root2.parent=root1;
                                               root2.right=temp2;
                                               root1=root2;
                                               root2=root1.parent;
                                               */
                                               temp=root1.right;
                                               temp2=root1.left;
                                               root2.left=temp;
                                               if(temp!=null)
                                               temp.parent=root2;
                                               root1.left=root2.parent;
                                               root1.right=root2;
                                               root2.parent.right=temp2;
                                               if(temp2!=null)
                                               temp2.parent=root2.parent;
                                              
                                               if(root2.parent.parent!=null)
                                               {
                                                   root1.parent=root2.parent.parent;
                                                   if(root1.parent.name.compareTo(root1.name)>0)
                                                    root1.parent.left=root1;
                                                else
                                                    root1.parent.right=root1;
                                               }
                                               else
                                               {
                                                   //System.out.println("piyush");
                                                  
                                                    System.out.println("piyushDd-----------");
                                                     System.out.println("root "+root.name+" piyush"+key);
                                                   
                                                     System.out.println("root "+root.name);
                                                      root=root1;
                                                   root.parent=null;
                                               }
                                                root2.parent=root1;
                                                
                                                root2=root1.parent;
                                                root1.left.b=true;
                                                root1.b=false;
                                                root1.left.parent=root1;
                                               
                                            }
                                            else if(root2.parent!=null)
                                            {
                                                temp=root2.left;
                                                root2.left=root2.parent;
                                                root2.parent.right=temp;
                                               if(temp!=null)
                                                temp.parent=root2.parent;
                                                
                                                
                                                temp2=root2.parent;
                                                if(temp2.parent!=null)
                                                {
                                                    root2.parent=temp2.parent;
                                                         if(root2.parent.name.compareTo(root2.name)>0)
                                                            root2.parent.left=root2;
                                                        else
                                                            root2.parent.right=root2;
                                                }
                                                else
                                                {
                                                    System.out.println("piyushDd-----------");
                                                     System.out.println("root "+root.name+" piyush"+key);
                                                   
                                                     System.out.println("root "+root.name);
                                                      root=root2;
                                                    root2.parent=null;
                                                }
                                                root2.left.parent=root2;
                                                root2.b=false;
                                                root2.left.b=true;

                                            }
                                        }
                                    }  

                        } 
                      if(bool)
                        break;
                if(root1.parent==null  )   
                    break;
                else if(root1.parent.parent==null)
                    break;
                
                //root2=root1.parent;
                }
                
            }
            
            root.b=false;
            // System.out.println(" ");
          //printrb (root);
              
            
                
    }
    
/*public void printrb(RedBlackNode<T,E> roota)
{

    if(roota==null)
        return;
    else
    {
        if(roota.b)
            {
                System.out.printf(roota.name +" Red " );
                if(roota.left!=null)
                    System.out.printf(roota.left.name +" left " );
                if(roota.right!=null)
                    System.out.printf(roota.right.name +" right " );
                if(roota.parent!=null)
                    System.out.printf(roota.parent.name +" parent " );
                System.out.println();
            }
        
        else
          
        {   
            System.out.printf(roota.name +" Black ");
             if(roota.left!=null)
                    System.out.printf(roota.left.name +" left " );
                if(roota.right!=null)
                    System.out.printf(roota.right.name +" right " );
                if(roota.parent!=null)
                    System.out.printf(roota.parent.name +" parent " );
                  System.out.println();
             
        }  
        
        printrb(roota.left);
        printrb(roota.right);
    }

}
*/
   
    public RedBlackNode<T, E> search(T key) 
    {
        RedBlackNode root1=new RedBlackNode();
        RedBlackNode root2=new RedBlackNode();
        root1=root;
       
        if(root==null)
            return root2;
        else
        {
            while(root1!=null)
                {
                    T k1=(T)root1.name;
                    if(k1!=null)
                    {
                        int a=k1.compareTo(key);
                        
                        if(a>0)
                            root1=root1.left;
                        else if(a<0)
                            root1=root1.right;
                        else
                         return root1;
                    }
                }
            return root2;
        }
    }
}