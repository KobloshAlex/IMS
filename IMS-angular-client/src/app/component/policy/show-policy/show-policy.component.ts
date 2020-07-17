import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {PolicyService} from '../../../service/policy.service';
import {Policy} from '../policy';

@Component({
  selector: 'app-show-policy',
  templateUrl: './show-policy.component.html',
  styleUrls: ['./show-policy.component.css']
})
export class ShowPolicyComponent implements OnInit {

  policies: Observable<Policy[]>;

  constructor(private policyService: PolicyService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchPolicyList();
  }

  fetchPolicyList() {
    this.policies = this.policyService.getAllPolicies();
  }

  deletePolicy(id: string) {
    this.policyService.deletePolicy(id).subscribe(data => {
        console.log(data);
        this.fetchPolicyList();
      },
      error => console.log(error));
  }

  policyDetails(id: string) {
    this.router.navigate(['policyDetails', id]);
  }

  policyUpdate(policy: Policy) {
    this.router.navigate(['updatePolicy', policy]);
  }
}
