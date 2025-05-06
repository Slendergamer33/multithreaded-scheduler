package ProcessScheduler;

import java.util.Queue;

/**
 * This thread takes processes from the job queue and moves them to the ready queue.
 * It also logs the process and sets its state and arrival time.
 */
public class ProcessCreator implements Runnable {
	private final JobQueue jobQueue;
	private final Queue<ProcessControlBlock> readyQueue;
	private final EventLog log;

	/**
	 * Constructor to initialize the job queue, ready queue, and event log.
	 *
	 * @param jobQueue   - queue holding all incoming jobs
	 * @param readyQueue - queue where ready-to-run processes go
	 * @param log        - log for context switch and monitoring
	 */
	public ProcessCreator(JobQueue jobQueue, Queue<ProcessControlBlock> readyQueue, EventLog log) {
		this.jobQueue = jobQueue;
		this.readyQueue = readyQueue;
		this.log = log;
	}

	/**
	 * Runs the thread that transfers PCBs from job queue to ready queue.
	 */
	@Override
	public void run() {
		// Keep looping until job queue is empty
		while (true) {
			ProcessControlBlock pcb;

			// Synchronize access to jobQueue to ensure thread safety
			synchronized (jobQueue.getQueue()) {
				if (jobQueue.getQueue().isEmpty()) {
					break; // exit if all jobs have been processed
				}
				pcb = jobQueue.getQueue().poll(); // remove job from the job queue
			}

			if (pcb != null) {
				// Set process to ready and record arrival time
				pcb.setState("ready");
				pcb.setArrivalTime(System.nanoTime());

				// Add to ready queue (synchronized to avoid threading issues)
				synchronized (readyQueue) {
					readyQueue.add(pcb);
				}

				// Log the process addition
				synchronized (log) {
					log.addPCB(pcb);
				}

				// Output PCB details (for debugging or tracking)
				System.out.println(pcb.printProcessControlBlock());
			}

			// Simulate a short delay between process creations
			try {
				Thread.sleep(10); // milliseconds
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // preserve interrupt status
				break;
			}
		}
	}

	// Getter methods
	public JobQueue getJobQueue() {
		return jobQueue;
	}

	public Queue<ProcessControlBlock> getReadyQueue() {
		return readyQueue;
	}
}
