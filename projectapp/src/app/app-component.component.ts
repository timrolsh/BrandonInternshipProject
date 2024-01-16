import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app-component.component.html',
  styleUrls: ['./app-component.component.css']
})
export class AppComponent {
  
  title: string;

  constructor() {
    this.title = 'ProjectApp';
  }
}