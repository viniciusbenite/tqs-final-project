import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";


import api from "../services/api";

import { Form, Container } from "./styles";

class SignUpDono extends Component {
  state = {
    name: "",
    email: "",
    password: "",
    type: "dono",
    error: ""
  };

  handleSignUp = async e => {
    e.preventDefault();
    const { name, email, password, type } = this.state;
    if (!name || !email || !password) {
      this.setState({ error: "Preencha todos os dados para se registar" });
    } else {
      try {
        await api.post("/user/", { name, email, password,type });
        this.props.history.push("/");
      } catch (err) {
        console.log(err);
        this.setState({ error: "Ocorreu um erro ao registrar sua conta." });
      }
    }
  };

  render() {
    return (
      <Container>
        <Form onSubmit={this.handleSignUp}>
         
          {this.state.error && <p>{this.state.error}</p>}
          <input
            type="text"
            placeholder="Nome"
            onChange={e => this.setState({ name: e.target.value })}
          />
          <input
            type="email"
            placeholder="Endereço de e-mail"
            onChange={e => this.setState({ email: e.target.value })}
          />
          <input
            type="password"
            placeholder="Password"
            onChange={e => this.setState({ password: e.target.value })}
          />
          
          <button type="submit">Registar</button>
          <hr />
          <Link to="/">Fazer login</Link>
        </Form>
      </Container>
    );
  }
}

export default withRouter(SignUpDono);