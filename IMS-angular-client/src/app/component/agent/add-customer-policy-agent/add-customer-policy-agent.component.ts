import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Agent} from '../../agent/agent';
import {CustomerPolicy} from '../../customer-policy/customerPolicy';
import {CustomerPolicyService} from '../../../service/customer-policy.service';
import {AgentService} from '../../../service/agent.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-customer-policy-agent',
  templateUrl: './add-customer-policy-agent.component.html',
  styleUrls: ['./add-customer-policy-agent.component.css']
})
export class AddCustomerPolicyAgentComponent implements OnInit {

  agentList: Observable<Agent[]>;
  customerPolicyList: Observable<CustomerPolicy[]>;
  agent: Agent;
  agentId: string;
  customerPolicyId: string;
  public error: any;

  constructor(private customerPolicyService: CustomerPolicyService, private agentService: AgentService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchAgentList();
    this.fetchCustomerPolicyList();
  }

  fetchAgentList() {
    this.agentList = this.agentService.getAllAgents();
  }

  fetchCustomerPolicyList() {
    this.customerPolicyList = this.customerPolicyService.getAllCustomerPolicies();
  }

  addCustomerPolicyToAgent() {
    this.agentService.addCustomerPolicyToAgent(this.agent, this.agentId, this.customerPolicyId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.agent = new Agent();
    this.goBack();
  }

  submitCustomerPolicy() {
    this.addCustomerPolicyToAgent();
  }

  goBack() {
    this.router.navigate(['agents']);
  }

  addNewLeft() {
    this.router.navigate(['addAgent']);
  }

  addNewRight() {
    this.router.navigate(['addCustomerPolicy']);
  }
}
