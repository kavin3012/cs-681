OneDrive PowerPoint Simulation in Java

Overview:

This project simulates the functionality of OneDrive PowerPoint files, emphasizing their key features: readability, editability, and the immediate reflection of changes across different instances. The simulation is implemented in Java.

Key Features:

Replication of OneDrive PowerPoint Behavior: The program models how OneDrive PowerPoint files allow for concurrent reading and editing, ensuring that changes made in one instance of the file are instantly visible in all others.
Concurrent Access Management: Special focus is given to managing concurrent access to the file's content, a common challenge in multi-threaded applications.
Implementation Details

OneDrivePowerPointWithRaceConditions: This initial class demonstrates potential race conditions that can occur when multiple threads interact with shared content.
OneDrivePPTWithoutRaceConditions: This enhanced version uses AtomicReference and ReentrantLock to address race conditions. It ensures thread-safe operations while maintaining the core functionalities of read and write, mirroring the real-time update feature of OneDrive PowerPoint files.
Objective

The primary goal is to illustrate how race conditions can be effectively managed in a multi-threaded environment, using Java's concurrent programming utilities. This project serves as an educational tool for understanding and solving concurrency issues in software development.