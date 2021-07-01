package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
import com.thinking.machine.hr.beans.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class AddEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
String errorString="";
PrintWriter pw=null;
String jsonString="";
BigDecimal basicSalary=null;
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
Date dateOfBirth;
try
{
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
System.out.println("jjnjjnejnejnrjr");
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
System.out.println(b);
String rawData=b.toString();
EmployeeBean employeeBean=new Gson().fromJson(rawData,EmployeeBean.class);
//System.out.println(rawData.name);
//Gson gson=new Gson();
//EmployeeDTO employeeDTO=gson.fromJson(rawData,EmployeeDTO.class);
pw=response.getWriter();
String name=employeeBean.getName();
String panNumber=employeeBean.getPANNumber();
String aadharCardNumber=employeeBean.getAadharCardNumber();
int designationCode=employeeBean.getDesignationCode();
String gender=employeeBean.getGender();
basicSalary=new BigDecimal(employeeBean.getBasicSalary());
dateOfBirth=sdf.parse(employeeBean.getDateOfBirth());
String title=employeeBean.getDesignation();
boolean isIndian=employeeBean.getIsIndian();
EmployeeDTO EmployeeDTO=new EmployeeDTO();
DesignationDAO designationDAO=new DesignationDAO();
EmployeeDTO employeeDTO=new EmployeeDTO();
System.out.println("jjnjjne234r");
EmployeeDAO employeeDAO=new EmployeeDAO();
boolean designationCodeExists;
designationCodeExists=designationDAO.designationCodeExists(designationCode);
if(designationCodeExists==false)
{
errorString="{\"result\":\"error\",\"errorMessage\":\"designation does not exists\"}";
pw.print(errorString);
}
boolean panNumberExists=employeeDAO.panNumberExists(panNumber);
if(panNumberExists==true)
{
errorString="{\"result\":\"error\",\"errorMessage\":\"panNumber already exists\"}";
pw.print(errorString);
}
boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
if(aadharCardNumberExists==true)
{
errorString="{\"result\":\"error\",\"errorMessage\":\"Aadhar Card Number alredy exists\"}";
pw.print(errorString);
}
if(aadharCardNumberExists||designationCodeExists==false||panNumberExists) return;
employeeDTO.setName(name);
employeeDTO.setGender(gender.charAt(0));
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setIsIndian(isIndian);

employeeDTO.setDesignationCode(designationCode);

System.out.println("hello12346");
employeeDAO.addEmployees(employeeDTO);
String responseData="";
responseData="{\"result\":\"success\"}";
pw.print(responseData);
}catch(DAOException daoexception)
{
errorString="{\"result\":\"error\",\"errorMessage\":\""+daoexception.getMessage()+"\"}";
pw.print(errorString);
return;
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


