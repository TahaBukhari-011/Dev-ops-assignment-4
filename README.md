# MERN Auth App

A simple authentication application built with MERN (MongoDB, Express, React, Node.js) stack featuring Sign Up, Sign In, and Welcome page functionality.

## Project Structure

```
Mern-auth-app/
├── backend/          # Express server and MongoDB integration
│   ├── models/      # Mongoose models
│   ├── routes/      # API routes
│   ├── middleware/  # Custom middleware
│   ├── server.js    # Express server
│   ├── package.json
│   └── .env         # Environment variables
│
└── frontend/         # React application
    ├── public/      # Static files
    ├── src/
    │   ├── components/  # React components
    │   ├── pages/      # Page components
    │   ├── services/   # API services
    │   ├── styles/     # CSS files
    │   ├── App.js
    │   └── index.js
    └── package.json
```

## Prerequisites

- Node.js (v14 or higher)
- npm (v6 or higher)
- MongoDB (Local or Atlas)

## Backend Setup

1. Navigate to the backend folder:
   ```bash
   cd backend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Configure environment variables in `.env`:
   - `MONGODB_URI`: Your MongoDB connection string
   - `JWT_SECRET`: Secret key for JWT tokens
   - `PORT`: Server port (default: 5000)

4. Start the backend server:
   ```bash
   npm start
   ```
   
   For development with auto-reload:
   ```bash
   npm run dev
   ```

The backend will run on `http://localhost:5000`

## Frontend Setup

1. Navigate to the frontend folder:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the React development server:
   ```bash
   npm start
   ```

The frontend will run on `http://localhost:3000`

## Features

- **Sign Up**: Create a new account with name, email, and password
- **Sign In**: Login with email and password
- **Welcome Page**: Displays a personalized welcome message after successful authentication
- **JWT Authentication**: Secure token-based authentication
- **Protected Routes**: Welcome page is protected and requires authentication

## API Endpoints

### Authentication Routes

- **POST** `/api/auth/signup` - Register a new user
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "confirmPassword": "password123"
  }
  ```

- **POST** `/api/auth/signin` - Login user
  ```json
  {
    "email": "john@example.com",
    "password": "password123"
  }
  ```

- **GET** `/api/auth/profile` - Get user profile (requires authentication)
  - Header: `Authorization: Bearer <token>`

## Usage

1. Open the app at `http://localhost:3000`
2. Click **Sign Up** to create a new account
3. Fill in your details and submit
4. After successful signup, you'll be redirected to the Welcome page
5. Alternatively, click **Sign In** if you already have an account
6. Enter your credentials and click Sign In
7. You'll see a personalized welcome message with your details
8. Click **Logout** to exit

## Technologies Used

### Backend
- Express.js - Web framework
- MongoDB - Database
- Mongoose - ODM
- JWT - Authentication
- bcryptjs - Password hashing
- CORS - Cross-Origin Resource Sharing

### Frontend
- React - UI library
- React Router - Navigation
- Axios - HTTP client
- CSS - Styling

## Notes

- Make sure MongoDB is running before starting the backend
- For MongoDB Atlas, update the connection string in `.env`
- The JWT token is stored in localStorage and used for subsequent requests
- Password must be at least 6 characters
- Email must be valid and unique

## Future Enhancements

- Email verification
- Password reset functionality
- User profile update
- Refresh tokens
- Role-based access control
- Database validation improvements

