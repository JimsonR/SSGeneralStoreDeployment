import { Component } from '@angular/core';
import { CartItemModel } from '../../models/CartItemModel';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart-summary',
  standalone: false,
  templateUrl: './cart-summary.component.html',
  styleUrl: './cart-summary.component.scss'
})
export class CartSummaryComponent {


  cartItems: CartItemModel[] = []
  totalPrice: number = 0;

  constructor(private cartService : CartService){}

  ngOnInit(){
    this.cartItems = this.cartService.getCart();
    this.totalPrice = this.cartItems.reduce((sum,item)=> sum + item.productPrice * item.quantity, 0);
  }

}
