/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.5
 * Generated at: 2021-04-25 02:01:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import com.cs336.pkg.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import javax.servlet.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("com.cs336.pkg");
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("java.io");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("java.text.SimpleDateFormat");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!--Import some libraries that have classes that we need -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\n");
      out.write("    href=\"https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css\">\n");
      out.write("<script type=\"text/javascript\" charset=\"utf8\" src=\"https://code.jquery.com/jquery-3.5.1.js\"></script>\n");
      out.write("<script type=\"text/javascript\" charset=\"utf8\"\n");
      out.write("    src=\"https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js\"></script>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("    <title>Insert title here</title>\n");
      out.write("</head>\n");
 ApplicationDB db=new ApplicationDB(); ArrayList<String[]> itemList = new ArrayList<String[]>();
        itemList = db.getListings();
		String account_id = String.valueOf(request.getAttribute("user"));
		String test = String.valueOf(session.getAttribute("user"));
        
      out.write("\n");
      out.write("\n");
      out.write("        <body>\n");
      out.write("\n");
      out.write("            <form method=\"get\" action=\"main.jsp\"><input type=\"submit\" value=\"Log Out\"></form>\n");
      out.write("            <form method=\"get\" action=\"createListing.jsp\"><input type=\"submit\" value=\"Create A Listing\"></form>\n");
      out.write("            <form method=\"get\" action=\"alert.jsp\"><input type=\"submit\" value=\"Go To Alerts\"></form>\n");
      out.write("            <form method=\"get\" action=\"personal_history.jsp\"><input type=\"submit\" value=\"My Bid History\"></form>\n");
      out.write("            <form method=\"get\" action=\"personal_sell_history.jsp\"><input type=\"submit\" value=\"My Selling History\"></form>\n");
      out.write("            <form method=\"get\" action=\"create_watching_alert.jsp\"><input type=\"submit\" value=\"Create Watch Alert for Item\"></form>\n");
      out.write("            <form method=\"get\" action=\"my_watching_alerts.jsp\"><input type=\"submit\" value=\"View My Watch Alerts\"></form>\n");
      out.write("\n");
      out.write("            <table id=\"table_id\" class=\"display\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Clothing ID</th>\n");
      out.write("                        <th>Category</th>\n");
      out.write("                        <th>Size</th>\n");
      out.write("                        <th>Brand</th>\n");
      out.write("                        <th>Name</th>\n");
      out.write("                        <th>Current Price</th>\n");
      out.write("                        <th>Min Bid Increment</th>\n");
      out.write("                        <th>Start Time</th>\n");
      out.write("                        <th>End Time</th>\n");
      out.write("                        <th>Seller</th>\n");
      out.write("                        <th>Bid</th>\n");
      out.write("                        <th>History</th>\n");
      out.write("                        <th>Similar</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 for(int i=0; i < itemList.size(); i++) { 
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            ");
 String cid = "";
                            for(int j=0; j < itemList.get(i).length; j++) { 
                            	if (j == 0) {
                            		cid =itemList.get(i)[j];
                            	}
                            
      out.write("\n");
      out.write("\n");
      out.write("                                <td>\n");
      out.write("                                    ");
      out.print(itemList.get(i)[j]);
      out.write("\n");
      out.write("                                </td>\n");
      out.write("\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                                <td>\n");
      out.write("                                \t\n");
      out.write("                                    <form name=\"bid_form\" method=\"get\" action=\"auctionServlet\">\n");
      out.write("                                    <input type=\"hidden\" name=\"bid_form\" value=\"123\">\n");
      out.write("                                    <input type=\"hidden\" name=\"cid\" value=");
      out.print(itemList.get(i)[0]);
      out.write(">\n");
      out.write("                                    <input type=\"hidden\" name=\"seller\" value=");
      out.print(itemList.get(i)[9]);
      out.write(">\n");
      out.write("                                    <input type=\"hidden\" name=\"bidIncrementForItem\" value=");
      out.print(itemList.get(i)[6]);
      out.write(">\n");
      out.write("                                    <input type=\"hidden\" name=\"currPrice\" value=");
      out.print(itemList.get(i)[5]);
      out.write(">\n");
      out.write("                                    <input type=\"submit\" value = \"Make Bid\"></form>\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <form name=\"history_form\" method=\"get\" action=\"auctionServlet\">\n");
      out.write("                                    <input type=\"hidden\" name=\"history_form\" value=\"123\">\n");
      out.write("                                    <input type=\"hidden\" name=\"cid\" value=");
      out.print(itemList.get(i)[0]);
      out.write(">\n");
      out.write("                                    <input type=\"submit\" value = \"View History Of This Item\"></form>\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <form name=\"similar_form\" method=\"get\" action=\"auctionServlet\">\n");
      out.write("                                    <input type=\"hidden\" name=\"similar_form\" value=\"123\">\n");
      out.write("                                    <input type=\"hidden\" name=\"cid\" value=");
      out.print(itemList.get(i)[0]);
      out.write(">\n");
      out.write("                                    <input type=\"hidden\" name=\"seller\" value=");
      out.print(itemList.get(i)[7]);
      out.write(">\n");
      out.write("                                    <input type=\"hidden\" name=\"category\" value=");
      out.print(itemList.get(i)[1]);
      out.write(">\n");
      out.write("                                    <input type=\"submit\" value = \"View Items Similar To This\"></form>\n");
      out.write("                                </td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");
 } 
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            <script>\n");
      out.write("                $(document).ready(function () {\n");
      out.write("                    $('#table_id').DataTable();\n");
      out.write("                });\n");
      out.write("            </script>\n");
      out.write("            <p>\n");
      out.write("            <form method=\"get\" action=\"customer_representative_functions.jsp\"><input type=\"submit\" value=\"Customer Representative Service\">\n");
      out.write("            </form>\n");
      out.write("            </p>\n");
      out.write("        \n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
