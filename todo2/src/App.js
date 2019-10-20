import React from 'react';
import axios from 'axios';
import ItemList from './Home'
import Login from './Login/Login'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import './styles.css'

const Fragment = React.Fragment;
var login='dinhhiep';
var password='dinhhiep';
// const  api_url ="todo/"
// import cors from 'cors';

 // axios.defaults.baseURL = 'http://localhost:3000';
 // axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
 // axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
// export default class App extends React.Component{
// 	constructor(props){
// 		super(props);
// 		this.state={
// 			items:[]
// 		};
// 		this.onSubmit = this.onSubmit.bind(this);
// 	}
	
// 	componentDidMount(){
// 		// fetch("http://localhost:8080/todo/todolist")
// 		// .then(function(response) {
//   //   		return response.json();
//   // 		})
// 		// .then((data)=>{
// 		// 	this.setState({items: data.item});
// 		// 	console.log(this.state.items);
// 		// })
// 		console.log(login);
// 		axios.get("http://localhost:8080/todolist", {
// 		 	mode: 'no-cors'
// 		}).then(response => {
// 		  if (!response.ok) throw new Error(response.status);
// 		  return response.json();
// 		}).then((data)=>{
// 			console.log(data);
// 		})
// 	}

// 	onSubmit(){
// 	    fetch("http://localhost:8080/todolist")
// 	    .then(function(response) {
//     		return response.json();
//   		})
// 		.then((data)=>{
// 			this.setState({items: data.item});
// 			console.log(this.state.items);
// 		})
//   	}

// 	render(){
// 		return(
// 			<Router>
//       <div>

//         {/* A <Switch> looks through its children <Route>s and
//             renders the first one that matches the current URL. */}
//         <Switch>
//           <Route path="/login">
//             <Login />
//           </Route>
//           <Route path="/todolist">
//             <Todolist />
//           </Route>
//         </Switch>
//       </div>
//     </Router>
// 		);
// 	}
// }


export default function BasicExample() {
	return(
			<Router>
      <div>
<ul>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/todolist">Todo List</Link>
          </li>
        </ul>

        <hr />
        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/todolist">
            <ItemList />
          </Route>
        </Switch>
      </div>
    </Router>
		);
}