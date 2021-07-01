package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class Guard extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
System.out.println("jjnjjnejnejnrjr");
pw=response.getWriter();
HttpSession session=request.getSession();
String username=null;
username=(String)session.getAttribute("username");
String password=(String)session.getAttribute("password");
if(username==null)
{
pw.print("error,log in first");
return;
}
else
{
pw.print(username+","+password);
return;
}
}catch(Exception e)
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


