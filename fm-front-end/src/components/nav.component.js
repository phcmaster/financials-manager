import React from "react";
import {Link} from "react-router-dom";

function Nav(){
        return (

<div className="Nav">
<nav className="navbar navbar-expand navbar-light fixed-top">
        <div className="container">
            <Link className="navbar-brand" to={'/'}>Home</Link>
            <div className="collapse navbar-collapse">
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to={'/login'}>Login</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link"to={'/register'}>Sign up</Link>
                    </li>
                </ul>
             </div>
        </div>
    </nav>

</div>
            
    )
}
export default Nav;