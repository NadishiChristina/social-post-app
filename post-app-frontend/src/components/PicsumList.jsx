import React, { useEffect, useState } from 'react';
import { getPicsum } from '../api';

const PicsumList = () => {
  const [images, setImages] = useState([]);  // State to hold fetched images

  useEffect(() => {
    getPicsum().then((res) => setImages(res.data));
  }, []);

  return (
    <div>
      {/* Section header */}
      <h5 className="text-2xl font-bold mb-6 text-white">Lorem Picsum Posts</h5>

      {/* Loop through each image and render */}
      {images.map((img) => (
        <div key={img.id} className="picsum-item relative">

        {/* Display image */}
        <img src={img.download_url} alt="picsum" className="picsum-image" />

        {/* Overlay text with author's name */}
        <div className="picsum-author-overlay">Post by {img.author}</div>
      </div>
      ))}
    </div>
  );
};

export default PicsumList;
