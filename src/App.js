import React from 'react';
import './App.css';

import Home from "./pages/Home";
import Barbeiros from "./pages/Barbeiros";
import Gestao from "./pages/Gestao";
import Reservas from "./pages/Reservas";
import Login from "./pages/Login";
import SingleBarbeiro from "./pages/SingleBarbeiro";
import Cabeleireiros from "./pages/Cabeleireiros";
import SingleCabeleireiro from "./pages/SingleCabeleireiro";
import Error from "./pages/Error";

import { Route, Switch } from "react-router-dom";

import Navbar from "./components/Navbar";

function App() {
    return (
        <>
            <Navbar />
            <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/cabeleireiros/" component ={Cabeleireiros} />
            <Route exact path="/cabeleireiros/:slug" component=
                    {SingleCabeleireiro} />
            <Route exact path="/barbeiros/" component ={Cabeleireiros} />
            <Route exact path="/barbeiros/:slug" component=
                    {SingleBarbeiro} />        
            <Route exact path="/login/" component ={Login} />
            <Route exact path="/gestao/" component ={Gestao} />
            <Route exact path="/reservas/" component ={Reservas} />
                <Route component={Error} />
            </Switch>
            
    </>
  );
}

export default App;
