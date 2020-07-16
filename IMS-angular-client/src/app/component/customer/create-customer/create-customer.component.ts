import {Component, OnInit} from '@angular/core';
import {Customer} from '../customer';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../../service/customer-service/customer.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {


  customer: Customer = new Customer();

  constructor(private route: ActivatedRoute, private router: Router, private customerService: CustomerService) {
  }

  ngOnInit(): void {
  }

  saveCustomer() {
    this.customerService.createCustomer(this.customer).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.customer = new Customer();
    this.gotoList();
  }

  onSubmit() {
    this.saveCustomer();
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }

}
