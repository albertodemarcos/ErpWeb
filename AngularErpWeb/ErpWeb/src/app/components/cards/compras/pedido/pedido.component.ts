import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';


@Component({
  selector: 'app-pedido',
  templateUrl: './pedido.component.html',
  styleUrls: ['./pedido.component.css']
})
export class PedidoComponent implements OnInit {

  public pedido: Pedido;
  private pedidoDto: any;
  private pedidoId: number;
  private respuestaGetPedido: AccionRespuesta;

  constructor(private pedidoService: PedidoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.pedidoId = 0;
    this.pedido = new Pedido();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.pedidoId = params['id'];
      this.getPedido();
    } );
  }

  getPedido(): void{

    this.pedidoService.getPedido(this.pedidoId).toPromise().then( (pedidoDto) => {
      try
      {
        console.log('Recuperamos el Pedido');

        this.respuestaGetPedido = pedidoDto;

        if ( this.respuestaGetPedido.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetPedido.data) );
        console.log('ES: ' + typeof(this.respuestaGetPedido.data));
        // tslint:disable-next-line: no-string-literal
        this.pedidoDto = this.respuestaGetPedido.data['pedidoDto'];
        this.obtenerPedidoDesdePedidoDto(this.pedidoDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Pedido' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Pedido' + error);
      }
    );
  }

   obtenerPedidoDesdePedidoDto(pedidoDto: any): void{

      if ( pedidoDto != null)
      {
        this.pedido.id = pedidoDto.id;
        this.pedido.codigo = pedidoDto.codigo;
        this.pedido.fechaPedido = pedidoDto.fechaPedido;
        this.pedido.articulo = pedidoDto.articulo;
        this.pedido.cantidad = pedidoDto.cantidad;
        this.pedido.baseImponibleTotal = pedidoDto.baseImponibleTotal;
        this.pedido.impuesto = pedidoDto.impuesto;
        this.pedido.importeTotal = pedidoDto.id;
        this.pedido.id = pedidoDto.id;
        this.pedido.id = pedidoDto.id;
      }
    }

  ngOnInit(): void {
  }

}