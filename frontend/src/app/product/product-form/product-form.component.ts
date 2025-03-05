import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-form',
  standalone: false,
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.scss'
})
export class ProductFormComponent {



  productForm: FormGroup;
  imageUrl: string | null = null;
  isUploading: boolean = false;
  uploadError: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient , private productService : ProductService){
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      price: [0,[Validators.required,Validators.min(1)]],
      imageUrl: ['',Validators.required]
    });
  }

  onFileSelected(event: any){
    const file: File = event.target.files[0];
    if(file){
      this.uploadImage(file);
    }
  }

  uploadImage(file: File){
    this.isUploading = true;
    this.uploadError = null;

    this.productService.sendImage(file).subscribe({
      next: (response) =>{
        this.imageUrl = response;
        this.productForm.patchValue({imageUrl: this.imageUrl});
        this.isUploading = false;
      },

      error: () =>{
        this.uploadError = 'Image upload failed. Try again.';
        this.isUploading = false;
      }

    })

    
  }

 onSubmit(){
  if(this.productForm.valid){
    this.productService.addProduct(this.productForm.value).subscribe({
      next: ()=>{
        alert('product added successfully');
        this.productForm.reset();
        this.imageUrl = null;
      },
      error: () => alert('Failed to add product.')
    });
  }
 }

}
