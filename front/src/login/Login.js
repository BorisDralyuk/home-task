import React from 'react';
import { Redirect } from "react-router-dom";
import './Login.css';
import { login} from '../utils/service.api';


export default class Login extends React.Component {

	constructor(props){
		super(props)
		this.state = {
			userId: '',
			redirect: false
		}
	}

	setCookie = (userId) => {
		var userId = `userId=${userId};`;
		document.cookie = userId;
	}

	removeCookies = () => {
		var res = document.cookie;
		var multiple = res.split(";");
		for (var i = 0; i < multiple.length; i++) {
			var key = multiple[i].split("=");
			document.cookie = key[0] + " =; expires = Thu, 01 Jan 1970 00:00:00 UTC";
		}
	}

	submit = async() => {
		await login(this.state.userId)
				.then( d => {
					if (d) this.setState({ redirect: true })
				})
				.catch(err => alert(err))
	}

	inputChange = (e) => {
		this.setState({
			userId: e.target.value
		})
	}

	render() {
		const { redirect } = this.state;
		
		if (redirect) {
			this.removeCookies();
			this.setCookie(this.state.userId)
			return <Redirect to="/home" />;
		}

		return (
			<div className="Login">
				<div className="Login-card">
					<input className="Login-card__text" type="text" value={this.state.userId} onChange={this.inputChange} />
					<button className="Login-card__btn" disabled={this.state.userId===''} onClick={this.submit}>SignIn</button>
				</div>
			</div>
		)
	}
}
