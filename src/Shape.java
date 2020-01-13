

public class Shape implements ShapeInterface
{
        Hashtable<Point> AllPoint = new Hashtable();
        Hashtable<Edge> AllEdge = new Hashtable();
        Hashtable<Triangle> AllTriangle = new Hashtable();
        int globalcounter=1;
        Arraylist<Component> AllComp=new Arraylist();
        boolean component_done=false;
        
        public void merge_sort (Triangle[] A, int left ,int right)
        {
            if(left<right)
        {
            //System.out.println("-----------------------------------------");
            int a=left+right;
            a=a/2;
         
            merge_sort(A ,left,a );
            merge_sort(A,a+1,right);

             int s1=a+1-left;
             int s2=right-a;
             
             Triangle[] L=new Triangle[s1];
             Triangle[] R=new Triangle[s2];
             for(int i=left;i<a+1;i++)
            {
                L[i-left]=A[i];
            }
             for(int i=a+1;i<right+1;i++)
            {
                R[i-a-1]=A[i];
            }
            int i=0,j=0,k=left;
             boolean b= false;
              while(i<s1 && j<s2)
             {
                 Triangle j1=L[i];
                 Triangle j2=R[j];
                 
                 if(j1.counter<=j2.counter)
                 {
                    A[k]=j1;
                    i++;
                    k++;
                 }
                 else
                 {
                    A[k]=j2;
                    j++;
                    k++;
                 }
                 
                        
             }
              while(i<s1)
             {
                  A[k]=L[i];
                     i++;
                     k++;
             }
             while(j<s2)
             {
                 A[k]=R[j];
                     j++;
                     k++;
             }
            
            
            
            return;
        }
    }
        
         public void merge_sort_edge (Edge[] A, int left,int right)
         {
             if(left<right)
        {
            //System.out.println("-----------------------------------------");
            int a=left+right;
            a=a/2;
         
            merge_sort_edge(A ,left,a );
            merge_sort_edge(A,a+1,right);

             int s1=a+1-left;
             int s2=right-a;
             
             Edge[] L=new Edge[s1];
             Edge[] R=new Edge[s2];
             for(int i=left;i<a+1;i++)
            {
                L[i-left]=A[i];
            }
             for(int i=a+1;i<right+1;i++)
            {
                R[i-a-1]=A[i];
            }
            int i=0,j=0,k=left;
             boolean b= false;
              while(i<s1 && j<s2)
             {
                 Edge j1=L[i];
                 Edge j2=R[j];
                 
                 if(distance_edge(j1)<=distance_edge(j2))
                 {
                    A[k]=j1;
                    i++;
                    k++;
                 }
                 else
                 {
                    A[k]=j2;
                    j++;
                    k++;
                 }
                 
                        
             }
              while(i<s1)
             {
                  A[k]=L[i];
                     i++;
                     k++;
             }
             while(j<s2)
             {
                 A[k]=R[j];
                     j++;
                     k++;
             }
            
            
            
            return;
        }
         }
        
         public void merge_sort_point(Point[] A, int left, int right)
         {
              if(left<right)
        {
            //System.out.println("-----------------------------------------");
            int a=left+right;
            a=a/2;
         
            merge_sort_point(A ,left,a );
            merge_sort_point(A,a+1,right);

             int s1=a+1-left;
             int s2=right-a;
             
             Point[] L=new Point[s1];
             Point[] R=new Point[s2];
             for(int i=left;i<a+1;i++)
            {
                L[i-left]=A[i];
            }
             for(int i=a+1;i<right+1;i++)
            {
                R[i-a-1]=A[i];
            }
            int i=0,j=0,k=left;
             boolean b= false;
              while(i<s1 && j<s2)
             {
                 Point j1=L[i];
                 Point j2=R[j];
                 
                 if(j1.comparator(j2)<0)
                 {
                    A[k]=j1;
                    i++;
                    k++;
                 }
                 else
                 {
                    A[k]=j2;
                    j++;
                    k++;
                 }
                 
                        
             }
              while(i<s1)
             {
                  A[k]=L[i];
                     i++;
                     k++;
             }
             while(j<s2)
             {
                 A[k]=R[j];
                     j++;
                     k++;
             }
            
            
            
            return;
        }
         }
         
