import { Component } from '@angular/core';
import { WishlistService } from './services/wishlist.service';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  wishlistCount : number = 0;
  cartCount : number= 0;


  itemAndPrice: { value: number; label: string }[] = [];
  
  constructor( private wishListService : WishlistService, private cartService : CartService){

  // Simulate API delay
  setTimeout(() => {
    this.itemAndPrice = [
      { value: 1, label: '250 g' },
      { value: 2, label: '500 g' },
      { value: 3, label: '1 kg' },
      { value: 4, label: '1.5 kg' },
      { value: 5, label: '2 kg' }
    ];
  }, 100); // Slight delay ensures proper hydration
}

  ngOnInit(){

    
    this.wishListService.wishlistCount$.subscribe(count => {
      this.wishlistCount = count;
    })


  this.cartService.cartCount$.subscribe(count => {
    this.cartCount = count;
  })
  }

  selectedQuantity = 1;
  basePrice=350;

  get totalPrice(): number{
    return this.basePrice * this.selectedQuantity;
  }



}
