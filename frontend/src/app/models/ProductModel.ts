export interface ProductModel{
    id : number,
    name : string,
    category:string,
    price:number,
    imageUrl:string,
    wishlist?:boolean,
    cartCount?:number
}