package Member;
import java.io.Serializable;
import java.util.ArrayList;

public class MemberBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String account;
	private String password;
	private String status;
	
	private ArrayList<Integer> array_Id;
	private ArrayList<String> array_Name;
	private ArrayList<String> array_Account;
	private ArrayList<String> array_Password;
	private ArrayList<String> array_Status;
	
	
	
	public MemberBean() {
	}
	
	//新建會員用的
	public MemberBean(String name, String account, String password, String status) {
		super();
		this.name = name;
		this.account = account;
		this.password = password;
		this.status = status;
	}
	
	//燈入會員用的
	public MemberBean(String account, String password) {
		this.account = account;
		this.password = password;
	}
	//修改會員資格用的
	public MemberBean(int id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	//改密碼用的
	public MemberBean(String new_P, int userID, String userName) {
		this.password = new_P;
		this.id = userID;
		this.name = userName;
	}
	
	//查詢全部會員用的
	public MemberBean(ArrayList<Integer> array_Id, ArrayList<String> array_Name, ArrayList<String> array_Account,
			ArrayList<String> array_Password, ArrayList<String> array_Status) {
		super();
		this.array_Id = array_Id;
		this.array_Name = array_Name;
		this.array_Account = array_Account;
		this.array_Password = array_Password;
		this.array_Status = array_Status;
	}
	
	//改密碼用的
	public MemberBean(int id) {
		super();
		this.id = id;
	}
	
	
	
	
	//arrayList getter & setter


	public ArrayList<Integer> getArray_Id() {
		return array_Id;
	}

	public void setArray_Id(ArrayList<Integer> array_Id) {
		this.array_Id = array_Id;
	}

	public ArrayList<String> getArray_Name() {
		return array_Name;
	}

	public void setArray_Name(ArrayList<String> array_Name) {
		this.array_Name = array_Name;
	}

	public ArrayList<String> getArray_Account() {
		return array_Account;
	}

	public void setArray_Account(ArrayList<String> array_Account) {
		this.array_Account = array_Account;
	}

	public ArrayList<String> getArray_Password() {
		return array_Password;
	}

	public void setArray_Password(ArrayList<String> array_Password) {
		this.array_Password = array_Password;
	}

	public ArrayList<String> getArray_Status() {
		return array_Status;
	}

	public void setArray_Status(ArrayList<String> array_Status) {
		this.array_Status = array_Status;
	}

	//普通getter & setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
