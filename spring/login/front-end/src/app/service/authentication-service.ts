import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
    private http = inject(HttpClient);

    login(email: string, password: string) {
        console.log("Logging in with email = " + email);
        return this.http.post<User>('/login', {email, password});
    }
}
