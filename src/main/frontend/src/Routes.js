


import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import { isAuthenticated } from "./services/auth";



import Home from "./pages/Home";

import Barbeiros from "./pages/Barbeiros";
import Gestao from "./pages/Gestao";
import Reservas from "./pages/Reservas";
import Login from "./pages/Login";
import SignUpDono from "./pages/SignUpDono";
import SignUpCliente from "./pages/SignUpCliente";
import SingleBarbeiro from "./pages/SingleBarbeiro";
import Cabeleireiros from "./pages/Cabeleireiros";
import Users from "./pages/Users";
import ReservasDono from "./pages/ReservasDono";
import SingleCabeleireiro from "./pages/SingleCabeleireiro";
import Error from "./pages/Error";

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/", state: { from: props.location } }} />
      )
    }
  />
);

const Routes = () => (
    <BrowserRouter>
   
    <Switch>
    <Route exact path="/" component={Login} />
    <Route path="/registarCliente" component={SignUpCliente} />
    <Route path="/registar" component={SignUpDono} />
    <PrivateRoute exact path="/cabeleireiros/" component ={Cabeleireiros} />
    <PrivateRoute exact path="/cabeleireiros/:slug" component=
            {SingleCabeleireiro} />
    <PrivateRoute exact path="/barbeiros/" component ={Barbeiros} />
    <PrivateRoute exact path="/barbeiros/:slug" component=
            {SingleBarbeiro} />        
    <PrivateRoute path="/home/" component ={Home} />
    <PrivateRoute exact path="/users/" component ={Users} />
    <PrivateRoute exact path="/gestao/" component ={Gestao} />
    <PrivateRoute exact path="/reservas/" component ={Reservas} />
    <PrivateRoute exact path="/marcacoes/" component ={ReservasDono} />
        <Route component={Error} />
    </Switch>
    </BrowserRouter>

);

export default Routes;