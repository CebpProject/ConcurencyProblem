package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

        // Create threads for normal users and admins for each door
        for (Door door : doors) {
            Thread normalUserThread = new Thread(() -> normalUser.accessDoor(door));
            Thread adminThread = new Thread(() -> admin.accessDoor(door));

            // Start threads
            normalUserThread.start();
            adminThread.start();

            // Wait for both threads to complete
            normalUserThread.join();
            adminThread.join();
        }
    }
}