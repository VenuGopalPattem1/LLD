# Multithreading & Concurrency — Complete Guide

---

## 1. Program, Process & Thread

| Concept | Definition |
|---|---|
| **Program** | A static file on disk (e.g., `chrome.exe`) — just instructions, not running |
| **Process** | A **running instance** of a program. Has its own memory, resources, PID |
| **Thread** | A **unit of execution** inside a process. Shares the process's memory |

> 🧠 **Analogy:** A **program** is a recipe. A **process** is a chef cooking it. A **thread** is each hand the chef uses simultaneously.

---

## 2. Cores in a CPU

- A **core** is an independent processing unit inside a CPU
- **Single-core** → can only truly do one thing at a time
- **Multi-core** → can run multiple tasks *truly simultaneously*

```
CPU
├── Core 1 → runs Thread A
├── Core 2 → runs Thread B
├── Core 3 → runs Thread C
└── Core 4 → runs Thread D
```

> A 4-core CPU can run **4 threads in parallel** at any given moment.

---

## 3. Context Switching

When the CPU **pauses one task and switches to another**, saving the state of the first so it can resume later.

```
Time →
Core 1:  [Thread A] → [save state] → [Thread B] → [restore A] → [Thread A]
```

- **Why it happens:** More threads than cores, or a thread is waiting (I/O, sleep)
- **Cost:** Time is spent saving/restoring state — too much switching = **overhead**

---

## 4. Multithreading

Running **multiple threads within a single process** to do work concurrently.

### Why use it?

| Reason | Example |
|---|---|
| 🚀 **Performance** | Split a big computation across threads |
| 📡 **Responsiveness** | UI thread stays live while background thread loads data |
| ⏳ **I/O efficiency** | One thread waits for disk/network while another keeps working |
| 💡 **Resource sharing** | Threads share memory — cheaper than multiple processes |

---

## 5. Concurrency vs Parallelism

```
Concurrency:                    Parallelism:
(1 core, 2 tasks)               (2 cores, 2 tasks)

Core 1:                         Core 1:    Core 2:
[A][B][A][B][A]                 [  A  ]    [  B  ]

Switching rapidly               Truly at the same time
```

| | Concurrency | Parallelism |
|---|---|---|
| **Definition** | Dealing with multiple tasks at once | Doing multiple tasks at once |
| **Requires** | 1+ cores | 2+ cores |
| **Example** | OS juggling apps | Video encoding on 8 cores |

> ⚡ **Concurrency** is about *structure*. **Parallelism** is about *execution*.

---

## 6. Process vs Thread

| Feature | Process | Thread |
|---|---|---|
| Memory | Own isolated memory | Shares process memory |
| Creation cost | Heavy (slow) | Light (fast) |
| Communication | Complex (IPC) | Easy (shared memory) |
| Crash impact | Doesn't affect others | Can crash entire process |
| Security | Strong isolation | Weak isolation |
| Example | Chrome tabs | Worker threads in a server |

---

## 7. Shared Memory vs Isolated Memory

```
PROCESS A          PROCESS B
┌──────────┐       ┌──────────┐
│ Memory A │  ✗    │ Memory B │   ← Isolated (cannot touch each other)
└──────────┘       └──────────┘

PROCESS (Threads)
┌─────────────────────────┐
│  Thread 1  │  Thread 2  │   ← Shared memory (can read/write same data)
│         Memory          │
└─────────────────────────┘
```

| | Shared Memory (Threads) | Isolated Memory (Processes) |
|---|---|---|
| Speed | Fast access | Slow (needs IPC) |
| Risk | Race conditions, deadlocks | Safe, no interference |
| Use case | High-speed in-process work | Safety-critical separation |

---

## 8. When to Use Thread vs Process

| Situation | Use |
|---|---|
| Need shared data & fast communication | **Thread** |
| Tasks are independent & isolated | **Process** |
| One crash shouldn't kill everything | **Process** |
| Lightweight, many small tasks | **Thread** |
| CPU-bound heavy computation | **Process** (avoids GIL in Python) |
| I/O-bound tasks (network, disk) | **Thread** |
| Browser tabs, microservices | **Process** |
| Web server handling requests | **Thread** |

---

> 💡 **Golden Rule:** Use **threads** for cooperation, use **processes** for isolation.
>
> ---


# Ways to Create Threads in Java

