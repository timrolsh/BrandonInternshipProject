import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {  
    this.authService.register(this.username, this.password)
      .subscribe({
        next: () => {
          this.router.navigate(['../login']);
        },
        error: (error) => {
          // Handle login error (e.g., display an error message)
          console.error('Registration failed', error);
        }
      });
  }
}
