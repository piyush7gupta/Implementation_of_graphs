package ProjectManagement;

import Trie.*;
import java.util.ArrayList;
public class Project extends TrieNode implements Comparable<Project>{
    
    public String Name;
    public int budget;
    public int priority;
    int projectcounter;
    ArrayList<Job> AllJobs=new ArrayList(); 
   
    
    public Project (String n, int p,int b)
    {
       Name=n;

       priority=p;
              budget=b;
    }

    @Override
    public int compareTo(Project o) {
        int a=priority-o.priority;
        if(a!=0)
            return a;
        else
        return Name.compareTo(o.Name);
    }
    
 
}