There are **4 main ways** to create threads in Java:

---

## 1. Extending `Thread` Class

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // ✅ always call start(), NOT run()
    }
}
```

> ⚠️ **Limitation:** Java doesn't support multiple inheritance — if you extend `Thread`, you can't extend any other class.

---

## 2. Implementing `Runnable` Interface ✅ *(Most Common)*

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        Thread t1 = new Thread(task);
        t1.start();
    }
}
```

> ✅ **Preferred** — separates task logic from thread management. Your class can still extend others.

---

## 3. Using `Callable` + `Future` *(Returns a Result)*

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Step 1: Wrap Callable inside FutureTask
        FutureTask<Integer> f = new FutureTask<Integer>(new Res());

        // Step 2: Wrap FutureTask inside Thread (FutureTask implements Runnable)
        Thread t = new Thread(f);

        // Step 3: ✅ START the thread FIRST!
        t.start(); // ← YOU WERE MISSING THIS!

        // Step 4: get() waits for result
        int ans = f.get();
        System.out.println(ans); // 10
    }
}

class Res implements Callable<Integer> {
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += i; // 0+1+2+3+4 = 10
        }
        return sum;
    }
}
```

> ✅ Use when you need a **return value** or want to **handle exceptions** from threads.

---

## 4. Using `ExecutorService` (Thread Pool) ✅ *(Best for Production)*

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Create a pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " → " + Thread.currentThread().getName());
            });
        }

        executor.shutdown(); // ✅ always shutdown after use
    }
}
```

```
Output:
Task 1 → pool-1-thread-1
Task 2 → pool-1-thread-2
Task 3 → pool-1-thread-3
Task 4 → pool-1-thread-1   ← thread reused!
Task 5 → pool-1-thread-2
Task 6 → pool-1-thread-3
```

> ✅ Best for real apps — **reuses threads**, avoids overhead of creating new ones every time.

---

## 5. Lambda / Anonymous Runnable *(Shorthand)*

```java
public class Main {
    public static void main(String[] args) {

        // Anonymous class
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Runnable");
            }
        });

        // Lambda (Java 8+) ✅ cleaner
        Thread t2 = new Thread(() -> {
            System.out.println("Lambda Thread: " + Thread.currentThread().getName());
        });

        t1.start();
        t2.start();
    }
}
```

---

## Comparison Table

| Method | Returns Value? | Reuses Threads? | Best For |
|---|---|---|---|
| `extends Thread` | ❌ | ❌ | Simple/learning |
| `implements Runnable` | ❌ | ❌ | General use |
| `Callable + Future` | ✅ | ❌ | Need result/exception |
| `ExecutorService` | ✅ (with Future) | ✅ | Production apps |
| Lambda Runnable | ❌ | ❌ | Quick inline tasks |

---

## ⚠️ `start()` vs `run()` — Critical Difference

```java
Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));

t.run();   // ❌ runs on MAIN thread — not a new thread!
t.start(); // ✅ spawns a NEW thread
```

---

> 💡 **Rule of Thumb:**
> - Learning/simple → `Runnable`
> - Need a result → `Callable + Future`
> - Real application → `ExecutorService`

---


# Thread Pool & ExecutorService — Complete Guide

---

## The Problem First — Why Do We Need Thread Pools?

```java
// ❌ BAD — creating a new thread for every task
for (int i = 0; i < 1000; i++) {
    Thread t = new Thread(() -> doTask());
    t.start(); // 1000 threads created! 💀
}
```

**Problems with creating threads manually:**

| Problem | Explanation |
|---|---|
| 🐢 Slow | Creating a thread is expensive — takes time & memory |
| 💀 Uncontrolled | 1000 requests = 1000 threads = system crash |
| ♻️ No reuse | Thread dies after task, wasted effort |
| 🔧 Hard to manage | No easy way to track, cancel, or get results |

---

## What is a Thread Pool?

A **Thread Pool** is a collection of **pre-created, reusable threads** that wait for tasks to execute.

```
                        THREAD POOL
                   ┌─────────────────────┐
Task 1 ──▶         │  Thread 1  [BUSY]   │
Task 2 ──▶  Queue  │  Thread 2  [BUSY]   │
Task 3 ──▶  ──▶──▶ │  Thread 3  [IDLE]   │
Task 4 ──▶         │  Thread 4  [IDLE]   │
Task 5 ──▶         └─────────────────────┘
                         ↑
                   fixed number of threads
                   tasks WAIT in queue if
                   all threads are busy
```

