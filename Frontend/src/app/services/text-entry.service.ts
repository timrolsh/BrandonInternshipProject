import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TextEntry } from '../models/text-entry.model';
import { map } from 'rxjs/operators';

//interface ApiResponse {
//    data: TextEntry[];
//}

@Injectable({
  providedIn: 'root',
})
export class TextEntryService {
  private BASE_URL = 'http://localhost:8080/project';

  constructor(private http: HttpClient) {}

  getTextInputs(): Observable<TextEntry[]> {
    return this.http.get</*ApiResponse*/ TextEntry[]>(`${this.BASE_URL}/list`);
    //.pipe(
    //    map(response => response.data)
    // );
  }

  getTextInput(id: number) {
    return this.http.get<TextEntry>(`${this.BASE_URL}/get/${id}`);
  }

  addTextInput(text: string, username: string) {
    return this.http.post<TextEntry>(`${this.BASE_URL}/add`, {
      text,
      username,
    });
  }

  deleteEntry(time: number) {
    return this.http.delete(`${this.BASE_URL}/delete/${time}`);
  }
}
