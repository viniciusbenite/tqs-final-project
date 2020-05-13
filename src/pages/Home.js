import React from 'react';
import Hero from "../components/Hero";
import Banner from "../components/Banner";
import { Link } from 'react-router-dom';

export default function Home() {
    return (
        <>
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
            
    
        </>
    );
}


