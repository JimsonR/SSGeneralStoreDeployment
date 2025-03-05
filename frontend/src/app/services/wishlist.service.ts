import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject } from 'rxjs';
import { ProductModel } from '../models/ProductModel';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private wishlist: ProductModel[] = []
  private wishlistSubject = new BehaviorSubject<ProductModel[]>([]);
  wishlist$ = this.wishlistSubject.asObservable();

  private wishlistCount = new BehaviorSubject<number>(0);
  wishlistCount$ = this.wishlistCount.asObservable(); 

  constructor(private cookieService : CookieService) {
    this.loadWishlistFromCookies();
   }

  toggleWishlist(product :ProductModel){
    const index = this.wishlist.findIndex(item => item.id === product.id)

    if(index > -1){
      // Remove product

      this.wishlist.splice(index,1);
      this.updateWishlistCount(-1)


    } else{
      // Add product

      this.wishlist.push(product);
      this.updateWishlistCount(1)
    }


    this.saveWishlistToCookies(); //save to cookies
    // Update Behaviour Subjects
    this.wishlistSubject.next([...this.wishlist]);

    console.log(this.wishlist)
    this.wishlistCount.next(this.wishlist.length);

  }

  isInWishList(productId: number){
    return this.wishlist.some(item => item.id === productId)
  }

  getWishlistItems()  {
    return this.wishlistSubject.asObservable();
  }

  removeFromWishlist(productId : number){
    this.wishlist = this.wishlist.filter(p => p.id !== productId)
    this.updateWishlistCount(-1);
    this.saveWishlistToCookies(); // save after removal
    // this.wishlistCount.next(this.wishlist.length)
    this.wishlistSubject.next([...this.wishlist])
  }

  updateWishlistCount(change : number){
    this.wishlistCount.next(this.wishlistCount.value + change);
  }

  //Save wishlist to cookies
  private saveWishlistToCookies(){
    const wishlistString = JSON.stringify(this.wishlist)

    this.cookieService.set('wishlist',wishlistString, 7); // expires in 7 days

  }

  // Load wishlist fom cookies
  private loadWishlistFromCookies(){
    const wishlistString = this.cookieService.get('wishlist');

    if(wishlistString){
      this.wishlist = JSON.parse(wishlistString)
      this.wishlistSubject.next([...this.wishlist])
      this.wishlistCount.next(this.wishlist.length)
    }

  }

}
