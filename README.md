## Operating Systems Project 1: Process Scheduling Simulation
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

Run the following command to create classes
```bash
javac *.java
```

Run the following command to print out both algorithms (FCFS and SJF)
```bash
java ProcessScheduler.java
```

## Operating Systems Project 2: Thread-Based Process Simulation and Synchronization
This project solves the Dining Philosophers problem using synchronization primitves

## Project Structure
- [`ProcessThread.java`](ProcessThread.java): Simulates the CPU burst using Thread.sleep()
- [`ThreadScheduler.java`](ThreadScheduler.java): The execution and synchronization of multiple threads
- [`DiningPhilosophers.java`](DiningPhilosophers.java): Solves the Dining Philosophers problem, shows the thead activity
- [`process.txt`](process.txt): Sample input file containing process data (same as project 1)

## How to Run

Run the following command to create classes for each java file if not done in the first project
```bash
javac *.java
```

Run the following command to simulate process threads
```bash
java ThreadScheduler
```

Run the following command to print the thread activity for the Dining Philosophers problem
```bash
java DiningPhilosophers
```
