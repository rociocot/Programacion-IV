import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { ProductoService } from '../services/producto';

import { ProductoCreateDTO } from '../models/producto-create.dto';

@Component({
  selector: 'app-producto-alta',

  standalone: true,

  imports: [
    CommonModule,
    ReactiveFormsModule
  ],

  templateUrl: './producto-alta.html',

  styleUrl: './producto-alta.css'
})

export class ProductoAlta {

  productoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService
  ) {

    this.productoForm = this.fb.group({

      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],

      categoria: [
        '',
        Validators.required
      ],

      precio: [
        null,
        [
          Validators.required,
          Validators.min(1)
        ]
      ],

      stock: [
        null,
        [
          Validators.required,
          Validators.min(0)
        ]
      ],

      descripcion: ['']
    });
  }

  guardar() {

    if (this.productoForm.invalid) {
      return;
    }

    const producto: ProductoCreateDTO =
      this.productoForm.value;

    this.productoService.guardarProducto(
      producto
    );

    console.log(producto);

    this.productoForm.reset();
  }
}