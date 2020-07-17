import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Ceo} from '../Ceo';
import {CeoService} from '../../../service/ceo.service';

@Component({
  selector: 'app-read-ceo',
  templateUrl: './read-ceo.component.html',
  styleUrls: ['./read-ceo.component.css']
})
export class ReadCeoComponent implements OnInit {

  CeoId: string;
  ceo: Ceo;

  constructor(private route: ActivatedRoute, private router: Router, private ceoService: CeoService) {
  }

  ngOnInit(): void {
    this.ceo = new Ceo();

    this.CeoId = this.route.snapshot.params['ceoId'];


    this.ceoService.getCeo(this.CeoId).subscribe(data => {
        console.log(data);
        this.ceo = data;
      },
      error => console.log(error));
  }

  backToCeos() {
    this.router.navigate(['ceos']);
  }

  ceoUpdate(ceo: Ceo) {
    this.router.navigate(['updateCeo', ceo]);
  }
}
