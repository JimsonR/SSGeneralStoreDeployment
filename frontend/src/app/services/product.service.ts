import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { ProductModel } from '../models/ProductModel';
import { env } from 'node:process';
import { environment } from '../../environments/enviroment.prod';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  API_URL = environment.api

  constructor(private _http: HttpClient) { }

  // _url = "http://localhost:8080/api"
  _url = `${this.API_URL}`

  allProducts(): Observable<ProductModel[]>{

    const url = `${this._url}/products/all?pgNo=0&pgSize=100`

    return this._http.get<ProductModel[]>(url).pipe(catchError(this.errorHandler))


  }

  errorHandler(error: HttpErrorResponse){
    return throwError(()=> error.message || "Server Error")
  }

  //Uploading an Image
  sendImage(file : File) : Observable<string>{

    const formData = new FormData();
    formData.append('image', file);

   return this._http.post<string>(`${this._url}/images/upload`, formData , {responseType:'text'as 'json'}).pipe(catchError(this.errorHandler))
  }



  // Adding a product

  addProduct(product : ProductModel): Observable<string>{

 return this._http.post<string>(`${this._url}/products/upsert`,product , {responseType:'text'as 'json'}).pipe(catchError(this.errorHandler))
  }

  // get a product

  getProduct(id : number) : Observable<ProductModel>{
    return this._http.get<ProductModel>(`${this._url}/products/all/product-detail?id=${id}`).pipe(catchError(this.errorHandler))
  }


}
