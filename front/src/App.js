import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import './App.css';
import HomePage from './home/Home';
import Login from './login/Login';

export default class App extends React.Component {
  // static propTypes = {
  //   children: PropTypes.any.isRequired
  // };
  // static path = '/';

  render(){
    return (
      <div className="App">
        <Router>
          {/* <div>
            <ul>
              <li>
                <Link to="/login">Home</Link>
              </li>
              <li>
                <Link to="/home">About</Link>
              </li>
             
            </ul>

            <hr /> */}

            <Route exact path="/" component={Login} />
            <Route path="/home" component={HomePage} />
          {/* </div> */}
        </Router>
      </div>
    );
  }
}


