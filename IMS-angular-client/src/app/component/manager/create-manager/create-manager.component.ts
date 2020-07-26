import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Manager} from '../manager';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-create-manager',
  templateUrl: './create-manager.component.html',
  styleUrls: ['./create-manager.component.css']
})
export class CreateManagerComponent implements OnInit {
  isError = false;
  isSuccessful = false;
  errorMessage = '';
  manager: Manager = new Manager();

  constructor(private route: ActivatedRoute, private router: Router, private managerService: ManagerService) {
  }

  ngOnInit(): void {
  }

  saveManager() {
    this.managerService.createManager(this.manager).subscribe(data => {
        console.log(data);
        this.isSuccessful = true
        this.isError = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.manager = new Manager();
  }

  onSubmit() {
    this.saveManager();
  }

  gotoList() {
    this.router.navigate(['/managers']);
  }
}
