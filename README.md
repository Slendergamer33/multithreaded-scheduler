# Multithreaded Scheduler: Java Simulation of OS Process Management Algorithms

This Java-based project simulates core operating system (OS) process management algorithms, including **Round Robin**, **First-Come-First-Serve (FCFS)**, and **Shortest Job Next (SJN)**. The scheduler is designed using multiple components such as a **CPU**, **Dispatcher**, **Event Log**, **Job Queue**, **Process Control Block**, **Process Creator**, and the **Scheduler** itself to simulate process scheduling in an operating system.

---

## 🚀 Features

- **Simulates multiple OS process scheduling algorithms**:
  - **Round Robin (RR)**: Assigns CPU time slices in a cyclic order.
  - **First-Come-First-Serve (FCFS)**: Processes are executed in the order they arrive.
  - **Shortest Job Next (SJN)**: Prioritizes processes with the shortest burst time.
- **Multithreaded execution**: Simulates process management with realistic time sharing and scheduling.
- **Components**:
  - **CPU**: Simulates the central processing unit where processes are executed.
  - **Dispatcher**: Manages process execution and context switching.
  - **Event Log**: Records system events for analysis and debugging.
  - **Job Queue**: Stores processes waiting for CPU time.
  - **Process Control Block (PCB)**: Stores the state of each process.
  - **Process Creator**: Generates new processes with specific attributes.
  - **Scheduler**: Implements the different scheduling algorithms and allocates CPU time.
- **SchedulerTest**: A unit test to validate the behavior of the scheduler and process management.

---

## 📁 File Overview

| File                      | Purpose                                          |
|---------------------------|--------------------------------------------------|
| `CPU.java`                 | Simulates the CPU where processes are executed.  |
| `Dispatcher.java`          | Manages context switching and process execution. |
| `EventLog.java`            | Logs system events during process execution.     |
| `JobQueue.java`            | Stores processes waiting for CPU execution.      |
| `ProcessControlBlock.java` | Stores the state of each process.                |
| `ProcessCreator.java`      | Generates and initializes new processes.         |
| `Scheduler.java`           | Implements scheduling algorithms (RR, FCFS, SJN).|
| `SchedulerTest.java`       | Unit tests for the scheduler and process handling|
| `Main.java`                | Test runner / usage example.                    |

---

## 🧪 Getting Started

### Requirements

Make sure you have Java installed. This project uses the standard Java library and does not require additional dependencies.

### Running the Scheduler

To run the scheduler, execute the following command in your terminal:

```bash
java Main

