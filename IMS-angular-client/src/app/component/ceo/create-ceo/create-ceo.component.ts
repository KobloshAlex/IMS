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

  ceo: Ceo = new Ceo();

  constructor(private route: ActivatedRoute, private router: Router, private ceoService: CeoService) {
  }

  ngOnInit(): void {
  }

  saveCeo() {
    this.ceoService.createCeo(this.ceo).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.ceo = new Ceo();
    this.gotoList();
  }

  onSubmit() {
    this.saveCeo();
  }

  gotoList() {
    this.router.navigate(['/ceos']);
  }
}
