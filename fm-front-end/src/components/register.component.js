import React, { Component } from "react";
import axios from 'axios';

export default class Register extends Component {

    handleSubmit = e => {
        e.preventDefault();

        const data = {
            name: this.name,
            email: this.email,
            password: this.password,
            confirmPassword: this.confirmPassword
        };
        console.log(data);

        axios.post('register/createNewAccount', data).then(

            res => {
                console.log(res);
            }
        ).catch(
            err => {
                console.log(err);
            }
        );
         
    };


    render() {
        return (
        
            <form  onSubmit={this.handleSubmit}>
            <h3>Registre sua conta</h3>

            <div className="form-group">
              <label>Nome completo:</label>
              <input type="text" className="form-control" placeholder="Nome completo"
              onChange={e => this.name = e.target.value} />
            </div>

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

            <div className="form-group">
              <label>Confirmação da Senha:</label>
              <input type="password" className="form-control" placeholder="Confirmação da Senha"
              onChange={e => this.confirmPassword = e.target.value} />
            </div>

            <button className="btn btn-primary btn-block">Registrar-se</button>

        </form>
    )
    }
}