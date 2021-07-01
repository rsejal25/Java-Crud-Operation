package com.thinking.machine.hr.dl;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class DesignationDAO 
{
public List<DesignationDTO> getAll() throws DAOException
{
List<DesignationDTO> designations=new LinkedList<>();
try
{
Statement statement;
Connection connection;
connection=DAOConnection.getConnection();
statement=connection.createStatement();
ResultSet resultSet;
DesignationDTO designation;
resultSet=statement.executeQuery("select * from designation");
while(resultSet.next())
{
designation=new DesignationDTO();
designation.setCode(resultSet.getInt("code"));
designation.setTitle(resultSet.getString("title"));
designations.add(designation);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return designations;
}
public void addDesignation(DesignationDTO designationDTO) throws DAOException
{
int code=designationDTO.getCode();
String title=designationDTO.getTitle();
Connection connection=null;
ResultSet resultSet=null;
PreparedStatement statement=null;

try
{
connection=DAOConnection.getConnection();
statement=connection.prepareStatement("select * from designation where title=?");
statement.setString(1,title);
resultSet=statement.executeQuery();
if(resultSet.next())
{
resultSet.close();
statement.close();
connection.close();
throw new DAOException("title already exists");
}
resultSet.close();
statement.close();
statement=connection.prepareStatement("insert into designation (title) values(?)",Statement.RETURN_GENERATED_KEYS);
statement.setString(1,title);
statement.executeUpdate();
int code1;
resultSet=statement.getGeneratedKeys();
resultSet.next();
code1=resultSet.getInt(1);
resultSet.close();
statement.close();
connection.close();
}catch(SQLException e)
{
throw new DAOException(e.getMessage());
}
}
public DesignationDTO getByCode(int code) throws DAOException
{
Connection connection;
PreparedStatement prepareStatement;
ResultSet resultSet;
DesignationDTO designationDTO=new DesignationDTO();
try
{
connection=DAOConnection.getConnection();
prepareStatement=connection.prepareStatement("select * from designation where code=?");
prepareStatement.setInt(1,code);
resultSet=prepareStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
prepareStatement.close();
connection.close();
throw new DAOException("code doesnot exists");
}
String title;
title=resultSet.getString(2).trim();
designationDTO.setCode(code);
designationDTO.setTitle(title);
resultSet.close();
prepareStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return designationDTO;
}
public void updateDesignation(DesignationDTO designationDTO) throws DAOException
{
int code;
String title;
code=designationDTO.getCode();
title=designationDTO.getTitle().trim();
Connection connection;
ResultSet resultSet;
PreparedStatement preparedStatement;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
throw new DAOException("Invalid designation code");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from designation where title=? and code!=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
throw new DAOException("Title already exists against another designation code");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void deleteDesignation(int code) throws DAOException
{
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select gender from employee where designation_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
preparedStatement.close();
resultSet.close();
connection.close();
throw new DAOException("Designation Code is already assigned to the employee");
}
preparedStatement.close();
resultSet.close();
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
connection.close();
preparedStatement.close();
resultSet.close();
throw new DAOException("invalid designation code");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from designation where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public boolean designationCodeExists(int designationCode) throws DAOException
{
if(designationCode<=0) return false;
ResultSet resultSet;
Connection connection;
PreparedStatement preparedStatement;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select title from designation where code=?");
preparedStatement.setInt(1,designationCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()) return true;
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return false;
}
}