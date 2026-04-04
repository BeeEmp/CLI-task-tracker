Markdown

# 📝 CLI Task Tracker

A lightweight, terminal-based task management application built with Java. This tool allows you to track your daily progress with local persistence, ensuring your tasks are saved even after closing the program.
                          <img width="437" height="194" alt="image" src="https://github.com/user-attachments/assets/e7474d1a-6735-4baa-ad19-f4fad57bdf84" />

                          Core Logic

    Data Persistence: Saves all entries to a tasks.json file in the project root.

    ID Re-indexing: Automatically re-sequences IDs (1, 2, 3...) when a task is deleted to prevent gaps.

    Timestamping: Records createdAt and updates updatedAt whenever a task is modified.

Command Reference
Command	Action
add	Creates a new task.
list	Displays all tasks or filters by status (todo, in-progress, done).
update	Edits description or status by ID.
delete	Removes a task and triggers re-indexing.
exit	Saves data to the JSON file and closes the app.
File Roles

    Main.java: Manages the user interface and input loop.

    TaskManager.java: Handles the logic for adding, deleting, and file I/O operations.

    Task.java: The object model for a task (ID, description, status, timestamps).
