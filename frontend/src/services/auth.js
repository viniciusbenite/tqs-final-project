
export const TOKEN_EMAIL = "user_email";
export const TOKEN_TYPE = "user_type";
export const isAuthenticated = () => localStorage.getItem("user_email") !== null;
export const getTokenEmail = () => localStorage.getItem(TOKEN_EMAIL);
export const logout = () => {
  localStorage.removeItem(TOKEN_EMAIL);
  localStorage.removeItem(TOKEN_TYPE);
};


// PARA GET:

// GET USERS DA API DO SPRING
export default function getUsers()  {
  return fetch('https://tqs-final-project-barbershop.herokuapp.com/user')
        .then((response) => { 
            return response.json().then((data) => {
               
                return data;
            }).catch((err) => {
                console.log(err);
            }) 
        });
}

  