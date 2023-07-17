import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getRoles() {
    return this.http.get(this.host + `/getRoles`);
  }

  setNewRole(roleName: string, userName: string) {
    return this.http.get(this.host + `/setNewRole?roleName=${roleName}&userName=${userName}`);
  }

}