         public Triangle traverse(Component c, Triangle t1)
         {
            Triangle b=c.triangle.search(t1);
             if(b==null)
                 return null;
             b.marked=true;
            
             Triangle t=b;
             Queue<Pair> q=new Queue();
             Pair p=new Pair(b,0);
             q.add(p);

             while(q.size!=0)
             {
                 Pair temp=q.pop();
                  t=temp.t;
                  float[] a1={t.p1.x,t.p1.y,t.p1.z,t.p2.x,t.p2.y,t.p2.z,t.p3.x,t.p3.y,t.p3.z};
                  Triangle[] arr=(Triangle[])NEIGHBORS_OF_TRIANGLE(a1);
                  for(int i=0;i<arr.length;i++)
                  {
                      if(!arr[i].marked)
                      {
                      Pair temp2=new Pair(arr[i],temp.val+1);
                      arr[i].marked=true;
                      q.add(temp2);
                      }
                  }
                  
             }
             for(int i=0;i<c.triangle.list.i;i++)
             {
                 c.triangle.list.get(i).marked=false;
             }
             return t;
         }
         
         public int traverse1(Component c, Triangle t1)
         {
            
             
             Triangle b=c.triangle.search(t1);
             if(b==null)
                 return 0;
             b.marked=true;
              Queue<Pair> q=new Queue();
             Pair p=new Pair(b,0);
            
             Triangle t=b;
             q.add(p);
             //t1.marked=true;
           
            // Triangle t=null;
              Pair temp=p;
             while(q.size!=0)
             {
                  temp=q.pop();
                  t=temp.t;
                  float[] a1={t.p1.x,t.p1.y,t.p1.z,t.p2.x,t.p2.y,t.p2.z,t.p3.x,t.p3.y,t.p3.z};
                  Triangle[] arr=(Triangle[])NEIGHBORS_OF_TRIANGLE(a1);
                  for(int i=0;i<arr.length;i++)
                  {
                      if(!arr[i].marked)
                      {
                        Pair temp2=new Pair(arr[i],temp.val+1);
                        arr[i].marked=true;
                        q.add(temp2);
                      }
                  }
                  
             }
              for(int i=0;i<c.triangle.list.i;i++)
             {
                 c.triangle.list.get(i).marked=false;
             }
              return temp.val;
             
             //return t;
         }
        
