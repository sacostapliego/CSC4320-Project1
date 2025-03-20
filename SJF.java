import java.util.*;

public class SJF {
    public static void schedule(List<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.burstTime)
                                 .thenComparingInt(p -> p.arrivalTime));

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

        FCFS.displayProcessDetails(processMetrics);  // Reuse display logic
    }
}
