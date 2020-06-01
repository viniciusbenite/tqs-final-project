import styled from "styled-components";
import img from '../images/login.jpg';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 87vh;
  background-size: fill;
  background-image: url(${img});

`;

export const Form = styled.form`
  width: 500px;
  background: #fff;
  padding: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  
  p {
    color: #ff3433;
    margin-bottom: 15px;
    border: 1px solid #ff3333;
    padding: 10px;
    width: 100%;
    text-align: center;
  }
  input {
    flex: 1;
    height: 46px;
    margin-bottom: 15px;
    padding: 0 20px;
    color: #777;
    font-size: 15px;
    width: 100%;
    border: 1px solid #ddd;
    &::placeholder {
      color: #999;
    }
  }
  button {
    color: #fff;
    font-size: 16px;
    background: #80002f;
    height: 56px;
    border: 0;
    border-radius: 5px;
    width: 100%;
  }
  hr {
    margin: 20px 0;
    border: none;
    border-bottom: 1px solid #cdcdcd;
    width: 100%;
  }
  a {
    font-size: 16;
    font-weight: bold;
    color: #999;
    text-decoration: none;
  }
`;