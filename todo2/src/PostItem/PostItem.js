import React from 'react';
import axios from 'axios';
import './styles.css'
import { useHistory } from 'react-router-dom'
// const api_url="/todo/"
class PostItem extends React.Component{
	constructor(props){
		super();
		this.state= {value: ''};
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event){
		this.setState({value : event.target.value});
	}

	handleSubmit(event){
		console.log("submited");
		var token = localStorage.getItem('token')
		if (token != null ) {
			axios({method: 'post', url: `/api/todolist/${this.state.value}`, headers : {'authorization' : `Bearer ${token}`}})
				.then( (data) => {
					this.props.onSubmit()
					this.setState({value: ''})
				});
		} else {
			alert("Chưa đăng nhập")
			var history = useHistory()
			history.push('/login')
		}
    	event.preventDefault();
    }
		
	

	render(){
		return(
			<div className="w-4/12 mx-auto justify-center bg-gray-200 ">
				<h1 className="text-indigo-600 text-2xl text-center ml-3 font-bold">TodoList app</h1>
				<form  onSubmit={this.handleSubmit} className="w-full" id="form1">
					<div className="flex px-4 py-2">
						<input className="shadow appearance-none border rounded leading-tight focus:outline-none focus:shadow-outline border-teal-900 w-56 border-red-900 rounded-sm border-black w-1 appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
						 type="text" value={this.state.value} onChange={this.handleChange}/>
						<button className="flex-shrink-0 bg-teal-500 hover:bg-teal-700 border-teal-500 hover:border-teal-700 text-sm border-4 text-white py-1 px-2 rounded float-right " type="submit" form="form1" value="Submit">Submit</button>
					</div>
				</form>
			</div>
		);
	}

}
export default PostItem;