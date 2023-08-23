import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registrationFailMsg: any;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  public registerForm = new FormGroup ({
    userName: new FormControl('', [Validators.required]),
    userFirstName: new FormControl('', [Validators.required]),
    userLastName: new FormControl('', [Validators.required]),
    userPassword: new FormControl('', [Validators.required])
  })

  get userName() {
    return this.registerForm.get('userName');
  }
  get userFirstName() {
    return this.registerForm.get('userFirstName');
  }
  get userLastName() {
    return this.registerForm.get('userLastName');
  }
  get userPassword() {
    return this.registerForm.get('userPassword');
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

  register() {
    this.userService.register(this.registerForm.value).subscribe(
      (response: any) => {
        localStorage.setItem("registeredUser", JSON.stringify(response));
        this.router.navigate(['/user']);
      },
      (error) => {
        console.log(error);
        if (error.status === 409) {
          this.registrationFailMsg = "Username already exists!"
        }
      }
    );
  }

  resetRegistrationFailMsg() {
    this.registrationFailMsg = null;
  }

}
