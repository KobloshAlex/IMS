import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Customer} from '../customer';
import {CustomerService} from '../../../service/customer-service/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-customers',
  templateUrl: './show-customers.component.html',
  styleUrls: ['./show-customers.component.css']
})
export class ShowCustomersComponent implements OnInit {

  customers: Observable<Customer[]>;

  constructor(private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchCustomerList();
  }

  fetchCustomerList() {
    this.customers = this.customerService.getAllCustomers();
  }

  deleteCustomer(id: string) {
    this.customerService.deleteCustomer(id).subscribe(data => {
        console.log(data);
        this.fetchCustomerList();
      },
      error => console.log(error));
  }

  customerDetails(id: string) {
    this.router.navigate(['details', id]);
  }

  customerUpdate(customer: Customer) {
    this.router.navigate(['update', customer]);
  }

}