> ♻️ Threads are **reused** — finish one task, pick up the next one from the queue!

---

## What is `ExecutorService`?

`ExecutorService` is Java's built-in **interface to manage a thread pool**.

```
You ──▶ submit(task) ──▶ ExecutorService ──▶ Thread Pool ──▶ runs task
                              │
                         manages all:
                         - thread creation
                         - task queuing
                         - thread reuse
                         - shutdown
```

Think of it as a **manager** — you give it tasks, it handles the rest.

---

## Types of Thread Pools

### 1. Fixed Thread Pool
```java
ExecutorService ex = Executors.newFixedThreadPool(3);
// exactly 3 threads, always
// extra tasks wait in queue
```
```
Threads:  [T1] [T2] [T3]
Queue:    Task4 → Task5 → Task6 (waiting)
```
> ✅ Best for **known, stable workloads**

---

### 2. Cached Thread Pool
```java
ExecutorService ex = Executors.newCachedThreadPool();
// creates new threads as needed
// reuses idle threads
// idle threads die after 60 seconds
```
```
Tasks surge  →  creates more threads
Tasks slow   →  threads die off
```
> ✅ Best for **many short-lived tasks**

---

### 3. Single Thread Executor
```java
ExecutorService ex = Executors.newSingleThreadExecutor();
// only 1 thread
// tasks run ONE BY ONE in order
```
```
Task1 → Task2 → Task3  (sequential, no overlap)
```
> ✅ Best when **order matters**, no parallel needed

---

### 4. Scheduled Thread Pool
```java
ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);

// run once after 3 seconds delay
ex.schedule(() -> System.out.println("delayed!"), 3, TimeUnit.SECONDS);

// run every 2 seconds repeatedly
ex.scheduleAtFixedRate(() -> System.out.println("repeating!"), 0, 2, TimeUnit.SECONDS);
```
> ✅ Best for **timers, scheduled jobs, periodic tasks**

---

## Core `ExecutorService` Methods

```java
ExecutorService ex = Executors.newFixedThreadPool(3);

// ── Submit tasks ──────────────────────────────────
ex.submit(runnable);          // submit Runnable (no return)
ex.submit(callable);          // submit Callable (returns Future)
ex.execute(runnable);         // like submit but no Future returned

// ── Get results ───────────────────────────────────
Future<Integer> f = ex.submit(callable);
f.get();                      // block & wait for result
f.get(2, TimeUnit.SECONDS);   // wait max 2 sec
f.isDone();                   // check if finished

// ── Shutdown ──────────────────────────────────────
ex.shutdown();                // stop accepting new tasks, finish existing
ex.shutdownNow();             // stop everything immediately
ex.awaitTermination(5, TimeUnit.SECONDS); // wait max 5s for shutdown
```

---

## Complete Working Example

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Step 1: Create pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Step 2: Submit 6 tasks (only 3 threads, so tasks queue up)
        for (int i = 1; i <= 6; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId
                    + " started  → " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (Exception e) {}
                System.out.println("Task " + taskId
                    + " finished → " + Thread.currentThread().getName());
            });
        }

        // Step 3: Shutdown after all tasks done
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All tasks done!");
    }
}
```

```
Output:
Task 1 started  → pool-1-thread-1   ← 3 tasks run at once
Task 2 started  → pool-1-thread-2
Task 3 started  → pool-1-thread-3
Task 1 finished → pool-1-thread-1   ← thread-1 now picks task 4
Task 4 started  → pool-1-thread-1   ← ♻️ reused!
Task 2 finished → pool-1-thread-2
Task 5 started  → pool-1-thread-2   ← ♻️ reused!
Task 3 finished → pool-1-thread-3
Task 6 started  → pool-1-thread-3   ← ♻️ reused!
Task 4 finished → pool-1-thread-1
Task 5 finished → pool-1-thread-2
Task 6 finished → pool-1-thread-3
All tasks done!
```

---

## With `Callable` — Getting Results Back

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 3 callable tasks
        Future<Integer> f1 = executor.submit(() -> 10 * 10);  // 100
        Future<Integer> f2 = executor.submit(() -> 20 * 20);  // 400
        Future<Integer> f3 = executor.submit(() -> 30 * 30);  // 900

        // Collect results (blocks until each is done)
        System.out.println("Result 1: " + f1.get());
        System.out.println("Result 2: " + f2.get());
        System.out.println("Result 3: " + f3.get());

        executor.shutdown();
    }
}
```

