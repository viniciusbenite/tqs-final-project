import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import getUsers from "../services/auth";




import { Form, Container } from "./styles";




class Login extends Component {
  state = {
    email: "",
    password: "",
    error: ""
  };

  handleSignIn = async e => {
    e.preventDefault();
    const { email, password } = this.state;
    if (!email || !password) {
      this.setState({ error: "Preencha e-mail e senha para continuar!" });
    } else {
      try {

        getUsers().then(data => {
    
          data.map(user => {
            if(user.email==email && user.password==password) { this.props.history.push("/home/"); localStorage.setItem("user_email", user.email) ; localStorage.setItem("user_type", user.type)}
            else this.setState({
              error:
                "Houve um problema com o login, verifique suas credenciais...."
            });
          })

       }).catch((err) => {
         console.log(err);
         
       });
        
          
            
          
      

      } catch (err) {
        this.setState({
          error:
            "Houve um problema com o login, verifique suas credenciais."
        });
      }
    }
  };


  render() {
    console.log(localStorage.getItem("user_type"));
    return (
      <Container>
        <Form onSubmit={this.handleSignIn}>
          
          {this.state.error && <p>{this.state.error}</p>}
          <input
            type="email"
            placeholder="Endereço de e-mail"
            onChange={e => this.setState({ email: e.target.value })}
          />
          <input
            type="password"
            placeholder="Senha"
            onChange={e => this.setState({ password: e.target.value })}
          />
          <button type="submit">Entrar</button>
          <hr />
          <Link to="/signup">Criar conta grátis</Link>
        </Form>
      </Container>
    );
  }
}

export default withRouter(Login);