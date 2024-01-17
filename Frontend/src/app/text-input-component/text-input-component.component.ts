import { Component } from '@angular/core';
import { TextEntryService } from '../services/text-entry.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-text-input',
  templateUrl: './text-input-component.component.html',
  styleUrls: ['./text-input-component.component.css'],
})
export class TextInputComponent {
  text: string = '';

  constructor(
    private textEntryService: TextEntryService,
    private userService: UserService
  ) {}

  // going to replace this with simple local storage method can be changed later when authorization is added
  // username: string = this.userService.getCurrentUser()?.username ?? '';
  username: string = localStorage.getItem('username') ?? '';

  onSubmit() {
    this.textEntryService
      .addTextInput(this.text, this.username)
      .subscribe((response) => {
        console.log('Response received:', this.text);
        this.text = ''; // Resetting the textarea after submitting
      });
  }
}
