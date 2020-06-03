import React, { Component,Fragment } from "react";
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link, withRouter } from "react-router-dom";
import NavbarCliente from "../components/NavbarCliente";
import NavbarDono from "../components/NavbarDono";


class Home extends Component {
    render() {
        console.log(localStorage.getItem("user_email"));

    return (
        <Fragment>
            {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
              <Hero>
                <Banner title="Bem-vindo" subtitle="Aqui poderá reservar os serviços que procura sem sair de casa">
                    <Link to='/cabeleireiros' className="btn-primary">
                        Cabeleireiros
                </Link>
                <strong> </strong>
                <Link to='/barbeiros' className="btn-primary">
                        Barbeiros
                </Link>
                </Banner>

            </Hero>
            </Fragment>
            
    
    
    );
    }
}

export default withRouter(Home);


