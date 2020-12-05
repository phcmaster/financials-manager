import React, {Component} from 'react';
import axios from 'axios';
import { Redirect } from "react-router-dom";
import './Login.css'

export class Forgot extends Component{

    state = {}

    handleSubmit = e => {
        e.preventDefault();
        

    axios.post('informations/forgot-password', null, {
        params: {
            email: this.email
        }
    }).then(
        res => {
            this.setState({
                sended: true
            });
            this.props.setUser(this.email);
            console.log(res);
        }
    ).catch(
        err => {
            console.log(err);
        }
      );
    };


    render(){

        if(this.state.sended){
            return <Redirect to={'/otp'} />;
        }

        return (
            
            <div className="auth-wrapper">
            <div className="card">
            <div class="card-body">
            <form  onSubmit={this.handleSubmit}>
            <h3>Esqueceu a senha</h3>

            <div className="form-group">
              <label>Email:</label>
              <input type="email" className="form-control" placeholder="Email" 
              onChange={e => this.email = e.target.value} />
            </div>  
            <button className="btn btn-primary btn-block">Enviar</button>
    
        </form>
                </div>
            </div>
        </div>
               
        )
    }
    
}

