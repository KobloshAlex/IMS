import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Manager} from '../manager';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-update-manager',
  templateUrl: './update-manager.component.html',
  styleUrls: ['./update-manager.component.css']
})
export class UpdateManagerComponent implements OnInit {

  managerId: string;
  manager: Manager;

  constructor(private route: ActivatedRoute, private router: Router, private managerService: ManagerService) {
  }

  ngOnInit(): void {
    this.manager = new Manager();
    this.managerId = this.route.snapshot.params['branchManagerId'];

    this.managerService.getManager(this.managerId).subscribe(data => {
        console.log(data);
        this.manager = data;
      },
      error => console.log(error));
  }

  updateManager() {
    this.managerService.updateManager(this.manager, this.managerId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.manager = new Manager();
    this.gotoList();
  }

  onSubmit() {
    this.updateManager();
  }

  gotoList() {
    this.router.navigate(['/managers']);
  }
}
