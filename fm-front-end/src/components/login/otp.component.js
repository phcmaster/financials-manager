import React, {Component} from 'react';
import axios from 'axios';
import { Redirect } from "react-router-dom";
import './Login.css'

export class Otp extends Component{

    state = {}

    handleSubmit = e => {
            e.preventDefault();

        axios.post('http://localhost:8081/informations/otp', null, {
            params: {
                email: this.props.user.userName, //pegar o email do usuario
                otp: this.otp
        
            }
        }).then(
            res => {
                this.setState({
                    valid: true
                });
              console.log(res);
            }
        ).catch(
            err => {
                console.log(err);
            }
        );
         
    };

    componentDidMount = () => {

 
        axios.get('http://localhost:8081/auth/user').then(

            res => {
                this.props.setUser(res.data);

            }
        ).catch(
            err => {
                console.log(err);
            }
        );

    
}

    render(){

        if(this.state.valid){
            return <Redirect to={'/change-password'} />;
        }
            
        return (
            <div className="auth-wrapper">
            <div className="card">
            <div class="card-body">
            <form  onSubmit={this.handleSubmit}>
            <h3>OTP</h3>
            <p className="mt-2">Foi enviado um email para que você confirme o código OTP e crie sua nova senha.</p>

            <div className="form-group">
              <label>Código:</label>
              <input type="text" className="form-control" placeholder="Código OTP"
              onChange={e => this.otp = e.target.value} />
            </div>
    
            <button className="btn btn-primary btn-block">Validar</button>

        </form>
                </div>
            </div>
        </div>
        
        )
    }
    }
    
