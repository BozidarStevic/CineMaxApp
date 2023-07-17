import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  deleteComment(commentId: number) {
    return this.http.delete(this.host + `/deleteComment?commentId=${commentId}`);
  }

  addComment(commentText: string, movieId: number, userName: string) {
    return this.http.get(this.host + `/addComment?commentText=${commentText}&movieId=${movieId}&userName=${userName}`);
  }

  getComments(movieId: number) {
    return this.http.get(this.host + `/getComments?movieId=${movieId}`, {headers: this.requestHeader,});
  }

}
