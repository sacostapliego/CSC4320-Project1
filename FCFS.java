/*
 * First Come First Serve (FCFS) Scheduling Algorithm
 * 
 * This class implements the FCFS scheduling algorithm, where the processes are
 * executed in the order they arrive in the system.
 */
 

import java.util.*;

public class FCFS {
    public static void schedule(List<Process> processes) {
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        Map<Process, Integer[]> processMetrics = new HashMap<>();

        System.out.print("Gantt Chart:\n|");
        StringBuilder timePoints = new StringBuilder();
        timePoints.append(String.format("%-5d", currentTime)); 

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
            System.out.print(" P" + p.pid + " |");
            timePoints.append(String.format("%-5d", currentTime + p.burstTime));
            currentTime += p.burstTime;
        }
        System.out.println("\n " + timePoints.toString());

        ProcessScheduler.displayProcessDetails(processMetrics);
    }
}
