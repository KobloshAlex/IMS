import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Branch} from '../branch';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-read-branch',
  templateUrl: './read-branch.component.html',
  styleUrls: ['./read-branch.component.css']
})
export class ReadBranchComponent implements OnInit {

  branchId: string;
  branch: Branch;

  constructor(private route: ActivatedRoute, private router: Router, private branchService: BranchService) {
  }

  ngOnInit(): void {
    this.branch = new Branch();

    this.branchId = this.route.snapshot.params['branchId'];


    this.branchService.getBranch(this.branchId).subscribe(data => {
        console.log(data);
        this.branch = data;
      },
      error => console.log(error));
  }

  backToBranch() {
    this.router.navigate(['branches']);
  }

  branchUpdate(branch: Branch) {
    this.router.navigate(['updateBranch', branch]);
  }
}
