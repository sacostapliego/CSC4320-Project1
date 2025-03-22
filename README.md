## Operating Systems Project: Process Scheduling Simulation
This project implements CPU scheduling algorithms:
- First-Come, First-Served (FCFS)
- Shortest Job First (SJF)

## Project Structure
- [`Process.java`](Process.java): Defines the Process class with attributes like PID, arrival time, burst time, and priority
- [`ProcessScheduler.java`](ProcessScheduler.java): Main class that reads process data and runs the scheduling algorithms
- [`FCFS.java`](FCFS.java): Implements the First-Come, First-Served scheduling algorithm
- [`SJF.java`](SJF.java): Implements the Shortest Job First scheduling algorithm
- [`process.txt`](process.txt): Sample input file containing process data

## How to Run
Run the following command to print out both algorithms (FCFS and SJF)
```bash
java ProcessScheduler.java
```
