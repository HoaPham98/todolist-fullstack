import React from 'react';
import './styles.css';
import axios from 'axios';
import Todolist from './../Todolist/Todolist'
import PostItem from './../PostItem/PostItem'
const api_url="/api"
class ItemList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items: []
    };

    this.onSubmit = this.onSubmit.bind(this)
    
  }

  componentDidMount() {
      var token = localStorage.getItem('token')
    axios({ url: api_url+"/todolist", method: 'get', headers : {'authorization' : `Bearer ${token}`}})
    .then(response => response.data)
    .then((data)=>{
      this.setState({items: data});
      console.log(this.state.items);
    })
  }

  onSubmit() {
    var token = localStorage.getItem('token')
    axios({ url: api_url+"/todolist", method: 'get', headers : {'authorization' : `Bearer ${token}`}}).then(response => response.data)
    .then((data)=>{
      this.setState({items: data});
      console.log(this.state.items);
    })
  }


  render() {
    return(
      <div>
        <PostItem onSubmit={this.onSubmit}/>
        <Todolist tasks={this.state.items} onSubmit={this.onSubmit}/>
      </div>
    )
  }
}
export default ItemList;