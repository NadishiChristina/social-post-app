import React, { useState, useCallback } from 'react';
import ContactsList from './components/ContactsList';
import PostForm from './components/PostForm';
import PostList from './components/PostList';
import PicsumList from './components/PicsumList';
import Header from './components/Header';
import Footer from './components/Footer';
import './App.css';

const App = () => {
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  // Function to trigger post refresh
  const refreshPosts = useCallback(() => {
    setRefreshTrigger(prev => prev + 1);
  }, []);

  return (
    <div className="app-container">
      <Header />

      {/* Main Content */}
      <main className="app-main">
        <div className="container-fluid">
          <div className="row g-4">
            
            {/* Left Sidebar - Contacts */}
            <div className="col-xl-3 col-lg-4 col-md-12">
              <div className="sidebar-section">
                <ContactsList />
              </div>
            </div>

            {/* Main Content Area - Posts */}
            <div className="col-xl-6 col-lg-8 col-md-12">
              <div className="main-content">
                
                {/* Post Form */}
                <div className="post-form-section">
                  <PostForm refreshPosts={refreshPosts} />
                </div>

                {/* Posts List */}
                <div className="posts-section">
                  <PostList refreshTrigger={refreshTrigger} />
                </div>
              </div>
            </div>

            {/* Right Sidebar - Lorem Picsum */}
            <div className="col-xl-3 col-lg-12 col-md-12">
              <div className="sidebar-section picsum-section">
                <PicsumList />
              </div>
            </div>
          </div>
        </div>
      </main>

      {/* Footer */}
      <Footer />
    </div>
  );
};

export default App;