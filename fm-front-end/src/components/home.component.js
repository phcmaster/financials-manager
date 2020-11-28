import React, { Component } from "react";
import axios from 'axios';

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
                <h2>Hi {this.props.user.name} </h2>             
            )
        }else{
            return (
                <h2>You are not logged in.</h2>
            )              

        }        
        

    }
}