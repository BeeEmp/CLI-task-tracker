Markdown

# 📝 CLI Task Tracker

A lightweight, terminal-based task management application built with Java. This tool allows you to track your daily progress with local persistence, ensuring your tasks are saved even after closing the program.

---

## 🚀 Getting Started

### Prerequisites
* **Java 25** (as specified in the project configuration).
* **Maven** (optional, for easier builds).

### Option 1: Run with Maven (Recommended)
From the root directory, run:
```bash
mvn clean compile exec:java

This uses the exec-maven-plugin defined in your configuration to target the Main class.
Option 2: Run with Standard Java

If you don't want to use Maven, compile and run directly from the source folder:


Bash

java -cp target/classes Main

or

cd src/main/java
javac Main.java Task.java TaskManager.java
java Main

🛠️ How It Works

    Persistence: Tasks are stored in a file named tasks.json in the project root. The system manually parses this JSON to rebuild your task list every time you launch the app.

    Automatic ID Management: Every task is assigned a unique ID. If a task is deleted, the system re-indexes the remaining tasks to keep the IDs sequential (1, 2, 3...).

    Timestamps: Each task automatically tracks its createdAt time and updates its updatedAt field whenever the description or status is modified.

⌨️ Available Commands
Command	Action
add	Create a new task. You will be prompted for a status and description.
list	View all saved tasks.
list <filter>	Filter tasks by status (e.g., list done, list todo, or list in-progress).
update	Modify an existing task's description or status by providing its ID.
delete	Remove a task by its ID.
exit	Securely saves all data to tasks.json and closes the application.
📂 Project Structure

    src/main/java/Main.java: The CLI menu and user input handler.

    src/main/java/TaskManager.java: The logic engine for adding, deleting, and file I/O.

    src/main/java/Task.java: The data model representing a single task.

    pom.xml: Maven build configuration.

    tasks.json: The local database file (created automatically).
