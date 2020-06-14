import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from 'src/app/components/home/home.component';

// LISTADOS
import { ListadoClientesComponent } from './components/listados/crm/listado-clientes/listado-clientes.component';
import { ListadoIngresosComponent } from './components/listados/bi/listado-ingresos/listado-ingresos.component';
import { ListadoGastosComponent } from './components/listados/bi/listado-gastos/listado-gastos.component';
import { ListadoResultadosComponent } from './components/listados/bi/listado-resultados/listado-resultados.component';
import { ListadoContratosComponent } from './components/listados/ventas/listado-contratos/listado-contratos.component';
import { ListadoVentasComponent } from './components/listados/ventas/listado-ventas/listado-ventas.component';
import { ListadoFacturasComponent } from './components/listados/ventas/listado-facturas/listado-facturas.component';
import { ListadoComprasComponent } from './components/listados/compras/listado-compras/listado-compras.component';
import { ListadoPedidosComponent } from './components/listados/compras/listado-pedidos/listado-pedidos.component';
import { ListadoProveedoresComponent } from './components/listados/compras/listado-proveedores/listado-proveedores.component';
import { ListadoArticulosComponent } from './components/listados/inventario/listado-articulos/listado-articulos.component';
import { ListadoAlmacenComponent } from './components/listados/inventario/listado-almacen/listado-almacen.component';
import { ListadoVehiculosComponent } from './components/listados/inventario/listado-vehiculos/listado-vehiculos.component';
import { ListadoEmpleadosComponent } from './components/listados/empresa/listado-empleados/listado-empleados.component';

// FORMULARIOS
import { FormularioClienteComponent } from './components/formularios/cllientes/formulario-cliente/formulario-cliente.component';
import { FormularioGastoComponent } from './components/formularios/bi/formulario-gasto/formulario-gasto.component';
import { FormularioIngresoComponent } from './components/formularios/bi/formulario-ingreso/formulario-ingreso.component';
import { FormularioCompraComponent } from './components/formularios/compras/formulario-compra/formulario-compra.component';
import { FormularioPedidoComponent } from './components/formularios/compras/formulario-pedido/formulario-pedido.component';
import { FormularioProveedorComponent } from './components/formularios/compras/formulario-proveedor/formulario-proveedor.component';
import { FormularioEmpleadoComponent } from './components/formularios/empresa/formulario-empleado/formulario-empleado.component';
import { FormularioAlmacenComponent } from './components/formularios/inventario/formulario-almacen/formulario-almacen.component';
import { FormularioArticuloComponent } from './components/formularios/inventario/formulario-articulo/formulario-articulo.component';
import { FormularioVehiculoComponent } from './components/formularios/inventario/formulario-vehiculo/formulario-vehiculo.component';
import { FormularioVentaComponent } from './components/formularios/ventas/formulario-venta/formulario-venta.component';
import { FormularioContratoComponent } from './components/formularios/ventas/formulario-contrato/formulario-contrato.component';
import { FormularioFacturaComponent } from './components/formularios/ventas/formulario-factura/formulario-factura.component';

import { ModalClienteComponent } from './components/modales/crm/modal-cliente/modal-cliente.component';

const routes: Routes = [
  // RUTA INICIO
  { path: 'inicio', component: HomeComponent },
  // RUTAS CRM
  { path: 'clientes', component: ListadoClientesComponent },
  { path: 'clientes/nuevo-cliente', component: FormularioClienteComponent },
  { path: 'clientes/cliente', component: ModalClienteComponent },
  // RUTAS BI
  { path: 'ingresos', component: ListadoIngresosComponent },
  { path: 'ingresos/nuevo-ingreso', component: FormularioIngresoComponent },
  { path: 'gastos', component: ListadoGastosComponent },
  { path: 'gastos/nuevo-gasto', component: FormularioGastoComponent },
  { path: 'resultados', component: ListadoResultadosComponent },
  // RUTAS VENTAS
  { path: 'contratos', component: ListadoContratosComponent },
  { path: 'contratos/nuevo-contrato', component: FormularioContratoComponent },
  { path: 'ventas', component: ListadoVentasComponent },
  { path: 'ventas/nueva-venta', component: FormularioVentaComponent },
  { path: 'facturas', component: ListadoFacturasComponent },
  { path: 'facturas/nuevo-factura', component: FormularioFacturaComponent },
  // RUTAS COMPRAS
  { path: 'compras', component: ListadoComprasComponent },
  { path: 'compras/nueva-compra', component: FormularioCompraComponent },
  { path: 'pedidos', component: ListadoPedidosComponent },
  { path: 'pedidos/nuevo-pedido', component: FormularioPedidoComponent },
  { path: 'proveedor', component: ListadoProveedoresComponent },
  { path: 'proveedor/nuevo-proveedor', component: FormularioProveedorComponent },
  // RUTAS INVENTARIO
  { path: 'catalgo/articulos', component: ListadoArticulosComponent },
  { path: 'catalgo/articulos/nuevo-articulo', component: FormularioArticuloComponent },
  { path: 'almacenes', component: ListadoAlmacenComponent },
  { path: 'almacenes/nuevo-almacen', component: FormularioAlmacenComponent },
  { path: 'vehiculos', component: ListadoVehiculosComponent },
  { path: 'vehiculos/nuevo-vehiculo', component: FormularioVehiculoComponent },
  // RUTAS EMPRESA
  { path: 'empresa', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'rrhh', component: ListadoEmpleadosComponent },
  { path: 'rrhh/nuevo-empleado', component: FormularioEmpleadoComponent },
  // RUTAS PERFIL
  { path: 'perfil', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'salir', component: FormularioClienteComponent }, // CAMBIAR
  { path: '**', redirectTo: 'inicio', pathMatch: 'full'}, // Ruta general
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }


/* RUTAS FUTURAS */
// { path: 'clientes/buscar-clientes', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'ingresos/buscar-ingreso', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'gastos/buscar-gasto', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'contratos/buscar-contrato', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'ventas/buscar-venta', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'facturas/buscar-factura', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'compras/buscar-compra', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'pedidos/buscar-pedido', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'proveedor/buscar-proveedor', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'catalgo/articulos/buscar-articulo', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'almacenes/buscar-almacen', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'vehiculos/buscar-vehiculo', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'rrhh/buscar-empleado', component: FormularioClienteComponent }, // CAMBIAR
