
import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        boolean running = true;

        //header
        System.out.println("\n");
        System.out.println("  =========================================");
        System.out.println("  ||          TASK TRACKER CLI           ||");
        System.out.println("  =========================================");
        System.out.println("  [*] Loading saved tasks...");

        //loading tasks from json file
        taskManager.loadTasks();

        while (running) {
            // --- ASCII Menu ---

            System.out.println("  |===================MENU====================|");
            System.out.println("  [add] [ list ] [ update ] [ delete ] [ exit ]");
            System.out.println("  Pro-tip: Type 'list done' to filter tasks");

            System.out.print("  [?] Command > ");

            String command = scanner.next().toLowerCase();
            System.out.println();
            switch (command) {
                case "add":
                    scanner.nextLine(); // Clear the buffer after command

                    System.out.print("New Status: (todo/in-progress/done): ");
                    String status = getValidStatus(scanner);
                    System.out.print("\n  [>] Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(description, status);
                    taskManager.saveTasks();
                    System.out.println();
                    break;

                case "list":
                    // Read whatever is left on the line (e.g., the "done" in "list done")
                    String filter = scanner.nextLine().trim().toLowerCase();

                    if (filter.isEmpty()) {
                        System.out.println("Tasks:");
                        taskManager.listTasks();
                    } else if (filter.equals("todo") || filter.equals("in-progress") || filter.equals("done")) {
                        System.out.println("Tasks (" + filter + "):");
                        // Calls the filter method we wrote in the TaskManager earlier!
                        taskManager.listTasksByStatus(filter);
                    } else {
                        System.out.println("Invalid filter. Use 'list', 'list todo', 'list in-progress', or 'list done'.");
                    }
                    System.out.println();
                    break;

                case "update":
                    System.out.print("  [>] Enter Task ID to modify: ");
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer after ID

                        System.out.print("  [>] Enter new description (or press Enter to keep current): ");
                        String newDesc = scanner.nextLine();

                        System.out.print("  [>] Enter new status (todo / in-progress / done): ");
                        String newStatus = getValidStatus(scanner);

                        taskManager.updateTask(id, newDesc, newStatus);
                    } else {
                        System.out.println("  [!] ERROR: ID must be a number.");
                        scanner.next(); // Clear trash
                    }
                    System.out.println();

                    break;

                case "delete":
                    System.out.print("  [>] Enter Task ID to delete: ");
                    if (scanner.hasNextInt()) {
                        taskManager.deleteTask(scanner.nextInt());
                    } else {
                        System.out.println("  [!] ERROR: ID must be a number.");
                        scanner.next(); // Clear trash
                    }
                    System.out.println();
                    break;

                case "exit":
                    running = false;
                    System.out.println("\n  [*] Saving data... Goodbye!");
                    break;

                default:
                    System.out.println("  [!] ERROR: Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // Helper method to trap the user until they type a valid status
    private static String getValidStatus(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.isEmpty()) {
                return input;
            }

            if (input.equals("todo") || input.equals("in-progress") || input.equals("done")) {
                return input;
            }

            System.out.print("  [!] Invalid status! Please type 'todo', 'in-progress', or 'done': ");
        }
    }
}