import React, { Component } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";
import './Dashboard.css';
import Table from 'react-bootstrap/Table'
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';


export default class Dashboard extends Component {

    state = {};


    componentDidMount = () => {

        axios.get('http://localhost:8080/investment/currencyList').then(

            res => {
                this.setState({
                    dolarName: res.data.USD.name,
                    dolarCode: res.data.USD.code,
                    dolarHigh: res.data.USD.high,
                    euroName: res.data.EUR.name,
                    euroCode: res.data.EUR.code,
                    euroHigh: res.data.EUR.high,
                    libraName: res.data.GBP.name,
                    libraCode: res.data.GBP.code,
                    libraHigh: res.data.GBP.high
                });
                console.log(res.data)

            }
        ).catch(
            err => {
                console.log(err);
            }
        );


        axios.get('http://localhost:8081/auth/user').then(

            res => {
                this.props.setUser(res.data);

            }
        ).catch(
            err => {
                console.log(err);
            }
        );


        axios.get('http://localhost:8082/expenses/monthlySpend').then(

            res => {
                this.setState({
                    monthlyValueSpend: res.data.monthlyValueSpend,
                    monthlyAverageSpend: res.data.monthlyAverageSpend,

                });
                console.log(res)

            }
        ).catch(
            err => {
                console.log(err);
            }
        );

    };



    render() {

        return (

            <div className="container-fluid">
                <div className="row justify-content-center mt-5">
                    <div className="container container-button text-center mt-5">
                        <Link to="/expenses-main">  <Button variant="primary ml-5" size="sm">Despesas</Button></Link>
                    </div>
                    <h2 className="mt-5">Olá, seja bem vindo.</h2>
                </div>

                <div className="row justify-content-center mt-2">

                    <div className="col-4 text-center mt-5">
                        <Card>
                            <Card.Header>Gasto mensal</Card.Header>
                            <Card.Body>
                                <Card.Text>
                                    <h4>

                                    {new Intl.NumberFormat("pt-BR", {
                                        style: "currency",
                                        currency: "BRL"
                                    }).format(this.state.monthlyValueSpend > 0 ? this.state.monthlyValueSpend : 0)}
                                    </h4>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </div>
                    <div className="col-4 text-center mt-5">
                        <Card>
                            <Card.Header>Gasto médio mensal</Card.Header>
                            <Card.Body>
                                <Card.Text>
                                    <h4>
                                    {new Intl.NumberFormat("pt-BR", {
                                        style: "currency",
                                        currency: "BRL"
                                    }).format(this.state.monthlyAverageSpend > 0 ? this.state.monthlyAverageSpend : 0)}
                                    </h4>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </div>
                </div>
                <div className="col-12 justify-content-center mt-5">
                    <Card>
                        <Card.Header>Investimentos - Moedas</Card.Header>
                        <Card.Body>
                            <Table striped bordered hover size="sm">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Moeda</th>
                                        <th>Código</th>
                                        <th>Preço</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>{this.state.dolarName}</td>
                                        <td>{this.state.dolarCode}</td>
                                        <td>{this.state.dolarHigh}</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>{this.state.euroName}</td>
                                        <td>{this.state.euroCode}</td>
                                        <td>{this.state.euroHigh}</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>{this.state.libraName}</td>
                                        <td>{this.state.libraCode}</td>
                                        <td>{this.state.libraHigh}</td>
                                    </tr>
                                </tbody>
                            </Table>
                        </Card.Body>
                    </Card>
                </div>
                <footer className="footer mt-3 text-center"><h5>FM - 2020</h5></footer>
            </div>


        )



    }

}