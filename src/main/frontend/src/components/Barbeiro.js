import React from 'react'
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

export default function Barbeiro({ barbeiro }) {
    const {id, nome, cidade} = barbeiro;
    return <article className="cabeleireiro">

        <div className="img-container">
            <img src={require('../images/esta.jpg')}
                alt="single cabeleireiro" />
            <div className="price-top">
            <h6>{cidade}</h6>
            
            </div>
            <Link to={`/barbeiros/${id}`}
                className="btn-primary cabeleireiro-link">
                Ver 
                </Link>
        </div>
        <p className="cabeleireiro-info">{nome}</p>
        </article>
   
}

Barbeiro.propTypes = {
    barbeiro: PropTypes.shape({
        nome: PropTypes.string.isRequired,
        cidade:PropTypes.string.isRequired,
    })
}