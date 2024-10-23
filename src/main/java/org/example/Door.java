package org.example;
class Door {
    private String id;
    private boolean isOpen;
    private boolean adminOverride; // Flag to indicate admin control

    public Door(String id) {
        this.id = id;
        this.isOpen = false; // Initially, the door is closed
        this.adminOverride = false; // Initially, no admin is overriding
    }

    public String getId() {
        return id;
    }

    public synchronized boolean isOpen() {
        return isOpen;
    }

    public synchronized void open() {
        if (!isOpen && adminOverride) {
            isOpen = true;
            System.out.println("Door " + id + " opened by Admin.");
        }
    }

    public synchronized void close() {
        if (isOpen && !adminOverride) {
            isOpen = false;
            System.out.println("Door " + id + " closed by Normal User.");
        } else {
            System.out.println("Normal User tried to close Door " + id + " but Admin is overriding.");
        }
    }

    // Admin gains control over the door, which overrides normal user actions
    public synchronized void setAdminOverride(boolean status) {
        adminOverride = status;
        if (adminOverride) {
            System.out.println("Admin has control over Door " + id);
        } else {
            System.out.println("Admin released control over Door " + id);
        }
    }
}