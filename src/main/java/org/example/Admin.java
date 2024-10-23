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
}