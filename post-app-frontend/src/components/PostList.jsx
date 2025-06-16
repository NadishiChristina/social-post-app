import React, { useEffect, useState } from 'react';
import { getPosts, deletePost, likePost, dislikePost } from '../api';
import { ThumbsUp, ThumbsDown, Trash } from 'lucide-react';
   
// State for posts, loading status, and error messages
const PostList = ({ refreshTrigger }) => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchPosts = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await getPosts();

      const postsData = response.data?.data || response.data || [];
       setPosts(postsData); // show latest post on top
    } catch (err) {
      console.error('Error fetching posts:', err);
      setError('Failed to load posts');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, [refreshTrigger]); 

  // Handle deleting a post by ID
  const handleDelete = async (id) => {
    try {
      await deletePost(id);
      fetchPosts(); // Refresh posts after delete
    } catch (err) {
      console.error('Error deleting post:', err);
      alert('Failed to delete post');
    }
  };

  // Handle liking a post
  const handleLike = async (id) => {
    try {
      await likePost(id);
      fetchPosts(); // Refresh posts after like
    } catch (err) {
      console.error('Error liking post:', err);
      alert('Failed to like post');
    }
  };

  // Handle disliking a post
  const handleDislike = async (id) => {
    try {
      await dislikePost(id);
      fetchPosts(); // Refresh posts after dislike
    } catch (err) {
      console.error('Error disliking post:', err);
      alert('Failed to dislike post');
    }
  };

  if (loading) {
    return (
      <div className="text-center py-4">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="alert alert-danger" role="alert">
        {error}
      </div>
    );
  }

  return (
    <div>
      {/* Header with posts count */}
      <h5 className='post-heading'>My Posts ({posts.length})</h5>

      {/* Message if no posts are available */}
      {posts.length === 0 ? (
        <div className="text-center py-4 text-muted">
          <p className='post-heading'>No posts yet. Create your first post!</p>
        </div>
      ) : (
        posts.map((post) => (
          <div key={post.id} className="card mb-3">

            {post.image && (
              <img
                src={`http://localhost:8080/uploads/${post.image}`}
                alt="post"
                className="card-img-top"
                style={{ maxHeight: '400px', objectFit: 'cover' }}
              />
            )}

            {/* Post info */}
            <div className="card-body">
              <h5 className="card-title">{post.title}</h5>
              <p className="card-text">{post.body}</p>
              <small className="text-muted">
                Posted on {new Date(post.createdAt).toLocaleDateString()} at{' '}
                {new Date(post.createdAt).toLocaleTimeString()}
              </small>

              {/* Like, Dislike, and Delete buttons */}
              <div className="mt-3">
                <button 
                  onClick={() => handleLike(post.id)} 
                  className="btn btn-outline-success me-2"
                  disabled={loading}
                >
                  <ThumbsUp size={20} /> {post.likes || 0}
                </button>
                <button 
                  onClick={() => handleDislike(post.id)} 
                  className="btn btn-outline-danger me-2"
                  disabled={loading}
                >
                  <ThumbsDown size={20} /> {post.dislikes || 0}
                </button>
                <button 
                  onClick={() => handleDelete(post.id)} 
                  className="btn btn-outline-secondary"
                  disabled={loading}
                >
                  <Trash size={20} />
                </button>
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default PostList;