        public float distance(Point p1,Point p2)
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
        public float distance_edge(Edge e1)
        {
            return distance(e1.p1,e1.p2);
        }
        public float square(float f)
        {
            return f*f;
        }
         public boolean extended_neighbor(Triangle t1, Triangle t2)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(t1.arr[i].equals(t2.arr[j]))
                        return true;
                }
            }
            return false;
        }
        
        public boolean neighbour(Triangle t1, Triangle t2)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(t1.ed[i].equals(t2.ed[j]))
                        return true;
                }
            }
            return false;
            
        }

       public boolean ADD_TRIANGLE(float [] triangle_coord)
       {
           Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
           Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
           Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
           Triangle t=new Triangle(p1,p2,p3);
          
         //  System.out.println("piyush");
           if(AllTriangle.search(t)!=null)
               return false;
           
            AllTriangle.add(t);
            
            t.counter=globalcounter-1;
           globalcounter++;
           float x1,x2,x3,y1,y2,y3,z1,z2,z3;
           x1=p1.x;x2=p2.x;x3=p3.x;
           y1=p1.y;y2=p2.y;y3=p3.y;
           z1=p1.z;z2=p2.z;z3=p3.z;
           
           float A=square((triangle_coord[0]-triangle_coord[3]))+square((triangle_coord[1]-triangle_coord[4]))+square((triangle_coord[2]-triangle_coord[5]));
		float B=square((triangle_coord[3]-triangle_coord[6]))+square((triangle_coord[4]-triangle_coord[7]))+square((triangle_coord[5]-triangle_coord[8]));
		float C=square((triangle_coord[0]-triangle_coord[6]))+square((triangle_coord[1]-triangle_coord[7]))+square((triangle_coord[2]-triangle_coord[8]));
	float S=((4*A*B)-(square(C-A-B)))/4;
	float check=S/((A*B)-S);
	if(check<=.000001)
		return false;
           float check1=S/((B*C)-S);
	if(check1<=.000001)
		return false;
	float check2=S/((C*A)-S);
	if(check2<=.000001)
		return false;
           
           Edge e1=new Edge(t.p1,t.p2);
           Edge e2=new Edge(t.p3,t.p2);
           Edge e3=new Edge(t.p1,t.p3);
           
           //System.out.println("Adding Point 1");
           AllPoint.add(t.p1);
          // System.out.println("Adding Point 2");
           AllPoint.add(t.p2);
          // System.out.println("Adding Point 3");
           AllPoint.add(t.p3);
           
            //System.out.println("Adding Edge 1");
                AllEdge.add(t.e1);
                // System.out.println("Adding Edge 2");
                 AllEdge.add(t.e2);
                  //System.out.println("Adding Edge 3");
                  AllEdge.add(t.e3);
                
               // System.out.println("******************************");
               for(int k=0;k<AllEdge.list.i;k++)
           {
              // System.out.println("plplpl "+AllEdge.list.get(k));
           }
               for(int i=0;i<12;i++)
                  // System.out.println();
               
           e1=(Edge)AllEdge.search(t.e1);
                e2=(Edge)AllEdge.search(t.e2);
                     e3=(Edge)AllEdge.search(t.e3);
                     p1=(Point)AllPoint.search(t.p1);
                      p2=(Point)AllPoint.search(t.p2);
                       p3=(Point)AllPoint.search(t.p3);
                   
                //AllPoint.print();
           int temp=e1.tri.i;
           for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=e1.tri.get(k);
               temptriangle.neigh.add(t);
               t.neigh.add(temptriangle);
           }
             temp=e2.tri.i;
           for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=e2.tri.get(k);
               temptriangle.neigh.add(t);
               t.neigh.add(temptriangle);
           }
             temp=e3.tri.i;
           for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=e3.tri.get(k);
               temptriangle.neigh.add(t);
               t.neigh.add(temptriangle);
           }
//           if(p1==null)
//               System.out.println("kjki");
//           if(p1.tri==null)
//               System.out.println("kjkioic");
            temp=p1.tri.i;
             for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=p1.tri.get(k);
               temptriangle.exneigh.add(t);
               t.exneigh.add(temptriangle);
           }
             temp=p2.tri.i;
             for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=p2.tri.get(k);
               temptriangle.exneigh.add(t);
               t.exneigh.add(temptriangle);
           }
             temp=p3.tri.i;
             for(int k=0;k<temp;k++)
           {
               Triangle temptriangle=p3.tri.get(k);
               temptriangle.exneigh.add(t);
               t.exneigh.add(temptriangle);
           }
           e1.tri.add(t);e2.tri.add(t);e3.tri.add(t);
           p1.tri.add(t);p2.tri.add(t);p3.tri.add(t);
           p1.pointneigh.add(p2); p1.pointneigh.add(p3);
             p2.pointneigh.add(p1); p2.pointneigh.add(p3);
               p3.pointneigh.add(p2); p3.pointneigh.add(p1);
               p1.edgeneigh.add(e1);p1.edgeneigh.add(e3);
               p2.edgeneigh.add(e1);p2.edgeneigh.add(e2);
               p3.edgeneigh.add(e2);p3.edgeneigh.add(e3);
               
