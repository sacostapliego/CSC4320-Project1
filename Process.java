/*
 * This class represents a process in the system. 
 * Each process has:
 * - a unique process ID (pid)
 * - an arrival time (arrivalTime)
 * - a burst time (burstTime)
 * - a priority (priority)
 * 
 * The class also provides a constructor to initialize the process data,
 * and a toString() method to return a string representation of the process.
 */

public class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int priority;

    // Constructor to initialize process data
    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    // Returns a string representation of the process
    @Override
    public String toString() {
        return "PID: " + pid + ", Arrival Time: " + arrivalTime +
               ", Burst Time: " + burstTime + ", Priority: " + priority;
    }
}
