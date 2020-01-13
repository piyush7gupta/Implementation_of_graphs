package ProjectManagement;

public class Job implements Comparable<Job> , JobReport_{

  
     public String name;
    public Project project;
    public User user;
    public int jobtime;
    public int finaltime=0;
    public int arrivingtime;
    public int status=0;
    public int jobcounter;
    public boolean lessbudget=false;
    
    public Job(String n, Project p, User u , int j,int a,int p1)
    {
        name=n;
        project=p;
        user=u;
        jobtime=j;
        jobcounter =p1;
        arrivingtime=a;
        
    }
    @Override
    public int compareTo(Job job) 
    {
        if(project.priority!=job.project.priority)
        return project.priority-job.project.priority;
        else
        return job.jobcounter-jobcounter;
        
    }
    
    public String user()
    {
        return user.Name;
    }
    public String project_name() 
    {
        return project.Name;
    }
    public  int budget()
    {
        return jobtime;
    }
     @Override
    public int arrival_time()
    {
        return arrivingtime;
    }
    public int completion_time()
    {
        return finaltime;
    }
    public String toString()
    {
       return name;
    }
}