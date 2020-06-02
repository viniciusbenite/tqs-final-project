export default function getReservas()  {
    return fetch('https://tqs-final-project-barbershop.herokuapp.com/reservation')
        .then((response) => {
            return response.json().then((data) => {

                return data;
            }).catch((err) => {
                console.log(err);
            })
        });
}