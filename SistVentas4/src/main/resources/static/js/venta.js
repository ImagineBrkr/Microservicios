let contador = 0;

let listaProductos;


function agregarTablas(username){

    let listadoHtml = '';
    contador +=1;
    listaProductos = username;

    let submit = document.getElementById('enviar');
    submit.removeAttribute('hidden');


    let listaProducto= '';
    for(let product of username){

        listaProducto += '<option value='+product.id+'>'+product.nombre+'</option>'
    }

    let productoHtml = '<tr id ="row_'+contador+'"><td><select required id="select_product'+contador+'" class="form-select" name="producto" onchange="cargaDatos('+contador+')" ><option value="" selected>Seleccionar un producto</option>'+listaProducto+'</select></td><td><input type="number" id="cantidad_'+contador+'" name="cantidad" onkeyup="calcular(this.value,'+contador+')" class="form-control"></td><td><input type="number" id="precio_'+contador+'" disabled class="form-control"></td><td><input id="total_'+contador+'" oninput="sumar()" type="number" disabled class="form-control input_total"></td><td><button type="button" onclick="eliminar('+contador+')" class="btn btn-outline-danger"><i class="far fa-trash-alt"></i></button></td></tr>';

    listadoHtml += productoHtml;

    document.querySelector('#productos tbody tr').outerHTML+= listadoHtml;

}

function cargaDatos(cont){
    let id_producto;
    let id_selector = "#select_product"+cont+"";
    id_producto = parseInt(document.querySelector(id_selector).value);

    if(id_producto != 0){
        let resultado = listaProductos.find( product => product.id === id_producto );
        let id_precio = "#precio_"+cont+"";
        let id_total = "#total_"+cont+"";
        let id_cantidad = "#cantidad_"+cont+"";
        document.querySelector(id_precio).value = resultado.precio
        document.querySelector(id_total).value = 0
        document.querySelector(id_cantidad).value = 0

        sumarTotales()
    }
    


}

function calcular(cantidad,cont){
    if(cantidad == 0){
        let id_total = "#total_"+cont+"";
        document.querySelector(id_total).value = 0
    }

    if(cantidad == null){
        let id_total = "#total_"+cont+"";
        document.querySelector(id_total).value = ''
    }

    if(cantidad > 0){
        let id_precio = "#precio_"+cont+"";
        precio = document.querySelector(id_precio).value
        let id_total = "#total_"+cont+"";
        document.querySelector(id_total).value = precio*cantidad
    }

    sumarTotales()
}


function eliminar(cont){
    id_row ="row_"+cont+"";
    let rowTable = document.getElementById(id_row);
    rowTable.remove();
    sumarTotales()

    if(document.querySelectorAll('.btn.btn-outline-danger').length === 0 ){
        let submit = document.getElementById('enviar');
        submit.setAttribute('hidden',true)
    }

}

function sumarTotales(){
    let totalHtml = document.querySelectorAll('.input_total');
    suma = 0;

    for ( total of totalHtml) {
        suma += parseInt(total.value)
    }

    document.getElementById('totalPagar').textContent = suma
    document.getElementById('total').value = suma
}