//               if(globalcounter==75)
//               {
//                   AllEdge.print();
//               }
           //System.out.println("_________"+e1.hashCode()%16);
           int size=AllComp.i;
           
           int index1=size,index2=size,index3=size;
           boolean b1=false,b2=false,b3=false;
           //System.out.println(size);
           
                 //  System.out.println(index1+" "+index2+" "+index3);
                 int vari=0;
           for(int k=0;k<size;k++)
           {
             Component c1=AllComp.get(k);
             
                 vari+=c1.edge.list.i;
               
             
             
             //c1.edge.print();
             //   System.out.println("===========");
//             for(int i=0;i<c1.edge.list.i;i++)
//             {
//                 int valpo=(c1.edge.list.get(i).hashCode()%c1.edge.size+c1.edge.size)%c1.edge.size;
//                 //System.out.println(valpo+" "+c1.edge.list.get(i));
//             }
//          Edge tempedge=c1.edge.search(t.e1);
//  
//          if(tempedge==null)
//          {
//              //System.out.println("bvvkncjkbhbvnkbhsdbcjkbahbc");
//          }
//          else
//          {
//           //   System.out.println(t.e1);
//          }
Edge er1=c1.edge.search(t.e1);
Edge er2=c1.edge.search(t.e2);
Edge er3=c1.edge.search(t.e3);
             if( er1!=null  && !b1)
             {
               // System.out.println(er1+" "+t.e1+"piyush1");
                 
                 index1=k;
                 b1=true;
                 c1.add(t);
             }
             if( er2!=null  && !b2)
             {
              //   System.out.println(er2+" "+t.e2+"piyush2");
                 index2=k;
                 b2=true;
                  c1.add(t);
             }
             if(er3!=null  && !b3)
             {
              //  System.out.println(er3+" "+t.e3+"piyush3");
                 index3=k;
                 b3=true;
                  c1.add(t);
             }
             
           }
          // System.out.println(index1+" "+index2+" "+index3+"piuy");
          
                 
               
               
           if(!b1  && !b2  && !b3)
           {
               Component c1=new Component(t);
               AllComp.add(c1);
//               if(globalcounter>65)
//                   //System.out.println("piyushg----------------------------------------------------");
//               System.out.print(t);
               //System.out.println("piyushg----------------------------------------------------");
              
               //System.out.println(e1+" "+e2+" "+e3);
               //System.out.println(b1+" "+b2+" "+b3);
           }
           
           if(index1!=size)
           {
               
                Component c1=AllComp.get(index1);
               if(index2!=size && index2!=index1)
               {
                    Component c2=AllComp.get(index2);
                    Arraylist<Triangle> tempa=c2.triangle.list;
                         int size31=tempa.i;
                          for(int k=0;k<size31;k++)
                         {
                            c1.add(tempa.get(k));
                         }
               }     
                   if(index3!=size && index3!=index1)
                   {
                      
                         Component c3=AllComp.get(index3);
                         Arraylist<Triangle> tempb=c3.triangle.list;
                         int size32=tempb.i;
                        
                          for(int k=0;k<size32;k++)
                         {
                            c1.add(tempb.get(k));
                         }
                   }
                   if(index2!=size && index2!=index1 &&index3!=size && index3!=index1 )
                   {
                       if(index2<index3)
                          {
//                              System.out.println("1--------------------------------");
                              AllComp.delete(index3);
                              AllComp.delete(index2);
                          }
                       else if(index2>index3)
                          {
                              
//                               System.out.println("2-----------------------");
                              AllComp.delete(index2);
                              AllComp.delete(index3);
                          
                          }
                       else if(index2==index3)
                       {
//                           System.out.println("6-----------------------");
                              AllComp.delete(index2);
                       }
                      
                   }
                   else if(index2!=size && index2!=index1)
                   {
//                        System.out.println("3------------------------");
                       AllComp.delete(index2);
                      // System.out.println("nnnc");
                   }
                   else if(index3!=size && index3!=index1)
                   {
//                        System.out.println("4-------------------------");
                       AllComp.delete(index3);
                        //System.out.println("nnnc");
                   }
                   
                   
                   
        }
           else if(index2!=size)
           {
               Component c2=AllComp.get(index2);
               if(index3!=size && index3!=index2)
                   {
                      
                         Component c3=AllComp.get(index3);
                         Arraylist<Triangle> tempb=c3.triangle.list;
                         int size32=tempb.i;
                        
                          for(int k=0;k<size32;k++)
                         {
                            c2.add(tempb.get(k));
                         }
//                          System.out.println("5-------------------------");
                          AllComp.delete(index3);
                   }
           }
           
        
