import React from 'react'
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from 'react-router-dom';
import BarbeirosContainer from '../components/BarbeirosContainer';
const Barbeiros = () => {
    return (
    <>
    <Hero hero="cabeleireirosHero">;
        <Banner title="our cabeleireiros">

            <Link to='/' className="btn-primary">
                return home 
                </Link>
        </Banner>
    </Hero>
    <BarbeirosContainer />
    </>
    );
};
export default Barbeiros;