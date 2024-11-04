package org.example;

import java.util.concurrent.locks.ReentrantLock;

class Admin implements User {
    private ReentrantLock lock;

    public Admin() {
        this.lock = new ReentrantLock();
    }

    @Override
    public void accessDoor(Door door) throws InterruptedException {
//        synchronized (door) {
        lock.lock();
            System.out.println("Admin trying to open door " + door.getId());
            door.setAdminOverride(true);  // Admin takes control
            door.open();
            door.setAdminOverride(false); // Admin releases control
        lock.unlock();
//        }
    }

    public void lockDoor(Door door) throws InterruptedException {

//        synchronized (door){
            System.out.println("door is locked by admin ");
             door.lockDoor();
//        }

    }

    public void unlockDoor(Door door) throws InterruptedException {

//        synchronized (door){
            System.out.println("door is unlocked by admin ");
            door.unlockDoor();
//        }

    }
}