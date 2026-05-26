import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ProductoService {

  constructor() { }

  guardarProducto(producto: any) {

    console.log(
      'Producto enviado al service:',
      producto
    );
  }
}