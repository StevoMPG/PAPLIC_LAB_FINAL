//peticion get ayax
/*function trearactcup(){
$.ayax({
	type:"GET",
	//lugar a donde hago la peticion
	url:"https://localhost:3306/algos/getcuponeras"
	succes: funtion(respuesta) {
		//buscar los datos
		if(respuesta != null){
			document.getElementById("tags").value = respuesta.tags;
		}
	},error: function(respuesta){
		alert("no hay resultados disponibles ");
	} 
})
}
*/

//Funcion de autocompletado
$( function() {
    var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];

$( "#tags" ).autocomplete({
      source: availableTags
    });
  } );