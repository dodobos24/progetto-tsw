document.addEventListener('DOMContentLoaded', function() {
    var cercaForm = document.getElementById('cerca');
    if (cercaForm) {
        cercaForm.addEventListener('submit', function(event) {
            var input1 = document.getElementById('dove').value;
            var input2 = document.getElementById('quando').value;
            var input3 = document.getElementById('categoria').value;
            var input4 = document.getElementById('artista').value;
            console.log(input1);
            console.log(input2);
            console.log(input3);
            console.log(input4);

            if (!input1 && input2 === "" && !input3 && !input4) {
                event.preventDefault();
                alert('Almeno un campo deve essere compilato.');
            }
        });
    }

    var registratiForm = document.getElementById('registrati');
    if (registratiForm) {
        registratiForm.addEventListener('submit', function(event) {
            var input1 = document.getElementById('passwordRegistrati').value;
            var input2 = document.getElementById('ripetiPasswordRegistrati').value;
            console.log(input1);
            console.log(input2);

            if (input1 !== input2) {
                event.preventDefault();
                alert('Le password non coincidono.');
            }
        });
    }
	
    var page = document.documentElement.getAttribute('data-page');
    if (page) {
    	var element = document.getElementById(page);
        if (element) {
        	element.classList.add('selected');
        }
    }
});
