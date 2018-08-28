function xs(){
			document.getElementById("service").style.display="block"
		}
function yc(){
			document.getElementById("service").style.display="none"
		}
var a = 0;
function xia() {
	a = a + 1100;
	if(a > 0) {
		a = -1100;
	}
	document.getElementById("anm").style.transform = "translateX(" + a + "px)";
	document.getElementById("anm").style.transition = "transform 1s ease-in-out";
}

function shang() {
	a = a - 1100;
	if(a <= -2180) {
		a = 0;
	}
	document.getElementById("anm").style.transform = "translateX(" + a + "px)";
	document.getElementById("anm").style.transition = "transform 1s ease-in-out";
}