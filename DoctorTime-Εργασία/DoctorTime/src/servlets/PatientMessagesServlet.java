package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class PatientMessagesServlet
 */
@WebServlet("/PatientMessagesServlet")
public class PatientMessagesServlet extends HttpServlet {
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
    public PatientMessagesServlet() {
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
		String requestType= request.getParameter("requestType3");
		HttpSession session = request.getSession(true);
		String Emailpa = (String) session.getAttribute("EmailLogin");
		if (requestType == null) {
			createDynPage(response, "’κυρος τύπος αιτήματος");
		}
		
		if (requestType.equalsIgnoreCase("insertvalue2")) {
			String emailpat = request.getParameter("emailpat");
			String emaildoc = request.getParameter("emaildoc");
			String messagep = request.getParameter("messagep");
			if(Emailpa.equals(emailpat)) {
				try {
					Connection con = datasource.getConnection();
					
					String sql1 = "INSERT INTO Messages(Message_text,Mpatient_email,Mdoctor_email) VALUES(?,?,?);";
					    
					PreparedStatement insertexamination = con.prepareStatement(sql1);
					insertexamination.setString(1, messagep);
					insertexamination.setString(2, emailpat);
					insertexamination.setString(3, emaildoc);
				    
					    
					insertexamination.executeUpdate();
					response.sendRedirect("PatientExaminations.html");
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
		out.println("<a href=\"PatientMessages.html\">Επιστροφή στη σελίδα</a>");
		out.println("</body></html>");
	}

}
