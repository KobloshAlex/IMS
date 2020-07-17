import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Customer} from '../../customer/customer';
import {CustomerPolicy} from '../../customer-policy/customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {CustomerService} from '../../../service/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-customer-policy-customer',
  templateUrl: './add-customer-policy-customer.component.html',
  styleUrls: ['./add-customer-policy-customer.component.css']
})
export class AddCustomerPolicyCustomerComponent implements OnInit {

  customerList: Observable<Customer[]>;
  customerPolicyList: Observable<CustomerPolicy[]>;
  customer: Customer;
  customerId: string;
  customerPolicyId: string;
  public error: any;

  constructor(private customerPolicyService: CustomerPolicyService, private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchCustomerList();
    this.fetchCustomerPolicyList();
  }

  fetchCustomerList() {
    this.customerList = this.customerService.getAllCustomers();
  }

  fetchCustomerPolicyList() {
    this.customerPolicyList = this.customerPolicyService.getAllCustomerPolicies();
  }

  addCustomerPolicyToCustomer() {
    this.customerService.addCustomerPolicyToCustomer(this.customer, this.customerId, this.customerPolicyId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.customer = new Customer();
    this.goBack();
  }

  submitCustomerPolicy() {
    this.addCustomerPolicyToCustomer();
  }

  goBack() {
    this.router.navigate(['customers']);
  }

  addNewLeft() {
    this.router.navigate(['add']);
  }

  addNewRight() {
    this.router.navigate(['addCustomerPolicy']);
  }
}
