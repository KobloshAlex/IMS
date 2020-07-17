import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Ceo} from '../Ceo';
import {CeoService} from '../../../service/ceo.service';
import {Router} from '@angular/router';
import {Branch} from '../../branch/branch';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-add-branch',
  templateUrl: './add-branch.component.html',
  styleUrls: ['./add-branch.component.css']
})
export class AddBranchComponent implements OnInit {

  ceoList: Observable<Ceo[]>;
  branchList: Observable<Branch[]>;
  ceo: Ceo;
  ceoId: string;
  branchId: string;
  public error: any;

  constructor(private branchService: BranchService, private ceoService: CeoService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchCeoList();
    this.fetchBranchList();
  }

  fetchCeoList() {
    this.ceoList = this.ceoService.getAllCeos();
  }

  fetchBranchList() {
    this.branchList = this.branchService.getAllBranchs();
  }

  addBranchToCeo() {
    this.ceoService.addBranchToCeo(this.ceo, this.ceoId, this.branchId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.ceo = new Ceo();
    this.goBack();
  }

  submitBranch() {
    this.addBranchToCeo();
  }

  goBack() {
    this.router.navigate(['ceos']);
  }
}
