/* Dining Philosophers Problem
 * 
 * This program simulates the dining philosophers problem using Java threads.
 * It also avoids deadlock by implementing a strategy where even-numbered philosophers pick up the right fork first
 * 
 */

import java.util.concurrent.locks.ReentrantLock; // Importing ReentrantLock for thread synchronization

public class DiningPhilosophers {
    // Number of philosophers (threads) and forks (resources)
    private static final int NUM_PHILOSOPHERS = 5;
    private static final ReentrantLock[] forks = new ReentrantLock[NUM_PHILOSOPHERS];
    
    // Philosopher class representing each philosopher thread
    static class Philosopher extends Thread {
        private final int id;
        private final ReentrantLock leftFork;
        private final ReentrantLock rightFork;
        
        // Constructor to initialize philosopher with an ID
        public Philosopher(int id) {
            this.id = id + 1;
            this.leftFork = forks[id];
            this.rightFork = forks[(id + 1) % NUM_PHILOSOPHERS];
        }
        
        // Simulate thinking and eating actions
        private void think() throws InterruptedException {
            System.out.println("[Philosopher " + id + "] Thinking...");
            Thread.sleep((long)(Math.random() * 1000));
        }
        
        private void eat() throws InterruptedException {
            System.out.println("[Philosopher " + id + "] Eating...");
            Thread.sleep((long)(Math.random() * 1000));
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    think();
                    
                    // Prevent deadlock by having philosophers pick up forks in order
                    // Even philosophers pick up right fork first, odd philosophers pick up left fork first
                    ReentrantLock firstFork = (id % 2 == 0) ? rightFork : leftFork;
                    ReentrantLock secondFork = (id % 2 == 0) ? leftFork : rightFork;
                    
                    System.out.println("[Philosopher " + id + "] Waiting for forks...");
                    
                    firstFork.lock();
                    System.out.println("[Philosopher " + id + "] Picked up first fork");
                    
                    secondFork.lock();
                    System.out.println("[Philosopher " + id + "] Picked up second fork");
                    
                    eat();
                    
                    secondFork.unlock();
                    System.out.println("[Philosopher " + id + "] Released second fork");
                    
                    firstFork.unlock();
                    System.out.println("[Philosopher " + id + "] Released first fork");
                    
                    // Only run one cycle for demonstration purposes
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("[Philosopher " + id + "] was interrupted");
            }
        }
    }
    
    public static void main(String[] args) {
        // Initialize forks (resources)
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }
        
        // Create and start philosopher threads
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i);
            philosophers[i].start();
        }
        
        // Wait for all philosophers to finish
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }
        
        System.out.println("Dining philosophers simulation completed");
    }
}