import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../services/authService';
import '../styles/Welcome.css';

const Welcome = () => {
  const [user, setUser] = useState(null);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          navigate('/signin');
          return;
        }

        const response = await authService.getProfile();
        setUser(response.user);
        setMessage(response.message);
      } catch (error) {
        console.error('Error fetching profile:', error);
        navigate('/signin');
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [navigate]);

  const handleLogout = () => {
    authService.logout();
    navigate('/signin');
  };

  if (loading) {
    return <div className="welcome-container"><p>Loading...</p></div>;
  }

  return (
    <div className="welcome-container">
      <div className="welcome-box">
        <h1 className="welcome-title">{message}</h1>

        {user && (
          <div className="user-info">
            <div className="info-item">
              <span className="label">Name:</span>
              <span className="value">{user.name}</span>
            </div>
            <div className="info-item">
              <span className="label">Email:</span>
              <span className="value">{user.email}</span>
            </div>
          </div>
        )}

        <button onClick={handleLogout} className="logout-button">
          Logout
        </button>
      </div>
    </div>
  );
};

export default Welcome;
