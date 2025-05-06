package ProcessScheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CPU Thread. Runs a python script from a given PCB.
 */
public class CPU extends Thread {

	private boolean running = true;
	private ProcessControlBlock PCB = null;
	private EventLog log = null;

	public CPU(ProcessControlBlock PCB, EventLog log) {
		this.PCB = PCB;
		this.log = log;
	}

	public CPU(ProcessControlBlock pcb, EventLog log, boolean b, long timeQuantum) {
	}

	@Override
	public void run() {
		running = true;

		// Set process state to running and record start time
		PCB.setState("running");
		long startTime = System.currentTimeMillis();

		String output = "";
		ProcessBuilder pb = new ProcessBuilder("C:\\Users\\slend\\AppData\\Local\\Microsoft\\WindowsApps\\python.exe", PCB.getScriptPath());
		pb.redirectErrorStream(true); // merge stderr with stdout

		try {
			Process process = pb.start();

			// Sleep for the estimated CPU burst time
			Thread.sleep(PCB.getCPUBurstTime());

			// Wait for Python script to finish
			process.waitFor();

			// Read Python script output
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output += line + "\n";
			}
			output = output.trim(); // Clean up trailing newlines

		} catch (IOException | InterruptedException e) {
			output = "Error running process: " + e.getMessage();
			e.printStackTrace();
		}

		// Record final execution time
		long endTime = System.currentTimeMillis();
		long execTime = endTime - startTime;

		// Update PCB
		PCB.setExecutionTime(execTime);
		PCB.setPCBResult(output);
		PCB.setState("terminated");

		// Log process completion
		String logEntry = PCB.getPID() + ": Complete, Context Switches: " + PCB.getContextSwitchCount()
				+ ", Output: " + output;
		log.addToCompletionLog(logEntry);

		running = false;
	}

	public ProcessControlBlock getPCB() {
		return this.PCB;
	}

	public boolean getRunning() {
		return this.running;
	}
}
