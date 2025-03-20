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
    public static void main(String[] args) {
        List<Process> process = readprocessFromFile("process.txt");

        System.out.println("First-Come, First-Served (FCFS)");
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
}
