import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductFormComponent } from './product/product-form/product-form.component';
import { WishlistComponent } from './shared/wishlist/wishlist.component';
import { CartComponent } from './cart/cart.component';
import { ProductDetailsComponent } from './product/product-details/product-details.component';

const routes: Routes = [
{path:"",redirectTo:"/home",pathMatch:'full'},
{path:"home", component:LandingComponent},
{path:"products",component:ProductListComponent},
{path:"productform",component:ProductFormComponent},
{path:"wishlist", component:WishlistComponent},
{path:"cart", component:CartComponent},
{path: 'products/:id', component: ProductDetailsComponent , data: { renderMode: 'no-prerender' }},
{path:'**',redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
