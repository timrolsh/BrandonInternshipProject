//import { User } from './user.model';

export class TextEntry {
    textId: number;
    username?: string;
    text?: string;
    time: number;
    checked?: Boolean;

    constructor(textId: number, username: string, text: string, time: number) {
        this.textId = textId;
        this.username = username;
        this.text = text;
        this.time = time;
    }
}