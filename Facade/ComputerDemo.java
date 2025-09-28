package Facade;

public class ComputerDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.computerOn();
        computer.computerOff();
    }
}

class Cpu {
    public void start() {
        System.out.println("CPU started");
    }
    public void shutdown() {
        System.out.println("CPU shutdown");
    }
}

class Memory {
    public void load() {
        System.out.println("Memory loaded");
    }
    public void unload() {
        System.out.println("Memory unloaded");
    }
}

class HardDrive {
    public void read() {
        System.out.println("HardDrive read");
    }
    public void write() {
        System.out.println("HardDrive write");
    }
}

// 🎯 Facade
class ComputerFacade {
    private Cpu cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        cpu = new Cpu();
        memory = new Memory();
        hardDrive = new HardDrive();
    }

    public void computerOn() {
        System.out.println("\nBooting up computer...");
        cpu.start();
        memory.load();
        hardDrive.read();
        System.out.println("Computer is ON");
    }

    public void computerOff() {
        System.out.println("\nShutting down computer...");
        hardDrive.write();
        memory.unload();
        cpu.shutdown();
        System.out.println("Computer is OFF");
    }
}
