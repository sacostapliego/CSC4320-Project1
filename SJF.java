import java.util.*;

public class SJF {
    public static void schedule(List<Process> processes) {
        // Sort processes by arrival time first
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        Map<Process, Integer[]> processMetrics = new HashMap<>();

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(
            Comparator.comparingInt((Process p) -> p.burstTime)
                      .thenComparingInt(p -> p.arrivalTime)
        );

        int index = 0; // Track which processes have been added to the queue
        System.out.print("Gantt Chart:\n|");
        StringBuilder timePoints = new StringBuilder();
        timePoints.append(String.format("%-5d", currentTime)); 

        while (!readyQueue.isEmpty() || index < processes.size()) {
            // Add all processes that have arrived up to current time
            while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                readyQueue.add(processes.get(index));
                index++;
            }

            if (readyQueue.isEmpty()) { 
                // No process is ready, so move to the next process arrival
                currentTime = processes.get(index).arrivalTime;
                continue;
            }

            // Pick the shortest job from the ready queue
            Process p = readyQueue.poll();

            // Calculate waiting and turnaround times
            int waitingTime = currentTime - p.arrivalTime;
            int turnaroundTime = waitingTime + p.burstTime;

            // Store metrics
            processMetrics.put(p, new Integer[]{waitingTime, turnaroundTime});

            // Display Gantt chart
            System.out.print(" P" + p.pid + " |");
            timePoints.append(String.format("%-5d", currentTime + p.burstTime));

            currentTime += p.burstTime; // Move time forward
        }

        System.out.println("\n " + timePoints.toString());
        ProcessScheduler.displayProcessDetails(processMetrics);
    }
}
