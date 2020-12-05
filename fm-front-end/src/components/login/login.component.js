import React, { Component } from "react";
import axios from 'axios';
import { Link, Redirect } from "react-router-dom";
import Alert from 'react-bootstrap/Alert';
import './Login.css'


export default class Login extends Component {
       
    state = {}

    handleSubmit = e => {
        e.preventDefault();

        const data = {
            email: this.email,
            password: this.password
        };

        axios.post('auth/login', data).then(

            res => {
                localStorage.setItem('token', res.data.token);
                this.setState({
                    loggedIn: true,
                    error: false
                });
                this.handleUser();
            }
        ).catch(
            err => {
                this.setState({
                    loggedIn: false,
                    error: err.response.status
                });
                console.log(err);
            }
        );
         
    };


handleUser(){

    axios.get('auth/user').then(

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


    if(this.state.loggedIn){
        return <Redirect to={'/dashboard'} />; //dashboard
    
    }else if(this.state.error === 400) {

        
        return (
      
            

            <div className="auth-wrapper">
            <div className="card">
            <div class="card-body">
            <form  onSubmit={this.handleSubmit}>

            <Alert variant="danger">
                Senha e/ou email incorretos.
            </Alert>

            <h3>Login</h3>
    
            <div className="form-group">
              <label>Email:</label>
              <input type="email" className="form-control" placeholder="Email" 
              onChange={e => this.email = e.target.value} />
            </div>
    
            <div className="form-group">
              <label>Senha:</label>
              <input type="password" className="form-control" placeholder="Senha"
              onChange={e => this.password = e.target.value} />
            </div>
    
            <button className="btn btn-primary btn-block">Entrar</button>
                <p className="forgot-password text-center">
                    <Link to={'/forgotPassword'}> Esqueceu a senha ?</Link>
                </p>
        </form>
      

           </div>
        </div>
    </div>


            
     )

    }else{
        
        return (
      
            <div className="auth-wrapper">
            <div className="card">
            <div class="card-body">
            <form  onSubmit={this.handleSubmit}>    

            <h3>Login</h3>
    
            <div className="form-group">
              <label>Email:</label>
              <input type="email" className="form-control" placeholder="Email" 
              onChange={e => this.email = e.target.value} />
            </div>
    
            <div className="form-group">
              <label>Senha:</label>
              <input type="password" className="form-control" placeholder="Senha"
              onChange={e => this.password = e.target.value} />
            </div>
    
            <button className="btn btn-primary btn-block">Entrar</button>
                <p className="forgot-password text-center">
                    <Link to={'/forgotPassword'}> Esqueceu a senha ?</Link>
                </p>
        </form>
                </div>
            </div>
        </div>
          )
        
        }
   
    }
}