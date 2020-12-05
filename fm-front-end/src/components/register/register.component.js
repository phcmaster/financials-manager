import React, { Component } from "react";
import axios from 'axios';
import Alert from 'react-bootstrap/Alert'
import './Register.css'

export default class Register extends Component {

    state = {}

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
                this.setState({
                    register: true
                });
                console.log(res);
            }
        ).catch(
            err => {
                this.setState({
                    error: true,
                    mensagem: err.response.data.message
                });

                console.log(err.response.data.message);
            }
        );
         
    };

    onShowAlert = ()=>{
          window.setTimeout(()=>{
            this.setState({visible:false})
          },2000);       
      }


    render() {


        if(this.state.register){

            return (
              <div className="auth-wrapper">
              <div className="card">
              <div class="card-body">
        
                <form  onSubmit={this.handleSubmit}>

            <Alert variant="success" >
               Conta registrada com sucesso.
            </Alert>

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
                </div>
              </div>
            </div>
        )

    }else if(this.state.error){

        return (
          <div className="auth-wrapper">
          <div className="card">
          <div class="card-body">
            <form  onSubmit={this.handleSubmit}>
             <Alert hidden={this.state.visible} transition="fade" variant="warning" >
            
                    {this.state.mensagem}
            </Alert>


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

            </div>
          </div>
        </div>
    )


    }


        return (
          <div className="auth-wrapper">
          <div className="card">
          <div class="card-body">
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
                </div>
            </div>
        </div>

    )
    }
}