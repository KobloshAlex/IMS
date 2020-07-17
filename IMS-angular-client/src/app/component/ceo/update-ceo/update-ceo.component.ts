import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Ceo} from '../Ceo';
import {CeoService} from '../../../service/ceo.service';

@Component({
  selector: 'app-update-ceo',
  templateUrl: './update-ceo.component.html',
  styleUrls: ['./update-ceo.component.css']
})
export class UpdateCeoComponent implements OnInit {

  ceoId: string;
  branchId: string;
  ceo: Ceo;

  constructor(private route: ActivatedRoute, private router: Router, private ceoService: CeoService) {
  }

  ngOnInit(): void {
    this.ceo = new Ceo();
    this.ceoId = this.route.snapshot.params['ceoId'];

    this.ceoService.getCeo(this.ceoId).subscribe(data => {
        console.log(data);
        this.ceo = data;
      },
      error => console.log(error));
  }

  updateCeo() {
    this.ceoService.updateCeo(this.ceo, this.ceoId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.ceo = new Ceo();
    this.gotoList();
  }

  onSubmit() {
    this.updateCeo();
  }

  gotoList() {
    this.router.navigate(['/ceos']);
  }

  addBranchToCeo() {
    this.ceoService.addBranchToCeo(this.ceo, this.ceoId, this.branchId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.ceo = new Ceo();
  }

  submitBranch() {
    this.addBranchToCeo();
  }
}
