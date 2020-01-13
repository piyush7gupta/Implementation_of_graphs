package ProjectManagement;

import java.util.ArrayList;

public class User implements Comparable<User> , UserReport_
{

    public String Name;
    int consumption=0;
    int latesttime=0;
    ArrayList<Job> AllJobs=new ArrayList();
    public User (String n)
    {
        Name=n;
    }
    
    @Override
    public int compareTo(User user) {
        
         int a=consumption-user.consumption;
        if(a!=0)
            return a;
        else
        {
            return user.latesttime-latesttime;
        }
    }
    public String user()
    {
        return Name;
    }
    public int consumed() 
    {
        return consumption;
    }
    public int compare1(User user)
    {
        int a=consumption-user.consumption;
        if(a!=0)
            return a;
        else
        {
            return user.latesttime-latesttime;
        }
    }
    public String toString()
    {
        return Name;
    }
}