//           
//         
//                 vari=0;
//                
//                  for(int k=0;k<AllComp.i;k++)
//           {
//             Component c1=AllComp.get(k);
//             
//                 vari+=c1.edge.list.i;
//                 //c1.print();
//           }
//                    System.out.println(" "+vari+" "+AllEdge.list.i+ " "+AllComp.i+" "+globalcounter);
//           
           
           //component_done=false;
           
           
          
           
           
           return true;
       }
       
        public int TYPE_MESH()
        {

             int a=AllEdge.list.i;
             int b=AllEdge.list.get(0).tri.i;
              int maxt=b,mint=b;
//              AllEdge.print();
             for(int i=0;i<a;i++)
             {
                 b=AllEdge.list.get(i).tri.i;
                 if(maxt<b)
                     maxt=b;
                 if(mint>b)
                     mint=b;

             }
             
             
             if(maxt==2 &&mint==2)
                 return 1;
             else if(maxt>2)
                 return 3;
             else
                 return 2;
             
             

        }
        
        public EdgeInterface [] BOUNDARY_EDGES()
        {
            Arraylist<Edge> edges =new Arraylist();
            int a=AllEdge.list.i;
              for(int i=0;i<a;i++)
             {
                int b=AllEdge.list.get(i).tri.i;
                if(b==1)
                    edges.add(AllEdge.list.get(i));

             }
             int size1=edges.i;
               if(size1==0)
                 return null;
               Edge[] arr=new Edge[size1];
               //edges.print();
             for(int i=0;i<size1;i++)
             {
                 arr[i]=(edges.get(i));
                
             }
             merge_sort_edge(arr,0,size1-1);
            return (EdgeInterface [])arr;
        }
        
        public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord)
        {
           Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
           Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
           Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
           Triangle t=new Triangle(p1,p2,p3);
           
           Triangle b=AllTriangle.search(t);
           if(b==null)
           {
//               System.out.println("piyush");
               return null;
           }
           else
           {
               int a=b.neigh.i;
               if(a==0)
               {
//                   System.out.println("piyush_gupta");
                   return null;
               }
            Triangle[] TriangleArray=new Triangle[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.neigh.get(k);
            merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
           }
           /*int a=AllTriangle.i;
           boolean b1=false;
           Arraylist<Triangle> Triangles =new Arraylist();
            for(int i=0;i<a;i++)
             {
                Triangle b=AllTriangle.get(i);
                if(b.equals(t))
                {
                    b1=true;
                    
                }
                else if(neighbour(b,t))      
                {
                    Triangles.add(b);
                }
             }
            if(!b1)
                return null;
            */
            
           /* a=t.e1.tri.i;
            for(int i=1;i<a;i++)
             {
                 Triangle b=t.e1.tri.get(i);
                  if(b.equals(t)){}
                else
                Triangles.add(b);
             }
            a=t.e2.tri.i;
            for(int i=1;i<a;i++)
             {
                 Triangle b=t.e2.tri.get(i);
                 if(b.equals(t)){}
                else
                Triangles.add(b);
             }
            a=t.e3.tri.i;
            for(int i=1;i<a;i++)
             {
                 Triangle b=t.e3.tri.get(i);
                if(b.equals(t)){}
                else
                Triangles.add(b);
             }
            */
            /*a=Triangles.i;
            if(a==0)
                return null;
            Triangle[] TriangleArray=new Triangle[a];
            for(int i=0;i<a;i++)
            TriangleArray[i]=Triangles.get(i);
            merge_sort(TriangleArray,0,a-1);
            return TriangleArray;*/
            
         
        }
        
        public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord)
        {
           Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
           Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
           Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
           Triangle t=new Triangle(p1,p2,p3);
           
            Triangle b=AllTriangle.search(t);
           if(b==null)
               return null;
           return b.ed;
          /* int a=AllTriangle.i;
           boolean b1=false;
            for(int i=0;i<a;i++)
             {
                Triangle b=AllTriangle.get(i);
                if(b.equals(t))
                {
                    b1=true;
                    break;
                }
             }
            if(!b1)
                return null;
            
           return t.ed;*/
           // return null;
        }
        
        public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord)
        {
            Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
           Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
           Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
           Triangle t=new Triangle(p1,p2,p3);
           
           Triangle b=AllTriangle.search(t);
           if(b==null)
               return null;
           return b.arr;
           
           /*int a=AllTriangle.i;
           boolean b1=false;
            for(int i=0;i<a;i++)
             {
                Triangle b=AllTriangle.get(i);
                if(b.equals(t))
                {
                    b1=true;
                    break;
                }
               
                    
             }
            if(!b1)
                return null;
            
           return t.arr;*/
        }
        
        public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord)
        {
           Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
           Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
           Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
           Triangle t=new Triangle(p1,p2,p3);
           
           Triangle b=AllTriangle.search(t);
           if(b==null)
               return null;
           else
           {
               int a=b.exneigh.list.i;
               if(a==0)
                return null;
            Triangle[] TriangleArray=new Triangle[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.exneigh.list.get(k);
            merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
           }
           
           /*int a=AllTriangle.i;
            Arraylist<Triangle> temp_tri=new Arraylist();
           boolean b1=false;
            for(int i=0;i<a;i++)
             {
                Triangle b=AllTriangle.get(i);
                
                if(b.equals(t))
                {
                    b1=true;
                   
                }
                else if(extended_neighbor(b,t))
                {
                    temp_tri.add(b);
                }
                    
             }
            if(!b1)
                return null;
            
           a=temp_tri.i;
           if(a==0)
               return null;
           Triangle[] TriangleArray=new Triangle[a];
            for(int i=0;i<a;i++)
            TriangleArray[i]=temp_tri.get(i);
            
            return  TriangleArray;*/
        }

        public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates)
        {
            
            Point p =new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
            Point b=AllPoint.search(p);
                    //System.out.println(p);
                    //System.out.println("Shape()"+b);
                    //AllPoint.print();
                    if(b==null)
                        return null;
                    else
                    {
                         int a=b.tri.i;
               if(a==0)
                return null;
            Triangle[] TriangleArray=new Triangle[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.tri.get(k);
            //merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
                    }
           /* int a=AllTriangle.i;
            Arraylist<Triangle> temp_tri=new Arraylist();
           //boolean b1=false;
            for(int i=0;i<a;i++)
             {
                Triangle b=AllTriangle.get(i);
                if(b.p1.equals(p) || b.p2.equals(p) || b.p3.equals(p))
                {
                    temp_tri.add(b);
                }
             }
             a=temp_tri.i;
           if(a==0)
               return null;
            
             Triangle[] TriangleArray=new Triangle[a];
            for(int i=0;i<a;i++)
            TriangleArray[i]=temp_tri.get(i);
           
            return  TriangleArray;*/
        }
        
        public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates)
        {
            Point p =new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
            Point b=AllPoint.search(p);
                    if(b==null)
                        return null;
                    else
                    {
                          int a=b.pointneigh.list.i;
               if(a==0)
                return null;
            Point[] TriangleArray=new Point[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.pointneigh.list.get(k);
            //merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
                    }
           /* Point p =new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
            
            int a= AllEdge.i;
             Arraylist<Point> temp_point=new Arraylist();
             
              for(int i=0;i<a;i++)
             {
                Edge b=AllEdge.get(i);
                if(b.p1.equals(p)  )
                {
                    temp_point.add(b.p2);
                }
                else if(b.p2.equals(p)  )
                {
                    temp_point.add(b.p1);
                }
             }
              a=temp_point.i;
           if(a==0)
               return null;
           
           Point[] PointArray=new Point[a];
            for(int i=0;i<a;i++)
            PointArray[i]=temp_point.get(i);
              
           return PointArray;*/
        }
        
        public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates)
        {
            Point p =new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
            Point b=AllPoint.search(p);
                    if(b==null)
                        return null;
                    else
                    {
                          int a=b.edgeneigh.list.i;
               if(a==0)
                return null;
            Edge[] TriangleArray=new Edge[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.edgeneigh.list.get(k);
            //merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
                    }
                    /*
              for(int i=0;i<a;i++)
             {
                Edge b=AllEdge.get(i);
                if(b.p1.equals(p) || b.p2.equals(p)   )
                {
                    temp_edge.add(b);
                }
                
             }
              a=temp_edge.i;
           if(a==0)
               return null;
           
            Edge[] EdgeArray=new Edge[a];
            for(int i=0;i<a;i++)
            EdgeArray[i]=temp_edge.get(i);
              
           return EdgeArray;
*/
            
        }
        
        public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates)
        { 
            return INCIDENT_TRIANGLES( point_coordinates);
        }
        
        public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates)
        { 
             Point p1=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
           Point p2=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
           
           Edge e1=new Edge(p1,p2);
           Edge b=AllEdge.search(e1);
                    if(b==null)
                        return null;
                    else
                    {
                          int a=b.tri.i;
               if(a==0)
                return null;
            Triangle[] TriangleArray=new Triangle[a];
            
            for(int k=0;k<a;k++)
            TriangleArray[k]=b.tri.get(k);
            //merge_sort(TriangleArray,0,a-1);
            return TriangleArray;
                    }
                    
           /*         
           int a=AllEdge.i;
            boolean b1=false;
            Edge b=null;
            for(int i=0;i<a;i++)
             {
                b=AllEdge.get(i);
                
                if(b.equals(e1))
                {
                    b1=true;
                    break;
                }
             }   
            if(!b1)
                return null;
            
            a=b.tri.i;
           if(a==0)
               return null;
           Triangle[] TriangleArray=new Triangle[a];
            for(int i=0;i<a;i++)
            TriangleArray[i]=b.tri.get(i);
           
            return  TriangleArray;*/
        }
        
        public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2)
        {
            
            
            Point p1=new Point(triangle_coord_1[0],triangle_coord_1[1],triangle_coord_1[2]);
           Point p2=new Point(triangle_coord_1[3],triangle_coord_1[4],triangle_coord_1[5]);
           Point p3=new Point(triangle_coord_1[6],triangle_coord_1[7],triangle_coord_1[8]);
           Triangle t1=new Triangle(p1,p2,p3);
           
           Point p4=new Point(triangle_coord_2[0],triangle_coord_2[1],triangle_coord_2[2]);
           Point p5=new Point(triangle_coord_2[3],triangle_coord_2[4],triangle_coord_2[5]);
           Point p6=new Point(triangle_coord_2[6],triangle_coord_2[7],triangle_coord_2[8]);
           Triangle t2=new Triangle(p4,p5,p6);
           
           int size=AllComp.i;
           
           //int index1=size,index2=size,index3=size;
           //boolean b1=false,b2=false,b3=false;
           for(int k=0;k<size;k++)
           {
             Component c1=AllComp.get(k);
           //  c1.print();
             if(c1.triangle.search(t1)!=null)
             {
                 if(c1.triangle.search(t2)!=null)
                     return true;
             }
           }
           
           
            return false;
        }
        
      /*  public void components()
        {
            int a=AllComp.i;
            int a1=AllTriangle.i;
            
             for(int i=0;i<a1;i++)
             {
                Triangle t=AllTriangle.get(i);
                boolean b1=false;
                int s1=AllComp.i;
                for(int j=0;j<s1;j++)
                {
                    Component c=AllComp.get(j);
                    int s=c.triangle.i;
                    for(int k=0;k<s;k++)
                    {
                        if(neighbour(c.triangle.get(k),t))
                        {
                            c.add(t);
                            b1=true;
                            break;
                        }
                    }
                    if(b1)
                        break;
                }
                if(!b1)
                {
                    Component c=new Component(t);
                    AllComp.add(c);
                }
             
                
             }
             component_done=true;
            
        }*/
        
        public PointInterface centroid(Component c)
        {
            int a=c.point.list.i;
            float X=0,Y=0,Z=0;
            for(int i=0;i<a;i++)
            {
                Point p=c.point.list.get(i);
                X+=p.x;
                Y+=p.y;
                Z+=p.z;
            }
            X=X/a;Y=Y/a;Z=Z/a;
            Point p1=new Point(X,Y,Z);
            return p1;
        }
        
         public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates)
         {
            
             
             Point p =new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
             int s1=AllComp.i;
                for(int j=0;j<s1;j++)
                {
                    Component c=AllComp.get(j);
                    if(c.point.search(p)!=null)
                         return centroid(c);
                 /*   int s=c.point.i;
                   
                    for(int k=0;k<s;k++)
                     if(p.equals(c.point.get(k)))
                         return centroid(c);
                */
                }
                   
             return null;
        }

         public int COUNT_CONNECTED_COMPONENTS()
         {
             
             
             return AllComp.i;
         }
         
         public int MAXIMUM_DIAMETER()
         {
          
             int size=AllComp.i;
            if(size==0)
                return 0;
             Component c=AllComp.get(0);
              int max=c.triangle.list.i;
              
             for(int i=1;i<size;i++)
             {
                 Component temp=AllComp.get(i);
                 if(max<temp.triangle.list.i)
                 {
                     max=temp.triangle.list.i;
                     c=temp;
                 }
             }
             size=c.triangle.list.i;
             int ans=0;
             for(int k=0;k<size;k++)
             {
                 int a=traverse1(c,c.triangle.list.get(k));
                 if(a>ans)
                     ans=a;
             }
            // Triangle t=c.triangle.get(0);
             //Triangle t1=traverse(c,t);
             
             //System.out.println("+++++++++++++++++");
            // System.out.print(ans);
            return ans;
         }
         
         public PointInterface [] CENTROID (){
             
              
             int count1=AllComp.i;
             Point[] centres=new Point[count1];
             for(int i=0;i<count1;i++)
             {
                 centres[i]=(Point)centroid(AllComp.get(i));
             }
             merge_sort_point((Point[])centres,0,count1-1);
             return (Point[])centres;

}
         
         public PointInterface [] CLOSEST_COMPONENTS(){
             
              int size=AllComp.i;
            if(size<2)
                return null;
            Point[] ans=new Point[2];
            ans[0]=AllComp.get(0).point.list.get(0);
            ans[1]=AllComp.get(1).point.list.get(0);
            
            float min=distance(AllComp.get(0).point.list.get(0),AllComp.get(1).point.list.get(0));
//            AllComp.get(0).print();
//            AllComp.get(1).print();
            for(int i=0;i<size;i++)
            {
                Component c=AllComp.get(i);
                for(int j=i+1;j<size;j++)
                {
                    Component c1=AllComp.get(j);
                   
                    int size1=c.point.list.i;
                    int size2=c1.point.list.i;
                    for(int k=0;k<size1;k++)
                    {
                        for(int l=0;l<size2;l++)
                        {
                        
                            float f=distance(c.point.list.get(k),c1.point.list.get(l));
//                                System.out.println(c.point.list.get(k)+" "+c1.point.list.get(l)+" "+f);
                            if(f<min)
                            {
                                f=min;
                                ans[0]=c.point.list.get(k);
                                ans[1]=c1.point.list.get(l);
                            }
                                
                        }
                    }
                }
            }
              
             
            return ans;
            }


       
}

