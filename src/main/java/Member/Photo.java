package Member;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Photo
 */
@MultipartConfig
@WebServlet("/Photo")
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String savePath = "C:\\Java_workspace\\Gather\\src\\main\\webapp\\Member\\photo";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// gets absolute path of the web application
		System.out.println("進入photo成功");
//        String appPath = request.getServletContext().getRealPath("");
        
        // show where the appPath is in the directory of the operation system
        // constructs path of the directory to save uploaded file
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
		Part photo = request.getPart("photo");
		/*
		String fileName = extractFileName(photo);
		System.out.println(fileName);
		fileName = new File(fileName).getName();
		System.out.println(fileName);
		*/
		MemberBean memberData = (MemberBean)request.getSession(true).getAttribute("memberDataForPhoto");
		String id = String.valueOf(memberData.getId());
		photo.write(savePath+File.separator+id+".jpg");
		System.out.println("successfully!");
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	/*
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] stringArray = contentDisp.split(";");
        for (String string : stringArray) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=")+2,string.length()-1);
			}
		}
		return "";
	}
	*/



}
