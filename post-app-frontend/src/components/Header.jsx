import React from 'react';
import { Settings, Bell, Mail } from 'lucide-react';

const Header = () => {
  return (
    <header className="app-header">
      <div className="container-fluid">
        <div className="d-flex justify-content-between align-items-center p-3">

          {/* Left: Icons */}
          <div className="header-left d-flex align-items-center gap-3">
            <Settings className="header-icon" />
            <Bell className="header-icon" />
            <Mail className="header-icon" />         
          </div>

          {/* Center: Logo */}
          <div className="header-center text-center flex-grow-1">
            <img 
              src="/logo.png" 
              alt="Post App Logo" 
              className="app-logo-img"
            />
          </div>

          {/* Right: Profile */}
          <div className="user-profile">

            <div className="profile-avatar">
              <img 
                src="https://i.pravatar.cc" 
                alt="Profile" 
                className="rounded-circle"
              />
            </div>
            <div className="profile-info">
              <span className="profile-name">CodzBee</span>
              <small className="profile-status">Online</small>
            </div>
            
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
