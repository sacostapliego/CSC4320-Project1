import java.util.*;

public class FCFS {
    public static void schedule(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        Map<Process, Integer[]> processMetrics = new HashMap<>();

        System.out.print("Gantt Chart: ");
        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            int waitingTime = currentTime - p.arrivalTime;
            int turnaroundTime = waitingTime + p.burstTime;

            processMetrics.put(p, new Integer[]{waitingTime, turnaroundTime});

            System.out.print("| P" + p.pid + " ");
            currentTime += p.burstTime;
        }
        System.out.println("| " + currentTime);

        displayProcessDetails(processMetrics);
    }

    public static void displayProcessDetails(Map<Process, Integer[]> metrics) {
        System.out.println("\nProcess Details:");
        int totalWT = 0, totalTAT = 0;

        for (Map.Entry<Process, Integer[]> entry : metrics.entrySet()) {
            Process p = entry.getKey();
            int wt = entry.getValue()[0];
            int tat = entry.getValue()[1];

            System.out.printf("P%d -> WT: %d, TAT: %d\n", p.pid, wt, tat);

            totalWT += wt;
            totalTAT += tat;
        }

        double avgWT = (double) totalWT / metrics.size();
        double avgTAT = (double) totalTAT / metrics.size();

        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTAT);
    }
}
