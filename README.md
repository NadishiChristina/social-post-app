# 📝 Social Post App

A full-stack social post application that allows users to **create**, **like**, **dislike**, and **delete** posts. Users can also view a **contact list** and dynamic **image gallery**, all within a clean and interactive UI designed for seamless engagement.

---

## Tech Stack

- **Backend:** Spring Boot (Java)
- **Frontend:** React + Vite
- **Styling:** Bootstrap, Lucide React Icons
- **Database:** MySQL

---

## Prerequisites

Before you begin, ensure you have the following installed:

-  **IDE**: IntelliJ (for backend) or Visual Studio Code (for frontend)
-  **MySQL Workbench** or any MySQL client
-  **Node.js & NPM** (for React + Vite)

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/social-post-app.git
cd social-post-app
```
Open the backend project in IntelliJ and the frontend in VS Code or your preferred editors.

### 2. Backend Configuration (Spring Boot)
- Navigate to the *src/main/resources/application.properties* file
- Insert your MySQL credentials (username and password).

### 3. Frontend Setup (React + Vite)
Install dependencies:
```bash
npm install
```

### 4. Run the Project

▶️ Start Backend (Spring Boot)
: *Run the main application class in IntelliJ.*

Backend URL: http://localhost:8080

▶️ Start Frontend (React + Vite)
```bash
npm run dev
```
Frontend URL: http://localhost:5173


## Features

- **📝 Create and Upload Posts** : Add a title, body content, and attach an image to create a post.

- **👍 Like, Dislike, and Remove Posts**

- **☎️ Contact List** : Fetched from: https://jsonplaceholder.typicode.com/users

- **🖼️ Image Gallery** : Fetched from: https://picsum.photos/v2/list?page=1&limit=10

