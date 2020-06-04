import React from 'react'
import Hero from "../components/Hero";
import NavbarCliente from "../components/NavbarCliente";
import NavbarDono from "../components/NavbarDono";
import BarbeirosContainer from '../components/BarbeirosContainer';
import Banner from "../components/Banner";

const Barbeiros = () => {
    return (
        <>
        {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
        <Hero hero="barbeirosHero">;
            <Banner title="Barbeiros">
    
               
            </Banner>
        </Hero>
        <BarbeirosContainer />
        </>
        );
};
export default Barbeiros;