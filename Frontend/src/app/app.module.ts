import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app-component.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login-component/login-component.component';
import { TextEntriesListComponent } from './text-entries-list-component/text-entries-list-component.component';
import { TextInputComponent } from './text-input-component/text-input-component.component';
import { AuthService } from './services/auth.service';
import { RegistrationComponent } from './registration/registration.component';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    LoginComponent,
    TextEntriesListComponent,
    TextInputComponent,
    AppComponent,
    RegistrationComponent,
  ],
  imports: [BrowserModule, FormsModule, AppRoutingModule, HttpClientModule],
  providers: [AuthService, UserService],
  bootstrap: [AppComponent],
})
export class AppModule {}
