import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ProductModel } from '../../models/ProductModel';
import { WishlistService } from '../../services/wishlist.service';
import { CartItemModel } from '../../models/CartItemModel';
import { CartService } from '../../services/cart.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-card',
  standalone: false,
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
// @Input() product! : {id:number, name : string, category:string, price: number, imageUrl:string}
@Input() product! : ProductModel
@Output() wishlistCountChange= new EventEmitter<number>;



itemAndPrice: { value: number; label: string }[] = [];

//cartQuantity
cartQuantity = 0;
  
  constructor(private wishlistService : WishlistService, private cartService: CartService , private snackbar : MatSnackBar){


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
    // this.wishlistService.toggleWishlist(this.product);
  this.isInWishlist(); // ✅ Ensure UI sync

  this.loadCart();

  
  }

  selectedQuantity = 1;
  basePrice=350;

  get totalPrice(): number{
    return this.basePrice * this.selectedQuantity;
  }

  toggleWishlist(){

    this.wishlistService.toggleWishlist(this.product)
    this.product.wishlist = this.wishlistService.isInWishList(this.product.id); // ✅ Ensure UI sync
  }

  isInWishlist(){
   return this.wishlistService.isInWishList(this.product.id)
  }

  addToCart(): void{
    const cartItem : CartItemModel = {

      productId : this.product.id,
      productName : this.product.name,
      productPrice: this.product.price,
      quantity:1,
      productImg:this.product.imageUrl
      
    };


    this.cartService.addToCart(cartItem)
    this.loadCart()
  }
  
// loading cart

loadCart(){
  const cartItem = this.cartService.getCartItem(this.product.id)
  this.cartQuantity = cartItem ? cartItem.quantity : 0;
}


  //increase quantity after adding

  increaseQuantity(){
    this.cartService.updateQuantity(this.product.id,1)
    this.loadCart()
    this.showPopup("product Added Successfully", 'success')
    }
    
    //decrease Quantity after adding

    decreaseQuantity(){
      this.cartService.updateQuantity(this.product.id,-1);
      this.loadCart()
    }


    
    showPopup(message : string, type: 'success'|'error'|'delete'){
      this.snackbar.open(message, "Close",{
        duration:3000,
        horizontalPosition: 'right',
        verticalPosition: 'bottom',
        panelClass: type === 'success' ? 'snackbar-success': 'snackbar-error',
      })
    }



}
