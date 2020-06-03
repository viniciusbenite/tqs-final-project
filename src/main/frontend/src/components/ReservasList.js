import React from 'react';
import Reserva from './Reserva';

export default function ReservasList({reservas}) {

    if(reservas.length === 0){
        return (
            <div className="empty-search">
                <h3>Não efetuou qualquer reserva</h3>
            </div>
        );
    }

    return (
        <section className="reservalist">
            <div className="reservalist-center">
                {reservas.map(item => {
                    return <Reserva key={item.id} reserva={item} />;
                })}
            </div>
        </section>
    );
}