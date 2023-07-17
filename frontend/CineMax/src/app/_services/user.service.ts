import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(
    private http: HttpClient,
    private userAuthService: UserAuthService
  ) {}

  public login(loginData: any) {
    return this.http.post(this.host + '/authenticate', loginData, {
      headers: this.requestHeader,
    });
  }

  public register(registerData: any) {
    return this.http.post(this.host + '/registerNewUser', registerData, {
      headers: this.requestHeader,
    });
  }

  public roleMatch(allowedRoles: string | any[]): boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          }
        }
      }
      return isMatch;
    }
    return isMatch;
  }

  getUsers(currentPage: number, sort: string, userName: string, userFirstName: string, userLastName: string) { 
    return this.http.get(this.host + 
      `/users?page=${currentPage}&sortUserName=${sort}&userName=${userName}&userFirstName=${userFirstName}&userLastName=${userLastName}`);
  }

  deleteUser(userName: string) {
    return this.http.delete(this.host + `/deleteUser?userName=${userName}`);
  }

}
