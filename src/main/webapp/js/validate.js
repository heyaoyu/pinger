function validate(){	
	var inputs = document.getElementById("tip");
	var username = document.forms.login.elements["username"].value;
	var password = document.forms.login.elements["password"].value;
	if(username==""||username==null){
		if(inputs.getElementsByTagName("p").length==0){
			var newNode = document.createElement("p");
			newNode.innerHTML = "enter your name";
			inputs.appendChild(newNode);
		}
		return false;
	}else if(password==""||password==null){
		if(inputs.getElementsByTagName("p").length==0){
			var newNode = document.createElement("p");
			newNode.innerHTML = "enter your password";
			inputs.appendChild(newNode);
		}
		return false;
	}
	return true;
}

function register(){
	var tips = "Please register later";
	document.getElementById("tip").innerHTML = tips;
}