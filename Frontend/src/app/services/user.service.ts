import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private currentUserSubject = new User('');

  constructor() {}

  setCurrentUser(username: string) {
    const user = new User(username);
    this.currentUserSubject = user;
  }

  getCurrentUser() {
    return this.currentUserSubject;
  }
}
