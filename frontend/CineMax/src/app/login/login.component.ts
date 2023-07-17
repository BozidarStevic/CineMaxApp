import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  public loginFailMsg: any;
  public registeredUser: any;

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem("registeredUser") != null) {
      this.registeredUser = JSON.parse(localStorage.getItem("registeredUser")!);
    }
  }

  loginUser() {
    this.userService.login(this.loginForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setRoles(response.user.roles);
        this.userAuthService.setToken(response.jwtToken);
        this.userAuthService.setUserName(response.user.userName);

        const role = response.user.roles[0].roleName;
        if (role === 'Admin') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/user']);
        }
      },
      (error) => {
        console.log(error);
        if (error.status === 401) {
          this.loginFailMsg = "The username or password is incorrect!"
        }
      }
    );
  }

  public loginForm = new FormGroup ({
    userName: new FormControl('', [Validators.required]),
    userPassword: new FormControl('', [Validators.required])
  })

  get userName() {
    return this.loginForm.get('userName');
  }
  get userPassword() {
    return this.loginForm.get('userPassword');
  }

  isValid(input: any): boolean {
    if (input) {
      if (input.valid && input.touched) {
        return true;
      }
    }
    return false;
  }

  isInvalid(input: any): boolean {
    if (input) {
      if (input.invalid && input.touched) {
        return true;
      }
    }
    return false;
  }

  ngOnDestroy() {
    this.resetRegisteredUser();
    this.loginFailMsg = null;
  }

  resetRegisteredUser() {
    this.registeredUser = null;
    localStorage.removeItem("registeredUser");
  }

  resetloginFailMsg() {
    this.loginFailMsg = null;
  }

}
