import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class Nav extends Component {

    handlerLogOut = () => {
        localStorage.clear();
        this.props.setUser(null);

    };

    render() {

        let buttons;
        let homeDash;

        if (this.props.user) {

            homeDash = (

                <li className="nav-item">
                <Link className="nav-link" to={'/dashboard'}>Dashboard</Link>
                </li>
            )

            buttons = (
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to={'/login'} onClick={() => this.handlerLogOut()} className="nav-link">Logout</Link>
                    </li>
                </ul>




            )

        } else {

            homeDash = (

                <li className="nav-item">
                <Link className="nav-link" to={'/'}>Home</Link>
                </li>
            )


            buttons = (
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to={'/login'}>Login</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to={'/register'}>Sign up</Link>
                    </li>
                </ul>
            )

        }
        return (

            <div className="Nav">
                <nav className="navbar navbar-expand navbar-light fixed-top">
                    <div className="container">
                        <ul className="navbar-nav ml-auto">
                           {homeDash}
                        </ul>
                        <div className="collapse navbar-collapse">
                            {buttons}
                        </div>
                    </div>
                </nav>
            </div>

        )
    }
}