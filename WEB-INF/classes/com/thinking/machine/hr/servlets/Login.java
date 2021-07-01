package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class Login extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
pw=response.getWriter();
String username=null;
String password=null;
System.out.println("yaar");
username=request.getParameter("username");
password=request.getParameter("password");
AdministratorDAO administratorDAO=new AdministratorDAO();
AdministratorDTO administratorDTO;
System.out.println("yaar22");
administratorDTO=administratorDAO.getByUsername(username);
String password1=administratorDTO.getPassword().trim();
System.out.println("ehoieier");
if(password1.equals(password)==false)
{
pw.print("error,incorrect password");
}
HttpSession session=request.getSession();
session.setAttribute("username",username);
session.setAttribute("password",password);
pw.print("success logged in");
}catch(DAOException daoexception)
{
pw.print("error,"+daoexception.getMessage());
}
catch(Exception exception)
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