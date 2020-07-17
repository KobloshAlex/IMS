import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Branch} from '../branch';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-update-branch',
  templateUrl: './update-branch.component.html',
  styleUrls: ['./update-branch.component.css']
})
export class UpdateBranchComponent implements OnInit {

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

  updateBranch() {
    this.branchService.updateBranch(this.branch, this.branchId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.branch = new Branch();
    this.gotoList();
  }

  onSubmit() {
    this.updateBranch();
  }

  gotoList() {
    this.router.navigate(['/branches']);
  }
}
