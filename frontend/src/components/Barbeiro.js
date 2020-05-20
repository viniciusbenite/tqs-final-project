import React from 'react'
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

export default function Barbeiro({ barbeiro }) {
    const { nome, slug, imagens, cidade } = barbeiro;

    var Url=imagens[0];
    console.log(Url);
    return <article className="cabeleireiro">

        <div className="img-container">
            <img src={Url} 
                alt="single cabeleireiro" />
            <div className="price-top">
            <h6>{cidade}</h6>
            
            </div>
            <Link to={`/barbeiros/${slug}`}
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
        slug: PropTypes.string.isRequired,
        imagens: PropTypes.arrayOf(PropTypes.string)
            .isRequired,
        cidade:PropTypes.string.isRequired
    })
}