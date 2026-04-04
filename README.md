
                        

Treść wiadomości Gemini
📝 CLI Task Tracker
  <img width="437" height="194" alt="image" src="https://github.com/user-attachments/assets/e7474d1a-6735-4baa-ad19-f4fad57bdf84" />

A lightweight, terminal-based task management application built with Java. This tool allows you to track your daily progress with local persistence, ensuring your tasks are saved even after closing the program.
🚀 Getting Started
Prerequisites

    Java 25 (as specified in the project configuration)

    Maven (optional, for easier builds)

Installation & Running
Option 1: Run with Maven (Recommended)

From the root directory, run:
Bash

mvn clean compile exec:java

This uses the exec-maven-plugin defined in your configuration to target the Main class.
Option 2: Run with Standard Java

If you prefer not to use Maven, compile and run directly from the source:
Bash

# Using the target classes
java -cp target/classes Main

# Or via the source directory
cd src/main/java
javac Main.java Task.java TaskManager.java
java Main
