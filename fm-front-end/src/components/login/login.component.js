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

        axios.post('http://localhost:8081/auth/login', data).then(

            res => {
                localStorage.setItem('token', res.data.token);
                this.setState({
                    loggedIn: true,
                    error: false
                });
                this.handleUser();
                this.refreshPage();
            }
        ).catch(
            err => {
                this.setState({
                    error: true
                });
                console.log(err);
            }
        );

    };


    refreshPage() {
        window.location.reload(true);
    }


    handleUser() {

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

    render() {


        if (this.state.loggedIn) {
            return <Redirect to={'/dashboard'} />; //dashboard

        } else {


            return (

                <div className="auth-wrapper">
                    <div className="card">
                        <div class="card-body">
                            <form onSubmit={this.handleSubmit}>

                                <Alert hidden={this.state.error ? false : true} variant="danger">
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

        }

    }
}