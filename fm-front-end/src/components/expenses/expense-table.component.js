import React, { Component } from "react";
import Table from 'react-bootstrap/Table'
import axios from 'axios';
import { Link } from "react-router-dom";

export default class ExpenseTable extends Component {

    state = { expenses: [] };

    componentDidMount = () => {

        axios.get('http://localhost:8082/expenses/all').then(

            res => {
                this.setState({
                    expenses: res.data
                });

            }
        ).catch(
            err => {
                console.log(err);
            }
        );

    }


    refreshPage() {
        window.location.reload(true);
    }


    handleDelete = async (idExpense) => {
     
        const resp = await axios.delete('http://localhost:8082/expenses/delete/'+ idExpense)
        try {

            this.setState({
                register: true
            });
            console.log(resp.data);
            this.refreshPage();


        } catch (err) {
            this.setState({
                error: true,
            });

            console.log(err.response.data.message);
        }

    }

    render() {
        return (

            <div className="container">

                <Table striped bordered hover size="sm">

                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                            <th>Data de vencimento</th>
                            <th>Valor</th>
                            <th>Parcelado</th>
                            <th>Vezes</th>
                            <th>Editar/Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.expenses.map(ex => (
                           
                            <tr>
                                <td>{ex.idExpense}</td>
                                <td>{ex.expenseName}</td>
                                <td>{ex.dueDate}</td>
                                <td>{ex.value}</td>
                                <td>{ex.installment ? "Sim" : "Não"}</td>
                                <td>{ex.installmentTimes}</td>
                                <td>
                                    <div className="row justify-content-center">
                                        <div className="col-4">
                                            <Link to="/expenses-edit/${ex.idExpense}">  <button className="btn  btn-warning btn-block" size="sm">Editar</button></Link>
                                    </div>
                                    <div className="col-4">
                                               <button onClick={() => this.handleDelete(ex.idExpense)} className="btn btn-danger btn-block" size="sm">Excluir</button>
                                            </div>
                                        </div>
                            </td>
                        </tr>
                                ))} 
                    </tbody>
                </Table>
        
            </div>


                    )
                }
            }
            
