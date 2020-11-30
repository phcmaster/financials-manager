import React, {Component} from 'react';
import axios from 'axios';
import { Redirect } from "react-router-dom";


export class ChangePassword extends Component{

    render(){

        // if(this.state.sended){
        //     return <Redirect to={'/'} />;
        // }

        return (
            <form  onSubmit={this.handleSubmit}>
            <h3>Nova senha</h3>

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
      
            <button className="btn btn-primary btn-block">Alterar</button>
    
        </form>
               
        )
    }


}