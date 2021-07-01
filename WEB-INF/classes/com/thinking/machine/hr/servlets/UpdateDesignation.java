package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class UpdateDesignation extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
System.out.println("jjnjj!!!!nejnejnrjr");
pw=response.getWriter();
int code=Integer.parseInt(request.getParameter("code"));
System.out.println(code);
String title=request.getParameter("title");
System.out.println(title);
System.out.println("jjnjj!!!!nejnejnrjrb33333");
DesignationDTO designationDTO=new DesignationDTO();
designationDTO.setTitle(title);

designationDTO.setCode(code);
System.out.println("jjnjj!!!!nejnejnrjkksdkdskk999r");
DesignationDAO designationDAO=new DesignationDAO();

System.out.println("jjnjjne234r");
designationDAO.updateDesignation(designationDTO);
pw.print("designation updated");
}catch(DAOException daoexception)
{
pw.print("error"+","+daoexception.getMessage());
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
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
}
}
}


