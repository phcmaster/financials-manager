import React, { Component } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";
import Form from 'react-bootstrap/Form'
import Moment from 'moment';
import './Expenses.css';
import Button from 'react-bootstrap/Button';


export default class ExpenseRegister extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isChecked: true,
        };
    }


    handleSubmit = async (e) => {
        e.preventDefault();


        const data = {
            expenseName: this.expenseName,
            description: this.description,
            dueDate: Moment(this.dueDate).format('DD/MM/yyyy'),
            value: this.value,
            installment: this.installment,
            installmentTimes: this.installmentTimes,

        };

        const resp = await axios.post('http://localhost:8082/expenses/register', data)
        try {

            this.setState({
                register: true
            });
            console.log(resp.data);
            this.refreshPage();


        } catch (err) {

            this.setState({
                error: true,
                mensagem: resp.response.data.message
            });

            console.log(err.response.data.message);
        }


    }

    onKeyPress(event) {
        const keyCode = event.keyCode || event.which;
        const keyValue = String.fromCharCode(keyCode);
        if (/\+|-/.test(keyValue))
            event.preventDefault();
    }

    refreshPage() {
        window.location.reload(true);
    }


    toggleChange = () => {
        this.setState({
            isChecked: !this.state.isChecked,
        });
    }


    render() {

        return (

            <div className="auth-wrapper">
                <div className="card mt-5">
                    <h2 className="mt-2 ml-2">
                        <strong>
                            <Link to={'/expenses-main'}><Button variant="link">Voltar</Button></Link>
                        </strong> </h2>
                    <div class="card-body mt-2">

                        <form onSubmit={this.handleSubmit}>
                            <h3>Cadastrar nova despesa</h3>
                            <div className="form-group">
                                <label>Nome da despesa:</label>
                                <input type="text" className="form-control" placeholder="Nome da despesa"
                                    onChange={e => this.expenseName = e.target.value} />
                            </div>

                            <div className="form-group">
                                <label>Descrição:</label>
                                <Form.Control as="textarea" placeholder="Descrição" rows={3} onChange={e => this.description = e.target.value} />
                            </div>
                            <div className="form-group">
                                <label>Data de vencimento:</label>
                                <input type="date" className="form-control"
                                    onChange={e => this.dueDate = e.target.value} />
                            </div>
                            <div className="form-group">
                                <label>Valor:</label>
                                <input type="number" placeholder="Valor" className="form-control"
                                    onKeyPress={this.onKeyPress.bind(this)} onChange={e => this.value = e.target.value} />
                            </div>
                            <div className="form-group" onChange={e => this.installment = e.target.value}>
                                <label>Parcelado:</label>
                                <Form.Check checked={this.state.isChecked}
                                    onClick={this.toggleChange} className="ml-4" inline label="SIM" value="true" type="radio" />
                                <Form.Check checked={!this.state.isChecked}
                                    onClick={this.toggleChange} className="ml-4" inline label="NÃO" value="false" type="radio" />
                            </div>
                            <div className="form-group" hidden={!this.state.isChecked}>
                                <label>Quantas vezes ?</label>
                                <input type="number" value={!this.installment ? 1 : ''} placeholder="Quantas de vezes" className="form-control"
                                    onChange={e => this.installmentTimes = e.target.value} />
                            </div>
                            <div className="row justify-content-center">
                                <div className="col-3">
                                    <button className="btn btn-primary btn-block">Cadastrar</button>
                                </div>
                                <div className="col-3">
                                    <Link to="/expenses-main"> <button className="btn btn-danger btn-block">Cancelar</button> </Link>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        )






    }


}

