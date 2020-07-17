import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Policy} from '../policy';
import {PolicyService} from '../../../service/policy.service';

@Component({
  selector: 'app-update-policy',
  templateUrl: './update-policy.component.html',
  styleUrls: ['./update-policy.component.css']
})
export class UpdatePolicyComponent implements OnInit {

  policyId: string;
  policy: Policy;

  constructor(private route: ActivatedRoute, private router: Router, private policyService: PolicyService) {
  }

  ngOnInit(): void {
    this.policy = new Policy();
    this.policyId = this.route.snapshot.params['policyId'];

    this.policyService.getPolicy(this.policyId).subscribe(data => {
        console.log(data);
        this.policy = data;
      },
      error => console.log(error));
  }

  updatePolicy() {
    this.policyService.updatePolicy(this.policy, this.policyId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.policy = new Policy();
    this.gotoList();
  }

  onSubmit() {
    this.updatePolicy();
  }

  gotoList() {
    this.router.navigate(['/policies']);
  }
}
