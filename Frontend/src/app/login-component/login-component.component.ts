import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
    selector: 'app-login',
    templateUrl: './login-component.component.html',
    styleUrls: ['./login-component.component.css'],
})
export class LoginComponent {

    username: string = '';
    password: string = '';
    
    constructor(private authService: AuthService, private router: Router, private userService: UserService) { }

    login(): void {
      this.authService.login(this.username, this.password)
      .subscribe({
        next: (response) => {
          this.userService.setCurrentUser(this.username);
          this.router.navigate(['/list']);
        },
        error: (error) => {
          // Handle login error (e.g., display an error message)
          console.error('Login failed', error);
        }
      });
    }

    goToRegistration(): void {
        this.router.navigate(['/registration']); // Navigate to the RegistrationComponent
    }
}
