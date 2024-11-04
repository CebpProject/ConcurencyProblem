package org.example;

class Admin implements User {
    @Override
    public void accessDoor(Door door) {
        synchronized (door) {
            System.out.println("Admin trying to open door " + door.getId());
            door.setAdminOverride(true);  // Admin takes control
            door.open();
            door.setAdminOverride(false); // Admin releases control
        }
    }

    public void lockDoor(Door door) throws InterruptedException {

        synchronized (door){
            System.out.println("door is locked by admin ");
             door.lockDoor();
        }

    }

    public void unlockDoor(Door door) throws InterruptedException {

        synchronized (door){
            System.out.println("door is unlocked by admin ");
            door.unlockDoor();
        }

    }
}