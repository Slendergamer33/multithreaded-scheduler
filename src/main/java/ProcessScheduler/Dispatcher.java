package ProcessScheduler;

import java.util.Queue;

/**
 * Dispatcher is a thread which checks the readyQueue for a process and
 * dispatches one at a time.
 */
public class Dispatcher implements Runnable {

	private ProcessControlBlock PCB = null;
	private Queue<ProcessControlBlock> readyQueue;
	private Scheduler scheduler;
	private boolean dispatched = false;

	public Dispatcher(Queue<ProcessControlBlock> readyQueue, Scheduler scheduler) {
		this.readyQueue = readyQueue;
		this.scheduler = scheduler;
	}

	/**
	 * Continuously checks for ready processes and dispatches them using the
	 * selected scheduling algorithm.
	 */
	public void run() {
		while (true) {
			synchronized (readyQueue) {
				if (!readyQueue.isEmpty() && !dispatched) {
					ProcessControlBlock pcb = readyQueue.peek(); // Look at the next process
					if (pcb != null) {
						this.PCB = pcb;
						setDispatched(true);

						// Call the scheduler to select and run the process
						scheduler.runAlgorithm();

						setDispatched(false);
					}
				} else if (readyQueue.isEmpty()) {
					break; // Exit loop when no more processes
				}
			}

			// Optional: pause a little to avoid busy waiting
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private void setDispatched(boolean b) {
		this.dispatched = b;
	}

	public ProcessControlBlock getPCB() {
		return PCB;
	}

	public void setPCB(ProcessControlBlock PCB) {
		this.PCB = PCB;
	}
}
