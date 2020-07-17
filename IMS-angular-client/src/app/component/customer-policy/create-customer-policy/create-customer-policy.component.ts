import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerPolicy} from '../customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';

@Component({
  selector: 'app-create-customer-policy',
  templateUrl: './create-customer-policy.component.html',
  styleUrls: ['./create-customer-policy.component.css']
})
export class CreateCustomerPolicyComponent implements OnInit {

  customerPolicy: CustomerPolicy = new CustomerPolicy();

  constructor(private route: ActivatedRoute, private router: Router, private customerPolicyService: CustomerPolicyService) {
  }

  ngOnInit(): void {
  }

  savePolicy() {
    this.customerPolicyService.createCustomerPolicy(this.customerPolicy).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.customerPolicy = new CustomerPolicy();
    this.gotoList();
  }

  onSubmit() {
    this.savePolicy();
  }

  gotoList() {
    this.router.navigate(['/customerPolicies']);
  }
}
