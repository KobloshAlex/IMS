import {Component, OnInit} from '@angular/core';
import {Customer} from '../customer';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../../service/customer.service';

@Component({
  selector: 'app-read-customer',
  templateUrl: './read-customer.component.html',
  styleUrls: ['./read-customer.component.css']
})
export class ReadCustomerComponent implements OnInit {

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

  backToCustomers() {
    this.router.navigate(['customers']);
  }

  customerUpdate(customer: Customer) {
    this.router.navigate(['update', customer]);
  }

  backToBranch() {
    this.router.navigate(['branches']);
  }
}
