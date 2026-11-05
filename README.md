
# School Grading Management System

![Java](https://img.shields.io/badge/Language-Java-orange)
![Version](https://img.shields.io/badge/Version-2.0-blue)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)

## Project Overview

The School Grading Management System is a Java-based application designed to help teachers manage student academic records and allow students to access their grades.

The application features two main sections:

- Teacher Dashboard
- Student Portal

This project demonstrates object-oriented programming, student record management, grade calculations, and file handling using Java.

## Features

### Teacher Dashboard
- Register new students.
- Assign unique student IDs.
- Enter assignment grades.
- Record class subjects.
- View registered students.
- Track assignment records.

### Student Portal
- Search student records using Student ID.
- View personal academic information.
- View class subjects.
- View assignment names and grades.
- Calculate overall grade averages.
- Display letter grades.
- View pass or fail status.

### Data Storage
- Save student records using Java Serialization.
- Automatically load previously saved records.
- Preserve student information after closing the application.
- Store assignment grades in a local data file.

## Technologies Used

- Java
- Visual Studio Code
- Java Scanner
- ArrayList
- Object-Oriented Programming (OOP)
- Java File Handling
- Object Serialization

## Project Structure

```text
SchoolGradingSystem/
|
|-- SchoolGradingSystem.java
|-- Student.java
|-- GradeRecord.java
|-- README.md
|-- students.dat
```

## How to Run the Program

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/school-grading-management-system.git
```

### 2. Open the Project Folder

Open the project in Visual Studio Code.

### 3. Compile the Java Files

```bash
javac *.java
```

### 4. Run the Application

```bash
java SchoolGradingSystem
```

## Grading Scale

| Percentage | Letter Grade |
|---|---|
| 90-100% | A |
| 80-89% | B |
| 70-79% | C |
| 60-69% | D |
| Below 60% | F |

## Data Storage

Student information and assignment records are saved locally in a file named `students.dat`.

The application automatically loads saved records when it starts and updates the file when student information or grades are added.

## Future Improvements

- Teacher authentication and login.
- Student authentication and login.
- Edit and delete assignment grades.
- Search and filter student records.
- MySQL database integration.
- Graphical user interface.
- Generate student grade reports.

## Current Limitations

This is a console-based prototype. The current student portal uses Student ID lookup rather than secure authentication. Local serialized records are not encrypted, and the application is not designed for production use.

## Developer

**Vera Chidinma**

Computer Science & Information Systems

## Project Status

Version 2.0 — Completed

The current version successfully supports student registration, assignment grading, grade calculations, and permanent data storage.

