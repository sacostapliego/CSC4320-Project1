/*
 * This program reads process data from a file and stores it in a list of Process objects.
 * Each process has::
 * - PID 
 * - ArrivalTime 
 * - BurstTime 
 * - Priority
 * 
 * This program verifies the loaded process data by displaying it to the console.
 */


import java.io.*;
import java.util.*;

public class ProcessScheduler {
    // Main method to test the scheduling algorithms
    public static void main(String[] args) {
        List<Process> process = readprocessFromFile("process.txt");

        System.out.println("Process Data:");
        for (Process p : process) {
            System.out.println(p);
        }

        System.out.println("\nFirst Come First Served (FCFS)");
        FCFS.schedule(new ArrayList<>(process));

        System.out.println("\nShortest Job First (SJF) ");
        SJF.schedule(new ArrayList<>(process));
    }

    // Reads process data from file
    public static List<Process> readprocessFromFile(String filename) {
        List<Process> process = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();  // Skip header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Split by spaces
                if (parts.length == 4) {
                    int pid = Integer.parseInt(parts[0]);
                    int arrivalTime = Integer.parseInt(parts[1]);
                    int burstTime = Integer.parseInt(parts[2]);
                    int priority = Integer.parseInt(parts[3]);

                    process.add(new Process(pid, arrivalTime, burstTime, priority));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return process;
    }

    // Displays process details, including waiting time and turnaround time
    public static void displayProcessDetails(Map<Process, Integer[]> metrics) {
        System.out.println("\nProcess Details:");
        int totalWT = 0, totalTAT = 0;

        // Display waiting time and turnaround time for each process
        for (Map.Entry<Process, Integer[]> entry : metrics.entrySet()) {
            Process p = entry.getKey();
            int wt = entry.getValue()[0];
            int tat = entry.getValue()[1];

            System.out.printf("P%d -> WT: %d, TAT: %d\n", p.pid, wt, tat);
            // Calculate total waiting time and total turnaround time
            totalWT += wt;
            totalTAT += tat;
        }

        // Calculate average waiting time and average turnaround time
        double avgWT = (double) totalWT / metrics.size();
        double avgTAT = (double) totalTAT / metrics.size();

        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTAT);
    }
}
