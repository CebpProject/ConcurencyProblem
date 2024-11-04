package org.example;

public class ReentrantLock {
    private boolean isLocked = false;    // Indicates if the lock is held
    private Thread lockingThread = null; // Tracks the thread currently holding the lock
    private int lockCount = 0;           // Counts the reentrant locks by the same thread

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (isLocked && lockingThread != currentThread) {
            wait(); // Wait if the lock is held by another thread
        }
        isLocked = true;
        lockingThread = currentThread;
        lockCount++; // Increase lock count for reentrant behavior
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockingThread) {
            lockCount--; // Decrement the count
            if (lockCount == 0) { // Fully release the lock if count reaches zero
                isLocked = false;
                lockingThread = null;
                notify(); // Notify waiting threads
            }
        }
    }

    public synchronized boolean isLocked() {
        return isLocked;
    }
}
