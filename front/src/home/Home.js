import React from 'react';
import './Home.css';
import { subscribe, users } from '../utils/service.api';


export default class HomePage extends React.Component{

	constructor(props) {
		super(props)
		this.state = {
			users: [],
			userId: undefined,
			userName: ''
		}
	}

	getCookie = (cname) => {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return "";
	}

	componentWillMount(){
		this.setState({
			userId: this.getCookie('userId')
		})
		this.refreshUsers();
	}

	refreshUsers = async() =>{
		try {
			const res = await users();
			this.setState({ users: res });
			this.setState({ userName: this.getUserName() });
		} catch (e) {
			alert(e)
		}
	}

	subsc = async(id, subscribeId) => {
		try{
			await subscribe(id, subscribeId)
			await this.refreshUsers()
		} catch(e) {
			alert(e)
		}
	}

	getUserName = () => {
		const res = this.state.users.find(i =>  i.id == this.state.userId);
		return res.name
	}

	isSubscription = (item) => {
		let uId = this.state.userId;
		const res = this.state.users.find(i => i.id == uId)
		const sub = res.subscribers.find(i => i == item.id)
		return (sub) 
			? <button className="Home-item__btn following" onClick={e => this.subsc(uId, item.id)} disabled={item.id == uId}><span>Following</span></button>
			: <button className="Home-item__btn follow" onClick={e => this.subsc(uId, item.id)} disabled={item.id == uId}><span>Follow</span></button>
	}

	render(){
		return(
			<div className="Home">
				<div className="Home-list">
					<h3>Welcome {this.state.userName}</h3>
					<div className="Home-title">
						<span>UserName</span>
						<span>GroupName</span>
						<span>Subscriptions</span>
						<span>Subscribe</span>
					</div>
					{
						this.state.users.map((item) =>
							<div key={item.id} className="Home-item">
								<span className="Home-item__user_name">{item.name}</span>
								<span className="Home-item__group_name">{item.group.name}</span>
								<span className="Home-item__subscribers">{item.subscriptions.length}</span>
								{ this.isSubscription(item) }
							</div>
						)
					}
				</div>
			</div>
		)
	}
}