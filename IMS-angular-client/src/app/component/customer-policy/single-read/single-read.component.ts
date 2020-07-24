import { Component, OnInit } from '@angular/core';
import {CustomerPolicy} from '../customerPolicy';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerPolicyService} from '../../../service/customer-policy.service';

@Component({
  selector: 'app-single-read',
  templateUrl: './single-read.component.html',
  styleUrls: ['./single-read.component.css']
})
export class SingleReadComponent implements OnInit {

  customerPolicyId: string;
  customerPolicy: CustomerPolicy;

  constructor(private route: ActivatedRoute, private router: Router, private customerPolicyService: CustomerPolicyService) {
  }

  ngOnInit(): void {
    this.customerPolicy = new CustomerPolicy();
    this.customerPolicyId = this.route.snapshot.params['customerPolicyId'];


    this.customerPolicyService.getCustomerPolicy(this.customerPolicyId).subscribe(data => {
        console.log(data);
        this.customerPolicy = data;
      },
      error => console.log(error));
  }

  backToCustomerPolicies() {
    this.router.navigate(['customerPolicies']);
  }

  customerPolicyUpdate(customerPolicy: CustomerPolicy) {
    this.router.navigate(['updateCustomerPolicy', customerPolicy]);
  }

  backToAgents() {
    this.router.navigate(['agents']);
  }

  backToManagers() {
    this.router.navigate(['managers']);
  }

  backToCustomers() {
    this.router.navigate(['customers']);
  }

  backToPolicy() {
    this.router.navigate(['policies']);
  }

}
