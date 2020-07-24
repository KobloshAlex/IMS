import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Branch} from '../branch';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-create-branch',
  templateUrl: './create-branch.component.html',
  styleUrls: ['./create-branch.component.css']
})
export class CreateBranchComponent implements OnInit {
  isError = false;
  errorMessage = '';
  branch: Branch = new Branch();

  constructor(private route: ActivatedRoute, private router: Router, private branchService: BranchService) {
  }

  ngOnInit(): void {
  }

  saveBranch() {
    this.branchService.createBranch(this.branch).subscribe(data => {
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.branch = new Branch();
    if (this.isError == true) {
      this.gotoList();
    }
  }

  onSubmit() {
    this.saveBranch();
  }

  gotoList() {
    this.router.navigate(['/branches']);
  }
}
