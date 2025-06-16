import React, { useState } from 'react';
import { createPost } from '../api';

const PostForm = ({ refreshPosts }) => {

  // State variables to manage form inputs
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');
  const [image, setImage] = useState(null);

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Form validation
    if (!title || !body) return alert('Title and body required');

    const formData = new FormData();
    formData.append('title', title);
    formData.append('body', body);
    if (image) formData.append('image', image);

    await createPost(formData);
    setTitle('');
    setBody('');
    setImage(null);
    refreshPosts(); // reload posts after submitting
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">

      {/* Form header */}
      <h5>New Post</h5>
      <p>It's your time to post!😏</p>

      {/* Title input */}
      <input
        className="form-control mb-2"
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />

      {/* Body textarea */}
      <textarea
        className="form-control mb-2"
        placeholder="Write something..."
        value={body}
        onChange={(e) => setBody(e.target.value)}
      />

      {/* Image upload and submit button */}
      <div className="d-flex align-items-center justify-content-between gap-3 mb-3">
        <div className="d-flex align-items-center gap-2 flex-grow-1">
          <label htmlFor="imageUpload" className="form-label mb-0 fw-semibold">
            Select Image
          </label>
          <input
            id="imageUpload"
            type="file"
            className="form-control"
            style={{ maxWidth: '300px' }}
            onChange={(e) => setImage(e.target.files[0])}
          />
        </div>

        {/* Submit button */}
        <button
          className="btn btn-primary px-4 py-2"
          type="submit"
          style={{ whiteSpace: 'nowrap', minWidth: '100px' }}
        >
          Post
        </button>
      </div>
    </form>
  );
};

export default PostForm;