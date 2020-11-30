import React, {Component} from 'react';
import axios from 'axios';
import { Redirect } from "react-router-dom";

export class Otp extends Component{

    state = {}

    // componentDidMount() {
  
    //     axios.get('auth/user').then(
       
    //            res => {
    //                   this.setState({
    //                       user: res.data
    //                   });
                   
    //            }
    //        ).catch(
    //            err => {
    //                console.log(err);
    //            }
    //        );
       
    //    }


    handleSubmit = e => {
            e.preventDefault();

        axios.post('informations/otp',  null, {
            params: {
                email: 'phcmaster4@gmail.com', //pegar o email do usuario
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
    

    render(){

        if(this.state.valid){
            return <Redirect to={'/change-password'} />;
        }
            
        return (
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
               
        )
    }
    }
    
