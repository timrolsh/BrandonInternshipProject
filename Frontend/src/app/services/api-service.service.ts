import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/project'; // The URL of your Spring Boot backend

  constructor(private http: HttpClient) {}

  //getSomeData() {
  //  return this.http.get(`${this.baseUrl}/endpoint`);
  //}
}
