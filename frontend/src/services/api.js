import axios from "axios";

// PARA POST:

const api = axios.create({
  baseURL: 'https://tqs-final-project-barbershop.herokuapp.com'
});



export default api;