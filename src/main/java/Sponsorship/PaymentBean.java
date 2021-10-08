package Sponsorship;

import java.io.Serializable;

public class PaymentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int orderNumber;
	private String sponsorName;
	private int projectID;
	private String projectName;
	private int amount;

	public PaymentBean() {
		// TODO Auto-generated constructor stub
	}

	public PaymentBean(int nOrderNumber, String nSponsorName, int nProjectID, String nProjectName, int nAmount) {
		this.orderNumber = nOrderNumber;
		this.sponsorName = nSponsorName;
		this.projectID = nProjectID;
		this.projectName = nProjectName;
		this.amount = nAmount;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int nOrderNumber) {
		this.orderNumber = nOrderNumber;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String nSponsorName) {
		this.sponsorName = nSponsorName;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int nProjectID) {
		this.projectID = nProjectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String nProjectName) {
		this.projectName = nProjectName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int nAmount) {
		this.amount = nAmount;
	}

}
