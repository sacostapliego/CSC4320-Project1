/*
 * Shortest Job First (SJF) Scheduling Algorithm
 * 
 * This class implements the SJF scheduling algorithm, where the processes are 
 * executed based on the shortest job (burst time) first.
 *
 */

import java.util.*;

public class SJF {
    public static void schedule(List<Process> processes) {
        // Sort processes by burst time
        // then by arrival time, if burst time is the same
        processes.sort(Comparator.comparingInt((Process p) -> p.burstTime)
                                 .thenComparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        Map<Process, Integer[]> processMetrics = new HashMap<>();

        System.out.print("Gantt Chart: ");
        for (Process p : processes) {
            // If the current time is less than the arrival time of the process, update the current time
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            // Waiting and turnaround times, not in process.txt file
            // Calculate waiting time and turnaround time
            int waitingTime = currentTime - p.arrivalTime;
            int turnaroundTime = waitingTime + p.burstTime;

            // Store the process metrics in the map
            processMetrics.put(p, new Integer[]{waitingTime, turnaroundTime});

            // Display the Gantt chart
            System.out.print("| P" + p.pid + " ");
            currentTime += p.burstTime;
        }
        System.out.println("| " + currentTime);

        ProcessScheduler.displayProcessDetails(processMetrics);
    }
}
