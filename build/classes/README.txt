Assignment 5
I have extended the assignment 4 first.
Handle_project :
Create a new project and add it to ALlProject trie and AllProjectArray.
Time Complexity= O(length of name of project)
BigO=O(length of name of project)

Handle User :
Create a new project and add it to ALlUser trie and AllUserArray.
Time Complexity= O(length of name of user)
BigO=O(length of name of user)

Handle Job:
Same as assignment 4
Time Complexity=log(n1)+log(n2)+log(n3);
BigO=log(n1)+log(n2)+log(n3);

Handle Query
Search the job in allJob aray and the return status of the job
Time Complexity=log(n);
BigO=log(n)

Handle add
First it search the project and add the budget to the project budget. Then it iterate through the alljobs array of project and then it add the job who are once executed but execution was not able to completed to the alljobheap again.
Time Complexity=log(n1)+log(n2)+n2(log(n2);
BigO=log(n1)+n2log(n2);

Handle empty line
I extract the job with highest priority from the heap then I see if it can be completed or not if not then I mark its lessbudget status as 1 and then extract the next key till I find the job which can be executed. The job which is executed is sent to the finishedjobs array and marked the status 1 that is completed.
Handle run_to_completion
I recursive execute job till the heap is not empty.

Print Stats
First I print all the finished jobs by iterating through the finsedJobs then I iterate through all job array then add all the jobs which are not finished to a new array Arr. Then I heapify the Arr(First by priority then by fifo) according  and then print it.

Handle new user
I have made a arraylist of alljob in user so iterated through it and returned the jobs.
Time Complexity=O(n);
BigO= O(n);

Handle newProject
I have made a arraylist of alljob in project so iterated through it and returned the jobs.
Time Complexity=O(n);
BigO= O(n);

Handle newProjecUser
First I found the project then iterated through its alljob and then found that if that job belong to that user then added t new array arr. Then I sorted the arr and reruned it
Time Complexity=O(n)+O(nlogn);
BigO= O(nlogn);

Handle priority
First I iterated through allProect and if the project priority is greater than or equal to priority then I iterated through its alljobs then if the job is unfinshed then returned it.
Time Complexity=O(n)+O(nlogn);
BigO= O(nlogn);

Handle top_consumer
I heapify the alluser list and then extraxt the first top users
Time Complexity=O(n)+O((top)logn);
Big O=O(n)+O((top)logn);

Handle Flush
First I extract the jobs from heap and then if its waittime is greater than given waittime then execute it if it can be executed else I add the array Arr and then I heapify the array and made the heap again equal to Arr.
Time Complexity=O(n)+O(nlogn);
BigO= O(nlogn);










