import { Component } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent {


  ngOnInit() {
    console.log("upravi")
  }



  ispis() {
    console.log("stisnuo");
    
    
  }
  

}


