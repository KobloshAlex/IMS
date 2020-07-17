import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Policy} from '../policy';
import {CustomerPolicy} from '../../customer-policy/customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {PolicyService} from '../../../service/policy.service';

@Component({
  selector: 'app-add-customer-policy-policy',
  templateUrl: './add-customer-policy-policy.component.html',
  styleUrls: ['./add-customer-policy-policy.component.css']
})
export class AddCustomerPolicyPolicyComponent implements OnInit {

  policyList: Observable<Policy[]>;
  customerPolicyList: Observable<CustomerPolicy[]>;
  policy: Policy;
  policyId: string;
  customerPolicyId: string;
  public error: any;

  constructor(private customerPolicyService: CustomerPolicyService, private policyService: PolicyService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchPolicyList();
    this.fetchCustomerPolicyList();
  }

  fetchPolicyList() {
    this.policyList = this.policyService.getAllPolicies();
  }

  fetchCustomerPolicyList() {
    this.customerPolicyList = this.customerPolicyService.getAllCustomerPolicies();
  }

  addCustomerPolicyToPolicy() {
    this.policyService.addCustomerPolicyToPolicy(this.policy, this.policyId, this.customerPolicyId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.policy = new Policy();
    this.goBack();
  }

  submitCustomerPolicy() {
    this.addCustomerPolicyToPolicy();
  }

  goBack() {
    this.router.navigate(['policies']);
  }

  addNewLeft() {
    this.router.navigate(['addPolicy']);
  }

  addNewRight() {
    this.router.navigate(['addCustomerPolicy']);
  }

}
