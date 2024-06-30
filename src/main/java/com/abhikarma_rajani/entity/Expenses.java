package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "expenses")
public class Expenses
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String expensesId;
	private String itemName;
	private String itemQuantity;
	private String expenseAmount;
	private String sourceOfPurches;
	private String dateOfPurches;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExpensesId() {
		return expensesId;
	}
	public void setExpensesId(String expensesId) {
		this.expensesId = expensesId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getSourceOfPurches() {
		return sourceOfPurches;
	}
	public void setSourceOfPurches(String sourceOfPurches) {
		this.sourceOfPurches = sourceOfPurches;
	}
	public String getDateOfPurches() {
		return dateOfPurches;
	}
	public void setDateOfPurches(String dateOfPurches) {
		this.dateOfPurches = dateOfPurches;
	}
	public Expenses(long id, String expensesId, String itemName, String itemQuantity, String expenseAmount,
			String sourceOfPurches, String dateOfPurches) {
		super();
		this.id = id;
		this.expensesId = expensesId;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.expenseAmount = expenseAmount;
		this.sourceOfPurches = sourceOfPurches;
		this.dateOfPurches = dateOfPurches;
	}
	public Expenses() {
		super();
		// TODO Auto-generated constructor stub
	}
}
