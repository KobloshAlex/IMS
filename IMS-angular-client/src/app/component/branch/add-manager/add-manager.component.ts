import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Manager} from '../../manager/manager';
import {Branch} from '../branch';
import {ManagerService} from '../../../service/manager.service';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-add-manager',
  templateUrl: './add-manager.component.html',
  styleUrls: ['./add-manager.component.css']
})
export class AddManagerComponent implements OnInit {

  branchList: Observable<Branch[]>;
  managerList: Observable<Manager[]>;
  branch: Branch;
  branchId: string;
  managerId: string;
  public error: any;

  constructor(private managerService: ManagerService, private branchService: BranchService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchBranchList();
    this.fetchManagerList();
  }

  fetchBranchList() {
    this.branchList = this.branchService.getAllBranchs();
  }

  fetchManagerList() {
    this.managerList = this.managerService.getAllManagers();
  }

  addManagerToBranch() {
    this.branchService.addManagerToBranch(this.branch, this.branchId, this.managerId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.branch = new Branch();
    this.goBack();
  }

  submitManager() {
    this.addManagerToBranch();
  }

  goBack() {
    this.router.navigate(['branches']);
  }

  addNewLeft() {
    this.router.navigate(['addBranch']);
  }

  addNewRight() {
    this.router.navigate(['addManager']);
  }
}
