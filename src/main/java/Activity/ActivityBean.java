package Activity;

import java.io.Serializable;

public class ActivityBean implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name;
  private String birthyear, birthmonth, birthday;
  private String id;
  private String zipcode;
  private String address;
  private String phone;
  private String mailaddress;
  
  public ActivityBean()
  {
  }
  
  public ActivityBean(String pName, String pBirthyear, String pBirthmonth, String pBirthday,
                 String pId, String pZipcode, String pAddress, String pPhone, String pMailaddress)
  {
    this.name = pName;
    this.birthyear = pBirthyear;
    this.birthmonth = pBirthmonth;
    this.birthday = pBirthday;
    this.id = pId;
    this.zipcode = pZipcode;
    this.address = pAddress;
    this.phone = pPhone;
    this.mailaddress = pMailaddress;
  }
  
 

  public void setName(String pName)
  {
    this.name=pName;
  }

  public String getName()
  {
    return name;
  }

  public void setBirthyear (String pBirthyear)
  {
    this.birthyear=pBirthyear;
  }

  public String getBirthyear()
  {
    return birthyear;
  }

  public void setBirthmonth (String pBirthmonth)
  {
    this.birthmonth=pBirthmonth;
  }

  public String getBirthmonth()
  {
    return birthmonth;
  }

  public void setBirthday (String pBirthday)
  {
    this.birthday=pBirthday;
  }

  public String getBirthday()
  {
    return birthday;
  }

  public void setId (String pId)
  {
    this.id=pId;
  }
  
  public String getId()
  {
    return id;
  }

  public void setZipcode (String pZipcode)
  {
    this.zipcode=pZipcode;
  }
  
  public String getZipcode()
  {
    return zipcode;
  }

  public void setAddress (String pAddress)
  {
    this.address=pAddress;
  }

  public String getAddress()
  {
    return address;
  }

  public void setPhone (String pPhone)
  {
    this.phone=pPhone;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setMailaddress (String pMailaddress)
  {
    this.mailaddress=pMailaddress;
  }

  public String getMailaddress()
  {
    return mailaddress;
  }

}