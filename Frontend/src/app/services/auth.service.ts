import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private BASE_URL = 'http://localhost:8080/project';

  constructor(private http: HttpClient) {}

  // You'll add login and logout methods here
  login(username: string, password: string): Observable<any> {
    const loginData = { username, password };
    return this.http.post<any>(`${this.BASE_URL}/login`, loginData);
  }

  logout() {
    // Implement based on your backend's logout strategy
  }

  register(username: string, password: string): Observable<any> {
    const registrationData = { username, password };
    return this.http.post<any>(`${this.BASE_URL}/register`, registrationData);
  }
}
