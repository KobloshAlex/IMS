import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Manager} from '../manager';
import {CustomerPolicy} from '../../customer-policy/customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-add-customer-policy',
  templateUrl: './add-customer-policy.component.html',
  styleUrls: ['./add-customer-policy.component.css']
})
export class AddCustomerPolicyComponent implements OnInit {

  managerList: Observable<Manager[]>;
  customerPolicyList: Observable<CustomerPolicy[]>;
  manager: Manager;
  managerId: string;
  customerPolicyId: string;
  public error: any;

  constructor(private customerPolicyService: CustomerPolicyService, private managerService: ManagerService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchManagerList();
    this.fetchCustomerPolicyList();
  }

  fetchManagerList() {
    this.managerList = this.managerService.getAllManagers();
  }

  fetchCustomerPolicyList() {
    this.customerPolicyList = this.customerPolicyService.getAllCustomerPolicies();
  }

  addCustomerPolicyToManager() {
    this.managerService.addCustomerPolicyToManager(this.manager, this.managerId, this.customerPolicyId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.manager = new Manager();
    this.goBack();
  }

  submitCustomerPolicy() {
    this.addCustomerPolicyToManager();
  }

  goBack() {
    this.router.navigate(['managers']);
  }
}
