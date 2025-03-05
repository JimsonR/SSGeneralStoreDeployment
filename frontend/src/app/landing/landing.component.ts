import { Component } from '@angular/core';
// declare var bootstrap: any;

@Component({
  selector: 'app-landing',
  standalone: false,
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.scss'
})
export class LandingComponent {

  itemAndPrice: { value: number; label: string }[] = [];
  
  constructor(){
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
  
  }
  // ngAfterViewInit() {
  //   var carouselElement = document.querySelector('#carouselExample');
  //   if (carouselElement) {
  //     new bootstrap.Carousel(carouselElement, {
  //       interval: 2000, // Auto-slide every 2 seconds
  //       wrap: true
  //     });
  //   }
  // }

  selectedQuantity = 1;
  basePrice=350;

  get totalPrice(): number{
    return this.basePrice * this.selectedQuantity;
  }

}
