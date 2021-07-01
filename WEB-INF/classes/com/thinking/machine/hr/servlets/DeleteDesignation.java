package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class DeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String jsonString="";
try
{
System.out.println("jjnjj!!!!nejnejnrjr");
pw=response.getWriter();
response.setContentType("application/json");
int code=Integer.parseInt(request.getParameter("code"));
System.out.println(code);
//String title=request.getParameter("title");
//System.out.println(title);
//System.out.println("jjnjj!!!!nejnejnrjrb33333");
//DesignationDTO designationDTO=new DesignationDTO();
//designationDTO.setTitle(title);

//designationDTO.setCode(code);
System.out.println("jjnjj!!!!nejnejnrjkksdkdskk999r");
DesignationDAO designationDAO=new DesignationDAO();

System.out.println("jjnjjne234r");
designationDAO.deleteDesignation(code);
jsonString="{\"result\":\"success\"}";
pw.print(jsonString);
pw.flush();
}catch(DAOException daoexception)
{
jsonString="{\"resut\":\"error\"}";
pw.print(jsonString);
pw.flush();
}
catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
}
}
}


