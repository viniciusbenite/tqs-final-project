import React, { Component } from "react";
import { Link } from "react-router-dom";
import { logout } from "../services/auth";

export default class NavbarDono extends Component {
    state = {
        isOpen: false
    };
    handleToggle = () => {
        this.setState({ isOpen: !this.state.isOpen });
    };
    handleCheck(e) {
        logout();
     }

    render() {
        return (
            <nav className="navbar">
                <div className="nav-center">
                    <div className="nav-header">
                        
                       
                    </div>
                    <ul
                        className={this.state.isOpen ? "nav-links show-nav" : "nav-links"}
                    >
                        <li>
                            <Link to="/home/">Home</Link>
                        </li>
                        
                        <li>
                            <Link to="/marcacoes/">Reservas do meu Salão</Link>
                        </li>
                        <li>
                            <Link to="/gestao/">Gestão</Link>
                        </li>
                        <li onClick={this.handleCheck.bind(this)}><Link to="/">Logout</Link></li>
                    </ul>
                </div>
            </nav>
        );
    }
}
