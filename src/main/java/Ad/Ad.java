package Ad;
import java.io.*;

public class Ad implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aId;
    private String aName;
    private String aPost;
 
    //Constructor
    public Ad(int aId, String aName, String aPost) {
      this.aId = aId;
      this.aName = aName;
      this.aPost = aPost;
    }

    public int getAid() {
      return this.aId ;
    }

    public void setAid(int aId) {
    	this.aId = aId;
    }
    
    public String getAname() {
      return this.aName ;
    }

    public void setAname(String aName) {
      this.aName = aName;
    }
    
    public String getApost() {
    	return this.aPost ;
    }
    
    public void setApost(String aPost) {
    	this.aPost = aPost;
    }


}