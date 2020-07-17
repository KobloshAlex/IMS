import {Component, OnInit} from '@angular/core';
import {Customer} from '../customer';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../../service/customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  customerId: string;
  customer: Customer;

  constructor(private route: ActivatedRoute, private router: Router, private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.customer = new Customer();
    this.customerId = this.route.snapshot.params['customerId'];

    this.customerService.getCustomer(this.customerId).subscribe(data => {
        console.log(data);
        this.customer = data;
      },
      error => console.log(error));
  }

  updateCustomer() {
    this.customerService.updateCustomer(this.customer, this.customerId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.customer = new Customer();
    this.gotoList();
  }

  onSubmit() {
    this.updateCustomer();
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }
}
