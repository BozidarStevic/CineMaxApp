import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './_services/user.service';
import { MoviesComponent } from './movies/movies.component';
import { TicketsComponent } from './tickets/tickets.component';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RepertoireComponent } from './repertoire/repertoire.component';
import { BookTicketComponent } from './book-ticket/book-ticket.component';
import { RegisterComponent } from './register/register.component';
import { AdminMoviesComponent } from './admin-movies/admin-movies.component';
import { AdminProjectionsComponent } from './admin-projections/admin-projections.component';
import { AdminTicketsComponent } from './admin-tickets/admin-tickets.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { RxStompService } from './_services/rx-stomp.service';
import { rxStompServiceFactory } from './_config/rx-stomp-service-factory';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminComponent,
    UserComponent,
    LoginComponent,
    HeaderComponent,
    ForbiddenComponent,
    MoviesComponent,
    TicketsComponent,
    MovieDetailsComponent,
    RepertoireComponent,
    BookTicketComponent,
    RegisterComponent,
    AdminMoviesComponent,
    AdminProjectionsComponent,
    AdminTicketsComponent,
    AdminUsersComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    CarouselModule,
    NgbModule
  ],
  providers: [
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
    },
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
