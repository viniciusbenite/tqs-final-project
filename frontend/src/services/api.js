import axios from "axios";

// PARA POST:

const api = axios.create({
  baseURL: 'http://localhost:3000'
});



export default api;