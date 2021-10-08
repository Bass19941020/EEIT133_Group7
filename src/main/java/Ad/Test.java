// Value Object:一個Object代表Dept Table一筆Row
package Ad;
import java.io.*;

public class Test implements Serializable{

    private int aId;
    private String aName;
    private String aPost; //新加的
 
    //Constructor
    public Test(int aId, String aName, String aPost) { //有改
      this.aId = aId;
      this.aName = aName;
      this.aPost = aPost;
    }

    public int getAid() {
      return this.aId ;
    }

    public void setAid(int aId) { //新加的
    	this.aId = aId;
    }
    
    public String getAname() {
      return this.aName ;
    }

    public void setAname(String aName) {
      this.aName = aName;
    }
    
    public String getApost() { //新加的
    	return this.aPost ;
    }
    
    public void setApost(String aPost) { //新加的
    	this.aPost = aPost;
    }


}
