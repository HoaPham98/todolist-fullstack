package com.hiep.todolist.service;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hiep.todolist.dao.ItemDAO;
import com.hiep.todolist.entity.Item;
import org.glassfish.jersey.internal.inject.Custom;
import org.hibernate.HibernateException;

@Path("/todolist")
public class TodoListService {

	@RolesAllowed("admin")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> itemJSONList() throws HibernateException, ClassNotFoundException{
		List<Item> items = ItemDAO.listItem();
		return items;
	}
	
	@RolesAllowed("admin")
	@POST
	@Path("/{item}")
	public void addItem(@PathParam("item")String item) throws HibernateException, ClassNotFoundException{
		 ItemDAO.addItem(item);
	}
	
	@DELETE
	@Path("/{id}")
	public void delItem(@PathParam("id") int id) throws HibernateException, ClassNotFoundException{
		ItemDAO.delItem(id);
	}	
}
