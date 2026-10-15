import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("PID Arrival Burst Priority: ");
            processes.add(new Process(
                    sc.next(),
                    sc.nextInt(),
                    sc.nextInt(),
                    sc.nextInt()
            ));
        }

        System.out.println("\n--- FCFS ---");
        List<Process> fcfsList = copy(processes);
        CPUscheduler.Fcfs(fcfsList);
        print(fcfsList);

        System.out.println("\n--- SJF ---");
        List<Process> sjfList = copy(processes);
        CPUscheduler.Sjf(sjfList);
        print(sjfList);

        System.out.println("\n--- Priority Scheduling ---");
        List<Process> priList = copy(processes);
        CPUscheduler.priority(priList);
        print(priList);

        System.out.print("\nQuantum for Round Robin: ");
        int q = sc.nextInt();

        System.out.println("\n--- Round Robin ---");
        List<Process> rrList = copy(processes);
        CPUscheduler.roundRobin(rrList, q);
        print(rrList);

        sc.close();
    }

    static List<Process> copy(List<Process> original) {
        List<Process> c = new ArrayList<>();
        for (Process p : original) {
            c.add(new Process(p.pid, p.arrival, p.burst, p.priority));
        }
        return c;
    }

    static void print(List<Process> ps) {
        System.out.printf("%-6s %-10s %-12s %-10s%n",
                "PID", "Waiting", "Turnaround", "Response");

        for (Process p : ps) {
            System.out.printf("%-6s %-10d %-12d %-10d%n",
                    p.pid, p.wating, p.trunaround, p.reponse);
        }
    }
}
public class Process {
public String pid;
public int arrival;
public int burst;
 public int priority;

 public int startTime =-1;
 public int copletion;
 public int wating;
 public int trunaround;
 public int reponse;
 public int  remaining;

 public Process(String pid,int arrival,int burst,int priority){
    this.pid=pid;
    this.arrival=arrival;
    this.burst=burst;
    this.priority=priority;
    this.remaining=burst;
 }
 public Process copy(){
    return new Process(pid,arrival,burst,priority);
 }

}
public class CPUscheduler {
public static void Fcfs(List<Process> ps){
    int time = 0;
    for (Process p : ps){
        p.wating=time;
        p.reponse=time;
        time+=p.burst;
        p.trunaround=time;
    }
}
public static void Sjf(List<Process> ps){
    ps.sort(Comparator.comparing(p -> p.burst));
    Fcfs(ps);
}
public static void priority(List<Process> ps){
     ps.sort(Comparator.comparing(p -> p.priority));
    Fcfs(ps);
}
public static void roundRobin(List<Process> ps,int quantum){
    int time =0;
    boolean done;
    
    do {
        done = true;
        for (Process p :ps){
            if (p.remaining>0){
                done = false;
                int run =Math.min(quantum,p.remaining);
                if(p.reponse == 0 && time==0)
                    p.reponse=time;
                p.remaining -= run;
                time+=run;
            }
        }
    }
    while(!done);
    for(Process p : ps){
        p.trunaround=time;
        p.wating=p.trunaround- p.burst;
    }
}
}
