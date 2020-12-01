import React, { Component } from "react";
import {Link} from "react-router-dom";

export default class Nav extends Component{

handlerLogOut = () => {
    localStorage.clear();
    this.props.setUser(null);

};

    render(){

        let buttons;

        if(this.props.user){

            buttons = (
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link  to={'/login'} onClick={() => this.handlerLogOut()} className="nav-link">Logout</Link>
                    </li>
                </ul>
        )

        }else{
            buttons = (
                <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                    <Link className="nav-link" to={'/login'}>Login</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link"to={'/register'}>Sign up</Link>
                </li>
            </ul>
        )
   
        }
        return (

    <div className="Nav">
        <nav className="navbar navbar-expand navbar-light fixed-top">
            <div className="container">
                <div className="collapse navbar-collapse">
              {buttons}
                </div>
            </div>
        </nav>
    </div>
            
    )
}
}