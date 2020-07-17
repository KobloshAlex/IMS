import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PolicyService} from '../../../service/policy.service';
import {Policy} from '../policy';

@Component({
  selector: 'app-read-policy',
  templateUrl: './read-policy.component.html',
  styleUrls: ['./read-policy.component.css']
})
export class ReadPolicyComponent implements OnInit {

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

  backToPolicy() {
    this.router.navigate(['policies']);
  }

  policyUpdate(policy: Policy) {
    this.router.navigate(['updatePolicy', policy]);
  }
}