---

## Thread Pool — Full Lifecycle

```
  [CREATE]                [SUBMIT]              [EXECUTE]
Executors.new...()  →  ex.submit(task)  →  thread picks task
                              │                     │
                         task queues           task runs
                         if all busy          result stored
                                                    │
  [SHUTDOWN]                                  [RESULT]
ex.shutdown()        ◀──────────────────   f.get() returns
```

---

## Choosing the Right Pool

```
How many tasks?
      │
      ├── Fixed number, CPU heavy   →  newFixedThreadPool(n)
      │
      ├── Many small, short tasks   →  newCachedThreadPool()
      │
      ├── Must run in order         →  newSingleThreadExecutor()
      │
      └── Scheduled / repeating     →  newScheduledThreadPool(n)
```

---

## Thread Pool vs Manual Threads

| | Manual Threads | Thread Pool |
|---|---|---|
| Creation cost | Every time | Once at start |
| Thread reuse | ❌ | ✅ |
| Control | ❌ Hard | ✅ Easy |
| Return values | ❌ | ✅ via Future |
| Safe for production | ❌ | ✅ |

---

> 💡 **Golden Rules:**
> - Always use **ExecutorService** over manual threads in real apps
> - Always call **`shutdown()`** when done — or threads leak!
> - Use **`Future.get()`** to collect results from `Callable`
> - Match pool type to your **workload pattern**
>
>   ---

# Java Concurrency Basics (Interview Notes)

## T1: What is Thread Safety?

When multiple threads access shared data simultaneously, **thread safety** ensures the program still produces correct results.

Think of it like two people editing the same Google Doc — without coordination, they may overwrite each other's changes.

---

## T2: Race Conditions

A **race condition** occurs when the result of a program depends on the timing or order of thread execution.

Example:

`count++` looks like one operation but actually involves three steps:

1. Read value
2. Modify value
3. Write value

Example problem:

Thread A reads `5`
Thread B reads `5`

Thread A writes `6`
Thread B writes `6`

Correct result should be `7`, but we get `6`.

---

## T3: synchronized

The `synchronized` keyword ensures **only one thread can execute a block or method at a time**.

Every Java object has a built‑in **monitor lock**.

When:

* Thread A acquires the lock → Thread B must wait
* Thread A releases the lock → Thread B can enter

Example:

```java
synchronized(lock) {
    count++;
}
```

Best practice:

Use a **private lock object** instead of locking on `this`.

```java
private final Object lock = new Object();
```

---

## T4: volatile

Threads may cache variables locally for performance.

The `volatile` keyword ensures that **reads and writes always happen from main memory**, so all threads see the latest value.

Example use case:

```java
volatile boolean running = true;
```

Important:

`volatile` only solves **visibility problems**, not **atomicity**.

So this is still unsafe:

```java
count++
```

because it still involves multiple steps.

---

## T5: Atomic Variables

Java provides classes such as:

* `AtomicInteger`
* `AtomicLong`
* `AtomicBoolean`

These use **CAS (Compare And Swap)** at the hardware level to perform operations atomically.

Example:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

Advantages:

* No locks
* Non‑blocking
* Faster for simple counters

---

## T6: Choosing the Right Tool

| Situation                                       | Use             |
| ----------------------------------------------- | --------------- |
| One variable, one writer, used as a flag        | `volatile`      |
| One variable, multiple writers needing ++ or += | `AtomicInteger` |
| Multiple variables must update together         | `synchronized`  |

---

## Quick Interview Summary

* **Thread Safety** → Correct results with multiple threads
* **Race Condition** → Incorrect results due to timing issues
* **synchronized** → Lock-based mutual exclusion
* **volatile** → Visibility guarantee
* **Atomic Classes** → Lock‑free thread-safe operations

---

## Example

```java
import java.util.concurrent.atomic.AtomicInteger;

class CounterExample {

    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() -> {
            for(int i=0;i<1000;i++)
                count.incrementAndGet();
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<1000;i++)
                count.incrementAndGet();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count.get());
    }
}
```

Expected Output:

```
2000
```

---

These notes cover the most common **Java concurrency concepts asked in interviews**.

