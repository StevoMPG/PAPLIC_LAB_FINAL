const ul= $("#opciones")
  const buscador= $("#campoTexto")
 
  const getA = ({ texto, link, extraClass = "" }) => `
     <a class="dropdown-item ${extraClass}" href="${link}">${texto}</a>
    `;
     
    // Oculta dropdown cuando se clickea fuera del buscador y del mismo dropdown
    $(document).on("click", function (event) {
        const $target = $(event.target);
        
        if (
            !$target.closest("#opciones").length &&
            !$target.closest("#campoTexto").length
        ) {
            ul.removeClass("show");
        }
    });
    
    // Se muestra el dropdown cuando se hace foco en el buscador
    // (solo si hay una busqueda)
    buscador.on("focus", function(event) {
        if (event.target.value) {
            ul.addClass("show");
        }
    });

     
     let timeoutId;
          
     buscador.on('input', function(event) {
        if (timeoutId) {
            clearTimeout(timeoutId);
        }
        
        timeoutId = setTimeout(() => {
            
            ul.empty();
            if(buscador.val() === ''){
                ul.removeClass("show");
                return;
            }
            
            $.ajax({
                type: 'POST',
                data: { coincidencia: buscador.val() },
                url: '/servidorWeb/servlets/Buscador',
        
            }).done(function (result) {
                const valores = JSON.parse(result)
        
                if(valores.length === 0){
                    agregarElemento();
                } else {
                    valores.forEach(valor => {
        
                        agregarElemento(valor.id, valor.link)
                    });        
                }            
            });
            
            ul.addClass("show");
        }, 400);
    })
    
    function agregarElemento(texto = "", link = "#"){
        const li = document.createElement("li");
        
        if(texto === ""){
            li.innerHTML = getA({texto: "No se ha encontrado ninguna coincidencia", link, extraClass: "btn disabled"})
        } else {        
            li.innerHTML = getA({texto, link})
        }
        ul.append(li);
    }
  
  
      if(texto === ""){
            li.innerHTML = getA({texto: "No se ha encontrado ninguna coincidencia", link, extraClass: "btn disabled"})
        } else {        
            li.innerHTML = getA({texto, link})
        }
        ul.append(li);
    
  
