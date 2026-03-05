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
