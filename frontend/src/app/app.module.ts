import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClient, provideHttpClient, withFetch } from '@angular/common/http';
import { LandingComponent } from './landing/landing.component';
import { ProductCardComponent } from './shared/product-card/product-card.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductFormComponent } from './product/product-form/product-form.component';
import { WishlistComponent } from './shared/wishlist/wishlist.component';
import { CookieService } from 'ngx-cookie-service';
import { CartComponent } from './cart/cart.component';
import { CartSummaryComponent } from './cart/cart-summary/cart-summary.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ProductDetailsComponent } from './product/product-details/product-details.component';


@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    ProductCardComponent,
    ProductListComponent,
    ProductFormComponent,
    WishlistComponent,
    CartComponent,
    CartSummaryComponent,
    ProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatSnackBarModule
    
    
    
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(
      withFetch()
    ),
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
