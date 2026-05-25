import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';

import { Router } from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})

export class LoginComponent {

  loginData = {

    username: '',
    password: ''

  };

  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLogin() {

    this.authService.login(this.loginData).subscribe({

      next: (response) => {

        this.authService.saveToken(response.token);

        this.router.navigate(['/dashboard']);

      },

      error: () => {

        this.errorMessage =
          'Credenciales inválidas, intente de nuevo';

      }

    });

  }

}