import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { TextEntryService } from '../services/text-entry.service';
import { TextEntry } from '../models/text-entry.model';

@Component({
  selector: 'app-text-entries-list',
  templateUrl: './text-entries-list-component.component.html',
  styleUrls: ['./text-entries-list-component.component.css'],
})
export class TextEntriesListComponent implements OnInit {
  textEntries: TextEntry[] = [];
  isAdmin: boolean = false; // Will get this from AuthService based on the logged-in user's role.

  constructor(
    private textEntryService: TextEntryService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.textEntryService.getTextInputs().subscribe((entries) => {
      console.log('Response received:', entries);

      this.textEntries = entries;

      console.log('Entry Array:', this.textEntries);

      this.cdr.detectChanges();
    });
  }

  deleteSelectedEntries() {
    if (
      localStorage.getItem('username') === undefined ||
      localStorage.getItem('username') !== 'admin'
    ) {
      alert(
        "Hey, you can't delete text entries. You are not logged in as an admin. You are logged in as: " +
          localStorage.getItem('username')
      );
    } else {
      const selectedEntries = this.textEntries.filter((entry) => entry.checked);
      if (selectedEntries.length === 0) {
        alert('No entries selected');
        return;
      }

      selectedEntries.forEach((entry) => {
        this.textEntryService.deleteEntry(entry.time).subscribe(() => {
          // Remove the entry from textEntries
          this.textEntries = this.textEntries.filter(
            (e) => e.time !== entry.time
          );
        });
      });
    }
  }
}
