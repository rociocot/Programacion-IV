import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private apiUrl = 'http://localhost:8080/auth';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login(credentials: any): Observable<any> {

    return this.http.post<any>(
      `${this.apiUrl}/login`,
      credentials
    );

  }

  saveToken(token: string): void {

    localStorage.setItem('token', token);

  }

  getToken(): string | null {

    return localStorage.getItem('token');

  }

  logout(): void {

    localStorage.removeItem('token');

    this.router.navigate(['/login']);

  }

}