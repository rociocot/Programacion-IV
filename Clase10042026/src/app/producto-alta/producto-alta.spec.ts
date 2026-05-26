import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoAlta } from './producto-alta';

describe('ProductoAlta', () => {
  let component: ProductoAlta;
  let fixture: ComponentFixture<ProductoAlta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductoAlta],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductoAlta);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
