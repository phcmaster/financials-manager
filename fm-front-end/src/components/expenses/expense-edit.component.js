import React, { Component } from "react";
import axios from 'axios';
import { Link, Redirect } from "react-router-dom";
import Button from 'react-bootstrap/Button';


export default class ExpenseEdit extends Component {


    render(){
        return(
            <div className="container-fluid">
                <h2>Edit </h2>
            <div className="row">
                <div className="container container-button text-center mt-5">
                    <Link to="/expenses-register">  <Button variant="primary ml-5" size="sm">Nova dispesa</Button></Link>
                </div>
            </div>
        </div>
        )
    }
}