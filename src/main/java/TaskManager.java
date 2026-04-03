import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;




public class TaskManager {

    ArrayList<Task> tasks = new ArrayList<Task>();
    private int nextId = 1;
    private final String FILE_PATH = "tasks.json";



    public void addTask(String description, String status) {
        Task newTask = new Task(nextId++, description);

        tasks.add(newTask);

        System.out.println("New task added! \n[ID: " + newTask.getId() + "]\n[Description: " + newTask.getDescription() + "]\n");


    }


    public void updateTask(int nextId, String newDescription, String newStatus) {
        for (Task task : tasks) {
            if (task.getId() == nextId) {

                //only update if user provided a new value
                if (newDescription != null && !newDescription.isEmpty()) {
                    task.setDescription(newDescription);
                }
                else if (newStatus != null && !newStatus.isEmpty()) {
                    task.setStatus(newStatus);
                }

                System.out.println("Task " + nextId + " updated!");
                saveTasks();
                return;

            }
        }
        System.out.println("Task updated!");
    }


    public void listTasks(){
     if (tasks.isEmpty()) {
         System.out.println("Nothing to list!\n");
         return;
     }
     for (Task task : tasks) {
         System.out.println(task);}
    }

    public void listTasksByStatus(String statusFilter) {
        boolean found = false;
        for (Task t : tasks) {
            if (t.getStatus().equalsIgnoreCase(statusFilter)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found with status: " + statusFilter + "\n");
        }
    }


    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);

        if (removed) {
            System.out.println("  [*] Task " + id + " deleted!");

            //renumber
            for (int i = 0; i < tasks.size(); i++) {
                // want IDs to be 1, 2, 3...
                tasks.get(i).setId(i + 1);
            }

            //counter so the next added task has the right number
            nextId = tasks.size() + 1;
            // -----------------------------

        } else {
            System.out.println("  [!] Task " + id + " not found!");
        }

        saveTasks(); // Save the newly numbered list!
    }

    // relative path - saves where program is.


    public void saveTasks() {
        StringBuilder jsonBuilder = new StringBuilder("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            jsonBuilder.append(tasks.get(i).toJson());

            // JSON requires a comma between items, BUT not after the last item!
            if (i < tasks.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }

        jsonBuilder.append("]"); // Close the JSON array

        // Write the big string to the file
        try {
            Path path = Paths.get(FILE_PATH);
            Files.writeString(path, jsonBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadTasks() {
        Path path = Paths.get(FILE_PATH);

        // If the file doesn't exist yet (first time running), just exit safely
        if (!Files.exists(path)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path);

            // Temporary variables to hold data while we read a task
            int id = 0;
            String desc = "", status = "", created = "", updated = "";

            for (String line : lines) {
                // Clean the line so it's easier to work with
                String cleanLine = line.trim().replace("\"", "").replace(",", "");

                if (cleanLine.startsWith("id:")) {
                    id = Integer.parseInt(cleanLine.split(":")[1].trim());

                } else if (cleanLine.startsWith("description:")) {
                    desc = cleanLine.substring(cleanLine.indexOf(":") + 1).trim();

                } else if (cleanLine.startsWith("status:")) {
                    status = cleanLine.split(":")[1].trim();

                } else if (cleanLine.startsWith("createdAt:")) {
                    // Time dates have multiple colons, so we only split on the FIRST one
                    created = cleanLine.substring(cleanLine.indexOf(":") + 1).trim();

                } else if (cleanLine.startsWith("updatedAt:")) {
                    updated = cleanLine.substring(cleanLine.indexOf(":") + 1).trim();

                } else if (cleanLine.equals("}")) {
                    // When we hit a closing brace, we have all the info for one task!
                    // We use your 5-argument constructor to rebuild it
                    Task loadedTask = new Task(id, desc, status,
                            LocalDateTime.parse(created),
                            LocalDateTime.parse(updated));
                    tasks.add(loadedTask);

                    // CRITICAL: Ensure the next new task ID doesn't overlap with loaded ones
                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

}






