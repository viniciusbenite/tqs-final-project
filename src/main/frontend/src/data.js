
import img1 from "./images/cabeleireiro1.jpg";
import img2 from "./images/cabeleireiro2.jpg";

export default function getSaloes()  {
  return fetch('http://localhost:3000/saloon')
        .then((response) => { 
            return response.json().then((data) => {
                return data;
            }).catch((err) => {
                console.log(err);
            }) 
        });
}
