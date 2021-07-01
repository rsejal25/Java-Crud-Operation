package com.thinking.machine.hr.dl;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class AdministratorDAO
{
public AdministratorDTO getByUsername(String username) throws DAOException
{
Connection connection;
PreparedStatement statement;
ResultSet resultSet;
System.out.println(username);
try
{
connection=DAOConnection.getConnection();
statement=connection.prepareStatement("select * from Administration where uname=?");
statement.setString(1,username);
System.out.println("yaar89393");
resultSet=statement.executeQuery();
AdministratorDTO administratorDTO;
if(resultSet.next())
{
administratorDTO=new AdministratorDTO();
System.out.println("yaahrhruir");
administratorDTO.setUsername(resultSet.getString("uname").trim());
administratorDTO.setPassword(resultSet.getString("pwd").trim());
System.out.println("yaam,jejkjkr");
resultSet.close();
statement.close();
connection.close();
return administratorDTO;
}
else
{
resultSet.close();
statement.close();
connection.close();
System.out.println("yaario438489");
throw new DAOException("username does not exists");
}
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}