package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class ExaminationImportServlet
 */
@WebServlet("/ExaminationImportServlet")
public class ExaminationImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private DataSource datasource = null;
	
	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:/comp/env/jdbc/postgres");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExaminationImportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestType= request.getParameter("requestType2");
		HttpSession session = request.getSession(true);
		String Emaild = (String) session.getAttribute("EmailLogin");
		if (requestType == null) {
			createDynPage(response, "’κυρος τύπος αιτήματος");
		}
		
		if (requestType.equalsIgnoreCase("insertvalue")) {
			String extype = request.getParameter("etype");
			String exdate = request.getParameter("edate");
			String emailpa = request.getParameter("emailp");
			String emaildo = request.getParameter("emaild");
			if(Emaild.equals(emaildo)) {
				try {
					Connection con = datasource.getConnection();
					
					String sql1 = "INSERT INTO Examinations(Examination_type,Examination_date,Patient_email,Doctor_email) VALUES(?,?,?,?);";
					    
					PreparedStatement insertexamination = con.prepareStatement(sql1);
					insertexamination.setString(1, extype);
					insertexamination.setString(2, exdate);
					insertexamination.setString(3, emailpa);
					insertexamination.setString(4, emaildo);
				    
					    
					insertexamination.executeUpdate();
					response.sendRedirect("DoctorExaminations.html");
				    insertexamination.close();
				
					con.close();
					
				} catch(SQLException sqle) {
					sqle.printStackTrace();
					createDynPage(response, "Το email σας δεν είναι σωστό");
				}
			}
			else {
				createDynPage(response, "Το email δεν είναι σωστό");
			}
			
			
		} else {
			createDynPage(response, "The request type parameter must be insert");
		}
	}
	
	private void createDynPage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Εισαγωγή στοιχείων</title></head>");
		out.println("<body style=\"background-color:#fafafa;\">");
		out.println("<h1 style=\"font-size:20px\">" + message + "</h1>");
		out.println("<a href=\"ExaminationImport.html\">Επιστροφή στη σελίδα</a>");
		out.println("</body></html>");
	}

}
