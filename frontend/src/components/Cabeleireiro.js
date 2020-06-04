import React from 'react'
import { Link } from "react-router-dom";
import defaultImg from '../images/cabeleireiro1.jpg';
import PropTypes from "prop-types";
export default function Cabeleireiro({ cabeleireiro }) {
    const {id, nome, cidade } = cabeleireiro;
    return <article className="cabeleireiro">

        <div className="img-container">
            <img src={require('../images/cabeleireiro1.jpg')}
                alt="single cabeleireiro" />
            <div className="price-top">
            <h6>{cidade}</h6>
            
            </div>
            <Link to={`/cabeleireiros/${id}`}
                className="btn-primary cabeleireiro-link">
                Ver 
                </Link>
        </div>
        <p className="cabeleireiro-info">{nome}</p>
        </article>
        
        
   
}

Cabeleireiro.propTypes = {
    cabeleireiro: PropTypes.shape({
        nome: PropTypes.string.isRequired,
        cidade:PropTypes.string.isRequired
    })
}