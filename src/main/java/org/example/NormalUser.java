package org.example;

class NormalUser implements User {
    @Override
    public void accessDoor(Door door) {
        synchronized (door) {
            System.out.println("Normal User trying to close door " + door.getId());
            door.close();
        }
    }
}