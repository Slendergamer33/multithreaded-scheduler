package ProcessScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.IOException;

/**
 * This class reads a text file, and create PCB objects to be added to the JobQueue  */ public class JobQueue {

	private Queue<ProcessControlBlock> queue = null;

	public JobQueue() throws FileNotFoundException {
		this.queue = new LinkedList<ProcessControlBlock>();
	}

	/** Reads the InputScript file, Ignores line if it begins with a hash.
	 * @param filePath
	 * @throws FileNotFoundException
	 */
	public void readFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);

		if (!file.exists()) {
			throw new FileNotFoundException("The file at path " + filePath + " does not exist.");
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (line.isEmpty() || line.startsWith("#")) {continue;}

				String[] splitProcesses = line.split(",");
				String PID = splitProcesses[0];
				int priority = Integer.parseInt(splitProcesses[1]);
				String processPathFile = splitProcesses[2];
				addToQueue(PID, priority, processPathFile);
			}
		}
	}

	/** Creates a PCB object and adds it to the jobQueue.
	 * @param PID - process id
	 * @param priority - int priority
	 * @param processPathFile - specific path of the python file.
	 */
	public void addToQueue(String PID, int priority, String processPathFile) {
		long generatedBurst = generateBurst(processPathFile);
		ProcessControlBlock pcb = new ProcessControlBlock(PID, priority, generatedBurst, processPathFile);
		queue.add(pcb);
	}

	/**
	 * Estimating CPU burst time for a Python script without running it is challenging
	 * To simplify this, we calculate the burst time of the process from size of the python file.
	 * The estimated value can be slightly different each time.
	 * This method should not be modified for consistency of marking.
	 * @param PythonPathFile - path of python script
	 * @return - generated burst time
	 */
	private long generateBurst(String PythonPathFile) {
		double generatedBurst = 0;
		long scriptSize = new File(PythonPathFile).length();
		generatedBurst = Math.round((Main.getBurstSpeed() * scriptSize)/10) * 10;
		return (long) generatedBurst;
	}

	public Queue<ProcessControlBlock> getQueue() {
		return this.queue;
	}

}

