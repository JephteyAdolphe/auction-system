/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.5
 * Generated at: 2021-04-16 22:40:57 UTC
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
import javax.servlet.http.*;
import javax.servlet.*;

public final class createListing_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = null;
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("\t\t<title>BuyMe</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"mystyle.css\" type= \"text/css\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body style=\"background-color:#1F1F1F;\">\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"Login\">\r\n");
      out.write("\t<p style=\"font-family:georgia,garamond,serif;font-size:24px;font-style:bold;color:white;\">\r\n");
      out.write("         List an Item\r\n");
      out.write("      </p>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t<form method=\"get\" action=\"dashboard.jsp\"><input type=\"submit\" value=\"Go Back to Dash Board\"></form>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<form method=\"post\" id=\"listing_form\" action=\"auctionServlet\">\r\n");
      out.write("\t\t<div class=\"inputs\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"listing_form\" value=\"123\">\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>    \r\n");
      out.write("\t\t\t\t\t<td> <p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tBrand:</p> </td> <td><input type=\"text\" name=\"brand\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tItem Type:</p></td><td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t\t\t<select name=\"item_type\" id=\"item_type\" form=\"itemform\">\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"shoes\">Shoes</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"bottoms\">Bottoms</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"tops\">Tops</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tClothing Size:</p></td><td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t\t\t<select name=\"clothing_size\" id=\"size\" form=\"sizeform\">\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"small\">Small</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"medium\">Medium</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"large\">Large</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tShoes Size:</p></td><td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t\t\t<select name=\"shoe_size\" id=\"size\" form=\"sizeform\">\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"8\">8</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"8.5\">8.5</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"9\">9</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"9.5\">9.5</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"10\">10</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"10.5\">10.5</option>\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"11\">11</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("         \t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tBid Increment:</p></td><td><input type=\"number\" name=\"bid_increment\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>    \r\n");
      out.write("\t\t\t\t\t<td> <p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tStart Price:</p> </td> <td><input type=\"number\" min=\"0.00\" step=\"0.01\" name=\"start_price\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>    \r\n");
      out.write("\t\t\t\t\t<td> <p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tEnd date:</p> </td> <td><input type=\"text\" name=\"end_date\" placeholder=\"MM/DD/YYYY\"><input type=\"text\" name=\"end_time\" placeholder=\"HH:MM:SS\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><p style=\"font-family:georgia,garamond,serif;font-size:16px;color:white;\">\r\n");
      out.write("         \t\t\tMinimum Reserve Price:</p></td><td><input type=\"number\" min=\"0.00\" step=\"0.01\" value=\"0.01\" name=\"min_price\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"submit\">\r\n");
      out.write("\t\t\t<input type=\"submit\" value=\"Create Listing\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
