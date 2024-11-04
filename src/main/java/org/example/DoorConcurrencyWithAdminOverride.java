package org.example;

import java.util.ArrayList;
import java.util.List;

public class DoorConcurrencyWithAdminOverride {
    private static final int NUM_DOORS = 5;

    public static void main(String[] args) throws InterruptedException {
        // List of doors
        List<Door> doors = new ArrayList<>();

        // Create multiple doors
        for (int i = 1; i <= NUM_DOORS; i++) {
            doors.add(new Door("Door" + i));
        }

        // Create user and admin
        NormalUser normalUser = new NormalUser();
        Admin admin = new Admin();

        // List to hold all threads
        List<Thread> threads = new ArrayList<>();

        // Create threads for normal users and admins for each door
        for (Door door : doors) {
            Thread normalUserThread = new Thread(() -> normalUser.accessDoor(door));
            Thread adminThread = new Thread(() -> admin.accessDoor(door));
            Thread adminLockThread = new Thread(() -> {
                try {
                    admin.lockDoor(door);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread normalUserThread2 = new Thread(() -> normalUser.accessDoor(door));
            Thread adminUnlockThread = new Thread(() -> {
                try {
                    admin.unlockDoor(door);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread normalUserThread3 = new Thread(() -> normalUser.accessDoor(door));

            // Add threads to list
            threads.add(normalUserThread);
            threads.add(adminThread);
            threads.add(adminLockThread);
            threads.add(normalUserThread2);
            threads.add(adminUnlockThread);
            threads.add(normalUserThread3);
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All threads have completed.");
    }
}
