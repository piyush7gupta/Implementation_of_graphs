package ProjectManagement;


import PriorityQueue.*;
import RedBlack.*;
import Trie.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }
    Trie<Project> AllProject = new Trie();
    Trie<User> AllUser = new Trie();
    ArrayList<User> AllUserArray = new ArrayList();
    MaxHeap<Job> AllJobHeap = new MaxHeap();
    ArrayList<Job> AllJobArray=new ArrayList();
    ArrayList<Project> AllProjectArray=new ArrayList();
    
    // RBTree<Project,Job> UnfinishedJobsTree=new RBTree();
    // ArrayList<Job> NotFinishedJobs=new ArrayList();
     ArrayList<Job> FinishedJobs=new ArrayList();
     int max_priority=0;
     
    int size=0;
    int remaining=0;
    int time=0;
    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
        MaxHeap<User> TopUsersHeap = new MaxHeap();
         int s=AllUserArray.size();
        /*for(int i=0;i<s;i++)
        {
            System.out.println(((User)(AllUserArray.get(i))).toString()+" "+((User)AllUserArray.get(i)).consumption+ " "+((User)AllUserArray.get(i)).latesttime);
        }*/
        heapify(AllUserArray);
        /*for(int i=0;i<s;i++)
        {
            System.out.println(((User)(AllUserArray.get(i))).toString()+" "+((User)AllUserArray.get(i)).consumption+ " "+((User)AllUserArray.get(i)).latesttime);
        }*/
        
        for(int i=0;i<s;i++)
        {
            TopUsersHeap.B.add(AllUserArray.get(i));
        }
        //TopUsersHeap.size1=s;
        //TopUsersHeap.B=AllUserArray;
        TopUsersHeap.size1=AllUserArray.size();
        ArrayList Arr= new ArrayList();
        //System.out.println("top conumer++++++++++++++++ "+top+" "+s+" "+TopUsersHeap.B.size());
        for(int i=0;i<top;i++)
        {
            //System.out.println(i);
            if(TopUsersHeap.size1!=0)
            {
               // System.out.println(i+"piyush");
                Arr.add( TopUsersHeap.extractMax1());
                //System.out.println(i+"gupta");
            }
            
        }
        
         /*System.out.println("top conumer");
        for(int i=0;i<Arr.size();i++)
        {
            System.out.println(((User)(Arr.get(i))).toString()+" "+((User)Arr.get(i)).consumption+ " "+((User)Arr.get(i)).latesttime);
        }*/
        
        
        return (ArrayList<UserReport_>)Arr;
        
    }

      /* public void timed_flush1(int waittime) 
    {
        int globalt=time;
        ArrayList arr=new ArrayList();
       // System.out.println("flush");
       
        while(remaining!=0)
        {
            
        
            Job j=AllJobHeap.extractMax1();
            remaining--;
            int t=globalt-j.arrivingtime;
            boolean b=true;
            if(t>=waittime)
            {
                //System.out.println("Piyush++++++++++++++++++++++++");
                if(j.jobtime<=j.project.budget)
                {
                    j.project.budget-=j.jobtime;
                    time+=j.jobtime;
                    j.finaltime=time;
                    FinishedJobs.add(j);
                    j.status=1;
                    j.user.latesttime=time;
                    j.user.consumption+=j.jobtime;
                    //System.out.println("Project: "+j.project.Name+" budget remaining: "+j.project.budget+" "+j.name+" +++++++++++++++++="+t+" "+globalt+" "+j.arrivingtime);
                   b=false;
                   //System.out.println(j.toString());
                }
                
                
            }
            if(b)
            arr.add(j);
        }
        heapify1(arr);
        AllJobHeap.B=arr;
        remaining=arr.size();
        AllJobHeap.size1=remaining;
            
    }*/

    public void timed_flush(int waittime) 
    {
        int globalt=time;
        ArrayList arr1=new ArrayList();
        ArrayList arr2=new ArrayList();
        int s=AllJobHeap.B.size();
       // System.out.println("flush");
       for(int i=0;i<s;i++)
       {
           Job j=AllJobHeap.B.get(i);
           int t=globalt-j.arrivingtime;
            if(t>=waittime)
            {
                arr1.add(j);
            }
            else
            {
                arr2.add(j);
            }
            
       }
       heapify1(arr1);
       s=arr1.size();
       AllJobHeap.B=arr1;
       AllJobHeap.size1=s;
       for(int i=0;i<s;i++)
       {
           Job j=AllJobHeap.extractMax1();
           if(j.jobtime<=j.project.budget)
                {
                    j.project.budget-=j.jobtime;
                    time+=j.jobtime;
                    j.finaltime=time;
                    FinishedJobs.add(j);
                    j.status=1;
                    j.user.latesttime=time;
                    j.user.consumption+=j.jobtime;
                    //System.out.println("Project: "+j.project.Name+" budget remaining: "+j.project.budget+" "+j.name+" +++++++++++++++++="+t+" "+globalt+" "+j.arrivingtime);
                   
                   //System.out.println(j.toString());
                }
           else
           {
               arr2.add(j);
           }
       }
       heapify1(arr2);
       s=arr2.size();
       AllJobHeap.B=arr2;
       AllJobHeap.size1=s;
       remaining=s;
       
        /*while(remaining!=0)
        {
            
        
            Job j=AllJobHeap.extractMax1();
            remaining--;
            int t=globalt-j.arrivingtime;
            boolean b=true;
            if(t>=waittime)
            {
                //System.out.println("Piyush++++++++++++++++++++++++");
                if(j.jobtime<=j.project.budget)
                {
                    j.project.budget-=j.jobtime;
                    time+=j.jobtime;
                    j.finaltime=time;
                    FinishedJobs.add(j);
                    j.status=1;
                    j.user.latesttime=time;
                    j.user.consumption+=j.jobtime;
                    //System.out.println("Project: "+j.project.Name+" budget remaining: "+j.project.budget+" "+j.name+" +++++++++++++++++="+t+" "+globalt+" "+j.arrivingtime);
                   b=false;
                   //System.out.println(j.toString());
                }
                
                
            }
            if(b)
            arr.add(j);
        }
        heapify1(arr);
        AllJobHeap.B=arr;
        remaining=arr.size();*/
        AllJobHeap.size1=remaining;
            
    }
    

    private ArrayList<JobReport_> handle_new_priority(String s) 
    
    {
       //System.out.println("priority");
        ArrayList Arr=new ArrayList();
        int sizea=AllProjectArray.size();
        int Pr=Integer.parseInt(s);
        if(Pr>max_priority)
            return (ArrayList<JobReport_>)Arr;
        for(int i=0;i<sizea;i++)
        {
           Project p= AllProjectArray.get(i);
           if(p.priority>=Pr)
           {
               int s1=p.AllJobs.size();
               for(int k=0;k<s1;k++)
               {
                   Job j=p.AllJobs.get(k);
                   if(j.status==0)
                       Arr.add(j);
               }
           }
           
            
           //Job j=AllJobArray.get(i);

        }
        /*
     for(int i=0;i<Arr.size();i++)
        {
            System.out.println(((Job)(Arr.get(i))).toString());
        }
    */
        return (ArrayList<JobReport_>)Arr;
    }

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) 
    {
        TrieNode t1=AllProject.search(cmd[1]);
        Project p=null;
        if(t1!=null)
        p= (Project)(t1.getValue());
      // System.out.println("project_user");
        /*TrieNode t2=AllUser.search(cmd[2]);
        User u=null;
        if(t2!=null)
        u= (User)(t2.getValue());*/
        
         ArrayList<Job> Arr1=p.AllJobs;
         ArrayList Arr=new ArrayList();
         
         int s=Arr1.size();
        
        int a=Integer.parseInt(cmd[3]);
        int b=Integer.parseInt(cmd[4]);
        
         for(int i=0;i<s;i++)
        {
            Job j=Arr1.get(i);
            
            if(j.user.Name.compareTo(cmd[2])==0)
            {
                if(j.arrivingtime>b)
                    break;
                if(j.arrivingtime>=a)
                    Arr.add(j);
            }
            
            
        }
    // System.out.println("projectuser");
         merge_sort(Arr,0,Arr.size()-1);
         /* for(int i=0;i<Arr.size();i++)
        {
            System.out.println(((Job)(Arr.get(i))).toString());
        }*/
    
         return (ArrayList<JobReport_>)Arr;
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
        
        //System.out.println("user");
       TrieNode t1=AllUser.search(cmd[1]);
        User p=null;
        if(t1!=null)
        p= (User)(t1.getValue());
         ArrayList<Job> Arr1=p.AllJobs;
         
        ArrayList Arr=new ArrayList();
        int s=Arr1.size();
        
        int a=Integer.parseInt(cmd[2]);
        int b=Integer.parseInt(cmd[3]);
        
        for(int i=0;i<s;i++)
        {
            Job j=Arr1.get(i);
            if(j.arrivingtime>b)
                break;
            if(j.arrivingtime>=a)
                Arr.add(j);
            
        }
        /* for(int i=0;i<Arr.size();i++)
        {
            System.out.println(((Job)(Arr.get(i))).toString());
        }
        */
    
        
        return (ArrayList<JobReport_>)Arr;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
        
       
       //System.out.println("project");
        
        TrieNode t1=AllProject.search(cmd[1]);
        Project p=null;
        if(t1!=null)
        p= (Project)(t1.getValue());
        ArrayList<Job> Arr1=p.AllJobs;
         
        ArrayList Arr=new ArrayList();
        int s=Arr1.size();
        
        int a=Integer.parseInt(cmd[2]);
        int b=Integer.parseInt(cmd[3]);
        
        for(int i=0;i<s;i++)
        {
            Job j=Arr1.get(i);
            if(j.arrivingtime>b)
                break;
            if(j.arrivingtime>=a)
                Arr.add(j);
            
            
        }
         /*for(int i=0;i<Arr.size();i++)
        {
            System.out.println(((Job)(Arr.get(i))).toString());
        }
    */
        return (ArrayList<JobReport_>)Arr;
    }




    public void schedule() {
        //System.out.println("Piyush---++++++++");
            execute_a_job();
    }

    public void run_to_completion() 
    {
         int size1=AllJobHeap.B.size();
          while(AllJobHeap.B.size()!=0)
          {
                System.out.println("Running code");
                System.out.println("Remaining jobs: "+remaining);
              
                schedule();
                System.out.println("System execution completed");
          }

    }
    public void timed_run_to_completion(){
    
        int size1=AllJobHeap.B.size();
          while(AllJobHeap.B.size()!=0)
          {
                
              Job j= AllJobHeap.extractMax1();
        while(j!=null)
        { 
            
            //System.out.println("Executing: "+j.name+" from: "+j.project.Name);
            remaining--;
            if(j.jobtime<=j.project.budget)
            {
                j.project.budget-=j.jobtime;
                time+=j.jobtime;
                j.finaltime=time;
                FinishedJobs.add(j);
                j.status=1;
                j.user.latesttime=time;
                j.user.consumption+=j.jobtime;
               // System.out.println("Project: "+j.project.Name+" budget remaining: "+j.project.budget);
                break;
            }
            
               // System.out.println("Un-sufficient budget.");
                 j.lessbudget=true;
                
                j=AllJobHeap.extractMax1();
                
            
        }
                
          }
    }

    /*public void print_stats() 
    {
           System.out.println("--------------STATS---------------");
           int s=FinishedJobs.size();
        System.out.println("Total jobs done: "+s);
        for(int i=0;i<s;i++)
        {
            Job j=FinishedJobs.get(i);
            System.out.println("Job{user='"+j.user.Name+"', project='"+j.project.Name+"', jobstatus=COMPLETED, execution_time="+j.jobtime+", end_time="+j.finaltime+", name='"+j.name+"'}");
        }
        System.out.println("------------------------");
        System.out.println("Unfinished jobs: ");
         s=AllJobArray.size();
        ArrayList Arr=new ArrayList();
        for(int i=0;i<s;i++)
        {
            Job j=AllJobArray.get(i);
            if(j.status==0)
                Arr.add(j);
        }
         s=Arr.size();
        
        heapify1(Arr);
         
       MaxHeap<Job> max=new MaxHeap();
       max.B=Arr;
       max.size1=s;
       
        for(int i=0;i<s;i++)
        {
            Job j=max.extractMax1();
            System.out.println("Job{user='"+j.user.Name+"', project='"+j.project.Name+"', jobstatus=REQUESTED, execution_time="+j.jobtime+", end_time=null, name='"+j.name+"'}");

        }
        System.out.println("Total unfinished jobs: "+s);
        System.out.println("--------------STATS DONE---------------");
        
        
    }*/
    
    public void print_stats() 
    {
           System.out.println("--------------STATS---------------");
           int s=FinishedJobs.size();
        System.out.println("Total jobs done: "+s);
        for(int i=0;i<s;i++)
        {
            Job j=FinishedJobs.get(i);
            System.out.println("Job{user='"+j.user.Name+"', project='"+j.project.Name+"', jobstatus=COMPLETED, execution_time="+j.jobtime+", end_time="+j.finaltime+", name='"+j.name+"'}");
        }
        System.out.println("------------------------");
        System.out.println("Unfinished jobs: ");
         s=AllJobArray.size();
        ArrayList Arr=new ArrayList();
        for(int i=0;i<s;i++)
        {
            Job j=AllJobArray.get(i);
            if(j.status==0)
                Arr.add(j);
        }
         s=Arr.size();
         
        
       
        merge_sort2(Arr,0,Arr.size()-1);
          
      
       
        for(int i=0;i<s;i++)
        {
            //Job j=max.extractMax1();
            Job j=(Job)Arr.get(i);
            System.out.println("Job{user='"+j.user.Name+"', project='"+j.project.Name+"', jobstatus=REQUESTED, execution_time="+j.jobtime+", end_time=null, name='"+j.name+"'}");

        }
        System.out.println("Total unfinished jobs: "+s);
        System.out.println("--------------STATS DONE---------------");
        
        
    }
    

    public void handle_add(String[] cmd) 
    {
        TrieNode t1=AllProject.search(cmd[1]);
            //System.out.println("--------------STATS----------------------------STATS---------------");
        Project p=null;
        if(t1!=null)
        {
            p= (Project)(t1.getValue());
        }
        if(p!=null)
        {
            p.budget += Integer.parseInt(cmd[2]);
            System.out.println("ADDING Budget");
            
             for(int i=0;i<AllJobArray.size();i++)
                {
                    Job j=AllJobArray.get(i);
                    if(j.project==p)
                    {
                        
                        if(j.status==0)
                        {
                            if(j.lessbudget)
                            {
                                AllJobHeap.insert1(j);
                                remaining++;
                                j.lessbudget=false;
                            }
                        }
                        
                        
                       
                    }
                    
                }
                       
                        
        }

    }

    public void handle_empty_line() {
        System.out.println("Running code");
      System.out.println("Remaining jobs: "+remaining);
              
        schedule();
        System.out.println("Execution cycle completed");
    }


    public void handle_query(String key) {
         System.out.println("Querying");   
        for(int i=0;i<AllJobArray.size();i++)
        {
            Job j=AllJobArray.get(i);
          
            if(key.equals(j.name))
            {
                if(j.status==0)
                 System.out.println(j.name+": NOT FINISHED");   
                if(j.status==1)
                 System.out.println(j.name+": COMPLETED");  
                
                return;
            }
        }
        System.out.println(key+": NO SUCH JOB"); 
    }

    public void handle_user(String name) {
            User user=new User(name);
            AllUser.insert(name, user);
            AllUserArray.add(user);
            System.out.println("Creating user");

    }
    public void timed_handle_user(String name)
    {
           User user=new User(name);
            AllUser.insert(name, user);
            AllUserArray.add(user);
            //System.out.println("Creating user");
    }


    public void handle_job(String[] cmd) 
    {
        System.out.println("Creating job");
        TrieNode t1=AllProject.search(cmd[2]);
        User u=null;Project p=null;
        if(t1!=null)
         p= (Project)(t1.getValue());
        else 
        {
            System.out.println("No such project exists. "+ cmd[2]);
            return;
        }
        
        
        TrieNode t2=AllUser.search(cmd[3]);
        if(t2!=null)
        u= (User)(t2.getValue());
       
        else
        {
            System.out.println("No such user exists: "+ cmd[3]);
            return;  
        }
          size++;
         Job j=new Job(cmd[1],p,u,Integer.parseInt(cmd[4]),time ,size);
         p.AllJobs.add(j);
         u.AllJobs.add(j);
         
            
           AllJobArray.add(j);
            //AllJobTree.insert(cmd[1], j);
            AllJobHeap.insert1(j);
            remaining++;

    }
    public void timed_handle_job(String[] cmd)
    {
       //System.out.println("Creating job");
        TrieNode t1=AllProject.search(cmd[2]);
        User u=null;Project p=null;
        if(t1!=null)
         p= (Project)(t1.getValue());
        else 
        {
           // System.out.println("No such project exists. "+ cmd[2]);
            return;
        }
        
        
        TrieNode t2=AllUser.search(cmd[3]);
        if(t2!=null)
        u= (User)(t2.getValue());
       
        else
        {
         //   System.out.println("No such user exists: "+ cmd[3]);
            return;  
        }
          size++;
         Job j=new Job(cmd[1],p,u,Integer.parseInt(cmd[4]),time ,size);
         p.AllJobs.add(j);
         u.AllJobs.add(j);
         
            
           AllJobArray.add(j);
            //AllJobTree.insert(cmd[1], j);
            AllJobHeap.insert1(j);
            remaining++;
    }

    public void handle_project(String[] cmd) {
        Project p=new Project(cmd[1] ,Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        AllProject.insert(cmd[1], p);
        if(max_priority<=p.priority);
        max_priority=p.priority;
        
         p.projectcounter=AllProjectArray.size();
        AllProjectArray.add(p);
        System.out.println("Creating project");
    }
    public void timed_handle_project(String[] cmd)
    {
        Project p=new Project(cmd[1] ,Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]));
        AllProject.insert(cmd[1], p);
        if(max_priority<=p.priority);
        max_priority=p.priority;
        
         AllProjectArray.add(p);
         p.projectcounter=AllProjectArray.size();
       // System.out.println("Creating project");
    }

    public void execute_a_job() {
        
        Job j= AllJobHeap.extractMax1();
        while(j!=null)
        { 
            
            System.out.println("Executing: "+j.name+" from: "+j.project.Name);
            remaining--;
            if(j.jobtime<=j.project.budget)
            {
                j.project.budget-=j.jobtime;
                time+=j.jobtime;
                j.finaltime=time;
                FinishedJobs.add(j);
                j.status=1;
                j.user.latesttime=time;
                j.user.consumption+=j.jobtime;
                
                System.out.println("Project: "+j.project.Name+" budget remaining: "+j.project.budget);
                break;
            }
            
                System.out.println("Un-sufficient budget.");
                 j.lessbudget=true;
                
                j=AllJobHeap.extractMax1();
                
            
        }

    }
    
    public void merge_sort(ArrayList<Job> A , int left ,int right)
    {
        if(left<right)
        {
            int a=left+right;
            a=a/2;
         
            merge_sort(A ,left,a );
            merge_sort(A,a+1,right);
            
            ArrayList<Job> L=new ArrayList();
            ArrayList<Job> R=new ArrayList();
            
            for(int i=left;i<a+1;i++)
            {
                L.add(A.get(i));
            }
             for(int i=a+1;i<right+1;i++)
            {
                R.add(A.get(i));
            }
             int i=0,j=0,k=left;
             int s1=a+1-left;
             int s2=right-a;
             boolean b= false;
             while(i<s1 && j<s2)
             {
                 Job j1=L.get(i);
                 Job j2=R.get(j);
                 b=false;
                 if(j1.status==j2.status)
                 {
                     if(j1.status==1)
                     {
                         if(j1.finaltime>=j2.finaltime)
                             b=true;
                     }
                     if(j1.status==0)
                     if(j1.arrivingtime>=j2.arrivingtime)
                             b=true;
     
                 }
                 else
                 {
                     if(j2.status==1)
                         b=true;
                     
                 }
                 
                 if(b)
                 {
                     A.set(k, j2);
                     j++;
                     k++;
                 }
                 else
                 {
                     A.set(k, j1);
                     i++;
                     k++;
                     
                 }
                 
             }
             
             while(i<s1)
             {
                  A.set(k, L.get(i));
                     i++;
                     k++;
             }
             while(j<s2)
             {
                 A.set(k, R.get(j));
                     j++;
                     k++;
             }
             
        
            
            
        }
    }
    
    public void heapify(ArrayList<User> A)
    {
        //ArrayList<User> A1=A;
        int n=A.size();
        int start =n/2-1;
        
        for(int i=start;i>=0;i--)
        {
            int curr=i;
            while(true)
            {
                int curr1=curr;
                int l=2*curr+1;
                int r=l+1;
                if(l<n)
                    if(A.get(curr1).compare1(A.get(l))<0)
                            curr1=l;
                if(r<n)
                    if(A.get(curr1).compare1(A.get(r))<0)
                            curr1=r;
                    
                    if(curr1!=curr)
                    {
                        User tempe=A.get(curr);
                        A.set(curr, A.get(curr1));
                        A.set(curr1, tempe);
                          curr=curr1;      
                    }
                    else
                        break;
                
            }
        }
        return ;
        
        
        //return null;
    }
    
    public void heapify1(ArrayList<Job> A)
    {
        //ArrayList<User> A1=A;
        int n=A.size();
        int start =n/2-1;
        
        for(int i=start;i>=0;i--)
        {
            int curr=i;
            while(true)
            {
                //System.out.println(curr+"+++++++++++++++++++"+i);
                int curr1=curr;
                int l=2*curr+1;
                int r=l+1;
                if(l<n)
                    if(A.get(curr1).compareTo(A.get(l))<0)
                            curr1=l;
                if(r<n)
                    if(A.get(curr1).compareTo(A.get(r))<0)
                            curr1=r;
                    
                    if(curr1!=curr)
                    {
                        Job tempe=A.get(curr);
                        A.set(curr, A.get(curr1));
                        A.set(curr1, tempe);
                        curr=curr1;
                                
                    }
                    else
                        break;
                
            }
        }
        return ;
        
        
        //return null;
    }
    
    public void merge_sort2(ArrayList<Job> A , int left ,int right)
    {
        if(left<right)
        {
            //System.out.println("-----------------------------------------");
            int a=left+right;
            a=a/2;
         
            merge_sort2(A ,left,a );
            merge_sort2(A,a+1,right);
            
            ArrayList<Job> L=new ArrayList();
            ArrayList<Job> R=new ArrayList();
            
            for(int i=left;i<a+1;i++)
            {
                L.add(A.get(i));
            }
             for(int i=a+1;i<right+1;i++)
            {
                R.add(A.get(i));
            }
             int i=0,j=0,k=left;
             int s1=a+1-left;
             int s2=right-a;
             boolean b= false;
             while(i<s1 && j<s2)
             {
                 Job j1=L.get(i);
                 Job j2=R.get(j);
                 b=false;
                 //System.out.println(j1.name+" "+j2.name);
                 if(j1.project.Name!=j2.project.Name)
                 {
                     //System.out.println("piyush1-----------");
                     if(j2.project.priority>j1.project.priority)
                     {
                         b=true;
                           //System.out.println("piyush1");
                     }
                     if(j2.project.priority==j1.project.priority)
                    if(j2.project.projectcounter<j1.project.projectcounter)
                     {
                         b=true;
                        // System.out.println("piyush2 "+j2.name+" "+j2.project.projectcounter+" "+j1.name+" "+j1.project.projectcounter);
                     }
                    // System.out.println("piyush1-----------");
                 }
                 else if(j2.arrivingtime<j1.arrivingtime)
                 {
                     b=true;
                    //System.out.println("piyush3");
                 }
                 
                 
                 if(b)
                 {
                     A.set(k, j2);
                     j++;
                     k++;
                 }
                 else
                 {
                     A.set(k, j1);
                     i++;
                     k++;
                     
                 }
                 
             }
             
             while(i<s1)
             {
                  A.set(k, L.get(i));
                     i++;
                     k++;
             }
             while(j<s2)
             {
                 A.set(k, R.get(j));
                     j++;
                     k++;
             }
             
        
            
            
        }
    }
    
}
