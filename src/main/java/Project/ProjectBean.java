package Project;

public class ProjectBean {
	private String pID;
	private String pName;
	private int pTarget;
	private String pDescribe;
	private String pSDate;
	

	public ProjectBean() {

	}// 空的建構子
	
	
	
	public ProjectBean(String pID, String pName, int pTarget, String pDescribe,String pSDate) {//update 要用的，所以id都要
		this.pID = pID;
		this.pName = pName;
		this.pTarget = pTarget;
		this.pDescribe = pDescribe;
		this.pSDate = pSDate;
	}



	public ProjectBean(String pName, int pTarget, String pDescribe,String pSDate) {//create要用的，所以不用id，資料庫會自己給
		this.pName = pName;
		this.pTarget = pTarget;
		this.pDescribe = pDescribe;
		this.pSDate = pSDate;
	}
	


	public ProjectBean(String pID) {
		this.pID = pID;
	}


	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpID() {
		return pID;
	}


	public void setpID(String pID) {
		this.pID = pID;
	}

	public int getpTarget() {
		return pTarget;
	}

	public void setpTarget(int pTarget) {
		this.pTarget = pTarget;
	}

	public String getpDescribe() {
		return pDescribe;
	}

	public void setpDescribe(String pDescribe) {
		this.pDescribe = pDescribe;
	}



	public String getpSDate() {
		return pSDate;
	}



	public void setpSDate(String pSDate) {
		this.pSDate = pSDate;
	}
	
	
}
