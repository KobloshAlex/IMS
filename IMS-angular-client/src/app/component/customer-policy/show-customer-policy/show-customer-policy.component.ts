import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {CustomerPolicy} from '../customerPolicy';

@Component({
  selector: 'app-show-customer-policy',
  templateUrl: './show-customer-policy.component.html',
  styleUrls: ['./show-customer-policy.component.css']
})
export class ShowCustomerPolicyComponent implements OnInit {

  customerPolicy: Observable<CustomerPolicy[]>;

  constructor(private customerPolicyService: CustomerPolicyService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchCustomerPolicyList();
  }

  fetchCustomerPolicyList() {
    this.customerPolicy = this.customerPolicyService.getAllCustomerPolicies();
  }

  deleteCustomerPolicy(id: string) {
    this.customerPolicyService.deleteCustomerPolicy(id).subscribe(data => {
        console.log(data);
        this.fetchCustomerPolicyList();
      },
      error => console.log(error));
  }

  customerPolicyDetails(id: string) {
    this.router.navigate(['customerPolicyDetails', id]);
  }

  customerPolicyUpdate(customerPolicy: CustomerPolicy) {
    this.router.navigate(['updateCustomerPolicy', customerPolicy]);
  }
}
