import { Component } from '@angular/core';
import { ProductModel } from '../../models/ProductModel';
import { WishlistService } from '../../services/wishlist.service';

@Component({
  selector: 'app-wishlist',
  standalone: false,
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.scss'
})
export class WishlistComponent {

  wishlistItems: ProductModel[] = [];

  constructor(private wishlistService : WishlistService){}

  ngOnInit(){
    // this.wishlistService.wishlistCount$    

    this.wishlistService.getWishlistItems().subscribe(products => {
      console.log(products)
      this.wishlistItems = products.map(product => ({
        ...product,
        wishlist:this.wishlistService.isInWishList(product.id)// sync with wishlist status
      }));

      
  })
  
  

}
// removeFromWIshlist(productId : number){
//   this.wishlistService.removeFromWishlist(productId)
// }

  
}
