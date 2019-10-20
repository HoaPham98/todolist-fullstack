console.log("Hello world");
import React from 'react';
import ReactDOM from 'react-dom';
import './styles.css'
import Todolist from './App'

ReactDOM.render(
	(
	<div className="mx-auto">
		<Todolist/>
	</div>
	), document.getElementById("root")	

);