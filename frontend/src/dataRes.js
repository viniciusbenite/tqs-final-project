export default function getReservas()  {
    return fetch('http://localhost:3000/reservation')
        .then((response) => {
            return response.json().then((data) => {

                return data;
            }).catch((err) => {
                console.log(err);
            })
        });
}