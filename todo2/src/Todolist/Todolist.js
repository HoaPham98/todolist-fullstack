import React from 'react';
import axios from 'axios';
import './styles.css'
import PostItem from './../PostItem/PostItem'
// const  api_url ="todo/"
// import cors from 'cors';

 // axios.defaults.baseURL = 'http://localhost:3000';
 // axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
 // axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';


export default class Todolist extends React.Component{
	constructor(props){
		super();
		this.handleClick = this.handleClick.bind(this);

	}

	handleClick(e) {
		e.preventDefault()
		var token = localStorage.getItem('token')
		if (token != null ) {
			var id= e.target.getAttribute("id")
			axios({ url: `/api/todolist/${id}`, method: 'delete',  headers : {'authorization' : `Bearer ${token}`}})
			.then( (data) => {
					this.props.onSubmit()
			});
		} else {
			alert("Chưa đăng nhập")
			var history = useHistory()
			history.push('/login')
		}

	}

	render(){
		let items = [];
		if (this.props.tasks) {
			items = this.props.tasks.map(function (item) {
				return (<li className="text-gray-700 text-left  px-4 py-2" key={item.id}>
							<span className="ml-2 font-bold">{item.id}  </span> 
							<span className="ml-2 font-bold">{item.itemName}     </span>
							<a className="flex-shrink-0 bg-teal-500 hover:bg-teal-700 border-teal-500 hover:border-teal-700 text-sm border-1 text-white py-1 px-2 rounded float-right" id={item.id} href="#" onClick={this.handleClick}>
								Delete
							</a>
						</li>
					);
			}, this);
		}
		return (
			<div className=" justify-center bg-gray-200 w-4/12 mx-auto">
				<ul>{items}</ul>
			</div>
		);	
	}
}
