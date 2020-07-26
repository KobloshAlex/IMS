import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerPolicy} from '../customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {Ceo} from '../../ceo/Ceo';

@Component({
  selector: 'app-create-customer-policy',
  templateUrl: './create-customer-policy.component.html',
  styleUrls: ['./create-customer-policy.component.css']
})
export class CreateCustomerPolicyComponent implements OnInit {
  isError = false;
  isSuccessful = false;
  errorMessage = '';
  customerPolicy: CustomerPolicy = new CustomerPolicy();

  constructor(private route: ActivatedRoute, private router: Router, private customerPolicyService: CustomerPolicyService) {
  }

  ngOnInit(): void {
  }

  savePolicy() {
    this.customerPolicyService.createCustomerPolicy(this.customerPolicy).subscribe(data => {
        this.isSuccessful = true
        this.isError = false;
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.customerPolicy = new CustomerPolicy();
  }

  onSubmit() {
    this.savePolicy();
  }

  gotoList() {
    this.router.navigate(['/customerPolicies']);
  }
}
