import React from 'react'
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import CabeleireirosContainer from '../components/CabeleireirosContainer';
import NavbarCliente from "../components/NavbarCliente";
import NavbarDono from "../components/NavbarDono";


const Cabeleireiros = () => {
    return (
    <>
    
    {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
    <Hero hero="cabeleireirosHero">;
        <Banner title="Cabeleireiros">

           
        </Banner>
    </Hero>
    <CabeleireirosContainer />
    </>
    );
};
export default Cabeleireiros;