import axios from "axios";

// PARA POST:

const api = axios.create({
  baseURL: 'http://localhost:8080'
});



export default api;