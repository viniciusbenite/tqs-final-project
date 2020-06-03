import React from 'react'
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
export default function Reserva({ reserva }) {
    const { dia, hora, servico } = reserva;

    return <article className="reserva">

        <div className="img-container">
            
            <div className="price-top">
            
            </div>
        </div>
        <p className="reserva-dia">{dia}</p>
        <p className="reserva-hora">{hora}</p>
        <p className="reserva-servico">{servico}</p>
        </article>
   
}

Reserva.propTypes = {
    reserva: PropTypes.shape({
        dia: PropTypes.string.isRequired,
        hora: PropTypes.string.isRequired,
        servico:PropTypes.string.isRequired
    })
}