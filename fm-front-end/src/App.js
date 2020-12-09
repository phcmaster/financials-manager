import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import Home from './components/home/home.component'
import Nav from './components/nav/nav.component'
import Login from './components/login/login.component'
import Register from './components/register/register.component'
import axios from 'axios';
import { Forgot } from './components/login/forgot.component';
import { Otp } from './components/login/otp.component';
import { ChangePassword } from './components/login/changePassword.component';
import Dashboard  from './components/dashboard/dashboard.component';
import ExpenseRegister from './components/expenses/expense-register.component';
import ExpenseMain from './components/expenses/expense-main.component';
import ExpenseEdit from './components/expenses/expense-edit.component';


export default class App extends Component {

  state = {};

  componentDidMount = () => {

    axios.get('http://localhost:8081/auth/user').then(

      res => {
        this.setUser(res.data);

      }
    ).catch(
      err => {
        console.log(err);

      }
    );

  };


  setUser = user => {
    this.setState({
      user: user
    });

  };


  render() {
    return (

      <BrowserRouter>
        <div className="container mb-5">
          <Route exact path="/" component={Home} />
        </div>
        <div className="App">
          <Nav user={this.state.user} setUser={this.setUser} />
          <Switch>
            <Route exact path="/login" component={() => <Login setUser={this.setUser} />} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/expenses-register" component={ExpenseRegister} />
            <Route exact path="/expenses-main" component={ExpenseMain} />
            <Route exact path="/expenses-edit/:id" component={ExpenseEdit} />
            <Route exact path="/dashboard" component={() => <Dashboard user={this.state.user} />} />
            <Route exact path="/forgotPassword" component={Forgot} />
            <Route exact path="/otp" component={() => <Otp user={this.state.user} />} />
            <Route exact path="/change-password" component={() => <ChangePassword user={this.state.user} />} />
          </Switch>
        </div>
      </BrowserRouter>

    );

  }
}