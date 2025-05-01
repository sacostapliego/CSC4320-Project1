// Example from document with slight modifications

public class ProcessThread extends Thread {
    private Process process;
    
    public ProcessThread(Process process) {
        this.process = process;
        this.setName("P" + process.pid);
    }
    
    @Override
    public void run() {
        System.out.println("[P" + process.pid + "] Started execution");
        try {
            // Simulate CPU burst time (milliseconds instead of seconds for faster testing)
            Thread.sleep(process.burstTime * 100);
        } catch (InterruptedException e) {
            System.out.println("[P" + process.pid + "] Interrupted");
        }
        System.out.println("[P" + process.pid + "] Completed execution");
    }
}