import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommentService } from '../_services/comment.service';
import { RateService } from '../_services/rate.service';
import { UserAuthService } from '../_services/user-auth.service';


@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  public ratedByCurrUser = false;
  public response: any;
  public movie: any;
  public averageRate: number = 0;
  public commentText: string = "";
  public comments: any;

  currentRate = 0;

  constructor(
    private rateService: RateService,
    private commentService: CommentService,
    private router: Router,
    private userAuthService: UserAuthService) 
  {}

  ngOnInit(): void {
    this.movie = JSON.parse(localStorage.getItem("currentMovieDetails") || '{}');
    this.averageRate = this.movie.averageRate;
    if (this.isLoggedIn()) this.isRated();
    this.getComments();
  }

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  usersComent(com: any) {
    if (this.isLoggedIn()) {
      if (this.userAuthService.getRoles().length > 0) {
        if (this.userAuthService.getRoles()[0].roleName === "Admin") {
          return true;
        }
      }
      return this.userAuthService.getUserName() === com.user.userName;
    } else {
      return false;
    }
  }

  rateMovie() {
    let userName = this.userAuthService.getUserName();
    this.rateService.rateMovie(this.currentRate, this.movie.id, userName)
      .subscribe(resp => {
        this.response = resp;
        this.movie.averageRate = this.response.averageRate.toFixed(2);
        this.ratedByCurrUser = true;
       });
  }

  isRated() {
    let userName = this.userAuthService.getUserName();
    this.rateService.isRated(this.movie.id, userName)
      .subscribe(resp => {
        this.response = resp;
        this.ratedByCurrUser = this.response;
      });
  }

  addComment() {
    let userName = this.userAuthService.getUserName();
    this.commentService.addComment(this.commentText, this.movie.id, userName)
    .subscribe(resp => {this.getComments();});
  }

  getComments() {
    this.commentService.getComments(this.movie.id).subscribe(resp => {
        this.comments = resp;
      });
  }

  goToTickets(movie: any) {
    this.router.navigate(["/tickets"]);
  }

  deleteComment(commentId: number) {
    this.commentService.deleteComment(commentId).subscribe(resp => {
      this.getComments();
    });
  }

}
