import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Branch} from '../branch';
import {Customer} from '../../customer/customer';
import {CustomerService} from '../../../service/customer.service';
import {BranchService} from '../../../service/branch.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  branchList: Observable<Branch[]>;
  customerList: Observable<Customer[]>;
  branch: Branch;
  branchId: string;
  customerId: string;
  public error: any;

  constructor(private customerService: CustomerService, private branchService: BranchService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchBranchList();
    this.fetchCustomerList();
  }

  fetchBranchList() {
    this.branchList = this.branchService.getAllBranchs();
  }

  fetchCustomerList() {
    this.customerList = this.customerService.getAllCustomers();
  }

  addCustomerToBranch() {
    this.branchService.addCustomerToBranch(this.branch, this.branchId, this.customerId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.branch = new Branch();
    this.goBack();
  }

  submitCustomer() {
    this.addCustomerToBranch();
  }

  goBack() {
    this.router.navigate(['branches']);
  }

  addNewLeft() {
    this.router.navigate(['addBranch']);
  }

  addNewRight() {
    this.router.navigate(['addCustomer']);
  }

}
