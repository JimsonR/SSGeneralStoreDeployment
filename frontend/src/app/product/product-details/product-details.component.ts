import { Component, OnInit } from '@angular/core';
import { ProductModel } from '../../models/ProductModel';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { response } from 'express';

@Component({
  selector: 'app-product-details',
  standalone: false,
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.scss'
})
export class ProductDetailsComponent implements OnInit {
product!: ProductModel;

constructor(
  private route: ActivatedRoute,
  private productService: ProductService
)
{}

ngOnInit(): void{
  const id = Number(this.route.snapshot.paramMap.get('id'));
  this.productService.getProduct(id).subscribe((data) =>{
    this.product = data;
  });
}
addToCart(){
  return this.productService.addProduct(this.product).subscribe({
    next : response => {
      
    }
  })
}


}
