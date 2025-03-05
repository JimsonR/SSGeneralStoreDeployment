import { Component } from '@angular/core';
import { CartItemModel } from '../models/CartItemModel';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-cart',
  standalone: false,
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
cartItems : CartItemModel[] = []

constructor(private cartService : CartService){}

ngOnInit(){
  this.cartItems = this.cartService.getCart();
}

loadCart() {
  this.cartItems = this.cartService.getCart();

}

increaseQuantity(productId : number){
this.cartService.updateQuantity(productId,1)
this.loadCart()
}

decreaseQuantity(productId : number){
  this.cartService.updateQuantity(productId,-1);
  this.loadCart()
}


removeItem(productId : number){
  this.cartService.removeFromCart(productId);
  this.loadCart();
}

clearCart(){
  this.cartService.clearCart();
  this.loadCart();
}

getTotalPrice(): number{
  return this.cartItems.reduce((sum,item)=> sum+item.productPrice * item.quantity, 0)
}


}
