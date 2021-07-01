<jsp:include page='MasterPageTopSection.jsp' />
<script>
function addEntity()
{
let title=document.getElementById("title").value;

let xhr=new XMLHttpRequest();
var designation={
"title":title
}
xhr.onreadystatechange=function(){
//let error=document.getElementById("errorSection").innerHTML="";
if(this.readyState==4&&this.status==200)
{
console.log('reah');
let responseData=JSON.parse(this.responseText);
if(responseData.result=="error")
{
alert('here id');
error=responseData.errorMessage;
document.getElementById("errorSection").innerHTML=error;
document.getElementById("title").focus();
}
else
{
console.log('rkkt');
document.getElementById("heading").innerHTML="Notification";
document.getElementById("description").innerHTML="Designation added .Want to add more?";
firstButton=document.getElementById("firstButton");
firstButton.innerHTML="Yes";
document.getElementById("title").remove();
firstButton.setAttribute('onclick',"pageLoader();");
secondButton=document.getElementById("secondButton");
secondButton.innerHTML="No";
}
}
};
xhr.open('POST','addDesignation',true);
xhr.setRequestHeader("Content-Type","application/json");
xhr.send(JSON.stringify(designation));
}
function cancelAddition(){
document.getElementById("cancelDesignationAddition").submit();
}
function pageLoader(){
document.getElementById("heading").innerHTML="Designation (Add Module)";
document.getElementById("description").innerHTML='Enter the title';
}
window.addEventListener('load',pageLoader);
</script>
<div id='heading'></div>
<div id='description'></div>
<input type='text' id='title'><br>
<span id='errorSection'></span>
<button type='button' onclick='addEntity()' id='firstButton'>Add</button>
<button type='button' onclick='cancelAddition()' id='secondButton'>No</button>


<form id='cancelDesignationAddition' action='/stylethree/Designations.jsp'></form>
<jsp:include page='MasterPageBottomSection.jsp' />
