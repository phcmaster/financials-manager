import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';



axios.defaults.baseURL='http://localhost:8081/';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');

ReactDOM.render(<App />, document.getElementById('root'));