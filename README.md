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
