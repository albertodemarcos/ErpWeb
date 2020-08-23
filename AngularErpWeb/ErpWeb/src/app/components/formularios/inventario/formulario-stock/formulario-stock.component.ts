import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { StockService } from 'src/app/services/inventario/stock.service';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';
import { Stock } from 'src/app/model/entitys/stock.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { Almacen } from 'src/app/model/entitys/almacen.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-formulario-stock',
  templateUrl: './formulario-stock.component.html',
  styleUrls: ['./formulario-stock.component.css']
})
export class FormularioStockComponent implements OnInit {

  public stockAlmacen: Stock;
  private stockAlmacenDto: any;
  private stockAlmacenId: number;
  public almacen: Almacen;
  public articulo: Articulo;
  private respuestaGetStock: AccionRespuesta;
  public erroresFormulario: Map<string, object>;

  // Autocompletar
  public keyword: string;
  public dataAlmacen: Almacen[];
  public dataArticulo: Articulo[];
  public mensajeError: string;
  public estaCargando: boolean;


  constructor(
    private stockAlmacenService: StockService,
    private autocompletarService: AutocompletarService,
    private router: Router,
    private activateRouter: ActivatedRoute) {

    this.stockAlmacen = new Stock();
    this.erroresFormulario = new Map<string, object>();
    // Autocompletar
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.stockAlmacenId = params['id'];
      if (this.stockAlmacenId != null){
        this.getEditarStockAlmacen();
      }
    });
    this.dataAlmacen = new Array<Almacen>();
    this.dataArticulo = new Array<Articulo>();
  }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearStockAlmacenFormulario(): void {

    console.log('Estamos dentro del metodo crearStockAlmacenFormulario()' + this.stockAlmacen );

    // Si tiene id, llamamos a crear, sino a editar
    if (this.stockAlmacen != null && this.stockAlmacen.id != null && this.stockAlmacen.id !== 0) {

      console.log('Vamos a editar el stock del almacen con ID: ' + this.stockAlmacen.id);

      this.stockAlmacen.almacenId = this.almacen.id;
      this.stockAlmacen.articuloId = this.articulo.id;

      this.stockAlmacenService.actualizarStockAlmacen(this.stockAlmacen).subscribe( accionRespuesta => {

        this.respuestaCrearEditarAlmacen(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.stockAlmacen.almacenId = this.almacen.id;
      this.stockAlmacen.articuloId = this.articulo.id;

      this.stockAlmacenService.crearStockAlmacen(this.stockAlmacen).subscribe( accionRespuesta => {

        console.log('Vamos a crear el stock del almacen con codigo: ' + this.stockAlmacen.codigo);

        this.respuestaCrearEditarAlmacen(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }
  }

  public getEditarStockAlmacen() {

    this.stockAlmacenService.getStockAlmacen(this.stockAlmacenId).toPromise().then(
      (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el stock');

          this.respuestaGetStock = accionRespuesta;

          if ( this.respuestaGetStock.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetStock.data) );
            console.log('ES: ' + typeof(this.respuestaGetStock.data));
            // tslint:disable-next-line: no-string-literal
            this.stockAlmacenDto = this.respuestaGetStock.data['stockArticuloDto'];
            this.obtenerStockAlmacenDesdeStockAlmacenDto(this.stockAlmacenDto);
          }
        }
        catch (errores)
        {
          console.log('Se ha producido un error al transformar el stock del almacen' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );
  }

  private obtenerStockAlmacenDesdeStockAlmacenDto(stockAlmacenDto: any): void{

    if ( stockAlmacenDto != null)
    {
      this.stockAlmacen.id = stockAlmacenDto.id;
      /*this.almacen.codigo = almacenDto.codigo;
      this.almacen.nombre = almacenDto.nombre;
      this.almacen.codigoPostal = almacenDto.codigoPostal;
      this.almacen.direccion = almacenDto.direccion;
      this.almacen.edificio = almacenDto.edificio;
      this.almacen.observaciones = almacenDto.observaciones;
      this.almacen.telefono = almacenDto.telefono;
      this.almacen.poblacion = almacenDto.poblacion;
      this.almacen.region = almacenDto.region;
      this.almacen.provincia = almacenDto.provincia;
      this.almacen.pais = almacenDto.pais;
      */
    }
  }

  public respuestaCrearEditarAlmacen(accionRespuesta: AccionRespuesta, esEditarStockAlmacen: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['stock', 'almacen', accionRespuesta.id]);

      if (esEditarStockAlmacen != null && esEditarStockAlmacen )
      {
        swal('Stock almacén editado', 'Se ha editado el stock del almacén correctamente', 'success');
       }
       else
       {
        swal('Nuevo stock almacén', 'Se ha creado el stock del almacén correctamente', 'success');
       }
    }
    else
    {
      // Error
      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }
      else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del stock almacén', 'error');
      }
    }

  }

  public getAlmacenes(term: any){
    this.estaCargando = true;
     // Limpiamos el array previamente
    this.dataAlmacen = new Array<Almacen>();
    this.autocompletarService.getAlmacenAutocompletar(term).then(
      (almacenes) => {
        try {
          // Introducimos los datos
          if ( almacenes !== null )
          {
            this.keyword = 'nombre';
            almacenes.forEach(almacen => {
              this.dataAlmacen.push(almacen);
            });
          }
          else
          {
            this.mensajeError = 'No existe el almacen';
          }
          this.estaCargando = false;
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
          this.estaCargando = false;
        }

      }, (errores) => {
        this.estaCargando = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      }
    );
  }

  public getArticulos(term: any){
    this.estaCargando = true;
    // Limpiamos el array previamente
    this.dataArticulo = new Array<Articulo>();
    this.autocompletarService.getArticuloAutcompletar(term).then(
      (articulos) => {
        try {
          // Introducimos los datos
          if ( articulos !== null )
          {
            this.keyword = 'nombre';
            articulos.forEach(articulo => {
              this.dataArticulo.push(articulo);
            });
          }
          else
          {
            this.mensajeError = 'No existe el almacen';
          }
          this.estaCargando = false;
        } catch (errores){
          this.estaCargando = false;
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }

      }, (errores) => {
        this.estaCargando = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      }
    );
  }

  public itemSeleccionado(item: any) {
    // do something with selected item
    console.log('Tenemos: ' + item);
  }

  public cambiosAlBuscar(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
    console.log('Entramos en cambiosAlBuscar()');
  }

  public cuadroSeleccionado(e: any) {
    // do something when input is focused
    console.log('Entramos en cuadroSeleccionado()');
  }

  public limpiarCuadroTextoAlmacen(){
    console.log('Limpiamos ');
    this.dataAlmacen = new Array<Almacen>();
  }

  public limpiarCuadroTextoArticulo(){
    console.log('Limpiamos ');
    this.dataArticulo = new Array<Articulo>();
  }

}