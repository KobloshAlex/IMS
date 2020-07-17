import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Branch} from '../branch';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-show-branch',
  templateUrl: './show-branch.component.html',
  styleUrls: ['./show-branch.component.css']
})
export class ShowBranchComponent implements OnInit {

  branches: Observable<Branch[]>;

  constructor(private branchService: BranchService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchBranchList();
  }

  fetchBranchList() {
    this.branches = this.branchService.getAllBranchs();
  }

  deleteBranch(id: string) {
    this.branchService.deleteBranch(id).subscribe(data => {
        console.log(data);
        this.fetchBranchList();
      },
      error => console.log(error));
  }

  branchDetails(id: string) {
    this.router.navigate(['branchDetails', id]);
  }

  branchUpdate(branch: Branch) {
    this.router.navigate(['updateBranch', branch]);
  }
}
