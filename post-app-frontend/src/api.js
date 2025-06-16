import axios from 'axios';

const API = axios.create({ baseURL: 'http://localhost:8080' });

/* 
  PUBLIC APIs
*/
export const getUsers = () => axios.get('https://jsonplaceholder.typicode.com/users');
export const getPicsum = () => axios.get('https://picsum.photos/v2/list?page=1&limit=10');

/* 
  BACKEND API ENDPOINTS (Spring Boot backend)
*/
export const getPosts = () => API.get('/posts');
export const createPost = (formData) => API.post('/posts/add', formData);
export const deletePost = (id) => API.delete(`/posts/${id}`);
export const likePost = (id) => API.patch(`/posts/${id}/like`);
export const dislikePost = (id) => API.patch(`/posts/${id}/dislike`);
