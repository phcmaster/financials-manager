import React, { Component } from "react";
import axios from 'axios';
import Jumbotron from 'react-bootstrap/Jumbotron'
import Container from 'react-bootstrap/Container'


export default class Home extends Component {
       
state = {};


componentDidMount() {
  
 axios.get('auth/user').then(

        res => {
               this.setState({
                   user: res.data
               });
            
        }
    ).catch(
        err => {
            console.log(err);
        }
    );

}
    render(){

        if(this.props.user){
            return (

            <Jumbotron fluid>
            <Container>
            <h2>Hi {this.props.user.name} </h2>
                <p>
               Bem vindo ao Financials Manager, vamos começar ?.
                </p>
            </Container>
            </Jumbotron>

            )
        }else{
            return (
                <Jumbotron className="text-center" fluid>
                <Container>
                <h1>Olá,</h1><h3>Bem vindo ao Financials Manager, vamos começar ?</h3>
                   
                </Container>
                </Jumbotron>
            )      

        }        
        

    }
}