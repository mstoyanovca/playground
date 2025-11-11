import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../model/user';

@Component({
  selector: 'app-login-page',
  imports: [
      FormsModule
  ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
    user = new User(1, '', '');
    submitted = false;

    onSubmit() {
        console.log('onSubmit()');
        this.submitted = true;
    }
}
