import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {CustomerPolicy} from '../customerPolicy';

@Component({
  selector: 'app-update-customer-policy',
  templateUrl: './update-customer-policy.component.html',
  styleUrls: ['./update-customer-policy.component.css']
})
export class UpdateCustomerPolicyComponent implements OnInit {

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

  updateCustomerPolicy() {
    this.customerPolicyService.updateCustomerPolicy(this.customerPolicy, this.customerPolicyId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.customerPolicy = new CustomerPolicy();
    this.gotoList();
  }

  onSubmit() {
    this.updateCustomerPolicy();
  }

  gotoList() {
    this.router.navigate(['/customerPolicies']);
  }
}
