package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class DoctorsMessagesServlet
 */
@WebServlet("/DoctorsMessagesServlet")
public class DoctorsMessagesServlet extends HttpServlet {
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
    public DoctorsMessagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String Emaildoct = (String) session.getAttribute("EmailLogin");
		PrintWriter out = response.getWriter();
        try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"UTF-8\">\r\n" + 
					"<title>Μηνύματα</title>\r\n" + 
					"<link rel=\"icon\" type=\"image/png\" href=\"logo.jpg\"/>\r\n" + 
					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
					"<script src='https://kit.fontawesome.com/a076d05399.js'></script>\r\n" + 
					"<style>\r\n" + 
					"html {\r\n" + 
					"  scroll-behavior: smooth;\r\n" + 
					"}\r\n" + 
					"body {\r\n" + 
					"  margin: 0;\r\n" + 
					"  font-size: 15px;\r\n" + 
					"  font-family: Arial, Helvetica, sans-serif;\r\n" + 
					"  background-color: #fafafa;\r\n" + 
					"}\r\n" + 
					"      \r\n" + 
					"/* Full-width input fields */\r\n" + 
					"input[type=text], input[type=password] {\r\n" + 
					"    width: 100%;\r\n" + 
					"    padding: 12px 20px;\r\n" + 
					"    margin: 8px 0;\r\n" + 
					"    display: inline-block;\r\n" + 
					"    border: 1px solid #ccc;\r\n" + 
					"    box-sizing: border-box;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"hr {\r\n" + 
					"  border: 2px solid #f1f1f1;\r\n" + 
					"  margin-bottom: 25px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"a:link {\r\n" + 
					"  color: #2b67f0;\r\n" + 
					"  background-color: transparent;\r\n" + 
					"  text-decoration: none;\r\n" + 
					"}\r\n" + 
					"a:hover {\r\n" + 
					"  color: red;\r\n" + 
					"  background-color: transparent;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"table {\r\n" + 
					"  font-family: arial, sans-serif;\r\n" + 
					"  border-collapse: collapse;\r\n" + 
					"  width: 100%;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"td, th {\r\n" + 
					"  border: 1px solid #dddddd;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"tr:nth-child(even) {\r\n" + 
					"  background-color: #dddddd;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"/* Extra styles for the cancel button */\r\n" + 
					".cancelbtn {\r\n" + 
					"    width: auto;\r\n" + 
					"    padding: 10px 18px;\r\n" + 
					"    cursor: pointer;\r\n" + 
					"    border: none;\r\n" + 
					"    color: white;\r\n" + 
					"    background-color: #f44336;\r\n" + 
					"}\r\n" + 
					"    \r\n" + 
					".cancelbtn:hover {\r\n" + 
					"    opacity: 0.8;        \r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".container {\r\n" + 
					"    padding: 16px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"span.psw {\r\n" + 
					"    float: right;\r\n" + 
					"    padding-top: 16px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"/* The Close Button (x) */\r\n" + 
					".close {\r\n" + 
					"    position: absolute;\r\n" + 
					"    right: 25px;\r\n" + 
					"    top: 0;\r\n" + 
					"    color: #000;\r\n" + 
					"    font-size: 35px;\r\n" + 
					"    font-weight: bold;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".close:hover,\r\n" + 
					".close:focus {\r\n" + 
					"    color: red;\r\n" + 
					"    cursor: pointer;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"/* Add Zoom Animation */\r\n" + 
					".animate {\r\n" + 
					"    -webkit-animation: animatezoom 0.6s;\r\n" + 
					"    animation: animatezoom 0.6s\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"@-webkit-keyframes animatezoom {\r\n" + 
					"    from {-webkit-transform: scale(0)} \r\n" + 
					"    to {-webkit-transform: scale(1)}\r\n" + 
					"}\r\n" + 
					"    \r\n" + 
					"@keyframes animatezoom {\r\n" + 
					"    from {transform: scale(0)} \r\n" + 
					"    to {transform: scale(1)}\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"/* Change styles for span and cancel button on extra small screens */\r\n" + 
					"@media screen and (max-width: 300px) {\r\n" + 
					"    span.psw {\r\n" + 
					"       display: block;\r\n" + 
					"       float: none;\r\n" + 
					"    }\r\n" + 
					"    .cancelbtn {\r\n" + 
					"       width: 100%;\r\n" + 
					"    }\r\n" + 
					"}    \r\n" + 
					"\r\n" + 
					".sidebar {\r\n" + 
					"    height: 100%;\r\n" + 
					"    width: 0;\r\n" + 
					"    position: fixed;\r\n" + 
					"    z-index: 1;\r\n" + 
					"    top: 0;\r\n" + 
					"    left: 0;\r\n" + 
					"    background-color: #b3afaf;\r\n" + 
					"    overflow-x: hidden;\r\n" + 
					"    transition: 0.5s;\r\n" + 
					"    padding-top: 60px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".sidebar a {\r\n" + 
					"    padding: 8px 8px 8px 32px;\r\n" + 
					"    text-decoration: none;\r\n" + 
					"    font-size: 25px;\r\n" + 
					"    color: white;\r\n" + 
					"    display: block;\r\n" + 
					"    transition: 0.3s;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".sidebar a:hover:not(.active) {\r\n" + 
					"  background-color: whitesmoke;\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".sidebar .closebtn {\r\n" + 
					"    position: absolute;\r\n" + 
					"    top: 0;\r\n" + 
					"    right: 25px;\r\n" + 
					"    font-size: 36px;\r\n" + 
					"    margin-left: 50px;\r\n" + 
					"}\r\n" + 
					"    \r\n" + 
					"#close:hover {\r\n" + 
					"  background-color: #b3afaf;        \r\n" + 
					"}     \r\n" + 
					"    \r\n" + 
					"    \r\n" + 
					"#login_signup:hover {\r\n" + 
					"  color: grey;    \r\n" + 
					"}        \r\n" + 
					"\r\n" + 
					"#articles:hover {\r\n" + 
					"  color: grey;        \r\n" + 
					"}\r\n" + 
					"    \r\n" + 
					"#about:hover {\r\n" + 
					"  color: grey;    \r\n" + 
					"}\r\n" + 
					"	\r\n" + 
					".openbtn {\r\n" + 
					"    font-size: 20px;\r\n" + 
					"    cursor: pointer;\r\n" + 
					"    background-color: #bdb7b7;\r\n" + 
					"    border-radius: 5px;\r\n" + 
					"    color: white;\r\n" + 
					"    padding: 10px 15px;\r\n" + 
					"    border: none;\r\n" + 
					"	margin: 20px 135px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".openbtn:hover {\r\n" + 
					"    background-color: grey;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".cop {\r\n" + 
					"   display: inline-block;\r\n" + 
					"   padding: 10px 15px;\r\n" + 
					"   margin: 20px 135px;\r\n" + 
					"   font-size: 18px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".cop:hover {\r\n" + 
					"   font-weight: bold;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".button {\r\n" + 
					"    background-color: white;\r\n" + 
					"    color: grey;\r\n" + 
					"    border: 2px solid grey;\r\n" + 
					"    border-radius: 5px;\r\n" + 
					"    padding: 10px 15px;\r\n" + 
					"    text-align: center;\r\n" + 
					"    text-decoration: none;\r\n" + 
					"    display: inline-block;\r\n" + 
					"    font-size: 16px;\r\n" + 
					"    margin: 20px 135px;\r\n" + 
					"    -webkit-transition-duration: 0.4s; /* Safari */\r\n" + 
					"    transition-duration: 0.4s;\r\n" + 
					"    cursor: pointer;\r\n" + 
					"    \r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".button:hover {\r\n" + 
					"    background-color: grey;\r\n" + 
					"    color: white;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"#myBtn {\r\n" + 
					"  display: none;\r\n" + 
					"  position: fixed;\r\n" + 
					"  height: 55px;\r\n" + 
					"  width: 55px;\r\n" + 
					"  bottom: 20px;\r\n" + 
					"  right: 30px;\r\n" + 
					"  z-index: 99;\r\n" + 
					"  font-size: 18px;\r\n" + 
					"  border: none;\r\n" + 
					"  outline: none;\r\n" + 
					"  background-color: #bdb7b7;\r\n" + 
					"  color: white;\r\n" + 
					"  cursor: pointer;\r\n" + 
					"  padding: 15px;\r\n" + 
					"  border-radius: 50px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"#myBtn:hover {\r\n" + 
					"  background-color: #555;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".header {\r\n" + 
					"  position: fixed;\r\n" + 
					"  top: 0;\r\n" + 
					"  z-index: 1;\r\n" + 
					"  width: 100%;\r\n" + 
					"  background-color: #ffffff;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".header h2 {\r\n" + 
					"  text-align: center;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".progress-container {\r\n" + 
					"  width: 100%;\r\n" + 
					"  height: 6px;\r\n" + 
					"  background: #ccc;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".progress-bar {\r\n" + 
					"  height: 6px;\r\n" + 
					"  background: #2e2d2d;\r\n" + 
					"  width: 0%;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".content {\r\n" + 
					"  padding: 120px 0;\r\n" + 
					"  margin: 50px auto 0 auto;\r\n" + 
					"  width: 80%;\r\n" + 
					"  background-repeat: no-repeat;\r\n" + 
					"  background-attachment: fixed;\r\n" + 
					"  background-size: cover;\r\n" + 
					"  background-position: center;\r\n" + 
					"  height: 150px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".hero-text {\r\n" + 
					"  text-align: center;\r\n" + 
					"  position: absolute;\r\n" + 
					"  top: 40%;\r\n" + 
					"  left: 24%;\r\n" + 
					"  transform: translate(-50%, -50%);\r\n" + 
					"  color: white;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".content2 {\r\n" + 
					"  padding: 0px 0;\r\n" + 
					"  margin: 0 auto 0 auto;\r\n" + 
					"  width: 80%;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"#snackbar {\r\n" + 
					"    visibility: hidden;\r\n" + 
					"    min-width: 250px;\r\n" + 
					"    margin-left: -125px;\r\n" + 
					"    background-color: #333;\r\n" + 
					"    color: #fff;\r\n" + 
					"    text-align: center;\r\n" + 
					"    border-radius: 5px;\r\n" + 
					"    padding: 16px;\r\n" + 
					"    position: fixed;\r\n" + 
					"    z-index: 1;\r\n" + 
					"    left: 50%;\r\n" + 
					"    bottom: 30px;\r\n" + 
					"    font-size: 20px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"#snackbar.show {\r\n" + 
					"    visibility: visible;\r\n" + 
					"    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;\r\n" + 
					"    animation: fadein 0.5s, fadeout 0.5s 2.5s;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"@-webkit-keyframes fadein {\r\n" + 
					"    from {bottom: 0; opacity: 0;} \r\n" + 
					"    to {bottom: 30px; opacity: 1;}\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"@keyframes fadein {\r\n" + 
					"    from {bottom: 0; opacity: 0;}\r\n" + 
					"    to {bottom: 30px; opacity: 1;}\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"@-webkit-keyframes fadeout {\r\n" + 
					"    from {bottom: 30px; opacity: 1;} \r\n" + 
					"    to {bottom: 0; opacity: 0;}\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"@keyframes fadeout {\r\n" + 
					"    from {bottom: 30px; opacity: 1;}\r\n" + 
					"    to {bottom: 0; opacity: 0;}\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"\r\n" + 
					"<div class=\"header\">\r\n" + 
					"  <h2>DoctorTime<img src=\"logo.jpg\" style=\"vertical-align:middle;width:60px;height:60px;\"></h2>\r\n" + 
					"  <div class=\"progress-container\">\r\n" + 
					"    <div class=\"progress-bar\" id=\"myBar\"></div>\r\n" + 
					"  </div>  \r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"<div class=\"content\">\r\n" + 
					"    <div>\r\n" + 
					"    <h1 style=\"font-size:30px\">Λίστα μηνυμάτων</h1><br>\r\n" + 
					"    <h1 style=\"font-size:20px\">Παρακάτω μπορείτε να δείτε τα μηνύματα που σας έχουν στείλει οι ασθενείς σας.</h1><br><br>\r\n");
			
		    String sql1 = "SELECT Message_text,Mpatient_email,Name_patient,Surname_patient FROM Messages INNER JOIN Patients ON Mpatient_email=Email_patient INNER JOIN Doctors ON Mdoctor_email=Email_doctor WHERE Mdoctor_email='" + Emaildoct + "';";

		    out.println("<table>\r\n" + 
					"  <tr>\r\n" + 
					"    <th>Μήνυμα</th>\r\n" + 
					"    <th>Email</th>\r\n" + 
					"    <th>Όνομα</th>\r\n" + 
					"    <th>Επώνυμο</th>\r\n" + 
					"  </tr>\r\n");
			ResultSet rs = stmt.executeQuery(sql1);
			while ( rs.next() ) {
            	String messagepa = rs.getString("Message_text");
				String emailpati = rs.getString("Mpatient_email");
				String namepati = rs.getString("Name_patient");
				String surnamepati = rs.getString("Surname_patient");
				String htmlRow = createHTMLRow(messagepa, emailpati, namepati, surnamepati);
                out.println("<tr>\r\n" + 
    					       htmlRow + 
    					    "</tr>\r\n");
            }
			
			out.println("</table>");
			rs.close();

			con.close();
		} catch(Exception e) {
			out.println("Database connection problem");
		}
        out.println("<br><br><hr>\r\n" + 
				"    \r\n" + 
				"	<a href=\"HomePageLogin2.html\">Αρχική σελίδα</a><br><br><br><br>\r\n" + 
				"  </div>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"<div id=\"snackbar\">Snackbar</div>\r\n" + 
				"\r\n" + 
				"<button onclick=\"topFunction()\" id=\"myBtn\" title=\"Πηγαίντε στη κορυφή\"><i class=\"material-icons\">expand_less</i></button>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<script>\r\n" + 
				"// When the user scrolls the page, execute myFunction \r\n" + 
				"window.onscroll = function() {myFunction(),scrollFunction()};\r\n" + 
				"\r\n" + 
				"function myFunction() {\r\n" + 
				"  var winScroll = document.body.scrollTop || document.documentElement.scrollTop;\r\n" + 
				"  var height = document.documentElement.scrollHeight - document.documentElement.clientHeight;\r\n" + 
				"  var scrolled = (winScroll / height) * 100;\r\n" + 
				"  document.getElementById(\"myBar\").style.width = scrolled + \"%\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"function newFunction() {\r\n" + 
				"    var x = document.getElementById(\"snackbar\");\r\n" + 
				"    x.className = \"show\";\r\n" + 
				"    setTimeout(function(){ x.className = x.className.replace(\"show\", \"\"); }, 3000);\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"function scrollFunction() {\r\n" + 
				"    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {\r\n" + 
				"        document.getElementById(\"myBtn\").style.display = \"block\";\r\n" + 
				"    } else {\r\n" + 
				"        document.getElementById(\"myBtn\").style.display = \"none\";\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"// When the user clicks on the button, scroll to the top of the document\r\n" + 
				"function topFunction() {\r\n" + 
				"    document.body.scrollTop = 0;\r\n" + 
				"    document.documentElement.scrollTop = 0;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"function openNav() {\r\n" + 
				"    document.getElementById(\"mySidebar\").style.width = \"250px\";\r\n" + 
				"    document.getElementById(\"main\").style.marginLeft = \"250px\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"function closeNav() {\r\n" + 
				"    document.getElementById(\"mySidebar\").style.width = \"0\";\r\n" + 
				"    document.getElementById(\"main\").style.marginLeft= \"0\";\r\n" + 
				"}\r\n" + 
				"    \r\n" + 
				"// Get the modal\r\n" + 
				"var modal = document.getElementById('id01');\r\n" + 
				"\r\n" + 
				"// When the user clicks anywhere outside of the modal, close it\r\n" + 
				"window.onclick = function(event) {\r\n" + 
				"    if (event.target == modal) {\r\n" + 
				"        modal.style.display = \"none\";\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"</script>\r\n");
        out.println("</body>\r\n" + 
				    "</html>");
	    out.close();
	}
	
	private String createHTMLRow(String messagepa, String emailpati, String namepati, String surnamepati) {
		String row = "<tr>";
		row  += "<td>" + messagepa + "</td>";
		row  += "<td>" + emailpati + "</td>";
		row  += "<td>" + namepati + "</td>";
		row  += "<td>" + surnamepati + "</td>";
		row +="</tr>";
		return row;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
