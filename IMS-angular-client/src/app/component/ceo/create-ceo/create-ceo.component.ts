import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Ceo} from '../Ceo';
import {CeoService} from '../../../service/ceo.service';

@Component({
  selector: 'app-create-ceo',
  templateUrl: './create-ceo.component.html',
  styleUrls: ['./create-ceo.component.css']
})
export class CreateCeoComponent implements OnInit {
  isError = false;
  isSuccessful = false;
  errorMessage = '';
  ceo: Ceo = new Ceo();

  constructor(private route: ActivatedRoute, private router: Router, private ceoService: CeoService) {
  }

  ngOnInit(): void {
  }

  saveCeo() {
    this.ceoService.createCeo(this.ceo).subscribe(data => {
        this.isSuccessful = true
        this.isError = false;
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.ceo = new Ceo();
  }

  onSubmit() {
    this.saveCeo();
  }

  gotoList() {
    this.router.navigate(['/ceos']);
  }
}
