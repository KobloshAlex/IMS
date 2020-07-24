import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Policy} from '../policy';
import {PolicyService} from '../../../service/policy.service';
import {Ceo} from '../../ceo/Ceo';

@Component({
  selector: 'app-create-policy',
  templateUrl: './create-policy.component.html',
  styleUrls: ['./create-policy.component.css']
})
export class CreatePolicyComponent implements OnInit {
  isError = false;
  errorMessage = '';
  policy: Policy = new Policy();

  constructor(private route: ActivatedRoute, private router: Router, private policyService: PolicyService) {
  }

  ngOnInit(): void {
  }

  savePolicy() {
    this.policyService.createPolicy(this.policy).subscribe(data => {
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.policy = new Policy();
    if (this.isError == true) {
      this.gotoList();
    }
  }

  onSubmit() {
    this.savePolicy();
  }

  gotoList() {
    this.router.navigate(['/policies']);
  }
}
