import { Injectable } from '@angular/core';
import path from 'path';
import { CookieService } from 'ngx-cookie-service';
import { CartItemModel } from '../models/CartItemModel';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartKey = 'cart';  // Key for storing in cookies
  private cartCount = new BehaviorSubject<number>(0);
  cartCount$ = this.cartCount.asObservable();

  constructor(private cookieService: CookieService) {
    this.updateCartCount();
   }

  //get cart from cookies

  getCart(): CartItemModel[]{
    const cartData = this.cookieService.get(this.cartKey);
    return cartData ? JSON.parse(cartData) : [];
  }

  //Add an item to the cart

  addToCart(item: CartItemModel): void {
    let cart = this.getCart();
    const existingItem = cart.find((cartItem) => cartItem.productId === item.productId);

    if (existingItem){
      existingItem.quantity += item.quantity;

    }else{
      cart.push(item);

    }
    this.saveCart(cart);
  }

  //Remove an item from the cart

  removeFromCart(productId: number): void{
    let cart = this.getCart().filter((item) => item.productId !== productId);
    this.saveCart(cart);
  }

  //Clear entire cart
  clearCart(): void{
    this.cookieService.delete(this.cartKey);
    this.cartCount.next(0);
  }

  //Save cart to cookies

  private saveCart(cart: CartItemModel[]): void{
    this.cookieService.set(this.cartKey, JSON.stringify(cart));
    this.updateCartCount();
  }

  private updateCartCount(){
    const count = this.getCart().reduce((total,item)=> total + item.quantity, 0)
    this.cartCount.next(count);
  }

  updateQuantity(productId : number , change:number){
    let cart = this.getCart();
    const item = cart.find(cartItem => cartItem.productId === productId)

    if(item){
      item.quantity += change;
      if(item.quantity <= 0){
        this.removeFromCart(productId);
      }else{
        this.saveCart(cart);
      }
    }

  }

  getCartItem(productId: number): CartItemModel | undefined {
    let cart = this.getCart();
   return cart.find(cartItem => cartItem.productId === productId)
  }


}
