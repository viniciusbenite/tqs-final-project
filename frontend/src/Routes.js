


import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import { isAuthenticated } from "./services/auth";



import Home from "./pages/Home";

import Barbeiros from "./pages/Barbeiros";
import Gestao from "./pages/Gestao";
import Reservas from "./pages/Reservas";
import Login from "./pages/Login";
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
    <Route exact path="/cabeleireiros/" component ={Cabeleireiros} />
    <Route exact path="/cabeleireiros/:slug" component=
            {SingleCabeleireiro} />
    <Route exact path="/barbeiros/" component ={Cabeleireiros} />
    <Route exact path="/barbeiros/:slug" component=
            {SingleBarbeiro} />        
    <Route path="/home/" component ={Home} />
    <Route exact path="/users/" component ={Users} />
    <Route exact path="/gestao/" component ={Gestao} />
    <Route exact path="/reservas/" component ={Reservas} />
    <Route exact path="/marcacoes/" component ={ReservasDono} />
        <Route component={Error} />
    </Switch>
    </BrowserRouter>

);

export default Routes;