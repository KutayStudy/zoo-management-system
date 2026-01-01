# ZooManagementSystem-

A text-file driven **Java CLI** project that simulates basic zoo operations.  
The program reads **animals**, **people**, **food stock**, and **commands** from input files (comma-separated lines) and writes a detailed execution log to an output file.

## Features
- OOP design with **inheritance & polymorphism**
  - `Animal` (abstract) → `Lion`, `Elephant`, `Penguin`, `Chimpanzee`
  - `Person` (abstract) → `Visitor`, `Personnel`
- Fully file-driven workflow (no GUI)
- Food stock tracking and validation per animal type
- Exception handling & input validation (unknown animal/person, unauthorized feeding, insufficient stock, invalid number format)
- Detailed logging to an output file (`output.txt`)
