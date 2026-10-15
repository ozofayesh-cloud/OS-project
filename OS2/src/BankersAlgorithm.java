import java.util.*;
public class BankersAlgorithm {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the number of the prosece: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resource types: ");
        int m = sc.nextInt();

        int [][] max = new int[n][m];
        int [][] alloc = new int[n][m];
        int [] avail = new int[m];
System.out.println("Enter MAX matrix (\" + n + \" x \" + m + \"):");
for ( int i =0;i< n;i++){
    System.out.println("process p"+i+" max: ");
    for(int j =0;j<m;j++){
        max[i][j]=sc.nextInt();
    }
}
System.out.println("\nEnter ALLOCATION matrix (" + n + " x " + m + "):");
for( int i=0;i<n;i++){
    System.out.println("process p"+i+"allocation: ");
    for(int j=0; j<m;j++){
        alloc[i][j]=sc.nextInt();
    }
}
System.out.println("\nEnter AVAILABLE resources (" + m + " values)");
for(int j=0;j<m;j++){
    avail[j]=sc.nextInt();
}
int [][] need =new int [n][m];
for(int i=0;i<n;i++){
    for(int j=0;j<m;j++){
        need[i][j]=max [i][j]-alloc[i][j];
    }
}
int [] work = Arrays.copyOf(avail,m);
boolean[] finish = new boolean[n];
List<Integer> safeSeq = new ArrayList<>();
boolean progress;
do{
    progress = false;
    for (int i=0;i<n;i++){
        if(!finish[i]){
            boolean canRun = true;
            for(int j=0;j<m;j++){
                if(need[i][j]> work[j]){
                    canRun=false;
                    break;
                }
            }
            if (canRun) {
                        // pretend process i finishes and releases its resources
                        for (int j = 0; j < m; j++) {
                            work[j] += alloc[i][j];
                        }
                        finish[i] = true;
                        safeSeq.add(i);
                        progress = true;
                    }
        }

    }
} while (progress);

boolean safe = true;
        for (boolean f : finish) {
            if (!f) {
                safe = false;
                break;
            }
        }
   if (safe) {
            System.out.println("\n✅ System is in a SAFE state.");
            System.out.print("Safe sequence: ");
            for (int i = 0; i < safeSeq.size(); i++) {
                System.out.print("P" + safeSeq.get(i));
                if (i < safeSeq.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println("\n❌ System is in an UNSAFE state (no safe sequence).");
        }
        sc.close();
        }

    }

