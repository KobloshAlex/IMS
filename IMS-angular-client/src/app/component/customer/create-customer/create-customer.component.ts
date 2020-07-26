import {Component, OnInit} from '@angular/core';
import {Customer} from '../customer';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../../service/customer.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
  isError = false;
  isSuccessful = false;
  errorMessage = '';
  customer: Customer = new Customer();

  constructor(private route: ActivatedRoute, private router: Router, private customerService: CustomerService) {
  }

  ngOnInit(): void {
  }

  saveCustomer() {
    this.customerService.createCustomer(this.customer).subscribe(data => {
        this.isSuccessful = true
        this.isError = false;
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.customer = new Customer();
  }

  onSubmit() {
    this.saveCustomer();
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }
}
