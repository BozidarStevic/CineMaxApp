import { Component, OnInit } from '@angular/core';
import { RoleService } from '../_services/role.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  public sort: string;
  public currentPage: number;
  public username: string;
  public firstName: string;
  public lastName: string;
  public users: any;
  public pageCount: number;
  public response: any;
  public roles: any;

  constructor(
    public roleService: RoleService,
    public userService: UserService,
    ) 
   {
    this.sort = "asc"
    this.currentPage = 0;
    this.pageCount = 0;
    this.username = "";
    this.firstName = "";
    this.lastName = "";
   }

  ngOnInit(): void {
    this.getUsers();
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles().subscribe(resp => {
      this.roles = resp;
    });
  }

  changeSort() {
    switch (this.sort) {
      case "asc": {
        this.sort = "desc";
        break;
      }
      case "desc": {
        this.sort = "asc";
        break;
      }
    }
    this.getUsers();
  }

  getUsers() {
    this.userService
      .getUsers(this.currentPage, this.sort, this.username, this.firstName, this.lastName)
      .subscribe(resp => {
        this.response = resp;
        this.users = this.response.content;
        this.pageCount = this.response.totalPages;
      });
  }

  filterUsers() {
    this.currentPage = 0;
    this.getUsers();
  }

  resetFilters() {
    this.currentPage = 0;
    this.sort = "desc";
    this.username = "";
    this.firstName = "";
    this.lastName = "";

    this.getUsers();
  }

  decrementPage() {
    if (this.currentPage > 0) {
      this.currentPage = this.currentPage - 1;
      this.getUsers();
    }
  }

  incrementPage() {
    if (this.currentPage < this.pageCount - 1) {
      this.currentPage = this.currentPage + 1;
      this.getUsers();
    }
  }

  createRange(num: number) {
    return new Array(num);
  }

  goToPage(pageNum: number) {
    this.currentPage = pageNum;
    this.getUsers();
  }

  deleteUser(userName: string) {
    this.userService.deleteUser(userName).subscribe(resp => {
      this.getUsers();
    });
  }

  setNewRole(roleName: any, userName: string) {
    this.roleService.setNewRole(roleName, userName).subscribe(resp => {
      this.getUsers();
    });
  }

}
