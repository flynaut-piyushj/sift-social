package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ServiceException;

public class Recipients
{
	public Recipients() {
	}
	
	@JsonProperty("list_id")
	private String listId;
	
	 public Recipients(String listId)
	 {
		 if(listId==null || listId=="")
			 throw new ServiceException("In Recipients: listId");
		 else
			 this.listId=listId;
	 }
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	@Override
	public String toString() {
		return "Recipients [listId=" + listId + "]";
	}
	
	
}
