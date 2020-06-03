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
         
          if(data.length!=0){
    
          data.map(user => {
            if(user.email==email && user.password==password) { console.log("devia entrar"); localStorage.setItem("user_email", user.email) ; localStorage.setItem("user_type", user.type); this.props.history.push("/home/"); }
            else this.setState({
              error:
                "Houve um problema com o login, verifique suas credenciais...."
            });
          })
        }
        else this.setState({
          error:
            "Houve um problema com o login, verifique suas credenciais...."
        });

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
    
    return (
      <Container>
        <Form onSubmit={this.handleSignIn}>
          
          {this.state.error && <p>{this.state.error}</p>}
          <h3>PÊLO</h3>
          <h3></h3>
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
          <h5></h5>
          <button type="submit">Entrar</button>
          <h5></h5>
          <hr />
          <h5></h5>
          <Link to="/registarCliente">Criar conta como Cliente</Link>
         
          <h5></h5>
          <Link to="/registar">Criar conta como Dono</Link>
        </Form>
        </Container>
    );
  }
}

export default withRouter(Login);