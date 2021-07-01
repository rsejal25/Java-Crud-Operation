<!DOCTYPE HTML>
<html>
<head>
<meta charset='utf-8'>
<script>
function login()
{
let dataToSend;
let username=document.getElementById("uname").value;
let password=document.getElementById("pwd").value
dataToSend="username="+username;
dataToSend+="&password="+password;
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
let responseData=this.responseText;
let res=responseData.split(",");
if(responseData.includes("error"))
{
if(responseData.includes("username"))
{
document.getElementById("usernameError").innerHTML=res[1];
}
else
{
document.getElementById("passwordError").innerHTML=res[1];
}
}
else
{
document.getElementById('logged').submit();
}
}
};
xhr.open('POST',"login",true);
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.send(dataToSend);
}
</script>
<link rel='stylesheet' type='text/css' href='/styletwo1/css/styles.css'>
</head>
<body>
<div class='main-container'>
<div class='header'>
<img src='/styletwo1/image/logo.png' class='logo'>
<div class='brand-name'>Thinking Machine</div>
</div>
<div class='content'>
<div class='content-left-panel'>
<a href='/styletwo1/Designations.jsp'></a><br>
<a href='/styletwo1/Employees.jsp'></a><br>
</div>
<div class='content-right-panel'>
<center>
Username 
<span class='error'>
</span>
Username<input type='text' name='username' id='uname'><span id='usernameError'></span><br>
Password<input type='password' name='password' id='pwd'><span id='passwordError'></span><br>
<button type='submit' onclick='login()'>Login</button>
<form id='logged' action='/stylethree/index.jsp'></form>
</center>
</div>
</div>
<div class='footer'>
&copy; Thinking Machine
</div>
</form>
</div>
</body>
</html>