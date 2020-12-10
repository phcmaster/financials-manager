import React, { Component } from "react";
import { Link } from "react-router-dom";
import Button from 'react-bootstrap/Button';
import ExpenseTable from "./expense-table.component";
import Card from 'react-bootstrap/Card'
import './Expenses.css'

export default class ExpenseMain extends Component {



    render() {

        return (
            <div className="container">
                <div className="row">
                    <h2 className="ml-5 mt-5"><strong>Despesas</strong></h2>
                </div>
                <div className="row">

                    <div className="container container-button text-center mt-5">

                        <Link to="/expenses-register">  <Button variant="primary ml-5" size="sm">Nova despesa</Button></Link>
                    </div>

                    <div className="container-fluid mt-3">

                        <Card>
                            <Card.Body>
                                <ExpenseTable />
                            </Card.Body>
                        </Card>

                    </div>
                </div>
                <footer className="footer mt-3 text-center"><h5>FM - 2020</h5></footer>
            </div>



        )
    }
}
