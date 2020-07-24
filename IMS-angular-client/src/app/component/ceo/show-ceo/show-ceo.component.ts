import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Ceo} from '../Ceo';
import {CeoService} from '../../../service/ceo.service';

@Component({
  selector: 'app-show-ceo',
  templateUrl: './show-ceo.component.html',
  styleUrls: ['./show-ceo.component.css']
})
export class ShowCeoComponent implements OnInit {

  isSignUpFailed = false;
  errorMessage = '';
  ceo: Observable<Ceo[]>;

  constructor(private ceoService: CeoService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchCeoList();
  }

  fetchCeoList() {
    this.ceo = this.ceoService.getAllCeos();
  }

  deleteCeo(id: string) {
    this.ceoService.deleteCeo(id).subscribe(data => {
        console.log(data);
        this.fetchCeoList();
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  ceoDetails(id: string) {
    this.router.navigate(['ceoDetails', id]);
  }

  ceoUpdate(ceo: Ceo) {
    this.router.navigate(['updateCeo', ceo]);
  }
}
