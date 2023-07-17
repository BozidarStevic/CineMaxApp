import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  updateCategory(category: any) {
    return this.http.get(this.host + `/updateCategory?categoryId=${category.id}&categoryName=${category.name}`);
  }

  deleteCategory(categoryId: number) {
    return this.http.delete(this.host + `/deleteCategory?categoryId=${categoryId}`);
  }

  addCategory(categoryName: string) {
    return this.http.get(this.host + `/addCategory?categoryName=${categoryName}`);
  }

  getCategories() {
    return this.http.get(this.host + `/categories`, {headers: this.requestHeader,});
  }

}
