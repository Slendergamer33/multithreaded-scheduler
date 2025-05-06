package ProcessScheduler;

import java.util.ArrayList;

/**
 * Stores logs of process activity and completion details.
 */
public class EventLog {

	private final ArrayList<String> completionLog;
	private final ArrayList<ProcessControlBlock> PCBLog;
	private String s;

	public EventLog() {
		this.completionLog = new ArrayList<>();
		this.PCBLog = new ArrayList<>();
	}

	// Log when a process completes
	public void addToCompletionLog(String logEntry) {
		this.completionLog.add(logEntry);
	}

	// Log when a PCB is created and enters the system
	public void addPCB(ProcessControlBlock PCB) {
		this.PCBLog.add(PCB);
	}

	public ArrayList<String> getCompletionLog() {
		return this.completionLog;
	}

	public ArrayList<ProcessControlBlock> getPCBLog() {
		return this.PCBLog;
	}

	public void add(String s) {
		completionLog.add(s);
	}
}
