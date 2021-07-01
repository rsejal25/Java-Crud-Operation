package com.thinking.machine.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machine.hr.dl.*;
public class ConfirmDeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
System.out.println("lave233");
int code=Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designationDTO;
designationDTO=designationDAO.getByCode(code);
System.out.println("lave");
pw=response.getWriter();
pw.print(designationDTO.getCode()+","+designationDTO.getTitle());
}catch(DAOException e)
{
pw.print("error"+","+e.getMessage());
}
catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception exceptin)
{

}

}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception exception)
{

}
}
}