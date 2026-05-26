import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductoAlta } from './producto-alta/producto-alta';

@Component({
  selector: 'app-root',
  imports: [
  RouterOutlet,
  ProductoAlta
],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-productos');
}
