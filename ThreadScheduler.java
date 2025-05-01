/* Thread Scheduler Simulation
 * This program simulates a thread scheduler that reads process information from a file (process.txt)
 * and creates threads for each process. Each thread simulates the execution of a process
 * by sleeping for a specified burst time. The program waits for all threads to complete
 */
import java.util.*;

public class ThreadScheduler {
    public static void main(String[] args) {
        // Read processes from file
        List<Process> processes = ProcessScheduler.readprocessFromFile("process.txt");
        
        System.out.println("Starting Process Thread Simulation");
        
        // Create threads for each process
        List<ProcessThread> threads = new ArrayList<>();
        for (Process p : processes) {
            threads.add(new ProcessThread(p));
        }
        
        // Start all threads
        for (ProcessThread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (ProcessThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }
        
        System.out.println("All processes completed execution");
    }
}