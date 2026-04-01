import java.time.LocalDateTime;


public class Task {
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // Version 1: For brand NEW tasks (Minimal info needed)
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";              // Default
        this.createdAt = LocalDateTime.now(); // Automatic
        this.updatedAt = LocalDateTime.now(); // Automatic
    }

    // Version 2: For LOADING tasks (The 5-argument one you already have)
    public Task(int id, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }


    public String toJson() {
        return "  {\n" +
                "    \"id\": " + id + ",\n" +
                "    \"description\": \"" + description + "\",\n" +
                "    \"status\": \"" + status + "\",\n" +
                "    \"createdAt\": \"" + createdAt.toString() + "\",\n" +
                "    \"updatedAt\": \"" + updatedAt.toString() + "\"\n" +
                "  }";
    }



    @Override
    public String toString() {
        return "[" + id + ", " + description + ", " + status + "]";
    }
}
