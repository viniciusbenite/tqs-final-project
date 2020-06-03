import React from 'react';
import './App.css';

import Home from "./pages/Home";
import Barbeiros from "./pages/Barbeiros";
import Gestao from "./pages/Gestao";
import Reservas from "./pages/Reservas";
import Login from "./pages/Login";
import SingleBarbeiro from "./pages/SingleBarbeiro";
import Cabeleireiros from "./pages/Cabeleireiros";
import Users from "./pages/Users";
import Routes from "./Routes";
import SingleCabeleireiro from "./pages/SingleCabeleireiro";
import Error from "./pages/Error";

import { Route, Switch } from "react-router-dom";


class App extends Component {
    render() {
      return (
        <Router>
          <Route exact path="/home/" component={Login} />
          
        </Router>
      );
    }
  }
  
  export default App;
