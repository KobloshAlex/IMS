import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './service/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showCeo = false;
  showCustomer = false;
  showAgent = false;
  showManager = false;
  username: string;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showCeo = this.roles.includes('ROLE_CEO');
      this.showCustomer = this.roles.includes('ROLE_CUSTOMER');
      this.showAgent = this.roles.includes('ROLE_AGENT');
      this.showManager = this.roles.includes('ROLE_MANAGER');

      this.username = user.username;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    //this.router.navigate(['home'])
    window.location.replace('home');
  }
}
