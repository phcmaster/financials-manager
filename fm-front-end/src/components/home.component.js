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
                This is a modified jumbotron that occupies the entire horizontal space of
                its parent.
                </p>
            </Container>
            </Jumbotron>

            )
        }else{
            return (
                <Jumbotron fluid>
                <Container>
                <h2>Hello world!</h2>
                    <p>
                    This is a modified jumbotron that occupies the entire horizontal space of
                    its parent.
                    </p>
                </Container>
                </Jumbotron>
            )      

        }        
        

    }
}