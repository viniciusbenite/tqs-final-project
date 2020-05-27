import React from 'react';
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import ReservasContainer from "../components/ReservasContainer";
import NavbarDono from "../components/NavbarDono";
import NavbarCliente from "../components/NavbarCliente";

const Reservas = () => {
  return (
      <>
          {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }

      <Hero hero="reservasHero">;
  <Banner title="Reservas">


  </Banner>
  </Hero>
  <ReservasContainer />
  </>
  );
};
export default Reservas;
