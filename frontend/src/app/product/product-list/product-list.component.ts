import { Component, EventEmitter, Output } from '@angular/core';
import { ProductModel } from '../../models/ProductModel';
import { ProductService } from '../../services/product.service';
import { WishlistComponent } from '../../shared/wishlist/wishlist.component';
import { WishlistService } from '../../services/wishlist.service';


@Component({
  selector: 'app-product-list',
  standalone: false,
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent {

  @Output() wishListCountChange = new EventEmitter<number>();

  wishlistCount: number = 0;


products: ProductModel[] = []

constructor(private _productService : ProductService , private wishlistService:WishlistService
){}

ngOnInit(){
  this._productService.allProducts().subscribe({
    next:(response)=>{
    
      this.products = response.map(product => ({ ...product,
        wishlist: this.wishlistService.isInWishList(product.id)
       }));    
     
    }
      
  })
}

updateWishListCount(change:number){
  this.wishlistCount += change;
  this.wishListCountChange.emit(change)
  // console.log(this.wishlistCount)
}

}
