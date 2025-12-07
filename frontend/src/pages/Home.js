import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const Home = () => {
  return (
    <div className="home-container">
      <div className="home-box">
        <h1>MERN Auth App</h1>
        <p>Welcome to our authentication application</p>
        <div className="button-group">
          <Link to="/signin" className="home-button signin-button">
            Sign In
          </Link>
          <Link to="/signup" className="home-button signup-button">
            Sign Up
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
